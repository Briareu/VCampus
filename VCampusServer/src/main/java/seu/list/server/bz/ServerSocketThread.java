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
 * 
 * 
 * @author WU
 * @version 2.0 8/18
 * 
 * 
 * 
 */

package seu.list.server.bz;

import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.ModuleType;
import seu.list.server.dao.*;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;

public class ServerSocketThread extends Thread {
	private Socket clientSocket = null;	
	private String id = null;
	private boolean isClosed = false;

	public ServerSocketThread(Socket socket, String id) {
		this.clientSocket = socket;
		this.id = id;
	}

	//@Override
	public synchronized void run() {
		try {
			//start try
			System.out.println("已与客户端建立连接，当前客户端ip为："+clientSocket.getInetAddress().getHostAddress());
			
			while(!this.isClosed && !this.clientSocket.isClosed()) {
				Message message = new Message();						
				ObjectInputStream request = new ObjectInputStream(clientSocket.getInputStream());
				message = (Message)request.readObject();
				
				if(message.isOffline()) { // 收到客户端的下线通知
					break;
				}
				
				System.out.println(message.getModuleType());
				System.out.println(message.getMessageType());
				
				Message serverResponse = new Message();
				switch(message.getModuleType()) {
					case ModuleType.User: {// 用户管理模块
						UserDaoImpl iud=new UserDaoImpl(message,this.id);
						iud.excute();
						serverResponse=iud.getMesToClient();
						break;
					}
					case ModuleType.Student: {// 学生学籍管理模块
						// 构造一个对应模块DAO类的对象，并送入客户端发来的信息
						ClassAdminServer classAdminServer = new ClassAdminServer(message);
						// 调用execute函数执行对应的操作
						classAdminServer.execute();
						// 获得想要发回客户端的数据
						serverResponse = classAdminServer.getMesToClient();
						break;
					}
					case ModuleType.Course: {
						// 选课模块
						CourseDaoImp courseServer = new CourseDaoImp(message);
						courseServer.execute();
						serverResponse = courseServer.getMesToClient();
						System.out.println(serverResponse.getContent());
						break;
					}
					case ModuleType.Library: {// 图书馆模块
						LibraryUserServer libServer = new LibraryUserServer(message);
						libServer.execute();
						serverResponse = libServer.getMesToClient();
						//System.out.println(serverResponse.getContent());
						break;
					}
					case ModuleType.Shop: {// 商店模块
						ShopSever shop_sever=new ShopSever(message);
						shop_sever.excute();
						serverResponse=shop_sever.getMesToClient();
						break;
					}
					case ModuleType.Dormitory: {// 宿舍模块
						DormitorServer dormitoryServer = new DormitorServer(message);
						dormitoryServer.execute();
						serverResponse = dormitoryServer.getMesToClient();
						System.out.println(serverResponse.getData());
						break;
					}
					default:
						break;
				}
					
				this.sendMesToClient(serverResponse); // 这里统一发回数据给客户端
			} // end while
			
			
			// run方法即将执行完毕，线程即将终止，关闭socket
			if(!this.clientSocket.isClosed()) {
				this.clientSocket.close();
			}
			
			if(ServerClientThreadMgr.get(this.id) != null) {
				ServerClientThreadMgr.remove(this.id);
			}
			
			System.out.println("客户端线程: " + this.id + "已关闭");
			
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMesToClient(Message mes) throws IOException{
		System.out.println("向客户端发送信息");
		mes.setMessageType(MessageType.operFeedback);
		mes.setLastOperState(true);
		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
		response.writeObject(mes); // 这里统一发回数据给客户端
		response.flush();
	}
	
	public void close() {
		System.out.println("关闭客户端线程：" + this.id);
		this.isClosed = true;
	}
}
