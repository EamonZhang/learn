package lucene1.hightlighter;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.util.Version;

/**
 * Lucene version.5.1.0
 * @author zhangjin
 *
 */
public class Demo2 {

	public static void main(String[] args) throws IOException, InvalidTokenOffsetsException, ParseException {
		String text = "this is a book,i like book.";
	
		TokenStream tokenStream = new SimpleAnalyzer(Version.LUCENE_46).tokenStream("f", new StringReader(text));
		
		QueryParser parser = new QueryParser(Version.LUCENE_46, "f", new SimpleAnalyzer(Version.LUCENE_46));
		Query query = parser.parse("like book");
		//自定义标签
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<span class=\"highlighter\">","</span>");
		QueryScorer scorer = new QueryScorer(query,"f");
		Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
		//加入自定义高亮显示效果
		Highlighter highter = new Highlighter(formatter,scorer);
		highter.setTextFragmenter(fragmenter);
		
		String highterText = highter.getBestFragment(tokenStream, text);
		System.out.println(highterText);
	}
	
//this is a <span class="highlighter">book</span>,i <span class="highlighter">like</span> <span class="highlighter">book</span>.
	
}
