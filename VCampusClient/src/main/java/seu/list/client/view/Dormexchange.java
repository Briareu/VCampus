package seu.list.client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import seu.list.client.bz.Client;
import seu.list.common.Dormitory;
import seu.list.common.IConstant;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.ModuleType;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextField;

public class Dormexchange extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JPanel buttonPane;
	private JTextField nametextField;
	private JTextField dormIDtextField;
	private JTextField exchangetextField_1;
	static Socket socket;
	public DormitoryStudentClient C;
	public Dormitory dorm;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			Dormexchange dialog = new Dormexchange(socket);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/**
	 * Create the dialog.
	 */
	public Dormexchange(DormitoryStudentClient c,Socket socket) {
		C=c;
		setVisible(true);
		setTitle("宿舍调换");
		setBounds(100, 100, 450, 300);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		JLabel ExchangeLabel = new JLabel("宿舍调换申请");
		ExchangeLabel.setFont(new Font("微软雅黑", Font.BOLD, 25));
		JLabel nameLabel_1 = new JLabel("学号：");
		nameLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		JLabel dormIDLabel_2 = new JLabel("原宿舍：");
		dormIDLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		JLabel exchangeLabel_3 = new JLabel("调换申请：");
		exchangeLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		nametextField = new JTextField();
		nametextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		nametextField.setColumns(10);
		dormIDtextField = new JTextField();
		dormIDtextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		dormIDtextField.setColumns(10);
		exchangetextField_1 = new JTextField();
		exchangetextField_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		exchangetextField_1.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(135)
							.addComponent(ExchangeLabel))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(82)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(nameLabel_1)
								.addComponent(dormIDLabel_2)
								.addComponent(exchangeLabel_3))
							.addGap(12)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(nametextField, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
								.addComponent(exchangetextField_1, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
								.addComponent(dormIDtextField, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
							.addGap(22)))
					.addContainerGap(103, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(ExchangeLabel)
					.addGap(24)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(nameLabel_1)
						.addComponent(nametextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(26)
							.addComponent(dormIDLabel_2))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addComponent(dormIDtextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(exchangeLabel_3)
						.addComponent(exchangetextField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			buttonPane = new JPanel();
			{
				okButton = new JButton("确定");
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
				
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						ExchangeAct(e);
						setVisible(false);
					}
				});
			}
			{
				cancelButton = new JButton("取消");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						setVisible(false);
					}
				});
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(85)
						.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addGap(84)
						.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addGap(111))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(5)
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
			);
			buttonPane.setLayout(gl_buttonPane);
		}
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(contentPanel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(buttonPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}

	protected void ExchangeAct(ActionEvent e) {
		// TODO Auto-generated method stub
		Message mes = new Message();
		mes.setUserType(0);
		mes.setModuleType(ModuleType.Dormitory);
		mes.setMessageType(MessageType.DormExcange);
		try {
			socket = new Socket(IConstant.SERVER_ADDRESS,IConstant.SERVER_PORT);
		}catch (IOException e1) {
			e1.printStackTrace();
		}
		Client client = new Client(socket);
		ArrayList<String> para = new ArrayList<String>();
		para.add(nametextField.getText());
		para.add(dormIDtextField.getText());
		para.add(exchangetextField_1.getText());

		mes.setData(para);
		System.out.println(para);
		Message serverResponse = new Message();
		serverResponse = client.sendRequestToServer(mes);
		dorm = (Dormitory) serverResponse.getData();
		C.updateFrameE(para);
		this.dispose();
	}

}
