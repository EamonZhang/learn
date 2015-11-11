package mybatis;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class MybatisSessionTest {

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.setProperty("driver", "com.mysql.jdbc.Driver");
		properties.setProperty("url", "jdbc:mysql://127.0.0.1:3306/mybatis");
		properties.setProperty("username", "root");
		properties.setProperty("password", "123456");
		PooledDataSourceFactory pooledDataSourceFactory = new PooledDataSourceFactory();
		pooledDataSourceFactory.setProperties(properties);
		DataSource dataSource = pooledDataSourceFactory.getDataSource();
//		DataSource dataSource = BlogDataSourceFactory.getBlogDataSource();
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);
		configuration.addMapper(PoiMapper.class);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
//		sqlSessionFactory
	}

}
