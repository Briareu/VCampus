//package main.java.seu.list.server.dao;
package seu.list.server.dao;

/*
import main.java.seu.list.common.Message;
import main.java.seu.list.common.MessageType;
import main.java.seu.list.common.Student;
import main.java.seu.list.server.db.StudentAccessHelper;
*/

import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.Student;
import seu.list.server.db.StudentAccessHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;



public class ClassAdminServer extends StudentAccessHelper {
	Connection con = null;
	Statement s = null;
	ResultSet rs = null;
	
	// Modified by WU 8.16
	// 在你的DAO类文件里添加以下两个数据成员
	private Message mesFromClient; // 从客户端发来的信息
	private Message mesToClient;  // 送回给客户端的信息
	
	// 在你的DAO类文件里添加以下这样的构造函数，接收从客户端发来的信息
	public ClassAdminServer(Message mesFromClient) {
		this.mesFromClient = mesFromClient;
	}
	
	// 在你的DAO类文件里添加以下这样一个函数，函数名称就叫execute，函数体内就一个switch结构来进行不同种类的操作
	public void execute() { 
		switch(this.mesFromClient.getMessageType()) // 读取接收到的信息
		{
			// 
			case MessageType.ClassAdminAdd:{
				this.add();
				break;
			}
			case MessageType.ClassAdminDelete:{
				String para = (String)this.mesFromClient.getData(); 
				// 如果你的DAO操作需要参数，可以从mesFromClient取出来并转换成你想要的类型
				int ret = this.delete(para);
				// 如果你的DAO操作有返回值需要送回给客户端，或者有其他数据想要送回客户端，
				// 可以放入mesToClient，可以放任意抽象数据类型，因为接收的是Object
				this.mesToClient.setData(ret);
				break;
			}
			case MessageType.ClassAdminGetAll:{
				Vector<Student> ret = this.getall();
				this.mesToClient.setData(ret);
				break;
			}
			case MessageType.ClassAdminUpdate:{
				this.update();
				break;
			}
			default:
				break;
		}
	}

	// 在你的DAO类文件中添加以下这样的函数，保持一模一样
	public Message getMesToClient() { 
		return this.mesToClient;
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
