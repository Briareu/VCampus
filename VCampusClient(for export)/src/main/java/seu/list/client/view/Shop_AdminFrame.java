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
	
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
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
//		getFrame().setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/image/shop_manager_bg.jpg"));
		getFrame().setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/image/shop_manager_bg.jpg")));
		getFrame().setFont(new Font("????????????", Font.BOLD, 17));
		getFrame().setTitle("?????????????????????");
		getFrame().getContentPane().setBackground(Color.WHITE);
		getFrame().setBackground(Color.WHITE);
		getFrame().setBounds(100, 100, 577, 449);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		double total=0.0;
			
		MyCellEditor_double cellEditor_db = new MyCellEditor_double(new JTextField());
		MyCellEditor_int cellEditor = new MyCellEditor_int(new JTextField());
		JLabel lblNewLabel = new JLabel("????????????");
		lblNewLabel.setBounds(185, 19, 80, 30);
		lblNewLabel.setFont(new Font("????????????", Font.BOLD, 20));
		lblNewLabel.setEnabled(false);
		textField = new JTextField();
		textField.setBounds(269, 17, 129, 32);
		textField.setFont(new Font("????????????", Font.BOLD, 20));
		textField.setEnabled(false);
		textField.setColumns(10);
		textField.setText(total+"");
		
		btnNewButton = new JButton("");
		btnNewButton.setBounds(10, 123, 60, 25);
//		btnNewButton.setIcon(new ImageIcon("src/main/resources/image/Goods_delete.jpg"));
		btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/resources/image/Goods_delete.jpg")));
		btnNewButton.setFont(new Font("????????????", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				DelGoods(e);
			}
		});
		
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(10, 274, 60, 25);
		btnNewButton_1.setBackground(Color.WHITE);
//		btnNewButton_1.setIcon(new ImageIcon("src/main/resources/image/??????.jpg"));
		btnNewButton_1.setIcon(new ImageIcon(this.getClass().getResource("/resources/image/??????.jpg")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		btnNewButton_1.setFont(new Font("????????????", Font.BOLD, 20));
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGoods(e);
			}
		});
		
//		btnNewButton_2.setIcon(new ImageIcon("src/main/resources/image/Goods_addgoods.jpg"));
		btnNewButton_2.setIcon(new ImageIcon(this.getClass().getResource("/resources/image/Goods_addgoods.jpg")));
		btnNewButton_2.setFont(new Font("????????????", Font.BOLD, 20));
		btnNewButton_2.setBounds(10, 199, 60, 25);
		
		btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchGood(e);
			}
		});
