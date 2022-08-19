
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
	/*			try {
					path = this.getClass().getResource("test.mdb").toURI().getPath().substring(1);
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}   //定位数据库文件的位置		 
				String url="jdbc:Access:///" + path;     //Access 驱动	
				*/
				path = "jdbc:Access:///E:\\VCampusCode'\\test.mdb";
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




