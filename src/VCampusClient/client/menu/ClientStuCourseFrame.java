package virtualSchoolClient.src.vsst.client.view;
//学生选课界面
import virtualSchoolClient.src.vsst.common.ClientReq;
import virtualSchoolClient.src.vsst.common.Course;
import virtualSchoolClient.src.vsst.common.User;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class ClientStuCourseFrame extends JFrame implements ActionListener{

	//private static final long serialVersionUID = 1L;

	JScrollPane jsp;
    JPanel jp1,jp2,jp3,jp4,jp5;
	JButton jb1,jb2,jb3,jb4;
	JLabel jl1,jl2,jl3,jl4;
	JTextField jtf1,jtf2;
	JTable jtb1;
	JScrollPane scrollPane;
	Socket socket;//传送数据
	//CouTableModel cm;
	private String userID;


	Font f1=new Font("华文行楷",Font.PLAIN,18);
	
	public ClientStuCourseFrame(String number, Socket socket) throws ClassNotFoundException, SQLException,IOException, ClassNotFoundException 
	{
		
		this.socket = socket;
		userID=number;
		jb1=new JButton("选择");
	    jb1.setFont(f1);
	    jb1.setBounds(10, 300, 70, 30);
	    jl1=new JLabel("课程号:");
	    jl1.setFont(f1);
		jb2=new JButton("已选课程查询");
		jb2.setFont(f1);
		jb3=new JButton("课程查询");
		jtf1=new JTextField(10);
		jtf2=new JTextField(10);
		jb3.setFont(f1);
		jb4=new JButton("退课");
		jb4.setFont(f1);
		Object[][] courseinformation= {};
        Object[] courselist = {"课程ID","课程名称","课程课时","最大容量","选课人数"};
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
		
		Object sigRow[] = new  String[5];
		for(int i=0;i<allCourseContents.size();) {
			for(int j=0;j<5;) {
				sigRow[j++]=allCourseContents.get(i++);
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
		this.setVisible(true);	
		this.setLocationRelativeTo(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "choose") {
			this.setVisible(false);
			ClientReq clientReq = new ClientReq();//新建申请用于交换
			Vector<String> reqContent = new Vector<String>();
            reqContent.add(jtf2.getText());
			reqContent.add(userID);
            clientReq.setContent(reqContent);
			clientReq.setType("REQ_STU_ADD_LESSON");
			try {
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(clientReq);
				oos.flush();
				this.setVisible(false);
				ClientStuCourseFrame csf = new ClientStuCourseFrame(userID,socket);
			} catch(Exception e1) {
				e1.printStackTrace();
			}clientReq.setType("REQ_SHOW_ALL_LESSON");
			try {
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(clientReq);
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				clientReq = (ClientReq) ois.readObject();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			Vector<String>	allCourseInfor = clientReq.getContent();
			int rowNumber = allCourseInfor.size()/5;
			String[][] allCourseTable = new String[rowNumber][5];
			int storingPlace = 0;
			for(int i=0;i<rowNumber;i++) {
				for(int j=0;j<5;j++)
					allCourseTable[i][j] = allCourseInfor.get(storingPlace++);
			}
			jtb1 = new JTable();
			jtb1.setModel(new DefaultTableModel(
				allCourseTable,
				new String[] {
						"课程ID","课程名称","课程课时","最大容量","选课人数"
				}
			));
			jtb1.getColumnModel().getColumn(0).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(1).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(2).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(3).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(4).setPreferredWidth(161);
			scrollPane.setViewportView(jtb1);
			this.setVisible(false);
			this.setLocationRelativeTo(null);
		}
		else if(e.getActionCommand() == "chozen") {
			this.setVisible(false);
			ClientReq clientReq = new ClientReq();//新建申请用于交换
			User user = new User();
			user.setId(userID);
			clientReq.setContent(user.getContent());
			clientReq.setType("REQ_STU_ALL_CHOOOSE");
			try {
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(clientReq);
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				clientReq = (ClientReq) ois.readObject();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
			Vector<String>	allCourseInfor = clientReq.getContent();
			int rowNumber = allCourseInfor.size()/5;
			String[][] allCourseTable = new String[rowNumber][5];
			int storingPlace = 0;
			for(int i=0;i<rowNumber;i++) {
				for(int j=0;j<5;j++)
					allCourseTable[i][j] = allCourseInfor.get(storingPlace++);
			}
			jtb1 = new JTable();
			jtb1.setModel(new DefaultTableModel(
				allCourseTable,
				new String[] {
						"课程ID","课程名称","课程课时","最大容量","选课人数"
				}
			));
			jtb1.getColumnModel().getColumn(0).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(1).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(2).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(3).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(4).setPreferredWidth(161);
			scrollPane.setViewportView(jtb1);
			this.setVisible(true);
			this.setLocationRelativeTo(null);
		}
		else if(e.getActionCommand() == "check") {
			ClientReq clientReq = new ClientReq();//新建申请用于交换
			clientReq.setType("REQ_SEARCH_LESSON");//查找课程
			Vector<String> reqContent = new Vector<String>();
			Course c = new Course();
			c.setCourseID(jtf1.getText());
			reqContent = c.getContent();
			clientReq.setContent(reqContent);//调用信息存进申请
			//通信
			try {
			ObjectOutputStream oos5 = new ObjectOutputStream(socket.getOutputStream());//把这个socket通过OutputStream输出给Server端
			oos5.writeObject(clientReq);//写进申请里面去（序列化）
			oos5.flush();//上传等待处理
            ObjectInputStream ois5 = new ObjectInputStream(socket.getInputStream());//把这个socket通过InputStream写回client端
			clientReq = (ClientReq) ois5.readObject();}//读出（去序列化）
			catch(Exception e3) {
				e3.printStackTrace();
			}
			Vector<String>	allCourseInfor = clientReq.getContent();
			int rowNumber = allCourseInfor.size()/5;
			String[][] allCourseTable = new String[rowNumber][5];
			int storingPlace = 0;
			for(int i=0;i<rowNumber;i++) {
				for(int j=0;j<5;j++)
					allCourseTable[i][j] = allCourseInfor.get(storingPlace++);
			}
			jtb1 = new JTable();
			jtb1.setModel(new DefaultTableModel(
				allCourseTable,
				new String[] {
						"课程ID","课程名称","课程课时","最大容量","选课人数"
				}
			));
			jtb1.getColumnModel().getColumn(0).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(1).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(2).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(3).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(4).setPreferredWidth(161);
			scrollPane.setViewportView(jtb1);
			this.setVisible(true);
			this.setLocationRelativeTo(null);
		}
	    else if(e.getActionCommand() == "cancel") {
	    	this.setVisible(false);
			ClientReq clientReq = new ClientReq();
			Vector<String> content = new Vector<String>();
			content.add(jtf2.getText());//课ID
			content.add(userID);//人ID
            clientReq.setContent(content);
			clientReq.setType("REQ_STU_REMOVE_LESSON");
			try {
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(clientReq);
				oos.flush();
			} catch(Exception e4) {
				e4.printStackTrace();
			}
			clientReq.setType("REQ_STU_ALL_CHOOOSE");
			User user =new User();
			user.setId(userID);
			clientReq.setContent(user.getContent());
			try {
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(clientReq);
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				clientReq = (ClientReq) ois.readObject();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			Vector<String>	allCourseInfor = clientReq.getContent();
			int rowNumber = allCourseInfor.size()/4;
			String[][] allCourseTable = new String[rowNumber][4];
			int storingPlace = 0;
			for(int i=0;i<rowNumber;i++) {
				for(int j=0;j<4;j++)
					allCourseTable[i][j] = allCourseInfor.get(storingPlace++);
			}
			jtb1 = new JTable();
			jtb1.setModel(new DefaultTableModel(
				allCourseTable,
				new String[] {
						"课程ID","课程名称","课程课时","最大容量","选课人数"
				}
			));
			jtb1.getColumnModel().getColumn(0).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(1).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(2).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(3).setPreferredWidth(161);
			jtb1.getColumnModel().getColumn(4).setPreferredWidth(161);
			scrollPane.setViewportView(jtb1);
			this.setVisible(true);
			this.setLocationRelativeTo(null);
	    }
	  }
	}
	
