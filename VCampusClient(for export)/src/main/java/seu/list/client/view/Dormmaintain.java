/**
 * @author 周楚翘
 * @version jdk1.8.0
 */
package seu.list.client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;

public class Dormmaintain extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	private JButton okButton;
	private JButton cancelButton;
	private JLabel maintainLabel;
	private JTextField nametextField;
	private JTextField dormIDtextField;
	private JTextField maintaintextField;
	static Socket socket;
	public DormitoryStudentClient C;
	public Dormitory dorm;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			Dormmaintain dialog = new Dormmaintain(socket);
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
	public Dormmaintain(DormitoryStudentClient c,Socket socket) {
		C=c;
		setVisible(true);
		setTitle("维修登记");
		setBounds(100, 100, 450, 300);
		//添加图标
//		Image image=new ImageIcon("src/main/resources/image/xiaobiao.jpg").getImage();
		Image image=new ImageIcon(this.getClass().getResource("/resources/image/xiaobiao.jpg")).getImage();
		setIconImage(image);
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		{
			buttonPane = new JPanel();
			{
				okButton = new JButton("确定");
				okButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
				
				okButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							MaintainAct(e);
							setVisible(false);
						}
					
					});
			}
			{
				cancelButton = new JButton("取消");
				cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				cancelButton.setActionCommand("Cancel");
				
				cancelButton.addActionListener(new ActionListener() {
					
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
					.addGap(90)
					.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(75)
					.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addGap(89))
		);
		gl_buttonPane.setVerticalGroup(
			gl_buttonPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(okButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		buttonPane.setLayout(gl_buttonPane);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(contentPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
						.addComponent(buttonPane, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonPane, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
		);
		{
			maintainLabel = new JLabel("维修登记");
			maintainLabel.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		}
		JLabel nameLabel = new JLabel("学号：");
		nameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		nameLabel.setBackground(new Color(240, 240, 240));
		JLabel dormIDLabel = new JLabel("宿舍：");
		dormIDLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		JLabel maintainALabel = new JLabel("维修内容：");
		maintainALabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		nametextField = new JTextField();
		nametextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		nametextField.setColumns(10);
		
		dormIDtextField = new JTextField();
		dormIDtextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		dormIDtextField.setColumns(10);
		
		maintaintextField = new JTextField();
		maintaintextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		maintaintextField.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(72)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(maintainALabel)
						.addComponent(dormIDLabel)
						.addComponent(nameLabel))
					.addGap(12)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(maintaintextField)
						.addComponent(dormIDtextField, Alignment.LEADING)
						.addComponent(nametextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
						.addComponent(maintainLabel, Alignment.LEADING))
					.addContainerGap(111, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(maintainLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(nameLabel)
						.addComponent(nametextField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(20)
							.addComponent(dormIDLabel))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addComponent(dormIDtextField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(maintainALabel, Alignment.TRAILING)
						.addComponent(maintaintextField, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
		);
		contentPanel.setLayout(gl_contentPanel);
		getContentPane().setLayout(groupLayout);
		
		//居中显示
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(2);
	}
/**
 * 宿舍维修申请登记
 * @param e
 */
	protected void MaintainAct(ActionEvent e) {
		// TODO Auto-generated method stub
		Message mes = new Message();
		mes.setUserType(0);
		mes.setModuleType(ModuleType.Dormitory);
		mes.setMessageType(MessageType.DormMaintain);
		try {
			socket = new Socket(IConstant.SERVER_ADDRESS,IConstant.SERVER_PORT);
		}catch (IOException e1) {
			e1.printStackTrace();
		}
		Client client = new Client(socket);
		ArrayList<String> para = new ArrayList<String>();
		para.add(nametextField.getText());
		para.add(dormIDtextField.getText());
		para.add(maintaintextField.getText());
		
		mes.setData(para);
		System.out.println(para);
			Message serverResponse=new Message();
			serverResponse=client.sendRequestToServer(mes);
			dorm=(Dormitory)serverResponse.getData();
			C.updateFrameM(para);
			this.dispose();
	}
}
