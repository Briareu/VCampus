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
import seu.list.common.Student;

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
import java.util.Vector;
import java.awt.event.ActionEvent;

public class Shop_StudentForPay extends JFrame {

	private JPanel contentPane;
	private Shop_StudentFrame SSF = null;
	private Double Credit = 0.0;
	private JPasswordField pwd;
	private Student thisStu = null;
	private JFrame shop = null;

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
	public Shop_StudentForPay(Shop_StudentFrame ssf, JFrame oldframe, Double sum, String id, String PWD) {
		SSF = ssf;
		shop = oldframe;
		setBounds(100, 100, 489, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("结账");
		JLabel lblNewLabel = new JLabel("您需要支付的金额为：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel money = new JLabel("New label");
		
		JLabel lblNewLabel_1 = new JLabel("请输入您的密码");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		System.out.println(sum);
		
		money.setText(sum + "");
		
		Vector<Student> StuAll = new Vector<Student>();
		Message mes = new Message();
		mes.setModuleType(ModuleType.Student);
		mes.setMessageType(MessageType.ClassAdminGetAll);
		List<Object> sendData = new ArrayList<Object>();
		mes.setData(sendData);

		Client client = new Client(ClientMainFrame.socket);

		Message serverResponse = new Message();
		serverResponse = client.sendRequestToServer(mes);
		StuAll = (Vector<Student>) serverResponse.getData();
		
		thisStu = new Student();
		
		int studenttemp = 0;
		while(studenttemp < StuAll.size()) {
			String tempid = StuAll.get(studenttemp).getStudentid();
			id.replaceAll("\\p{C}", "");
			tempid.replaceAll("\\p{C}", "");
			if(tempid.equals(id)) {
				thisStu = StuAll.get(studenttemp);
				break;
			}
			studenttemp++;
		}
		
		
		
		JButton commitbtn = new JButton("确认");
		commitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String in = String.valueOf(pwd.getPassword());
				in.replaceAll("\\p{C}", "");
				PWD.replaceAll("\\p{C}", "");
				//System.out.println(in);
				//System.out.println(PWD);
				//System.out.println(in.equals(PWD));
				if(in.equals(PWD)) {
					Credit = thisStu.getStudentcredit() - sum;
					
					if(Credit.compareTo(0.0) < 0) {
						JOptionPane.showMessageDialog(null, "余额不足，无法进行支付！", "提示", JOptionPane.WARNING_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "结账完成！", "提示", JOptionPane.WARNING_MESSAGE);
						
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
						
						System.out.println("Pay Success");
						ssf.buy();
						ssf.show();
						close();
					}
				}else {
					JOptionPane.showMessageDialog(null, "密码错误！", "提示", JOptionPane.WARNING_MESSAGE);
				}
			
			}
		});
		
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(pwd, 164, 164, 164)
							.addGap(4))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(money, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(461))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(58)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_2)
						.addComponent(money))
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
		shop.setEnabled(true);
		this.dispose();
	}
}
