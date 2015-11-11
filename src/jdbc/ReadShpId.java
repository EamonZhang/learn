package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadShpId {

	public static void main(String[] args) throws Exception {
		Connection con = getConn();
		String sql = "SELECT strshpid FROM oes.servinfo where strshpid is not null";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			String shpid = rs.getString(1);
			System.out.println(shpid);
		}
		rs.close();
		pst.close();
		con.close();
	}
	public static Connection getConn(){
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:postgresql://192.168.6.30:5432/oesv21", "postgres", "123456");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
