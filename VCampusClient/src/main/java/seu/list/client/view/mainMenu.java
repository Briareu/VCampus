package seu.list.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import seu.list.client.bz.ClientMainFrame;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setTitle("\u865A\u62DF\u6821\u56ED\u7CFB\u7EDF-\u4E3B\u83DC\u5355");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u865A\u62DF\u6821\u56ED\u7CFB\u7EDF\uFF01");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(98, 0, 426, 53);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel_1.setBounds(22, 46, 164, 39);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("\u5B66\u7C4D\u7BA1\u7406");
		btnNewButton.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton.setBounds(89, 95, 153, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u56FE\u4E66\u9986");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton_1.setBounds(347, 95, 153, 39);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u9009\u8BFE");
		btnNewButton_2.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton_2.setBounds(89, 172, 153, 39);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\u5BBF\u820D");
		btnNewButton_3.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton_3.setBounds(347, 172, 153, 39);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("\u5546\u5E97");
		btnNewButton_4.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton_4.setBounds(89, 256, 153, 39);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_4_1 = new JButton("\u9000\u51FA");
		btnNewButton_4_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // 退出按钮
				//ClientMainFrame.goOffline();
				System.exit(0);
			}
		});
		btnNewButton_4_1.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton_4_1.setBounds(423, 345, 153, 39);
		contentPane.add(btnNewButton_4_1);
	}
}
