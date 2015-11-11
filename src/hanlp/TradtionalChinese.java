package hanlp;

import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;

public class TradtionalChinese {
	public static void main(String[] args) {
		System.out.println(HanLP.convertToTraditionalChinese("“以后等你当上皇后，就能买草莓庆祝了”"));
		System.out.println(HanLP.convertToSimplifiedChinese("用筆記簿型電腦寫程式HelloWorld"));
		String text = "美行科技有限公司";
		List<Pinyin> pinyinList = HanLP.convertToPinyinList(text);
		System.out.print("原文,");
		for (char c : text.toCharArray())
		{
		    System.out.printf("%c,", c);
		}
		System.out.println();

		System.out.print("拼音（数字音调）,");
		for (Pinyin pinyin : pinyinList)
		{
		    System.out.printf("%s,", pinyin);
		}
		System.out.println();

		System.out.print("拼音（符号音调）,");
		for (Pinyin pinyin : pinyinList)
		{
		    System.out.printf("%s,", pinyin.getPinyinWithToneMark());
		}
		System.out.println();

		System.out.print("拼音（无音调）,");
		for (Pinyin pinyin : pinyinList)
		{
		    System.out.printf("%s,", pinyin.getPinyinWithoutTone());
		}
		System.out.println();

		System.out.print("声调,");
		for (Pinyin pinyin : pinyinList)
		{
		    System.out.printf("%s,", pinyin.getTone());
		}
		System.out.println();

		System.out.print("声母,");
		for (Pinyin pinyin : pinyinList)
		{
		    System.out.printf("%s,", pinyin.getShengmu());
		}
		System.out.println();

		System.out.print("韵母,");
		for (Pinyin pinyin : pinyinList)
		{
		    System.out.printf("%s,", pinyin.getYunmu());
		}
		System.out.println();

		System.out.print("输入法头,");
		for (Pinyin pinyin : pinyinList)
		{
		    System.out.printf("%s,", pinyin.getHead());
		}
		System.out.println();
	}
}
