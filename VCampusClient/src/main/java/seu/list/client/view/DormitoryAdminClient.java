//package view;
package seu.list.client.view;

import seu.list.common.Dormitory;
import seu.list.client.bz.Client;
import seu.list.client.bz.ClientMainFrame;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.ModuleType;
/*
import common.Dormitory;
import common.IConstant;
import Message.MessageType;
import Message.Message;
import client.Client;
import client.ClientMainFrame;
import Message.ModuleType;
*/
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/*
import seu.list.common.*;
import seu.list.client.*;
import seu.list.client.bz.Client;
import seu.list.client.bz.ClientMainFrame;
*/

public class DormitoryAdminClient extends JFrame {

	private JPanel contentPane;
	private JTextField searchField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DormitoryAdminClient frame = new DormitoryAdminClient();
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
	public DormitoryAdminClient() {
		setFont(new Font("微软雅黑", Font.BOLD, 12));
		setTitle("宿舍-管理员");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel dormLabel = new JLabel("宿舍管理");
		dormLabel.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		
		searchField = new JTextField();
		searchField.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		searchField.setColumns(10);
		
		JButton searchButton = new JButton("查询");
		searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SearchAct(e);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton modifyNewButton = new JButton("修改");
		modifyNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		modifyNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dormmodify Modify = new Dormmodify();
				Modify.setVisible(true);
				ModifyAct(e);
			}
		});
		
		JButton exitNewButton_1 = new JButton("完成");
		exitNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		exitNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		
		JButton deleteButton = new JButton("删除");
		deleteButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dormdelete delete = new Dormdelete();
				delete.setVisible(true);
				DeleteAct(e);
			}
		});
		
		JButton addButton = new JButton("添加");
		addButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dormadd add = new Dormadd();
				add.setVisible(true);
				AddAct(e);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(245, Short.MAX_VALUE)
					.addComponent(searchField, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(searchButton)
					.addGap(42))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(18, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 551, GroupLayout.PREFERRED_SIZE)
					.addGap(18))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(42)
					.addComponent(modifyNewButton, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(exitNewButton_1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(44, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(232)
					.addComponent(dormLabel, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(247, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(13)
					.addComponent(dormLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(addButton)
						.addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(modifyNewButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(exitNewButton_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(9))
		);
		
		table = new JTable();
		table.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u59D3\u540D", "\u5BBF\u820D", "\u5E8A\u4F4D", "\u7535\u8D39", "\u6C34\u8D39", "\u536B\u751F\u8BC4\u5206", "\u5BBF\u820D\u8C03\u6362\u7533\u8BF7", "\u7EF4\u4FEE\u7533\u8BF7"
			}
		));
		table.getColumnModel().getColumn(2).setMaxWidth(2147483646);
		table.getColumnModel().getColumn(6).setPreferredWidth(95);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		Object[][] dorminformation= {};
		Object[] dormlist = {"学号","宿舍","床位","卫生评分","水费","电费","调换申请","维修申请"};
		DefaultTableModel model;
		model = new DefaultTableModel(dorminformation, dormlist);

		Message mes = new Message();
		mes.setUserType(1);
		mes.setModuleType(ModuleType.Dormitory);
		mes.setMessageType(MessageType.DormAdShow);
		
		Socket socket = null;
		try {
			socket = new Socket(IConstant.SERVER_ADDRESS,IConstant.SERVER_PORT);
		}catch (IOException e) {
			e.printStackTrace();
		}
		Client client = new Client(socket);
		
		Message rec=new Message();
		rec=client.sendRequestToServer(mes);
		ArrayList<Dormitory> allDormitoryContents = (ArrayList<Dormitory>) rec.getData();
		System.out.println(allDormitoryContents.size());
		Object sigRow[] = new  String[8];
		for(int i=0;i<allDormitoryContents.size();) {
			String[] arr=new String[8];
			arr[0]=allDormitoryContents.get(i).getuserID();
			arr[1]=allDormitoryContents.get(i).getDormitoryID();
			arr[2]=String.valueOf(allDormitoryContents.get(i).getStudentBunkID());
			arr[3]=String.valueOf(allDormitoryContents.get(i).getWater());
			arr[4]=String.valueOf(allDormitoryContents.get(i).getElectricity());
			arr[5]=String.valueOf(allDormitoryContents.get(i).getDormitoryScore());
			arr[6]=allDormitoryContents.get(i).getStudentExchange();
			arr[7]=allDormitoryContents.get(i).getDormitoryMaintain();
			
			model.addRow(arr);
		}
	}

	protected void ModifyAct(ActionEvent e) {
		// TODO Auto-generated method stub
		SetTableShow();
	}

	protected void AddAct(ActionEvent e) {
		// TODO Auto-generated method stub
		SetTableShow();
	}

	protected void DeleteAct(ActionEvent e) {
		// TODO Auto-generated method stub
		
		SetTableShow();
	}

	private void SetTableShow() {
		// TODO Auto-generated method stub
		ArrayList<Dormitory> dorm = new ArrayList<Dormitory>();

		Message mes = new Message();
		Client client = new Client(ClientMainFrame.socket);
		mes.setModuleType(ModuleType.Dormitory);
		mes.setMessageType(MessageType.DormAdShow);
		Message serverResponse = new Message();

		serverResponse = client.sendRequestToServer(mes);

		DefaultTableModel tablemodel;
		tablemodel = new DefaultTableModel(new Object[][] {}, new String[] { "学号","宿舍","床位","卫生评分","水费","电费","调换申请","维修申请" }) {

			private static final long serialVersionUID = 1L;

			/*
			 * overload the method to change the table's factor
			 */
			@Override
			public boolean isCellEditable(int row, int column) {

				return false;
			}
		};

		for (int i = 0; i < dorm.size(); i++) {
			String[] arr=new String[8];
			arr[0]=dorm.get(i).getuserID();
			arr[1]=dorm.get(i).getDormitoryID();
			arr[2]=String.valueOf(dorm.get(i).getStudentBunkID());
			arr[3]=String.valueOf(dorm.get(i).getWater());
			arr[4]=String.valueOf(dorm.get(i).getElectricity());
			arr[5]=String.valueOf(dorm.get(i).getDormitoryScore());
			arr[6]=dorm.get(i).getStudentExchange();
			arr[7]=dorm.get(i).getDormitoryMaintain();
		}

		table.setModel(tablemodel);
	}


	protected void SearchAct(ActionEvent e) {
		// TODO Auto-generated method stub
		Message mes =new Message();
		Client client=new Client(ClientMainFrame.socket);
		mes.setModuleType(ModuleType.Dormitory);
		mes.setMessageType(MessageType.DormSearch);
		mes.setData(searchField.getText());
		
		Message serverResponse=new Message();

		serverResponse=client.sendRequestToServer(mes);

		ArrayList<Dormitory> res=new ArrayList<Dormitory>();
		res=(ArrayList<Dormitory>)serverResponse.getData();
		
		Object[][] dorminformation= {};
		Object[] dormlist = {"学号","宿舍","床位","卫生评分","水费","电费","调换申请","维修申请"};
		DefaultTableModel model;
		model = new DefaultTableModel(dorminformation, dormlist);

		for(int i=0;i<res.size();i++) {
			String[] arr=new String[8];
			arr[0]=res.get(i).getuserID();
			arr[1]=res.get(i).getDormitoryID();
			arr[2]=String.valueOf(res.get(i).getStudentBunkID());
			arr[3]=String.valueOf(res.get(i).getWater());
			arr[4]=String.valueOf(res.get(i).getElectricity());
			arr[5]=String.valueOf(res.get(i).getDormitoryScore());
			arr[6]=res.get(i).getStudentExchange();
			arr[7]=res.get(i).getDormitoryMaintain();
		}

		table.setModel(model);
	}
}