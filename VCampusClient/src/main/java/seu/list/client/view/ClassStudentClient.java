package seu.list.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the frame.
	 */
	public ClassStudentClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
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
		name.setEditable(false);
		name.setFont(new Font("宋体", Font.PLAIN, 15));
		name.setColumns(10);
		
		studentid = new JTextField();
		studentid.setEditable(false);
		studentid.setFont(new Font("宋体", Font.PLAIN, 15));
		studentid.setColumns(10);
		
		major = new JTextField();
		major.setEditable(false);
		major.setFont(new Font("宋体", Font.PLAIN, 15));
		major.setColumns(10);
		
		classid = new JTextField();
		classid.setEditable(false);
		classid.setFont(new Font("宋体", Font.PLAIN, 15));
		classid.setColumns(10);
		
		teacherid = new JTextField();
		teacherid.setEditable(false);
		teacherid.setFont(new Font("宋体", Font.PLAIN, 15));
		teacherid.setColumns(10);
		
		phone = new JTextField();
		phone.setEditable(false);
		phone.setFont(new Font("宋体", Font.PLAIN, 15));
		phone.setColumns(10);
		
		credit = new JTextField();
		credit.setEditable(false);
		credit.setFont(new Font("宋体", Font.PLAIN, 15));
		credit.setColumns(10);
		
		origion = new JTextField();
		origion.setEditable(false);
		origion.setFont(new Font("宋体", Font.PLAIN, 15));
		origion.setColumns(10);
		
		final JComboBox gender = new JComboBox();
		gender.setEnabled(false);
		gender.setFont(new Font("宋体", Font.PLAIN, 15));
		gender.addItem("未知");
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
		
		JLabel lblNewLabel_11 = new JLabel("元");
		lblNewLabel_11.setFont(new Font("宋体", Font.PLAIN, 20));
		
		final JButton modifybutton = new JButton("修改");
		modifybutton.setFont(new Font("新宋体", Font.PLAIN, 15));
		JButton investbutton = new JButton("充值");
		investbutton.setFont(new Font("宋体", Font.PLAIN, 15));
		
		modifybutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modifybutton.getText().compareTo("修改") == 0) {
					if(credit.isEditable() == true) {
						//alert1
						JOptionPane.showMessageDialog(null,"请先完成充值操作","提示",JOptionPane.WARNING_MESSAGE);
					}
					else {
						//turn to modify
						modifybutton.setText("保存");
						name.setEditable(true);
						studentid.setEditable(true);
						major.setEditable(true);
						classid.setEditable(true);
						teacherid.setEditable(true);
						origion.setEditable(true);
						phone.setEditable(true);
						status.setEnabled(true);
						gender.setEnabled(true);
					}
				}
				else {
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
		});
		
		investbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String info = JOptionPane.showInputDialog(null, "请输入充值金额", "充值", JOptionPane.WARNING_MESSAGE);
				if(credit.getText().length() == 0) {
					credit.setText(info);
				}
				else {
					credit.setText(String.valueOf(Integer.valueOf(info) + Integer.valueOf(credit.getText())));
				}
			}
		});
		
		JButton exitbutton = new JButton("退出");
		exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modifybutton.getText().compareTo("修改") == 0) {
					if(credit.isEditable() == true) {
						//finish the invest
						credit.setEditable(false);
					}
					else {
						//exit
					}
				}
				else {
					//exit without saving
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
	void close() {
		this.dispose();
	}
}
