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
/**
 * 类{@code Shop_StudentFrame}为商店的管理员界面
 * 如果用户为管理员则进入商店时跳转到此界面
 * @author 欧阳瑜
 * @version 1.0
 */
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
	
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JLabel lblNewLabel_6;
	private JButton btnNewButton_6;
	private JLabel lblNewLabel_7;
	

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
	 * 类{@code Shop_AdminFrame}的构造器
	 * 
	 */
	public Shop_AdminFrame() {
		initialize();
		GoodsList=new ArrayList<Goods>();
		frame.setVisible(true);
		
	}

	/**
	 * 构造ui
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
						
		setFrame(new JFrame());
		getFrame().setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/image/shop_manager_bg.jpg"));
		getFrame().setFont(new Font("微软雅黑", Font.BOLD, 17));
		getFrame().setTitle("管理员视图商店");
		getFrame().getContentPane().setBackground(Color.WHITE);
		getFrame().getContentPane().setBounds(0,0,800,532);
		getFrame().setBackground(Color.WHITE);
		getFrame().setBounds(100, 100, 800, 522);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		double total=0.0;
			
		MyCellEditor_double cellEditor_db = new MyCellEditor_double(new JTextField());
		MyCellEditor_int cellEditor = new MyCellEditor_int(new JTextField());
		JLabel lblNewLabel = new JLabel("营业额：");
		lblNewLabel.setBounds(294, 19, 80, 30);
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		lblNewLabel.setEnabled(false);
		textField = new JTextField();
		textField.setBounds(416, 20, 129, 32);
		textField.setFont(new Font("微软雅黑", Font.BOLD, 20));
		textField.setEnabled(false);
		textField.setColumns(10);
		textField.setText(total+"");
		
		lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\admin\\Desktop\\money.png"));
		lblNewLabel_7.setBounds(366, 13, 37, 37);
		frame.getContentPane().add(lblNewLabel_7);
		
		btnNewButton = new JButton("");
		btnNewButton.setBounds(10, 102, 60, 25);
		btnNewButton.setIcon(new ImageIcon("src/main/resources/image/Goods_delete.jpg"));
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				DelGoods(e);
			}
		});
		
		btnNewButton_6 = new JButton("");
		btnNewButton_6.setIcon(new ImageIcon("src/main/resources/image/Goods_refresh.png"));
		btnNewButton_6.setBounds(664, 2, 50, 50);
		frame.getContentPane().add(btnNewButton_6);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show();
			}
		});
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(10, 331, 60, 25);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setIcon(new ImageIcon("src/main/resources/image/退出.jpg"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGoods(e);
			}
		});
		
		btnNewButton_2.setIcon(new ImageIcon("src/main/resources/image/Goods_addgoods.jpg"));
		btnNewButton_2.setFont(new Font("微软雅黑", Font.BOLD, 20));
		btnNewButton_2.setBounds(10, 212, 60, 25);
		
		btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchGood(e);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon("src/main/resources/image/search_Goods.jpg"));
		btnNewButton_3.setFont(new Font("微软雅黑", Font.BOLD, 20));
		btnNewButton_3.setBounds(75, 20, 37, 30);
		
		getFrame().getContentPane().add(btnNewButton_3);
		
		getFrame().getContentPane().add(btnNewButton_2);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(80, 55, 653, 406);
		scrollPane.getViewport().setBackground(new Color(0,0,0,0));
		
		setTable(new JTable());
		getTable().setBackground(Color.WHITE);
		scrollPane.setViewportView(getTable());
		getTable().setBorder(new LineBorder(new Color(0, 0, 0)));
		getTable().setRowHeight(25);
		getTable().getTableHeader().setReorderingAllowed(false);
		show();
		getTable().getColumnModel().getColumn(3).setPreferredWidth(79);
		getFrame().getContentPane().setLayout(null);
		getFrame().getContentPane().add(lblNewLabel);
		getFrame().getContentPane().add(textField);
		getFrame().getContentPane().add(btnNewButton);
		getFrame().getContentPane().add(btnNewButton_1);
		getFrame().getContentPane().add(scrollPane);
		
		Searchtext = new JTextField();
		Searchtext.setBounds(122, 20, 105, 30);
		getFrame().getContentPane().add(Searchtext);
		Searchtext.setColumns(10);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\admin\\Desktop\\shop_back.jpg"));
		lblNewLabel_1.setBounds(0, 0, 800, 532);
		getFrame().getContentPane().add(lblNewLabel_1);
		
		
		// 居中显示
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(2);
	}
	/**  
	 * 方法{@code  void modify()}点击表格修改后触发此函数，完成修改相关的功能， 
	 */
	protected void modify() {
		// TODO 自动生成的方法存根
		Goods_modifyprice a=new Goods_modifyprice(this);
	}

	/**  
	 * 方法{@code  void DelGoods(ActionEvent e)}点击“删除”按钮后触发此函数，完成删除相关的功能
	 */
	private void DelGoods(ActionEvent e) {
		// TODO 自动生成的方法存根
        Shop_DeleteGoods a=new Shop_DeleteGoods(this);		
	}
	/**  
	 * 方法{@code  void AddGoods(ActionEvent e)}点击“增加”按钮后触发此函数，完成增加相关的功能
	 */
	private void AddGoods(ActionEvent e) {
		// TODO 自动生成的方法存根
		Goods_Addframe a=new Goods_Addframe(this);
	}
	/**  
	 * 方法{@code  void SearchGood(ActionEvent e)}在搜素框输入内容并点击搜索按钮后触发，完成搜素相关功能
	 */
	private void SearchGood(ActionEvent e) {
		// TODO 自动生成的方法存根
		Message mes =new Message();
		mes.setData(Searchtext.getText());
		mes.setModuleType(ModuleType.Shop);
		if(((String)Searchtext.getText()).equals("")) {
			show();
			return;
			}
		if(Searchtext.getText().matches("[0-9]*")) {//商品ID查找
		mes.setMessageType(MessageType.GoodsSearch_ID);
		
		Client client=new Client(ClientMainFrame.socket);
		Message serverResponse = client.sendRequestToServer(mes); 
		ArrayList<Goods> res=(ArrayList<Goods>)serverResponse.getData();
		
		MyCellEditor_double cellEditor_db = new MyCellEditor_double(new JTextField());
		MyCellEditor_int cellEditor = new MyCellEditor_int(new JTextField());
		
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
		getTable().setModel(tablemodel);
		//System.out.println("1");
		TableColumn tableColumn = getTable().getColumn("库存");
		tableColumn.setCellEditor(cellEditor);
		
		TableColumn tableColumnd = getTable().getColumn("单价");
		tableColumnd.setCellEditor(cellEditor_db);
		
		
		table.getModel().addTableModelListener(new TableModelListener() {
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
		        	modify();
		        }
		    }
		});
		}
		else{
			mes.setMessageType(MessageType.GoodsSearch_Name);
			
			Client client=new Client(ClientMainFrame.socket);
			Message serverResponse= client.sendRequestToServer(mes); 
			ArrayList<Goods> res=(ArrayList<Goods>)serverResponse.getData();
			
			MyCellEditor_double cellEditor_db = new MyCellEditor_double(new JTextField());
			MyCellEditor_int cellEditor = new MyCellEditor_int(new JTextField());
			
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
			getTable().setModel(tablemodel);
			TableColumn tableColumn = getTable().getColumn("库存");
			tableColumn.setCellEditor(cellEditor);
			
			TableColumn tableColumnd = getTable().getColumn("单价");
			tableColumnd.setCellEditor(cellEditor_db);
			
			
			table.getModel().addTableModelListener(new TableModelListener() {
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
			        	modify();
			        }
			    }
			});
		}
	}
	/**  
	 * 方法{@code  void get_turnover()}获得营业额并显示在前端
	 */
	private void get_turnover() {
		Message mes =new Message();
		mes.setMessageType(MessageType.Goodsgetturnover);
		mes.setModuleType(ModuleType.Shop);
		Client client=new Client(ClientMainFrame.socket);
		Message serverResponse= client.sendRequestToServer(mes); 
		Double total = (Double)serverResponse.getData();
		textField.setText(total+"");
	}
	/**  
	 * 方法{@code  void show()}在表格呈现所有商品相关的信息
	 */
	public void show() {	
		MyCellEditor_double cellEditor_db = new MyCellEditor_double(new JTextField());
		MyCellEditor_int cellEditor = new MyCellEditor_int(new JTextField());
		
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
			getTable().setModel(tablemodel);
			
			TableColumn tableColumn = getTable().getColumn("库存");
			tableColumn.setCellEditor(cellEditor);
			
			TableColumn tableColumnd = getTable().getColumn("单价");
			tableColumnd.setCellEditor(cellEditor_db);
			
			table.getModel().addTableModelListener(new TableModelListener() {
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
			        	modify();
			        }
			    }
			});
			get_turnover();
	}
	 
	 public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
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
	
}
