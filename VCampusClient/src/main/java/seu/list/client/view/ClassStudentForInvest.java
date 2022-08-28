package seu.list.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import seu.list.client.bz.Client;
import seu.list.client.bz.ClientMainFrame;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.ModuleType;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ClassStudentForInvest extends JFrame {

	private JPanel contentPane;
	private JTextField money;
	private ClassStudentClient CSC;
	private Double Credit = 0.0;
	private JPasswordField pwd;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassStudentForInvest frame = new ClassStudentForInvest();
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
	public ClassStudentForInvest(ClassStudentClient csc, final Double credit, final String id, final String PWD) {
		CSC=csc;
		setBounds(100, 100, 489, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("充值");
		JLabel lblNewLabel = new JLabel("请输入您想要充值的金额");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1 = new JLabel("请输入您的密码");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JButton commitbtn = new JButton("确认");
		commitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(money.getText() == null)
				{
					JOptionPane.showMessageDialog(null, "请填写充值金额！", "提示", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int i = 0;
					Boolean flag = true;
					while(i<money.getText().length()) {
						if(money.getText().charAt(i) == '.'&&i != 0) {
							//empty
						}else if(money.getText().charAt(i) == '.'&&i == 0) {
							JOptionPane.showMessageDialog(null, "请正确填写充值金额(非小数点开头)！", "提示", JOptionPane.WARNING_MESSAGE);
							flag = false;
							break;
						}else if(money.getText().charAt(i) == '0' && i == 0) {
							JOptionPane.showMessageDialog(null, "请正确填写充值金额(非零开头)！", "提示", JOptionPane.WARNING_MESSAGE);
							flag = false;
							break;
						}else if(money.getText().charAt(i) > '9' || money.getText().charAt(i) < '0'){
							JOptionPane.showMessageDialog(null, "请正确填写充值金额(数值)！", "提示", JOptionPane.WARNING_MESSAGE);
							flag = false;
							break;
						}else {
							i++;
						}
					}
					if(flag) {
						String in = String.valueOf(pwd.getPassword());
						in.replaceAll("\\p{C}", "");
						PWD.replaceAll("\\p{C}", "");
						//System.out.println(in);
						//System.out.println(PWD);
						//System.out.println(in.equals(PWD));
						if(in.equals(PWD)) {
							JOptionPane.showMessageDialog(null, "充值完成！", "提示", JOptionPane.WARNING_MESSAGE);
							Credit = credit + Double.parseDouble(money.getText());
							
							Message mes = new Message();
							mes.setModuleType(ModuleType.Student);
							mes.setMessageType(MessageType.ClassAdminUpdate);
							List<Object> sendData = new ArrayList<Object>();
							sendData.add(8);
							sendData.add(Credit);
							sendData.add(id);
							mes.setData(sendData);
							
							Client client = new Client(ClientMainFrame.socket);
							
							Message serverResponse = new Message();
							serverResponse = client.sendRequestToServer(mes);
							int res = (int)serverResponse.getData();
							
							System.out.println(Credit);
							close();
						}else {
							JOptionPane.showMessageDialog(null, "密码错误！", "提示", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			}
		});
		
		money = new JTextField();
		money.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("元");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JButton exitbtn = new JButton("退出");
		exitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		
		pwd = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(60)
					.addComponent(commitbtn, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(112)
					.addComponent(exitbtn, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(99, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(pwd))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(money, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)))
					.addGap(4)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(461))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(58)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(money, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(pwd, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(commitbtn)
						.addComponent(exitbtn))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(2);
	}
	void close() {
		CSC.update(Credit);
		CSC.setEnabled(true);
		this.dispose();
	}
}
