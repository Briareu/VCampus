package seu.list.client.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import seu.list.client.bz.Client;
import seu.list.client.bz.ClientMainFrame;
import seu.list.common.Goods;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.ModuleType;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.ScrollPane;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Toolkit;
import javax.swing.JInternalFrame;
import java.awt.Button;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class Shop_AdminFrame {
    ArrayList<Goods>GoodsList;
	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton_3;
	private JTextField Searchtext;
	private JPanel addpanel;
	private JTextField GoodsIDtext;
	private JTextField GoodsNametext;
	private JTextField Goodspricetext;
	private JTextField GoodsNumebrtext;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JTextField GoodsIDdeltext;
	private JLabel lblNewLabel_6;
	private JButton btnNewButton_6;
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Shop_AdminFrame window = new Shop_AdminFrame();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public Shop_AdminFrame() {
		initialize();
		GoodsList=new ArrayList<Goods>();
		frame.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
						
		setFrame(new JFrame());
		getFrame().setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/image/shop_manager_bg.jpg"));
		getFrame().setFont(new Font("微软雅黑", Font.BOLD, 17));
		getFrame().setTitle("管理员视图商店");
		getFrame().getContentPane().setBackground(Color.WHITE);
		getFrame().setBackground(Color.WHITE);
		getFrame().setBounds(100, 100, 577, 449);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		double total=0.0;
		
		MyCellEditor_double cellEditor_db = new MyCellEditor_double(new JTextField());
		MyCellEditor_int cellEditor = new MyCellEditor_int(new JTextField());
		
		JPanel delpanel = new JPanel();
		delpanel.setBounds(67, 19, 260, 116);
		getFrame().getContentPane().add(delpanel);
		delpanel.setVisible(false);
		
		GoodsIDdeltext = new JTextField();
		GoodsIDdeltext.setColumns(10);
		
		lblNewLabel_6 = new JLabel("商品编号");
		lblNewLabel_6.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		JButton btnNewButton_4_1 = new JButton("确定");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DelGoods(e);
				GoodsIDdeltext.setText(null);
				delpanel.setVisible(false);
			}

		});
		
		JButton btnNewButton_5_1 = new JButton("取消");
		btnNewButton_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoodsIDdeltext.setText(null);
				delpanel.setVisible(false);
			}
		});
		GroupLayout gl_delpanel = new GroupLayout(delpanel);
		gl_delpanel.setHorizontalGroup(
			gl_delpanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_delpanel.createSequentialGroup()
					.addGap(35)
					.addComponent(lblNewLabel_6)
					.addGap(18)
					.addComponent(GoodsIDdeltext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(37, Short.MAX_VALUE))
				.addGroup(gl_delpanel.createSequentialGroup()
					.addContainerGap(49, Short.MAX_VALUE)
					.addComponent(btnNewButton_4_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addGap(54)
					.addComponent(btnNewButton_5_1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_delpanel.setVerticalGroup(
			gl_delpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_delpanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_delpanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_6)
						.addComponent(GoodsIDdeltext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
					.addGroup(gl_delpanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNewButton_5_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_4_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(19))
		);
		delpanel.setLayout(gl_delpanel);
		
		
		addpanel = new JPanel();
		addpanel.setBounds(10, 19, 334, 245);
		addpanel.setVisible(false);
		getFrame().getContentPane().add(addpanel);
		
		
		GoodsIDtext = new JTextField();
		GoodsIDtext.setColumns(10);
		
		GoodsNametext = new JTextField();
		GoodsNametext.setColumns(10);
		
		Goodspricetext = new JTextField();
		Goodspricetext.setColumns(10);
		
		
		GoodsNumebrtext = new JTextField();
		GoodsNumebrtext.setColumns(10);
		
		lblNewLabel_2 = new JLabel("商品名称");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		lblNewLabel_3 = new JLabel("商品编号");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		lblNewLabel_4 = new JLabel("单价");
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		lblNewLabel_5 = new JLabel("库存");
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		btnNewButton_4 = new JButton("确定");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				AddGoods(e);
				GoodsIDtext.setText(null);
				GoodsNametext.setText(null);
				Goodspricetext.setText(null);
				GoodsNumebrtext.setText(null);
				addpanel.setVisible(false);
			}
		});
		
		btnNewButton_5 = new JButton("取消");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoodsIDtext.setText(null);
				GoodsNametext.setText(null);
				Goodspricetext.setText(null);
				GoodsNumebrtext.setText(null);
				addpanel.setVisible(false);
			}
		});
		GroupLayout gl_panel = new GroupLayout(addpanel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_4)
								.addComponent(lblNewLabel_5)
								.addComponent(btnNewButton_4))
							.addGap(18))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addGap(8))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(GoodsIDtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Goodspricetext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(GoodsNumebrtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(GoodsNametext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(56)
							.addComponent(btnNewButton_5)))
					.addContainerGap(80, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(GoodsIDtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(GoodsNametext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(Goodspricetext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(GoodsNumebrtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_4)
						.addComponent(btnNewButton_5))
					.addContainerGap(208, Short.MAX_VALUE))
		);
		addpanel.setLayout(gl_panel);
		
		JLabel lblNewLabel = new JLabel("营业额：");
		lblNewLabel.setBounds(185, 19, 80, 30);
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		lblNewLabel.setEnabled(false);
		textField = new JTextField();
		textField.setBounds(269, 17, 129, 32);
		textField.setFont(new Font("微软雅黑", Font.BOLD, 20));
		textField.setEnabled(false);
		textField.setColumns(10);
		textField.setText(total+"");
		
		btnNewButton = new JButton("");
		btnNewButton.setBounds(10, 123, 60, 25);
		btnNewButton.setIcon(new ImageIcon("src/main/resources/image/Goods_delete.jpg"));
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				delpanel.setVisible(true);
				if(!GoodsIDdeltext.getText().matches("[0-9]*")) {
					System.out.println("请输入正确的ID");
				}
			}
		});
		
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(10, 274, 60, 25);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setIcon(new ImageIcon("src/main/resources/image/退出.jpg"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		
		btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addpanel.setVisible(true);
			}
		});
		
		btnNewButton_2.setIcon(new ImageIcon("src/main/resources/image/Goods_addgoods.jpg"));
		btnNewButton_2.setFont(new Font("微软雅黑", Font.BOLD, 20));
		btnNewButton_2.setBounds(10, 199, 60, 25);
		
		btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchGood(e);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon("src/main/resources/image/search_Goods.jpg"));
		btnNewButton_3.setFont(new Font("微软雅黑", Font.BOLD, 20));
		btnNewButton_3.setBounds(20, 19, 37, 30);
		
		getFrame().getContentPane().add(btnNewButton_3);
		
		getFrame().getContentPane().add(btnNewButton_2);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(80, 55, 452, 351);
		scrollPane.setBackground(new Color(0,0,0,0));
		
		table = new JTable();
		table.setBackground(new Color(0,0,0,0));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setRowHeight(25);
		show();
		table.getColumnModel().getColumn(3).setPreferredWidth(79);
		
		
		TableColumn tableColumn = table.getColumn("库存");
		tableColumn.setCellEditor(cellEditor);
		
		
		TableColumn tableColumnd = table.getColumn("单价");
		tableColumnd.setCellEditor(cellEditor_db);
		
		TableModel tableModel = table.getModel();
		tableModel.addTableModelListener(new TableModelListener() {
		    @Override
		    public void tableChanged(TableModelEvent e) {
		        // 第一个 和 最后一个 被改变的行（只改变了一行，则两者相同）
		        int firstRow = e.getFirstRow();
		        int lastRow = e.getLastRow();

		        // 被改变的列
		        int column = e.getColumn();

		        // 事件的类型，可能的值有:
		        //     TableModelEvent.INSERT   新行或新列的添加
		        //     TableModelEvent.UPDATE   现有数据的更改
		        //     TableModelEvent.DELETE   有行或列被移除
		        int type = e.getType();
		        if (type == TableModelEvent.UPDATE) {
		        	double t=0.0;
		        	if(column==3) {
		        		
		        		Message mes =new Message();
		        		mes.setMessageType(MessageType.AddNumberofGoods);
		        		mes.setModuleType(ModuleType.Shop);
		        		Client client=new Client(ClientMainFrame.socket);
		        		String[] temp=new String[2];
		        		System.out.println(firstRow);
		        		System.out.println(table.getValueAt(firstRow,0));
		        		temp[0]=table.getValueAt(firstRow,0)+"";
		        		temp[1]=table.getValueAt(firstRow,3)+"";
		        		mes.setData(temp);
		        		Message serverResponse= client.sendRequestToServer(mes);
		        	}
		        	else if(column==2) {
		        		Message mes =new Message();
		        		mes.setMessageType(MessageType.ModifyGoodsPrice);
		        		mes.setModuleType(ModuleType.Shop);
		        		Client client=new Client(ClientMainFrame.socket);
		        		String[] temp=new String[2];
		        		System.out.println(firstRow);
		        		System.out.println(table.getValueAt(firstRow,0));
		        		temp[0]=table.getValueAt(firstRow,0)+"";
		        		temp[1]=table.getValueAt(firstRow,2)+"";
		        		mes.setData(temp);
		        		Message serverResponse= client.sendRequestToServer(mes);
		        	}
		        	//else return;
		        }
		    }
		});
		getFrame().getContentPane().setLayout(null);
		getFrame().getContentPane().add(lblNewLabel);
		getFrame().getContentPane().add(textField);
		getFrame().getContentPane().add(btnNewButton);
		getFrame().getContentPane().add(btnNewButton_1);
		getFrame().getContentPane().add(scrollPane);
		
		Searchtext = new JTextField();
		Searchtext.setBounds(65, 19, 105, 30);
		getFrame().getContentPane().add(Searchtext);
		Searchtext.setColumns(10);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("src/main/resources/image/shop_manager_bg.jpg"));
		lblNewLabel_1.setBounds(0, 0, 561, 406);
		getFrame().getContentPane().add(lblNewLabel_1);
		
		btnNewButton_6 = new JButton("");
		btnNewButton_6.setIcon(new ImageIcon("src/main/resources/image/Goods_refresh.png"));
		btnNewButton_6.setBounds(459, 0, 50, 50);
		frame.getContentPane().add(btnNewButton_6);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show();
			}
		});
	
		
		
		
		// 居中显示
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(2);
	}
	
	private void DelGoods(ActionEvent e) {
		// TODO 自动生成的方法存根
		
		Message mes =new Message();
		mes.setData(Integer.parseInt(GoodsIDdeltext.getText()));
		mes.setMessageType(MessageType.GoodsDelete);
		mes.setModuleType(ModuleType.Shop);
		Client client=new Client(ClientMainFrame.socket);
		
		Message serverResponse = client.sendRequestToServer(mes); 
		int res=(int)serverResponse.getData();
		show();
		
	}
	
	private void AddGoods(ActionEvent e) {
		// TODO 自动生成的方法存根
		Goods temp=new Goods(Integer.parseInt(GoodsIDtext.getText()),GoodsNametext.getText(),
				Double.parseDouble(Goodspricetext.getText()),Integer.parseInt(GoodsNumebrtext.getText()));
		
		Message mes =new Message();
		mes.setData(temp);
		mes.setModuleType(ModuleType.Shop);
		mes.setMessageType(MessageType.GoodsAdd);
		Client client=new Client(ClientMainFrame.socket);

		Message serverResponse = client.sendRequestToServer(mes); 
		int res=(int)serverResponse.getData();
	    
		
		show();
	}
	
	private void SearchGood(ActionEvent e) {
		// TODO 自动生成的方法存根
		Message mes =new Message();
		mes.setData(Searchtext.getText());
		mes.setModuleType(ModuleType.Shop);
		if(Searchtext.getText()==null)return;
		if(Searchtext.getText().matches("[0-9]*")) {//商品ID查找
		mes.setMessageType(MessageType.GoodsSearch_ID);
		Client client=new Client(ClientMainFrame.socket);
		Message serverResponse = client.sendRequestToServer(mes); 
		Goods res=(Goods)serverResponse.getData();
		
		DefaultTableModel tablemodel;
		tablemodel=new DefaultTableModel(new Object[][] {},new String[] {
				"商品编号", "商品名称", "单价", "库存"}) {

				
				/*
				 * overload the method to change the table's factor
				 */
						boolean[] columnEditables = new boolean[] {
								false, false,true, true
							};
				@Override
				public boolean isCellEditable(int row, int column) {

					return columnEditables[column];
				}
		};
		String temp[]=new String[4];
		temp[0]=res.getGoodsid()+"";
		temp[1]=res.getGoodsname();
		temp[2]=res.getGoodsprice()+"";
		temp[3]=res.getGoodsnumber()+"";
		tablemodel.addRow(temp);
		table.setModel(tablemodel);
		//System.out.println("1");
		
		}
		else{
			mes.setMessageType(MessageType.GoodsSearch_Name);
			
			Client client=new Client(ClientMainFrame.socket);
			Message serverResponse= client.sendRequestToServer(mes); 
			ArrayList<Goods> res=(ArrayList<Goods>)serverResponse.getData();
			
			DefaultTableModel tablemodel;
			tablemodel=new DefaultTableModel(new Object[][] {},new String[] {
					"商品编号", "商品名称", "单价", "库存"}) {

					
					/*
					 * overload the method to change the table's factor
					 */
							boolean[] columnEditables = new boolean[] {
									false, false,true, true
								};
					@Override
					public boolean isCellEditable(int row, int column) {

						return columnEditables[column];
					}
			};
			for(int i=0;i<res.size();i++) {
				String tempgoods[]=new String[4];
				tempgoods[0]=res.get(i).getGoodsid()+"";
				tempgoods[1]=res.get(i).getGoodsname();
				tempgoods[2]=res.get(i).getGoodsprice()+"";
				tempgoods[3]=res.get(i).getGoodsnumber()+"";
				tablemodel.addRow(tempgoods);
			}
			table.setModel(tablemodel);
			//System.out.println("2");
		}
	}
	
	private void get_turnover() {
		Message mes =new Message();
		mes.setMessageType(MessageType.Goodsgetturnover);
		mes.setModuleType(ModuleType.Shop);
		Client client=new Client(ClientMainFrame.socket);
		Message serverResponse= client.sendRequestToServer(mes); 
		Double total = (Double)serverResponse.getData();
		textField.setText(total+"");
	}
	
	public void show() {	
		Message mes =new Message();
		mes.setMessageType(MessageType.Goodsgetall);
		mes.setModuleType(ModuleType.Shop);
		Client client=new Client(ClientMainFrame.socket);
		Message serverResponse= client.sendRequestToServer(mes); 
		GoodsList = (ArrayList<Goods>)serverResponse.getData();
		
		
		DefaultTableModel tablemodel;
		tablemodel=new DefaultTableModel(new Object[][] {},new String[] {
					"商品编号", "商品名称", "单价", "库存"}) {

					
					/*
					 * overload the method to change the table's factor
					 */
							boolean[] columnEditables = new boolean[] {
									false, false,true, true
								};
					@Override
					public boolean isCellEditable(int row, int column) {

						return columnEditables[column];
					}
			};
		for(int i=0;i<GoodsList.size();i++) {
				String tempgoods[]=new String[4];
				tempgoods[0]=GoodsList.get(i).getGoodsid()+"";
				tempgoods[1]=GoodsList.get(i).getGoodsname();
				tempgoods[2]=GoodsList.get(i).getGoodsprice()+"";
				tempgoods[3]=GoodsList.get(i).getGoodsnumber()+"";
				tablemodel.addRow(tempgoods);
			}
			table.setModel(tablemodel);
			
			table.setModel(tablemodel);
			get_turnover();
	}
	 
	 public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public static class MyCellEditor_int extends DefaultCellEditor {

	        public MyCellEditor_int(JTextField textField) {
	            super(textField);
	        }
	        @Override
	        public boolean stopCellEditing() {
	            // 获取当前单元格的编辑器组件
	            Component comp = getComponent();

	            // 获取当前单元格编辑器输入的值
	            Object obj = getCellEditorValue();

	            // 如果当前单元格编辑器输入的值不是数字，则返回 false（表示数据非法，不允许设置，无法保存）
	            if (obj == null || !obj.toString().matches("[0-9]*")) {
	                // 数据非法时，设置编辑器组件内的内容颜色为红色
	                comp.setForeground(Color.RED);
	                return false;
	            }

	            // 数据合法时，设置编辑器组件内的内容颜色为黑色
	            comp.setForeground(Color.BLACK);

	            // 合法数据交给父类处理
	            return super.stopCellEditing();
	        }
	    }
	 
	 
	 public static class MyCellEditor_double extends DefaultCellEditor {

	        public MyCellEditor_double(JTextField textField) {
	            super(textField);
	        }
	        @Override
	        public boolean stopCellEditing() {
	            // 获取当前单元格的编辑器组件
	            Component comp = getComponent();

	            // 获取当前单元格编辑器输入的值
	            Object obj = getCellEditorValue();

	            // 如果当前单元格编辑器输入的值不是double，则返回 false（表示数据非法，不允许设置，无法保存）
	            if (obj == null || !obj.toString().matches("[0-9]+[.]{0,1}[0-9]*[dD]{0,1}")) {
	                // 数据非法时，设置编辑器组件内的内容颜色为红色
	                comp.setForeground(Color.RED);
	                return false;
	            }

	            // 数据合法时，设置编辑器组件内的内容颜色为黑色
	            comp.setForeground(Color.BLACK);
    
	            // 合法数据交给父类处理
	            return super.stopCellEditing();
	        }
	    }
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
