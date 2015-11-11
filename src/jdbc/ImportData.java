package jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportData {

	public static void main(String[] args) {
		Connection conn = getConn ();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/home/zhangjin/weizhang/car_illegal.csv"))));
			String line = null;
			while((line = br.readLine() ) != null){
				try {
					PreparedStatement pst = conn.prepareStatement(line);
					pst.execute();
				} catch (Exception e) {
					System.out.println(line);
				}
			}
			br.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public static Connection getConn(){
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:postgresql://192.168.6.30:5432/weizhang", "postgres", "123456");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
