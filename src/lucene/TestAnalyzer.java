package lucene;

//SimpleAnalyzer
//StandardAnalyzer
//WhitespaceAnalyzer
//StopAnalyzer
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

//import net.paoding.analysis.analyzer.PaodingAnalyzer;
import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.SimpleAnalyzer;
//import org.apache.lucene.analysis.StopAnalyzer;
//import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
//import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.util.AttributeSource;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class TestAnalyzer {
  private static String testString1 = "The quick brown fox jumped over the lazy dogs";
  private static String testString2 = "xy&z mail is - xyz@sohu.com";
  private static String testString3 = "我中国人，你不是中国人,我爱我的祖国，中华人民共和国万岁！";
  public static void testIkAnalyzer(String testString) throws IOException{
		IKAnalyzer ikanalyzer = new IKAnalyzer(true);
		StringReader reader=new StringReader(testString);
		TokenStream ts=ikanalyzer.tokenStream("", reader);
	      System.out.println("=====IKAnalyzer analyzer====");
	      System.out.println("分析方法：建议应用,比较强大，两种模式");
		CharTermAttribute term=ts.getAttribute(CharTermAttribute.class);
		OffsetAttribute offAttr = ts.getAttribute(OffsetAttribute.class);
		ts.reset();
		while(ts.incrementToken()){
			System.out.println(term+" ("+offAttr.startOffset()+","+offAttr.endOffset()+")");
		}
  }
  
  public static void testWhitespace(String testString) throws Exception{
      Analyzer analyzer = new WhitespaceAnalyzer(Version.LUCENE_46);     
      Reader r = new StringReader(testString);     
      Tokenizer ts = (Tokenizer) analyzer.tokenStream("", r);
      System.out.println("=====Whitespace analyzer====");
      System.out.println("分析方法：空格分割");
      CharTermAttribute ct = ts.getAttribute(CharTermAttribute.class);
      OffsetAttribute offAttr = ts.getAttribute(OffsetAttribute.class);
      ts.reset();//这个很关键
      while (ts.incrementToken()) {
    		System.out.println(ct+" ("+offAttr.startOffset()+","+offAttr.endOffset()+")");
      }
  }
  public static void testSimple(String testString) throws Exception{
      Analyzer analyzer = new SimpleAnalyzer(Version.LUCENE_46);     
      Reader r = new StringReader(testString);     
      Tokenizer ts = (Tokenizer) analyzer.tokenStream("", r);     
      System.out.println("=====Simple analyzer====");
      System.out.println("分析方法：空格及各种符号分割");
      CharTermAttribute ct = ts.getAttribute(CharTermAttribute.class);
      OffsetAttribute offAttr = ts.getAttribute(OffsetAttribute.class);
      while (ts.incrementToken()) {     
    	  System.out.println(ct+" ("+offAttr.startOffset()+","+offAttr.endOffset()+")");
      }    
  }
  public static void testStop(String testString) throws Exception{
      Analyzer analyzer = new StopAnalyzer(Version.LUCENE_46);     
      Reader r = new StringReader(testString);     
      StopFilter sf = (StopFilter) analyzer.tokenStream("", r);
      System.err.println("=====stop analyzer====");
      System.err.println("分析方法：空格及各种符号分割,去掉停止词，停止词包括 is,are,in,on,the等无实际意义的词");
      //停止词
      CharTermAttribute ct = sf.getAttribute(CharTermAttribute.class);
      OffsetAttribute offAttr = sf.getAttribute(OffsetAttribute.class);
      while (sf.incrementToken()) {     
    	System.out.println(ct+" ("+offAttr.startOffset()+","+offAttr.endOffset()+")");  
      }
  }
  public static void testStandard(String testString) throws Exception{
      Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);     
      Reader r = new StringReader(testString);     
      StopFilter sf = (StopFilter) analyzer.tokenStream("", r);
      System.err.println("=====standard analyzer====");
      System.err.println("分析方法：混合分割,包括了去掉停止词，支持汉语");
      CharTermAttribute ct = sf.getAttribute(CharTermAttribute.class);
      OffsetAttribute offAttr = sf.getAttribute(OffsetAttribute.class);
      while (sf.incrementToken()) {     
    	  System.out.println(ct+" ("+offAttr.startOffset()+","+offAttr.endOffset()+")");  
      }    
  }
//  public static void testPaoding(String testString) throws Exception{
//      {
//          Analyzer analyzer = new PaodingAnalyzer();
//          String indexString = testString;
//          StringReader reader = new StringReader(indexString);
//          TokenStream ts = analyzer.tokenStream(indexString, reader);
//          System.err.println("=====paoding analyzer====");
//          System.err.println("汉语分词庖丁解牛");
//          CharTermAttribute ct = ts.getAttribute(CharTermAttribute.class);
//          OffsetAttribute offAttr = ts.getAttribute(OffsetAttribute.class);
//          while (ts.incrementToken())
//          {
//        	  System.out.println(ct+" ("+offAttr.startOffset()+","+offAttr.endOffset()+")");  
//          }
//      }
//  }
  public static void main(String[] args) throws Exception{
//      String testString = testString1;
      String testString = "沈阳市人民政府";
      System.out.println(testString);
      testIkAnalyzer(testString);
//      testWhitespace(testString1);
//      testSimple(testString);
//      testStop(testString);
//      testStandard(testString);
//      testPaoding(testString);
  }

}
