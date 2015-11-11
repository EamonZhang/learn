package lucene1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.synonym.SynonymFilterFactory;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.FilesystemResourceLoader;
import org.apache.lucene.util.Version;

public class SynoTools {
	private static SynonymFilterFactory factory = null;
	private static Analyzer analyzer = new WhitespaceAnalyzer(Version.LUCENE_46);
	static {
		try {
			Map<String, String> filterArgs = new HashMap<String, String>();
			filterArgs.put("luceneMatchVersion", Version.LUCENE_46.toString());
			filterArgs.put("synonyms", "config/synonyms.txt");
			filterArgs.put("expand", "true");
			factory = new SynonymFilterFactory(filterArgs);
			factory.inform(new FilesystemResourceLoader());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		String entityWord = convertSynonym("萨");
		System.out.println(entityWord);
	}

	/**
	 * 
	 * 此方法描述的是：针对上面方法拆分后的词组进行同义词匹配，返回TokenStream
	 */
	public static String convertSynonym(String input) throws IOException {
		String entityWord = null;
		TokenStream ts = factory.create(analyzer.tokenStream("someField", input));
		CharTermAttribute termAttr = ts.addAttribute(CharTermAttribute.class);
		ts.reset();
		while (ts.incrementToken()) {
			entityWord = termAttr.toString();
		}
		ts.end();
		ts.close();
		return entityWord;
	}
}
