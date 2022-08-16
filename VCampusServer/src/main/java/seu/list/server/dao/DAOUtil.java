package seu.list.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import virtualSchoolServer.common.Book;
import virtualSchoolServer.common.Course;
import virtualSchoolServer.common.Goods;
import virtualSchoolServer.common.User;


public class DAOUtil {
	public static List<User> UserResultSet2List(ResultSet rs) throws SQLException {
		List<User> users = new ArrayList<User>();
		while(rs.next())
		{				
			User u = new User();
			u.setId(rs.getString(1));
			u.setName(rs.getString(2));
			u.setAge(rs.getString(3));
			u.setSex(rs.getString(4));
			u.setGrade(rs.getString(5));
			u.setMajor(rs.getString(6));
			u.setPwd(rs.getString(7));
			u.setRole(rs.getString(8));
			u.setMoney(rs.getString(9));
			users.add(u);
		}	
		return users;
	}
	public static List<Book> BookResultSet2List(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		while(rs.next())
		{				
			Book  b= new Book(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
			books.add(b);
		}	
		return books;
	}
	public static List<Goods> GoodsResultSet2List(ResultSet rs) throws SQLException {
		List<Goods> goods = new ArrayList<Goods>();
		while(rs.next())
		{				
			Goods g = new Goods(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
			goods.add(g);
		}	
		return goods;
	}
	public static List<Course> CourseResultSet2List(ResultSet rs) throws SQLException {
		List<Course> courses = new ArrayList<Course>();
		while(rs.next())
		{	//String courseID, String semester, String courseName,
			// String courseMajor, String teacherID, String courseState, String courseType
			Course c=new Course();
			c.setSemester(rs.getString(1));
			c.setCourseID(rs.getString(2));
			c.setCourseMajor(rs.getString(3));
			c.setTeacherID(rs.getString(4));
			c.setCourseState(rs.getString(5));
			c.setCourseName(rs.getString(6));
			c.setCourseType(rs.getString(7));
			//for(int i=1;i<=7;i++)System.out.print(rs.getString(i)+"\t");
			//System.out.print(rs.getString(6)+"\t");
			courses.add(c);
		}	
		return courses;
	}
	public static List<String> relationResulList(ResultSet rs) throws SQLException{
		List<String> objs = new  ArrayList<String>();
		while(rs.next())
		{
			String obj=rs.getString(3);
			objs.add(obj); 
		}
		return objs;
	}
}
