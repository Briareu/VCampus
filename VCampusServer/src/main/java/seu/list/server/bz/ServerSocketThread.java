package seu.list.server.bz;

import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.ModuleType;
import seu.list.server.dao.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * 类{@code ServerSocketThread}为服务器客户端线程类，用于处理与客户端的通信 <br>
 * 每个连接到服务器的客户端独占一个服务器客户端线程 <br>
 * 主体为{@code while}循环，等待客户端发送数据，用标志{@code isClosed}和{@code Socket}的关闭来控制循环 <br>
 * <br>
 * 私有数据成员: <br>
 * 1. {@code clientSocket}, 类型: {@code Socket}, 客户端的{@code Socket} <br>
 * 2. {@code id}, 类型: {@code String}, 客户端线程ID <br>
 * 3. {@code isClosed}, 类型: {@code boolean}, 标志，用于退出{@code while}循环
 * @author 吴慕陶 柳多荣
 * @version 1.1
 * @see java.lang.Thread
 */
public class ServerSocketThread extends Thread {
	private Socket clientSocket = null;	
	private String id = null;
	private boolean isClosed = false;

	public ServerSocketThread(Socket socket, String id) {
		this.clientSocket = socket;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			//start try
			System.out.println("已与客户端建立连接，当前客户端ip为："+clientSocket.getInetAddress().getHostAddress());
			
			while(!this.isClosed && !this.clientSocket.isClosed()) {
				Message message = new Message();						
				ObjectInputStream request = new ObjectInputStream(clientSocket.getInputStream());
				message = (Message)request.readObject();
				
				if(message.isOffline()) { // 收到客户端的下线通知
					this.isClosed = true;
					break;
				}
				
				System.out.println(message.getModuleType());
				System.out.println(message.getMessageType());
				
				Message serverResponse = this.processMes(message); // 处理消息
					
				this.sendMesToClient(serverResponse); // 这里统一发回数据给客户端
			} // end while
						
		}catch(SocketException se) {
			System.out.println("Socket closed");
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(ServerClientThreadMgr.get(this.id) != null) {
				ServerClientThreadMgr.remove(this.id);
			}
			System.out.println("客户端线程: " + this.id + "已关闭");
		}
	}
	
	public Message processMes(Message message) {
		Message serverResponse = null;
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
		return serverResponse;
	}
	
	public void sendMesToClient(Message mes) {
		mes.setMessageType(MessageType.operFeedback);
		mes.setLastOperState(true);
		System.out.println("向客户端发送信息: " + mes.getMessageType());
		try {
			ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
			response.writeObject(mes); // 这里统一发回数据给客户端
			response.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		this.isClosed = true;
		try {
			this.clientSocket.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("关闭客户端线程：" + this.id);
	}
	
	public String getCliThdID() {
		return this.id;
	}
	
	public String getIP() {
		return this.clientSocket.getInetAddress().getHostAddress();
	}
}
