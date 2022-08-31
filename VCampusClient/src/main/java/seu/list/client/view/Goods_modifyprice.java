package seu.list.client.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import seu.list.client.bz.Client;
import seu.list.client.bz.ClientMainFrame;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.ModuleType;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Goods_modifyprice {

	private JFrame frame;
	 private Shop_AdminFrame shop;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public Goods_modifyprice(Shop_AdminFrame a) {
		shop=a;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 454, 275);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modify(e);
				close();
			}
		});
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shop.show();
				close();
			}
		});
		
		JLabel lblNewLabel = new JLabel("确认修改商品信息？");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 32));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(82)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addGap(62))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(93, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(53))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(81, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(58))
		);
		frame.getContentPane().setLayout(groupLayout);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(2);
	}
	protected void Modify(ActionEvent e) {
		// TODO 自动生成的方法存根
		Message mes =new Message();
		mes.setMessageType(MessageType.ModifyGoods);
		mes.setModuleType(ModuleType.Shop);
		Client client=new Client(ClientMainFrame.socket);
		ArrayList<String> args=new ArrayList<String>();
		for(int i=0;i<shop.getTable().getRowCount();i++) {
		args.add(shop.getTable().getValueAt(i,0)+"");
		args.add(shop.getTable().getValueAt(i,2)+"");
		args.add(shop.getTable().getValueAt(i,3)+"");
		}
		mes.setData(args);
		Message serverResponse= client.sendRequestToServer(mes);
		close();
	}

	protected void close() {
		// TODO 自动生成的方法存根
		frame.setEnabled(true);
		frame.dispose();
	}
}
