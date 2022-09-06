
package seu.list.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**  
 * 类{@code Shop_DbAccess}是商店和数据库进行连接的基类，用的是ucanAccess jdbc驱动
 */
public class Shop_DbAccess {
    static Connection con = null;
	Statement stmt = null;
	ResultSet rs=null;
	public static  Connection getConnection() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}// 这个驱动的地址不要改
		
		try {
			con = DriverManager
					.getConnection("jdbc:ucanaccess://src/main/resources/vCampus.accdb", "", "");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		try {
			Statement stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
       return con;
		}
	    public static void closeConnection(Connection con,ResultSet rs,Statement s) {
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

	
	
	    

