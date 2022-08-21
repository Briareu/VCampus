package seu.list.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Button;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.ScrollPane;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;

import seu.list.common.*;
import seu.list.client.*;
import seu.list.client.bz.Client;
import seu.list.client.bz.ClientMainFrame;
import seu.list.client.test.MainTest;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ClassAdminClient extends JFrame {

	private JPanel contentPane;
	private JTextField search;
	private JTable table;
	private DefaultTableModel model;
	private int num;
	private int targetrow;
	private int targetcol;
	private int target;
	private Vector<Student> Stu = null;// save data from db ,actually for time saving
	private Vector<ClassManage> Clss = null;
	private JScrollPane scrollPane;
	private Vector<Student> StuTemp = null;

	private enum MODEL {
		WATCHING, MODIFY, ADD, DELETE
	};

	private MODEL nowmodel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassAdminClient frame = new ClassAdminClient();
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
	@SuppressWarnings("unchecked")
	public ClassAdminClient() {
		nowmodel = MODEL.WATCHING;
		num = 0;
		model = new DefaultTableModel(new Object[][] {}, new String[] { "\u73ED\u7EA7", "\u6559\u5E08", "\u5B66\u53F7",
				"\u59D3\u540D", "\u4E13\u4E1A", "\u8054\u7CFB\u7535\u8BDD" }) {
			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			/*
			 * overload the method to change the table's factor
			 */
			@Override
			public boolean isCellEditable(int row, int column) {
				if (target == 0) {// col
					if (column == gettargetcol()) {
						return true;
					} else {
						return false;
					}
				} else if (target == 1) {
					if (row == gettargetrow()) {
						return true;
					} else {
						return false;
					}
				} else if (target == 2) {
					if (column == gettargetcol() && row == gettargetrow()) {
						return true;
					} else {
						return false;
					}
				} else {// set id
					if ((column == gettargetcol() || column == gettargetcol() + 1 || column == gettargetcol() + 4)
							&& row == gettargetrow()) {
						return false;
					} else if (row == gettargetrow()) {
						return true;
					} else {
						return false;
					}
				}
			}
		};
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 434);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JButton exitbutton = new JButton("退出");

		JMenu mnNewMenu = new JMenu("菜单");
		mnNewMenu.setFont(new Font("微軟正黑體", Font.PLAIN, 13));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("修改");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nowmodel == MODEL.ADD) {
					JOptionPane.showMessageDialog(null, "请先进行保存", "提示", JOptionPane.WARNING_MESSAGE);
				} else if (nowmodel == MODEL.DELETE) {
					JOptionPane.showMessageDialog(null, "请先完成删除操作", "提示", JOptionPane.WARNING_MESSAGE);
				} else {
					nowmodel = MODEL.MODIFY;
					setModifyFrame();
					nowmodel = MODEL.WATCHING;
				}
			}
		});
		mntmNewMenuItem.setFont(new Font("宋体", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("增加");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nowmodel == MODEL.ADD) {
					JOptionPane.showMessageDialog(null, "请先进行保存", "提示", JOptionPane.WARNING_MESSAGE);
				} else if (nowmodel == MODEL.DELETE) {
					JOptionPane.showMessageDialog(null, "请先完成删除操作", "提示", JOptionPane.WARNING_MESSAGE);
				} else if (nowmodel == MODEL.MODIFY) {
					JOptionPane.showMessageDialog(null, "请先完成修改操作", "提示", JOptionPane.WARNING_MESSAGE);
				} else {
					/*
					 * String[] arr = new String[6]; arr[0] = ""; arr[1] = ""; arr[2] = ""; arr[3] =
					 * ""; arr[4] = ""; arr[5] = ""; model.addRow(arr); table.setModel(model); int
					 * count = table.getRowCount(); settargetrow(count - 1);
					 * table.isCellEditable(count - 1, 1); exitbutton.setText("确定");
					 */
					nowmodel = MODEL.ADD;
					setAddFrame();
					nowmodel = MODEL.WATCHING;
				}
			}
		});
		mntmNewMenuItem_1.setFont(new Font("宋体", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("删除");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nowmodel = MODEL.DELETE;
				setDeleteFrame();
				nowmodel = MODEL.WATCHING;
			}
		});
		mntmNewMenuItem_2.setFont(new Font("宋体", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JComboBox<String> select = new JComboBox<String>();
		select.addItem("全部");
		select.addItem("班级");
		select.addItem("学号");
		select.addItem("姓名");

		search = new JTextField();
		search.setColumns(10);

		JButton serachbutton = new JButton("确认");
		serachbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (select.getSelectedIndex()) {
				case 0: {
					updateStudent(Stu);
				}
					break;
				case 1: {
					// class
					StuTemp = null;
					StuTemp = new Vector<Student>();
					int iforsearch = 0;
					String sch = search.getText();
					while (iforsearch < Stu.size()) {
						// System.out.println(iforsearch);
						String test = Stu.get(iforsearch).getClassid();
						test.replaceAll("\\p{C}", "");
						sch.replaceAll("\\p{C}", "");
						// System.out.println(test.equals(sch));
						// System.out.println(test);
						// System.out.println(sch);
						if (test.equals(sch)) {
							StuTemp.add(Stu.get(iforsearch));
							// System.out.println(1);
						}
						iforsearch++;
						// System.out.println("okay");
					}
					System.out.println("update Student vector success!");
					updateStudent(StuTemp);
				}
					break;
				case 2: {
					// student
					StuTemp = null;
					StuTemp = new Vector<Student>();
					int iforsearch = 0;
					String sch = search.getText();
					while (iforsearch < Stu.size()) {
						if (Stu.get(iforsearch).getStudentid().equals(sch)) {
							StuTemp.add(Stu.get(iforsearch));
						}
						iforsearch++;
					}
					System.out.println("update Student vector success!");
					System.out.println(StuTemp.size());
					updateStudent(StuTemp);
				}
					break;
				case 3: {
					// name
					StuTemp = null;
					StuTemp = new Vector<Student>();
					int iforsearch = 0;
					String sch = search.getText();
					while (iforsearch < Stu.size()) {
						if (Stu.get(iforsearch).getStudentName().equals(sch)) {
							StuTemp.add(Stu.get(iforsearch));
						}
						iforsearch++;
					}
					System.out.println("update Student vector success!");
					updateStudent(StuTemp);
				}
				default:
					break;
				}
			}
		});

		scrollPane = new JScrollPane(table);

		JLabel lblNewLabel = new JLabel("学生学籍管理");
		lblNewLabel.setFont(new Font("新宋体", Font.PLAIN, 23));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(
						gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(196)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 162,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(221, Short.MAX_VALUE))
								.addGroup(gl_contentPane
										.createSequentialGroup().addContainerGap(44, Short.MAX_VALUE)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 491,
												GroupLayout.PREFERRED_SIZE)
										.addGap(44))
								.addGroup(
										gl_contentPane.createSequentialGroup().addContainerGap(306, Short.MAX_VALUE)
												.addComponent(select, GroupLayout.PREFERRED_SIZE, 67,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(search, GroupLayout.PREFERRED_SIZE, 102,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(serachbutton, GroupLayout.PREFERRED_SIZE, 61,
														GroupLayout.PREFERRED_SIZE)
												.addGap(31))
								.addGroup(Alignment.LEADING,
										gl_contentPane.createSequentialGroup().addGap(234)
												.addComponent(exitbutton, GroupLayout.PREFERRED_SIZE, 97,
														GroupLayout.PREFERRED_SIZE)
												.addContainerGap(248, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(20).addComponent(lblNewLabel).addGap(30)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(search, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(select, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(serachbutton))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(exitbutton)
				.addContainerGap(21, Short.MAX_VALUE)));

		table = new JTable();
		table.setFont(new Font("Adobe 仿宋 Std R", Font.PLAIN, 12));
		table.setModel(model);
		table.getColumnModel().getColumn(5).setPreferredWidth(144);
		scrollPane.setViewportView(table);
		table.setRowHeight(25);
		addRows();
		getClasses();

		System.out.println("First Page of Student Management success!");

		/*
		 * table.getModel().addTableModelListener(new TableModelListener() { public int
		 * lastrow = -2, lastcol = -2; public String lastedit = null; public void
		 * tableChanged(TableModelEvent e) { if(nowmodel == MODEL.ADD) { if(lastrow ==
		 * table.getEditingColumn() && lastcol == table.getEditingRow() &&
		 * lastedit.compareTo((String) table.getValueAt(table.getEditingRow(),
		 * table.getEditingColumn())) == 0) { //empty } else {
		 * System.out.println("table changed!"); lastrow = table.getEditingRow();
		 * lastcol = table.getEditingColumn(); lastedit = (String)
		 * table.getValueAt(lastrow, lastcol); //check(); } } } });
		 */

		contentPane.setLayout(gl_contentPane);
		exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
	}

	@SuppressWarnings("unchecked")
	void addRows() {
		Message mes = new Message();
		mes.setMessageType(MessageType.ClassAdminGetAll);// operation type
		mes.setModuleType(ModuleType.Student);
		Message serverresponse = new Message();
		Vector<Student> stu = new Vector<Student>();// your data
		Client client = new Client(ClientMainFrame.socket);
		serverresponse = client.sendRequestToServer(mes);
		stu = (Vector<Student>) serverresponse.getData();
		Stu = stu;
		// String get = serverresponse.getData().toString();
		// System.out.println(get);
		String[] arr = new String[6];
		for (int i = 0; i < stu.size(); i++) {
			arr[0] = stu.get(i).getClassid();
			arr[1] = stu.get(i).getTeacherid();
			arr[2] = stu.get(i).getStudentid();
			arr[3] = stu.get(i).getStudentName();
			arr[4] = stu.get(i).getMajor();
			arr[5] = stu.get(i).getStudentphone();

			model.addRow(arr);
			table.setModel(model);
		}
	}

	@SuppressWarnings("unchecked")
	void getClasses() {
		// TODO Auto-generated method stub
		Message mes = new Message();
		mes.setMessageType(MessageType.ClassGetAll);// operation type
		mes.setModuleType(ModuleType.Student);
		Message serverresponse = new Message();
		Client client = new Client(ClientMainFrame.socket);
		serverresponse = client.sendRequestToServer(mes);
		Clss = new Vector<ClassManage>();
		Clss = (Vector<ClassManage>) serverresponse.getData();
		//System.out.println("get Classes");
		//System.out.println(Clss.isEmpty());
	}

	void settargetrow(int tar) {
		targetrow = tar;
		target = 1;// row
	}

	int gettargetrow() {
		return targetrow;
	}

	void settargetcol(int tar) {
		targetcol = tar;
		targetcol = 0;// col
	}

	int gettargetcol() {
		return targetcol;
	}

	void settarget(int tar) {
		target = tar;
	}

	void setAddFrame() {
		this.setEnabled(false);
		this.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);

		ClassAdminForAdd frame = new ClassAdminForAdd(this, Stu, Clss);
		frame.setVisible(true);
	}

	void setModifyFrame() {
		this.setEnabled(false);
		this.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
		ClassAdminForModify frame = new ClassAdminForModify(this, Stu, Clss);
		frame.setVisible(true);
	}

	void setDeleteFrame() {
		this.setEnabled(false);
		this.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
		ClassAdminForDelete frame = new ClassAdminForDelete(this, Stu, Clss);
		frame.setVisible(true);
	}

	void close() {
		this.dispose();
		MainTest frame = new MainTest();
		frame.setVisible(true);
	}

	private void updateStudent(Vector<Student> tempforstu) {
		while (table.getRowCount() > 0) {
			model.removeRow(table.getRowCount() - 1);
			table.setModel(model);
		}

		String[] arr = new String[6];
		for (int i = 0; i < tempforstu.size(); i++) {
			arr[0] = tempforstu.get(i).getClassid();
			arr[1] = tempforstu.get(i).getTeacherid();
			arr[2] = tempforstu.get(i).getStudentid();
			arr[3] = tempforstu.get(i).getStudentName();
			arr[4] = tempforstu.get(i).getMajor();
			arr[5] = tempforstu.get(i).getStudentphone();
			System.out.println("111111");

			model.addRow(arr);
			table.setModel(model);
		}
	}

	public void updateFrame(Vector<Student> Stu_update, Vector<ClassManage> Clss_update) {
		// use it in the child frame
		Stu = Stu_update;
		Clss = Clss_update;
		while (table.getRowCount() > 0) {
			model.removeRow(table.getRowCount() - 1);
			table.setModel(model);
		}
		addRows();
	}
}
