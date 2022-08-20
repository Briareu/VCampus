package seu.list.client.view;




import seu.list.client.bz.Client;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.ModuleType;
import seu.list.common.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class ClientRegisterFrame extends JDialog implements ActionListener{
	private Socket socket;
	private JTextField jtf_id;
	private JTextField jtf_name;
	private JPasswordField jtf_pwd;
	private JTextField jtf_sex;
	private JTextField jtf_age;
	private JTextField jtf_grade;
	private JTextField jtf_money;
	private JTextField jtf_major;
	JComboBox comboBox = new JComboBox();
	String role;


	public ClientRegisterFrame(Socket socket){
		this.socket=socket;
		setForeground(SystemColor.inactiveCaption);
		setBackground(Color.WHITE);
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\rep\\VCampus\\VCampusClient\\src\\main\\resources\\image\\xiaobiao.jpg"));
		setBak();

		this.setSize(711,496);
		this.setTitle("新用户注册");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		getContentPane().setLayout(null);

		JLabel JLabel9 = new JLabel("\u4E13\u4E1A\uFF1A");
		JLabel9.setFont(new Font("华文楷体", Font.PLAIN, 20));
		JLabel9.setBounds(364, 139, 75, 21);
		getContentPane().add(JLabel9);

		JButton register = new JButton("\u786E\u5B9A");

		register.addActionListener(this);
		register.setActionCommand("register");
		register.setFont(new Font("华文楷体", Font.PLAIN, 20));
		register.setBounds(304, 391, 75, 27);
		getContentPane().add(register);//ע�ᰴť

		JLabel JLabel8 = new JLabel("\u521D\u59CB\u91D1\u989D\uFF1A");
		JLabel8.setFont(new Font("华文楷体", Font.PLAIN, 20));
		JLabel8.setBounds(364, 299, 111, 21);
		getContentPane().add(JLabel8);

		JLabel JLabel7 = new JLabel("\u8EAB\u4EFD\uFF1A");
		JLabel7.setFont(new Font("华文楷体", Font.PLAIN, 20));
		JLabel7.setBounds(364, 205, 75, 21);
		getContentPane().add(JLabel7);

		JLabel JLabel6 = new JLabel("\u5E74\u7EA7\uFF1A");
		JLabel6.setFont(new Font("华文楷体", Font.PLAIN, 20));
		JLabel6.setBounds(364, 68, 75, 21);
		getContentPane().add(JLabel6);

		JLabel JLabel5 = new JLabel("\u5E74\u9F84\uFF1A");
		JLabel5.setFont(new Font("华文楷体", Font.PLAIN, 20));
		JLabel5.setBounds(59, 332, 75, 21);
		getContentPane().add(JLabel5);

		JLabel JLabel4 = new JLabel("\u6027\u522B\uFF1A");
		JLabel4.setFont(new Font("华文楷体", Font.PLAIN, 20));
		JLabel4.setBounds(59, 270, 75, 21);
		getContentPane().add(JLabel4);

		JLabel JLabel3 = new JLabel("\u5BC6\u7801\uFF1A");
		JLabel3.setForeground(Color.BLACK);
		JLabel3.setFont(new Font("华文楷体", Font.PLAIN, 20));
		JLabel3.setBackground(Color.WHITE);
		JLabel3.setBounds(59, 205, 75, 27);
		getContentPane().add(JLabel3);

		JLabel JLabel2 = new JLabel("\u59D3\u540D\uFF1A");
		JLabel2.setForeground(Color.BLACK);
		JLabel2.setFont(new Font("华文楷体", Font.PLAIN, 20));
		JLabel2.setBackground(Color.BLACK);
		JLabel2.setBounds(59, 136, 75, 27);
		getContentPane().add(JLabel2);

		JLabel JLabel1 = new JLabel("ID:");
		JLabel1.setFont(new Font("华文楷体", Font.PLAIN, 20));
		JLabel1.setForeground(Color.BLACK);
		JLabel1.setBackground(Color.BLACK);
		JLabel1.setBounds(59, 68, 75, 27);
		getContentPane().add(JLabel1);

		JLabel JLabel0 = new JLabel("\u9762\u677F");
		JLabel0.setFont(new Font("华文楷体", Font.PLAIN, 21));
		JLabel0.setForeground(SystemColor.activeCaption);
		JLabel0.setLabelFor(this);
		JLabel0.setBounds(-175, -149, 1054, 774);
		getContentPane().add(JLabel0);

		jtf_name = new JTextField();
		jtf_name.setForeground(Color.BLACK);
		jtf_name.setFont(new Font("宋体", Font.PLAIN, 17));
		jtf_name.setColumns(12);
		jtf_name.setBackground(Color.WHITE);
		jtf_name.setBounds(123, 138, 101, 27);
		getContentPane().add(jtf_name);

		jtf_pwd = new JPasswordField();
		jtf_pwd.setBackground(Color.white);
		jtf_pwd.setBounds(123, 205, 101, 27);
		getContentPane().add(jtf_pwd);

		jtf_sex = new JTextField();
		jtf_sex.setBounds(123, 270, 101, 27);
		getContentPane().add(jtf_sex);
		jtf_sex.setColumns(10);

		jtf_age = new JTextField();
		jtf_age.setColumns(10);
		jtf_age.setBounds(123, 332, 101, 24);
		getContentPane().add(jtf_age);

		jtf_grade = new JTextField();
		jtf_grade.setColumns(10);
		jtf_grade.setBounds(427, 68, 111, 27);
		getContentPane().add(jtf_grade);

		jtf_money = new JTextField();
		jtf_money.setColumns(10);
		jtf_money.setBounds(427, 332, 139, 27);
		getContentPane().add(jtf_money);

		jtf_id = new JTextField();
		jtf_id.setFont(new Font("宋体", Font.PLAIN, 17));
		jtf_id.setBackground(Color.WHITE);
		jtf_id.setForeground(Color.BLACK);
		jtf_id.setBounds(123, 68, 101, 25);
		getContentPane().add(jtf_id);
		jtf_id.setColumns(12);

		//JComboBox comboBox = new JComboBox();
		comboBox.setForeground(Color.BLACK);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u5B66\u751F", "\u8001\u5E08", "\u5546\u5E97\u7BA1\u7406\u5458", "\u56FE\u4E66\u9986\u7BA1\u7406\u5458"}));
		comboBox.setFont(new Font("华文楷体", Font.PLAIN, 20));
		comboBox.setBounds(433, 242, 133, 33);
		getContentPane().add(comboBox);

		jtf_major = new JTextField();
		jtf_major.setColumns(10);
		jtf_major.setBounds(427, 136, 111, 27);
		getContentPane().add(jtf_major);
		JPanel jp=(JPanel) getContentPane();
		jp.setOpaque(false);

	}

	private void setBak() {
		ImageIcon img = new ImageIcon("D:\\rep\\VCampus\\VCampusClient\\src\\main\\resources\\image\\registerback.jpg");
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 10, img.getIconWidth(), img.getIconHeight());
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (comboBox.getSelectedItem() =="学生")
		{role="0";}
		if (comboBox.getSelectedItem() =="管理员")
		{role="1";}


		User user=new User();
		user.setId(jtf_id.getText());
		user.setAge(jtf_age.getText());
		user.setGrade(jtf_grade.getText());
		user.setMajor(jtf_major.getText());
		user.setMoney(jtf_money.getText());
		user.setName(jtf_name.getName());
		user.setPwd(jtf_pwd.getText());
		user.setSex(jtf_sex.getText());
		user.setRole(role);
		Message clientreq=new Message();
		clientreq.setModuleType(ModuleType.User);
		clientreq.setMessageType(MessageType.REQ_REGISTER);
		clientreq.setContent(user.getContent());
		Client client=new Client(this.socket);
		Message rec=client.sendRequestToServer(clientreq);
		int sign=rec.getUserType();

		if(e.getActionCommand().equals("register"))
		{
			//System.out.print(ccs.sign);
			//返回的权限

			if(sign==3)
			{
				JOptionPane.showMessageDialog(null,"您已经注册，请不要重复","错误",JOptionPane.ERROR_MESSAGE);
			}
			else if(sign==0||sign==1)
			{
				JOptionPane.showMessageDialog(null,"同学，恭喜注册成功","提示",JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null,"注册失败","错误",JOptionPane.ERROR_MESSAGE);
			}
		}
	}


}