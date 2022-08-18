/**
 * Below is class description
 * 
 * @version  1.0 8/12
 * @author Liu
 * 
 * this is a connection class
 */
package seu.list.server.db;

import java.sql.*;
import java.util.List;
import java.util.Vector;

import seu.list.common.*;

public class ClassAccessHelper{
	private Connection con = null;

	public Connection getConnection(){
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			String url = "jdbc:ucanaccess://D:\\javaprogram\\VCampusServer\\src\\main\\resources\\Class.mdb";
			con = DriverManager.getConnection(url);
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Exception: "+((SQLException) e).getNextException());
		}
		return con;
	}

	public Connection getConnection(String path) {
		try {
			this.con = DriverManager.getConnection("jdbc:ucanaccess://" + path);
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return this.con;
	}

	public Vector<ClassManage> getall(Connection con) throws Exception{
		Vector<ClassManage> clss = new Vector<ClassManage>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from Class");
			int i = 0;
			while(rs.next()) {
				ClassManage temp = new ClassManage(rs.getString(1), rs.getString(2), rs.getString(3) , rs.getInt(4));
				clss.addElement(temp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			st.close();
			rs.close();
			con.close();
		}
		return clss;
	}

	public int insert(Connection connection, ClassManage clss) throws Exception{
		PreparedStatement ps = con.prepareStatement("insert into Class(ClassID,TeacherId,Major,ClassSize) values(?,?,?,?)");
		ps.setString(1, clss.getClassID());
		ps.setString(2, clss.getTeacherID());
		ps.setString(3, clss.getMajor());
		ps.setDouble(4, clss.getClassSize());
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	public int delete(Connection con, String id) throws Exception{
		Statement statement = con.createStatement();
		int res = statement.executeUpdate("delete from Classt where ClassID='"+ id +"'");
		statement.close();
		con.close();
		return res;
	}

	public int update(Connection connection, List<Object> data)throws Exception{
		Statement statement = con.createStatement();
		int res = -1;
		switch((int)data.get(0)) {//type, newdata, olddata
		//0---update classid, 1---update teacher, 2----update major
		case 0:
		{
			res = statement.executeUpdate("update Class set ClassID='"+ data.get(1).toString() +"' where ClassID='"+ data.get(2).toString() +"'");
		}
		break;
		case 1:
		{
			res = statement.executeUpdate("update Class set TeacherId='"+ data.get(1).toString() +"' where ClassID='"+ data.get(2).toString() +"'");
		}
		break;
		case 2:
		{
			res = statement.executeUpdate("update Class set Major='"+ data.get(1).toString() +"' where ClassID='"+ data.get(2).toString() +"'");
		}
		break;
		default:
			break;
		}
		statement.close();
		con.close();
		return res;
	}

	public Vector<ClassManage> select(Connection con, List<Object>data)throws Exception{
		Vector<ClassManage> clss = new Vector<ClassManage>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from Class");
			int i = 0;
			while(rs.next()) {
				ClassManage temp = new ClassManage(rs.getString(1), rs.getString(2), rs.getString(3) , rs.getInt(4));
				switch((int)data.get(0)) {
				case 0:
				{
					//select from classid
					if((temp.getClassID()).compareTo((data.get(1)).toString()) == 0) {
						clss.add(temp);
					}
				}
				break;
				case 1:
				{
					//select from major
					if((temp.getMajor()).compareTo(data.get(1).toString())== 0) {
						clss.add(temp);
					}
				}
				break;
				case 2:
				{
					//select from teacher
					if((temp.getTeacherID()).compareTo(data.get(1).toString())== 0) {
						clss.add(temp);
					}
				}
				default:
					break;
				}
			}
			System.out.println("select success");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			st.close();
			rs.close();
			con.close();
		}
		return clss;
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