package seu.list.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import seu.list.client.bz.Client;
import seu.list.client.bz.ClientMainFrame;
import seu.list.client.test.MainTest;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.ModuleType;
import seu.list.common.Student;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

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
	private String PWD;
	private String ID;
	private Student thisStu = null;

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
	public ClassStudentClient(String id, String pwd) {
		PWD = pwd;
		ID = id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 441);
		contentPane = new JPanel();
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
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 23));
		
		JLabel lblNewLabel_1 = new JLabel("姓名");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_2 = new JLabel("学号");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_3 = new JLabel("专业");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_4 = new JLabel("班级");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_5 = new JLabel("老师");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_6 = new JLabel("性别");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_7 = new JLabel("籍贯");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_8 = new JLabel("政治面貌");
		lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_9 = new JLabel("联系电话");
		lblNewLabel_9.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_10 = new JLabel("账户余额");
		lblNewLabel_10.setFont(new Font("宋体", Font.PLAIN, 20));
		
		name = new JTextField();
		name.setText("null");
		name.setEditable(false);
		name.setFont(new Font("宋体", Font.PLAIN, 15));
		name.setColumns(10);
		name.setText(thisStu.getStudentName());
		
		studentid = new JTextField();
		studentid.setEditable(false);
		studentid.setFont(new Font("宋体", Font.PLAIN, 15));
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
		classid.setFont(new Font("宋体", Font.PLAIN, 15));
		classid.setColumns(10);
		classid.setText(thisStu.getClassid());
		
		teacherid = new JTextField();
		teacherid.setText("null");
		teacherid.setEditable(false);
		teacherid.setFont(new Font("宋体", Font.PLAIN, 15));
		teacherid.setColumns(10);
		teacherid.setText(thisStu.getTeacherid());
		
		phone = new JTextField();
		phone.setText("null");
		phone.setEditable(false);
		phone.setFont(new Font("宋体", Font.PLAIN, 15));
		phone.setColumns(10);
		phone.setText(thisStu.getStudentphone());
		
		credit = new JTextField();
		credit.setEditable(false);
		credit.setFont(new Font("宋体", Font.PLAIN, 15));
		credit.setColumns(10);
		credit.setText("" + thisStu.getStudentcredit());
		Money = thisStu.getStudentcredit();
		
		origion = new JTextField();
		origion.setText("null");
		origion.setEditable(false);
		origion.setFont(new Font("宋体", Font.PLAIN, 15));
		origion.setColumns(10);
		origion.setText(thisStu.getStudentorigion());
		
		final JComboBox gender = new JComboBox();
		gender.setEnabled(false);
		gender.setFont(new Font("宋体", Font.PLAIN, 15));
		gender.addItem("男");
		gender.addItem("女");
		
		gender.setToolTipText("");
		
		final JComboBox status = new JComboBox();
		status.setEnabled(false);
		status.setFont(new Font("宋体", Font.PLAIN, 15));
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
		
		String statusmy = thisStu.getStudentstatus().replaceAll("\\p{C}", "");
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
		
		JLabel lblNewLabel_11 = new JLabel("元");
		lblNewLabel_11.setFont(new Font("宋体", Font.PLAIN, 20));
		
		final JButton modifybutton = new JButton("修改");
		modifybutton.setFont(new Font("新宋体", Font.PLAIN, 15));
		JButton investbutton = new JButton("充值");
		investbutton.setFont(new Font("宋体", Font.PLAIN, 15));
		
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
		exitbutton.setFont(new Font("新宋体", Font.PLAIN, 15));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(168)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(10)
												.addComponent(lblNewLabel_1))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addContainerGap()
												.addComponent(lblNewLabel_2))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addContainerGap()
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
													.addComponent(lblNewLabel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(lblNewLabel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
											.addComponent(name, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
											.addComponent(studentid)
											.addComponent(major)
											.addComponent(classid)))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblNewLabel_5)
										.addGap(18)
										.addComponent(teacherid)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(54)
									.addComponent(modifybutton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(investbutton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNewLabel_8, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
									.addComponent(lblNewLabel_9, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
									.addComponent(lblNewLabel_10, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(lblNewLabel_7, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblNewLabel_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED))))))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(credit, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
									.addGap(14))
								.addComponent(gender, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(phone, Alignment.LEADING)
									.addComponent(status, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(origion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addComponent(exitbutton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_6)
								.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_7)
								.addComponent(studentid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(gender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(origion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_3)
							.addComponent(major, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_8)
							.addComponent(status, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(lblNewLabel_9)
						.addComponent(classid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_10)
						.addComponent(teacherid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(credit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5)
						.addComponent(lblNewLabel_11))
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(modifybutton)
						.addComponent(exitbutton)
						.addComponent(investbutton))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
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
	}
	public void setinvestframe() {
		this.setEnabled(false);
		this.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
		ClassStudentForInvest frame = new ClassStudentForInvest(this, Money, ID, PWD);
		frame.setVisible(true);
	}
	public void update(Double temp) {
		credit.setText("" + temp);
	}
	void close() {
		this.dispose();
		MainTest frame = new MainTest();
		frame.setVisible(true);
	}
}
