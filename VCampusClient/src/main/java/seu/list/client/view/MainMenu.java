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
	private String uID;
	private int userType;
	private Socket socket;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public MainMenu(int sign,String uID,Socket socket) {
		Toolkit kit = Toolkit.getDefaultToolkit();//获取当前屏幕大小
		Dimension screensize = kit.getScreenSize();
		int width=screensize.width;
		int height = screensize.height;
		int x=(width-627)/2;
		int y=(height-450)/2;
		setBounds(x,y,627,450);

		this.uID=uID;
		this.socket=socket;
		userType=sign;
		setTitle("\u865A\u62DF\u6821\u56ED\u7CFB\u7EDF-\u4E3B\u83DC\u5355");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 627, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u865A\u62DF\u6821\u56ED\u7CFB\u7EDF\uFF01");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(98, 0, 426, 53);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		lblNewLabel_1.setBounds(22, 46, 164, 39);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("\u5B66\u7C4D\u7BA1\u7406");//学籍管理
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		btnNewButton.setBounds(89, 95, 153, 39);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u56FE\u4E66\u9986");//图书馆
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		btnNewButton_1.setBounds(347, 95, 153, 39);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("\u9009\u8BFE");//选课
		btnNewButton_2.setFont(new Font("微软雅黑", Font.BOLD, 20));
		btnNewButton_2.setBounds(89, 172, 153, 39);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setActionCommand("Course");
		JButton btnNewButton_3 = new JButton("\u5BBF\u820D");//宿舍
		btnNewButton_3.setFont(new Font("微软雅黑", Font.BOLD, 20));
		btnNewButton_3.setBounds(347, 172, 153, 39);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("\u5546\u5E97");//商店
		btnNewButton_4.setFont(new Font("微软雅黑", Font.BOLD, 20));
		btnNewButton_4.setBounds(89, 256, 153, 39);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_4_1 = new JButton("\u9000\u51FA");//退出

		btnNewButton_4_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // 退出按钮
				//ClientMainFrame.goOffline();
				System.exit(0);
			}
		});

		btnNewButton_4_1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		btnNewButton_4_1.setBounds(423, 345, 153, 39);
		contentPane.add(btnNewButton_4_1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Course")){
			if(userType==0){
				try {
					ClientStuCourseFrame s=new ClientStuCourseFrame(uID,socket);
				} catch (ClassNotFoundException ex) {
					throw new RuntimeException(ex);
				} catch (SQLException ex) {
					throw new RuntimeException(ex);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}else{
				try {
					ClientTeacherFrame s=new ClientTeacherFrame(uID,socket);
				} catch (ClassNotFoundException ex) {
					throw new RuntimeException(ex);
				} catch (SQLException ex) {
					throw new RuntimeException(ex);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
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
