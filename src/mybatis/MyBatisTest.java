package mybatis;

import java.io.IOException;
import mybatis.pojo.Poi;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/** 
* myBatis数据库连接测试 
*  
* @author db2admin 
*  
*/  
public class MyBatisTest {  
   /** 
    * 获得MyBatis SqlSessionFactory   
    * SqlSessionFactory负责创建SqlSession，一旦创建成功，就可以用SqlSession实例来执行映射语句，commit，rollback，close等方法。 
    * @return 
    */  
   private static SqlSessionFactory getSessionFactory() {  
       SqlSessionFactory sessionFactory = null;  
       String resource = "configuration.xml";  
       try {  
           sessionFactory = new SqlSessionFactoryBuilder().build(Resources  
                   .getResourceAsReader(resource));  
       } catch (IOException e) {  
           e.printStackTrace();  
       }  
       return sessionFactory;  
   }

   public static void main(String[] args) {  
       SqlSession sqlSession = getSessionFactory().openSession();  
       PoiMapper poiMapper = sqlSession.getMapper(PoiMapper.class);  
       Poi poi = poiMapper.findById("6914675");
       System.out.println(poi.getName()+poi.getAddress()+poi.getTelephone()); 
   }
}
