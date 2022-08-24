package seu.list.client.view;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class MainMenu extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	
	private final String cmdClass = "CMD_CLASS";
	private final String cmdLib = "CMD_LIB";
	private final String cmdCourse = "CMD_COURSE";
	private final String cmdDorm = "CMD_DORM";
	private final String cmdShop = "CMD_SHOP";

	private String uID;
	private String pwd;
	private int userType;
	private Socket socket;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			String uID=this.uID;
			int userType=this.userType;
			Socket socket=this.socket;
			public void run() {
				try {

					MainMenu frame = new MainMenu(userType,uID,socket);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 */

	public MainMenu(int sign, String uID, String pwd, Socket socket) {
		Toolkit kit = Toolkit.getDefaultToolkit();//获取当前屏幕大小
		Dimension screensize = kit.getScreenSize();
		int width=screensize.width;
		int height = screensize.height;
		int x=(width-627)/2;
		int y=(height-450)/2;
		setBounds(x,y,627,450);

		this.uID=uID;
		this.pwd=pwd;
		this.socket=socket;
		this.userType=sign;

		
		setTitle("\u865A\u62DF\u6821\u56ED\u7CFB\u7EDF-\u4E3B\u83DC\u5355");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 627, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel titleButton = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u865A\u62DF\u6821\u56ED\u7CFB\u7EDF\uFF01");
		titleButton.setFont(new Font("微软雅黑", Font.BOLD, 30));
		titleButton.setHorizontalAlignment(SwingConstants.CENTER);
		titleButton.setBounds(98, 0, 426, 53);
		contentPane.add(titleButton);

		JLabel userNameButton = new JLabel("\u7528\u6237\u540D\uFF1A" + this.uID);
		userNameButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		userNameButton.setBounds(22, 46, 164, 39);
		contentPane.add(userNameButton);

		JButton classButton = new JButton("\u5B66\u7C4D\u7BA1\u7406");//学籍管理
		classButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		classButton.setBounds(89, 95, 153, 39);
		contentPane.add(classButton);
		classButton.addActionListener(this);
		classButton.setActionCommand(this.cmdClass);

		JButton libraryButton = new JButton("\u56FE\u4E66\u9986");//图书馆
		libraryButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		libraryButton.setBounds(347, 95, 153, 39);
		contentPane.add(libraryButton);
		libraryButton.addActionListener(this);
		libraryButton.setActionCommand(this.cmdLib);

		JButton courseButton = new JButton("\u9009\u8BFE");//选课
		courseButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		courseButton.setBounds(89, 172, 153, 39);
		contentPane.add(courseButton);
		courseButton.addActionListener(this);
		courseButton.setActionCommand(this.cmdCourse);
		
		JButton dormButton = new JButton("\u5BBF\u820D");//宿舍
		dormButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		dormButton.setBounds(347, 172, 153, 39);
		contentPane.add(dormButton);
		dormButton.addActionListener(this);
		dormButton.setActionCommand(this.cmdDorm);
		
		JButton shopButton = new JButton("\u5546\u5E97");//商店
		shopButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		shopButton.setBounds(89, 256, 153, 39);
		contentPane.add(shopButton);
		shopButton.addActionListener(this);
		shopButton.setActionCommand(this.cmdShop);
		
		JButton exitButton = new JButton("\u9000\u51FA");//退出
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // 退出按钮
				//ClientMainFrame.goOffline();
				System.exit(0);
			}
		});
		exitButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		exitButton.setBounds(423, 345, 153, 39);
		contentPane.add(exitButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getActionCommand().equals(this.cmdCourse)){ // 选课
				if(userType==0){
					ClientStuCourseFrame s=new ClientStuCourseFrame(uID,socket);
					s.setVisible(true);
				}else{
					ClientTeacherFrame s=new ClientTeacherFrame(uID,socket);
					s.setVisible(true);
				}
			}else if(e.getActionCommand().equals(this.cmdClass)) { // 学籍
				if(userType == 0) {
					ClassStudentClient classStu = new ClassStudentClient(this.uID, this.pwd);
					classStu.setVisible(true);
				}else {
					ClassAdminClient classAdmin = new ClassAdminClient();
					classAdmin.setVisible(true);
				}
			}else if(e.getActionCommand().equals(this.cmdLib)) { //图书馆
				if(userType == 0) {
					LibraryStu libStu = new LibraryStu();
					libStu.setVisible(true);
				}else {
					LibraryManage libMgr = new LibraryManage();
					libMgr.setVisible(true);
				}
			}else if(e.getActionCommand().equals(this.cmdDorm)) { // 宿舍
				String title = "宿舍管理";
				if(userType == 0) {
					DormitoryStudentClient dormStu = new DormitoryStudentClient(uID,this.socket);
					dormStu.setVisible(true);
				}else {
					DormitoryAdminClient dormAdmin = new DormitoryAdminClient(this.socket);
					dormAdmin.setVisible(true);
				}
			}else { //商店
				if(userType == 0) {
					Shop_StudentFrame shopStu = new Shop_StudentFrame();
					shopStu.setVisible(true);
				}else {
					Shop_AdminFrame shopAdmin = new Shop_AdminFrame();
					shopAdmin.setVisible(true);
				}
			}
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException(ex);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
