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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import seu.list.client.bz.Client;
import seu.list.client.bz.ClientMainFrame;
//import seu.list.client.test.MainTest;
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
	private DefaultTableModel model1;
	private DefaultTableModel model2;
	private Vector<Student> StuAll = null;
	private Vector<ClassManage> ClssAll = null;
	private enum MODEL {
		ClASSADD, STUDENTADD
	};
	private MODEL now = MODEL.STUDENTADD;
	private ClassAdminClient CAC = null;
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
	public ClassAdminForAdd(final ClassAdminClient cac, Vector<Student> Stu, Vector<ClassManage> Clss) {
		CAC = cac;
		StuAll = Stu;
		ClssAll = Clss;

		setTitle("学生管理界面");
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
		
		model1 = new DefaultTableModel(new Object[][] {{null, null, null, null}},
				new String[] { "\u73ED\u7EA7", "\u5B66\u53F7", "\u59D3\u540D", "\u8054\u7CFB\u7535\u8BDD" });
		model2 = new DefaultTableModel(new Object[][] {{null, null, null, null}},
				new String[] { "\u73ED\u7EA7", "\u8001\u5E08", "\u4E13\u4E1A" });
		
		if((selectmode.getSelectedItem().toString()).equalsIgnoreCase("学生")) {
			table.setModel(model1);
			now = MODEL.STUDENTADD;
		}
		else {
			table.setModel(model2);
			now = MODEL.ClASSADD;
		}
		
		selectmode.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if((selectmode.getSelectedItem().toString()).equalsIgnoreCase("学生")) {
					table.setModel(model1);
					now = MODEL.STUDENTADD;
				}
				else {
					table.setModel(model2);
					now = MODEL.ClASSADD;
				}
			}
			
		});
		
		Commitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean Modified = false;
				if((selectmode.getSelectedItem().toString()).equalsIgnoreCase("学生")) {
					//班级，学号， 姓名，电话
					int clssid = 0;
					if(table.getValueAt(0, 0) == null ||
							table.getValueAt(0, 1) == null ||
							table.getValueAt(0, 2) == null ||
							table.getValueAt(0, 3) == null) {
						JOptionPane.showMessageDialog(null, "请完善学生信息！", "提示", JOptionPane.WARNING_MESSAGE);
					}
					else {
						Student stu = new Student();
						stu.setClassid(table.getValueAt(0, 0).toString());
						stu.setStudentid(table.getValueAt(0, 1).toString());
						stu.setStudentName(table.getValueAt(0, 2).toString());
						stu.setStudentphone(table.getValueAt(0, 3).toString());
						String newclssid = stu.getClassid().replaceAll("\\p{C}", "");
						String newid = stu.getStudentid().replaceAll("\\p{C}", "");
						String newname = stu.getStudentName().replaceAll("\\p{C}", "");
						String newphone = stu.getStudentphone().replaceAll("\\p{C}", "");
						
						if(newphone.length() != 11) {
							JOptionPane.showMessageDialog(null, "请正确填写电话号码（11位）！", "提示", JOptionPane.WARNING_MESSAGE);
						}else {

							//add
							Boolean flag = true;
							
							//check the id is unique
							int i = 0;
							while(i < StuAll.size()) {
								String tempid = StuAll.get(i).getStudentid();
								tempid.replaceAll("\\p{C}", "");
								if(tempid.equals(newid)) {
									flag = false;
									break;
								}
								i++;
							}
							
							if(flag) {
								flag = false;
								i = 0;
								
								//check the class exist
								while(i < ClssAll.size()) {
									String tempclss = ClssAll.get(i).getClassID();
									tempclss.replaceAll("\\p{C}", "");
									if(tempclss.equals(newclssid)) {
										ClssAll.get(i).setClassSize(ClssAll.get(i).getClassSize() + 1);
										flag = true;
										clssid = i;
										break;
									}
									i++;
								}
								
								if(flag) {
									/*flag = true;
									int j = 0;
									System.out.println("check id is of class");
									//check whether the studentid is of the class
									while(j < (ClssAll.get(clssid).getClassID()).length()) {
										if(stu.getClassid().length() - 1 < j) {
											flag = false;
											break;
										}else if(stu.getClassid().charAt(j) != ClssAll.get(clssid).getClassID().charAt(j)) {
											flag = false;
											break;
										}else {
											j++;
										}
									}*/
									Modified = true;
									Message mes = new Message();
									mes.setModuleType(ModuleType.Student);
									mes.setMessageType(MessageType.ClassAdminAdd);
									stu.setMajor(ClssAll.get(clssid).getMajor());
									stu.setTeacherid(ClssAll.get(clssid).getTeacherID());
									mes.setData(stu);
									
									Client client = new Client(ClientMainFrame.socket);
									
									Message serverResponse = new Message();
									serverResponse = client.sendRequestToServer(mes);
									int res = (int)serverResponse.getData();
									System.out.println("Add Student Confirmed!");
									
									StuAll.add(stu);
									
									clear();
									/*
									if(flag) {
										Message mes = new Message();
										mes.setModuleType(ModuleType.Student);
										mes.setMessageType(MessageType.ClassAdminAdd);
										stu.setMajor(ClssAll.get(clssid).getMajor());
										stu.setTeacherid(ClssAll.get(clssid).getTeacherID());
										mes.setData(stu);
										
										Client client = new Client(ClientMainFrame.socket);
										
										Message serverResponse = new Message();
										serverResponse = client.sendRequestToServer(mes);
										int res = (int)serverResponse.getData();
										System.out.println("Add Student Confirmed!");
										
										StuAll.add(stu);
										
										clear();
									}else {
										int input = JOptionPane.showConfirmDialog(null, "该学号所属班级与填写班级不一致，请您确认执行该操作", "提示",JOptionPane.YES_NO_OPTION);
										if(input == 0) {//yes
											Message mes = new Message();
											mes.setModuleType(ModuleType.Student);
											mes.setMessageType(MessageType.ClassAdminAdd);
											stu.setMajor(ClssAll.get(clssid).getMajor());
											stu.setTeacherid(ClssAll.get(clssid).getTeacherID());
											mes.setData(stu);
											
											Client client = new Client(ClientMainFrame.socket);
											
											Message serverResponse = new Message();
											serverResponse = client.sendRequestToServer(mes);
											int res = (int)serverResponse.getData();
											System.out.println("Add Student Confirmed!");

											StuAll.add(stu);
											
											clear();
										}
									}*/
								}else {
									JOptionPane.showMessageDialog(null, "不存在该班级！", "提示", JOptionPane.WARNING_MESSAGE);
								}
								
							}else {
								JOptionPane.showMessageDialog(null, "该学号的学生已经存在！", "提示", JOptionPane.WARNING_MESSAGE);
							}
						
						}
					}
				}
				else {
					//班级， 老师， 专业
					if(table.getValueAt(0, 0) == null ||
							table.getValueAt(0, 1) == null ||
							table.getValueAt(0,  2) == null) {
						JOptionPane.showMessageDialog(null, "请完善班级信息！", "提示", JOptionPane.WARNING_MESSAGE);
					}else {
						ClassManage clss = new ClassManage();
						clss.setClassID(table.getValueAt(0, 0).toString());
						clss.setMajor(table.getValueAt(0, 1).toString());
						clss.setTeacherID(table.getValueAt(0, 2).toString());
						clss.setClassSize(0);
						String newclssid = clss.getClassID();
						newclssid.replaceAll("\\p{C}", "");
						String newteacher = clss.getTeacherID();
						newteacher.replaceAll("\\p{C}", "");
						String newmajor = clss.getMajor();
						newmajor.replaceAll("\\p{C}", "");
						

						//add
						Boolean flag = true;
						
						//check the id is unique
						int i = 0;
						while(i < ClssAll.size()) {
							String temp = ClssAll.get(i).getClassID();
							temp.replaceAll("\\p{C}", "");
							if(temp.equals(newclssid)) {
								flag = false;
								break;
							}
							i++;
						}

						if(flag) {
							Modified = true;
							Message mes = new Message();
							mes.setModuleType(ModuleType.Student);
							mes.setMessageType(MessageType.ClassAdd);
							mes.setData(clss);
							
							Client client = new Client(ClientMainFrame.socket);
							
							Message serverResponse = new Message();
							serverResponse = client.sendRequestToServer(mes);
							int res = (int)serverResponse.getData();
							System.out.println("Add Class Confirmed!");
							
							ClssAll.add(clss);
							clear();
						}else {
							JOptionPane.showMessageDialog(null, "该班级已经存在！", "提示", JOptionPane.WARNING_MESSAGE);
						}
					
					}
				}
				if(Modified) {
					JOptionPane.showMessageDialog(null, "增加成功", "提示", JOptionPane.WARNING_MESSAGE);
				}
				table.setEnabled(true);
			}
		});
		
		table.setRowHeight(25);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		System.out.println("Student Mange of Add frame load success");
		
		exitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cac.setEnabled(true);
				close();
			}
		});
	}
	void clear() {
		if(now == MODEL.STUDENTADD) {
			table.setModel(model1);
		}
		else {
			table.setModel(model2);
		}
	}
	
	void close() {
		CAC.setEnabled(true);
		CAC.updateFrame(StuAll, ClssAll);
		this.dispose();
	}
}
