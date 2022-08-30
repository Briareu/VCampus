package seu.list.server.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import seu.list.server.bz.ServerMainFrame;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * 类{@codeServerFrame}用于生成服务器端的界面 <br>
 * 静态数据成员: {@code contentOane}, 类型: {@code JPanel}, 服务器端界面 <br>
 * 支持对服务器的操作
 * @author 柳多荣
 * @version 1.0
 * @see java.swing.*
 * @see java.awt.*
 * @see java.util.Vector
 */

public class ServerFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * 服务端界面的初始化
	 * @author 柳多荣
	 * @version 1.0
	 * @see java.swing.*
	 * @see java.awt.*;
	 * @see java.util.Vector
	 */
	public ServerFrame() {
		this.setTitle("服务端");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "是否退出？", "退出", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(result == JOptionPane.OK_OPTION) {
					close();
					ServerMainFrame.exitbtn();
				}
			}
		});
		
		JButton btnNewButton = new JButton("Help");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "launch -- 启动服务器\nclose -- 关闭服务器\nreboot -- 重启服务器\ngetall -- 打印所有客户端信息\nexit -- 退出服务端程序", "帮助", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JButton btnNewButton_1 = new JButton("Launch");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res = ServerMainFrame.launch();
				if(res == 0) {
					JOptionPane.showMessageDialog(null, "请勿重复启动服务器，或按help获取帮助", "错误", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JButton btnNewButton_2 = new JButton("Close");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res = ServerMainFrame.closebtn();
				if(res == 0) {
					JOptionPane.showMessageDialog(null, "还未启动服务器，或按help获取帮助", "错误", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JButton btnNewButton_3 = new JButton("Reboot");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res = ServerMainFrame.reboot();
				if(res == 0) {
					JOptionPane.showMessageDialog(null, "还未启动服务器，或按help获取帮助", "错误", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JButton btnNewButton_4 = new JButton("Exit");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(null, "是否要退出服务器端程序?", "提示", JOptionPane.YES_NO_OPTION);
				if(res == 0) {
					close();
					ServerMainFrame.exitbtn();
				}else {
					JOptionPane.showMessageDialog(null, "按help获取帮助", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton_4.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel = new JLabel("欢迎使用虚拟校园系统-服务端程序！");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 22));
		
		JButton btnNewButton_5 = new JButton("GetAll");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<String> res = new Vector<String>();
				res = ServerMainFrame.getall();
				if(res.size() == 0) {
					JOptionPane.showMessageDialog(null, "目前没有客户端连接到服务器", "错误", JOptionPane.ERROR_MESSAGE);
				}else {
					String info = null;
					info = "目前连接到服务器上的客户端：";
					int ivector = 0;
					while(ivector<res.size()) {
						info = info + "\n";
						info = info + res.get(ivector) + ": " + res.get(ivector + 1);
						ivector += 2;
					}
					JOptionPane.showMessageDialog(null, info, "信息", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton_5.setFont(new Font("宋体", Font.PLAIN, 18));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnNewButton_5, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
									.addGap(33)))
							.addGap(59))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(36)
					.addComponent(lblNewLabel)
					.addGap(55)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_3))
					.addGap(53)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_5))
					.addGap(61)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_4))
					.addContainerGap(95, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		this.setLocationRelativeTo(null);
	}
	/**
	 * 关闭本界面
	 * @author 柳多荣
	 * @version 1.0
	 */
	public void close() {
		this.dispose();
	}
}
