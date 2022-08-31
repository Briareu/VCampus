package seu.list.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import seu.list.client.bz.Client;
import seu.list.client.bz.ClientMainFrame;
//import seu.list.client.test.MainTest;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.ModuleType;
import seu.list.common.Student;

import javax.swing.JLabel;
import javax.print.DocFlavor.URL;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ClassStudentClient extends JFrame {


	private JPanel contentPane;
	private JTextField name;
	private JTextField studentid;
	private JTextField major;
	private JTextField classid;
	private JTextField teacherid;
	private JTextField phone;
	private JTextField credit;
	private JTextField origion;
	private Double Money;
	private String Name;
	private String PWD;
	private String ID;
	private Student thisStu = null;
	private String statusmy = null;
	private JLabel lblNewLabel_1;
	private JLabel userimage = null;
	private MainMenu Mainmenu = null;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassStudentClient frame = new ClassStudentClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public ClassStudentClient(String id, String pwd, MainMenu mainmenu) {
		this.Mainmenu = mainmenu;
		this.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		this.setBounds(100, 100, 719, 570);
		
		PWD = pwd;
		ID = id;

		setTitle("学生个人信息管理界面");
		this.setDefaultCloseOperation(2);
		setBounds(100, 100, 719, 570);
		contentPane = new JPanel();
		contentPane.setVisible(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		Vector<Student> StuAll = new Vector<Student>();
		Message mes = new Message();
		mes.setModuleType(ModuleType.Student);
		mes.setMessageType(MessageType.ClassAdminGetAll);
		List<Object> sendData = new ArrayList<Object>();
		mes.setData(sendData);

		Client client = new Client(ClientMainFrame.socket);

		Message serverResponse = new Message();
		serverResponse = client.sendRequestToServer(mes);
		StuAll = (Vector<Student>) serverResponse.getData();
		
		thisStu = new Student();
		
		int studenttemp = 0;
		while(studenttemp < StuAll.size()) {
			String tempid = StuAll.get(studenttemp).getStudentid();
			id.replaceAll("\\p{C}", "");
			tempid.replaceAll("\\p{C}", "");
			if(tempid.equals(id)) {
				thisStu = StuAll.get(studenttemp);
				break;
			}
			studenttemp++;
		}
		
		JLabel lblNewLabel = new JLabel("个人信息管理");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 27));
		
		JLabel lblNewLabel_1 = new JLabel("姓名");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 22));
		
		JLabel lblNewLabel_2 = new JLabel("学号");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 22));
		
		JLabel lblNewLabel_3 = new JLabel("专业");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 22));
		
		JLabel lblNewLabel_4 = new JLabel("班级");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 22));
		
		JLabel lblNewLabel_5 = new JLabel("老师");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 22));
		
		JLabel lblNewLabel_6 = new JLabel("性别");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 22));
		
		JLabel lblNewLabel_7 = new JLabel("籍贯");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 22));
		
		JLabel lblNewLabel_8 = new JLabel("政治面貌");
		lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 22));
		
		JLabel lblNewLabel_10 = new JLabel("账户余额");
		lblNewLabel_10.setFont(new Font("宋体", Font.PLAIN, 22));
		
		name = new JTextField();
		name.setText("null");
		name.setEditable(false);
		name.setFont(new Font("宋体", Font.PLAIN, 18));
		name.setColumns(10);
		name.setText(thisStu.getStudentName());
		Name = thisStu.getStudentName();
		
		studentid = new JTextField();
		studentid.setEditable(false);
		studentid.setFont(new Font("宋体", Font.PLAIN, 18));
		studentid.setColumns(10);
		studentid.setText("null");
		studentid.setText(id);
		
		major = new JTextField();
		major.setText("null");
		major.setEditable(false);
		major.setFont(new Font("宋体", Font.PLAIN, 15));
		major.setColumns(10);
		major.setText(thisStu.getMajor());
		
		classid = new JTextField();
		classid.setText("null");
		classid.setEditable(false);
		classid.setFont(new Font("宋体", Font.PLAIN, 18));
		classid.setColumns(10);
		classid.setText(thisStu.getClassid());
		
		teacherid = new JTextField();
		teacherid.setText("null");
		teacherid.setEditable(false);
		teacherid.setFont(new Font("宋体", Font.PLAIN, 18));
		teacherid.setColumns(10);
		teacherid.setText(thisStu.getTeacherid());
		
		phone = new JTextField();
		phone.setText("null");
		phone.setEditable(false);
		phone.setFont(new Font("宋体", Font.PLAIN, 18));
		phone.setColumns(10);
		phone.setText(thisStu.getStudentphone());
		
		DecimalFormat df = new DecimalFormat("0.00");
		credit = new JTextField();
		credit.setEditable(false);
		credit.setFont(new Font("宋体", Font.PLAIN, 18));
		credit.setColumns(10);
		credit.setText("" + df.format(thisStu.getStudentcredit()));
		Money = thisStu.getStudentcredit();
		
		origion = new JTextField();
		origion.setText("null");
		origion.setEditable(false);
		origion.setFont(new Font("宋体", Font.PLAIN, 15));
		origion.setColumns(10);
		origion.setText(thisStu.getStudentorigion());
		
		final JComboBox gender = new JComboBox();
		gender.setEnabled(false);
		gender.setFont(new Font("宋体", Font.PLAIN, 16));
		gender.addItem("男");
		gender.addItem("女");
		
		if(thisStu.getStudentgender() == false) {
			gender.setSelectedIndex(1);
		}
		
		gender.setToolTipText("");
		
		final JComboBox status = new JComboBox();
		status.setEnabled(false);
		status.setFont(new Font("宋体", Font.PLAIN, 16));
		status.addItem("群众");
		status.addItem("共青团员");
		status.addItem("中共预备党员");
		status.addItem("中共党员");
		status.addItem("民革党员");
		status.addItem("民盟盟员");
		status.addItem("民建会员");
		status.addItem("民进会员");
		status.addItem("农工党党员");
		status.addItem("致公党党员");
		status.addItem("九三学社社员");
		status.addItem("台盟盟员");
		status.addItem("无党派人士");
		
		
		if(thisStu.getStudentstatus() != null) {
			statusmy = thisStu.getStudentstatus().replaceAll("\\p{C}", "");
			if(statusmy.length() != 0) {
				switch(statusmy) {
				case "群众":
				{
					status.setSelectedIndex(0);
				}
				break;
				case "共青团员":
				{
					status.setSelectedIndex(1);
				}
				break;
				case "中共预备党员":
				{
					status.setSelectedIndex(2);
				}
				break;
				case "中共党员":
				{
					status.setSelectedIndex(3);
				}
				break;
				case "民革党员":
				{
					status.setSelectedIndex(4);
				}
				break;
				case "民盟盟员":
				{
					status.setSelectedIndex(5);
				}
				break;
				case "民建会员":
				{
					status.setSelectedIndex(6);
				}
				break;
				case "民进会员":
				{
					status.setSelectedIndex(7);
				}
				break;
				case "农工党党员":
				{
					status.setSelectedIndex(8);
				}
				break;
				case "致公党党员":
				{
					status.setSelectedIndex(9);
				}
				break;
				case "九三学社社员":
				{
					status.setSelectedIndex(10);
				}
				break;
				case "台盟盟员":
				{
					status.setSelectedIndex(11);
				}
				break;
				case "无党派人士":
				{
					status.setSelectedIndex(12);
				}
				break;
				default:
					break;
				}
			}
		}
		
		
		JLabel lblNewLabel_11 = new JLabel("元");
		lblNewLabel_11.setFont(new Font("宋体", Font.PLAIN, 20));
		
		final JButton modifybutton = new JButton("修改");
		modifybutton.setFont(new Font("新宋体", Font.PLAIN, 20));
		JButton investbutton = new JButton("充值");
		investbutton.setFont(new Font("宋体", Font.PLAIN, 20));
		
		modifybutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modifybutton.getText().compareTo("修改") == 0) {
					//turn to modify
					
					//System.out.println(thisStu.getStudentName());
					//System.out.println(thisStu.getMajor());
					//System.out.println(thisStu.getStudentorigion());
					//System.out.println(thisStu.getStudentstatus());
					//System.out.println(thisStu.getStudentphone());
					
					System.out.println(statusmy);
					System.out.println(statusmy.equals("共青团员"));
					
					modifybutton.setText("保存");
					name.setEditable(true);
					
					studentid.setEditable(false);
					major.setEditable(false);
					classid.setEditable(false);
					teacherid.setEditable(false);
					
					origion.setEditable(true);
					phone.setEditable(true);
					status.setEnabled(true);
					gender.setEnabled(true);
				}
				else {
					if(name.getText().trim().equals("")||
							origion.getText().trim().equals("")||
							phone.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "请完善学生信息！", "提示", JOptionPane.WARNING_MESSAGE);
					}
					else {
						Student temp = new Student();
						temp.setClassid(classid.getText());
						temp.setMajor(major.getText());
						if(gender.getSelectedIndex() == 0) {
							temp.setStudentgender(true);//boy
						}else{
							temp.setStudentgender(false);//girl
						}
						temp.setStudentid(studentid.getText());
						temp.setStudentName(name.getText());
						temp.setStudentorigion(origion.getText());
						temp.setStudentphone(phone.getText());//remember to check
						temp.setTeacherid(teacherid.getText());
						switch (gender.getSelectedIndex()) {
						case 0:
							temp.setStudentgender(true);
							break;
						case 1:
							temp.setStudentgender(false);
							break;
						default:
							break;
						}
						switch(status.getSelectedIndex()) {
						case 0:
							temp.setStudentstatus("群众");
							break;
						case 1:
							temp.setStudentstatus("共青团员");
							break;
						case 2:
							temp.setStudentstatus("中共预备党员");
							break;
						case 3:
							temp.setStudentstatus("中共党员");
							break;
						case 4:
							temp.setStudentstatus("民革党员");
							break;
						case 5:
							temp.setStudentstatus("民盟盟员");
							break;
						case 6:
							temp.setStudentstatus("民建会员");
							break;
						case 7:
							temp.setStudentstatus("民进会员");
							break;
						case 8:
							temp.setStudentstatus("农工党党员");
							break;
						case 9:
							temp.setStudentstatus("致公党党员");
							break;
						case 10:
							temp.setStudentstatus("九三学社社员");
							break;
						case 11:
							temp.setStudentstatus("台盟盟员");
							break;
						case 12:
							temp.setStudentstatus("无党派人士");
							break;
						default:
							break;

						}
						
						if(temp.getStudentphone().length()!=11) {
							JOptionPane.showMessageDialog(null, "请正确填写电话号码（11位）！", "提示", JOptionPane.WARNING_MESSAGE);
						}else {
							save(temp);
							modifybutton.setText("修改");
							name.setEditable(false);
							studentid.setEditable(false);
							major.setEditable(false);
							classid.setEditable(false);
							teacherid.setEditable(false);
							origion.setEditable(false);
							phone.setEditable(false);
							status.setEnabled(false);
							gender.setEnabled(false);
						}
					}
				}
			}
		});
		
		investbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setinvestframe();
			}
		});
		
		JButton exitbutton = new JButton("退出");
		exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modifybutton.getText().compareTo("修改") == 0) {
					close();
				}
				else {
					//exit without saving
					int input = JOptionPane.showConfirmDialog(null, "确认放弃该操作吗？", "提示",JOptionPane.YES_NO_OPTION);
					if(input == 0)
					{
						modifybutton.setText("修改");
						name.setEditable(false);
						studentid.setEditable(false);
						major.setEditable(false);
						classid.setEditable(false);
						teacherid.setEditable(false);
						origion.setEditable(false);
						phone.setEditable(false);
						status.setEnabled(false);
						gender.setEnabled(false);
					}
				}
			}
		});
		exitbutton.setFont(new Font("新宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_9 = new JLabel("联系电话");
		lblNewLabel_9.setFont(new Font("宋体", Font.PLAIN, 22));
		
		userimage = new JLabel("User Image");
/*		userimage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateimage();
			}
		});*/
		userimage.setVerticalAlignment(SwingConstants.TOP);
		userimage.setIcon(new ImageIcon("src/main/resources/image/WindowsIcon/images/Stuinfo.png"));//\resources\image\WindowsIcon\images
//		userimage.setIcon(new ImageIcon(this.getClass().getResource("/resources/image/WindowsIcon/images/Stuinfo.png")));//\resources\image\WindowsIcon\images
		userimage.setBounds(0, 0, 150, 150);
		this.getContentPane().add(userimage);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(248)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(77, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblNewLabel_5)
											.addGap(38)
											.addComponent(teacherid, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblNewLabel_4)
											.addGap(38)
											.addComponent(classid, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)))
									.addGap(37))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(userimage, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
									.addGap(74)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_8)
								.addComponent(lblNewLabel_9)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(credit, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblNewLabel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_7, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(59)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(studentid)
										.addComponent(gender, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
										.addComponent(name, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
										.addComponent(origion, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGap(4)
													.addComponent(phone, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
												.addComponent(major)
												.addComponent(lblNewLabel_11, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGap(4)
													.addComponent(status, 0, 171, Short.MAX_VALUE)))
											.addPreferredGap(ComponentPlacement.RELATED))))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(42)
							.addComponent(modifybutton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(155)
							.addComponent(investbutton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(159)
							.addComponent(exitbutton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addGap(44))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(studentid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_6)
								.addComponent(gender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_7)
								.addComponent(origion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_3)
								.addComponent(major, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(14)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_8)
								.addComponent(status, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(userimage, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addGap(44)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(classid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_9)
							.addComponent(phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_5)
						.addComponent(teacherid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_10)
							.addComponent(credit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_11)))
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(modifybutton)
						.addComponent(investbutton)
						.addComponent(exitbutton))
					.addGap(43))
		);
		contentPane.setLayout(gl_contentPane);

		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("src/main/resources/image/student3.png"));
