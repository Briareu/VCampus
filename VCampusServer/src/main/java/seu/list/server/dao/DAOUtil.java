package seu.list.server.dao;

import seu.list.common.Course;
import seu.list.common.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOUtil {
	public static List<Course> CourseResultSet2List(ResultSet rs) throws SQLException {
		List<Course> courses = new ArrayList<Course>();
		while(rs.next())
		{	//String courseID, String semester, String courseName,
			// String courseMajor, String teacherID, String courseState, String courseType
			Course c=new Course();
			c.setSemester(rs.getString(2));
			c.setCourseID(rs.getString(1));
			c.setCourseMajor(rs.getString(4));
			c.setTeacherID(rs.getString(5));
			c.setCourseState(rs.getString(6));
			c.setCourseName(rs.getString(3));
			c.setCourseType(rs.getString(7));
			for(int i=1;i<=7;i++)System.out.print(rs.getString(i)+"\t");
			//System.out.print(rs.getString(6)+"\t");
			System.out.println(c);
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
}
