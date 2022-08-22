package seu.list.client.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu(String id, String pwd) {	
		setTitle("\u865A\u62DF\u6821\u56ED\u7CFB\u7EDF-\u4E3B\u83DC\u5355");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u865A\u62DF\u6821\u56ED\u7CFB\u7EDF\uFF01");
		titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(98, 0, 426, 53);
		contentPane.add(titleLabel);
		
		JLabel userNameLabel = new JLabel("\u7528\u6237\u540D\uFF1A" + id);
		userNameLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		userNameLabel.setBounds(22, 46, 164, 39);
		contentPane.add(userNameLabel);
		
		JButton classButton = new JButton("\u5B66\u7C4D\u7BA1\u7406");
		classButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // 学籍管理按钮
				int level = Integer.parseInt(String.valueOf(id.charAt(0)));
				if(level == 0) { // 学生用户
					ClassStudentClient classStu = new ClassStudentClient(id, pwd);
					classStu.setVisible(true);
				}else if(level == 1) { // 管理员用户
					ClassAdminClient classAdmin = new ClassAdminClient();
					classAdmin.setVisible(true);
				}
			}
		});
		classButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		classButton.setBounds(89, 95, 153, 39);
		contentPane.add(classButton);
		
		JButton libraryButton = new JButton("\u56FE\u4E66\u9986");
		libraryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		libraryButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		libraryButton.setBounds(347, 95, 153, 39);
		contentPane.add(libraryButton);
		
		JButton courseButton = new JButton("\u9009\u8BFE");
		courseButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		courseButton.setBounds(89, 172, 153, 39);
		contentPane.add(courseButton);
		
		JButton dormButton = new JButton("\u5BBF\u820D");
		dormButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		dormButton.setBounds(347, 172, 153, 39);
		contentPane.add(dormButton);
		
		JButton shopButton = new JButton("\u5546\u5E97");
		shopButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		shopButton.setBounds(89, 256, 153, 39);
		contentPane.add(shopButton);
		
		JButton exitButton = new JButton("\u9000\u51FA");

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
}
