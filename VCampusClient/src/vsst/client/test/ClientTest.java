package virtualSchoolClient.src.vsst.client.test;

import java.net.Socket;


public class ClientTest {

	public static void main(String[] args) {	
		try{
			Socket clientSendSocket=new Socket("127.0.0.1",8888);//���ö˿�
			/**
			 * �˴�Ϊ���̲߳���
			 */
			//�鿴ѧ��ѧ����Ϣ�Ĳ���
			/*
			//��ʼ��
			ClientReq clientReq = new ClientReq();//�½��������ڽ���
			clientReq.setType("REQ_SHOW_SIG_INFOR");//������õ���ѧ��ѧ��
			Vector<String> contents = new Vector<String>();//����ID�洢
			contents.add("15232");
			clientReq.setContent(contents);//������Ϣ�������
			//ͨ��
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());//�����socketͨ��OutputStream�����Server��
			oos.writeObject(clientReq);//д����������ȥ�����л���
			oos.flush();//�ϴ��ȴ�����

			ObjectInputStream ois = new ObjectInputStream(clientSendSocket.getInputStream());//�����socketͨ��InputStreamд��client��
			clientReq = (ClientReq) ois.readObject();//������ȥ���л���
			for(int i=0;i<=8;i++) {
				System.out.println(clientReq.getContent().get(i));
			}
			*/
			
			//���ѧ����Ϣ����
			/*
			//��ʼ��
			User user=new User();
			user.setId("213532");
			user.setAge("422");
			user.setGrade("����");
			user.setMajor("�ź�");
			user.setMoney("4332");
			user.setName("�滨");
			user.setPwd("h3723432"); 
			user.setRole("��ʦ");
			user.setSex("Ů");
			ClientReq clientReq = new ClientReq();//�½��������ڽ���
			clientReq.setType("REQ_ADD_SIG_INFOR");//�������ӵ���ѧ��ѧ��
			clientReq.setContent(user.getContent());

			//ͨ��
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());//�����socketͨ��OutputStream�����Server��
			oos.writeObject(clientReq);//д����������ȥ�����л���
			oos.flush();//�ϴ��ȴ�����
			*/
			
			//ɾ��ѧ����Ϣ����
			/*
			ClientReq clientReq = new ClientReq();//�½��������ڽ���
			clientReq.setType("REQ_REMOVE_SIG_INFOR");//����ɾ������ѧ��ѧ��
			Vector<String> contents = new Vector<String>();//����ID�洢
			contents.add("532412");
			clientReq.setContent(contents);//������Ϣ�������
			//ͨ��
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());//�����socketͨ��OutputStream�����Server��
			oos.writeObject(clientReq);//д����������ȥ�����л���
			oos.flush();//�ϴ��ȴ�����
			*/
			
			//�г�ȫ��ѧ������
			/*
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_SHOW_ALL_INFOR");
			ObjectOutputStream oos= new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			//���ܷ���������
			ObjectInputStream ois=new ObjectInputStream(clientSendSocket.getInputStream());
			clientReq = (ClientReq)ois.readObject();
			Vector<String> returnedContent = clientReq.getContent();
			System.out.println("size: " + returnedContent.size() + "\n");
			for(int i = 0;i<returnedContent.size();i++) {
				System.out.println(returnedContent.get(i));
			}
			*/
			
			//�鿴ȫ����Ʒ����
			/*
			//��ʼ��
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_SHOW_ALL_GOODS");
			//������
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			//��������
			ObjectInputStream ois = new ObjectInputStream(clientSendSocket.getInputStream());
			clientReq = (ClientReq) ois.readObject();
			Vector<String> allGoodsContents = clientReq.getContent();
			for(int i=0;i<allGoodsContents.size();i++) {
				System.out.println(clientReq.getContent().get(i));
			}
			*/
			
			//�����Ʒ����
			/*
			Goods goods = new Goods();
			goods.setGoodsID("3321");
			goods.setGoodsName("����");
			goods.setGoodsAmount("32");
			goods.setGoodsPrice("42322");
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_ADD_GOODS");
			clientReq.setContent(goods.getContent());
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			*/
			
			//ɾ����Ʒ����
			/*
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_REMOVE_GOODS");
			Vector<String> reqContent = new Vector<String>();
			reqContent.setSize(4);
			reqContent.set(1,"�ɻ�");
			clientReq.setContent(reqContent);
			
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			*/
			
			//������Ʒ����
			/*
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_BUY_GOODS");
			Vector<String> reqContent=new Vector<String>();
			reqContent.add("ɨ��");
			reqContent.add("10");
			reqContent.add("2");
			clientReq.setContent(reqContent);
			
			ObjectOutputStream oos1 = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos1.writeObject(clientReq);
			oos1.flush();
			*/
			
			//�����鱾����
			/*
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_ADD_BOOK");
			Book book = new Book();
			book.setBookID("23222");
			book.setBookName("����");
			book.setBookAmount("372");
			book.setLeft("8833");
			clientReq.setContent(book.getContent());
			ObjectOutputStream oos1=new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos1.writeObject(clientReq);
			oos1.flush();
			*/
			
			//ɾ���鱾����
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
			
			//����ȫ���鱾����
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
			
			//�������
			/*
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_BORROW_BOOK");
			Vector<String> content = new Vector<String>();
			content.add("38274");//��ID
			content.add("213193524");//��ID
			clientReq.setContent(content);
			
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			*/
			
			//�������
			/*
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_RETURN_BOOK");
			Vector<String> content = new Vector<String>();
			content.add("38274");//��ID
			content.add("213193524");//��ID
			clientReq.setContent(content);
			
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			*/
			
			//�鿴�����γ̲���
			/*
			ClientReq clientReq = new ClientReq();//�½��������ڽ���
			clientReq.setType("REQ_SEARCH_LESSON");//���ҿγ�
			Vector<String> reqContent = new Vector<String>();
			Course c = new Course();
			c.setCourseID("1");
			reqContent = c.getContent();
			clientReq.setContent(reqContent);//������Ϣ�������
			//ͨ��
			ObjectOutputStream oos5 = new ObjectOutputStream(clientSendSocket.getOutputStream());//�����socketͨ��OutputStream�����Server��
			oos5.writeObject(clientReq);//д����������ȥ�����л���
			oos5.flush();//�ϴ��ȴ�����

			ObjectInputStream ois5 = new ObjectInputStream(clientSendSocket.getInputStream());//�����socketͨ��InputStreamд��client��
			clientReq = (ClientReq) ois5.readObject();//������ȥ���л���
			for(int i=0;i<5;i++) {
				System.out.println(clientReq.getContent().get(i));
			}
			*/
			
			//�鿴ȫ���γ�
			/*
			ClientReq clientReq = new ClientReq();//�½��������ڽ���
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
			
			//��ʦ���ӿγ�
			/*
			Course c = new Course();
			c.setCourseID("36263");
			c.setCourseName("����");
			c.setCourseHours("43");
			c.setCMaxnum("6555");
			c.setCNownum("342");
			ClientReq clientReq = new ClientReq();//�½��������ڽ���
			clientReq.setType("REQ_ADD_LESSON");
			clientReq.setContent(c.getContent());
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			*/
			
			//��ʦɾ���γ�
			/*
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_REMOVE_LESSON");
			Vector<String> reqContent = new Vector<String>();
			reqContent.setSize(5);
			reqContent.set(1,"��������ԭ��");
			clientReq.setContent(reqContent);
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			*/
			
			//ѧ��ѡ��
			/*
			String Cid = "2";
			String Uid = "2";
			ClientReq clientReq = new ClientReq();//�½��������ڽ���
			Vector<String> reqContent = new Vector<String>();
			reqContent.add(Uid);
			reqContent.add(Cid);
			clientReq.setContent(reqContent);
			clientReq.setType("REQ_STU_ADD_LESSON");
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			*/
			
			//ѧ���˿�
			/*
			String Cid = "2";
			String Uid = "2";
			ClientReq clientReq = new ClientReq();//�½��������ڽ���
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
			 * �˴�Ϊ���̲߳���
			 */
			
			//��Ϣע��
			/*
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_REGISTER");//����ע��
			User user=new User();
			user.setId("4341");
			user.setAge("422");
			user.setGrade("��һ");
			user.setMajor("��ѧ");
			user.setMoney("3424332");
			user.setName("�üһ�");
			user.setPwd("vfvdw2r2fe"); 
			user.setRole("����Ա");
			user.setSex("��");
			Vector<String> content = user.getContent();
			clientReq.setContent(content);
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos.writeObject(clientReq);
			oos.flush();
			
			ObjectInputStream ois = new ObjectInputStream(clientSendSocket.getInputStream());//�������᷵��һ��ע���Ƿ�ɹ������ݣ����ɹ���Type��ԭ���Ĳ�һ��
			clientReq  = (ClientReq) ois.readObject();
			*/
			
			//��¼����
			/*
			ClientReq clientReq = new ClientReq();//�½��������ڽ���
			clientReq.setType("REQ_LOGIN");//�����¼
			Vector<String> contents = new Vector<String>();//����ID�洢
			User user=new User();
			user.setId("4341");
			user.setPwd("vfvdw2r2fe"); 
			contents = user.getContent();
			clientReq.setContent(contents);//������Ϣ�������
			//ͨ��
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());//�����socketͨ��OutputStream�����Server��
			oos.writeObject(clientReq);//д����������ȥ�����л���
			oos.flush();//�ϴ��ȴ�����
			
			ObjectInputStream ois = new ObjectInputStream(clientSendSocket.getInputStream());//�������᷵��һ��ע���Ƿ�ɹ������ݣ����ɹ���Type��ԭ���Ĳ�һ��
			clientReq  = (ClientReq) ois.readObject();
			System.out.println(clientReq.getType());
			*/
			
			//�ǳ�����
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
			 * �˴�Ϊ�ۺϲ���
			 */
			
			/*
			//��¼��֤
			ClientReq clientReq = new ClientReq();//�½��������ڽ���
			clientReq.setType("REQ_LOGIN");//�����¼
			Vector<String> contents = new Vector<String>();//����ID�洢
			User user=new User();
			user.setId("2");
			user.setPwd("2"); 
			contents = user.getContent();
			clientReq.setContent(contents);//������Ϣ�������
			ObjectOutputStream oos = new ObjectOutputStream(clientSendSocket.getOutputStream());//�����socketͨ��OutputStream�����Server��
			oos.writeObject(clientReq);//д����������ȥ�����л���
			oos.flush();//�ϴ��ȴ�����
			ObjectInputStream ois = new ObjectInputStream(clientSendSocket.getInputStream());
			clientReq = (ClientReq) ois.readObject();
			
			
			//�ǳ�
			clientReq.setType("REQ_LOGOUT");
			User user1 = new User();
			user1.setId("2");
			clientReq.setContent(user1.getContent());
			ObjectOutputStream oos111 = new ObjectOutputStream(clientSendSocket.getOutputStream());
			oos111.writeObject(clientReq);
			oos111.flush();
			*/
			clientSendSocket.close();//�ر�,���̲߳���ʹ��
		}
		catch(Exception e) {
			e.printStackTrace();//�����е��쳣�������
		}
	}

}