//		lblNewLabel_1.setIcon(new ImageIcon(this.getClass().getResource("/resources/image/student3.png")));
		lblNewLabel_1.setBounds(0, 0, 700, 623);
		this.getContentPane().add(lblNewLabel_1);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(2);
	}
	void save(Student temp) {
		Message mes = new Message();
		mes.setModuleType(ModuleType.Student);
		mes.setMessageType(MessageType.ClassAdminUpdate);
		List<Object> sendData = new ArrayList();
		sendData.add(15);//name, origin, phone, status, gender ---- id
		sendData.add(temp.getStudentName());
		sendData.add(temp.getStudentorigion());
		sendData.add(temp.getStudentphone());
		sendData.add(temp.getStudentstatus());
		sendData.add(temp.getStudentgender());
		sendData.add(temp.getStudentid());
		mes.setData(sendData);

		Client client = new Client(ClientMainFrame.socket);

		Message serverResponse = new Message();
		serverResponse = client.sendRequestToServer(mes);
		int res = (int) serverResponse.getData();
		
		this.Name = temp.getStudentName();
	}
	public void setinvestframe() {
		this.setEnabled(false);
		this.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
		ClassStudentForInvest frame = new ClassStudentForInvest(this, Money, ID, PWD);
		frame.setVisible(true);
	}
	public void update(Double temp) {
		DecimalFormat df = new DecimalFormat("0.00");
		credit.setText("" + df.format(temp));
		Money = temp;
	}
	
	
	/*
	public void updateimage() {
		this.setEnabled(false);
		this.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
		ClassStudentImage frame = new ClassStudentImage(this);
		frame.setVisible(true);
	}*/
	
	
