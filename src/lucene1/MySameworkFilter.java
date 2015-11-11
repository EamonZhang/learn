package lucene1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.AttributeSource;

public class MySameworkFilter extends TokenFilter {

	// 保存相应的词汇
	private CharTermAttribute cta = null;
	// 保存词与词之间的位置增量
	private PositionIncrementAttribute pia = null;
	// 定义一个状态
	private AttributeSource.State current = null;
	// 用栈保存同义词集合
	private Stack<String> sames = null;

	protected MySameworkFilter(TokenStream input) {
		super(input);
		cta = this.addAttribute(CharTermAttribute.class);
		pia = this.addAttribute(PositionIncrementAttribute.class);
		sames = new Stack<String>();
	}

	@Override
	public boolean incrementToken() throws IOException {
		if (sames.size() > 0) {
			// 将元素出栈,并获取同义词
			String str = sames.pop();
			// 还原状态
			restoreState(current);
			// 先清空,再添加
			cta.setEmpty();
			cta.append(str);
			// 设置位置为0,表示同义词
			pia.setPositionIncrement(0);
			return true;
		}

		if (!this.input.incrementToken())
			return false;

		// 如果改词中有同义词,捕获当前状态
		if (this.getSamewords(cta.toString())) {
			current = captureState();
		}

		return true;
	}

	// 定义同义词字典,并判断如果有同义词就返回true
	private boolean getSamewords(String key) {
		Map<String, String[]> maps = new HashMap<String, String[]>();
		maps.put("我", new String[] { "咱", "俺" });
		maps.put("中国", new String[] { "大陆", "天朝" });

		if (maps.get(key) != null) {
			for (String s : maps.get(key)) {
				sames.push(s);
			}
		}

		if (sames.size() > 0) {
			return true;
		}
		return false;
	}

}