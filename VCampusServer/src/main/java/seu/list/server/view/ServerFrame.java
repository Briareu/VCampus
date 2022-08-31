package seu.list.server.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import seu.list.server.bz.ServerMainFrame;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

/**
 * 类{@codeServerFrame}用于生成服务器端的界面 <br>
 * 静态数据成员: {@code contentOane}, 类型: {@code JPanel}, 服务器端界面 <br>
 * 支持对服务器的操作 <br>
 * 重定向控制台输出到GUI界面显示 <br>
 * @author 柳多荣 吴慕陶
 * @version 1.0
 * @see java.swing.*
 * @see java.awt.*
 * @see java.util.Vector
 */

public class ServerFrame extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	
	public static JTextArea consoleText;

	/**
	 * 服务端界面的初始化 <br>
	 * 重定向控制台输出到GUI界面显示
	 * @author 柳多荣 吴慕陶
	 * @version 1.0
	 * @see java.swing.*
	 * @see java.awt.*;
	 * @see java.util.Vector
	 */
	public ServerFrame() {
		this.setTitle("服务端");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1013, 588);
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
		btnNewButton.setBounds(20, 68, 97, 31);
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "launch -- 启动服务器\nclose -- 关闭服务器\nreboot -- 重启服务器\ngetall -- 打印所有客户端信息\nexit -- 退出服务端程序", "帮助", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JButton btnNewButton_1 = new JButton("Launch");
		btnNewButton_1.setBounds(139, 68, 97, 31);
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
		btnNewButton_2.setBounds(264, 68, 97, 31);
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
		btnNewButton_3.setBounds(383, 68, 97, 31);
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
		btnNewButton_4.setBounds(614, 68, 97, 31);
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
		lblNewLabel.setBounds(310, 21, 370, 26);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 22));
		
		JButton btnNewButton_5 = new JButton("GetAll");
		btnNewButton_5.setBounds(507, 68, 97, 31);
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
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);

		contentPane.add(btnNewButton_2);
		contentPane.add(lblNewLabel);
		contentPane.add(btnNewButton);
		contentPane.add(btnNewButton_1);
		contentPane.add(btnNewButton_5);
		contentPane.add(btnNewButton_4);
		contentPane.add(btnNewButton_3);
		
		
		consoleText = new JTextArea();
		consoleText.setEditable(false);
		consoleText.setFont(new Font("Monospaced", Font.PLAIN, 16));
		consoleText.setBounds(42, 129, 695, 312);
		consoleText.setCaretPosition(consoleText.getText().length());
		
		scrollPane = new JScrollPane(consoleText);
		scrollPane.setBounds(20, 125, 969, 405);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane);
		
		
		OutputStream textAreaStream = new OutputStream() {
			public void write(int b) throws IOException {
				consoleText.append(String.valueOf((char)b));
				consoleText.setCaretPosition(consoleText.getText().length());
			}
			
			public void write(byte b[]) throws IOException {
				consoleText.append(new String(b));
				consoleText.setCaretPosition(consoleText.getText().length());
			}
			
			public void write(byte b[], int off, int len) throws IOException {
				consoleText.append(new String(b, off, len));
				consoleText.setCaretPosition(consoleText.getText().length());
			}
		};
		PrintStream myOut = new PrintStream(textAreaStream);
		System.setOut(myOut);
		System.setErr(myOut);
		
		System.out.println("欢迎使用虚拟校园系统-服务端");
		System.out.println("点击Help获取服务端使用帮助");
		consoleText.setCaretPosition(consoleText.getText().length());
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
