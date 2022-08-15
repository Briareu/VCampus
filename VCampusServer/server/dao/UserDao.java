package virtualSchoolServer.server.dao;

import java.util.List;
import java.util.Vector;

import virtualSchoolServer.common.User;


public interface UserDao {
    
	public User getUser(User user);
	public User getUserByPwd(Vector<String> content);
	public User addUser(User user);
	public boolean delUser(String userID);
	//public boolean updUser(User user);
	public User searchUser(String id);		
	public List<User> getAllUsers();

}
