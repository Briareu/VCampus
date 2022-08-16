package seu.list.client.view;

import dao.Access;
import style.stylefont;
import tools.EasyCode;
import tools.Tools;
import tools.mysqld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginWindows {
    JFrame jframe=new JFrame();
    final int WIDTH=600;
    final int HEIGHT=400;
    public LoginWindows(){
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
        jframe.setLayout(null);//设置布局
        //设置背景图片
        ImageIcon img=new ImageIcon(
                "D:\\ideaprojects\\src\\src\\main\\resources\\WindowsIcon\\21.jpg"
        );
        JLabel beimg=new JLabel(img);
        beimg.setBounds(0,0,WIDTH,HEIGHT);


        JPanel jPanel = new JPanel();//定义一个盘子装标题
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,HEIGHT/6-40));
        jPanel.setBackground(null);
        jPanel.setBounds(0,0,WIDTH,HEIGHT/3);
        jPanel.setOpaque(false); // 设置为透明

        JLabel jLabel=new JLabel("学生选课系统");
        jPanel.add(jLabel);
        jLabel.setFont(stylefont.titlefont);
        jLabel.setForeground(Color.BLACK);

        JPanel jPanel1=new JPanel();//存放账号和密码
        jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));//设置居中对齐
        jPanel1.setBounds(0,HEIGHT/3,WIDTH,HEIGHT/3-45);
        jPanel1.setBackground(null);
        jPanel1.setOpaque(false); // 设置为透明

        Box boxH;
        Box boxOne, bowTwo;
        boxH=Box.createHorizontalBox();
        boxOne=Box.createVerticalBox();
        bowTwo=Box.createVerticalBox();

        //__________________________________________________

        ImageIcon img1=new ImageIcon(
                "D:\\ideaprojects\\src\\src\\main\\resources\\WindowsIcon\\images\\01.png"
        );
        JLabel beimg1=new JLabel(img1);
        //jPanel1.add(beimg1);
        JTextField jTextField= new JTextField(14);
        //jPanel1.add(jTextField);

        ImageIcon img2=new ImageIcon(
                "D:\\ideaprojects\\src\\src\\main\\resources\\WindowsIcon\\images\\password.png"
        );
        JLabel beimg2=new JLabel(img2);
        //jPanel1.add(beimg2);
        JPasswordField jTextField2= new JPasswordField(14);
        //jPanel1.add(jTextField2);

        //定义盘子3

        JPanel jPanel2=new JPanel();
        jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel2.setBounds(0,HEIGHT/3*2-67,WIDTH,HEIGHT/3+10);
        jPanel2.setBackground(null);
        jPanel2.setOpaque(false);

        JButton jButton= new JButton("安全登录");
        Dimension preferredSize = new Dimension(150,40);
        jButton.setPreferredSize(preferredSize);
        jButton.setFont(stylefont.titlefont3);

        jButton.setOpaque(false);
        jButton.setContentAreaFilled(false);
        jButton.setBorderPainted(false);
        jPanel2.add(jButton);

        boxOne.add(beimg1);
        boxOne.add(Box.createVerticalStrut(10));
        boxOne.add(beimg2);
        bowTwo.add(jTextField);
        bowTwo.add(Box.createVerticalStrut(10));
        bowTwo.add(jTextField2);
        boxH.add(boxOne);
        boxH.add(Box.createHorizontalStrut(10));
        boxH.add(bowTwo);
        jPanel1.add(boxH);

        JLabel jLabel1=new JLabel("找回密码");
        jLabel1.setFont(stylefont.titlefont2);
        jLabel1.setBounds(480,250,150,150);


        jframe.add(jLabel1);
        jframe.add(jPanel2);
        jframe.add(jPanel1);
        jframe.add(jPanel);
        jframe.add(beimg);

        //对事件的处理
        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("您点击了按钮");

                String account=jTextField.getText();

                String password=Tools.getpassword(jTextField2);


                if (account.equals("")){
                    JOptionPane.showMessageDialog(null,"账号不能为空","消息",JOptionPane.WARNING_MESSAGE);

                }else if(password.equals("")){

                    JOptionPane.showMessageDialog(null,"密码不能为空","消息",JOptionPane.WIDTH);
                }else{
                    String []data=new String[2];
                    data[0]=account;
                    data[1]=password;
                    String sql="select * from student_info";
                    try {
                        ResultSet rs= mysqld.select(Access.connection,sql);
                        int isl = Tools.ishasdata(rs,data);
                        if(isl==1){
                            JOptionPane.showMessageDialog(
                                    null,
                                    "登录学生界面",
                                    "消息",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }else if(isl==2){
                            JOptionPane.showMessageDialog(
                                    null,
                                    "登录管理员界面",
                                    "消息",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(
                                    null,
                                    "账号或密码错误，请重新输入",
                                    "消息",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

}
