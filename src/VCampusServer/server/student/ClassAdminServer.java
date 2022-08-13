package VCampusServer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import VCampusDomain.StudentManage;
import VCampusServer.StudentAccess;
import VCampusServer.ClassAccess;

public class ClassAdminServer extends StudentAccess{
	Connection con = null;
	Statement s = null;
	ResultSet rs = null;
	
	public Vector<StudentManage> search() {
		Vector<StudentManage> stu = new Vector<StudentManage>();
		try {
			con = getConnection();
			s = con.createStatement();//SQL object
			rs = s.executeQuery("select * from Student");//Student message
			int i = 0;
			while(rs.next()) {
				StudentManage temp = new StudentManage(rs.getString(1), rs.getString(2), rs.getString(3) , rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getString(9) , rs.getDouble(10));
				stu.addElement(temp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con,rs,s);
		}
		System.out.println("search completion\t");
		return stu;
	}
	
	public void add() {
		try {
			int result = 0;
			con = getConnection();
			s = con.createStatement();//SQL object
			result = s.executeUpdate("insert into Student(StudentID, StudentName, TeacherID, ClassID, Major, StudentOrigion, StudentStatus, StudentGender, StudentPhone, StudentCredit) values('1','2','33','4','5','6','7','false','9','10')");
			if(result>0) {
				System.out.println("insert completion\t");//sucess
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, rs, s);//close
		}
		//search();
	}
	
	public void update() {
		try {
			int result = 0;
			con = getConnection();
			s = con.createStatement();//SQL
			result = s.executeUpdate("update Student set StudentID=666 where StudentName='张三'");
			if(result>0) {
				System.out.println("update completion\t");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, rs, s);
		}
	}
	
	public int delete(String student) {
		int res = 0;
		try {
			int result= 0;
			con = getConnection();
			s = con.createStatement();//SQL
			result = s.executeUpdate("delete from Student where StudentName='"+ student +"'");
			if(result>0) {
				res = result;
				System.out.println("delete completion\t");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con,rs,s);
		}
		return res;
	}
	
	public static void main(String[] args) {
		ClassAdminServer serve=new ClassAdminServer();
		serve.delete("3");
	}
}
