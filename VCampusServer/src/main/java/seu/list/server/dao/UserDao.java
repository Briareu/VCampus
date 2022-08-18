<<<<<<< HEAD
package VCampusServer.src.main.java.seu.list.server.dao;
import VCampusServer.src.main.java.seu.list.common.User;
=======
//package main.java.seu.list.server.dao;
package seu.list.server.dao;
>>>>>>> 27e89bf9ba1d83dbc5221972383f137fcce3b463

import java.util.List;
import java.util.Vector;

<<<<<<< HEAD


=======
//import main.java.seu.list.common.User;
import seu.list.common.*;
>>>>>>> 27e89bf9ba1d83dbc5221972383f137fcce3b463


public interface UserDao {
    
	public User getUser(User user);
	public User getUserByPwd(Vector<String> content);
	public User addUser(User user);
	public boolean delUser(String userID);
	//public boolean updUser(User user);
	public User searchUser(String id);		
	public List<User> getAllUsers();

}
