package DormitoryManageClient;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;


public class DormitoryManageClient extends JFrame{

	private  DefaultTableModel stu=null;
	private JTable Stable;
	
	public DormitoryManageClient(String title) {
		// 调用父类
		super(title);

		// 添加面板
		JPanel root = new JPanel();
		this.setContentPane(root);

		root.setLayout(null);
		
		String[][] datas= {};
		String [] titles= {"姓名","宿舍","床位号","电费","水费","卫生评分","宿舍调换申请","维修申请"};
		
		stu=new DefaultTableModel(datas,titles);
		Stable=new JTable(stu);
		
		//添加按钮
		JButton modifyButton = new JButton("修改");
		JButton addButton = new JButton("增加");
		JButton searchButton = new JButton("查找");
		JButton exitButton = new JButton("退出");
		
		root.add(modifyButton);
		root.add(addButton);
		root.add(searchButton);
		root.add(exitButton);
		
		modifyButton.setBounds(0,0,100,30);
		addButton.setBounds(150,0, 100, 30);
		searchButton.setBounds(300, 0, 100, 30);
		exitButton.setBounds(0, 0, 100, 30);
		
		// 设置监听器
		ActionListener modifyListener = new ModifyListener();
		modifyButton.addActionListener(modifyListener);

		ActionListener addListener = new AddListener();
		addButton.addActionListener(addListener);

		ActionListener searchListener = new SearchListener();
		searchButton.addActionListener(searchListener);

		ActionListener exitListener = new ExitListener();
		exitButton.addActionListener(exitListener);
		
		
	}
	
	private class ModifyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("**修改成功");
			//////////////////
		}
		
	}
	
	private class AddListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame Add=new JFrame("添加宿舍信息");
			Add.setSize(300, 550);
			Add.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			JPanel panel=new JPanel();
			Add.setContentPane(panel);
			Add.setLayout(null);
			Add.setVisible(true);
			
			//标题
			JLabel AddLabel=new JLabel("添加宿舍信息");
			AddLabel.setBounds(0, 10, 300, 50);
			AddLabel.setFont(new Font("微软黑雅", Font.BOLD, 25));
			AddLabel.setHorizontalAlignment(SwingConstants.CENTER);
			Add.add(AddLabel);
			
			//添加宿舍信息
			Object[] an=new Object[9];
			JTextField StuName = new JTextField(10);
			StuName.setFont(new Font(null,Font.PLAIN, 14));
			StuName.setBounds(150, 70, 110, 25);
			panel.add(StuName);
			
			JLabel StuNameLabel=new JLabel("学生姓名：");
			StuNameLabel.setBounds(50, 70, 110, 25);
			StuNameLabel.setFont(new Font("微软黑雅", Font.PLAIN, 14));
			panel.add(StuNameLabel);
			
			JTextField StuNumber = new JTextField(10);
			StuNumber.setFont(new Font(null,Font.PLAIN, 14));
			StuNumber.setBounds(150, 110, 110, 25);
			panel.add(StuNumber);
			
			JLabel StuNumberLabel=new JLabel("学生学号：");
			StuNumberLabel.setBounds(50,110, 110, 25);
			StuNumberLabel.setFont(new Font("微软黑雅", Font.PLAIN, 14));
			panel.add(StuNumberLabel);
			
			JTextField DormNumber = new JTextField(10);
			DormNumber.setFont(new Font(null,Font.PLAIN, 14));
			DormNumber.setBounds(150, 150, 110, 25);
			panel.add(DormNumber);
			
			JLabel DormNumberLabel=new JLabel("宿舍号：");
			DormNumberLabel.setBounds(50, 150, 110, 25);
			DormNumberLabel.setFont(new Font("微软黑雅", Font.PLAIN, 14));
			panel.add(DormNumberLabel);
			
			JTextField BunkID = new JTextField(10);
			BunkID.setFont(new Font(null,Font.PLAIN, 14));
			BunkID.setBounds(150, 190, 110, 25);
			panel.add(BunkID);
			
			JLabel BunkIDLabel=new JLabel("床位号：");
			BunkIDLabel.setBounds(50, 190, 110, 25);
			BunkIDLabel.setFont(new Font("微软黑雅", Font.PLAIN, 14));
			panel.add(BunkIDLabel);
			
			JTextField Electricity = new JTextField(10);
			Electricity.setFont(new Font(null,Font.PLAIN, 14));
			Electricity.setBounds(150, 230, 110, 25);
			panel.add(Electricity);
			
			JLabel ElectricityLabel=new JLabel("电费：");
			ElectricityLabel.setBounds(50, 230, 110, 25);
			ElectricityLabel.setFont(new Font("微软黑雅", Font.PLAIN, 14));
			panel.add(ElectricityLabel);
			
			JTextField Water = new JTextField(10);
			Water.setFont(new Font(null,Font.PLAIN, 14));
			Water.setBounds(150, 270, 110, 25);
			panel.add(Water);
			
			JLabel WaterLabel=new JLabel("水费：");
			WaterLabel.setBounds(50, 270, 110, 25);
			WaterLabel.setFont(new Font("微软黑雅", Font.PLAIN, 14));
			panel.add(WaterLabel);
			
			JTextField Score = new JTextField(10);
			Score.setFont(new Font(null,Font.PLAIN, 14));
			Score.setBounds(150, 310, 110, 25);
			panel.add(Score);
			
			JLabel ScoreLabel=new JLabel("卫生评分：");
			ScoreLabel.setBounds(50, 310, 110, 25);
			ScoreLabel.setFont(new Font("微软黑雅", Font.PLAIN, 14));
			panel.add(ScoreLabel);
			
			JTextField Exchange = new JTextField(10);
			Exchange.setFont(new Font(null,Font.PLAIN, 14));
			Exchange.setBounds(150, 350, 110, 25);
			panel.add(Exchange);
			
			JLabel ExchangeLabel=new JLabel("宿舍调换申请：");
			ExchangeLabel.setBounds(50, 350, 110, 25);
			ExchangeLabel.setFont(new Font("微软黑雅", Font.PLAIN, 14));
			panel.add(ExchangeLabel);
			
			JTextField Modify = new JTextField(10);
			Modify.setFont(new Font(null,Font.PLAIN, 14));
			Modify.setBounds(150, 390, 110, 25);
			panel.add(Modify);
			
			JLabel ModifyLabel=new JLabel("维修申请：");
			ModifyLabel.setBounds(50, 390, 110, 25);
			ModifyLabel.setFont(new Font("微软黑雅", Font.PLAIN, 14));
			panel.add(ModifyLabel);
			
			JButton OKButton=new JButton("确定");
			OKButton.setBounds(150, 450, 100, 30);
			panel.add(OKButton);
			
			JButton ReturnButton=new JButton("取消");
			ReturnButton.setBounds(30, 450, 100, 30);
			panel.add(ReturnButton);
			
			OKButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					an[0]=StuName.getText();
					an[1]=StuNumber.getText();
					an[2]=DormNumber.getText();
					an[3]=BunkID.getText();
					an[4]=Electricity.getText();
					an[5]=Score.getText();
					an[6]=Water.getText();
					an[7]=Exchange.getText();
					an[8]=Modify.getText();
					stu.addRow(an);
				}
			});
			
			ReturnButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Add.setVisible(false);
					
				}
			});
		}
		
	}
	private class SearchListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("**查询成功");
			////////////////
		}
		
	}
	private class ExitListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("**退出成功");
			///////////////////
		}
		
	}
}
