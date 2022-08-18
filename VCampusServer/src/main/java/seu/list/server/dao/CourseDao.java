package VCampusServer.src.main.java.seu.list.server.dao;
//package seu.list.server.dao;


//import VCampusServer.src.main.java.seu.list.common.Course;


import VCampusClient.src.main.java.seu.list.common.Message;
import VCampusServer.src.main.java.seu.list.common.Course;


import java.util.*;

public interface CourseDao {
		public void execute();
		public Message getMesToClient();

		public List<Course> getAllCourse();
		public boolean sigAddCourse(String couseID,String uID);//瀛︾敓鐢ㄤ簬閫夎
		public boolean sigRemoveCourse(String courseID,String uID);//瀛︾敓鐢ㄤ簬閫�閫�
		public boolean genAddCourse(Course course);//绠＄悊鍛橈紝鑰佸笀鐢ㄤ簬澧炲姞璇剧▼
		public boolean genRemoveCourse(String courseID);//绠＄悊鍛橈紝鑰佸笀鐢ㄤ簬鍒犻櫎璇剧▼
		public Course searchCourseByID(String coutseID);
	}
