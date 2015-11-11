package hanlp;

import com.hankcs.hanlp.dictionary.CoreSynonymDictionary;

public class Distance {

	public static void main(String[] args) {
		long score = CoreSynonymDictionary.distance("妇婴", "孕婴");
		if(score == Long.MAX_VALUE){
			System.out.println("辞典中无");
		}else{
			System.out.println(score);
		}
//		Suggester suggester = new Suggester();
//		String[] titleArray =
//		(
//		        "威廉王子发表演说 呼吁保护野生动物\n" +
//		        "《时代》年度人物最终入围名单出炉 普京马云入选\n" +
//		        "“黑格比”横扫菲：菲吸取“海燕”经验及早疏散\n" +
//		        "日本保密法将正式生效 日媒指其损害国民知情权\n" +
//		        "英报告说空气污染带来“公共健康危机”"
//		).split("\\n");
//		for (String title : titleArray)
//		{
//		    suggester.addSentence(title);
//		}
//
//		System.out.println(suggester.suggest("保密", 1));       // 语义
//		System.out.println(suggester.suggest("危机公共", 1));   // 字符
//		System.out.println(suggester.suggest("mayun", 1));      // 拼
	}

}
