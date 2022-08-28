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
			String url = "jdbc:ucanaccess://src/main/resources/vCampus.accdb";
			con = DriverManager.getConnection(url, "", "");
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
	//for student manage
	public Vector<Student> getall(Connection con) throws Exception{
		Vector<Student> stu = new Vector<Student>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			//rs = st.executeQuery("select * from Student");
			rs = st.executeQuery("select * from tb_Student");
			int i = 0;
			while(rs.next()) {
				//System.out.println(rs.getString(1));
				//System.out.println(rs.getString(2));
				//System.out.println(rs.getString(3));
				//System.out.println(rs.getString(4));
				Student temp = new Student(rs.getString(1), rs.getString(2), rs.getString(3) , rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getString(9) , rs.getDouble(10));
				stu.addElement(temp);
				//System.out.println(temp.getMajor());
			}
			System.out.println("Student getall success");
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
//		PreparedStatement ps = con.prepareStatement("insert into Student(StudentID,StudentName,TeacherID,ClassID,Major,StudentOrigion,StudentStatus,StudentGender,StudentPhone,StudentCredit) values('"+ stu.getStudentid() +"','"+ stu.getStudentName() +"','"+ stu.getTeacherid() +"','"+ stu.getClassid() +"','"+ stu.getMajor() +"','"+ stu.getStudentorigion() +"','"+ stu.getStudentstatus() +"','"+ stu.getStudentgender() +"','"+ stu.getStudentphone() +"','"+ stu.getStudentcredit() +"')");
		PreparedStatement ps = con.prepareStatement("insert into tb_Student(StudentID,StudentName,TeacherID,ClassID,Major,StudentOrigion,StudentStatus,StudentGender,StudentPhone,StudentCredit) values('"+ stu.getStudentid() +"','"+ stu.getStudentName() +"','"+ stu.getTeacherid() +"','"+ stu.getClassid() +"','"+ stu.getMajor() +"','"+ stu.getStudentorigion() +"','"+ stu.getStudentstatus() +"','"+ stu.getStudentgender() +"','"+ stu.getStudentphone() +"','"+ stu.getStudentcredit() +"')");
		int result = ps.executeUpdate();
		System.out.println("add success");
		ps.close();
		con.close();
		return result;
	}
	
	public int delete(Connection con, List<Object> data) throws Exception{
		Statement statement = con.createStatement();
		int res = -1;
		switch((int)data.get(0)) {
		case 0:
		{
//			res = statement.executeUpdate("delete from Student where StudentID='"+ data.get(1) +"'");
			res = statement.executeUpdate("delete from tb_Student where StudentID='"+ data.get(1) +"'");
		}
		break;
		case 1:
		{
//			res = statement.executeUpdate("delete from Student where ClassID='"+ data.get(1) +"'");
			res = statement.executeUpdate("delete from tb_Student where ClassID='"+ data.get(1) +"'");
		}
		break;
		default:
			break;
		}
		
		statement.close();
		con.close();
		System.out.println("delete success");
		return res;
	}
	
	public int update(Connection connection, List<Object> data)throws Exception{
		Statement statement = con.createStatement();
		int res = -1;
		switch((int)data.get(0)) {//type, newdata, olddata
		//0---update classid, 1---update teacher, 2----update major, 3--studentname, 4--studentphone, 5--studentid, 6--studentgender, 7--studentorigion, 8--studentcredit, 9--studentstatus
		//10--all of admin
		//11--wide update--update classid, teacher, major
		case 0:
		{//classid
//			res = statement.executeUpdate("update Student set ClassID='"+ data.get(1).toString() +"' where StudentID='"+ data.get(2).toString() +"'");
			res = statement.executeUpdate("update tb_Student set ClassID='"+ data.get(1).toString() +"' where StudentID='"+ data.get(2).toString() +"'");
			statement.close();
			con.close();
			System.out.println("update success");
			return res;
		}
		case 1:
		{//teacher
//			res = statement.executeUpdate("update Student set TeacherID='"+ data.get(1).toString() +"' where StudentID='"+ data.get(2).toString() +"'");
			res = statement.executeUpdate("update tb_Student set TeacherID='"+ data.get(1).toString() +"' where StudentID='"+ data.get(2).toString() +"'");
			statement.close();
			con.close();
			System.out.println("update success");
			return res;
		}
		case 2:
		{//major
//			res = statement.executeUpdate("update Student set Major='"+ data.get(1).toString() +"' where StudentID='"+ data.get(2).toString() +"'");
			res = statement.executeUpdate("update tb_Student set Major='"+ data.get(1).toString() +"' where StudentID='"+ data.get(2).toString() +"'");
			statement.close();
			con.close();
			System.out.println("update success");
			return res;
		}
		case 3:
		{//studentname
//			res = statement.executeUpdate("update Student set StudentName='"+ data.get(1).toString() +"' where StudentID='"+ data.get(2).toString() +"'");
			res = statement.executeUpdate("update tb_Student set StudentName='"+ data.get(1).toString() +"' where StudentID='"+ data.get(2).toString() +"'");
			statement.close();
			con.close();
			System.out.println("update success");
			return res;
		}
		case 4:
		{//studentphone
//			res = statement.executeUpdate("update Student set StudentPhone='"+ data.get(1).toString() +"' where StudentID='"+ data.get(2).toString() +"'");
			res = statement.executeUpdate("update tb_Student set StudentPhone='"+ data.get(1).toString() +"' where StudentID='"+ data.get(2).toString() +"'");
			statement.close();
			con.close();
			System.out.println("update success");
			return res;
		}
		case 5:
		{//studentid
//			res = statement.executeUpdate("update Student set StudentID='"+ data.get(1).toString() +"' where StudentID='"+ data.get(2).toString() +"'");
			res = statement.executeUpdate("update tb_Student set StudentID='"+ data.get(1).toString() +"' where StudentID='"+ data.get(2).toString() +"'");
			statement.close();
			con.close();
			System.out.println("update success");
			return res;
		}
		case 6:
		{//studentgender
//			res = statement.executeUpdate("update Student set StudentGender='"+ (Boolean)data.get(1) +"' where StudentID='"+ data.get(2).toString() +"'");
			res = statement.executeUpdate("update tb_Student set StudentGender='"+ (Boolean)data.get(1) +"' where StudentID='"+ data.get(2).toString() +"'");
			statement.close();
			con.close();
			System.out.println("update success");
			return res;
		}
		case 7:
		{//studentorigion
//			res = statement.executeUpdate("update Student set StudentOrigion='"+ data.get(1).toString() +"' where StudentID='"+ data.get(2).toString() +"'");
			res = statement.executeUpdate("update tb_Student set StudentOrigion='"+ data.get(1).toString() +"' where StudentID='"+ data.get(2).toString() +"'");
			statement.close();
			con.close();
			System.out.println("update success");
			return res;
		}
		case 8:
		{//studentcredit
//			res = statement.executeUpdate("update Student set StudentCredit='"+ (Double)data.get(1) +"' where StudentID='"+ data.get(2).toString() +"'");
			res = statement.executeUpdate("update tb_Student set StudentCredit='"+ (Double)data.get(1) +"' where StudentID='"+ data.get(2).toString() +"'");
			statement.close();
			con.close();
			System.out.println("update success");
			return res;
		}
		case 9:
		{//studentstatus
//			res = statement.executeUpdate("update Student set StudentStatus='"+ data.get(1).toString() +"' where StudentID='"+ data.get(2).toString() +"'");
			res = statement.executeUpdate("update tb_Student set StudentStatus='"+ data.get(1).toString() +"' where StudentID='"+ data.get(2).toString() +"'");
			statement.close();
			con.close();
			System.out.println("update success");
			return res;
		}
		case 10:
		{//classid + studentid + studentname + studentphone
//			res = statement.executeUpdate("update Student set ClassID='"+ data.get(1).toString() +"', StudentID='"+ data.get(2).toString() +"', StudentName='"+ data.get(3).toString() +"', StudentPhone='"+ data.get(4).toString() +"' where StudentID='"+ data.get(5).toString() +"'");
			res = statement.executeUpdate("update tb_Student set ClassID='"+ data.get(1).toString() +"', StudentID='"+ data.get(2).toString() +"', StudentName='"+ data.get(3).toString() +"', StudentPhone='"+ data.get(4).toString() +"' where StudentID='"+ data.get(5).toString() +"'");
			statement.close();
			con.close();
			System.out.println("update success");
			return res;
		}
		case 11:
		{//classid
//			res = statement.executeUpdate("update Student set ClassID='"+ data.get(1).toString() +"' where ClassID='"+ data.get(2).toString() +"'");
			res = statement.executeUpdate("update tb_Student set ClassID='"+ data.get(1).toString() +"' where ClassID='"+ data.get(2).toString() +"'");
			statement.close();
			con.close();
			System.out.println("update success");
			return res;
		}
		case 12:
		{//teacher
//			res = statement.executeUpdate("update Student set TeacherId='"+ data.get(1).toString() +"' where ClassID='"+ data.get(2).toString() +"'");
			res = statement.executeUpdate("update tb_Student set TeacherId='"+ data.get(1).toString() +"' where ClassID='"+ data.get(2).toString() +"'");
			statement.close();
			con.close();
			System.out.println("update success");
			return res;
		}
		case 13:
		{// major
//			res = statement.executeUpdate("update Student set Major='"+ data.get(1).toString() +"' where ClassID='"+ data.get(2).toString() +"'");
			res = statement.executeUpdate("update tb_Student set Major='"+ data.get(1).toString() +"' where ClassID='"+ data.get(2).toString() +"'");
			statement.close();
			con.close();
			System.out.println("update success");
			return res;
		}
		case 14:
		{//teacher major
//			res = statement.executeUpdate("update Student set TeacherId='"+ data.get(1).toString() +"', Major='"+ data.get(2).toString() +"' where ClassID='"+ data.get(3).toString() +"'");
			res = statement.executeUpdate("update tb_Student set TeacherId='"+ data.get(1).toString() +"', Major='"+ data.get(2).toString() +"' where ClassID='"+ data.get(3).toString() +"'");
			statement.close();
			con.close();
			System.out.println("update success");
			return res;
		}
		case 15:
		{//name, origin, phone, status, gender ---- id
			res = statement.executeUpdate("update tb_Student set StudentName='"+ data.get(1).toString() +"', StudentOrigion='"+ data.get(2).toString() +"', StudentPhone='"+ data.get(3).toString() +"', StudentStatus='"+ data.get(4).toString() +"', StudentGender='"+ (Boolean)data.get(5) +"' where StudentID='"+ data.get(6).toString() +"'");
//			res = statement.executeUpdate("update Student set StudentName='"+ data.get(1).toString() +"', StudentOrigion='"+ data.get(2).toString() +"', StudentPhone='"+ data.get(3).toString() +"', StudentStatus='"+ data.get(4).toString() +"', StudentGender='"+ (Boolean)data.get(5) +"' where StudentID='"+ data.get(6).toString() +"'");
			statement.close();
			con.close();
			System.out.println("update success");
			return res;
		}
		default:
			break;
		}
		return res;
	}
	
	public Vector<Student> select(Connection con, List<Object>data)throws Exception{
		Vector<Student> stu = new Vector<Student>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
//			rs = st.executeQuery("select * from Student");
			rs = st.executeQuery("select * from tb_Student");
			int i = 0;
			while(rs.next()) {
				Student temp = new Student(rs.getString(1), rs.getString(2), rs.getString(3) , rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getString(9) , rs.getDouble(10));
				switch((int)data.get(0)) {
				case 0:
				{
					//select from studentid
					if((temp.getStudentid()).compareTo((data.get(1)).toString()) == 0) {
						stu.add(temp);
						System.out.println(temp.getClassid());
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
	
	//for class manage
	public Vector<ClassManage> getallclass(Connection con) throws Exception{
		Vector<ClassManage> clss = new Vector<ClassManage>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
//			rs = st.executeQuery("select * from Class");
			rs = st.executeQuery("select * from tb_StudentClass");
			int i = 0;
			while(rs.next()) {
				ClassManage temp = new ClassManage(rs.getString(1), rs.getString(2), rs.getString(3) , rs.getInt(4));
				clss.addElement(temp);
			}
			System.out.println("Class getall success");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			st.close();
			rs.close();
			con.close();
		}
		return clss;
	}
	
	public int addclass(Connection connection, ClassManage clss) throws Exception{
//		PreparedStatement ps = con.prepareStatement("insert into Class(ClassID,TeacherId,Major,ClassSize) values('"+ clss.getClassID() +"','"+ clss.getTeacherID() +"','"+ clss.getMajor() +"','"+ clss.getClassSize() +"')");
		PreparedStatement ps = con.prepareStatement("insert into tb_StudentClass(ClassID,TeacherId,Major,ClassSize) values('"+ clss.getClassID() +"','"+ clss.getTeacherID() +"','"+ clss.getMajor() +"','"+ clss.getClassSize() +"')");
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}
	
	public int deleteclass(Connection con, String id) throws Exception{
		Statement statement = con.createStatement();
//		int res = statement.executeUpdate("delete from Class where ClassID='"+ id +"'");
		int res = statement.executeUpdate("delete from tb_StudentClass where ClassID='"+ id +"'");
		statement.close();
		con.close();
		return res;
	}
	
	public int updateclass(Connection connection, List<Object> data)throws Exception{
		Statement statement = con.createStatement();
		int res = -1;
		switch((int)data.get(0)) {//type, newdata, olddata
		//0---update classid, 1---update teacher, 2----update major，3--all 
		case 0:
		{
//			res = statement.executeUpdate("update Class set ClassID='"+ data.get(1).toString() +"' where ClassID='"+ data.get(2).toString() +"'");
			res = statement.executeUpdate("update tb_sStudentClass set ClassID='"+ data.get(1).toString() +"' where ClassID='"+ data.get(2).toString() +"'");
		}
		break;
		case 1:
		{
//			res = statement.executeUpdate("update Class set TeacherId='"+ data.get(1).toString() +"' where ClassID='"+ data.get(2).toString() +"'");
			res = statement.executeUpdate("update tb_StudentClass set TeacherId='"+ data.get(1).toString() +"' where ClassID='"+ data.get(2).toString() +"'");
		}
		break;
		case 2:
		{
//			res = statement.executeUpdate("update Class set Major='"+ data.get(1).toString() +"' where ClassID='"+ data.get(2).toString() +"'");
			res = statement.executeUpdate("update tb_StudentClass set Major='"+ data.get(1).toString() +"' where ClassID='"+ data.get(2).toString() +"'");
		}
		break;
		case 3:
		{//all
//			res = statement.executeUpdate("update Class set TeacherId='"+ data.get(1).toString() +"', Major='"+ data.get(2).toString() +"', ClassID='"+ data.get(3).toString() +"' where ClassID='"+ data.get(4).toString() +"'");
			res = statement.executeUpdate("update tb_StudentClass set TeacherId='"+ data.get(1).toString() +"', Major='"+ data.get(2).toString() +"', ClassID='"+ data.get(3).toString() +"' where ClassID='"+ data.get(4).toString() +"'");
		}
		break;
		case 4:
		{
			res = statement.executeUpdate("update tb_StudentClass set ClassSize='"+ Integer.parseInt(data.get(1).toString()) +"' where ClassID='"+ data.get(2).toString() +"'");
		}
		default:
			break;
		}
		statement.close();
		con.close();
		return res;
	}
	
	public Vector<ClassManage> selectclass(Connection con, List<Object>data)throws Exception{
		Vector<ClassManage> clss = new Vector<ClassManage>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
//			rs = st.executeQuery("select * from Class");
			rs = st.executeQuery("select * from tb_StudentClass");
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