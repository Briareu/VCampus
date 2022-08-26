
package seu.list.server.db;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
	
public class Library_DbAccess {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs=null;
	
	public Connection getConnection() {
			try {
				// 装载驱动 
				Class.forName("com.hxtt.sql.access.AccessDriver");
				// 建立连接			 
				String path = null;
				path = "jdbc:Access://src/main/resources/vCampus.mdb";
				conn = DriverManager.getConnection(path,"","");
				//conn = DriverManager.getConnection(url);
			}catch (ClassNotFoundException e) {
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