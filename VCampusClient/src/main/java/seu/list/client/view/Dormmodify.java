/**
 * @author 周楚翘
 * @version jdk1.8.0
 */
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
import javax.swing.table.DefaultTableModel;
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
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Dormmodify extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	private JButton okButton;
	private JButton cancelButton;
	private JLabel lblNewLabel;
	private JTextField userIDField;
	private JTextField modifyField_1;
	//static Socket socket;
	JComboBox modifyt = new JComboBox();
	DormitoryAdminClient C;
	public ArrayList<Dormitory> allDormitoryContents;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			Dormmodify dialog = new Dormmodify(socket);
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
	public Dormmodify(DormitoryAdminClient c,Socket socket) {
		C=c;
		//this.socket=socket;
		setTitle("修改信息");
		setVisible(true);
		setBounds(100, 100, 450, 300);
		//添加图标
		Image image=new ImageIcon("src/main/resources/image/xiaobiao.jpg").getImage();
		setIconImage(image);
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		{
			buttonPane = new JPanel();
			{
				okButton = new JButton("确定");
				okButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener()
						{

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
							    ModifyAct(e);
								setVisible(false);
							}
					
						});
			}
			{
				cancelButton = new JButton("取消");
				cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
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
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(contentPanel, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(47)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(62, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(21))
		);
		{
			lblNewLabel = new JLabel("修改信息");
			lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 25));
		}
		JLabel lblNewLabel_1 = new JLabel("学号：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		userIDField = new JTextField();
		userIDField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		userIDField.setColumns(10);
		
		modifyt.setModel(new DefaultComboBoxModel(new String[] {"卫生评分", "水费", "电费", "宿舍维修", "宿舍调换"}));
		
		JLabel lblNewLabel_2 = new JLabel("修改项目：");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel lblNewLabel_3 = new JLabel("修改内容：");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		modifyField_1 = new JTextField();
		modifyField_1.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(89)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblNewLabel_3)
							.addComponent(lblNewLabel_2))
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(modifyField_1)
						.addComponent(modifyt, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel)
						.addComponent(userIDField, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
					.addGap(129))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(25)
					.addComponent(lblNewLabel)
					.addGap(25)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(userIDField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(modifyt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(modifyField_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
		gl_buttonPane.setHorizontalGroup(
			gl_buttonPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addGap(23)
					.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addGap(5))
		);
		gl_buttonPane.setVerticalGroup(
			gl_buttonPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
		);
		buttonPane.setLayout(gl_buttonPane);
		getContentPane().setLayout(groupLayout);
		
		//居中显示
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(2);
	}
/**
 * 宿舍信息修改
 * @param e
 */
	protected void ModifyAct(ActionEvent e) {
		// TODO Auto-generated method stub
		Message mes = new Message();
		mes.setUserType(1);
		mes.setModuleType(ModuleType.Dormitory);
		mes.setMessageType(MessageType.DormModify);
		/*
		try {
			socket = new Socket(IConstant.SERVER_ADDRESS,IConstant.SERVER_PORT);
		}catch (IOException e1) {
			e1.printStackTrace();
		}*/
		
		Client client = new Client(ClientMainFrame.socket); // 不需要新建socket，客户端一启动就建立好了
		ArrayList<String> para = new ArrayList<String>();
		para.add(userIDField.getText());
		String usertype = (String) modifyt.getSelectedItem();
		if ("卫生评分".equals(usertype)) para.add("卫生评分");
		if ("水费".equals(usertype)) para.add("水费");
		if ("电费".equals(usertype)) para.add("电费");
		if ("宿舍调换".equals(usertype)) para.add("宿舍调换");
		if ("宿舍维修".equals(usertype)) para.add("宿舍维修");
		para.add(modifyField_1.getText());
		
		mes.setData(para);
		System.out.println(para);
		System.out.println("!!!!!!!!!!!!!!");
			Message serverResponse=new Message();
			serverResponse=client.sendRequestToServer(mes);
			allDormitoryContents = (ArrayList<Dormitory>) serverResponse.getData();
			System.out.println(allDormitoryContents);
			C.updateFrameM(para);
			this.dispose();
	}

}

