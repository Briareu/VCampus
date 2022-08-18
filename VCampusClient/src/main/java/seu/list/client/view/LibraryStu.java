package seu.list.client.view;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import seu.list.common.Book;

//import seu.list.common.Client;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.client.bz.*;

import seu.list.client.bz.Client;
import seu.list.client.bz.ClientMainFrame;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.ModuleType;


public class LibraryStu extends JFrame {

	private JPanel contentPane;
	private JPanel lendPane,returnPane; //借书、还书界面

	private JTextField findText,lendIDText,returnIDText;
	private JButton returnBookButton,exitButton,lendBookButton;  //contentPane
	private JButton qrLendButton,qrReturnButton,qxLendButton,qxReturnButton; //lendPane&returnPane
	private JLayeredPane layerPane;

	private JTable table;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryStu frame = new LibraryStu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 */
	public LibraryStu() {

		ArrayList<Book> booklist=new ArrayList<Book>();		
		

		Message mes =new Message();
		Client client=new Client(ClientMainFrame.socket);
		mes.setModuleType(ModuleType.Library);
		mes.setMessageType(MessageType.LibraryBookGetAll);	
		Message serverResponse=new Message();

		serverResponse=client.sendRequestToServer(mes);

		//booklist=(ArrayList<Book>)serverResponse.getData();
		
		System.out.print(serverResponse.getData());

		setTitle("图书馆-学生");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 770, 520);

		layerPane=new JLayeredPane();
		layerPane.setInheritsPopupMenu(true);
		layerPane.setIgnoreRepaint(true);
		setContentPane(layerPane);
		layerPane.setLayout(new BorderLayout(0, 0));

		lendPane = new JPanel();
		layerPane.setLayer(lendPane, 200);
		lendPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		lendPane.setBackground(UIManager.getColor("Panel.background"));
		layerPane.add(lendPane, BorderLayout.SOUTH);

		returnPane = new JPanel();
		layerPane.setLayer(returnPane, 300);
		returnPane.setBackground(UIManager.getColor("Panel.background"));
		returnPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		layerPane.add(returnPane, BorderLayout.NORTH);

		contentPane = new JPanel();
		layerPane.setLayer(contentPane, 2);
		contentPane.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		layerPane.add(contentPane);

		findText = new JTextField();
		findText.setForeground(UIManager.getColor("Button.shadow"));
		findText.setFont(new Font("华文新魏", Font.PLAIN, 20));
		findText.setText("书名/书号");
		findText.setColumns(10);

		JButton findButton = new JButton("查询");
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindAvt(e);
			}
		});
		findButton.setBackground(SystemColor.activeCaption);
		findButton.setFont(new Font("宋体", Font.BOLD, 25));

		JScrollPane scrollPane = new JScrollPane();

		returnBookButton = new JButton("还书");
		returnBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReturnAvt(e);
			}
		});
		returnBookButton.setForeground(new Color(0, 0, 128));
		returnBookButton.setFont(new Font("楷体", Font.BOLD, 25));
		returnBookButton.setBackground(Color.LIGHT_GRAY);

		exitButton = new JButton("退出");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExitAvt(e);
			}
		});
		exitButton.setForeground(new Color(0, 0, 128));
		exitButton.setFont(new Font("楷体", Font.BOLD, 25));
		exitButton.setBackground(Color.LIGHT_GRAY);

		lendBookButton = new JButton("借书");
		lendBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LendAvt(e);
			}
		});
		lendBookButton.setForeground(new Color(0, 0, 128));
		lendBookButton.setFont(new Font("楷体", Font.BOLD, 25));
		lendBookButton.setBackground(Color.LIGHT_GRAY);

		//contentPane
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(194)
							.addComponent(findText, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(findButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(51))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lendBookButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addComponent(returnBookButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
							.addGap(42)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 540, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(35, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(33, Short.MAX_VALUE)
							.addComponent(findText, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addGap(16))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(32)
							.addComponent(findButton)
							.addGap(17)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lendBookButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGap(45)
							.addComponent(returnBookButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGap(53)
							.addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 339, GroupLayout.PREFERRED_SIZE))
					.addGap(445))
		);


		DefaultTableModel tablemodel;
		tablemodel=new DefaultTableModel(new Object[][] {},new String[] {
				"书名", "书号", "作者", "出版社", "库存", "状态"}) {

				private static final long serialVersionUID = 1L;
				/*
				 * overload the method to change the table's factor
				 */
				@Override
				public boolean isCellEditable(int row, int column) {

				return false;
				}
		};


		
