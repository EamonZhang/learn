package lucene1;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.synonym.SynonymFilterFactory;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.FilesystemResourceLoader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;

/** 
 * 此类描述的是：
 * @author yax 2015-1-28 下午8:42:24 
 * @version v1.0 
 */
public class AnalyzerUtil {
	
	/**
	 * 
	     * 此方法描述的是：进行中文拆分
	 */
	public static String analyzeChinese(String input, boolean userSmart) throws IOException{
		StringBuffer sb = new StringBuffer();
        StringReader reader = new StringReader(input.trim());
        IKSegmenter ikSeg = new IKSegmenter(reader, userSmart);// true　用智能分词　，false细粒度
        for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next()) {
        	sb.append(lexeme.getLexemeText()).append(" ");
        }
        return sb.toString();
	}
	
	/**
	 * 
	     * 此方法描述的是：针对上面方法拆分后的词组进行同义词匹配，返回TokenStream
	 */
	public static TokenStream convertSynonym(String input) throws IOException{
        Version ver = Version.LUCENE_46;
        Map<String, String> filterArgs = new HashMap<String, String>();
        filterArgs.put("luceneMatchVersion", ver.toString());
        filterArgs.put("synonyms", "config/synonyms.txt");
        filterArgs.put("expand", "true");
        SynonymFilterFactory factory = new SynonymFilterFactory(filterArgs);
        factory.inform(new FilesystemResourceLoader());
        Analyzer analyzer = new WhitespaceAnalyzer(Version.LUCENE_46);
//        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
        TokenStream ts = factory.create(analyzer.tokenStream("someField", input));
        return ts;
	}
	
	/**
	 * 
	     * 此方法描述的是：将tokenstream拼成一个特地格式的字符串，交给IndexSearcher来处理
	 */
    public static String displayTokens(TokenStream ts) throws IOException
    {
    	StringBuffer sb = new StringBuffer();
        CharTermAttribute termAttr = ts.addAttribute(CharTermAttribute.class);
        ts.reset();
        while (ts.incrementToken())
        {
            String token = termAttr.toString();
            sb.append(token).append(" ");
            System.out.print(token+"|");
//            System.out.print(offsetAttribute.startOffset() + "-" + offsetAttribute.endOffset() + "[" + token + "] ");
        }
        System.out.println();
        ts.end();
        ts.close();
        return sb.toString();
    }
    
    public static void main(String[] args) {
    	String indexPath = "/home/zhangjin/study/index";
    	String input = "具";
    	String[] indexs = { "辽宁大学"};
    	System.out.println("**********************");
		try {
			String result = displayTokens(convertSynonym(analyzeChinese(input, true)));
			MyIndexer.createIndex(indexPath,indexs);
			List<String> docs = MySearcher.searchIndex(result, indexPath);
			for (String string : docs) {
				System.out.println(string);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}