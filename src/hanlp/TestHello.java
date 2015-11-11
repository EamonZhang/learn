package hanlp;

import com.hankcs.hanlp.HanLP;

public class TestHello {

	public static void main(String[] args) {
		// System.out.println(HanLP.segment("你好，欢迎使用HanLP！"));
		// // 标准分词=----------------
		// List<Term> termList = HanLP.segment("商品和服务");
		// System.out.println(termList);
		// NLP分词 会执行全部命名实体识别和词性标注。速度比标准分词慢，并且有误识别的情况
		// List<Term> termList =
		// NLPTokenizer.segment("中国科学院计算技术研究所的宗成庆教授正在教授自然语言处理课程");
		// System.out.println(termList);
		// 索引分词 是面向搜索引擎的分词器，能够对长词全切分，另外通过 term.offset 可以获取单词在文本中的偏移量。
		// List<Term> termList = IndexTokenizer.segment("主副食品");
		// for (Term term : termList)
		// {
		// System.out.println(term + " [" + term.offset + ":" + (term.offset +
		// term.word.length()) + "]");
		// }
		// 繁体分词 可以直接对繁体进行分词，输出切分后的繁体词语
		// List<Term> termList =
		// TraditionalChineseTokenizer.segment("大衛貝克漢不僅僅是名著名球員，球場以外，其妻為前辣妹合唱團成員維多利亞·碧咸，亦由於他擁有突出外表、百變髮型及正面的形象，以至自己品牌的男士香水等商品，及長期擔任運動品牌Adidas的代言人，因此對大眾傳播媒介和時尚界等方面都具很大的影響力，在足球圈外所獲得的認受程度可謂前所未見。");
		// System.out.println(termList);
		// 极速词典分词 极速分词是词典最长分词，速度极其快，精度一般
//		 String text = "江西鄱阳湖干枯，中国最大淡水湖变成大草原";
//		 System.out.println(SpeedTokenizer.segment(text));
//		 long start = System.currentTimeMillis();
//		 int pressure = 1000000;
//		 for (int i = 0; i < pressure; ++i)
//		 {
//		 SpeedTokenizer.segment(text);
//		 }
//		 double costTime = (System.currentTimeMillis() - start) /
//		 (double)1000;
//		 System.out.printf("分词速度：%.2f字每秒", text.length() * pressure /
//		 costTime);
		// N-最短路径分词N最短路分词器 NShortSegment 比最短路分词器( DijkstraSegment
		// )慢，但是效果稍微好一些，对命名实体识别能力更强。调用方法如下:
//		Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
//		Segment shortestSegment = new ViterbiSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
//		String[] testCase = new String[] { "刘喜杰石国祥会见吴亚琴先进事迹报告团成员", };
//		for (String sentence : testCase) {
//			System.out.println("N-最短分词：" + nShortSegment.seg(sentence) + "\n最短路分词：" + shortestSegment.seg(sentence));
//		}
		// CRF分词---需要/model/segment/CRFSegmentModel.mini.txt 
		//基于CRF模型和BEMS标注训练得到的分词器
		//CRF对新词有很好的识别能力，但是无法利用自定义词典。
		//也不支持命名实体识别，应用场景仅限于新词识别。
//		 Segment segment = new CRFSegment();
//		 segment.enablePartOfSpeechTagging(true);
//		 List<Term> termList = segment.seg("你看过穆赫兰道吗");
//		 System.out.println(termList);
//		 for (Term term : termList)
//		 {
//		 if (term.nature == null)
//		 {
//		 System.out.println("识别到新词：" + term.word);
//		 }
//		 }
//		 通过此工厂方法得到的是当前版本速度和效果最平衡的分词器:
		HanLP.Config.enableDebug();
			System.out.println(HanLP.newSegment().seg("同济药业批发长庆店"));
	}
}
