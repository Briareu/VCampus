package seu.list.client.view;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import seu.list.common.Client;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.StudentManage;
import seu.list.common.Book;

public class LibraryManage extends JFrame {

	private JPanel contentPane,modifyPane,panel,addPane,deletePane;
	private JTextField findText;
	private JTable table;
	private JLayeredPane layerPane;
	private JTextField oldIDText;
	private JTextField modifiedText;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton nameRadioButton,idRadioButton,authorRadioButton,pressRadioButton,stockRadioButton;
	
	private LibraryUserServer manager;
	private JButton deleteButton;
	private JButton addButton;
	private JLabel addNameLabel;
	private JLabel addIDLabel;
	private JLabel addAuthorLabel;
	private JLabel addPressLabel;
	private JLabel addStockLabel;
	private JTextField addNameText;
	private JTextField addIDText;
	private JTextField addAuthorText;
	private JTextField addPressText;
	private JTextField addStockText;
	private JButton addqrButton;
	private JButton addqxButton;
	private JLabel delNameLabel;
	private JLabel delIDLabel;
	private JLabel delAuthorLabel;
	private JLabel delPressLabel;
	private JLabel delStockLabel;
	private JTextField delStockText;
	private JTextField delPressText;
	private JTextField delAuthorText;
	private JTextField delIDText;
	private JTextField delNameText;
	private JButton delqrButton;
	private JButton delqxButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryManage frame = new LibraryManage();
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
	public LibraryManage() {
		manager=new LibraryUserServer();
		Message mes =new Message();
		mes.setExtraMessage(manager);
		mes.setMessageType(MessageType.LibraryBookGetAll);
		Message serverResponse=new Message();
		ArrayList<Book> resbook=new ArrayList<Book>();
		Client client=new Client();
		serverResponse=client.sendRequestToServer(mes);
		manager.setBookList((ArrayList<Book>)serverResponse.getData());

		
		setTitle("图书馆-管理员");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 770, 520);
		
		layerPane=new JLayeredPane();
		layerPane.setInheritsPopupMenu(true);
		layerPane.setIgnoreRepaint(true);
		setContentPane(layerPane);
		layerPane.setLayout(new BorderLayout(0, 0));
		
		
		contentPane = new JPanel();
		layerPane.setLayer(contentPane, 2);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		layerPane.add(contentPane);
		
		modifyPane = new JPanel();
		modifyPane.setBackground(UIManager.getColor("Panel.background"));
		layerPane.setLayer(modifyPane, 200);
		modifyPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		layerPane.add(modifyPane, BorderLayout.WEST);		
		
		addPane = new JPanel();
		layerPane.setLayer(addPane, 300);
		addPane.setBackground(UIManager.getColor("Panel.background"));
		addPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		layerPane.add(addPane, BorderLayout.SOUTH);	
		
		deletePane = new JPanel();
		layerPane.setLayer(deletePane, 400);
		deletePane.setBackground(UIManager.getColor("Panel.background"));
		deletePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		layerPane.add(deletePane, BorderLayout.NORTH);	
		
		delNameLabel = new JLabel("书名");
		delNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		delNameLabel.setFont(new Font("宋体", Font.BOLD, 25));
		
		delIDLabel = new JLabel("书号");
		delIDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		delIDLabel.setFont(new Font("宋体", Font.BOLD, 25));
		
		delAuthorLabel = new JLabel("作者");
		delAuthorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		delAuthorLabel.setFont(new Font("宋体", Font.BOLD, 25));
		
		delPressLabel = new JLabel("出版社");
		delPressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		delPressLabel.setFont(new Font("宋体", Font.BOLD, 25));
		
		delStockLabel = new JLabel("库存");
		delStockLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		delStockLabel.setFont(new Font("宋体", Font.BOLD, 25));
		
		delStockText = new JTextField();
		delStockText.setColumns(10);
		
		delPressText = new JTextField();
		delPressText.setColumns(10);
		
		delAuthorText = new JTextField();
		delAuthorText.setColumns(10);
		
		delIDText = new JTextField();
		delIDText.setColumns(10);
		
		delNameText = new JTextField();
		delNameText.setColumns(10);
		