//		btnNewButton_3.setIcon(new ImageIcon("src/main/resources/image/search_Goods.jpg"));
		btnNewButton_3.setIcon(new ImageIcon(this.getClass().getResource("/resources/image/search_Goods.jpg")));
		btnNewButton_3.setFont(new Font("????????????", Font.BOLD, 20));
		btnNewButton_3.setBounds(20, 19, 37, 30);
		
		getFrame().getContentPane().add(btnNewButton_3);
		
		getFrame().getContentPane().add(btnNewButton_2);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(80, 55, 452, 351);
		scrollPane.setBackground(new Color(0,0,0,0));
		
		setTable(new JTable());
		getTable().setBackground(Color.WHITE);
		scrollPane.setViewportView(getTable());
		getTable().setBorder(new LineBorder(new Color(0, 0, 0)));
		getTable().setRowHeight(25);
		getTable().getTableHeader().setReorderingAllowed(false);
		show();
		getTable().getColumnModel().getColumn(3).setPreferredWidth(79);
		TableColumn tableColumn = getTable().getColumn("??????");
		tableColumn.setCellEditor(cellEditor);
		
		
		TableColumn tableColumnd = getTable().getColumn("??????");
		tableColumnd.setCellEditor(cellEditor_db);
		
		TableModel tableModel = getTable().getModel();
		tableModel.addTableModelListener(new TableModelListener() {
		    @Override
		    public void tableChanged(TableModelEvent e) {
		        // ????????? ??? ???????????? ?????????????????????????????????????????????????????????
		        int firstRow = e.getFirstRow();
		        int lastRow = e.getLastRow();

		        // ???????????????
		        int column = e.getColumn();

		        // ?????????????????????????????????:
		        //     TableModelEvent.INSERT   ????????????????????????
		        //     TableModelEvent.UPDATE   ?????????????????????
		        //     TableModelEvent.DELETE   ?????????????????????
		        int type = e.getType();
		        if (type == TableModelEvent.UPDATE) {
		        	modify();
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
//		lblNewLabel_1.setIcon(new ImageIcon("src/main/resources/image/shop_manager_bg.jpg"));
		lblNewLabel_1.setIcon(new ImageIcon(this.getClass().getResource("/resources/image/shop_manager_bg.jpg")));
		lblNewLabel_1.setBounds(0, 0, 561, 406);
		getFrame().getContentPane().add(lblNewLabel_1);
		
		btnNewButton_6 = new JButton("");
//		btnNewButton_6.setIcon(new ImageIcon("src/main/resources/image/Goods_refresh.png"));
		btnNewButton_6.setIcon(new ImageIcon(this.getClass().getResource("/resources/image/Goods_refresh.png")));
		btnNewButton_6.setBounds(459, 0, 50, 50);
		frame.getContentPane().add(btnNewButton_6);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show();
			}
		});
		// ????????????
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(2);
	}
	
	protected void modify() {
		// TODO ???????????????????????????
		Goods_modifyprice a=new Goods_modifyprice(this);
	}

	private void DelGoods(ActionEvent e) {
		// TODO ???????????????????????????
        Shop_DeleteGoods a=new Shop_DeleteGoods(this);		
	}
	
	private void AddGoods(ActionEvent e) {
		// TODO ???????????????????????????
		Goods_Addframe a=new Goods_Addframe(this);
	}
	
	private void SearchGood(ActionEvent e) {
		// TODO ???????????????????????????
		Message mes =new Message();
		mes.setData(Searchtext.getText());
		mes.setModuleType(ModuleType.Shop);
		if(((String)Searchtext.getText()).equals("")) {
			show();
			return;
			}
		if(Searchtext.getText().matches("[0-9]*")) {//??????ID??????
		mes.setMessageType(MessageType.GoodsSearch_ID);
		
		Client client=new Client(ClientMainFrame.socket);
		Message serverResponse = client.sendRequestToServer(mes); 
		ArrayList<Goods> res=(ArrayList<Goods>)serverResponse.getData();
		
		DefaultTableModel tablemodel;
		tablemodel=new DefaultTableModel(new Object[][] {},new String[] {
				"????????????", "????????????", "??????", "??????"}) {

				
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
		
		}
		else{
			mes.setMessageType(MessageType.GoodsSearch_Name);
			
			Client client=new Client(ClientMainFrame.socket);
			Message serverResponse= client.sendRequestToServer(mes); 
			ArrayList<Goods> res=(ArrayList<Goods>)serverResponse.getData();
			
			DefaultTableModel tablemodel;
			tablemodel=new DefaultTableModel(new Object[][] {},new String[] {
					"????????????", "????????????", "??????", "??????"}) {

					
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
					"????????????", "????????????", "??????", "??????"}) {

					
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
			
			TableColumn tableColumn = getTable().getColumn("??????");
			tableColumn.setCellEditor(cellEditor);
			
			TableColumn tableColumnd = getTable().getColumn("??????");
			tableColumnd.setCellEditor(cellEditor_db);
			
			table.getModel().addTableModelListener(new TableModelListener() {
			    @Override
			    public void tableChanged(TableModelEvent e) {
			        // ????????? ??? ???????????? ?????????????????????????????????????????????????????????
			        int firstRow = e.getFirstRow();
			        int lastRow = e.getLastRow();

			        // ???????????????
			        int column = e.getColumn();

			        // ?????????????????????????????????:
			        //     TableModelEvent.INSERT   ????????????????????????
			        //     TableModelEvent.UPDATE   ?????????????????????
			        //     TableModelEvent.DELETE   ?????????????????????
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
	            // ???????????????????????????????????????
	            Component comp = getComponent();

	            // ??????????????????????????????????????????
	            Object obj = getCellEditorValue();

	            // ?????????????????????????????????????????????????????????????????? false?????????????????????????????????????????????????????????
	            if (obj == null || !obj.toString().matches("[0-9]*")) {
	                // ??????????????????????????????????????????????????????????????????
	                comp.setForeground(Color.RED);
	                return false;
	            }

	            // ??????????????????????????????????????????????????????????????????
	            comp.setForeground(Color.BLACK);

	            // ??????????????????????????????
	            return super.stopCellEditing();
	        }
	    }
	 
	 
	 public static class MyCellEditor_double extends DefaultCellEditor {

	        public MyCellEditor_double(JTextField textField) {
	            super(textField);
	        }
	        @Override
	        public boolean stopCellEditing() {
	            // ???????????????????????????????????????
	            Component comp = getComponent();

	            // ??????????????????????????????????????????
	            Object obj = getCellEditorValue();

	            // ????????????????????????????????????????????????double???????????? false?????????????????????????????????????????????????????????
	            if (obj == null || !obj.toString().matches("[0-9]+[.]{0,1}[0-9]*[dD]{0,1}")) {
	                // ??????????????????????????????????????????????????????????????????
	                comp.setForeground(Color.RED);
	                return false;
	            }

	            // ??????????????????????????????????????????????????????????????????
	            comp.setForeground(Color.BLACK);
    
	            // ??????????????????????????????
	            return super.stopCellEditing();
	        }
	    }
	
}
