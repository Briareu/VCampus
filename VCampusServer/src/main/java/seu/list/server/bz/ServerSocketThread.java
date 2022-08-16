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
package seu.list.server.bz;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.Student;
import seu.list.server.dao.ClassAdminServer;

public class ServerSocketThread implements Runnable {
	private Socket clientSocket;
	private ClassAdminServer classAdminServer = new ClassAdminServer();
	// your access-class and server-class

	ServerSocketThread(Socket socket) {
		this.clientSocket = socket;
	}

	@Override
	public synchronized void run() {
		
		try {
			//start try
			ObjectInputStream message = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
			Message object = (Message)message.readObject();
			System.out.println("已与客户端建立连接，当前客户端ip为："+clientSocket.getInetAddress().getHostAddress());
			System.out.println(object.getMessageType());
			Message serverResponse = new Message();
			
			//thread start run
			switch(object.getMessageType())
			{//switch start
			
//用户登入登出---------------------------------------
			
			
//主窗口--------------------------------------------
			
			
//学生学籍管理---------------------------------------
			
			//1.获取所有的学生信息
			case MessageType.ClassAdminGetAll:
			{
				try {
					Vector<Student> stu = new Vector<Student>();
					stu = classAdminServer.getall();
					serverResponse.setData(stu);
					serverResponse.setMessageType(MessageType.operFeedback);
					serverResponse.setLastOperState(true);
					
				}finally {
					ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
					response.writeObject(serverResponse);
				}
			}
			break;//end of 1st function
			
			//2.删除某一行学生信息
			case MessageType.ClassAdminDelete:
			{
				try {
					String stu = null;
					serverResponse.setMessageType(MessageType.operFeedback);
					stu = object.getExtraMessage();//get id
					int res = classAdminServer.delete(stu);
					serverResponse.setData(res);
					serverResponse.setLastOperState(true);
				}finally {
					ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
					response.writeObject(serverResponse);
				}
			}
			break;//end of 1st function
			
//选课-----------------------------------------------
			
			
//图书馆---------------------------------------------
			//1.获取所有的书籍信息
			case MessageType.LibraryBookGetAll:
			{
				try {
					serverResponse.setMessageType(MessageType.operFeedback);
					ArrayList<Book> bookList;
					bookList = LibraryUserServer.createList();
					serverResponse.setData(bookList);
					serverResponse.setMessageType(MessageType.operFeedback);
					serverResponse.setLastOperState(true);
					
				}finally {
					ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
					response.writeObject(serverResponse);
				}
			}
			break;	
			
			//2.修改某一行书籍信息
			case MessageType.ClassAdminUpdate:
			{
				try {
					ArrayList<String> para = new ArrayList<String>();
					serverResponse.setMessageType(MessageType.operFeedback);
					para = (ArrayList<String>) object.getData();
					LibraryUserServer.ModifyBook(para.get(0),para.get(1),para.get(2));
					serverResponse.setLastOperState(true);
				}finally {
					ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
					response.writeObject(serverResponse);
				}
			}
			break;
			
			//3.借书
			case MessageType.LibraryBookLend:
			{
				try {
					String bookid = null;
					serverResponse.setMessageType(MessageType.operFeedback);
					bookid = object.getExtraMessage();//get id
					LibraryUserServer.LendBook(bookid);
					serverResponse.setLastOperState(true);
				}finally {
					ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
					response.writeObject(serverResponse);
				}
			}
			break;	
			
			//4.还书
			case MessageType.LibraryBookReturn:
			{
				try {
					String bookid = null;
					serverResponse.setMessageType(MessageType.operFeedback);
					bookid = object.getExtraMessage();//get id
					LibraryUserServer.ReturnBook(bookid);

					serverResponse.setLastOperState(true);
				}finally {
					ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
					response.writeObject(serverResponse);
				}
			}
			break;	
			
			//5.增加书籍
			case MessageType.LibraryBookAdd:
			{
				try {
					Book book;
					serverResponse.setMessageType(MessageType.operFeedback);
					book = (Book) object.getData();//get id
					LibraryUserServer.AddBook(book);
					serverResponse.setLastOperState(true);
				}finally {
					ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
					response.writeObject(serverResponse);
				}
			}
			break;
			
			//6.删除书籍
			case MessageType.LibraryBookDelete：
			{
				try {
					String bookid = null;
					serverResponse.setMessageType(MessageType.operFeedback);
					bookid = object.getExtraMessage();//get id
					LibraryUserServer.DeleteBook(bookid);
					serverResponse.setLastOperState(true);
				}finally {
					ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
					response.writeObject(serverResponse);
				}
			}
			break;
			
			//7.寻找书籍
			case MessageType.LibraryBookFind：
			{
				try {
					String bookid = null;
					ArrayList<Book> booklist=new ArrayList<Book>();
					serverResponse.setMessageType(MessageType.operFeedback);
					bookid = object.getExtraMessage();//get Bookid
					booklist=LibraryUserServer.FindBook(bookid);
					serverResponse.setData(booklist);
					serverResponse.setLastOperState(true);
				}finally {
					ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
					response.writeObject(serverResponse);
				}
			}
			break;	
			
//商店----------------------------------------------
			
			
//宿舍----------------------------------------------
			}//end switch
		}//end first try
		catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}