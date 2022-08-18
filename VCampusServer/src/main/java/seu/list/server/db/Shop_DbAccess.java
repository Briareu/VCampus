package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**  
 * 
 *
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
					.getConnection("jdbc:ucanaccess://db/Databaseshop.accdb");
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


/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Shop_DbAccess {
	/**
	 * Access数据库Connection
	 
	private Connection connection;

	static {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//加载ucanaccess驱动
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	public Connection getAccessConnection(String path) {
		try {
			//获取Access数据库连接(Connection)
			this.connection = DriverManager.getConnection("jdbc:ucanaccess://" + path, user, pwd);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return this.connection;
	}

	public static void main(String[] args) throws Exception {
		/*Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");// 这个驱动的地址不要改
		// Connection
		// con=DriverManager.getConnection("jdbc:ucanaccess://C:\\1\\ACCESSdb.mdb","123456","123456");
		Connection con = DriverManager
				.getConnection("jdbc:ucanaccess://C:\\javaeclipse\\Vshop1.0\\Databaseshop.accdb");
		Statement stmt = con.createStatement();
	}

		Shop_DbAccess access=new Shop_DbAccess();
		Connection connection = access.getAccessConnection("C:\\javaeclipse\\Vshop1.0\\Databaseshop.accdb"", "", "");
		access.select(connection);
	}
	/*public void select(Connection connection) throws Exception {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("select * from student");
		while (result.next()) {
			System.out.print(result.getString("id") + "\t");
			System.out.print(result.getString("name") + "\t");
			System.out.print(result.getString("address") + "\t");
			System.out.print(result.getString("age") + "\t");
			System.out.println();
		}
		statement.close();
		connection.close();
	}*/


	
	
	
	    
