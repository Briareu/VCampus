package seu.list.server.db;
//package seu.list.server.db;

/*
import VCampusServer.src.main.java.seu.list.common.Course;
import VCampusServer.src.main.java.seu.list.common.User;
*/




import seu.list.common.Course;
import seu.list.common.User;

import java.util.*;

/**
 * @author 郭念宗
 * @version jdk1.8.0
 */
public interface SqlHelper {
	//用于系统管理
	/**
	 * 根据要求返回用户列表
	 * @param sql 数据库操作语言
	 * @param paras 数据库操作的参数
	 * @return 用户列表
	 */
	public List<User> sqlUserQuery(String sql, String []paras);
	/**
	 * 根据要求返回课程列表
	 * @param sql 数据库操作语言
	 * @param paras 数据库操作的参数
	 * @return 用户列表
	 */
	public List<Course> sqlCourseQuery(String sql, String []paras);
	/**
	 * 根据要求返回已选课程列表
	 * @param sql 数据库操作语言
	 * @param paras 数据库操作的参数
	 * @return 用户列表
	 */
	public List<String> sqlRelationQuery(String sql,String[] paras);
	/**
	 * 根据要求更新数据库文件
	 * @param sql 数据库操作语言
	 * @param paras 数据库操作的参数
	 * @return 更新是否成功
	 */
	//连接数据库并更新数据
	public boolean sqlUpdate(String sql,String []paras);
}
