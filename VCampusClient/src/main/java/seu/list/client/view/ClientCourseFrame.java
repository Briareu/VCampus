//package VCampusClient.src.main.java.seu.list.client.view;
package seu.list.client.view;

/*
import VCampusClient.src.main.java.seu.list.common.ModuleType;
import main.java.seu.list.common.Course;
import main.java.seu.list.common.Message;
*/

import seu.list.client.bz.Client;
import seu.list.common.Course;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.ModuleType;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;


public class ClientCourseFrame extends JFrame implements ActionListener{


	JFrame jFrame=new JFrame();
	final int WIDTH=1200;
	final int HEIGHT=800;
	JPanel jp1,jp2;
	JTable jtb1;
	JScrollPane jsp;
	JComboBox jcb;
	JTextField jtf,jtf1;
	JButton jco_Search,jco_Add,jco_Delete;
	Socket socket;
	JScrollPane scrollPane;
	JLabel jlb;
	private String userID;
	
	int index = 0;
	
	public ClientCourseFrame(String ID, Socket socket) throws ClassNotFoundException, SQLException,IOException, ClassNotFoundException {
		Tools.setWindowspos(WIDTH,HEIGHT,jFrame);
		userID=ID;
		this.socket=socket;
		Client client=new Client(this.socket);
		JPanel jbackground=new JPanel();
		jbackground.setBackground(new Color(66,99,116));
		jbackground.setLayout(null);
		jbackground.setBounds(0,0,WIDTH,HEIGHT);
		jFrame.add(jbackground);
		jp1=new JPanel();
		jp1.setBackground(new Color(255,251,240));
		jp1.setBounds(0,0,WIDTH,HEIGHT-700);
		jbackground.add(jp1);
		JLabel jtitle=new JLabel("选课系统");
		jtitle.setFont(new Font("宋体",Font.BOLD,70));
		jp1.add(jtitle);

		jp2=new JPanel();
		jp2.setLayout(new FlowLayout(FlowLayout.RIGHT,50,10));
		jp2.setBounds(0,HEIGHT-700,WIDTH,700);


		Box box1,box2,box3,boxH;
		box1=Box.createHorizontalBox();
		box2=Box.createHorizontalBox();
		box3=Box.createHorizontalBox();
		boxH=Box.createVerticalBox();

//		jp1 = new JPanel();
//		jp2 = new JPanel();

		String [] seOp = {"全部","课程号"};
		jlb = new JLabel("课程名称:");
		jlb.setFont(new Font("微软雅黑",Font.BOLD,20));
		jcb = new JComboBox(seOp);
		jcb.setFont(new Font("微软雅黑",Font.BOLD,20));
		jtf = new JTextField(20);
		jtf1 = new JTextField(10);
		jco_Search = new JButton("搜索");
		jco_Search.setFont(new Font("微软雅黑",Font.BOLD,20));
		jco_Search.addActionListener(this);
		jco_Search.setActionCommand("search");
		jco_Add =new JButton("增加课程");
		jco_Add.setFont(new Font("微软雅黑",Font.BOLD,20));
		jco_Add.addActionListener(this);
		jco_Add.setActionCommand("add");
		jco_Delete =new JButton("移除课程");
		jco_Delete.setFont(new Font("微软雅黑",Font.BOLD,20));
		jco_Delete.addActionListener(this);
		jco_Delete.setActionCommand("delete");
		Object[][] courseinformation= {};
		Object[] courselist = {"课程编号","学年学期","课程","专业","授课教师","状态","类型"};
		DefaultTableModel model;
		model = new DefaultTableModel(courseinformation, courselist);

		Message clientReq = new Message();
		clientReq.setModuleType(ModuleType.Course);
		clientReq.setMessageType("REQ_SHOW_ALL_LESSON");
		Message rec=client.sendRequestToServer(clientReq);
		Vector<String> allCourseContents = rec.getContent();

		Object sigRow[] = new  String[7];
		for(int i=0;i<allCourseContents.size();) {
			for(int j=0;j<7;) {
				sigRow[j++]=allCourseContents.get(i++);
			}
			model.addRow(sigRow);
		}

		jtb1=new JTable();
		jtb1.setModel(model);
		jtb1.setPreferredSize(new Dimension(WIDTH-100,2000));
		jtb1.setFont(new Font("微软雅黑",Font.BOLD,20));
		jtb1.getTableHeader().setPreferredSize(new Dimension(1, 40));
		jtb1.getTableHeader().setFont(new Font("宋体",Font.BOLD,25));
		jtb1.setRowHeight(50);
		scrollPane = new JScrollPane(jtb1);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(WIDTH-200,500));

		/*
		lm = new LibModel();
		lm.init(vec);
		jt = new JTable(lm);
		jsp = new JScrollPane(jt);*/

