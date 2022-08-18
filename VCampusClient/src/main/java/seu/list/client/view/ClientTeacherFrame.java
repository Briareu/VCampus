package seu.list.client.view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ClientTeacherFrame extends JFrame implements ActionListener {
	JPanel jp1,jp2;
	JButton courseManage,Quit;
	JLabel jl1,jl2,jl3;
	String number;
	Socket socket;//传送数据
	public ClientTeacherFrame(String number, Socket socket) throws ClassNotFoundException, SQLException,IOException, ClassNotFoundException {
		this.socket = socket;
		this.number = number;

		setBak(); //调用背景方法
		Container c = getContentPane(); //获取JFrame面板

		//jp1及jp1上逐渐的初始化和添加
		jp1 = new JPanel();
		jp1.setOpaque(false);
		jl1 = new JLabel("编号："+number);
		jl2 = new JLabel("权限：教师");

		jp1.add(jl1);
		jp1.add(jl2);

		//jp1及jp1上逐渐的初始化和添加
		jp2 = new JPanel();
		jp2.setOpaque(false);
		jl3 = new JLabel("您好！欢迎登陆教师系统！");

		//button初始化和注册监听
		courseManage = new JButton("课程信息管理");
		courseManage.addActionListener(this);
		Quit = new JButton("退出");
		Quit.addActionListener(this);

		jl3.setBounds(20,20,200,15);
		courseManage.setBounds(143, 67, 159, 40);
		Quit.setBounds(143, 131, 159, 40);

		jp2.add(jl3);
		jp2.add(courseManage);
		jp2.add(Quit);
		jp2.setLayout(null);

		//添加jp1和jp2
		c.add(jp1,BorderLayout.NORTH);
		c.add(jp2,BorderLayout.CENTER);

		this.setSize(461, 286);
		this.setTitle("教师系统");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	private void setBak() {
		// TODO Auto-generated method stub
		((JPanel)this.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("image/");
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(-110, -80, img.getIconWidth(), img.getIconHeight());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == courseManage)
		{
			try {
				ClientCourseFrame ccf = new ClientCourseFrame(this.number,this.socket);
			} catch (ClassNotFoundException | SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(arg0.getSource() == Quit)
		{
			System.exit(0);
		}

	}
}

