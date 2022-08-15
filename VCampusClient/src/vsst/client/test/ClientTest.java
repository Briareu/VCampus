package virtualSchoolClient.src.vsst.client.test;

import java.net.Socket;


public class ClientTest {

	public static void main(String[] args) {	
		try{
			Socket clientSendSocket=new Socket("127.0.0.1",8888);//设置端口
			/**
			 * 此处为单线程测试
			 */
			//查看学生学籍信息的操作
			/*
			//初始化
			ClientReq clientReq = new ClientReq();//新建申请用于交换
			clientReq.setType("REQ_SHOW_SIG_INFOR");//申请调用单个学生学籍
			Vector<String> contents = new Vector<String>();//调用ID存储
			contents.add("15232");
			clientReq.setContent(contents);//调用信息存进申请
			//通信
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());//把这个socket通过OutputStream输出给Server端
			oos.writeObject(clientReq);//写进申请里面去（序列化）
			oos.flush();//上传等待处理

			ObjectInputStream ois = new ObjectInputStream(clientSendSocket.getInputStream());//把这个socket通过InputStream写回client端
			clientReq = (ClientReq) ois.readObject();//读出（去序列化）
			for(int i=0;i<=8;i++) {
				System.out.println(clientReq.getContent().get(i));
			}
			*/
			
			//添加学籍信息操作
			/*
			//初始化
			User user=new User();
			user.setId("213532");
			user.setAge("422");
			user.setGrade("大三");
			user.setMajor("信号");
			user.setMoney("4332");
			user.setName("梨花");
			user.setPwd("h3723432"); 
			user.setRole("老师");
			user.setSex("女");
			ClientReq clientReq = new ClientReq();//新建申请用于交换
			clientReq.setType("REQ_ADD_SIG_INFOR");//申请增加单个学生学籍
			clientReq.setContent(user.getContent());

			//通信
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());//把这个socket通过OutputStream输出给Server端
			oos.writeObject(clientReq);//写进申请里面去（序列化）
			oos.flush();//上传等待处理
			*/
			
			//删除学籍信息测试
			/*
			ClientReq clientReq = new ClientReq();//新建申请用于交换
			clientReq.setType("REQ_REMOVE_SIG_INFOR");//申请删除单个学生学籍
			Vector<String> contents = new Vector<String>();//调用ID存储
			contents.add("532412");
			clientReq.setContent(contents);//调用信息存进申请
			//通信
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());//把这个socket通过OutputStream输出给Server端
			oos.writeObject(clientReq);//写进申请里面去（序列化）
			oos.flush();//上传等待处理
			*/
			
			//列出全部学籍测试
			/*
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_SHOW_ALL_INFOR");
			ObjectOutputStream oos= new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			//接受服务器数据
			ObjectInputStream ois=new ObjectInputStream(clientSendSocket.getInputStream());
			clientReq = (ClientReq)ois.readObject();
			Vector<String> returnedContent = clientReq.getContent();
			System.out.println("size: " + returnedContent.size() + "\n");
			for(int i = 0;i<returnedContent.size();i++) {
				System.out.println(returnedContent.get(i));
			}
			*/
			
			//查看全部商品操作
			/*
			//初始化
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_SHOW_ALL_GOODS");
			//传数据
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			//接受数据
			ObjectInputStream ois = new ObjectInputStream(clientSendSocket.getInputStream());
			clientReq = (ClientReq) ois.readObject();
			Vector<String> allGoodsContents = clientReq.getContent();
			for(int i=0;i<allGoodsContents.size();i++) {
				System.out.println(clientReq.getContent().get(i));
			}
			*/
			
			//添加商品操作
			/*
			Goods goods = new Goods();
			goods.setGoodsID("3321");
			goods.setGoodsName("杯杯");
			goods.setGoodsAmount("32");
			goods.setGoodsPrice("42322");
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_ADD_GOODS");
			clientReq.setContent(goods.getContent());
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			*/
			
			//删除商品操作
			/*
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_REMOVE_GOODS");
			Vector<String> reqContent = new Vector<String>();
			reqContent.setSize(4);
			reqContent.set(1,"飞机");
			clientReq.setContent(reqContent);
			
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			*/
			
			//购买商品测试
			/*
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_BUY_GOODS");
			Vector<String> reqContent=new Vector<String>();
			reqContent.add("扫把");
			reqContent.add("10");
			reqContent.add("2");
			clientReq.setContent(reqContent);
			
			ObjectOutputStream oos1 = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos1.writeObject(clientReq);
			oos1.flush();
			*/
			
			//增加书本测试
			/*
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_ADD_BOOK");
			Book book = new Book();
			book.setBookID("23222");
			book.setBookName("得行");
			book.setBookAmount("372");
			book.setLeft("8833");
			clientReq.setContent(book.getContent());
			ObjectOutputStream oos1=new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos1.writeObject(clientReq);
			oos1.flush();
			*/
			
			//删除书本测试
			/*
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_REMOVE_BOOK");
			Vector<String> bookID = new Vector<String>();
			bookID.add("1");
			clientReq.setContent(bookID);
			
			ObjectOutputStream oos =  new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			*/
			
			//呈现全部书本测试
			/*
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_SHOW_ALL_BOOK");
			
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			
			ObjectInputStream ois = new ObjectInputStream(clientSendSocket.getInputStream());
			clientReq = (ClientReq) ois.readObject();
			
			for(int i=0;i<clientReq.getContent().size();i++) {
				System.out.println(clientReq.getContent().get(i));
			}
			*/
			
			//借书测试
			/*
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_BORROW_BOOK");
			Vector<String> content = new Vector<String>();
			content.add("38274");//书ID
			content.add("213193524");//人ID
			clientReq.setContent(content);
			
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			*/
			
			//还书测试
			/*
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_RETURN_BOOK");
			Vector<String> content = new Vector<String>();
			content.add("38274");//书ID
			content.add("213193524");//人ID
			clientReq.setContent(content);
			
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			*/
			
			//查看单个课程操作
			/*
			ClientReq clientReq = new ClientReq();//新建申请用于交换
			clientReq.setType("REQ_SEARCH_LESSON");//查找课程
			Vector<String> reqContent = new Vector<String>();
			Course c = new Course();
			c.setCourseID("1");
			reqContent = c.getContent();
			clientReq.setContent(reqContent);//调用信息存进申请
			//通信
			ObjectOutputStream oos5 = new ObjectOutputStream(clientSendSocket.getOutputStream());//把这个socket通过OutputStream输出给Server端
			oos5.writeObject(clientReq);//写进申请里面去（序列化）
			oos5.flush();//上传等待处理

			ObjectInputStream ois5 = new ObjectInputStream(clientSendSocket.getInputStream());//把这个socket通过InputStream写回client端
			clientReq = (ClientReq) ois5.readObject();//读出（去序列化）
			for(int i=0;i<5;i++) {
				System.out.println(clientReq.getContent().get(i));
			}
			*/
			
			//查看全部课程
			/*
			ClientReq clientReq = new ClientReq();//新建申请用于交换
			clientReq.setType("REQ_SHOW_ALL_LESSON");
			ObjectOutputStream oos2= new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos2.writeObject(clientReq);
			oos2.flush();
			ObjectInputStream ois2=new ObjectInputStream(clientSendSocket.getInputStream());
			clientReq = (ClientReq)ois2.readObject();
			Vector<String> returnedContent = clientReq.getContent();
			System.out.println("size: " + returnedContent.size() + "\n");
			for(int i = 0;i<returnedContent.size();i++) {
				System.out.println(returnedContent.get(i));
			}
			*/
			
			//老师增加课程
			/*
			Course c = new Course();
			c.setCourseID("36263");
			c.setCourseName("大物");
			c.setCourseHours("43");
			c.setCMaxnum("6555");
			c.setCNownum("342");
			ClientReq clientReq = new ClientReq();//新建申请用于交换
			clientReq.setType("REQ_ADD_LESSON");
			clientReq.setContent(c.getContent());
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			*/
			
			//老师删除课程
			/*
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_REMOVE_LESSON");
			Vector<String> reqContent = new Vector<String>();
			reqContent.setSize(5);
			reqContent.set(1,"计算机组成原理");
			clientReq.setContent(reqContent);
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			*/
			
			//学生选课
			/*
			String Cid = "2";
			String Uid = "2";
			ClientReq clientReq = new ClientReq();//新建申请用于交换
			Vector<String> reqContent = new Vector<String>();
			reqContent.add(Uid);
			reqContent.add(Cid);
			clientReq.setContent(reqContent);
			clientReq.setType("REQ_STU_ADD_LESSON");
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			*/
			
			//学生退课
			/*
			String Cid = "2";
			String Uid = "2";
			ClientReq clientReq = new ClientReq();//新建申请用于交换
			Vector<String> reqContent = new Vector<String>();
			reqContent.add(Uid);
			reqContent.add(Cid);
			clientReq.setContent(reqContent);
			clientReq.setType("REQ_STU_REMOVE_LESSON");
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			*/
			
			/**
			 * 此处为多线程测试
			 */
			
			//信息注册
			/*
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_REGISTER");//申请注册
			User user=new User();
			user.setId("4341");
			user.setAge("422");
			user.setGrade("大一");
			user.setMajor("数学");
			user.setMoney("3424332");
			user.setName("好家伙");
			user.setPwd("vfvdw2r2fe"); 
			user.setRole("管理员");
			user.setSex("男");
			Vector<String> content = user.getContent();
			clientReq.setContent(content);
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			
			ObjectInputStream ois = new ObjectInputStream(clientSendSocket.getInputStream());//服务器会返回一个注册是否成功的内容，不成功则Type和原来的不一样
			clientReq  = (ClientReq) ois.readObject();
			*/
			
			//登录测试
			/*
			ClientReq clientReq = new ClientReq();//新建申请用于交换
			clientReq.setType("REQ_LOGIN");//申请登录
			Vector<String> contents = new Vector<String>();//调用ID存储
			User user=new User();
			user.setId("4341");
			user.setPwd("vfvdw2r2fe"); 
			contents = user.getContent();
			clientReq.setContent(contents);//调用信息存进申请
			//通信
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());//把这个socket通过OutputStream输出给Server端
			oos.writeObject(clientReq);//写进申请里面去（序列化）
			oos.flush();//上传等待处理
			
			ObjectInputStream ois = new ObjectInputStream(clientSendSocket.getInputStream());//服务器会返回一个注册是否成功的内容，不成功则Type和原来的不一样
			clientReq  = (ClientReq) ois.readObject();
			System.out.println(clientReq.getType());
			*/
			
			//登出测试
			/*
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_LOGOUT");
			User user = new User();
			user.setId("4341");
			clientReq.setContent(user.getContent());
			
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			*/
			
			//clientSendSocket.close();
			
			/**
			 * 此处为综合测试
			 */
			
			/*
			//登录验证
			ClientReq clientReq = new ClientReq();//新建申请用于交换
			clientReq.setType("REQ_LOGIN");//申请登录
			Vector<String> contents = new Vector<String>();//调用ID存储
			User user=new User();
			user.setId("2");
			user.setPwd("2"); 
			contents = user.getContent();
			clientReq.setContent(contents);//调用信息存进申请
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());//把这个socket通过OutputStream输出给Server端
			oos.writeObject(clientReq);//写进申请里面去（序列化）
			oos.flush();//上传等待处理
			ObjectInputStream ois = new ObjectInputStream(clientSendSocket.getInputStream());
			clientReq = (ClientReq) ois.readObject();
			
			
			//登出
			clientReq.setType("REQ_LOGOUT");
			User user1 = new User();
			user1.setId("2");
			clientReq.setContent(user1.getContent());
			ObjectOutputStream oos111 = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos111.writeObject(clientReq);
			oos111.flush();
			*/
			clientSendSocket.close();//关闭,单线程测试使用
		}
		catch(Exception e) {
			e.printStackTrace();//必须有的异常处理语句
		}
	}

}