/*	public void setImage(JButton btn) {
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "png");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(btn);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File[] arrfiles = chooser.getSelectedFiles();
			if(arrfiles == null || arrfiles.length == 0) {
				return;
			}
			
			File ff = chooser.getSelectedFile();
			String fileName = ff.getName();
			
			String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
			
			if(!(prefix.equals("jpg") || prefix.equals("png"))) {
				JOptionPane.showMessageDialog(null, "请选择.jpg或.png格式的图片！", "提示", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			FileInputStream input = null;
			FileOutputStream output = null;
			
			String path = "src/main/resources/image/user";
			try {
				for (File f : arrfiles) {
				     File dir = new File(path);
				     //目标文件夹 
				     File[] fs = dir.listFiles();
				     HashSet<String> set = new HashSet<String>();
				     for (File file : fs) {
				      set.add(file.getName());
				     }
				     //判断是否已有该文件
				     if (set.contains(f.getName())) {
				      JOptionPane.showMessageDialog(new JDialog(), f.getName() + ":该文件已存在！");
				      return;
				     }
				     
				     String absolutePath = chooser.getSelectedFile().getAbsolutePath();
				     
				     ImageIcon imageIcon = new ImageIcon(absolutePath);
				     
				     userimage.setIcon(imageIcon);
				     userimage.getIcon();
				     input = new FileInputStream(f);
				     byte[] buffer = new byte[1024];
				     File des = new File(path, f.getName());
				     output = new FileOutputStream(des);
				     int len = 0;
				     while(-1 != (len = input.read(buffer))) {
				    	 output.write(buffer, 0, len);
				     }
				     output.close();
				     input.close();
				}
				JOptionPane.showMessageDialog(null, "上传成功", "提示", JOptionPane.INFORMATION_MESSAGE);
				
				Message mes = new Message();
				mes.setModuleType(ModuleType.Student);
				mes.setMessageType(MessageType.ClassAdminUpdate);
				List<Object> sendData = new ArrayList();
				sendData.add(16);
				sendData.add(prefix);
				sendData.add(ID);
				mes.setData(sendData);
				Client client = new Client(ClientMainFrame.socket);
				Message serverResponse = new Message();
				serverResponse = client.sendRequestToServer(mes);
				int res = (int) serverResponse.getData();
				
			}catch(FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "上传失败", "提示", JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
			}catch(IOException e) {
				JOptionPane.showMessageDialog(null, "上传失败", "提示", JOptionPane.INFORMATION_MESSAGE);
				e.printStackTrace();
			}
		}
	}*/
	
	void close() {
		this.dispose();
		Mainmenu.set(Name, Money);
		//MainTest frame = new MainTest();
		//frame.setVisible(true);
	}
}
