//以前用过的Lucene还停留在2.0时代，拿到4.0中完全不好用了。
//之前也只停留在了解的程度。现在系统的在学习一遍，User Vsersion Lucene4.6
package lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public class HelloLucene {
	public static void main(String[] args) throws Exception {
		  Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);

		    // Store the index in memory:
		    Directory directory = new RAMDirectory();
//		     To store an index on disk, use this instead:
//		    Directory directory = FSDirectory.open("/tmp/testindex");
		    IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_46, analyzer);
		    config.setOpenMode(OpenMode.CREATE);
		    IndexWriter iwriter = new IndexWriter(directory, config);
		    //与关系数据库对应理解 Document 表中的一条记录、Field一个字段
			Document doc = new Document();
			doc.add(new Field("id", "1", TextField.TYPE_STORED));
			doc.add(new Field("name","中国人民大团结万岁." , TextField.TYPE_STORED));
			iwriter.addDocument(doc);
			
			Document doc1 = new Document();
			doc1.add(new Field("id", "2", TextField.TYPE_STORED));
			doc1.add(new Field("name","世界人民大团结万岁." , TextField.TYPE_STORED));
			iwriter.addDocument(doc1);
			iwriter.forceMerge(1);
			iwriter.commit();
		    iwriter.close();
		    
		    // Now search the index:
		    DirectoryReader ireader = DirectoryReader.open(directory);
		    IndexSearcher isearcher = new IndexSearcher(ireader);
		    // Parse a simple query that searches for "text":
//		    QueryParser parser = new QueryParser(Version.LUCENE_46, "name", analyzer);
//		    Query query = parser.parse("人民");
		    Query termQ = new TermQuery(new Term("name", "中"));
//		    //关系表中 ResultSet
		    ScoreDoc[] hits = isearcher.search(termQ,1000).scoreDocs;
		    System.out.println(hits.length);
		    // Iterate through the results:
		    for (int i = 0; i < hits.length; i++) {
		      Document hitDoc = isearcher.doc(hits[i].doc);
		      System.out.println(hitDoc.get("id")+"		"+ hitDoc.get("name"));
		    }
		    ireader.close();
		    directory.close();
	}
}
