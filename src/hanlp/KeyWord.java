package hanlp;

import java.util.List;

import com.hankcs.hanlp.HanLP;

public class KeyWord {

	public static void main(String[] args) {
		//太平街道南泉中医社区卫生服务站
		//[社区, 卫生, 中医, 太平街道]
		//太平街道南泉中医社区卫生站
		//[服务站, 社区, 卫生, 中医, 太平街道]
		String content = "太平街道南泉中医社区卫生服务站";
		List<String> keywordList = HanLP.extractKeyword(content,content.length()/2);
		System.out.println(keywordList);
	}

}
