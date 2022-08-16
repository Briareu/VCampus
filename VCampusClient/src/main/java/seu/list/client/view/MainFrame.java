package seu.list.client.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame
{
   // 默认表格模型
   private DefaultTableModel model = null;
   private JTable table = null;
   private JButton addBtn = null;
   private JButton addBtn1 = null;
   final JFrame jf = new JFrame("测试窗口");
   public MainFrame()
   {
      super("管理员视图商店");
      String[][] datas = {};
      String[] titles = { "名称","商品编号", "单价","库存" };
      model = new DefaultTableModel(datas, titles);
      table = new JTable(model);
      model.addRow(new Object[] {"面包","001",5,100 });
      model.addRow(new Object[] {"矿泉水","002",2,100 });
      addBtn = new JButton("增加数据");
      addBtn.setBounds(0, 500, 90, 25);
      addBtn1 = new JButton("修改数据");
      addBtn1.setBounds(100, 500, 90,25 );
      //增加数据按钮的响应
      addBtn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e)
         {
        	// Object[] an= addnew();
        	 JFrame newJFrame = new JFrame("输入要增加的商品信息");
             newJFrame.setSize(500, 250);
             newJFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
             JPanel panel = new JPanel();

          // 创建文本框，输入增加商品信息
          Object[] an= new Object[4];
          final JTextField textField = new JTextField(6);
          textField.setFont(new Font(null, Font.PLAIN, 20));
          panel.add(textField);
          final JTextField textField1 = new JTextField(6);
          textField1.setFont(new Font(null, Font.PLAIN, 20));
          panel.add(textField1);
          final JTextField textField2 = new JTextField(6);
          textField2.setFont(new Font(null, Font.PLAIN, 20));
          panel.add(textField2);
          final JTextField textField3 = new JTextField(6);
          textField3.setFont(new Font(null, Font.PLAIN, 20));
          panel.add(textField3);
          // 创建一个按钮，点击后获取文本框中的文本

          JButton btn = new JButton("提交");

          btn.setFont(new Font(null, Font.PLAIN, 20));

          btn.addActionListener(new ActionListener() {

          @Override

          public void actionPerformed(ActionEvent e) {
          an[0]=textField.getText();
          an[1]=textField1.getText();
          an[2]=textField2.getText();
          an[3]=textField3.getText();
          model.addRow(an);
          }
          });
          
          panel.add(btn);
          newJFrame.setContentPane(panel);
          newJFrame.setVisible(true);

         }
      });
      //修改按钮的响应（暂未完成）
      addBtn1.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e)
          {
            
          }
       });
      add(addBtn);
      add(addBtn1);
      add(new JScrollPane(table));

      setSize(600, 620);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);

   }
   public static void main(String[] args) {
	   MainFrame a=new MainFrame();
   }
}