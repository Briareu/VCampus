package seu.list.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.ScrollPane;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import seu.list.client.bz.Client;
import seu.list.client.bz.ClientMainFrame;
import seu.list.common.ClassManage;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.ModuleType;
import seu.list.common.Student;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClassAdminForAdd extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassAdminForAdd frame = new ClassAdminForAdd();
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
	public ClassAdminForAdd(final ClassAdminClient cac) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 286);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton Commitbtn = new JButton("确定");

		Commitbtn.setFont(new Font("宋体", Font.PLAIN, 15));

		JButton exitbtn = new JButton("返回");

		exitbtn.setFont(new Font("宋体", Font.PLAIN, 15));

		JComboBox selectmode = new JComboBox();
		selectmode.addItem("学生");
		selectmode.addItem("班级");

		JLabel lblNewLabel = new JLabel("模式");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(39)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(selectmode, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(49, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(102)
					.addComponent(Commitbtn, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
					.addComponent(exitbtn, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(102))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(54)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(49, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(selectmode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(34)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Commitbtn)
						.addComponent(exitbtn))
					.addGap(47))
		);

		table = new JTable();

		if((selectmode.getSelectedItem().toString()).equalsIgnoreCase("学生")) {
			table.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null, null},
					},
					new String[] {
						"\u73ED\u7EA7", "\u5B66\u53F7", "\u59D3\u540D", "\u8054\u7CFB\u7535\u8BDD"
					}
				));
		}
		else {
			table.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null, null},
					},
					new String[] {
						"\u73ED\u7EA7", "\u8001\u5E08", "\u4E13\u4E1A"
					}
				));
		}

		selectmode.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if((selectmode.getSelectedItem().toString()).equalsIgnoreCase("学生")) {
					table.setModel(new DefaultTableModel(
							new Object[][] {
								{null, null, null, null},
							},
							new String[] {
								"\u73ED\u7EA7", "\u5B66\u53F7", "\u59D3\u540D", "\u8054\u7CFB\u7535\u8BDD"
							}
						));
				}
				else {
					table.setModel(new DefaultTableModel(
							new Object[][] {
								{null, null, null, null},
							},
							new String[] {
								"\u73ED\u7EA7", "\u8001\u5E08", "\u4E13\u4E1A"
							}
						));
				}
			}

		});

		Commitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((selectmode.getSelectedItem().toString()).equalsIgnoreCase("学生")) {
					//班级，学号， 姓名，电话
					Student stu = new Student();
					stu.setClassid(table.getValueAt(1, 0).toString());
					stu.setStudentid(table.getValueAt(1, 1).toString());
					stu.setStudentName(table.getValueAt(1, 2).toString());
					stu.setStudentphone(table.getValueAt(1, 3).toString());
					if(stu.getClassid().length()==0 ||  stu.getStudentid().length()==0 || stu.getStudentName().length()==0 || stu.getStudentphone().length() == 0) {
						JOptionPane.showMessageDialog(null, "请完善学生信息！", "提示", JOptionPane.WARNING_MESSAGE);
					}
					else if(stu.getStudentphone().length() != 11) {
						JOptionPane.showMessageDialog(null, "请正确填写电话号码（11位）！", "提示", JOptionPane.WARNING_MESSAGE);
					}
					else {
						//add
						Message mes = new Message();
						mes.setModuleType(ModuleType.Student);
						mes.setMessageType(MessageType.ClassAdminAdd);
						mes.setData(stu);

						Client client = new Client(ClientMainFrame.socket);

						Message serverResponse = new Message();
						serverResponse = client.sendRequestToServer(mes);
						int res = (int)serverResponse.getData();
						System.out.println("Add Student Confirmed!");
					}
				}
				else {
					//班级， 老师， 专业
					ClassManage clss = new ClassManage();
					clss.setClassID(table.getValueAt(1, 0).toString());
					clss.setMajor(table.getValueAt(1, 1).toString());
					clss.setTeacherID(table.getValueAt(1, 2).toString());
					if(clss.getClassID().length()==0 || clss.getMajor().length()==0 || clss.getTeacherID().length()==0) {
						JOptionPane.showMessageDialog(null, "请完善学生信息！", "提示", JOptionPane.WARNING_MESSAGE);
					}
					else {
						//add
					}
				}
			}
		});

		table.setRowHeight(25);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);

		exitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cac.setEnabled(true);
				close();
			}
		});
	}

	void close() {
		this.dispose();
	}
}
