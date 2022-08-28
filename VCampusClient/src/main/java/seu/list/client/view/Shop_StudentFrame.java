package seu.list.client.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
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
import java.awt.Dialog.ModalExclusionType;

public class Shop_StudentFrame {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_1;
	private JTextField SearchText;
	private JButton btnNewButton_2;
	
	//总价
	private double sum = 0.0;
	private String id = null;
	private String PWD = null;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Shop_StudentFrame(String id, String PWD) {
		this.id = id;
		this.PWD = PWD;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		sum = 0.0;
		
		frame = new JFrame();
		frame.setFont(new Font("微软雅黑", Font.BOLD, 17));
		frame.setTitle("学生视图商店");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 577, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		double total=0.0;
		
		JLabel lblNewLabel = new JLabel("总计：");
		lblNewLabel.setBounds(205, 19, 60, 28);
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		lblNewLabel.setEnabled(false);
		textField = new JTextField();
		textField.setBounds(269, 17, 129, 32);
		textField.setFont(new Font("微软雅黑", Font.BOLD, 20));
		textField.setEnabled(false);
		textField.setColumns(10);
//		textField.setText(total+"");
		textField.setText(sum + "");
		
		btnNewButton = new JButton("");
		btnNewButton.setBounds(10, 123, 60, 25);
		btnNewButton.setIcon(new ImageIcon("src/main/resources/image/结账.jpg"));
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				buy();
				setPayFrame();
			}
		});
		
		// JScrollPane scrollPane = new JScrollPane(table);
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(10, 210, 60, 25);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setIcon(new ImageIcon("src/main/resources/image/退出.jpg"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//退出
				
				//增加了退出事件
				frame.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
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
		
		MyCellEditor cellEditor = new MyCellEditor(new JTextField());
		TableColumn tableColumn = table.getColumn("数量");
		tableColumn.setCellEditor(cellEditor);//确保输入合法

		table.getColumnModel().getColumn(3).setPreferredWidth(79);
		
		final TableModel tableModel = table.getModel();
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
//		        	double t=0.0;
		        	sum = 0.0;
		        	if(column==4) {
		        		 for (int row =0; row <table.getRowCount(); row++) {
		        			 Object tempnumber=tableModel.getValueAt(row, 4);
		        			 Object tempprice=tableModel.getValueAt(row, 2);
		        			 double tem=Double.parseDouble((String)tempprice);
		        			int tem1=Integer.parseInt((String)tempnumber);
//		        			t+=tem*tem1;
		        			sum += tem*tem1;
		        		
		        		 }
//		        		 textField.setText(t+"");
		        		 textField.setText(sum + "");
		        	}
		        	else return;
		        }
		    }
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblNewLabel);
		frame.getContentPane().add(textField);
		frame.getContentPane().add(btnNewButton);
		frame.getContentPane().add(btnNewButton_1);
		frame.getContentPane().add(scrollPane);
		
		SearchText = new JTextField();
		SearchText.setColumns(10);
		SearchText.setBounds(72, 19, 105, 30);
			
		btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchGood(e);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("src/main/resources/image/search_Goods.jpg"));
		btnNewButton_2.setFont(new Font("微软雅黑", Font.BOLD, 20));
		btnNewButton_2.setBounds(27, 19, 37, 30);
		frame.getContentPane().add(btnNewButton_2);
		
		frame.getContentPane().add(SearchText);
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("src\\main\\resources\\image\\shop_buyer_bg.jpg"));
		lblNewLabel_1.setBounds(0, 0, 561, 406);
		frame.getContentPane().add(lblNewLabel_1);
		
		

		//重写关闭事件和窗口居中
		frame.setDefaultCloseOperation(2);
		frame.setLocationRelativeTo(null);
	}
	
	private void show() {
		// TODO 自动生成的方法存根
		Message mes =new Message();
		mes.setMessageType(MessageType.Goodsgetall);
		mes.setModuleType(ModuleType.Shop);
		Client client=new Client(ClientMainFrame.socket);
		Message serverResponse= client.sendRequestToServer(mes); 
		ArrayList<Goods> Goodslist = (ArrayList<Goods>)serverResponse.getData();
		DefaultTableModel tablemodel;
		
		tablemodel=new DefaultTableModel(new Object[][] {},new String[] {
				"商品编号", "商品名称", "单价", "库存","数量"}) {

				 
						boolean[] columnEditables = new boolean[] {
								false, false,false, false,true
							};
				@Override
				public boolean isCellEditable(int row, int column) {

					return columnEditables[column];
				}
		};
		for(int i=0;i<Goodslist.size();i++) {
			String tempgoods[]=new String[5];
			tempgoods[0]=Goodslist.get(i).getGoodsid()+"";
			tempgoods[1]=Goodslist.get(i).getGoodsname();
			tempgoods[2]=Goodslist.get(i).getGoodsprice()+"";
			tempgoods[3]=Goodslist.get(i).getGoodsnumber()+"";
			tempgoods[4]="0";
			tablemodel.addRow(tempgoods);
		}
		table.setModel(tablemodel);
	}
	
	private void SearchGood(ActionEvent e) {
		// TODO 自动生成的方法存根
		Message mes =new Message();
		mes.setData(SearchText.getText());
		mes.setModuleType(ModuleType.Shop);
		if(SearchText.getText()==null)return;
		if(SearchText.getText().matches("[0-9]*")) {//商品ID查找
		mes.setMessageType(MessageType.GoodsSearch_ID);
		Client client=new Client(ClientMainFrame.socket);
		Message serverResponse = client.sendRequestToServer(mes); 
		Goods res=(Goods)serverResponse.getData();
		
		DefaultTableModel tablemodel;
		tablemodel=new DefaultTableModel(new Object[][] {},new String[] {
				"商品编号", "商品名称", "单价", "库存","数量"}) {

				
				/*
				 * overload the method to change the table's factor
				 */
						boolean[] columnEditables = new boolean[] {
								false, false,false, false,true
							};
				@Override
				public boolean isCellEditable(int row, int column) {

					return columnEditables[column];
				}
		};
		String temp[]=new String[5];
		temp[0]=res.getGoodsid()+"";
		temp[1]=res.getGoodsname();
		temp[2]=res.getGoodsprice()+"";
		temp[3]=res.getGoodsnumber()+"";
		temp[4]="0";
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
					"商品编号", "商品名称", "单价", "库存","数量"}) {

					
					/*
					 * overload the method to change the table's factor
					 */
							boolean[] columnEditables = new boolean[] {
									false, false,false, true,false
								};
					@Override
					public boolean isCellEditable(int row, int column) {

						return columnEditables[column];
					}
			};
			for(int i=0;i<res.size();i++) {
				String tempgoods[]=new String[5];
				tempgoods[0]=res.get(i).getGoodsid()+"";
				tempgoods[1]=res.get(i).getGoodsname();
				tempgoods[2]=res.get(i).getGoodsprice()+"";
				tempgoods[3]=res.get(i).getGoodsnumber()+"";
				tempgoods[4]="0";
				tablemodel.addRow(tempgoods);
			}
			table.setModel(tablemodel);
			//System.out.println("2");
		}
	}
	
	 protected void buy() {
		// TODO 自动生成的方法存根
		Message mes =new Message();
		mes.setModuleType(ModuleType.Shop);
		mes.setMessageType(MessageType.Buy);
		ArrayList<String> bgoods =new ArrayList<String>();
		for(int i=0;i<table.getRowCount();i++) {
			String id=(String)table.getValueAt(i,0);
			String number=(String)table.getValueAt(i,4);
			//int tempid=Integer.parseInt(id);
			int temp=Integer.parseInt(number);
			if(temp!=0) {
				bgoods.add(id);
				bgoods.add(number);
			}
		}
		mes.setData(bgoods);
		mes.setModuleType(ModuleType.Shop);
		Client client=new Client(ClientMainFrame.socket);
		
		Message serverResponse= client.sendRequestToServer(mes); 
		//int res=(int)serverResponse.getData();
	}
	 
	public static class MyCellEditor extends DefaultCellEditor {

	        public MyCellEditor(JTextField textField) {
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
	
	void setPayFrame() {
		frame.setEnabled(false);
		frame.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);

		Shop_StudentForPay newframe = new Shop_StudentForPay(this, frame, sum, id, PWD);
		newframe.setVisible(true);
	}
}
