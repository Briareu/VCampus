package seu.list.client.view;

import seu.list.common.Dormitory;
import seu.list.common.IConstant;
import seu.list.client.bz.Client;
import seu.list.client.bz.ClientMainFrame;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.ModuleType;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class Dormadd extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	private JButton okButton;
	private JButton cancelButton;
	private JLabel lblNewLabel;
	private JTextField AuserIDField;
	private JTextField AdormIDField;
	private JTextField AbunkIDField;
	private JTextField AscoreField;
	private JTextField AwaterField;
	private JTextField AelectricityField;
	private JTextField AexchangeField;
	private JTextField AmaintainField;
	static Socket socket;
	private Message mes =new Message();
	private Client client;
	private Dormitory temp;
	public ArrayList<Dormitory> allDormitoryContents;
	private DormitoryAdminClient c=null;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			Dormadd dialog = new Dormadd(socket);
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
	public Dormadd(final DormitoryAdminClient C,Socket socket) {
		c=C;
		setVisible(true);
		setTitle("添加宿舍");
		setBounds(100, 100, 469, 496);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		{
			buttonPane = new JPanel();
			{
				okButton = new JButton("确定");
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						AddAct(e);
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
		}
		GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
		gl_buttonPane.setHorizontalGroup(
			gl_buttonPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addGap(94)
					.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(104))
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
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 437, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(contentPanel, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(37))
		);
		{
			lblNewLabel = new JLabel("添加宿舍");
			lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		}
		JLabel lblNewLabel_1 = new JLabel("学号：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		JLabel lblNewLabel_2 = new JLabel("宿舍：");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		JLabel lblNewLabel_3 = new JLabel("床位：");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		JLabel lblNewLabel_4 = new JLabel("卫生评分：");
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		JLabel lblNewLabel_5 = new JLabel("水费：");
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		JLabel lblNewLabel_6 = new JLabel("电费：");
		lblNewLabel_6.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		JLabel lblNewLabel_7 = new JLabel("调换申请：");
		lblNewLabel_7.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		JLabel lblNewLabel_8 = new JLabel("维修申请：");
		lblNewLabel_8.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		AuserIDField = new JTextField();
		AuserIDField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		AuserIDField.setColumns(10);
		AdormIDField = new JTextField();
		AdormIDField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		AdormIDField.setColumns(10);
		AbunkIDField = new JTextField();
		AbunkIDField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		AbunkIDField.setColumns(10);
		AscoreField = new JTextField();
		AscoreField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		AscoreField.setColumns(10);
		AwaterField = new JTextField();
		AwaterField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		AwaterField.setColumns(10);
		AelectricityField = new JTextField();
		AelectricityField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		AelectricityField.setColumns(10);
		AexchangeField = new JTextField();
		AexchangeField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		AexchangeField.setColumns(10);
		AmaintainField = new JTextField();
		AmaintainField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		AmaintainField.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(166, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(162))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(91)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_4)
						.addComponent(lblNewLabel_5)
						.addComponent(lblNewLabel_6)
						.addComponent(lblNewLabel_7)
						.addComponent(lblNewLabel_8))
					.addGap(22)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(AmaintainField)
						.addComponent(AexchangeField)
						.addComponent(AelectricityField)
						.addComponent(AwaterField)
						.addComponent(AscoreField)
						.addComponent(AbunkIDField)
						.addComponent(AdormIDField)
						.addComponent(AuserIDField, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
					.addContainerGap(115, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(20)
							.addComponent(lblNewLabel_1))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addComponent(AuserIDField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(20)
							.addComponent(lblNewLabel_2))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addComponent(AdormIDField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(20)
							.addComponent(lblNewLabel_3))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addComponent(AbunkIDField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_4)
						.addComponent(AscoreField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(AwaterField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(20)
							.addComponent(lblNewLabel_6))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addComponent(AelectricityField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(20)
							.addComponent(lblNewLabel_7))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addComponent(AexchangeField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(20)
							.addComponent(lblNewLabel_8))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addComponent(AmaintainField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(66, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		getContentPane().setLayout(groupLayout);
		
		//居中显示
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(2);
	}
	public ArrayList<Dormitory> getAll()
	{
		return allDormitoryContents;
	}
	protected void AddAct(ActionEvent e) {
		// TODO Auto-generated method stub
		temp = new Dormitory();
		temp.setuserID(AuserIDField.getText());
		temp.setDormitoryID(AdormIDField.getText());
		temp.setStudentBunkID(Integer.parseInt(AbunkIDField.getText()));
		temp.setWater(Integer.parseInt(AwaterField.getText()));
		temp.setElectricity(Integer.parseInt(AelectricityField.getText()));
		temp.setDormitoryScore(Integer.parseInt(AscoreField.getText()));
		temp.setDormitoryMaintain(AmaintainField.getText());
		temp.setStudentExchange(AexchangeField.getText());
		
		Message mes =new Message();
		mes.setUserType(1);
		mes.setModuleType(ModuleType.Dormitory);
		mes.setMessageType(MessageType.DormAdd);
		try {
			socket = new Socket(IConstant.SERVER_ADDRESS,IConstant.SERVER_PORT);
		}catch (IOException e1) {
			e1.printStackTrace();
		}
		client = new Client(socket);
		mes.setData(temp);
		
		Message rec = new Message();
		rec = client.sendRequestToServer(mes);
		
		allDormitoryContents = (ArrayList<Dormitory>) rec.getData();
		System.out.println(allDormitoryContents);
		c.setEnabled(true);
		c.updateFrame(temp);
		this.dispose();
		
		/*
		int res = (int)serverResponse.getData();
		if(res > 0)
			JOptionPane.showMessageDialog(null,"完成","提示",JOptionPane.WARNING_MESSAGE);
	*/
	}

}
