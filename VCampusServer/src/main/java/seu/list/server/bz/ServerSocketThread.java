/**
 * to do with thread
 * @author LIU
 * @version 1.0 8/14
 * 
 * you need to import the package of server and dao
 * you need to add your server implementation like: Server server = new Server();
 * you need to add the operations you want the thread to do with your module, like ClassAdd\Delete or something
 * you need to do the things through input-stream and out-stream, there are examples under
 * remember to update the 'MessageType.java' file
 * this is a demo, you can update the socket frame and the way of message-passing any time and tell us
 */
//package VCampusServer.src.main.java.seu.list.server.bz;
package seu.list.server.bz;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;

/*
import VCampusServer.src.main.java.seu.list.common.*;
import VCampusServer.src.main.java.seu.list.server.dao.CourseDaoImp;
import main.java.seu.list.server.dao.ClassAdminServer;
*/

import seu.list.common.*;
import seu.list.server.dao.*;
import seu.list.server.dao.CourseDaoImp;
import seu.list.server.dao.ClassAdminServer;


public class ServerSocketThread implements Runnable {
	private Socket clientSocket = null;
	private String id = null;
	private boolean isClosed = false;

	ServerSocketThread(Socket socket, String id) {
		this.clientSocket = socket;
		this.id = id;
	}

	@Override
	public synchronized void run() {
		
		try {
			//start try
			ObjectInputStream request = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
			System.out.println("已与客户端建立连接，当前客户端ip为："+clientSocket.getInetAddress().getHostAddress());
			
			while(!this.isClosed) {
				Message message = (Message)request.readObject();
				if(message.isOffline()) {
					isClosed = true;
					ServerClientThreadMgr.remove(this.id);
					break;
				}
				
				System.out.println(message.getModuleType());
				System.out.println(message.getMessageType());
				Message serverResponse = new Message();
				
				try {
					switch(message.getModuleType())
					{
						case ModuleType.User: // 用户管理模块
							
							break;
						case ModuleType.Student: // 学生学籍管理模块
							ClassAdminServer classAdminServer = new ClassAdminServer(message);
							classAdminServer.execute();
							serverResponse = classAdminServer.getMesToClient();
							break;
						case ModuleType.Course:
							// 选课模块
							CourseDaoImp courseServer = new CourseDaoImp(message);
							courseServer.execute();
							serverResponse = courseServer.getMesToClient();
							break;
						case ModuleType.Library: // 图书馆模块
							
							break;
						case ModuleType.Shop: // 商店模块
							
							break;
						case ModuleType.Dormitory: // 宿舍模块
							
							break;
						default:
							break;
					}
				} finally {
					serverResponse.setMessageType(MessageType.operFeedback);
					serverResponse.setLastOperState(true);
					ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
					response.writeObject(serverResponse);
					response.flush();
					response.close();
				}
			}
			request.close();
			this.clientSocket.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		System.out.println("关闭客户端线程：" + this.id);
		this.isClosed = true;
		
		try {
			if(!this.clientSocket.isClosed()) {
				this.clientSocket.close();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		if(ServerClientThreadMgr.get(id) != null) {
			ServerClientThreadMgr.remove(id);
		}
	}
}