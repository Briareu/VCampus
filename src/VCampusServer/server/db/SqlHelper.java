package virtualSchoolServer.server.db;

import virtualSchoolServer.common.Book;
import virtualSchoolServer.common.Course;
import virtualSchoolServer.common.Goods;
import virtualSchoolServer.common.User;

import java.util.*;


public interface SqlHelper {
	//用于系统管理
	public List<User> sqlUserQuery(String sql, String []paras);
	public List<Book> sqlBookQuery(String sql, String []paras);
	public List<Goods> sqlGoodsQuery(String sql, String []paras);
	public List<Course> sqlCourseQuery(String sql, String []paras);
	public List<String> sqlRelationQuery(String sql,String[] paras);
	//连接数据库并更新数据
	public boolean sqlUpdate(String sql,String []paras);
}
