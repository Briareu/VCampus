package seu.list.server.dao;

import seu.list.common.Course;
import seu.list.common.Message;

import java.util.*;
/**
 * @author 郭念宗
 * @version jdk1.8.0
 */
public interface CourseDao {
		/**
		 * 执行不同的操作
		 */
		public void execute();
		/**
		 * @return 获取服务器发送给客户端的消息
		 */
		public Message getMesToClient();
		/**
		 * @return 获取全部课程信息
		 */
		public List<Course> getAllCourse();
		/**
		 * 学生选课
		 * @param courseID 课程编号
		 * @param uID 学生学号
		 */
		public boolean sigAddCourse(String courseID,String uID);
		/**
		 * 学生退课
		 * @param courseID 课程编号
		 * @param uID 学生学号
		 */
		public boolean sigRemoveCourse(String courseID,String uID);
		/**
		 * 管理员添加课程
		 * @param course 课程信息
		 */
		public boolean genAddCourse(Course course);
		/**
		 * 管理员删除课程
		 * @param courseID 课程编号
		 */
		public boolean genRemoveCourse(String courseID);
		/**
		 * 根据课程编号搜索课程
		 * @param courseID 课程编号
		 */
		public Course searchCourseByID(String courseID);
	}
