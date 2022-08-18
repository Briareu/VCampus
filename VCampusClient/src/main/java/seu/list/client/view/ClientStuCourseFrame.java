//package VCampusClient.src.main.java.seu.list.client.view;
package seu.list.client.view;
//学生选课界面




import VCampusClient.src.main.java.seu.list.client.bz.Client;
import VCampusClient.src.main.java.seu.list.common.*;


import seu.list.client.bz.ClientMainFrame;
/*
import VCampusClient.src.main.java.seu.list.common.*;
import main.java.seu.list.common.Course;
*/
import seu.list.common.*;
import seu.list.client.bz.*;


import java.awt.*;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ClientStuCourseFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	final int WIDTH=1200;
	final int HEIGHT=800;
	JFrame jframe=new JFrame();
	JScrollPane jsp;
    JPanel jp1,jp2,jp3,jp4,jp5;
	JPanel jbackground;
	JButton jb1,jb2,jb3,jb4;
	JLabel jl1,jl2,jl3,jl4;
	JTextField jtf1,jtf2;
	JLabel jtitle;
	JTable jtb1;
	JScrollPane scrollPane;
	Socket socket;//传送数据
	//CouTableModel cm;
	private String userID;


	Font f1=new Font("华文行楷",Font.PLAIN,18);

	public ClientStuCourseFrame(String number, Socket socket) throws ClassNotFoundException, SQLException,IOException, ClassNotFoundException
	{
		Tools.setWindowspos(WIDTH,HEIGHT,jframe);
		this.socket=socket;
		Client client =new Client(socket);
		userID=number;
		jframe.setTitle("选课管理系统");
		jbackground=new JPanel();
		jbackground.setBackground(new Color(66,99,116));
		jbackground.setLayout(null);
		jbackground.setBounds(0,0,WIDTH,HEIGHT);
		jframe.add(jbackground);

		jp1=new JPanel();
		jp1.setBackground(new Color(255,251,240));
		jp1.setBounds(0,0,WIDTH,HEIGHT-700);
		jbackground.add(jp1);

		jb1=new JButton("选择");
		jb1.setFont(f1);
		jb1.setBounds(10, 300, 70, 30);

		jl1=new JLabel("课程号:");
		jl1.setFont(f1);

		jb2=new JButton("已选课程查询");
		jb2.setFont(f1);

		jb3=new JButton("课程查询");
		jb3.setFont(f1);

		jtf1=new JTextField(10);

		jtf2=new JTextField(10);


		jb4=new JButton("退课");
		jb4.setFont(f1);

		jtitle=new JLabel("选课系统");
		jtitle.setFont(new Font("宋体",Font.BOLD,70));
		jp1.add(jtitle);

		jp2=new JPanel();
		jp2.setLayout(new FlowLayout(FlowLayout.RIGHT,50,10));
		jp2.setBounds(0,HEIGHT-700,WIDTH,600);


		Object[][] courseinformation= {};
		Object[] courselist = {"课程编号","学年学期","课程","专业","授课教师","状态","类型"};
		DefaultTableModel model;
		model = new DefaultTableModel(courseinformation, courselist);

		Message mes = new Message();

		Client client=new Client(this.socket);

		mes.setUserType(0);

		mes.setModuleType(ModuleType.Course);
		mes.setMessageType(MessageType.REQ_SHOW_ALL_LESSON);
		Message rec=new Message();
		rec=client.sendRequestToServer(mes);
		Vector<String> allCourseContents =  rec.getContent();
		System.out.println(allCourseContents.size());
		Object sigRow[] = new  String[7];
		for(int i=0;i<allCourseContents.size();) {
			for(int j=0;j<7;j++) {
				sigRow[j]=allCourseContents.get(i);
				i++;
			}
			model.addRow(sigRow);
		}

		Box box1= Box.createHorizontalBox();
		Box box2=Box.createHorizontalBox();
		Box box3=Box.createHorizontalBox();
		Box boxH=Box.createVerticalBox();

		jtb1=new JTable();
		jtb1.setModel(model);
		scrollPane = new JScrollPane(jtb1);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(WIDTH-100,500));
		jtb1.setPreferredSize(new Dimension(WIDTH-100,2000));
		jtb1.setFont(new Font("微软雅黑",Font.BOLD,20));
		jtb1.getTableHeader().setPreferredSize(new Dimension(1, 40));
		jtb1.getTableHeader().setFont(new Font("宋体",Font.BOLD,25));
		jtb1.setRowHeight(50);
		//jtb1.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		box1.add(Box.createHorizontalStrut(400));
		box1.add(scrollPane);


		jb1=new JButton("选择");
		jb1.setFont(f1);
		jb1.setBounds(10, 300, 70, 30);

		jl1=new JLabel("课程号:");
		jl1.setFont(f1);

		jb2=new JButton("已选课程查询");
		jb2.setFont(f1);

		jb3=new JButton("课程查询");
		jb3.setFont(f1);

		jtf1=new JTextField(10);

		jtf2=new JTextField(10);



		jb4=new JButton("退课");
		jb4.setFont(f1);


		jb1.addActionListener(this);
		jb1.setActionCommand("choose");
		jb2.addActionListener(this);
		jb2.setActionCommand("chozen");
		jb3.addActionListener(this);
		jb3.setActionCommand("check");
		jb4.addActionListener(this);
		jb4.setActionCommand("cancel");

		box2.add(Box.createHorizontalStrut(1000));
		box2.add(jl1);
		box2.add(Box.createHorizontalStrut(10));
		box2.add(jtf2);
		box2.add(Box.createHorizontalStrut(10));
		box2.add(jb1);
		box2.add(Box.createHorizontalStrut(10));
		box2.add(jb4);
		box2.add(Box.createHorizontalStrut(10));
		box2.add(jb2);
		//box2.add(Box.createHorizontalStrut(400));

		box3.add(Box.createHorizontalStrut(1200));
		box3.add(jtf1);
		box3.add(Box.createHorizontalStrut(10));
		box3.add(jb3);
		//box3.add(Box.createHorizontalStrut(400));


		boxH.add(box1);
		boxH.add(Box.createVerticalStrut(10));
		boxH.add(box2);
		boxH.add(Box.createVerticalStrut(10));
		boxH.add(box3);



		jbackground.add(jp2);
		jp2.add(boxH);
		jframe.setVisible(true);
		jframe.validate();


		/*jbackground=new JPanel();


		jb1=new JButton("选择");
	    jb1.setFont(f1);
	    jb1.setBounds(10, 300, 70, 30);

	    jl1=new JLabel("课程号:");
	    jl1.setFont(f1);

		jb2=new JButton("已选课程查询");
		jb2.setFont(f1);

		jb3=new JButton("课程查询");
		jb3.setFont(f1);

		jtf1=new JTextField(10);

		jtf2=new JTextField(10);



		jb4=new JButton("退课");
		jb4.setFont(f1);

		Object[][] courseinformation= {};
        Object[] courselist = {"学年学期","专业","课程编号","课程","授课人数","状态","类型"};
        DefaultTableModel model;
        model = new DefaultTableModel(courseinformation, courselist);

        ClientReq clientReq = new ClientReq();
		clientReq.setType("REQ_SHOW_ALL_LESSON");
		//传数据
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(clientReq);
		oos.flush();
		//接受数据
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		clientReq = (ClientReq) ois.readObject();
		Vector<String> allCourseContents = clientReq.getContent();
		System.out.println(allCourseContents.size());
		Object sigRow[] = new  String[7];
		for(int i=0;i<allCourseContents.size();) {
			for(int j=0;j<7;j++) {
				sigRow[j]=allCourseContents.get(i);
				i++;
			}
			model.addRow(sigRow);
		}

        jtb1=new JTable();
		jtb1.setModel(model);
		scrollPane = new JScrollPane(jtb1);

		jb1.addActionListener(this);
		jb1.setActionCommand("choose");
		jb2.addActionListener(this);
		jb2.setActionCommand("chozen");
		jb3.addActionListener(this);
		jb3.setActionCommand("check");
		jb4.addActionListener(this);
		jb4.setActionCommand("cancel");

		jp1=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
		jp5=new JPanel();

	    jp1.add(jl1);
		jp1.add(jtf2);
		jp1.add(jb1);
		jp1.add(jb4);
		jp1.add(jb2);
		jp5.add(jb3);
		jp4.add(jtf1);
		jp3.add(jp4);
		jp3.add(jp5);


		//cm=new CouTableModel(0);
		//jt = new JTable(cm);


		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		getContentPane().add(jp3,BorderLayout.EAST);
		getContentPane().add(jp1,BorderLayout.SOUTH);
		getContentPane().add(scrollPane,BorderLayout.WEST);

		this.setTitle("学生选课");
		this.setSize(w/2,(h-25)/2);

		this.setLocationRelativeTo(null);*/
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Client client =new Client(this.socket);
		if(e.getActionCommand() == "choose") {
			this.setVisible(false);
			Message clientReq = new Message();//新建申请用于交换
			Vector<String> reqContent = new Vector<String>();
			reqContent.add(jtf2.getText());
			reqContent.add(userID);
			clientReq.setContent(reqContent);
			clientReq.setModuleType(ModuleType.Course);
			clientReq.setMessageType("REQ_STU_ADD_LESSON");
			Message rec=client.sendRequestToServer(clientReq);
//			try {
//				ClientStuCourseFrame csf = new ClientStuCourseFrame(userID,this.socket);
//			} catch (ClassNotFoundException ex) {
//				throw new RuntimeException(ex);
//			} catch (SQLException ex) {
//				throw new RuntimeException(ex);
//			} catch (IOException ex) {
//				throw new RuntimeException(ex);
//			}
//			clientReq.setModuleType(ModuleType.Course);
			clientReq.setMessageType("REQ_SHOW_ALL_LESSON");
			rec=client.sendRequestToServer(clientReq);

			Vector<String>	allCourseInfor = rec.getContent();
			int rowNumber = allCourseInfor.size()/7;
			String[][] allCourseTable = new String[rowNumber][7];
			int storingPlace = 0;
			for(int i=0;i<rowNumber;i++) {
				for(int j=0;j<7;j++)
					allCourseTable[i][j] = allCourseInfor.get(storingPlace++);
			}
			jtb1 = new JTable();
			jtb1.setModel(new DefaultTableModel(
					allCourseTable,
					new String[] {
							"课程编号","学年学期","课程","专业","授课教师","状态","类型"
					}
			));
			jtb1.getColumnModel().getColumn(0).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(1).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(2).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(3).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(4).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(5).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(6).setPreferredWidth(161);
			scrollPane.setViewportView(jtb1);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setPreferredSize(new Dimension(WIDTH-100,500));
			jtb1.setPreferredSize(new Dimension(WIDTH-100,2000));
			jtb1.setFont(new Font("微软雅黑",Font.BOLD,20));
			jtb1.getTableHeader().setPreferredSize(new Dimension(1, 40));
			jtb1.getTableHeader().setFont(new Font("宋体",Font.BOLD,25));
			jtb1.setRowHeight(50);
			//this.setVisible(false);
			this.setLocationRelativeTo(null);
		}
		else if(e.getActionCommand() == "chozen") {
			this.setVisible(false);
			Message clientReq = new Message();//新建申请用于交换
			User user = new User();
			user.setId(userID);
			clientReq.setContent(user.getContent());
			clientReq.setModuleType(ModuleType.Course);
			clientReq.setMessageType("REQ_STU_ALL_CHOOOSE");
			Message rec=client.sendRequestToServer(clientReq);

			Vector<String>	allCourseInfor = rec.getContent();
			int rowNumber = allCourseInfor.size()/7;
			String[][] allCourseTable = new String[rowNumber][7];
			int storingPlace = 0;
			for(int i=0;i<rowNumber;i++) {
				for(int j=0;j<7;j++)
					allCourseTable[i][j] = allCourseInfor.get(storingPlace++);
			}
			jtb1 = new JTable();
			jtb1.setModel(new DefaultTableModel(
					allCourseTable,
					new String[] {
							"课程编号","学年学期","课程","专业","授课教师","状态","类型"
					}
			));
			jtb1.getColumnModel().getColumn(0).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(1).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(2).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(3).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(4).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(5).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(6).setPreferredWidth(161);
			scrollPane.setViewportView(jtb1);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setPreferredSize(new Dimension(WIDTH-100,500));
			jtb1.setPreferredSize(new Dimension(WIDTH-100,2000));
			jtb1.setFont(new Font("微软雅黑",Font.BOLD,20));
			jtb1.getTableHeader().setPreferredSize(new Dimension(1, 40));
			jtb1.getTableHeader().setFont(new Font("宋体",Font.BOLD,25));
			jtb1.setRowHeight(50);
			//this.setVisible(true);
			this.setLocationRelativeTo(null);
		}
		else if(e.getActionCommand() == "check") {
			Message clientReq = new Message();//新建申请用于交换
			clientReq.setModuleType(ModuleType.Course);
			clientReq.setMessageType("REQ_SEARCH_LESSON");//查找课程
			Vector<String> reqContent = new Vector<String>();
			Course c = new Course();
			c.setCourseID(jtf1.getText());
			reqContent = c.getContent();
			clientReq.setContent(reqContent);//调用信息存进申请
			Message rec=client.sendRequestToServer(clientReq);
			System.out.println(rec.getContent());
			//通信

			Vector<String>	allCourseInfor =  rec.getContent();
			int rowNumber = allCourseInfor.size()/7;
			String[][] allCourseTable = new String[rowNumber][7];
			int storingPlace = 0;
			for(int i=0;i<rowNumber;i++) {
				for(int j=0;j<7;j++)
					allCourseTable[i][j] = allCourseInfor.get(storingPlace++);
			}
			jtb1 = new JTable();
			jtb1.setModel(new DefaultTableModel(
					allCourseTable,
					new String[] {
							"课程编号","学年学期","课程","专业","授课教师","状态","类型"
					}
			));
			jtb1.getColumnModel().getColumn(0).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(1).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(2).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(3).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(4).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(5).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(6).setPreferredWidth(161);
			scrollPane.setViewportView(jtb1);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setPreferredSize(new Dimension(WIDTH-100,500));
			jtb1.setPreferredSize(new Dimension(WIDTH-100,2000));
			jtb1.setFont(new Font("微软雅黑",Font.BOLD,20));
			jtb1.getTableHeader().setPreferredSize(new Dimension(1, 40));
			jtb1.getTableHeader().setFont(new Font("宋体",Font.BOLD,25));
			jtb1.setRowHeight(50);
			//this.setVisible(true);
			this.setLocationRelativeTo(null);
		}
		else if(e.getActionCommand() == "cancel") {
			this.setVisible(false);
			Message clientReq = new Message();
			Vector<String> content = new Vector<String>();
			content.add(jtf2.getText());//课ID
			content.add(userID);//人ID
			clientReq.setContent(content);
			clientReq.setModuleType(ModuleType.Course);
			clientReq.setMessageType("REQ_STU_REMOVE_LESSON");
			Message rec=client.sendRequestToServer(clientReq);

			clientReq.setModuleType(ModuleType.Course);
			clientReq.setMessageType("REQ_STU_ALL_CHOOOSE");
			User user =new User();
			user.setId(userID);
			clientReq.setContent(user.getContent());
			rec=client.sendRequestToServer(clientReq);

			Vector<String>	allCourseInfor =  rec.getContent();
			int rowNumber = allCourseInfor.size()/7;
			String[][] allCourseTable = new String[rowNumber][7];
			int storingPlace = 0;
			for(int i=0;i<rowNumber;i++) {
				for(int j=0;j<7;j++)
					allCourseTable[i][j] = allCourseInfor.get(storingPlace++);
			}
			jtb1 = new JTable();
			jtb1.setModel(new DefaultTableModel(
					allCourseTable,
					new String[] {
							"课程编号","学年学期","课程","专业","授课教师","状态","类型"
					}
			));
			jtb1.getColumnModel().getColumn(0).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(1).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(2).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(3).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(4).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(5).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(6).setPreferredWidth(161);

			scrollPane.setViewportView(jtb1);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setPreferredSize(new Dimension(WIDTH-100,500));
			jtb1.setPreferredSize(new Dimension(WIDTH-100,2000));
			jtb1.setFont(new Font("微软雅黑",Font.BOLD,20));
			jtb1.getTableHeader().setPreferredSize(new Dimension(1, 40));
			jtb1.getTableHeader().setFont(new Font("宋体",Font.BOLD,25));
			jtb1.setRowHeight(50);
			//this.setVisible(true);
			this.setLocationRelativeTo(null);
		}
	}
}
	