		//添加组件
//		jp1.add(jcb);
//		jp1.add(jtf);
//		jp1.add(jco_Search);
		box1.add(Box.createHorizontalStrut(1000));
		box1.add(jcb);
		box1.add(Box.createHorizontalStrut(10));
		box1.add(jtf);
		box1.add(Box.createHorizontalStrut(10));
		box1.add(jco_Search);

		box2.add(Box.createHorizontalStrut(400));
		box2.add(scrollPane);
		box3.add(Box.createHorizontalStrut(1000));
		box3.add(jco_Add);
		box3.add(Box.createHorizontalStrut(10));
		box3.add(jlb);
		box3.add(Box.createHorizontalStrut(10));
		box3.add(jtf1);
		box3.add(Box.createHorizontalStrut(10));
		box3.add(jco_Delete);
//		jp2.add(jco_Add);
//		jp2.add(jlb);
//		jp2.add(jtf1);
//		jp2.add(jco_Delete);


		boxH.add(box1);
		boxH.add(Box.createVerticalStrut(10));
		boxH.add(box2);
		boxH.add(Box.createVerticalStrut(10));
		boxH.add(box3);
		jp2.add(boxH);
		jbackground.add(jp2);
		jFrame.setVisible(true);
		jFrame.validate();
		//this.setLocationRelativeTo(null);
//		this.add(jp1,BorderLayout.NORTH);
//		this.add(jp3,BorderLayout.CENTER);
//		this.add(jp2,BorderLayout.SOUTH);

		//this.setSize(WIDTH, HEIGHT);
		//this.setLocationRelativeTo(null);
		//this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Client client=new Client(this.socket);
		if(e.getActionCommand()=="search"){
			if(jcb.getSelectedItem().equals("全部")) {
				Message clientReq = new Message();
				clientReq.setModuleType(ModuleType.Course);
				clientReq.setMessageType("REQ_SHOW_ALL_LESSON");
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

				jtb1.setPreferredSize(new Dimension(WIDTH-100,2000));
				jtb1.setFont(new Font("微软雅黑",Font.BOLD,20));
				jtb1.getTableHeader().setPreferredSize(new Dimension(1, 40));
				jtb1.getTableHeader().setFont(new Font("宋体",Font.BOLD,25));
				jtb1.setRowHeight(50);

				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setPreferredSize(new Dimension(WIDTH-200,500));
				scrollPane.setViewportView(jtb1);


			}else if(jcb.getSelectedItem().equals("课程号")) {
				Message clientReq = new Message();//新建申请用于交换
				clientReq.setModuleType(ModuleType.Course);
				clientReq.setMessageType("REQ_SEARCH_LESSON");//查找课程
				Vector<String> reqContent = new Vector<String>();
				Course c = new Course();
				c.setCourseID(jtf.getText());
				reqContent = c.getContent();
				clientReq.setContent(reqContent);//调用信息存进申请
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
				jtb1.setPreferredSize(new Dimension(WIDTH-100,2000));
				jtb1.setFont(new Font("微软雅黑",Font.BOLD,20));
				jtb1.getTableHeader().setPreferredSize(new Dimension(1, 40));
				jtb1.getTableHeader().setFont(new Font("宋体",Font.BOLD,25));
				jtb1.setRowHeight(50);

				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setPreferredSize(new Dimension(WIDTH-200,500));
				scrollPane.setViewportView(jtb1);
			}

		}
		else if(e.getActionCommand()=="add") {
			CourseInfor courseInfor = new CourseInfor(userID,this.socket);
			this.setVisible(false);

		}
		else if(e.getActionCommand()=="delete") {
			Message clientReq = new Message();
			clientReq.setModuleType(ModuleType.Course);
			clientReq.setMessageType("REQ_REMOVE_LESSON");
			Vector<String> reqContent = new Vector<String>();
			reqContent.setSize(7);
			reqContent.set(2,jtf1.getText());
			clientReq.setContent(reqContent);
			Message rec=client.sendRequestToServer(clientReq);


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

			jtb1.setPreferredSize(new Dimension(WIDTH-100,2000));
			jtb1.setFont(new Font("微软雅黑",Font.BOLD,20));
			jtb1.getTableHeader().setPreferredSize(new Dimension(1, 40));
			jtb1.getTableHeader().setFont(new Font("宋体",Font.BOLD,25));
			jtb1.setRowHeight(50);

			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setPreferredSize(new Dimension(WIDTH-200,500));
			scrollPane.setViewportView(jtb1);
//			this.setVisible(false);
//			try {
//				ClientCourseFrame ccf = new ClientCourseFrame(userID,this.socket);
//			} catch (ClassNotFoundException ex) {
//				throw new RuntimeException(ex);
//			} catch (SQLException ex) {
//				throw new RuntimeException(ex);
//			} catch (IOException ex) {
//				throw new RuntimeException(ex);
//			}

		}
	}
}
