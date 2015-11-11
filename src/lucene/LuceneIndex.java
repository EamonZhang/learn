package lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.spatial.SpatialStrategy;
import org.apache.lucene.spatial.prefix.RecursivePrefixTreeStrategy;
import org.apache.lucene.spatial.prefix.tree.GeohashPrefixTree;
import org.apache.lucene.spatial.prefix.tree.SpatialPrefixTree;
import org.apache.lucene.spatial.query.SpatialArgs;
import org.apache.lucene.spatial.query.SpatialOperation;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;

public class LuceneIndex {
	static SpatialContext ctx = SpatialContext.GEO;  
	static SpatialPrefixTree grid = new GeohashPrefixTree(ctx, 11);  
	static SpatialStrategy strategy = new RecursivePrefixTreeStrategy(grid, "myGeoField");
	static Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);

	/**
	 * 创建索引
	 * 
	 * @param analyzer
	 * @throws Exception
	 */
	public static void createIndex() throws Exception {
		Directory dire = FSDirectory.open(new File(Constants.INDEX_STORE_PATH));
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46,
				analyzer);
		iwc.setOpenMode(OpenMode.CREATE);
		IndexWriter iw = new IndexWriter(dire, iwc);
		LuceneIndex.addDoc(iw);
		iw.forceMerge(2);
		iw.close();
	}

	/**
	 * 动态添加Document
	 * 
	 * @param iw
	 * @throws Exception
	 */
	public static void addDoc(IndexWriter iw) throws Exception {
		File[] files = new File(Constants.INDEX_FILE_PATH).listFiles();
		for (File file : files) {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String line =  br.readLine();
			int i = 0;
			while ((line = br.readLine()) != null && !line.isEmpty()) {
//				System.out.println(line);
				String[] context = line.split("!!");
				if(context.length != 9){
					continue;
				}
				Document doc = new Document();
				doc.add(new StringField("sunmapid", context[0], Store.YES));
				doc.add(new StringField("strid", context[1], Store.YES));
				doc.add(new StringField("admincode", context[2], Store.YES));
				doc.add(new StringField("s_name", context[3], Store.YES));
				doc.add(new TextField("t_name", context[3], Store.YES));
				doc.add(new TextField("address", context[4].replace(";", ""), Store.YES));
				doc.add(new StringField("tele", (context[5]==null ||   context[5].equals("null") ||context[5].isEmpty() )?"":context[5], Store.YES));
				doc.add(new StringField("type", context[6], Store.YES));
				for (IndexableField f : strategy
						.createIndexableFields(ctx.makePoint(
								(double) (Long.parseLong(context[7]) / 2560.0 / 3600.0),
								(double) (Long.parseLong(context[8])) / 2560.0 / 3600.0))) {
					doc.add(f);
				}
				doc.add(new LongField("lon",Long.parseLong( context[7]), Store.YES));
				doc.add(new LongField("lat",Long.parseLong( context[8]), Store.YES));
				
				iw.addDocument(doc);
				if(i++ %100000 == 0){
					iw.commit();
					System.out.println("完成索引 ： "+ i +"	"+ new Date());
				}
			}
			br.close();
		}
	}

	/**
	 * 搜索
	 * 
	 * @param query
	 * @throws Exception
	 */
	private static void search(Query query,IndexSearcher is) throws Exception {
		TopDocs td = is.search(query, 1);
		System.out.println("共为您查找到" + td.totalHits + "条结果");
		ScoreDoc[] sds = td.scoreDocs;
		for (ScoreDoc sd : sds) {
			Document d = is.doc(sd.doc);
			System.out.println(d.get("sunmapid") + ":[" + d.get("admincode") + "]" + ":[" + d.get("s_name") + "]"+":[" + d.get("address") + "]"+":[" + d.get("tele") + "]"+":[" + d.get("lat") + "]"+":[" + d.get("lon") + "]"+":[" + d.get("type") + "]");
		}
	}
	private static Query createQuery(String admincode,String name) throws ParseException{
		Query queryName = new QueryParser(Version.LUCENE_46, "t_name",
				analyzer).parse(name);
		Query queryAdmincode = new QueryParser(Version.LUCENE_46, "admincode",
				analyzer).parse(admincode);
//		Query queryName = new TermQuery(new Term("t_name", name));
//		Query queryAdmincode = new TermQuery(new Term("admincode", admincode));
		BooleanQuery query = new BooleanQuery();
		query.add(queryName, Occur.MUST);
		query.add(queryAdmincode, Occur.MUST);
		return query;
	}
	public static void main(String[] args) throws Exception, Exception {
		System.out.println("开始时间：" + new Date());
		LuceneIndex.createIndex();
		System.out.println("索引完成：" + new Date());
//		Directory dire = FSDirectory.open(new File(Constants.INDEX_STORE_PATH));
//		IndexReader ir = DirectoryReader.open(dire);
//		IndexSearcher is = new IndexSearcher(ir);
//		BufferedReader br = new BufferedReader(new InputStreamReader(LuceneIndex.class.getResourceAsStream("tingche")));
//		String line = null;
//		while((line = br.readLine()) != null){
//			System.out.println(line); 
//			if(line.split("\t").length !=2){
//				continue;
//			}
//			String admincode = line.split("\t")[0];
//			String name = line.split("\t")[1];
//			Query query = createQuery(admincode,name);
//			LuceneIndex.search(query,is);
//		}
//		br.close();
//		ir.close(); 
//		searchOne("", "兆兴大酒店");"创发潮州食府"
		String name = "创发潮州食府";
		double x = 114.12908*2560*3600;
		double y = 22.54233*2560*3600;
		searchSpatial(name, x, y, 1000);
		System.out.println("搜索完成：" + new Date());
	}
	//茵特拉根欢乐广场
