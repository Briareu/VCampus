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

public class StudentAccessHelper{
	private Connection con = null;

	public Connection getConnection(){
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			String url = "jdbc:ucanaccess://D:\\javaprogram\\VCampusServer\\src\\main\\resources\\Student.mdb";
			con = DriverManager.getConnection(url);
			System.out.println("connection success");
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Exception: "+((SQLException) e).getNextException());
		}
		return con;
	}

	public Connection getConnection(String path) {
		try {
			this.con = DriverManager.getConnection("jdbc:ucanaccess://" + path);
			System.out.println("connection success");
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return this.con;
	}

	public Vector<Student> getall(Connection con) throws Exception{
		Vector<Student> stu = new Vector<Student>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from Student");
			int i = 0;
			while(rs.next()) {
				Student temp = new Student(rs.getString(1), rs.getString(2), rs.getString(3) , rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getString(9) , rs.getDouble(10));
				stu.addElement(temp);
			}
			System.out.println("getall success");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			st.close();
			rs.close();
			con.close();
		}
		return stu;
	}

	public int add(Connection connection, Student stu) throws Exception{
		PreparedStatement ps = con.prepareStatement("insert into Student(StudentID,StudentName,TeacherID,ClassID,Major,StudentOrigion,StudentStatus,StudentGender,StudentPhone,StudentCredit) values(?,?,?,?,?,?,?,?,?,?)");
		ps.setString(1, stu.getStudentid());
		ps.setString(2, stu.getStudentName());
		ps.setString(3, stu.getTeacherid());
		ps.setString(4, stu.getClassid());
		ps.setString(5, stu.getMajor());
		ps.setString(6, stu.getStudentorigion());
		ps.setString(7, stu.getStudentstatus());
		ps.setBoolean(8, stu.getStudentgender());
		ps.setString(9, stu.getStudentphone());
		ps.setDouble(10, stu.getStudentcredit());
		int result = ps.executeUpdate();
		System.out.println("add success");
		ps.close();
		con.close();
		return result;
	}

	public int delete(Connection con, String id) throws Exception{
		Statement statement = con.createStatement();
		int res = statement.executeUpdate("delete from Student where StudentID='"+ id +"'");
		statement.close();
		con.close();
		System.out.println("delete success");
		return res;
	}

	public int update(Connection connection, List<Object> data)throws Exception{
		Statement statement = con.createStatement();
		int res = -1;
		switch((int)data.get(0)) {//type, newdata, olddata
		//0---update classid, 1---update teacher, 2----update major, 3--studentname, 4--studentid, 5--studentphone, 6--studentgender, 7--studentorigion, 8--studentcredit, 9--studentstatus
		case 0:
		{
			res = statement.executeUpdate("update Student set ClassID='"+ data.get(1).toString() +"' where ClassID='"+ data.get(2).toString() +"'");
		}
		break;
		case 1:
		{
			res = statement.executeUpdate("update Student set TeacherID='"+ data.get(1).toString() +"' where ClassID='"+ data.get(2).toString() +"'");
		}
		break;
		case 2:
		{
			res = statement.executeUpdate("update Student set Major='"+ data.get(1).toString() +"' where ClassID='"+ data.get(2).toString() +"'");
		}
		break;
		default:
			break;
		}
		statement.close();
		con.close();
		System.out.println("update success");
		return res;
	}

	public Vector<Student> select(Connection con, List<Object>data)throws Exception{
		Vector<Student> stu = new Vector<Student>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from Student");
			int i = 0;
			while(rs.next()) {
				Student temp = new Student(rs.getString(1), rs.getString(2), rs.getString(3) , rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getString(9) , rs.getDouble(10));
				switch((int)data.get(0)) {
				case 0:
				{
					//select from studentid
					if((temp.getStudentid()).compareTo((data.get(1)).toString()) == 0) {
						stu.add(temp);
					}
				}
				break;
				case 1:
				{
					//select from name
					if((temp.getStudentName()).compareTo(data.get(1).toString())== 0) {
						stu.add(temp);
					}
				}
				break;
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
		return stu;
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