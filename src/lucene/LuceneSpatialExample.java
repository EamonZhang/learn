package lucene;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.spatial.SpatialStrategy;
import org.apache.lucene.spatial.prefix.RecursivePrefixTreeStrategy;
import org.apache.lucene.spatial.prefix.tree.GeohashPrefixTree;
import org.apache.lucene.spatial.prefix.tree.SpatialPrefixTree;
import org.apache.lucene.spatial.query.SpatialArgs;
import org.apache.lucene.spatial.query.SpatialOperation;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.shape.Shape;
/**
 * 
 * Lucene 空间索引
 *
 */
public class LuceneSpatialExample {

	public static void main(String[] args) throws IOException, ParseException {
		new LuceneSpatialExample().test();
	}

	public void test() throws IOException, ParseException {
		init();
		createIndex();
		search();
	}

	private SpatialContext ctx;// "ctx" is the conventional variable name

	private SpatialStrategy strategy;

	private Directory directory;

	private Analyzer analyzer = new IKAnalyzer();

	protected void init() {
		// Typical geospatial context
		// These can also be constructed from SpatialContextFactory
		this.ctx = SpatialContext.GEO;

		int maxLevels = 11;// results in sub-meter precision for geohash
		// TODO demo lookup by detail distance
		// This can also be constructed from SpatialPrefixTreeFactory
		SpatialPrefixTree grid = new GeohashPrefixTree(ctx, maxLevels);

		this.strategy = new RecursivePrefixTreeStrategy(grid, "myGeoField");

		this.directory = new RAMDirectory();
	}

	private void createIndex() throws IOException {
		//默认构造器
//		Analyzer defualtAnalyzer = new IKAnalyzer(true);
//		defualtAnalyzer.
//		Analyzer defualtAnalyzer = new StandardAnalyzer(Version.LUCENE_46);
//		PerFieldAnalyzerWrapper preFiledAnalyzerWrapper = new PerFieldAnalyzerWrapper(defualtAnalyzer);
//		for (int i = 0; i < array.length; i++) {
//			preFiledAnalyzerWrapper.
//		}
//		preFiledAnalyzerWrapper.
		IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_46,analyzer);
		IndexWriter indexWriter = new IndexWriter(directory, iwConfig);
		// Spatial4j is x-y order for arguments|对于参数的顺序是Longitude,Latitude
		indexWriter.addDocument(newSampleDocument(0, "一星大饭店公园",
				ctx.makePoint(116.430360, 39.939686)));
		indexWriter.addDocument(newSampleDocument(1, "二星大饭店公园",
				ctx.makePoint(116.430319, 39.939702)));
		indexWriter.addDocument(newSampleDocument(2, "三星大饭店公园",
				ctx.makePoint(116.430459, 39.939802)));
		indexWriter.addDocument(newSampleDocument(3, "四星大饭店公园",
				ctx.makePoint(116.430449, 39.939902)));
		indexWriter.addDocument(newSampleDocument(4, "六星大饭店公园",
				ctx.makePoint(116.430439, 39.93402)));
		indexWriter.addDocument(newSampleDocument(5, "七星大饭店公园",
				ctx.makePoint(116.430419, 39.934102)));
		indexWriter.addDocument(newSampleDocument(6, "五星大饭店公园",
				ctx.makePoint(116.430429, 39.934202)));
		indexWriter.addDocument(newSampleDocument(6, "五星大酒店公园",
				ctx.makePoint(115.430429, 39.934202)));
		indexWriter.commit();
		indexWriter.close();
	}

	private Document newSampleDocument(int id, String keyword, Shape... shapes) {
		Document doc = new Document();
		doc.add(new IntField("id", id, Field.Store.YES));
		// Potentially more than one shape in this field is supported by some
		// strategies; see the javadocs of the SpatialStrategy impl to see.
		for (Shape shape : shapes) {
			for (IndexableField f : strategy.createIndexableFields(shape)) {
				doc.add(f);
			}
			// store it too; the format is up to you
			doc.add(new StoredField(strategy.getFieldName(), ctx
					.toString(shape)));
		}
		doc.add(new TextField("keyword", keyword, Field.Store.YES));
		return doc;
	}

	private void search() throws IOException, ParseException {
		IndexReader indexReader = DirectoryReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		{
			// 16米范围内
			double dis = 100.0;
			SpatialArgs args = new SpatialArgs(SpatialOperation.Intersects,
					ctx.makeCircle(116.430459, 39.939802, DistanceUtils
							.dist2Degrees(dis/1000.0,
									DistanceUtils.EARTH_MEAN_RADIUS_KM)));
			Filter filter = strategy.makeFilter(args);
			QueryParser parser = new QueryParser(Version.LUCENE_46, "keyword", analyzer);
			Query query = parser.parse("公园");
//			new  TermQuery(null).setQuery("{! score=distance} location:"Intersects( Circle(22.48,108.19 d=20))" " );
			TopDocs docs = indexSearcher.search(query, filter, 1000);
			System.out.println("搜索结果：["+docs.totalHits+"]条");

			ScoreDoc[] scoreDoc = docs.scoreDocs;
			if (docs.totalHits > 0)
				for (int i = 0; i < scoreDoc.length; i++) {
					int doc = scoreDoc[i].doc;
					Document mydoc = indexReader.document(doc);
//					System.out.println(mydoc.get("myGeoField"));
					String value = mydoc.get("myGeoField");
					String[] lonlat = value.split(" ");
					double eLat = Double.valueOf(lonlat[1]);
					double eLon = Double.valueOf(lonlat[0]);
					System.out.println(Util.twoPointDistance(39.939802,116.430459, eLat, eLon) +"	|	"+ mydoc.get("keyword"));
				}
		}
		indexReader.close();
		//TODO 按照距离排序
	}
}