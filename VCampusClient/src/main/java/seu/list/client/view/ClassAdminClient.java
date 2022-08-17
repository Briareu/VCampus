//package VCampusClient.src.main.java.seu.list.client.view;
package seu.list.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.ScrollPane;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

import seu.list.common.*;
//import seu.list.common.Client;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.Student;
import seu.list.client.bz.*;
import seu.list.client.bz.ClientMainFrame;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ClassAdminClient extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private DefaultTableModel model;
	private int num;
	private int targetrow;
	private int targetcol;
	private int target;
	private enum MODEL{WATCHING, MODIFY, ADD,DELETE};
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
	public ClassAdminClient() {
		nowmodel = MODEL.WATCHING;
		num = 0;
		model = new DefaultTableModel(new Object[][] {},new String[] {
			"\u73ED\u7EA7", "\u6559\u5E08", "\u5B66\u53F7", "\u59D3\u540D", "\u4E13\u4E1A", "\u8054\u7CFB\u7535\u8BDD"}) {
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
				}
				else {
					if(row == gettargetrow()) {
						return true;
					}else {
						return false;
					}
				}
			}
		};
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 434);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton exitbutton = new JButton("閫�鍑�");
		
		JMenu mnNewMenu = new JMenu("鑿滃崟");
		mnNewMenu.setFont(new Font("寰粺姝ｉ粦楂�", Font.PLAIN, 13));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("娴忚");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitbutton.setText("閫�鍑�");
			}
		});
		mntmNewMenuItem_3.setFont(new Font("瀹嬩綋", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("淇敼");
		mntmNewMenuItem.setFont(new Font("瀹嬩綋", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("澧炲姞");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nowmodel == MODEL.ADD){
					JOptionPane.showMessageDialog(null,"璇峰厛杩涜淇濆瓨","鎻愮ず",JOptionPane.WARNING_MESSAGE);
				}
				else if(nowmodel == MODEL.DELETE) {
					JOptionPane.showMessageDialog(null,"璇峰厛瀹屾垚鍒犻櫎鎿嶄綔","鎻愮ず",JOptionPane.WARNING_MESSAGE);
				}
				else if(nowmodel == MODEL.MODIFY) {
					JOptionPane.showMessageDialog(null,"璇峰厛瀹屾垚淇敼鎿嶄綔","鎻愮ず",JOptionPane.WARNING_MESSAGE);
				}
				else {
					String[] arr = new String[6];
					arr[0] = "";
					arr[1] = "";
					arr[2] = "";
					arr[3] = "";
					arr[4] = "";
					arr[5] = "";
					model.addRow(arr);
					table.setModel(model);
					int count = table.getRowCount();
					settargetrow(count - 1);
					table.isCellEditable(count - 1, 1);
					exitbutton.setText("纭畾");
					nowmodel = MODEL.ADD;
				}
			}
		});
		mntmNewMenuItem_1.setFont(new Font("瀹嬩綋", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("鍒犻櫎");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nowmodel = MODEL.DELETE;
				exitbutton.setText("鍒犻櫎");
			}
		});
		mntmNewMenuItem_2.setFont(new Font("瀹嬩綋", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JComboBox select = new JComboBox<String>();
		select.addItem("鐝骇");
		select.addItem("瀛﹀彿");
		select.addItem("濮撳悕");
		
		textField = new JTextField();
		textField.setText("馃攳");
		textField.setColumns(10);
		
		JButton serachbutton = new JButton("纭");
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		JLabel lblNewLabel = new JLabel("瀛︾敓瀛︾睄绠＄悊");
		lblNewLabel.setFont(new Font("鏂板畫浣�", Font.PLAIN, 23));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(196)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(221, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(44, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 491, GroupLayout.PREFERRED_SIZE)
					.addGap(44))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(306, Short.MAX_VALUE)
					.addComponent(select, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(serachbutton, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addGap(31))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(234)
					.addComponent(exitbutton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(248, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(select, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(serachbutton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(exitbutton)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		
		
		table = new JTable();
		String[][] data = new String[][] {};
		table.setFont(new Font("Adobe 浠垮畫 Std R", Font.PLAIN, 12));
		table.setModel(model);
		table.getColumnModel().getColumn(5).setPreferredWidth(144);
		scrollPane.setViewportView(table);
		table.setRowHeight(25);
		addRows();
		
		contentPane.setLayout(gl_contentPane);
		exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(nowmodel) {
				case WATCHING:
				{
					//exit
					close();
				}
				break;
				case MODIFY:
				{
					//save
				}
				break;
				case ADD:
				{
					//save
					int rownum = table.getRowCount() - 1;
					
				}
				break;
				case DELETE:
				{
					//save
					int rownum = table.getSelectedRow();
					if(rownum == -1)
						JOptionPane.showMessageDialog(null,"璇烽�夋嫨涓�琛屽啀杩涜鍒犻櫎","鎻愮ず",JOptionPane.WARNING_MESSAGE);
					//int res = server.delete((String) table.getValueAt(rownum, 3));
					
					Message mes = new Message(); // 新建一个Message对象
					mes.setModuleType(ModuleType.Student); // 设定你是哪个模块的，这里是学生管理
					mes.setMessageType(MessageType.ClassAdminDelete); // 设定你希望服务端进行的操作
					mes.setData(table.getValueAt(rownum, 2)); // 放入你想传输的数据，如果不需要传就不用写这行
															  // 数据可以是任意的抽象数据类型，因为接收的Object
					Client client = new Client(ClientMainFrame.socket); // 新建一个用来发数据的对象
																		// 参数务必用ClientMainFrame下的这个socket
					Message serverResponse = new Message();
					serverResponse = client.sendRequestToServer(mes); // 接收服务器回传的数据
					int res = (int)serverResponse.getData(); // 回传的数据可以转换成你想要的类型
					
					if(res > 0)
						JOptionPane.showMessageDialog(null,"瀹屾垚鍒犻櫎","鎻愮ず",JOptionPane.WARNING_MESSAGE);
					model.removeRow(rownum);
					table.setModel(model);
				}
				break;
				}
			}
		});
	}
	@SuppressWarnings("unchecked")
	void addRows() {
		
		Message mes = new Message(); 
		mes.setModuleType(ModuleType.Student); 
		mes.setMessageType(MessageType.ClassAdminGetAll); 
		Message serverresponse = new Message();
		Client client = new Client(ClientMainFrame.socket);
		serverresponse = client.sendRequestToServer(mes);
		Vector<Student> stu=new Vector<Student>();//your data
		stu = (Vector<Student>)serverresponse.getData();
		
		
		String[] arr = new String[6];
		for(int i = 0; i < stu.size(); i++) {
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
	void settargetrow(int tar) {
		targetrow = tar;
		target = 1;//row
	}
	int gettargetrow() {
		return targetrow;
	}
	void settargetcol(int tar) {
		targetcol = tar;
		targetcol = 0;//col
	}
	int gettargetcol() {
		return targetcol;
	}
	void close() {
		this.dispose();
	}
}
