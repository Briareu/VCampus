package seu.list.server.dao;

import virtualSchoolServer.common.Course;

import java.util.*;

public interface CourseDao {



		public List<Course> getAllCourse();
		public boolean sigAddCourse(String couseID,String uID);//学生用于选课
		public boolean sigRemoveCourse(String courseID,String uID);//学生用于退选
		public boolean genAddCourse(Course course);//管理员，老师用于增加课程
		public boolean genRemoveCourse(String courseID);//管理员，老师用于删除课程
		public Course searchCourseByID(String coutseID);
	}
