//package lucene;
//
//import java.io.IOException;
//import java.util.Date;
//
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.index.CorruptIndexException;
//import org.apache.lucene.index.IndexReader;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.queryparser.classic.QueryParser;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.LockObtainFailedException;
//import org.apache.lucene.store.RAMDirectory;
//import org.apache.lucene.util.Version;
//public class TestQueryParser {  
//  
//    public static void main(String[] args) throws CorruptIndexException, IOException {  
//    	Directory path = new RAMDirectory();
//        TestQueryParser tqp = new TestQueryParser();  
//        tqp.createIndex(path);  
//        IndexSearcher search = tqp.getSearcher(path);  
//        System.out.println("#_2");  
//        tqp.testTime(search, tqp.getQueryParser2());  
//        System.out.println("#_1");  
//        tqp.testTime(search, tqp.getQueryParser1());  
//        System.out.println("#_3");  
//        tqp.testTime(search, tqp.getQueryParser3());  
//        System.out.println("#_4");  
//        tqp.testTime(search, tqp.getQueryParser4());  
//        System.out.println("#_5");  
//        tqp.testTime(search, tqp.getQueryParser5());  
//        System.out.println("#_6");  
//        tqp.testTime(search, tqp.getQueryParser6());  
//        System.out.println("#_7");  
//        tqp.testTime(search, tqp.getQueryParser7());  
//    }  
//      
//    public void testTime(Searcher search,Query query) throws IOException{  
//        Date start = new Date();  
//        Hits hits = search.search(query);  
//        for (int i = 0; i < hits.length(); i++) {  
//            System.out.println(hits.id(i));  
//            System.out.println(hits.doc(i));  
//            System.out.println(hits.score(i));  
//        }  
//          
//        System.out.println("本次搜索用时：" + ((new Date()).getTime() - start.getTime()) + "毫秒");  
//          
//    }  
//      
//    public IndexSearcher getSearcher(Directory path) throws CorruptIndexException, IOException{  
//            return new IndexSearcher(path);  
//    }  
//      
//    public Query getQueryParser1(){  
//        //默认搜索字段  
//        QueryParser queryParser = new QueryParser("content", new StandardAnalyzer());  
//        try {  
//            return queryParser.parse("搜索 - 擎");  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//        return null;  
//    }  
//      
//    public Query getQueryParser2(){  
//        QueryParser queryParser = new QueryParser("content", new StandardAnalyzer());  
//        try {  
//            return queryParser.parse("欢迎");  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//        return null;  
//    }  
//      
//    public Query getQueryParser3(){  
//        QueryParser queryParser = new QueryParser("content", new StandardAnalyzer());  
//        try {  
//            return queryParser.parse("搜索 and 擎");  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//        return null;  
//    }  
//      
//    public Query getQueryParser4(){  
//        QueryParser queryParser = new QueryParser("content", new StandardAnalyzer());  
//        try {  
//            //content字段搜索 索引   title字段搜寻 你好  
//            return queryParser.parse("索引 title:你好");  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//        return null;  
//    }  
//      
//    public Query getQueryParser5(){  
//        QueryParser queryParser = new QueryParser("content", new StandardAnalyzer());  
//        //允许使用正则表达式方式  
//        queryParser.setAllowLeadingWildcard(true);  
//        try {  
//            return queryParser.parse("*索*");  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//        return null;  
//    }  
//    /** 
//     * 采用标准分词器StandardAnalyzer会在创建索引的时候把存入的英文全部换成小写放在索引中 查询的时候也会将查询的关键词转为小写进行查询 
//     * @return 
//     */  
//    public Query getQueryParser6(){  
//        QueryParser queryParser = new QueryParser("testCapital", new StandardAnalyzer());  
//        try {  
//            return queryParser.parse("hellOwangzi");  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//        return null;  
//    }  
//      
//    /** 
//     * 采用标准分词器StandardAnalyzer会在创建索引的时候把存入的and or等关键字过滤掉 所以在查询的时候怎么也查不到 
//     * @return 
//     */  
//    public Query getQueryParser7(){  
//        QueryParser queryParser = new QueryParser("testAndOr", new StandardAnalyzer());  
//        try {  
//            //return queryParser.parse("and");  
//            return queryParser.parse("test");  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//        return null;  
//    }  
//    /** 
//     * 创建索引 
//     * @param path 
//     */  
//    public void createIndex(Directory path){  
//        try {  
//        	 IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_CURRENT, new StandardAnalyzer(Version.LUCENE_CURRENT));
//            IndexWriter writer = new IndexWriter(path,config);  
//            Document docA = new Document();  
//            //相当于数据库中列的概念，因此第一个参数是列名，第二个参数是列的值，最后两个参数是enum类型的(JDK1.5)，对创建的索引的设置  
//            //Field.Store 是否覆盖原来的索引文件，而不是重新建一个  
//            Field fieldA = new Field("content","搜索引擎",Field.Store.YES,Field.Index.ANALYZED);  
//            //我们把列(fieldA)加到某一行(docA)中  
//            docA.add(fieldA);  
//              
//            docA.add(new Field("title","你好中国",Field.Store.YES,Field.Index.ANALYZED));  
//            docA.add(new Field("content","欢迎你llying",Field.Store.YES,Field.Index.ANALYZED));  
//            docA.add(new Field("lastModifyTime","2008-9-17",Field.Store.YES,Field.Index.ANALYZED));  
//            docA.add(new Field("testCapital","HelloWangzi",Field.Store.YES,Field.Index.ANALYZED));  
//            docA.add(new Field("testAndOr","test and",Field.Store.YES,Field.Index.ANALYZED));  
//              
//            Document docB = new Document();  
//            //相当于数据库中列的概念，因此第一个参数是列名，第二个参数是列的值，最后两个参数是enum类型的(JDK1.5)，对创建的索引的设置  
//            Field fieldB = new Field("content","创建索引",Field.Store.YES,Field.Index.ANALYZED);  
//            //我们把列(fieldA)加到某一行(docA)中  
//            docB.add(fieldB);  
//            docB.add(new Field("title","你好世界",Field.Store.YES,Field.Index.ANALYZED));  
//            docB.add(new Field("content","欢迎加入jee高级开发群46176507",Field.Store.YES,Field.Index.ANALYZED));  
//            docB.add(new Field("lastModifyTime","2008-9-6",Field.Store.YES,Field.Index.ANALYZED));  
//            docB.add(new Field("testCapital","hellowangZi",Field.Store.YES,Field.Index.ANALYZED));  
//            docB.add(new Field("testAndOr","test or",Field.Store.YES,Field.Index.ANALYZED));  
//              
//            writer.addDocument(docA);  
//            writer.addDocument(docB);  
//              
//            //如果对海量数据进行创建索引的时候，需要对索引进行优化，以便提高速度  
//            writer.optimize();  
//              
//            //跟数据库类似，打开一个连接，使用完后，要关闭它  
//            writer.close();  
//        } catch (CorruptIndexException e) {  
//            e.printStackTrace();  
//        } catch (LockObtainFailedException e) {  
//            e.printStackTrace();  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        }  
//    }  
//  
//}  
