//package main.java.seu.list.server.db;
<<<<<<< HEAD
//
//public class Dormitory_DbAccess {
//
//	Connection conn = null;
//	Statement stmt = null;
//	ResultSet rs=null;
//
//	public Connection getConnection() {
//		try {
//			// 装载驱动
//			Class.forName("com.hxtt.sql.access.AccessDriver");
//			// 建立连接
//			String path = null;
//			try {
//				path = this.getClass().getResource("Dormitory.mdb").toURI().getPath().substring(1);
//			} catch (URISyntaxException e1) {
//				e1.printStackTrace();
//			} // 定位数据库文件的位置
//			String url = "jdbc:Access:///" + path; // Access 驱动
//
//			conn = DriverManager.getConnection(url);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return conn;
//	}
//
//	public void closeConnection(Connection con,ResultSet rs,Statement s) {
//		try {
//			if (s!=null) {
//				s.close();
//			}
//			if (con!=null) {
//				con.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//}
=======
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
			Class.forName("com.hxtt.sql.access.AccessDriver");
			// 建立连接
			String path = null;
			try {
				path = this.getClass().getResource("Dormitory.mdb").toURI().getPath().substring(1);
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			} // 定位数据库文件的位置
			String url = "jdbc:Access:///" + path; // Access 驱动

			conn = DriverManager.getConnection(url);
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
>>>>>>> 27e89bf9ba1d83dbc5221972383f137fcce3b463
