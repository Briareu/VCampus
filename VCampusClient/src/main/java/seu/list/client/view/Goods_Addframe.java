package seu.list.client.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

import seu.list.client.bz.Client;
import seu.list.client.bz.ClientMainFrame;
import seu.list.common.Goods;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.ModuleType;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 类{@code Goods_Addframe}为增加商品界面界面
 * 点击“增加”按钮后会进入到当前界面
 * @author 欧阳瑜
 * @version 1.0
 */
public class Goods_Addframe {

	private JFrame frame;
	private JTextField IDtextField;private JTextField NametextField;
	
	private JLabel lblNewLabel_4;
    private Shop_AdminFrame shop;
    private JTextField PicetextField;
    private JTextField NumbertextField;
	/**
	 * Launch the application.
	 */
	
	public Goods_Addframe(Shop_AdminFrame a) {
		this.shop=a;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @param IDtextField 输入商品id
	 * @param NametextField 输入商品名称
	 * @param PicetextField 输入商品单价
	 * @param NumbertextField 输入商品库存
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 466, 332);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		IDtextField = new JTextField();
		IDtextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("商品编号");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		JLabel lblNewLabel_1 = new JLabel("商品名称");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		NametextField = new JTextField();
		NametextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("单价");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		JLabel lblNewLabel_3 = new JLabel("库存");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		lblNewLabel_4 = new JLabel("请输入新商品的信息！");
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addgoods();
				frame.dispose();
			}
		});
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		PicetextField = new JTextField();
		PicetextField.setColumns(10);
		
		NumbertextField = new JTextField();
		NumbertextField.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(124)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addGap(18)
									.addComponent(IDtextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1)
										.addComponent(lblNewLabel_2))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(PicetextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(NametextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(NumbertextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addComponent(lblNewLabel_3, Alignment.LEADING)))
					.addContainerGap(126, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(58)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addGap(76))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(IDtextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(NametextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(PicetextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(NumbertextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
		
		frame.setVisible(true);
	}
	/**  
	 * 方法{@code void Addgoods())}点击确认后执行，利用当前输入的参数增加商品。
	 */
	protected void Addgoods() {
		// TODO 自动生成的方法存根
		System.out.println(IDtextField.getText());
		System.out.println(PicetextField.getText());
		System.out.println(NumbertextField.getText());
		for(int i=0;i<shop.getTable().getColumnCount();i++) {
		if(IDtextField.getText().equals(shop.getTable().getValueAt(i, 0)))
		{
			JOptionPane.showMessageDialog(null, "ID重复！！", "提示", JOptionPane.WARNING_MESSAGE);
		}
		}
		if(!IDtextField.getText().matches("[0-9]*")) {
		JOptionPane.showMessageDialog(null, "ID应为正整数！！", "提示", JOptionPane.WARNING_MESSAGE);
		}
		else if(!PicetextField.getText().matches("[0-9]+[.]{0,1}[0-9]*[dD]{0,1}"))
		{
			JOptionPane.showMessageDialog(null, "请输入合法的价格！！", "提示", JOptionPane.WARNING_MESSAGE);
		}
		else if(!NumbertextField.getText().matches("[0-9]*")) {
			JOptionPane.showMessageDialog(null, "请输入合法库存！！", "提示", JOptionPane.WARNING_MESSAGE);
		}
		else {
		Goods temp=new Goods(Integer.parseInt(IDtextField.getText()),NametextField.getText(),
				Double.parseDouble(PicetextField.getText()),Integer.parseInt(NumbertextField.getText()));
		
		Message mes =new Message();
		mes.setData(temp);
		mes.setModuleType(ModuleType.Shop);
		mes.setMessageType(MessageType.GoodsAdd);
		Client client=new Client(ClientMainFrame.socket);

		Message serverResponse = client.sendRequestToServer(mes); 
		int res=(int)serverResponse.getData();
		shop.show();
		}
	}
}
