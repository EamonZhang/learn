package lucene;
import java.io.IOException;  
import java.io.Reader;  
import java.io.StringReader;  
  



import org.apache.lucene.analysis.Analyzer;  
import org.apache.lucene.analysis.TokenStream;  
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;  
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;  
import org.apache.lucene.analysis.util.CharTokenizer;
//import org.apache.lucene.analysis.tokenattributes.TermAttribute;  
import org.apache.lucene.util.Version;  
//import org.wltea.analyzer.lucene.IKAnalyzer;  
  
/** 
 * 分词器测试类，支持lucene3.0以上版本 
 * 分词器IKAnalyzer3.2.5Stable.jar 
 * @author hpjianhua 
 * @Date 2011 04 08 
 * 
 */  
public class TokenTest {  
      
    //  要分词的字符串  
    private static String text = "中国，古时通常泛指中原地区，与中华中夏 中土中州含义相同。古代华夏族、汉族建国于黄河流域一带，以为居天下之中，故称中国";  
    /** 
     * @param args 
     * @throws IOException  
     */  
    public static void main(String[] args) throws IOException {  
        Analyzer analyzer = new SimpleAnalyzer (Version.LUCENE_46);  
        System.out.println("======中文=======IKAnalyzer======分词=======");  
        showToken(analyzer, text);  
          
        Analyzer standardAnalyzer = new StandardAnalyzer(Version.LUCENE_46);  
        System.out.println("=====一元========StandardAnalyzer=====分词========");  
        showToken(standardAnalyzer, text);  
    }  
      
    /** 
     * 分词及打印分词结果的方法 
     * @param analyzer     分词器名称 
     * @param text         要分词的字符串 
     * @throws IOException 抛出的异常 
     */  
    public static void showToken(Analyzer analyzer, String text) throws IOException {  
          
        Reader reader = new StringReader(text);  
        TokenStream stream = (TokenStream)analyzer.tokenStream("", reader);  
//        CharTokenizer
        //添加工具类  注意：以下这些与之前lucene2.x版本不同的地方  
        CharTermAttribute termAtt  = (CharTermAttribute)stream.addAttribute(CharTermAttribute.class);  
        OffsetAttribute offAtt  = (OffsetAttribute)stream.addAttribute(OffsetAttribute.class);  
        // 循环打印出分词的结果，及分词出现的位置  
        stream.reset();
        while(stream.incrementToken()){
            System.out.print(termAtt.toString()+ "|("+ offAtt.startOffset() + " " + offAtt.endOffset()+")"); 
        }  
        System.out.println();  
    }  
}
  