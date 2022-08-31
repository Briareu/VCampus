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
 * @version 1.0
 * @see java.lang.Thread
 */
public class ServerSocketThread extends Thread {
	private Socket clientSocket = null;	
	private String id = null;
	private boolean isClosed = false;

	/**
	 * 类{@code ServerSocketThread}的构造器，接收服务器主线程传入的参数，初始化{@code Socket}和线程ID
	 * @param socket 该客户端线程对应的客户端{@code Socket}
	 * @param id 该客户端线程的ID，由服务器主线程分配
	 * @author 吴慕陶 
	 * @version 1.0
	 */
	public ServerSocketThread(Socket socket, String id) {
		this.clientSocket = socket;
		this.id = id;
	}

	/**
	 * 客户端线程的{@code run}方法，监听客户端，随时接收对应客户端发来的数据请求并响应 <br>
	 * 主体为{@code while}循环，由标志{@code isClosed}和客户端{@code Socket}的关闭来控制 <br>
	 * 接收到客户端的请求后，将消息送入{@code processMes(Message)}方法进行处理 <br>
	 * 处理完毕后将需要发回客户端的数据统一由{@code sendMesToClient(Message)}方法发送回客户端 <br>
	 * 该线程运行结束将被从线程池中移除销毁
	 * @author 吴慕陶 柳多荣
	 * @version 1.0
	 * @see seu.list.common.Message
	 * @see seu.list.common.MessageType
	 * @see seu.list.common.ModuleType
	 */
	@Override
	public void run() {
		try {
			//start try
			String cliIP = this.getIP();
			System.out.println("已与客户端建立连接，当前客户端ip为：" + cliIP);
			
			while(!this.isClosed && !this.clientSocket.isClosed()) {
				Message message = new Message();						
				ObjectInputStream request = new ObjectInputStream(clientSocket.getInputStream());
				message = (Message)request.readObject();
				
				System.out.println("-------------已收到客户端发送的请求-------------");
				
				if(message.isOffline()) { // 收到客户端的下线通知
					System.out.println("客户端已下线，IP为: " + cliIP + ", 线程ID: " + this.id);
					this.isClosed = true;
					break;
				}
				
				System.out.println("请求来自模块: " + message.getModuleType());
				System.out.println("请求的操作类型: " + message.getMessageType());
				
				Message serverResponse = this.processMes(message); // 处理消息
					
				this.sendMesToClient(serverResponse); // 这里统一发回数据给客户端
				
				System.out.println("-------------已将数据发回客户端-------------");
			} // end while
						
		}catch(SocketException se) {
			System.out.println("Socket closed");
			System.out.println("客户端线程: " + this.id + "已关闭");
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(ServerClientThreadMgr.get(this.id) != null) {
				ServerClientThreadMgr.remove(this.id);
			}
		}
	}
	
	/**
	 * 处理从客户端收到的消息，根据消息的{@code ModuleType}来将消息分配给不同模块的{@code DAO}类处理 <br>
	 * 各模块的{@code DAO}类会根据消息的{@code MessageType}来执行不同的数据库操作 <br>
	 * {@code DAO}类处理完毕会返回{@code MesToClient}, 该消息为服务器对客户端请求的响应
	 * @param message 从客户端收到的消息
	 * @return 处理完毕后送回客户端的消息
	 * @author 吴慕陶
	 * @version 1.0
	 * @see seu.list.common.Message
	 * @see seu.list.common.MessageType
	 * @see seu.list.common.ModuleType
	 * @see seu.list.server.dao
	 */
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
	
	/**
	 * 该方法将{@code DAO}类处理完毕后返回，需要发回客户端的消息统一发回
	 * @param mes 发送回客户端的消息
	 * @author 吴慕陶
	 * @version 1.0
	 */
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
	
	/**
	 * 该方法调用后将关闭客户端线程，会使客户端与服务器断开连接
	 * @author 吴慕陶
	 * @version 1.0
	 */
	public void close() {
		this.isClosed = true;
		try {
			this.clientSocket.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("关闭客户端线程：" + this.id);
	}
	
	/**
	 * 获取该客户端线程ID
	 * @return 客户端线程ID
	 * @author 吴慕陶
	 * @version 1.0
	 */
	public String getCliThdID() {
		return this.id;
	}
	
	/**
	 * 获取该线程对应的客户端IP地址
	 * @return 该客户端线程对应的客户端IP地址
	 */
	public String getIP() {
		return this.clientSocket.getInetAddress().getHostAddress();
	}
}
