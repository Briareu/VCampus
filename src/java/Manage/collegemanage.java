package Manage;

import style.stylefont;
import tools.Table;

import javax.swing.*;
import java.awt.*;

public class collegemanage extends JPanel {
    int WIDTH;
    int HEIGHT;

    public collegemanage(int x,int y, int width,int height){
        this.WIDTH=width;
        this.HEIGHT=height;
        this.setBounds(x,y,width,height);
        init();
    }
    void init(){
        this.setBackground(new Color(255,255,255));
        this.setLayout(new FlowLayout(FlowLayout.LEFT,20,42));
        Box box1=Box.createHorizontalBox();
        Box boxH=Box.createVerticalBox();


        JLabel jLabel=new JLabel("学院id");
        jLabel.setFont(new Font("微软雅黑",Font.PLAIN,25));
        box1.add(jLabel);
        box1.add(Box.createHorizontalStrut(10));
        JTextField jTextField=new JTextField(12);
        jTextField.setFont(new Font("微软雅黑",Font.PLAIN,20));
        box1.add(jTextField);

        JButton jButton=new JButton("查找学院");
        Dimension preferredSize = new Dimension(150,40);
        jButton.setPreferredSize(preferredSize);
        jButton.setFont(stylefont.titlefont3);
        box1.add(Box.createHorizontalStrut(10));
        box1.add(jButton);

        JButton jButton2=new JButton("删除学院");
        Dimension preferredSize2 = new Dimension(150,40);
        jButton2.setPreferredSize(preferredSize);
        jButton2.setFont(stylefont.titlefont3);
        box1.add(Box.createHorizontalStrut(10));
        box1.add(jButton2);

        JButton jButton3=new JButton("更改学院");
        Dimension preferredSize3 = new Dimension(150,40);
        jButton3.setPreferredSize(preferredSize);
        jButton3.setFont(stylefont.titlefont3);
        box1.add(Box.createHorizontalStrut(10));
        box1.add(jButton3);

        Box box2=Box.createHorizontalBox();


        JLabel jLabel1=new JLabel("学院名称");
        jLabel1.setFont(new Font("微软雅黑",Font.PLAIN,25));
        box2.add(jLabel1);
        box2.add(Box.createHorizontalStrut(10));
        JTextField jTextField1=new JTextField(12);
        jTextField1.setFont(new Font("微软雅黑",Font.PLAIN,20));
        box2.add(jTextField1);

        JButton jButton1=new JButton("增加学院");
        Dimension preferredSize1 = new Dimension(150,40);
        jButton1.setPreferredSize(preferredSize1);
        jButton1.setFont(stylefont.titlefont3);
        box2.add(Box.createHorizontalStrut(10));
        box2.add(jButton1);
        box2.add(Box.createHorizontalStrut(160));

        boxH.add(box1);
        boxH.add(Box.createVerticalStrut(20));
        boxH.add(box2);

        this.add(boxH);
   //________________________________________________
        Object columns[]={"序号","学员编号","学院名字"};
        Table t1Table=new Table(columns);
        JTable jTable=t1Table.gettables();
        JScrollPane jScrollPane= t1Table.getjScrollPane();
        jScrollPane.setPreferredSize(new Dimension(WIDTH-100,200));
        jTable.setPreferredSize(new Dimension(WIDTH-1000,1000));
        this.add(jScrollPane);
    //——————————————————————————————————————————————————————————————————————
    }
    void settable(){


    }
}
