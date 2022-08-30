package seu.list.server.dao;

import java.util.List;
import java.util.Vector;

import seu.list.common.*;
/**
 * @author 郭念宗
 * @version jdk1.8.0
 */
public interface UserDao {
	/**
	 * 获取指定用户信息
	 * @param user 包含用户id和姓名
	 * @return 返回符合条件的用户信息
	 */
	public User getUser(User user);
	/**
	 * 登录验证
	 * @param content 用户信息
	 * @return 返回符合条件的用户信息
	 */
	public User getUserByPwd(Vector<String> content);
	/**
	 * 添加用户
	 * @param user 要添加的用户的信息
	 * @return 返回添加的用户信息，如果为null则添加失败
	 */
	public User addUser(User user);
	/**
	 * 删除用户
	 * @param userID 要删除的用户的ID
	 * @return 返回删除是否失败true-成功，false-失败
	 */
	public boolean delUser(String userID);
	//public boolean updUser(User user);
	/**
	 * 搜索用户
	 * @param id 要搜索的用户的ID
	 * @return 返回搜索到的用户的信息
	 */
	public User searchUser(String id);
	/**
	 * 过去所有用户的信息
	 * @return 返回包含所有用户信息的列表
	 */
	public List<User> getAllUsers();
	/**
	 * 更新指定用户的信息
	 * @param userID 用户原ID
	 * @param newID	用户新ID
	 * @return 返回更新是否成功，true-成功，false-失败
	 */
	public boolean updateUser(String userID,String newID);
}
