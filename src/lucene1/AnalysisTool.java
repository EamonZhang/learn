//package lucene1;
//
//import java.io.IOException;
//import java.io.Reader;
//import java.io.StringReader;
//
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.TokenStream;
//import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
//import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
//import org.wltea.analyzer.lucene.IKAnalyzer;
//
//import com.chenlb.mmseg4j.Dictionary;
//
//public class AnalysisTool extends Analyzer {
//
//	public static void main(String[] args) throws IOException {
//		AnalysisTool ikanalyzer = new AnalysisTool();
//		StringReader reader = new StringReader("");
//		TokenStream ts = ikanalyzer.tokenStream("", reader);
//		CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
//		OffsetAttribute offAttr = ts.getAttribute(OffsetAttribute.class);
//		ts.reset();
//		while (ts.incrementToken()) {
//			System.out.println(term + " (" + offAttr.startOffset() + "," + offAttr.endOffset() + ")");
//		}
//	}
//
//	@Override
//	protected TokenStreamComponents createComponents(String arg0, Reader arg1) {
//		   Dictionary dic=Dictionary.getInstance("F:\\官方包\\lucene-3.5.0\\mmseg4j-1.8.5\\data");
//		  return new MySameworkFilter(new MMSegTokenizer(new MaxWordSeg(dic), reader));
//	}
//}
