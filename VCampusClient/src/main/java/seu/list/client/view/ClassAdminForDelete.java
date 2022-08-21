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
import seu.list.client.test.MainTest;
import seu.list.common.ClassManage;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.ModuleType;
import seu.list.common.Student;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ClassAdminForDelete extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField searchdata;
	private DefaultTableModel model1;
	private DefaultTableModel model2;
	private ClassAdminClient CAC = null;
	private Vector<Student> StuAll = null;
	private Vector<ClassManage> ClssAll = null;
	private Vector<Student> StudentTemp = null;
	private Vector<ClassManage> ClassTemp = null;
	private Vector<Integer> StudentIndex = null;
	private Vector<Integer> ClassIndex = null;

	private enum MODEL {
		CLASSDELETE, STUDENTDELTE, CLASSTEMP, STUDENTTEMP
	};

	private MODEL now = MODEL.STUDENTDELTE;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { ClassAdminForAdd frame = new
	 * ClassAdminForAdd(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public ClassAdminForDelete(final ClassAdminClient cac, Vector<Student> Stu, Vector<ClassManage> Clss) {
		CAC = cac;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 380);
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
		
		JComboBox searchbtn = new JComboBox();

		JLabel lblNewLabel = new JLabel("模式");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));

		JScrollPane scrollPane = new JScrollPane();

		searchdata = new JTextField();
		searchdata.setText("🔍");
		searchdata.setColumns(10);

		table = new JTable();
		table.setFont(new Font("Adobe 仿宋 Std R", Font.PLAIN, 12));
		scrollPane.setViewportView(table);

		model1 = new DefaultTableModel(new Object[][] {},
				new String[] { "\u73ED\u7EA7", "\u5B66\u53F7", "\u59D3\u540D", "\u8054\u7CFB\u7535\u8BDD" });
		model2 = new DefaultTableModel(new Object[][] {},
				new String[] { "\u73ED\u7EA7", "\u8001\u5E08", "\u4E13\u4E1A" });

		StuAll = Stu;
		ClssAll = Clss;

		if ((selectmode.getSelectedItem().toString()).equalsIgnoreCase("学生")) {
			table.setModel(model1);// student
			table.getColumnModel().getColumn(3).setPreferredWidth(144);
			getStudent();
			now = MODEL.STUDENTDELTE;
			searchbtn.addItem("全部");
			searchbtn.addItem("班级");
			searchbtn.addItem("学号");
			searchbtn.addItem("姓名");
		} else {
			table.setModel(model2);// class
			getClass_all();
			now = MODEL.CLASSDELETE;
			searchbtn.addItem("全部");
			searchbtn.addItem("班级");
			searchbtn.addItem("专业");
			searchbtn.addItem("老师");
		}

		selectmode.addItemListener(new ItemListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (selectmode.getSelectedIndex() == 0) {
					// student
					if(now == MODEL.STUDENTDELTE) {
						//empty do nothing
					}else {
						if(now == MODEL.CLASSDELETE || now == MODEL.CLASSTEMP) {
							Message mes = new Message();
							mes.setModuleType(ModuleType.Student);
							mes.setMessageType(MessageType.ClassAdminGetAll);
							List<Object> sendData = new ArrayList<Object>();
							mes.setData(sendData);

							Client client = new Client(ClientMainFrame.socket);

							Message serverResponse = new Message();
							serverResponse = client.sendRequestToServer(mes);
							StuAll = (Vector<Student>) serverResponse.getData();
						}
						while(model2.getRowCount() > 0) {
							model2.removeRow(model2.getRowCount() - 1);
							table.setModel(model2);
						}
						table.setModel(model1);
						getStudent();
						now = MODEL.STUDENTDELTE;
						searchbtn.removeItemAt(2);
						searchbtn.removeItemAt(2);
						searchbtn.addItem("学号");
						searchbtn.addItem("姓名");
						System.out.println("Model Change");
					}
				} else {
					// class modify
					if(now == MODEL.CLASSDELETE) {
						//empty body
					}else {
						while(model1.getRowCount() > 0) {
							//System.out.println(table.getRowCount() - 1);
							model1.removeRow(model1.getRowCount() - 1);
							table.setModel(model1);
						}
						table.setModel(model2);
						getClass_all();
						now = MODEL.CLASSDELETE;
						searchbtn.removeItemAt(2);
						searchbtn.removeItemAt(2);
						searchbtn.addItem("专业");
						searchbtn.addItem("老师");
						System.out.println("Model Change");
					}
				}
			}

		});

		Commitbtn.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
			public void actionPerformed(ActionEvent e) {
				//delete
				if(now == MODEL.STUDENTDELTE) {
					int target = table.getSelectedRow();
					if(target == -1) {
						JOptionPane.showMessageDialog(null, "请先选择要进行删除的学生！", "提示", JOptionPane.WARNING_MESSAGE);
					}else {
						int classtempforadd = 0;
						Boolean newclass = false;
						while(classtempforadd < ClssAll.size() && !newclass) {
							if(ClssAll.get(classtempforadd).getClassID().equals(table.getValueAt(target, 0))) {
								newclass = true;
								ClssAll.get(classtempforadd).setClassSize(ClssAll.get(classtempforadd).getClassSize() - 1);
							}
							classtempforadd++;
						}
						
						Message mes = new Message();
						mes.setModuleType(ModuleType.Student);
						mes.setMessageType(MessageType.ClassAdminDelete);
						List<Object> sendData = new ArrayList<Object>();
						sendData.add(0);
						sendData.add(StuAll.get(target).getStudentid());
						mes.setData(sendData);

						Client client = new Client(ClientMainFrame.socket);

						Message serverResponse = new Message();
						serverResponse = client.sendRequestToServer(mes);
						int res = (int) serverResponse.getData();
						if(res > 0) {
							JOptionPane.showMessageDialog(null, "完成删除！", "提示", JOptionPane.WARNING_MESSAGE);
							model1.removeRow(target);
							StuAll.remove(target);
							table.setModel(model1);
						}
					}
				}else if(now == MODEL.CLASSDELETE) {
					int target = table.getSelectedRow();
					if(target == -1) {
						JOptionPane.showMessageDialog(null, "请先选择要进行删除的班级！", "提示", JOptionPane.WARNING_MESSAGE);
					}else {
						if(ClssAll.get(target).getClassSize() == 0) {
							Message mes = new Message();
							mes.setModuleType(ModuleType.Student);
							mes.setMessageType(MessageType.ClassDelete);
							String sendData = null;
							sendData = ClssAll.get(target).getClassID();
							mes.setData(sendData);

							Client client = new Client(ClientMainFrame.socket);

							Message serverResponse = new Message();
							serverResponse = client.sendRequestToServer(mes);
							int res = (int) serverResponse.getData();
							if(res > 0) {
								JOptionPane.showMessageDialog(null, "完成删除！", "提示", JOptionPane.WARNING_MESSAGE);
								model2.removeRow(target);
								ClssAll.remove(target);
								table.setModel(model2);
							}
						}else {
							//size != 0
							int input = JOptionPane.showConfirmDialog(null, "该操作同时会删除该班的学生信息，请您确认执行该操作", "提示",JOptionPane.YES_NO_OPTION);
							if(input == 0) {
								Message mes = new Message();
								mes.setModuleType(ModuleType.Student);
								mes.setMessageType(MessageType.ClassAdminDelete);
								List<Object> sendData = new ArrayList<Object>();
								sendData.add(1);
								sendData.add(ClssAll.get(target).getClassID());
								mes.setData(sendData);

								Client client = new Client(ClientMainFrame.socket);

								Message serverResponse = new Message();
								serverResponse = client.sendRequestToServer(mes);
								int res = (int) serverResponse.getData();
								
								mes = null;
								mes = new Message();
								mes.setModuleType(ModuleType.Student);
								mes.setMessageType(MessageType.ClassDelete);
								String send = null;
								send = ClssAll.get(target).getClassID();
								mes.setData(send);

								client = null;
								client = new Client(ClientMainFrame.socket);

								serverResponse = null;
								serverResponse = new Message();
								serverResponse = client.sendRequestToServer(mes);
								res = (int) serverResponse.getData();
								if(res > 0) {
									JOptionPane.showMessageDialog(null, "完成删除！", "提示", JOptionPane.WARNING_MESSAGE);
									model2.removeRow(target);
									ClssAll.remove(target);
									table.setModel(model2);
								}
							}
						}
					}
				}else if(now == MODEL.STUDENTTEMP) {
					int target = table.getSelectedRow();
					if(target == -1) {
						JOptionPane.showMessageDialog(null, "请先选择要进行删除的学生！", "提示", JOptionPane.WARNING_MESSAGE);
					}else {
						int classtempforadd = 0;
						Boolean newclass = false;
						while(classtempforadd < ClssAll.size() && !newclass) {
							if(ClssAll.get(classtempforadd).getClassID().equals(table.getValueAt(target, 0))) {
								newclass = true;
								ClssAll.get(classtempforadd).setClassSize(ClssAll.get(classtempforadd).getClassSize() - 1);
							}
							classtempforadd++;
						}
						
						Message mes = new Message();
						mes.setModuleType(ModuleType.Student);
						mes.setMessageType(MessageType.ClassAdminDelete);
						List<Object> sendData = new ArrayList<Object>();
						sendData.add(0);
						sendData.add(StuAll.get(target).getStudentid());
						mes.setData(sendData);

						Client client = new Client(ClientMainFrame.socket);

						Message serverResponse = new Message();
						serverResponse = client.sendRequestToServer(mes);
						int res = (int) serverResponse.getData();
						if(res > 0) {
							JOptionPane.showMessageDialog(null, "完成删除！", "提示", JOptionPane.WARNING_MESSAGE);
							model1.removeRow(target);
							StudentTemp.remove(target);
							StuAll.remove(StudentIndex.get(target));
							table.setModel(model1);
						}
					
					}
				}else if(now == MODEL.CLASSTEMP) {
					int target = table.getSelectedRow();
					if(target == -1) {
						JOptionPane.showMessageDialog(null, "请先选择要进行删除的班级！", "提示", JOptionPane.WARNING_MESSAGE);
					}else {
						if(ClassTemp.get(target).getClassSize() == 0) {
							Message mes = new Message();
							mes.setModuleType(ModuleType.Student);
							mes.setMessageType(MessageType.ClassDelete);
							String sendData = null;
							sendData = ClssAll.get(target).getClassID();
							mes.setData(sendData);

							Client client = new Client(ClientMainFrame.socket);

							Message serverResponse = new Message();
							serverResponse = client.sendRequestToServer(mes);
							int res = (int) serverResponse.getData();
							if(res > 0) {
								JOptionPane.showMessageDialog(null, "完成删除！", "提示", JOptionPane.WARNING_MESSAGE);
								model1.removeRow(target);
								ClassTemp.remove(target);
								Clss.remove(ClassIndex.get(target));
								table.setModel(model2);
							}
						}else {
							//size != 0
							int input = JOptionPane.showConfirmDialog(null, "该操作同时会删除该班的学生信息，请您确认执行该操作", "提示",JOptionPane.YES_NO_OPTION);
							if(input == 0) {
								Message mes = new Message();
								mes.setModuleType(ModuleType.Student);
								mes.setMessageType(MessageType.ClassAdminDelete);
								List<Object> sendData = new ArrayList<Object>();
								sendData.add(1);
								sendData.add(ClassTemp.get(target).getClassID());
								mes.setData(sendData);

								Client client = new Client(ClientMainFrame.socket);

								Message serverResponse = new Message();
								serverResponse = client.sendRequestToServer(mes);
								int res = (int) serverResponse.getData();
								
								mes = null;
								mes = new Message();
								mes.setModuleType(ModuleType.Student);
								mes.setMessageType(MessageType.ClassDelete);
								String send = null;
								send = ClssAll.get(target).getClassID();
								mes.setData(send);

								client = null;
								client = new Client(ClientMainFrame.socket);

								serverResponse = null;
								serverResponse = new Message();
								serverResponse = client.sendRequestToServer(mes);
								res = (int) serverResponse.getData();
								if(res > 0) {
									JOptionPane.showMessageDialog(null, "完成删除！", "提示", JOptionPane.WARNING_MESSAGE);
									model1.removeRow(target);
									ClassTemp.remove(target);
									Clss.remove(ClassIndex.get(target));
									table.setModel(model2);
								}
							}
						}
					
					}
				}//end
				table.setEnabled(true);
			}
		});

		table.setRowHeight(25);
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("查找");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.getCellEditor().stopCellEditing();
				table.setEnabled(false);
				if(now == MODEL.STUDENTDELTE || now == MODEL.STUDENTTEMP) {
					//search student
					switch(searchbtn.getSelectedIndex()) {
					case 0:
					{
						//all
						UpdateTable();
						now = MODEL.STUDENTDELTE;
					}
					break;
					case 1:
					{
						//class
						now = MODEL.STUDENTTEMP;
						
						StudentTemp = null;
						StudentTemp = new Vector<Student>();
						StudentIndex = null;
						StudentIndex = new Vector<Integer>();
						int i_search = 0;
						String sch = searchdata.getText();
						while(i_search < StuAll.size()) {
							//System.out.println(i_search);
							String test = StuAll.get(i_search).getClassid();
							test.replaceAll("\\p{C}", "");
							sch.replaceAll("\\p{C}", "");
							//System.out.println(test);
							//System.out.println(sch);
							//System.out.println(test.equals(sch));
							if(test.equals(sch)) {
								StudentTemp.add(StuAll.get(i_search));
								StudentIndex.add(i_search);
							}
							i_search++;
						}
						UpdateStudent(StudentTemp);
						System.out.println("model change commit");
					}
					break;
					case 2:
					{
						//student id
						now = MODEL.STUDENTTEMP;
						StudentTemp = null;
						StudentTemp = new Vector<Student>();
						StudentIndex = null;
						StudentIndex = new Vector<Integer>();
						int i_search = 0;
						String sch = searchdata.getText();
						while(i_search < StuAll.size()) {
							String test = StuAll.get(i_search).getStudentid();
							test.replaceAll("\\p{C}", "");
							sch.replaceAll("\\p{C}", "");
							if(test.equals(sch)) {
								StudentTemp.add(StuAll.get(i_search));
								StudentIndex.add(i_search);
							}
							i_search++;
						}
						UpdateStudent(StudentTemp);
						System.out.println("model change commit");
					}
					break;
					case 3:
					{
						//student name
						now = MODEL.STUDENTTEMP;
						StudentTemp = null;
						StudentTemp = new Vector<Student>();
						StudentIndex = null;
						StudentIndex = new Vector<Integer>();
						int i_search = 0;
						String sch = searchdata.getText();
						while(i_search < StuAll.size()) {
							String test = StuAll.get(i_search).getStudentName();
							test.replaceAll("\\p{C}", "");
							sch.replaceAll("\\p{C}", "");
							if(test.equals(sch)) {
								StudentTemp.add(StuAll.get(i_search));
								StudentIndex.add(i_search);
							}
							i_search++;
						}
						UpdateStudent(StudentTemp);
						System.out.println("model change commit");
					}
					break;
					default:
						break;
					}
				}//end of student searching
				else {
					//search class
					switch(searchbtn.getSelectedIndex()) {
					case 0:
					{
						//all
						UpdateTable();
						now = MODEL.CLASSDELETE;
					}
					break;
					case 1:
					{
						//class
						now = MODEL.CLASSTEMP;
						ClassTemp = null;
						ClassTemp = new Vector<ClassManage>();
						ClassIndex = null;
						ClassIndex = new Vector<Integer>();
						int i_search = 0;
						String sch = searchdata.getText();
						while(i_search < ClssAll.size()) {
							String test = ClssAll.get(i_search).getClassID();
							test.replaceAll("\\p{C}", "");
							sch.replaceAll("\\p{C}", "");
							System.out.println(test);
							System.out.println(sch);
							System.out.println(test.equals(sch));
							if(test.equals(sch)) {
								ClassTemp.add(ClssAll.get(i_search));
								ClassIndex.add(i_search);
							}
							i_search++;
						}
						UpdateClass(ClassTemp);
						System.out.println("model change commit");
					}
					break;
					case 2:
					{
						//major
						now = MODEL.CLASSTEMP;
						ClassTemp = null;
						ClassTemp = new Vector<ClassManage>();
						ClassIndex = null;
						ClassIndex = new Vector<Integer>();
						int i_search = 0;
						String sch = searchdata.getText();
						while(i_search < ClssAll.size()) {
							String test = ClssAll.get(i_search).getMajor();
							test.replaceAll("\\p{C}", "");
							sch.replaceAll("\\p{C}", "");
							if(test.equals(sch)) {
								ClassTemp.add(ClssAll.get(i_search));
								ClassIndex.add(i_search);
							}
							i_search++;
						}
						UpdateClass(ClassTemp);
						System.out.println("model change commit");
					}
					break;
					case 3:
					{
						//teacher
						now = MODEL.CLASSTEMP;
						ClassTemp = null;
						ClassTemp = new Vector<ClassManage>();
						ClassIndex = null;
						ClassIndex = new Vector<Integer>();
						int i_search = 0;
						String sch = searchdata.getText();
						while(i_search < ClssAll.size()) {
							String test = ClssAll.get(i_search).getTeacherID();
							test.replaceAll("\\p{C}", "");
							sch.replaceAll("\\p{C}", "");
							if(test.equals(sch)) {
								ClassTemp.add(ClssAll.get(i_search));
								ClassIndex.add(i_search);
							}
							i_search++;
						}
						UpdateClass(ClassTemp);
						System.out.println("model change commit");
					}
					break;
					default:
						break;
					}
				}//end of class searching
				table.setEnabled(true);
			}
		});

		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(39)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(selectmode, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(286)
							.addComponent(searchbtn, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(searchdata, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(54)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(100)
							.addComponent(Commitbtn, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(153)
							.addComponent(exitbtn, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(selectmode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(3)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(searchdata, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchbtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(Commitbtn)
						.addComponent(exitbtn)))
		);
		contentPane.setLayout(gl_contentPane);

		exitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cac.setEnabled(true);
				close();
			}
		});
	}
	
	private void UpdateTable() {
		if(now == MODEL.STUDENTDELTE) {
			// student
			while(model1.getRowCount() > 0) {
				//System.out.println(table.getRowCount() - 1);
				model1.removeRow(model1.getRowCount() - 1);
				table.setModel(model1);
			}
			table.setModel(model1);
			getStudent();
			now = MODEL.STUDENTDELTE;
		}else {// class modify
			while(model2.getRowCount() > 0) {
				//System.out.println(table.getRowCount() - 1);
				model2.removeRow(model2.getRowCount() - 1);
				table.setModel(model2);
			}
			table.setModel(model2);
			getClass_all();
			now = MODEL.CLASSDELETE;
		}
	}
	
	private void UpdateStudent(Vector<Student> temp) {
		// student
		while(model1.getRowCount() > 0) {
			//System.out.println(table.getRowCount() - 1);
			model1.removeRow(model1.getRowCount() - 1);
			table.setModel(model1);
		}
		table.setModel(model1);
		
		String[] arr = new String[4];
		for (int i = 0; i < temp.size(); i++) {
			// 班级 学号 姓名 电话
			arr[0] = temp.get(i).getClassid();
			arr[1] = temp.get(i).getStudentid();
			arr[2] = temp.get(i).getStudentName();
			arr[3] = temp.get(i).getStudentphone();

			model1.addRow(arr);
			table.setModel(model1);
		}
	}
	
	private void UpdateClass(Vector<ClassManage> temp) {
		while(model2.getRowCount() > 0) {
			//System.out.println(table.getRowCount() - 1);
			model2.removeRow(model2.getRowCount() - 1);
			table.setModel(model2);
		}
		table.setModel(model2);
		String[] arr = new String[3];
		for (int i = 0; i < temp.size(); i++) {
			// 班级， 老师， 专业
			arr[0] = temp.get(i).getClassID();
			arr[1] = temp.get(i).getTeacherID();
			arr[2] = temp.get(i).getMajor();

			model2.addRow(arr);
			table.setModel(model2);
		}
	}

	public void getClass_all() {
		// String get = serverresponse.getData().toString();
		// System.out.println(get);
		String[] arr = new String[3];
		for (int i = 0; i < ClssAll.size(); i++) {
			// 班级， 老师， 专业
			arr[0] = ClssAll.get(i).getClassID();
			arr[1] = ClssAll.get(i).getTeacherID();
			arr[2] = ClssAll.get(i).getMajor();

			model2.addRow(arr);
			table.setModel(model2);
		}
	}
	
	/*
	private void setStudent(ClassManage classtemp) {
		Message mes = new Message();
		mes.setModuleType(ModuleType.Student);
		mes.setMessageType(MessageType.ClassAdminUpdate);
		List<Object> sendData = new ArrayList();
		sendData.add(14);//teacher, major, class
		sendData.add(classtemp.getTeacherID());
		sendData.add(classtemp.getMajor());
		sendData.add(classtemp.getClassID());
		mes.setData(sendData);

		Client client = new Client(ClientMainFrame.socket);

		Message serverResponse = new Message();
		serverResponse = client.sendRequestToServer(mes);
		int res = (int) serverResponse.getData();
	}
	*/
	
	@SuppressWarnings("unchecked")
	private void finalupdate() {
		Message mes = new Message();
		mes.setModuleType(ModuleType.Student);
		mes.setMessageType(MessageType.ClassAdminGetAll);
		List<Object> sendData = new ArrayList<Object>();
		mes.setData(sendData);

		Client client = new Client(ClientMainFrame.socket);

		Message serverResponse = new Message();
		serverResponse = client.sendRequestToServer(mes);
		StuAll = (Vector<Student>) serverResponse.getData();
	}

	public void getStudent() {
		String[] arr = new String[4];
		for (int i = 0; i < StuAll.size(); i++) {
			// 班级 学号 姓名 电话
			arr[0] = StuAll.get(i).getClassid();
			arr[1] = StuAll.get(i).getStudentid();
			arr[2] = StuAll.get(i).getStudentName();
			arr[3] = StuAll.get(i).getStudentphone();

			model1.addRow(arr);
			table.setModel(model1);
		}
	}

	/*
	 * @SuppressWarnings("unchecked") private void addRow_Student() { // TODO
	 * Auto-generated method stub Message mes = new Message();
	 * mes.setMessageType(MessageType.ClassAdminGetAll);// operation type
	 * mes.setModuleType(ModuleType.Student); Message serverresponse = new
	 * Message(); Vector<Student> stu = new Vector<Student>();// your data Client
	 * client = new Client(ClientMainFrame.socket); serverresponse =
	 * client.sendRequestToServer(mes); stu = (Vector<Student>)
	 * serverresponse.getData(); //String get = serverresponse.getData().toString();
	 * //System.out.println(get); String[] arr = new String[6]; for (int i = 0; i <
	 * stu.size(); i++) { //班级 学号 姓名 电话 arr[0] = stu.get(i).getClassid(); arr[1] =
	 * stu.get(i).getStudentid(); arr[2] = stu.get(i).getStudentName(); arr[3] =
	 * stu.get(i).getStudentphone();
	 * 
	 * model1.addRow(arr); table.setModel(model1); } }
	 */

	void close() {
		finalupdate();
		CAC.setEnabled(true);
		CAC.updateFrame(StuAll, ClssAll);
		this.dispose();
	}
}
