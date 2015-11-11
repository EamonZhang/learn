package lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

/** 
 * lucene配置文件解析定义 
 * @author quzishen 
 * 
 */  
public class SearchConfigure {  
    /**~~~ 配置的KEY值，用于唯一性区分，使用DO的短名称，定义性的要求，不需要外部配置，自动解析className生成*/  
    private String key;  
    /**~~~ 建立索引的DO类名，全路径*/  
    private String className;  
    /**~~~ 需要建立索引的字段串，以","分割*/  
    private String fields;  
    /**~~~ 索引存放的位置*/  
    private String indexDir;  
    /**~~~ 特殊的需要转义的Map*/  
    private Map<String,String> specials = new HashMap<String,String>();  
    /**~~~ 特殊的不需要使用分词器的字段，使用空格分词器，一般整体会建立一个索引*/  
    private List<String> fieldsNoNeedAnalyzer = new ArrayList<String>();  
      
    public String getKey() {  
        return key;  
    }  
    public void setKey(String key) {  
        this.key = key;  
    }  
    public String getClassName() {  
        return className;  
    }  
    public void setClassName(String className) {  
        this.className = className;  
        int index = className.lastIndexOf(".");  
        this.key =  className.substring(index+1, className.length());  
    }  
    public String getIndexDir() {  
        return indexDir;  
    }  
    public void setIndexDir(String indexDir) {  
        this.indexDir = indexDir;  
    }  
    public String getFields() {  
        return fields;  
    }  
    public void setFields(String fields) {  
        this.fields = fields;  
    }  
      
    public void initMap(String key,String value){  
//        if(StringUtils.isBlank(key) || StringUtils.isBlank(value)){  
//            return;  
//        }  
        specials.put(key, value);  
    }  
    public Map<String, String> getSpecials() {  
        return specials;  
    }  
      
    public void initList(String keys){  
//        if(StringUtils.isBlank(keys)){  
//            return;  
//        }  
//        String[] keyarray = StringUtils.split(keys, ",");  
    	 String[] keyarray = keys.split(",");  
        fieldsNoNeedAnalyzer = Arrays.asList(keyarray);  
    }  
      
    public List<String> getFieldsNoNeedAnalyzer() {  
        return fieldsNoNeedAnalyzer;  
    }  
    public void setFieldsNoNeedAnalyzer(List<String> fieldsNoNeedAnalyzer) {  
        this.fieldsNoNeedAnalyzer = fieldsNoNeedAnalyzer;  
    }  
    public void setSpecials(Map<String, String> specials) {  
        this.specials = specials;  
    }  
   public static void main(String[] args) {

}
   @SuppressWarnings("unchecked")
public List<SearchConfigure> parseList(String filePath) throws IOException, SAXException{
		// 解析xml文件，封装索引列表  
       Digester digester = new Digester();  
       digester.setValidating(false);  
         
       // 遇见lucene标签，生成java.util.ArrayList对象  
       digester.addObjectCreate("lucene", "java.util.ArrayList");  
       // 对lucene生成的对象赋值  
       digester.addSetProperties("lucene");  
       // 遇到lucene/rule标签，生成SearchConfigure对象  
       digester.addObjectCreate("lucene/rule", SearchConfigure.class.getName());  
       // 对其赋值  
       digester.addSetProperties("lucene/rule");  
       digester.addBeanPropertySetter("lucene/rule/className");  
       digester.addBeanPropertySetter("lucene/rule/fields");  
       digester.addBeanPropertySetter("lucene/rule/indexDir");  
         
       digester.addCallMethod("lucene/rule/special", "initMap",2);  
       digester.addCallParam("lucene/rule/special/special-field", 0);  
       digester.addCallParam("lucene/rule/special/special-convert", 1);  
         
       digester.addCallMethod("lucene/rule/noNeedAnalyzer", "initList",1);  
       digester.addCallParam("lucene/rule/noNeedAnalyzer", 0);  
         
       // 生成列表中的next项  
       digester.addSetNext("lucene/rule", "add",SearchConfigure.class.getName());  
       return (List<SearchConfigure>)digester.parse(filePath);  
   }
}  