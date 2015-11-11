package lucene1;
import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.MergePolicy.OneMerge;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
//import org.lionsoul.jcseg.analyzer.JcsegAnalyzer4X;
//import org.lionsoul.jcseg.core.JcsegTaskConfig;
import org.wltea.analyzer.lucene.IKAnalyzer;

/** 
 * 此类描述的是：
 * @author yax 2015-1-28 下午8:53:37 
 * @version v1.0 
 */
public class MyIndexer {
	
	public static void createIndex(String indexPath, String[] contexts) throws IOException{
		Directory directory = FSDirectory.open(new File(indexPath));
		Analyzer analyzer = new IKAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_46, analyzer);
		IndexWriter indexWriter = new IndexWriter(directory, config);
		indexWriter.deleteAll();
		for (String context : contexts) {
			Document document = new Document();
			document.add(new TextField("title", context, Store.YES));
			indexWriter.addDocument(document);
		}
		indexWriter.forceMerge(1);
		indexWriter.close();
		
	}

}