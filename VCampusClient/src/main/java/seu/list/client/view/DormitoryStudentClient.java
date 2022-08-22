package seu.list.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import seu.list.client.bz.Client;
import seu.list.client.bz.ClientMainFrame;
import seu.list.common.Dormitory;
import seu.list.common.IConstant;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.ModuleType;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class DormitoryStudentClient extends JFrame {

	private JPanel contentPane;
	static Socket socket;
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DormitoryStudentClient frame = new DormitoryStudentClient(socket);
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
	public DormitoryStudentClient(String userID,Socket socket) {
		setFont(new Font("微软雅黑", Font.BOLD, 12));
		setTitle("宿舍-学生");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel dormLabel = new JLabel("宿舍信息");
		dormLabel.setFont(new Font("微软雅黑", Font.BOLD, 25));
		dormLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel userIDLabel_1 = new JLabel("学号：");
		userIDLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel dormIDLabel_2 = new JLabel("宿舍：");
		dormIDLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel bunkIDLabel = new JLabel("床位：");
		bunkIDLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel scoreLabel = new JLabel("卫生评分：");
		scoreLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel waterLabel = new JLabel("水费：");
		waterLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel electricityLabel = new JLabel("电费：");
		electricityLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel exchangeLabel = new JLabel("调换申请：");
		exchangeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		exchangeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
	
		JLabel maintainLabel = new JLabel("维修申请：");
		maintainLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton maintianButton = new JButton("维修登记");
		maintianButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		maintianButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						MaintainAct(e);
						
					}
			
				});
		
		JButton exchangeButton = new JButton("宿舍调换申请");
		exchangeButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		exchangeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ExchangeAct(e);
			}
		});
		
		JButton exitButton = new JButton("退出");
		exitButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		
		JLabel UserIDLabel = new JLabel("");
		UserIDLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel DormIDLabel = new JLabel("");
		DormIDLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel BunkIDLabel_1 = new JLabel("");
		BunkIDLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel ScoreLabel_2 = new JLabel("");
		ScoreLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel WaterLabel_3 = new JLabel("");
		WaterLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel ElectricityLabel_4 = new JLabel("");
		ElectricityLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		ElectricityLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel ExchangeLabel_5 = new JLabel("");
		ExchangeLabel_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel MaintainLabel_6 = new JLabel("");
		MaintainLabel_6.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(31)
							.addComponent(maintianButton, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addGap(45)
							.addComponent(exchangeButton)
							.addGap(39)
							.addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(120)
							.addComponent(dormLabel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(66)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(dormIDLabel_2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addComponent(userIDLabel_1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(bunkIDLabel)
								.addComponent(scoreLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(UserIDLabel, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addComponent(ScoreLabel_2)
								.addComponent(BunkIDLabel_1)
								.addComponent(DormIDLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(maintainLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(MaintainLabel_6))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(exchangeLabel)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(ExchangeLabel_5))
												.addComponent(electricityLabel))
											.addGap(18))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(waterLabel, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
											.addGap(22)))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(WaterLabel_3)
										.addComponent(ElectricityLabel_4))
									.addGap(53)))))
					.addGap(54))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addComponent(dormLabel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(UserIDLabel)
						.addComponent(userIDLabel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(waterLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(WaterLabel_3))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(dormIDLabel_2, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(DormIDLabel)
						.addComponent(electricityLabel)
						.addComponent(ElectricityLabel_4))
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(bunkIDLabel)
						.addComponent(BunkIDLabel_1)
						.addComponent(exchangeLabel)
						.addComponent(ExchangeLabel_5))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scoreLabel)
						.addComponent(ScoreLabel_2)
						.addComponent(maintainLabel)
						.addComponent(MaintainLabel_6))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(maintianButton)
						.addComponent(exchangeButton)
						.addComponent(exitButton))
					.addGap(51))
		);
		contentPane.setLayout(gl_contentPane);
		
		setVisible(true);
		validate();
		
		Object[][] dorminformation= {};
		Object[] dormlist = {"学号","宿舍","床位","卫生评分","水费","电费","调换申请","维修申请"};
		DefaultTableModel model;
		model = new DefaultTableModel(dorminformation, dormlist);

		Message mes = new Message();
		mes.setUserType(1);
		mes.setModuleType(ModuleType.Dormitory);
		mes.setMessageType(MessageType.DormStShow);
		
		
		try {
			socket = new Socket(IConstant.SERVER_ADDRESS,IConstant.SERVER_PORT);
		}catch (IOException e) {
			e.printStackTrace();
		}
		Client client = new Client(this.socket);
		
		Message rec=new Message();
		rec=client.sendRequestToServer(mes);
		Dormitory dorm =(Dormitory) rec.getData();
		System.out.println(dorm);
		UserIDLabel.setText(dorm.getuserID());
		DormIDLabel.setText(dorm.getDormitoryID());
		BunkIDLabel_1.setText(String.valueOf(dorm.getStudentBunkID()));
		ScoreLabel_2.setText(String.valueOf(dorm.getDormitoryScore()));
		WaterLabel_3.setText(String.valueOf(dorm.getWater()));
		ElectricityLabel_4.setText(String.valueOf(dorm.getElectricity()));
		MaintainLabel_6.setText(dorm.getDormitoryMaintain());
		ExchangeLabel_5.setText(dorm.getStudentExchange());
	}

	protected void ExchangeAct(ActionEvent e) {
		// TODO Auto-generated method stub
		Dormexchange Exchange = new Dormexchange(socket);
		Exchange.setVisible(true);
	}

	protected void MaintainAct(ActionEvent e) {
		// TODO Auto-generated method stub
		Dormmaintain Maintain = new Dormmaintain(socket);
		Maintain.setVisible(true);
	}
}
