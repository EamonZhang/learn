//package hanlp;
//
//import java.io.StringReader;
//import java.util.HashSet;
//
//import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
//import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
//import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
//
//import ch.ethz.ssh2.util.Tokenizer;
//
//import com.hankcs.hanlp.tokenizer.IndexTokenizer;
//
//public class LucenePuls {
//
//	public static void main(String[] args) {
//		String text = "中华人民共和国很辽阔";
//		for (int i = 0; i < text.length(); ++i)
//		{
//		    System.out.print(text.charAt(i) + "" + i + " ");
//		}
//		System.out.println();
//		StringReader reader = new StringReader(text);
//		HashSet<String> filter = new HashSet<String>();
//		filter.add("很");
//		Tokenizer tokenizer = new HanLPTokenizer(IndexTokenizer.SEGMENT.enableOffset(true), reader, filter, false);
//		while (tokenizer.incrementToken())
//		{
//		    CharTermAttribute attribute = tokenizer.getAttribute(CharTermAttribute.class);
//		    // 偏移量
//		    OffsetAttribute offsetAtt = tokenizer.getAttribute(OffsetAttribute.class);
//		    // 距离
//		    PositionIncrementAttribute positionAttr = tokenizer.getAttribute(PositionIncrementAttribute.class);
//		    System.out.println(attribute + " " + offsetAtt.startOffset() + " " + offsetAtt.endOffset() + " " + positionAttr.getPositionIncrement());
////		}
//
//	}
//
//}