/*		for(int i=0;i<booklist.size();i++) {
>>>>>>> 27e89bf9ba1d83dbc5221972383f137fcce3b463
			String[] arr=new String[6];
			arr[0]=booklist.get(i).getName();
			arr[1]=booklist.get(i).getId();
			arr[2]=booklist.get(i).getAuthor();
			arr[3]=booklist.get(i).getPress();
			arr[4]=String.valueOf(booklist.get(i).getStock());
			if(booklist.get(i).getState()==true)
				arr[5]="可借";
			else
				arr[5]="不可借";

			tablemodel.addRow(arr);
		}
<<<<<<< HEAD

=======
		
		*/
		
		for(int i=0;i<2;i++) {
			String[] arr=new String[6];
			arr[0]="书名"+i;
			arr[1]="书号"+i;
			arr[2]="作者"+i;
			arr[3]="出版社"+i;
			arr[4]=""+i;
			arr[5]="可借";
			tablemodel.addRow(arr);
		}
		

		table = new JTable(tablemodel);
		table.setBackground(SystemColor.info);
		table.setFillsViewportHeight(true);

		table.setModel(tablemodel);

		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);

		lendIDText = new JTextField();
		lendIDText.setForeground(UIManager.getColor("Button.shadow"));
		lendIDText.setFont(new Font("华文新魏", Font.PLAIN, 20));
		lendIDText.setText("书号");
		lendIDText.setColumns(10);

		returnIDText = new JTextField();
		returnIDText.setForeground(UIManager.getColor("Button.shadow"));
		returnIDText.setFont(new Font("华文新魏", Font.PLAIN, 20));
		returnIDText.setText("书号");
		returnIDText.setColumns(10);

		qrLendButton = new JButton("确认");
		qrLendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LendAvt(e);
			}
		});
		qrLendButton.setForeground(new Color(0, 0, 128));
		qrLendButton.setFont(new Font("楷体", Font.BOLD, 25));
		qrLendButton.setBackground(Color.LIGHT_GRAY);

		qrReturnButton = new JButton("确认");
		qrReturnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReturnAvt(e);
			}
		});
		qrReturnButton.setForeground(new Color(0, 0, 128));
		qrReturnButton.setFont(new Font("楷体", Font.BOLD, 25));
		qrReturnButton.setBackground(Color.LIGHT_GRAY);

		qxLendButton = new JButton("取消");
		qxLendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qxLendAvt(e);
			}
		});
		qxLendButton.setForeground(new Color(0, 0, 128));
		qxLendButton.setFont(new Font("楷体", Font.BOLD, 25));
		qxLendButton.setBackground(Color.LIGHT_GRAY);

		qxReturnButton = new JButton("取消");
		qxReturnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qxReturnAvt(e);
			}
		});
		qxReturnButton.setForeground(new Color(0, 0, 128));
		qxReturnButton.setFont(new Font("楷体", Font.BOLD, 25));
		qxReturnButton.setBackground(Color.LIGHT_GRAY);

		//lendPane
        GroupLayout gl_lendPane = new GroupLayout(lendPane);
		gl_lendPane.setHorizontalGroup(
				gl_lendPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_lendPane.createSequentialGroup()
						.addGap(147)
						.addGroup(gl_lendPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_lendPane.createSequentialGroup()
								.addComponent(qrLendButton, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(qxLendButton, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
							.addComponent(lendIDText, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(179, Short.MAX_VALUE))
			);
			gl_lendPane.setVerticalGroup(
				gl_lendPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_lendPane.createSequentialGroup()
						.addGap(157)
						.addComponent(lendIDText, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addGap(57)
						.addGroup(gl_lendPane.createParallelGroup(Alignment.LEADING)
							.addComponent(qrLendButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addComponent(qxLendButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(177, Short.MAX_VALUE))
			);
		lendPane.setLayout(gl_lendPane);

		//returnPane
        GroupLayout gl_returnPane = new GroupLayout(returnPane);
        gl_returnPane.setHorizontalGroup(
        	gl_returnPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_returnPane.createSequentialGroup()
        			.addGap(184)
        			.addGroup(gl_returnPane.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(Alignment.TRAILING, gl_returnPane.createSequentialGroup()
        					.addComponent(qrReturnButton, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(qxReturnButton, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
        				.addComponent(returnIDText, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(212, Short.MAX_VALUE))
        );
        gl_returnPane.setVerticalGroup(
        	gl_returnPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_returnPane.createSequentialGroup()
        			.addGap(157)
        			.addComponent(returnIDText, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
        			.addGap(57)
        			.addGroup(gl_returnPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(qxReturnButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
        				.addComponent(qrReturnButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(177, Short.MAX_VALUE))
        );
        returnPane.setLayout(gl_returnPane);

		contentPane.setVisible(true);
		lendPane.setVisible(false);
		returnPane.setVisible(false);
	}


	protected void qxReturnAvt(ActionEvent e) {
		contentPane.setVisible(true);
		lendPane.setVisible(false);
		returnPane.setVisible(false);
	}

	protected void qxLendAvt(ActionEvent e) {
		contentPane.setVisible(true);
		lendPane.setVisible(false);
		returnPane.setVisible(false);
	}

	//还书
	protected void ReturnAvt(ActionEvent e) {
		contentPane.setVisible(false);
		lendPane.setVisible(false);
		returnPane.setVisible(true);

		Message mes =new Message();
		Client client=new Client(ClientMainFrame.socket);
		mes.setModuleType(ModuleType.Library);
		mes.setMessageType(MessageType.LibraryBookReturn);
		mes.setData(returnIDText.getText());
		Message serverResponse=new Message();

		serverResponse=client.sendRequestToServer(mes);

		int res = (int)serverResponse.getData();
		if(res > 0)
			JOptionPane.showMessageDialog(null,"还书完成","提示",JOptionPane.WARNING_MESSAGE);

	
		SetTableShow();

	}

	//借书
	protected void LendAvt(ActionEvent e) {
		//setContentPane(lendPane);
		contentPane.setVisible(false);
		returnPane.setVisible(false);
		lendPane.setVisible(true);

		Message mes =new Message();
		Client client=new Client(ClientMainFrame.socket);
		mes.setModuleType(ModuleType.Library);
		mes.setMessageType(MessageType.LibraryBookLend);
		mes.setData(lendIDText.getText());
		Message serverResponse=new Message();

		serverResponse=client.sendRequestToServer(mes);

		int res = (int)serverResponse.getData();
		if(res > 0)
			JOptionPane.showMessageDialog(null,"借书完成","提示",JOptionPane.WARNING_MESSAGE);

		SetTableShow();
	}

	//查询
	protected void FindAvt(ActionEvent e) {
		Message mes =new Message();
		Client client=new Client(ClientMainFrame.socket);
		mes.setModuleType(ModuleType.Library);
		mes.setMessageType(MessageType.LibraryBookFind);
		mes.setData(findText.getText());
		Message serverResponse=new Message();

		serverResponse=client.sendRequestToServer(mes);
		
		ArrayList<Book> resbook=new ArrayList<Book>();
		resbook=(ArrayList<Book>)serverResponse.getData();

		DefaultTableModel tablemodel;
		tablemodel=new DefaultTableModel(new Object[][] {},new String[] {
				"书名", "书号", "作者", "出版社", "库存", "状态"}) {

				private static final long serialVersionUID = 1L;
				/*
				 * overload the method to change the table's factor
				 */
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
		};

		for(int i=0;i<resbook.size();i++) {
			String[] arr=new String[6];
			arr[0]=resbook.get(i).getName();
			arr[1]=resbook.get(i).getId();
			arr[2]=resbook.get(i).getAuthor();
			arr[3]=resbook.get(i).getPress();
			arr[4]=String.valueOf(resbook.get(i).getStock());
			if(resbook.get(i).getState()==true)
				arr[5]="可借";
			else
				arr[5]="不可借";
		}

		table.setModel(tablemodel);
	}

	public void SetTableShow() {
		ArrayList<Book> booklist=new ArrayList<Book>();

		Message mes =new Message();
		Client client=new Client(ClientMainFrame.socket);
		mes.setModuleType(ModuleType.Library);
		mes.setMessageType(MessageType.LibraryBookGetAll);		
		Message serverResponse=new Message();

		serverResponse=client.sendRequestToServer(mes);
		
		booklist=(ArrayList<Book>)serverResponse.getData();

		DefaultTableModel tablemodel;
		tablemodel=new DefaultTableModel(new Object[][] {},new String[] {
				"书名", "书号", "作者", "出版社", "库存", "状态"}) {

				private static final long serialVersionUID = 1L;
				/*
				 * overload the method to change the table's factor
				 */
				@Override
				public boolean isCellEditable(int row, int column) {

				return false;
				}
		};

		for(int i=0;i<booklist.size();i++) {
			String[] arr=new String[6];
			arr[0]=booklist.get(i).getName();
			arr[1]=booklist.get(i).getId();
			arr[2]=booklist.get(i).getAuthor();
			arr[3]=booklist.get(i).getPress();
			arr[4]=String.valueOf(booklist.get(i).getStock());
			if(booklist.get(i).getState()==true)
				arr[5]="可借";
			else
				arr[5]="不可借";
			tablemodel.addRow(arr);
		}

		table.setModel(tablemodel);
	}

	//退出
	protected void ExitAvt(ActionEvent e) {
		 //登录界面LibraryLogin

		this.setVisible(false);//关闭本界面

	}
}