//	private static void searchOne(String admincode,String name) throws IOException, ParseException{
//		Directory dire = FSDirectory.open(new File(Constants.INDEX_STORE_PATH));
//		IndexReader ir = DirectoryReader.open(dire);
//		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
//		QueryParser parser = new QueryParser(Version.LUCENE_46, "t_name", analyzer);
//		Query query =parser.parse(name);
////		parser.setDefaultOperator(Operator.AND);
////		Query query = new TermQuery(new Term("s_name", name));
//		IndexSearcher is = new IndexSearcher(ir);
//		TopDocs tds =is.search(query, 10);
//		System.out.println("一共数量："+tds.totalHits);
//		for (ScoreDoc doc : tds.scoreDocs) {
//				System.out.println(is.doc(doc.doc).get("s_name")+"	"+doc.score);
//		}
//	}
	
	private static void searchSpatial(String name,double x ,double y,int r) throws IOException, ParseException{
		Directory dir = FSDirectory.open(new File(Constants.INDEX_STORE_PATH));
		IndexReader ir  = DirectoryReader.open(dir);
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
		QueryParser parser = new QueryParser(Version.LUCENE_46, "t_name", analyzer);
		Query query = parser.parse(name);
		SpatialArgs args = new SpatialArgs(SpatialOperation.Intersects, 
	    		 ctx.makeCircle(x/2560.0/3600.0, y/2560.0/3600.0, DistanceUtils.dist2Degrees((double)(r/1000.0),DistanceUtils.EARTH_MEAN_RADIUS_KM)));  
	    Filter filter = strategy.makeFilter(args);
	    IndexSearcher searcher = new IndexSearcher(ir);
		TopDocs tds = searcher.search(query, filter, 5);
		System.out.println("一共数量："+tds.totalHits);
		for (ScoreDoc doc : tds.scoreDocs) {
				Document document = ir.document(doc.doc);
				double distance = Util.twoPointDistance( y/2560.0/3600.0,x/2560.0/3600.0, Double.parseDouble(document.get("lon"))/2560.0/3600.0, Double.parseDouble(document.get("lat"))/2560.0/3600.0);
				System.out.println(document.get("sunmapid") +"|"+document.get("s_name")+"|"+document.get("address")+"|"+distance+"|"+doc.score);
		}
	}
}