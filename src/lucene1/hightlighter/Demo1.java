package lucene1.hightlighter;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.util.Version;

/**
 * Lucene version.5.1.0
 * @author zhangjin
 *
 */
public class Demo1 {

	public static void main(String[] args) throws IOException, InvalidTokenOffsetsException {
		String text = "this is a book,i like book.";
	
		TokenStream tokenStream = new SimpleAnalyzer(Version.LUCENE_46).tokenStream("f", new StringReader(text));
		
		TermQuery query = new TermQuery(new Term("f","book"));
		QueryScorer scorer = new QueryScorer(query,"f");
		Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
		Highlighter highter = new Highlighter(scorer);
		highter.setTextFragmenter(fragmenter);
		
		String highterText = highter.getBestFragment(tokenStream, text);
		System.out.println(highterText);
	}
	
	//this is a <B>book</B>,i like <B>book</B>.
}
