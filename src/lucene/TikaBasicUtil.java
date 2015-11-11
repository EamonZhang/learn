//package lucene;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.ContentHandler;
//
//import org.apache.lucene.search.FieldCache.Parser;
//import org.xml.sax.SAXException;
//
//public class TikaBasicUtil {  
//    
//    public static String extractContent(File f) {  
//        //1、创建一个parser  
//        Parser parser = new AutoDetectParser();  
//        InputStream is = null;  
//        try {  
//            Metadata metadata = new Metadata();  
//            metadata.set(Metadata.RESOURCE_NAME_KEY, f.getName());  
//            is = new FileInputStream(f);  
//            ContentHandler handler = new BodyContentHandler();  
//            ParseContext context = new ParseContext();  
//            context.set(Parser.class,parser);  
//              
//            //2、执行parser的parse()方法。  
//            parser.parse(is,handler, metadata,context);  
//                  
//            String returnString = handler.toString();  
//              
//            System.out.println(returnString.length());  
//            return returnString;  
//        } catch (FileNotFoundException e) {  
//            e.printStackTrace();  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        } catch (SAXException e) {  
//            e.printStackTrace();  
//        } catch (TikaException e) {  
//            e.printStackTrace();  
//        }finally {  
//            try {  
//                if(is!=null) is.close();  
//            } catch (IOException e) {  
//                e.printStackTrace();  
//            }  
//        }  
//        return "No Contents";  
//    }  
//}  