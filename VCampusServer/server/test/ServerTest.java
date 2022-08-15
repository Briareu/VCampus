package virtualSchoolServer.server.test;


import virtualSchoolServer.server.bz.ServerSocketSrvImp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;



public class ServerTest {
	public static void main(String[] args) throws IOException {
	/*User user=new User();
		user.setId("2");
		user.setAge("22");
		user.setGrade("大一");
		user.setMajor("数学");
		user.setMoney("12324");
		user.setName("你爹");
		user.setPwd("362");
		user.setRole("老师");
		user.setSex("女");
		
		
		UserDaoImpl tempDao = new UserDaoImpl();
		List<User> userList =  tempDao.getAllUsers();
		User tempUser= new User();
		System.out.println(userList.size());
		Iterator<User> check= userList.iterator();
		while(check.hasNext()) {
			tempUser = check.next();
			tempUser.print();
		}
		
		tempDao.searchUser("123").print();*/
		System.out.println("Server strated");
		ServerSocketSrvImp sssi=new ServerSocketSrvImp();
		sssi.run();
	}
}