package hanlp;

import java.util.Map;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.dictionary.BaseSearcher;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;

public class Dictionary {

	public static void main(String[] args) {
		// 动态增加
		CustomDictionary.add("孔雀女");
		// 强行插入
		CustomDictionary.insert("码农", "nz 1024");
		// 删除词语（注释掉试试）
		// CustomDictionary.remove("码农");
		System.out.println(CustomDictionary.add("裸婚", "v 2 nz 1"));
		System.out.println(CustomDictionary.get("裸婚"));

		String text = "码农和孔雀女裸婚了";  // 怎么可能噗哈哈！

		// AhoCorasickDoubleArrayTrie自动机分词
		final char[] charArray = text.toCharArray();
		CoreDictionary.trie.parseText(charArray, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>()
		{
		    @Override
		    public void hit(int begin, int end, CoreDictionary.Attribute value)
		    {
		        System.out.printf("[%d:%d]=%s %s\n", begin, end, new String(charArray, begin, end - begin), value);
		    }
		});
		// trie树分词
		BaseSearcher searcher = CustomDictionary.getSearcher(text);
		Map.Entry entry;
		while ((entry = searcher.next()) != null)
		{
		    System.out.println(entry);
		}

		// 标准分词
		System.out.println(HanLP.segment(text));
	}

}
