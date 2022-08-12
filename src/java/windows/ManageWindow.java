package windows;

import Manage.collegemanage;
import style.stylefont;
import tools.EasyCode;
import tools.Tools;

import javax.swing.*;
import java.awt.*;

public class ManageWindow {
    JFrame jframe=new JFrame();
    final int WIDTH=1500;
    final int HEIGHT=870;

    public ManageWindow(){
        init();
        jframe.setResizable(false);//窗口是否可变
        jframe.setVisible(true);//窗口是否可见
        jframe.setDefaultCloseOperation(jframe.DISPOSE_ON_CLOSE);
        jframe.validate();//让组件生效
    }
    void init(){
        //设置窗口在屏幕中间,并设置大小
        Tools.setWindowspos(WIDTH,HEIGHT,jframe);
        EasyCode.strtileIcon(jframe);//设置标题和图标
        jframe.setLayout(null);

        //设置背景颜色
        JPanel jPanel1=new JPanel();
        jPanel1.setBackground(new Color(66,99,116));
        jPanel1.setLayout(null);
        jPanel1.setBounds(0,0,WIDTH,HEIGHT);
        jframe.add(jPanel1);

        JPanel jPanel2=new JPanel();
        jPanel2.setBackground(new Color(255,255,255));
        jPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        jPanel2.setBounds(10,20,400,HEIGHT-95);
        jPanel1.add(jPanel2);

        Box box1;//用于存储图标
        box1=Box.createVerticalBox();

        JLayeredPane jPanel3=new JLayeredPane();
        jPanel3.setBounds(420,20,WIDTH-420-40,HEIGHT-95);
        collegemanage collegeMan=new collegemanage(0,0,WIDTH-420,HEIGHT-95);
        jPanel3.add(collegeMan,(Integer)(JLayeredPane.PALETTE_LAYER));
        jPanel3.moveToFront(collegeMan);
        jPanel1.add(jPanel3);

        jPanel2.add(box1);
        box1.add(new JLabel(""));
        JLabel jLabel=new JLabel("学院管理");
        jLabel.setFont(new Font("微软雅黑",Font.BOLD,25));
        jLabel.setForeground(new Color(150,150,150));
        box1.add(jLabel);


        JLabel jLabel1=new JLabel("学院与专业信息管理");
        jLabel1.setForeground(new Color(51,51,51));
        jLabel1.setFont(new Font("微软雅黑",Font.PLAIN,30));
        jLabel1.setIcon(new ImageIcon(
                "D:\\ideaprojects\\vcampus\\src\\main\\resources\\WindowsIcon\\Stuinfo.png"
        ));
        box1.add(jLabel1);



//        JLabel jLabel1=new JLabel("添加学院专业");
//        jLabel1.setForeground(new Color(51,51,51));
//        box1.add(jLabel1);




    }
}
