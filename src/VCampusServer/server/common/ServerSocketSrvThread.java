package virtualSchoolServer.server.bz;



import virtualSchoolClient.src.vsst.common.ClientReq;

import virtualSchoolServer.common.*;
import virtualSchoolServer.server.dao.BookDaoImp;
import virtualSchoolServer.server.dao.CourseDaoImp;
import virtualSchoolServer.server.dao.GoodsDaoImp;
import virtualSchoolServer.server.dao.UserDaoImpl;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;


import java.util.*;
/**
 *  服务器端消息处理线程
 */
public class ServerSocketSrvThread extends Thread {
	private Socket clientReqSocket;
	private boolean isClosed;

	/**
	 * 用于socket的传递
	 * @param s 从线程启动处获得的socket
	 * @throws IOException 抛出的输入输出错误
	 * @throws ClassNotFoundException 抛出的类未发现错误
	 */
	public ServerSocketSrvThread(Socket s) throws IOException, ClassNotFoundException {
		this.clientReqSocket = s;
		this.isClosed = false;
	}

	@Override
	/**
	 * 用于线程的服务获取和执行
	 */
	public void run() {

		while (!isClosed) {
			// 这里该线程就可以接收客户端的操作申请
			try {
				ObjectInputStream ois = new ObjectInputStream(clientReqSocket.getInputStream());
				ClientReq clientReq = (ClientReq) ois.readObject();
				String type = clientReq.getType();
				// 对从客户端取得的消息进行类型判断，让后做相应的处理
				if (type.equals(ClientReqType.REQ_SHOW_SIG_INFOR)) {
					System.out.println("serving REQ_SHOW_SIG_INFOR");
					System.out.println("grabbing.....");
					//返回单个学生学籍信息
					User user = new User();
					String studentID = clientReq.getContent().get(0);
					UserDaoImpl userDao=new UserDaoImpl();
					user = userDao.searchUser(studentID);
					//content的更新
					clientReq.setContent(user.getContent());
					// --发送至查询用户
					ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
					oos.writeObject(clientReq);//服务端写回
					oos.flush();
					System.out.println("REQ_SHOW_SIG_INFOR finished");
				} else if(type.equals(ClientReqType.REQ_ADD_SIG_INFOR)) {
					System.out.println("serving REQ_ADD_SIG_INFOR");
					System.out.println("adding...... ");
					//增加单个学生学籍信息
					User user = new User();
					user.setContent(clientReq.getContent());
					UserDaoImpl userDao=new UserDaoImpl();
					userDao.addUser(user);
					System.out.println("REQ_ADD_SIG_INFOR finished");
				} else if(type.equals(ClientReqType.REQ_REMOVE_SIG_INFOR)) {
					System.out.println("serving REQ_REMOVE_SIG_INFOR");
					System.out.println("removing....");
					//删除单个学生学籍信息
					String userID = clientReq.getContent().get(0);
					UserDaoImpl userDao=new UserDaoImpl();
					userDao.delUser(userID);
					System.out.println("REQ_REMOVE_SIG_INFOR finished");
				} else if(type.equals(ClientReqType.REQ_SHOW_ALL_INFOR)) {
					System.out.println("serving REQ_SHOW_ALL_INFOR");
					System.out.println("grabbing......");
					//返回全部学生信息，服务端接收Vector自行处理
					List<User> userList = new LinkedList<User>();
					UserDaoImpl userDao = new UserDaoImpl();
					userList = userDao.getAllUsers();
					Iterator<User> listIterator = userList.iterator();
					Vector<String> sigUserContent=new Vector<String>();
					Vector<String> allUserContent= new Vector<String>();
					while(listIterator.hasNext()){
						sigUserContent = listIterator.next().getContent();
						for(int i=0;i<=8;i++) {
							allUserContent.add(sigUserContent.get(i));
						}
					}
					clientReq.setContent(allUserContent);
					ObjectOutputStream oos=new ObjectOutputStream(clientReqSocket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					System.out.println("REQ_SHOW_ALL_INFOR finished");
				} else if(type.equals(ClientReqType.REQ_ADD_GOODS)) {
					System.out.println("serving REQ_ADD_GOODS");
					System.out.println("adding....");
					//管理员用于增加商品
					GoodsDaoImp goodsDao= new GoodsDaoImp();
					Goods goods=new Goods();
					goods.setContent(clientReq.getContent());
					goodsDao.addGoods(goods);
					System.out.println("REQ_ADD_GOODS finished");
				} else if(type.equals(ClientReqType.REQ_REMOVE_GOODS)) {
					//管理员用于移除商品
					System.out.println("serving REQ_REMOVE_GOODS");
					System.out.println("removing.....");
					String goodsName = clientReq.getContent().get(1);
					GoodsDaoImp goodsDao = new GoodsDaoImp();
					boolean result = goodsDao.removeGoods(goodsName);
					System.out.println("REQ_REMOVE_GOODS finished");
				} else if(type.equals(ClientReqType.REQ_BUY_GOODS)) {
					System.out.println("serving REQ_BUY_GOODS");
					System.out.println("buying.....");
					//大家用于购买商品
					String goodsName = clientReq.getContent().get(0);
					GoodsDaoImp goodsDao = new GoodsDaoImp();
					Goods goods = goodsDao.searchGoods(goodsName);
					if(goods==null) {
						clientReq.setType("NULL");
						clientReq.setContent(new Vector<String>());
					}
					else {
						String goodAmountString = clientReq.getContent().get(1);
						String buyerID = clientReq.getContent().get(2);
						int goodsAmount = Integer.valueOf(goodAmountString,10);
						goodsDao.buyGoods(goodsName, goodsAmount, buyerID);
					}
					ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					System.out.println("REQ_BUY_GOODS finished");
				} else if(type.equals(ClientReqType.REQ_SHOW_ALL_GOODS)) {
					System.out.println("serving REQ_SHOW_ALL_GOODS");
					System.out.println("grabbing");
					//返回全部的货物信息
					Vector<String> sigGoodsContent= new Vector<String>();
					Vector<String> allGoodsContent = new Vector<String>();
					List<Goods> allGoods = new LinkedList<Goods>();
					GoodsDaoImp goodsDao= new GoodsDaoImp();
					allGoods = goodsDao.getAllGoods();
					Iterator<Goods> iteAllGoods = allGoods.iterator();
					while(iteAllGoods.hasNext()) {
						sigGoodsContent = iteAllGoods.next().getContent();
						for(int i=0;i<=3;i++) {
							allGoodsContent.add(sigGoodsContent.get(i));
						}
					}
					clientReq.setContent(allGoodsContent);
					ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					System.out.println("REQ_SHOW_ALL_GOODS finished");
				}  else if(type.equals(ClientReqType.REQ_ADD_BOOK)) {
					System.out.println("serving REQ_ADD_BOOK");
					System.out.println("adding.....");
					BookDaoImp bookDao = new BookDaoImp();
					Book book = new Book();
					book.setContent(clientReq.getContent());
					bookDao.addBook(book);
					System.out.println("REQ_ADD_BOOK finished");
				} else if(type.equals(ClientReqType.REQ_REMOVE_BOOK)) {
					System.out.println("serving REQ_REMOVE_BOOK");
					System.out.println("removing.....");
					BookDaoImp bookDao = new BookDaoImp();
					String bookID=clientReq.getContent().get(0);
					bookDao.removeBook(bookID);
					System.out.println("REQ_REMOVE_BOOK finished");
				} else if(type.equals(ClientReqType.REQ_SHOW_ALL_BOOK)) {
					System.out.println("serving REQ_SHOW_ALL_BOOK");
					System.out.println("processing.....");
					BookDaoImp bookDao = new BookDaoImp();
					List<Book> allBook= new LinkedList<Book>();
					allBook = bookDao.getAllBook();
					Iterator<Book>iteBook = allBook.iterator();
					Vector<String>allBookInfor = new Vector<String>();
					Book tempBook = new Book();
					while(iteBook.hasNext()) {
						tempBook= iteBook.next();
						for(int i=0;i<4;i++) {
							allBookInfor.add(tempBook.getContent().get(i));
						}
					}
					clientReq.setContent(allBookInfor);
					ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					System.out.println("REQ_SHOW_ALL_BOOK finished");
				} else if(type.equals(ClientReqType.REQ_BORROW_BOOK)) {
					System.out.println("serving REQ_BORROW_BOOK");
					System.out.println("borrowing......");
					String bookID = clientReq.getContent().get(0);
					String userID = clientReq.getContent().get(1);
					BookDaoImp bookDao = new BookDaoImp();
					bookDao.borrowBook(bookID, userID);
					System.out.println("REQ_BORROW_BOOK finished");
				} else if(type.equals(ClientReqType.REQ_RETURN_BOOK)) {
					System.out.println("serving REQ_RETURN_BOOK");
					System.out.println("returning.....");
					String bookID = clientReq.getContent().get(0);
					String userID = clientReq.getContent().get(1);
					BookDaoImp bookDao = new BookDaoImp();
					bookDao.returnBook(bookID, userID);
					System.out.println("REQ_RETURN_BOOK finished");
				} else if(type.equals(ClientReqType.REQ_SHOW_SIG_BOOK)) {
					System.out.println("serving REQ_SHOW_SIG_BOOK");
					System.out.println("grabbing....");
					BookDaoImp bookDao = new BookDaoImp();
					String bookID = clientReq.getContent().get(0);
					Book book = bookDao.searchBookByID(bookID);
					if(book!=null) {
						clientReq.setContent(book.getContent());
						ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
						oos.writeObject(clientReq);
						oos.flush();
					} else {
						clientReq.setContent(new Vector<String>());
						clientReq.setType("NULL");
						ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
						oos.writeObject(clientReq);
						oos.flush();
					}
					System.out.println("REQ_SHOW_SIG_BOOK finished");
				} else if (type.equals(ClientReqType.REQ_STU_ADD_LESSON)) {
					System.out.println("serving REQ_STU_ADD_LESSON");
					System.out.println("adding....");
					//学生选课
					CourseDaoImp courseDao= new CourseDaoImp();
					String CourseId = clientReq.getContent().get(0);
					String UserId = clientReq.getContent().get(1);
					boolean result = courseDao.sigAddCourse(CourseId,UserId);
					System.out.println("REQ_STU_ADD_LESSON finished");
				} else if (type.equals(ClientReqType.REQ_STU_REMOVE_LESSON)) {
					System.out.println("serving REQ_STU_REMOVE_LESSON");
					System.out.println("removing....");
					//学生退课
					CourseDaoImp courseDao= new CourseDaoImp();
					String CourseId = clientReq.getContent().get(0);
					String UserId = clientReq.getContent().get(1);
					courseDao.sigRemoveCourse(CourseId,UserId);
					System.out.println("REQ_STU_REMOVE_LESSON finshed");
				} else if (type.equals(ClientReqType.REQ_ADD_LESSON)) {
					System.out.println("serving REQ_ADD_LESSON");
					System.out.println("adding....");
					//老师添加课程
					CourseDaoImp courseDao= new CourseDaoImp();
					Course course=new Course();
					course.setContent(clientReq.getContent());
					courseDao.genAddCourse(course);
					System.out.println("REQ_ADD_LESSON finished");
				} else if (type.equals(ClientReqType.REQ_REMOVE_LESSON)) {
					//老师删除课程
					System.out.println("serving REQ_REMOVE_LESSON");
					System.out.println("removing.....");
					String courseName = clientReq.getContent().get(1);
					CourseDaoImp courseDao = new CourseDaoImp();
					boolean result = courseDao.genRemoveCourse(courseName);
					System.out.println("REQ_REMOVE_LESSON finished");
				} else if (type.equals(ClientReqType.REQ_SEARCH_LESSON)) {
					System.out.println("serving REQ_SEARCH_LESSON");
					System.out.println("searching.....");
					//返回单个课程信息
					Course course = new Course();
					String courseID = clientReq.getContent().get(0);
					CourseDaoImp courseDao=new CourseDaoImp();
					course = courseDao.searchCourseByID(courseID);
					//course.print();
					//content的更新
					clientReq.setContent(course.getContent());
					// --发送至查询用户
					ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
					oos.writeObject(clientReq);//服务端写回
					oos.flush();
					System.out.println("REQ_SEARCH_LESSON finished");
				} else if (type.equals(ClientReqType.REQ_SHOW_ALL_LESSON)) {
					System.out.println("serving REQ_SHOW_ALL_LESSON");
					System.out.println("grabbing.....");
					//返回所有课程
					Vector<String> sigCourseContent= new Vector<String>();
					Vector<String> allCourseContent = new Vector<String>();
					List<Course> allCourse = new LinkedList<Course>();
					CourseDaoImp courseDao= new CourseDaoImp();
					allCourse = courseDao.getAllCourse();
					Iterator<Course> iteAllCourse = allCourse.iterator();
					while(iteAllCourse.hasNext()) {
						sigCourseContent = iteAllCourse.next().getContent();
						for(int i=0;i<=4;i++) {
							allCourseContent.add(sigCourseContent.get(i));
						}
					}
					clientReq.setContent(allCourseContent);
					ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					System.out.println("REQ_SHOW_ALL_LESSON finished");
				} else if (type.equals(ClientReqType.REQ_STU_ALL_CHOOOSE)) {
					System.out.println("serving REQ_STU_ALL_CHOOOSE");
					System.out.println("grabbing......");
					//返回所有该学生已选课程
					Vector<String> sigCourseContent= new Vector<String>();
					Vector<String> allCourseContent = new Vector<String>();
					List<Course> allCourse = new LinkedList<Course>();
					User user = new User();
					user.setContent(clientReq.getContent());
					allCourse = user.getCourses();
					Iterator<Course> iteAllCourse = allCourse.iterator();
					while(iteAllCourse.hasNext()) {
						sigCourseContent = iteAllCourse.next().getContent();
						for(int i=0;i<=4;i++) {
							allCourseContent.add(sigCourseContent.get(i));
						}
					}
					clientReq.setContent(allCourseContent);
					ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					System.out.println("REQ_STU_ALL_CHOOOSE finished");
				} else if (type.equals(ClientReqType.REQ_STU_ALL_BORROW)) {
					System.out.println("serving REQ_STU_ALL_BORROW");
					System.out.println("grabbing......");
					//返回所有该学生已借的书
					Vector<String> sigBookContent= new Vector<String>();
					Vector<String> allBookContent = new Vector<String>();
					List<Book> allBook = new LinkedList<Book>();
					User user = new User();
					user.setContent(clientReq.getContent());
					allBook = user.getBooks();
					Iterator<Book> iteAllBook = allBook.iterator();
					while(iteAllBook.hasNext()) {
						sigBookContent = iteAllBook.next().getContent();
						for(int i=0;i<=3;i++) {
							allBookContent.add(sigBookContent.get(i));
						}
					}
					clientReq.setContent(allBookContent);
					ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					System.out.println("REQ_STU_ALL_BORROW finished");
				} else if(type.equals(ClientReqType.REQ_SHOW_SIG_GOODS)) {
					System.out.println("serving REQ_SHOW_SIG_GOODS");
					System.out.println("grabbing......");
					Vector<String> sigGoodsInfor = new Vector<String>();
					String goodsName = clientReq.getContent().get(0);
					GoodsDaoImp goodsDao = new GoodsDaoImp();
					Goods goods = goodsDao.searchGoods(goodsName);
					if(goods!=null) {
						clientReq.setContent(goods.getContent());
						ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
						oos.writeObject(clientReq);
						oos.flush();
					} else {
						clientReq.setContent(new Vector<String>());
						clientReq.setType("NULL");
						ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
						oos.writeObject(clientReq);
						oos.flush();
					}
					System.out.println("REQ_SHOW_SIG_GOODS finished");
				} else if(type.equals(ClientReqType.REQ_LOGOUT)) {
					System.out.println("serving REQ_LOGOUT");
					isClosed = true;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				isClosed = true;
			}
		}
	}

	/**
	 * 用于关闭线程，不进行后续的服务获取
	 */
	public void close() {
		isClosed = true;
	}

}
