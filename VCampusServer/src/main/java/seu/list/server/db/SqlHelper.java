package VCampusServer.src.main.java.seu.list.server.db;


import VCampusServer.src.main.java.seu.list.common.Course;
import VCampusServer.src.main.java.seu.list.common.User;


import java.util.*;


public interface SqlHelper {
	//用于系统管理
	public List<User> sqlUserQuery(String sql, String []paras);

	public List<Course> sqlCourseQuery(String sql, String []paras);
	public List<String> sqlRelationQuery(String sql,String[] paras);
	//连接数据库并更新数据
	public boolean sqlUpdate(String sql,String []paras);
}
