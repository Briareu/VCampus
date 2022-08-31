package seu.list.server.db;

import java.io.File;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 连接数据库
 * @author 王映方
 * @version jdk1.8.0
 */
	
public class Library_DbAccess {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs=null;
	
	/**
	 * 与数据库连接
	 * @return 连接
	 */
	public Connection getConnection() {
			try {
				
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				String url ="jdbc:ucanaccess://" + new File("").getAbsolutePath().replace('\\', '/') + "/vCampus.accdb";
				conn = DriverManager.getConnection(url, "", "");
				System.out.println("connection success");
				
/*				// 装载驱动 
				Class.forName("com.hxtt.sql.access.AccessDriver");
				// 建立连接			 
				String path = null;
				path = "jdbc:Access://src/main/resources/vCampus.mdb";
				conn = DriverManager.getConnection(path,"","");*/
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
	}
				
	/**
	 * 关闭与数据库连接
	 * @param con 数据库的连接
	 * @param rs 连接结果
	 * @param s 连接状态
	 */
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