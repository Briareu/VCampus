/**
 * Below is class description
 * 
 * @version  1.0 8/12
 * @author Liu
 * 
 * this is a connection class
 */
package Dao;

import java.sql.*;

import VCampusDomain.ClassManage;

public class StudentAccess{
	private Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	
	public Connection getConnection(){
		try {
			Class.forName("com.hxtt.sql.access.AccessDriver");
			String url = "jdbc:Access:///D:\\javaprogram\\VCampus\\src\\main\\resources\\view\\Student.mdb";
			con = DriverManager.getConnection(url,"","");
			/*st = con.createStatement();
			rs = st.executeQuery("select * from Student");
			System.out.println("1111111");
			while(rs.next())
	            System.out.println(rs.getString("player_mobile"));*/
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Exception: "+((SQLException) e).getNextException());
		}
		return con;
	}
	public void closeConnection(Connection con,ResultSet rs,Statement s) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(s!=null) {
				s.close();
			}
			if(con!=null) {
				con.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println("Exception: "+((SQLException) e).getNextException());
		}
	}
}