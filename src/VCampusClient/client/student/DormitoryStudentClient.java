package Dormitorystudentclient;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DormitoryStudentClient extends JFrame {
	public DormitoryStudentClient(String title) {
		// 调用父类
		super(title);

		// 添加面板
		JPanel root = new JPanel();
		this.setContentPane(root);

		root.setLayout(null);

		// 添加按钮
		JButton modifyButton = new JButton("维修登记");
		JButton exchangeButton = new JButton("宿舍登记");
		JButton exitButton = new JButton("退出");

		root.add(modifyButton);
		root.add(exchangeButton);
		root.add(exitButton);

		modifyButton.setBounds(20, 200, 100, 30);
		exchangeButton.setBounds(140, 200, 100, 30);
		exitButton.setBounds(260, 200, 100, 30);

		// 添加文本
		JLabel manageLabel = new JLabel("宿舍信息");
		JLabel nameLabel = new JLabel("姓名:???");
		JLabel numberLabel = new JLabel("学号：???");
		JLabel dormIDLabel = new JLabel("宿舍：???");
		JLabel scoreLabel = new JLabel("卫生评分：???");
		JLabel bunkIDLabel = new JLabel("床位号：???");
		JLabel waterLabel = new JLabel("水费：???");
		JLabel electricityLabel = new JLabel("电费：???");
		JLabel modifyLabel = new JLabel("维修状态：???");

		root.add(manageLabel);
		root.add(nameLabel);
		root.add(numberLabel);
		root.add(dormIDLabel);
		root.add(scoreLabel);
		root.add(bunkIDLabel);
		root.add(waterLabel);
		root.add(electricityLabel);

		manageLabel.setBounds(0, 0, 400, 60);
		manageLabel.setFont(new Font("微软雅黑", Font.BOLD, 25));
		manageLabel.setHorizontalAlignment(SwingConstants.CENTER);

		nameLabel.setBounds(50, 60, 180, 30);
		nameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		nameLabel.setHorizontalAlignment(SwingConstants.LEFT);

		numberLabel.setBounds(50, 90, 180, 30);
		numberLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		numberLabel.setHorizontalAlignment(SwingConstants.LEFT);

		dormIDLabel.setBounds(50, 120, 180, 30);
		dormIDLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		dormIDLabel.setHorizontalAlignment(SwingConstants.LEFT);

		scoreLabel.setBounds(50, 150, 180, 30);
		scoreLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		scoreLabel.setHorizontalAlignment(SwingConstants.LEFT);

		bunkIDLabel.setBounds(210, 60, 180, 30);
		bunkIDLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		bunkIDLabel.setHorizontalAlignment(SwingConstants.LEFT);

		waterLabel.setBounds(210, 90, 180, 30);
		waterLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		waterLabel.setHorizontalAlignment(SwingConstants.LEFT);

		electricityLabel.setBounds(210, 120, 180, 30);
		electricityLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		electricityLabel.setHorizontalAlignment(SwingConstants.LEFT);

		// 设置监听器
		ActionListener modifyListener = new ModifyListener();
		modifyButton.addActionListener(modifyListener);

		ActionListener exchangeListener = new ExchangeListener();
		exchangeButton.addActionListener(exchangeListener);

		ActionListener exitListener = new ExitListener();
		exitButton.addActionListener(exitListener);
	}

	// 宿舍维修登记响应
	private class ModifyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println("**维修登记成功");
			// ModifyYNLabel.setText("是");

		}
	}

	// 调换申请响应
	private class ExchangeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println("**申请成功");

		}

	}

	// 退出响应
	private class ExitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println("**退出成功");

		}

	}
}
