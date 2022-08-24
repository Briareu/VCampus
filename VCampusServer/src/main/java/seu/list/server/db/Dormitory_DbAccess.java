package seu.list.server.db;

import java.sql.*;
import java.net.*;

public class Dormitory_DbAccess {

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs=null;
	
	public Connection getConnection() {
		try {
			// 装载驱动
			
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			String dbpath = "src\\main\\resources\\vCampus.accdb";
			String user="";
			String passwd="";
			// 建立连接
			String url = "jdbc:ucanaccess://"+dbpath;
			conn = DriverManager.getConnection(url,user,passwd);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
   			
	public void closeConnection(Connection con,ResultSet rs,Statement s) {
		try {
			if (s!=null) {
				s.close();
			}
			if (con!=null) {
				con.close();	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}



