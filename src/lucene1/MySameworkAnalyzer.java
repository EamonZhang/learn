package lucene1;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.util.packed.PackedInts.Reader;

import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.MaxWordSeg;
import com.chenlb.mmseg4j.analysis.MMSegTokenizer;

public class MySameworkAnalyzer extends Analyzer {

	// @Override
	// public TokenStream tokenStream(String str, Reader reader) {
	// //获取中文分词器的字段,我这里使用的是MMSeg4j的中文分词器
	// Dictionary
	// dic=Dictionary.getInstance("F:\\官方包\\lucene-3.5.0\\mmseg4j-1.8.5\\data");
	// return new MySameworkFilter(new MMSegTokenizer(new MaxWordSeg(dic),
	// reader));
	// }

	@Override
	protected TokenStreamComponents createComponents(String arg0, java.io.Reader arg1) {
		Dictionary dic = Dictionary.getInstance("/home/zhangjin/study/dic");
		return new TokenStreamComponents(new MMSegTokenizer(new MaxWordSeg(dic), arg1));
	}

	public static void main(String[] args) throws IOException {
		Analyzer analyzer = new MySameworkAnalyzer();
		StringReader reader = new StringReader("中国大放送的速读法三家庭");
		TokenStream ts = analyzer.tokenStream("somefield", reader);
		CharTermAttribute termAttr = ts.addAttribute(CharTermAttribute.class);
//		ts.reset();
		while (ts.incrementToken()) {
			System.out.println(termAttr.toString());
		}
		ts.end();
		ts.close();
	}
	// public void test05(){
	// try {
	// Analyzer a1=new MySameworkAnalyzer();
	// String str="我来自中国,我的名字叫什么";
	// AnalyzerUtil.displayToken(str, a1);
	// Directory directory=new RAMDirectory();
	// IndexWriter indexWriter=new IndexWriter(directory, new
	// IndexWriterConfig(Version.LUCENE_35, a1));
	// Document document=new Document();
	// document.add(new Field("content",
	// str,Field.Store.YES,Field.Index.ANALYZED));
	// indexWriter.addDocument(document);
	// indexWriter.close();
	// IndexReader indexReader=IndexReader.open(directory);
	// IndexSearcher searcher=new IndexSearcher(indexReader);
	// TopDocs tds=searcher.search(new TermQuery(new Term("content", "大陆")),
	// 10);
	// ScoreDoc[] docs=tds.scoreDocs;
	// Document doc=searcher.doc(docs[0].doc);
	// System.out.println(doc.get("content"));
	// searcher.close();
	// indexReader.close();
	// } catch (CorruptIndexException e) {
	// e.printStackTrace();
	// } catch (LockObtainFailedException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
}