		delqrButton = new JButton("确定");
		delqrButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DelqrAvt(e);
			}
		});
		delqrButton.setForeground(Color.BLACK);
		delqrButton.setFont(new Font("楷体", Font.BOLD, 29));
		
		delqxButton = new JButton("取消");
		delqxButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DelqxAvt(e);
			}
		});
		delqxButton.setForeground(Color.BLACK);
		delqxButton.setFont(new Font("楷体", Font.BOLD, 29));
		GroupLayout gl_deletePane = new GroupLayout(deletePane);
		gl_deletePane.setHorizontalGroup(
			gl_deletePane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_deletePane.createSequentialGroup()
					.addGap(122)
					.addGroup(gl_deletePane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_deletePane.createSequentialGroup()
							.addComponent(delNameLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(delNameText, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_deletePane.createSequentialGroup()
							.addComponent(delIDLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(delIDText, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_deletePane.createSequentialGroup()
							.addComponent(delAuthorLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(delAuthorText, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_deletePane.createSequentialGroup()
							.addComponent(delPressLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(delPressText, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_deletePane.createSequentialGroup()
							.addComponent(delStockLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(delStockText, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_deletePane.createSequentialGroup()
							.addGap(101)
							.addComponent(delqrButton)
							.addGap(124)
							.addComponent(delqxButton, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(125, Short.MAX_VALUE))
		);
		gl_deletePane.setVerticalGroup(
			gl_deletePane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_deletePane.createSequentialGroup()
					.addGap(57)
					.addGroup(gl_deletePane.createParallelGroup(Alignment.LEADING)
						.addComponent(delNameLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(delNameText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(gl_deletePane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_deletePane.createSequentialGroup()
							.addGap(2)
							.addComponent(delIDLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addComponent(delIDText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_deletePane.createParallelGroup(Alignment.LEADING)
						.addComponent(delAuthorLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(delAuthorText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_deletePane.createParallelGroup(Alignment.LEADING)
						.addComponent(delPressLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(delPressText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(gl_deletePane.createParallelGroup(Alignment.LEADING)
						.addComponent(delStockLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(delStockText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_deletePane.createParallelGroup(Alignment.BASELINE)
						.addComponent(delqrButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(delqxButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(57, Short.MAX_VALUE))
		);
		deletePane.setLayout(gl_deletePane);
		
		addNameLabel = new JLabel("书名");
		addNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addNameLabel.setFont(new Font("宋体", Font.BOLD, 25));
		
		addIDLabel = new JLabel("书号");
		addIDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addIDLabel.setFont(new Font("宋体", Font.BOLD, 25));
		
		addAuthorLabel = new JLabel("作者");
		addAuthorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addAuthorLabel.setFont(new Font("宋体", Font.BOLD, 25));
		
		addPressLabel = new JLabel("出版社");
		addPressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addPressLabel.setFont(new Font("宋体", Font.BOLD, 25));
		
		addStockLabel = new JLabel("库存");
		addStockLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addStockLabel.setFont(new Font("宋体", Font.BOLD, 25));
		
		addNameText = new JTextField();
		addNameText.setColumns(10);
		
		addIDText = new JTextField();
		addIDText.setColumns(10);
		
		addAuthorText = new JTextField();
		addAuthorText.setColumns(10);
		
		addPressText = new JTextField();
		addPressText.setColumns(10);
		
		addStockText = new JTextField();
		addStockText.setColumns(10);
		
		addqrButton = new JButton("确定");
		addqrButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddbookAvt(e);
			}
		});
		addqrButton.setForeground(UIManager.getColor("Button.focus"));
		addqrButton.setFont(new Font("楷体", Font.BOLD, 29));
		
		addqxButton = new JButton("取消");
		addqxButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddqxAvt(e);
			}
		});
		addqxButton.setForeground(Color.BLACK);
		addqxButton.setFont(new Font("楷体", Font.BOLD, 29));
		GroupLayout gl_addPane = new GroupLayout(addPane);
		gl_addPane.setHorizontalGroup(
			gl_addPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addPane.createSequentialGroup()
					.addContainerGap(135, Short.MAX_VALUE)
					.addGroup(gl_addPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(addNameLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
						.addComponent(addIDLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(addAuthorLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(addPressLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
						.addComponent(addStockLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(26)
					.addGroup(gl_addPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(addStockText)
						.addComponent(addPressText)
						.addComponent(addAuthorText)
						.addComponent(addIDText)
						.addComponent(addNameText, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
					.addGap(116))
				.addGroup(gl_addPane.createSequentialGroup()
					.addGap(194)
					.addComponent(addqrButton)
					.addGap(113)
					.addComponent(addqxButton, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(239, Short.MAX_VALUE))
		);
		gl_addPane.setVerticalGroup(
			gl_addPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addPane.createSequentialGroup()
					.addContainerGap(45, Short.MAX_VALUE)
					.addGroup(gl_addPane.createParallelGroup(Alignment.LEADING)
						.addComponent(addNameText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(addNameLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_addPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_addPane.createSequentialGroup()
							.addGap(16)
							.addComponent(addIDText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_addPane.createSequentialGroup()
							.addGap(18)
							.addComponent(addIDLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
					.addGap(13)
					.addGroup(gl_addPane.createParallelGroup(Alignment.LEADING)
						.addComponent(addAuthorText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(addAuthorLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_addPane.createParallelGroup(Alignment.LEADING)
						.addComponent(addPressText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(addPressLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(gl_addPane.createParallelGroup(Alignment.LEADING)
						.addComponent(addStockText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(addStockLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(gl_addPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(addqrButton)
						.addComponent(addqxButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGap(135))
		);
		addPane.setLayout(gl_addPane);
		
		nameRadioButton = new JRadioButton("书名");
		buttonGroup.add(nameRadioButton);
		nameRadioButton.setFont(new Font("宋体", Font.BOLD, 25));
		
		JLabel selectLabel = new JLabel("请选择修改信息：");
		selectLabel.setFont(new Font("华文中宋", Font.BOLD, 25));
		
		idRadioButton = new JRadioButton("书号");
		buttonGroup.add(idRadioButton);
		idRadioButton.setFont(new Font("宋体", Font.BOLD, 25));
		
		authorRadioButton = new JRadioButton("作者");
		buttonGroup.add(authorRadioButton);
		authorRadioButton.setFont(new Font("宋体", Font.BOLD, 25));
		
		pressRadioButton = new JRadioButton("出版社");
		buttonGroup.add(pressRadioButton);
		pressRadioButton.setFont(new Font("宋体", Font.BOLD, 25));
		
		stockRadioButton = new JRadioButton("库存");
		buttonGroup.add(stockRadioButton);
		stockRadioButton.setFont(new Font("宋体", Font.BOLD, 25));
		
		panel = new JPanel();
		panel.setForeground(UIManager.getColor("Panel.background"));
		GroupLayout gl_modifyPane = new GroupLayout(modifyPane);
		gl_modifyPane.setHorizontalGroup(
			gl_modifyPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modifyPane.createSequentialGroup()
					.addGroup(gl_modifyPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_modifyPane.createSequentialGroup()
							.addGap(73)
							.addGroup(gl_modifyPane.createParallelGroup(Alignment.LEADING)
								.addComponent(idRadioButton, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
								.addComponent(authorRadioButton, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
								.addComponent(pressRadioButton, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
								.addComponent(stockRadioButton, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
								.addComponent(nameRadioButton, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_modifyPane.createSequentialGroup()
							.addGap(28)
							.addComponent(selectLabel)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
					.addGap(16))
		);
		gl_modifyPane.setVerticalGroup(
			gl_modifyPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modifyPane.createSequentialGroup()
					.addGroup(gl_modifyPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_modifyPane.createSequentialGroup()
							.addGap(51)
							.addComponent(selectLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(nameRadioButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(idRadioButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(authorRadioButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pressRadioButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(stockRadioButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_modifyPane.createSequentialGroup()
							.addGap(23)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		
		JLabel oldIDLabel = new JLabel("原书号：");
		oldIDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		oldIDLabel.setFont(new Font("华文中宋", Font.PLAIN, 25));
		
		JLabel modifiedLabel = new JLabel("修改后信息：");
		modifiedLabel.setFont(new Font("华文中宋", Font.PLAIN, 25));
		
		oldIDText = new JTextField();
		oldIDText.setColumns(10);
		
		modifiedText = new JTextField();
		modifiedText.setColumns(10);
		
		JButton modqrButton = new JButton("确认修改");
		modqrButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModiInfo(e);
			}
		});
		modqrButton.setFont(new Font("宋体", Font.BOLD, 25));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
									.addComponent(oldIDLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(oldIDText, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(modifiedLabel, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(modifiedText, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(145)
							.addComponent(modqrButton, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(81)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(oldIDLabel)
						.addComponent(oldIDText, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGap(44)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(modifiedLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(modifiedText, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGap(82)
					.addComponent(modqrButton)
					.addContainerGap(95, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		modifyPane.setLayout(gl_modifyPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton modifyButton = new JButton("修改");
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifyAvt(e);
			}
		});
		modifyButton.setForeground(new Color(0, 0, 128));
		modifyButton.setFont(new Font("楷体", Font.BOLD, 25));
		modifyButton.setBackground(Color.LIGHT_GRAY);
		
		JButton exitButton = new JButton("退出");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExitAvt(e);
			}
		});
		exitButton.setForeground(new Color(0, 0, 128));
		exitButton.setFont(new Font("楷体", Font.BOLD, 25));
		exitButton.setBackground(Color.LIGHT_GRAY);
		
		findText = new JTextField();
		findText.setFont(new Font("华文新魏", Font.PLAIN, 20));
		findText.setForeground(UIManager.getColor("Button.shadow"));
		findText.setText("书名/书号");
		findText.setColumns(10);
		
		JButton findButton = new JButton("查询");
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindAvt(e);
			}
		});
		findButton.setFont(new Font("宋体", Font.BOLD, 25));
		findButton.setBackground(SystemColor.activeCaption);
		
		deleteButton = new JButton("删除");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteAvt(e);
			}
		});
		deleteButton.setForeground(new Color(0, 0, 128));
		deleteButton.setFont(new Font("楷体", Font.BOLD, 25));
		deleteButton.setBackground(Color.LIGHT_GRAY);
		
		addButton = new JButton("增加");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddAvt(e);
			}
		});
		addButton.setForeground(new Color(0, 0, 128));
		addButton.setFont(new Font("楷体", Font.BOLD, 25));
		addButton.setBackground(Color.LIGHT_GRAY);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addComponent(modifyButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(344, Short.MAX_VALUE)
							.addComponent(findText, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(findButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addGap(35))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(findButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(findText, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGap(44)
							.addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGap(41)
							.addComponent(modifyButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
							.addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGap(34)))
					.addGap(36))
		);
		
		table = new JTable();
		table.setBackground(SystemColor.info);
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"\u4E66\u540D", "\u4E66\u53F7", "\u4F5C\u8005", "\u51FA\u7248\u793E", "\u5E93\u5B58", "\u72B6\u6001"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		contentPane.setVisible(true);
		modifyPane.setVisible(false);
		panel.setVisible(false);
		addPane.setVisible(false);
		deletePane.setVisible(false);
	}

	protected void FindAvt(ActionEvent e) {
		Message mes =new Message();
		mes.setExtraMessage(findText.getText());
		mes.setMessageType(MessageType.LibraryBookFind);
		Message serverResponse=new Message();
		Client client=new Client();
		serverResponse=client.sendRequestToServer(mes);
		ArrayList<Book> resbook=new ArrayList<Book>();
		resbook=(ArrayList<Book>)serverResponse.getData();
	}

	protected void DelqrAvt(ActionEvent e) {
		Book tbook=new Book(addIDText.getText(),addNameText.getText(),addAuthorText.getText(),Integer.parseInt(addStockText.getText()),addPressText.getText());
		
		Message mes =new Message();
		mes.setExtraMessage(tbook);
		mes.setMessageType(MessageType.LibraryBookDelete);
		Message serverResponse=new Message();
		Client client=new Client();
		serverResponse=client.sendRequestToServer(mes);
		
		int res = (int)serverResponse.getData();
		if(res > 0)
			JOptionPane.showMessageDialog(null,"完成","提示",JOptionPane.WARNING_MESSAGE);
	}

	protected void ModifyAvt(ActionEvent e) {
		contentPane.setVisible(false);
		modifyPane.setVisible(true);
		panel.setVisible(true);
		addPane.setVisible(false);
		deletePane.setVisible(false);
	}

	protected void DelqxAvt(ActionEvent e) {
		contentPane.setVisible(true);
		modifyPane.setVisible(false);
		panel.setVisible(false);
		addPane.setVisible(false);
		deletePane.setVisible(false);	
	}

	protected void DeleteAvt(ActionEvent e) {
		contentPane.setVisible(false);
		modifyPane.setVisible(false);
		panel.setVisible(false);
		addPane.setVisible(false);
		deletePane.setVisible(true);
		
	}

	//增加书籍界面取消
	protected void AddqxAvt(ActionEvent e) {
		contentPane.setVisible(true);
		modifyPane.setVisible(false);
		panel.setVisible(false);
		addPane.setVisible(false);
		deletePane.setVisible(false);		
	}

	//增加书籍确认界面
	protected void AddbookAvt(ActionEvent e) {
		Book tbook=new Book(addIDText.getText(),addNameText.getText(),addAuthorText.getText(),Integer.parseInt(addStockText.getText()),addPressText.getText());
		
		Message mes =new Message();
		mes.setExtraMessage(tbook);
		mes.setMessageType(MessageType.LibraryBookAdd);
		Message serverResponse=new Message();
		Client client=new Client();
		serverResponse=client.sendRequestToServer(mes);
		
		int res = (int)serverResponse.getData();
		if(res > 0)
			JOptionPane.showMessageDialog(null,"完成","提示",JOptionPane.WARNING_MESSAGE);
		
	}

	//增加书籍
	protected void AddAvt(ActionEvent e) {
		contentPane.setVisible(false);
		modifyPane.setVisible(false);
		panel.setVisible(false);
		addPane.setVisible(true);
		
	}

	//修改信息确认
	protected void ModiInfo(ActionEvent e) {
		if((nameRadioButton.isSelected()==false)&&(idRadioButton.isSelected()==false)&&(authorRadioButton.isSelected()==false)&&
				(pressRadioButton.isSelected()==false)&&(stockRadioButton.isSelected()==false))
			JOptionPane.showMessageDialog(null, "请选择修改的书籍信息！", "要求",JOptionPane.WARNING_MESSAGE);

     	Message mes=new Massage();
		int res=0;
		mes.setMessageType(MessageType.LibraryBookUpdate);  //
		if(nameRadioButton.isSelected()) {
			ArrayList<String> para = new ArrayList<String>();
			para.add(oldIDText.getText());
			para.add("Name");
			para.add(modifiedText.getText());
			mes.setExtraMessage(para);	
		}
		if(idRadioButton.isSelected()) {
			ArrayList<String> para = new ArrayList<String>();
			para.add(oldIDText.getText());
			para.add("ID");
			para.add(modifiedText.getText());
			mes.setExtraMessage(para);		
		}
		if(authorRadioButton.isSelected()) {
			ArrayList<String> para = new ArrayList<String>();
			para.add(oldIDText.getText());
			para.add("Author");
			para.add(modifiedText.getText());
			mes.setExtraMessage(para);	
		}
		if(pressRadioButton.isSelected()) {
			ArrayList<String> para = new ArrayList<String>();
			para.add(oldIDText.getText());
			para.add("Press");
			para.add(modifiedText.getText());
			mes.setExtraMessage(para);	
		}
		if(stockRadioButton.isSelected()) {
			ArrayList<String> para = new ArrayList<String>();
			para.add(oldIDText.getText());
			para.add("Stock");
			para.add(modifiedText.getText());
			mes.setExtraMessage(para);	
		}

     	Message serverResponse=new Message();
		Client client=new Client();
		serverResponse=client.sendResquestToServer(mes);
		res = (int)serverResponse.getData();
		if(res > 0)
			JOptionPane.showMessageDialog(null,"修改完成","提示",JOptionPane.WARNING_MESSAGE);
		
		contentPane.setVisible(true);
		modifyPane.setVisible(false);
		panel.setVisible(false);
		addPane.setVisible(false);
	}

	
	//退出到登录界面
	protected void ExitAvt(ActionEvent e) {

		this.setVisible(false);//关闭本界面
		
	}
}
