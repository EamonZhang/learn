package lucene1;

import java.io.Reader;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LetterTokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.util.Version;

public class UserDefinedAnalyzer extends Analyzer {

	// 定义禁用词集合
	private Set stops;

	// 无参构造器使用默认的禁用词分词器
	public UserDefinedAnalyzer() {
		stops = StopAnalyzer.ENGLISH_STOP_WORDS_SET;
	}

	/**
	 * 传一个禁用词数组
	 * 
	 * @param sws
	 */
	public UserDefinedAnalyzer(String[] sws) {
		// 使用stopFilter创建禁用词集合
		stops = StopFilter.makeStopSet(Version.LUCENE_46, sws, true);
		// 将默认的禁用词添加进集合
		stops.addAll(StopAnalyzer.ENGLISH_STOP_WORDS_SET);
	}

//	/**
//	 * 自定义分词器
//	 */
//	@Override
//	public TokenStream tokenStream(String str, Reader reader) {
//
//		return new StopFilter(Version.LUCENE_35, new LowerCaseFilter(Version.LUCENE_35, new LetterTokenizer(Version.LUCENE_35, reader)), stops);
//	}

	@Override
	protected TokenStreamComponents createComponents(String arg0, Reader arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}