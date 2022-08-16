package main.java.seu.list.server.dao;

import main.java.seu.list.common.Message;
import main.java.seu.list.common.MessageType;
import main.java.seu.list.common.Student;
import main.java.seu.list.server.db.StudentAccessHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;



public class ClassAdminServer extends StudentAccessHelper {
	Connection con = null;
	Statement s = null;
	ResultSet rs = null;
	
	// Modified by WU 8.16
	private Message mesFromClient; // 从客户端收到的数据
	private Object dataToClient = null; // 发给客户端的数据
	
	public ClassAdminServer(Message mesFromClient) {
		this.mesFromClient = mesFromClient;
	}
	
	public void execute() { 
		// 根据类型去执行不同的DAO层操作，不同模块的DAO类需要修改这个函数
		// 如果操作需要的参数，请在mesFromClient内取出
		// 如果操作需要返回数据给客户端，请存入dataToClient，如果没有返回值，则默认为null
		switch(this.mesFromClient.getMessageType()) 
		{
			case MessageType.ClassAdminAdd:
				this.add();
				break;
			case MessageType.ClassAdminDelete:
				this.dataToClient = this.delete(this.mesFromClient.getData().toString());
				break;
			case MessageType.ClassAdminGetAll:
				this.dataToClient = this.getall();
				break;
			case MessageType.ClassAdminUpdate:
				this.update();
				break;
			default:
				break;
		}
	}

	public Object getDataToClient() { // 无需修改，网络层需要调用这个函数
		return this.dataToClient;
	}
	// Modified by WU 8.16
	
	public Vector<Student> getall() {
		Vector<Student> stu = new Vector<Student>();
		try {
			con = getConnection();
			s = con.createStatement();//SQL object
			rs = s.executeQuery("select * from Student");//Student message
			int i = 0;
			while(rs.next()) {
				Student temp = new Student(rs.getString(1), rs.getString(2), rs.getString(3) , rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getString(9) , rs.getDouble(10));
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
			result = s.executeUpdate("update Student set StudentID=666 where StudentName='寮犱笁'");
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
			result = s.executeUpdate("delete from Student where StudentID='"+ student +"'");
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
	
	/*
	public static void main(String[] args) {
		ClassAdminServer serve=new ClassAdminServer();
		serve.delete("3");
	}
	*/
}
