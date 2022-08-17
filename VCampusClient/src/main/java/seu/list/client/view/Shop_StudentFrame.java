package Vshopclient;

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

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
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
import java.awt.Toolkit;

public class Shop_StudentFrame {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Shop_StudentFrame window = new Shop_StudentFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Shop_StudentFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\admin\\Desktop\\登录背景.jpg"));
		frame.setFont(new Font("微软雅黑", Font.BOLD, 17));
		frame.setTitle("学生视图商店");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 577, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		double total=0.0;
		
		JLabel lblNewLabel = new JLabel("总计：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		lblNewLabel.setEnabled(false);
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.BOLD, 20));
		textField.setEnabled(false);
		textField.setColumns(10);
		textField.setText(total+"");
		
		btnNewButton = new JButton("结账");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//
			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		// JScrollPane scrollPane = new JScrollPane(table);
		
		btnNewButton_1 = new JButton("退出");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//响应
			}
		});
		btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addGap(250))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton_1)
								.addComponent(btnNewButton))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(116)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(106)
							.addComponent(btnNewButton)
							.addGap(34)
							.addComponent(btnNewButton_1)))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		
		table = new JTable();
		
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setRowHeight(25);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "Bread", "5", "100", "0"},
			},
			new String[] {
				"GoodsID", "GoodsName", "GoodsPrice", "GoodsNumber", "Number"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		MyCellEditor cellEditor = new MyCellEditor(new JTextField());
		TableColumn tableColumn = table.getColumn("Number");
		tableColumn.setCellEditor(cellEditor);//确保输入合法

		table.getColumnModel().getColumn(3).setPreferredWidth(79);
		
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
		        	if(column==4) {
		        		 for (int row = firstRow; row <= lastRow; row++) {
		        			 Object tempnumber=tableModel.getValueAt(row, 4);
		        			 Object tempprice=tableModel.getValueAt(row, 2);
		        			 double tem=Double.parseDouble((String)tempprice);
		        			int tem1=Integer.parseInt((String)tempnumber);
		        			t+=tem*tem1;
		        			//t+=1;
		        		 }
		        		 textField.setText(t+"");
		        	}
		        	else return;
		        }
		    }
		});
		frame.getContentPane().setLayout(groupLayout);
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
}
