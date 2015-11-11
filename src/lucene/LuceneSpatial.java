/*ucene版本为3.3，所有用到的jar包名如下： 
log4j-1.2.15.jar 
lucene-spatial-3.3.0.jar 
lucene-core-3.3.0.jar 
lucene-misc-3.3.0.jar 
lucene-analyzers-3.3.0.jar 
lucene-demo-3.3.0.jar 
lucene-queries-3.3.0.jar 
lucene-queryparser-3.3.0.jar 
json.jar  */
/*{"id":12,"title":"时尚码头美容美发热烫特价","longitude":116.3838183,"latitude":39.9629015}
{"id":17,"title":"审美个人美容美发套餐","longitude":116.386564,"latitude":39.966102}
{"id":23,"title":"海底捞吃300送300","longitude":116.38629,"latitude":39.9629573}
{"id":26,"title":"仅98元！享原价335元李老爹","longitude":116.3846175,"latitude":39.9629125}
{"id":29,"title":"都美造型烫染美发护理套餐","longitude":116.38629,"latitude":39.9629573}
{"id":30,"title":"仅售55元！原价80元的老舍茶馆相声下午场","longitude":116.0799914,"latitude":39.9655391}
{"id":33,"title":"仅售55元！原价80元的新笑声客栈早场","longitude":116.0799914,"latitude":39.9655391}
{"id":34,"title":"仅售39元（红色礼盒）！原价80元的平谷桃","longitude":116.0799914,"latitude":39.9655391}
{"id":46,"title":"仅售38元！原价180元地质礼堂白雪公主","longitude":116.0799914,"latitude":39.9655391}
{"id":49,"title":"仅99元！享原价342.7元自助餐","longitude":116.0799914,"latitude":39.9655391}
{"id":58,"title":"桑海教育暑期学生报名培训九折优惠券","longitude":116.0799914,"latitude":39.9655391}
{"id":59,"title":"全国发货：仅29元！贝玲妃超模粉红高光光","longitude":116.0799914,"latitude":39.9655391}
{"id":65,"title":"海之屿生态水族用品店抵用券","longitude":116.0799914,"latitude":39.9655391}
{"id":67,"title":"小区东门时尚烫染个人护理美发套餐","longitude":116.3799914,"latitude":39.9655391}
{"id":74,"title":"《郭德纲相声专辑》CD套装","longitude":116.0799914,"latitude":39.9655391}
*/
//package lucene;
// 
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
// 
//import org.apache.log4j.Logger;
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.index.IndexReader;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.index.IndexWriterConfig.OpenMode;
//import org.apache.lucene.queryParser.QueryParser;
//import org.apache.lucene.queryParser.QueryParser.Operator;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.MatchAllDocsQuery;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.Sort;
//import org.apache.lucene.search.SortField;
//import org.apache.lucene.search.TopDocs;
//import org.apache.lucene.spatial.tier.DistanceFieldComparatorSource;
//import org.apache.lucene.spatial.tier.DistanceQueryBuilder;
//import org.apache.lucene.spatial.tier.projections.CartesianTierPlotter;
//import org.apache.lucene.spatial.tier.projections.IProjector;
//import org.apache.lucene.spatial.tier.projections.SinusoidalProjector;
//import org.apache.lucene.store.FSDirectory;
//import org.apache.lucene.util.NumericUtils;
//import org.apache.lucene.util.Version;
//import org.json.JSONObject;
// 
//@SuppressWarnings("deprecation")
//public class LuceneSpatial
//{
// 
//    private static final Logger logger = Logger.getLogger(LuceneSpatial.class);
// 
//    private Analyzer analyzer;
//    private IndexWriter writer;
//    private FSDirectory indexDirectory;
//    private IndexSearcher indexSearcher;
//    private IndexReader indexReader;
//    private String indexPath = "index";
// 
//    // Spatial
//    private IProjector projector;
//    private CartesianTierPlotter ctp;
//    public static final double RATE_MILE_TO_KM = 1.609344; // 英里和公里的比率
//    public static final String LAT_FIELD = "lat";
//    public static final String LON_FIELD = "lng";
//    private static final double MAX_RANGE = 15.0; // 索引支持的最大范围，单位是千米
//    private static final double MIN_RANGE = 3.0; // 索引支持的最小范围，单位是千米
//    private int startTier;
//    private int endTier;
// 
//    public LuceneSpatial()
//    {
//        try
//        {
//            init();
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
// 
//    private void init() throws Exception
//    {
//        initializeSpatialOptions();
// 
//        analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
// 
//        File path = new File(indexPath);
// 
//        boolean isNeedCreateIndex = false;
// 
//        if (path.exists() && !path.isDirectory())
//            throw new Exception("Specified path is not a directory");
// 
//        if (!path.exists())
//        {
//            path.mkdirs();
//            isNeedCreateIndex = true;
//        }
// 
//        indexDirectory = FSDirectory.open(new File(indexPath));
// 
//        // 建立索引
//        if (isNeedCreateIndex)
//        {
//            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_CURRENT, analyzer);
//            indexWriterConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
//            writer = new IndexWriter(indexDirectory, indexWriterConfig);
//            buildIndex();
//        }
// 
//        indexReader = IndexReader.open(indexDirectory, true);
//        indexSearcher = new IndexSearcher(indexReader);
// 
//    }
// 
//    private void initializeSpatialOptions()
//    {
//        projector = new SinusoidalProjector();
//        ctp = new CartesianTierPlotter(0, projector, CartesianTierPlotter.DEFALT_FIELD_PREFIX);
//        startTier = ctp.bestFit(MAX_RANGE / RATE_MILE_TO_KM);
//        endTier = ctp.bestFit(MIN_RANGE / RATE_MILE_TO_KM);
//    }
// 
//    private int mile2Meter(double miles)
//    {
//        double dMeter = miles * RATE_MILE_TO_KM * 1000;
// 
//        return (int) dMeter;
//    }
// 
//    private double km2Mile(double km)
//    {
//        return km / RATE_MILE_TO_KM;
//    }
// 
//    private void buildIndex()
//    {
//        BufferedReader br = null;
//        try
//        {
//            // 逐行添加测试数据到索引中，测试数据文件和源文件在同一个目录下
//            br = new BufferedReader(new InputStreamReader(LuceneSpatial.class.getResourceAsStream("\\data.txt")));
//            String line = null;
//            while ((line = br.readLine()) != null)
//            {
//                index(new JSONObject(line));
//            }
// 
//            writer.commit();
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        } finally
//        {
//            if (br != null)
//            {
//                try
//                {
//                    br.close();
//                } catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
// 
//    private void index(JSONObject jo) throws Exception
//    {
//        //
//         
//        Document doc = new Document();
// 
//        doc.add(new Field("id", jo.getString("id"), Field.Store.YES, Field.Index.ANALYZED));
// 
//        doc.add(new Field("title", jo.getString("title"), Field.Store.YES, Field.Index.ANALYZED));
// 
//        // 将位置信息添加到索引中
//        indexLocation(doc, jo);
// 
//        writer.addDocument(doc);
//    }
// 
//    private void indexLocation(Document document, JSONObject jo) throws Exception
//    {
// 
//        double longitude = jo.getDouble("longitude");
//        double latitude = jo.getDouble("latitude");
// 
//        document.add(new Field("lat", NumericUtils.doubleToPrefixCoded(latitude), Field.Store.YES, Field.Index.NOT_ANALYZED));
//        document.add(new Field("lng", NumericUtils.doubleToPrefixCoded(longitude), Field.Store.YES, Field.Index.NOT_ANALYZED));
// 
//        for (int tier = startTier; tier <= endTier; tier++)
//        {
//            ctp = new CartesianTierPlotter(tier, projector, CartesianTierPlotter.DEFALT_FIELD_PREFIX);
//            final double boxId = ctp.getTierBoxId(latitude, longitude);
//            document.add(new Field(ctp.getTierFieldName(), NumericUtils.doubleToPrefixCoded(boxId), Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
//        }
//    }
// 
//    public List<String> search(String keyword, double longitude, double latitude, double range) throws Exception
//    {
//        List<String> result = new ArrayList<String>();
// 
//        double miles = km2Mile(range);
// 
//        DistanceQueryBuilder dq = new DistanceQueryBuilder(latitude, longitude, miles, "lat", "lng", CartesianTierPlotter.DEFALT_FIELD_PREFIX, true, startTier,
//                endTier);
// 
//        // 按照距离排序
//        DistanceFieldComparatorSource dsort = new DistanceFieldComparatorSource(dq.getDistanceFilter());
//        Sort sort = new Sort(new SortField("geo_distance", dsort));
// 
//        Query query = buildQuery(keyword);
// 
//        // 搜索结果
//        TopDocs hits = indexSearcher.search(query, dq.getFilter(), Integer.MAX_VALUE, sort);
//        // 获得各条结果相对应的距离
//        Map<Integer, Double> distances = dq.getDistanceFilter().getDistances();
// 
//        for (int i = 0; i < hits.totalHits; i++)
//        {
//            final int docID = hits.scoreDocs[i].doc;
// 
//            final Document doc = indexSearcher.doc(docID);
// 
//            final StringBuilder builder = new StringBuilder();
//            builder.append("找到了: ").append(doc.get("title")).append("， 距离: ").append(mile2Meter(distances.get(docID))).append("米。");
//            // System.out.println(builder.toString());
//            logger.info(builder.toString());
// 
//            result.add(builder.toString());
//        }
// 
//        return result;
//    }
// 
//    private Query buildQuery(String keyword) throws Exception
//    {
//        // 如果没有指定关键字，则返回范围内的所有结果
//        if (keyword == null || keyword.isEmpty())
//        {
//            return new MatchAllDocsQuery();
//        }
//        QueryParser parser = new QueryParser(Version.LUCENE_CURRENT, "title", analyzer);
// 
//        parser.setDefaultOperator(Operator.AND);
// 
//        return parser.parse(keyword.toString());
//    }
// 
//    public static void main(String[] args) throws Exception
//    {
//        LuceneSpatial spatialSearcher = new LuceneSpatial();
//        long start1 = System.currentTimeMillis();
//        List<String> results1 = spatialSearcher.search("美发", 116.3838183, 39.9629015, 3.0);
//        logger.info(results1.size() + "个匹配结果，共耗时 " + (System.currentTimeMillis() - start1) + "毫秒。\n");
//         
//        long start2 = System.currentTimeMillis();
//        List<String> results2 = spatialSearcher.search(null, 116.3838183, 39.9629015, 3.0);
//        logger.info(results2.size() + "个匹配结果，共耗时 " + (System.currentTimeMillis() - start2) + "毫秒.\n");
//         
//    }
//     
//}
