//package VCampusClient.src.main.java.seu.list.client.view;
package seu.list.client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import javax.swing.JCheckBox;
import java.awt.Color;
import seu.list.common.*;
//import vc.bz.IUserSrv;
//import vc.bz.impl.IUserSrvImpl;

public class LoginFrame extends  JFrame implements  ActionListener{
    JPanel root =new JPanel();
    JButton login =new JButton("login");
    JLabel name=new JLabel("Username");
    JLabel password=new JLabel("Password");
    JLabel signup=new JLabel("Create account");
    JLabel forget=new JLabel("Forgot password?");
    JTextField account=new JTextField();
    JPasswordField pass=new JPasswordField();
    JCheckBox remember =new JCheckBox("remember password");
    public LoginFrame(){

        this.setContentPane(root);
        root.setLayout(null);

        ImageIcon img =new ImageIcon("client/src/image/back.png");
        JLabel back=new JLabel(img);
        back.setBounds(0,-25,300,400);
        this.getLayeredPane().add(back, Integer.valueOf(Integer.MIN_VALUE));
        JPanel imPanel = (JPanel)getContentPane();
        imPanel.setOpaque(false);

        login.setBounds(359,240,330,40);
        login.setBackground(Color.blue);
        login.setForeground(Color.white);
        root.add(login);

        name.setBounds(343,35,100,20);
        name.setFont(new Font("寰蒋闆呴粦",Font.PLAIN,14));
        name.setHorizontalAlignment(SwingConstants.CENTER);
        name.setForeground(Color.blue);
        root.add(name);

        password.setBounds(343,115,100,20);
        password.setFont(new Font("寰蒋闆呴粦",Font.PLAIN,14));
        password.setHorizontalAlignment(SwingConstants.CENTER);
        password.setForeground(Color.blue);
        root.add(password);

        signup.setBounds(75,280,120,20);
        signup.setFont(new Font("寰蒋闆呴粦",Font.PLAIN,14));
        signup.setHorizontalAlignment(SwingConstants.CENTER);
        signup.setForeground(Color.red);
        root.add(signup);

        signup.setCursor(new Cursor(Cursor.HAND_CURSOR));// 璁剧疆榧犳爣澶栬
        // 璁剧疆榧犳爣浜嬩欢鐩戝惉
        signup.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                signup.setText("<html><A   href='http://id.qq.com/'>Create account</A></html>");
            }

            public void mouseExited(MouseEvent e) {
                signup.setText("Create account");
            }

            public void mouseClicked(MouseEvent e) {

            }});

        forget.setBounds(80,300,130,20);
        forget.setFont(new Font("寰蒋闆呴粦",Font.PLAIN,14));
        forget.setHorizontalAlignment(SwingConstants.CENTER);
        forget.setForeground(Color.red);
        root.add(forget);

        forget.setCursor(new Cursor(Cursor.HAND_CURSOR));// 璁剧疆榧犳爣澶栬
        // 璁剧疆榧犳爣浜嬩欢鐩戝惉
        forget.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                forget.setText("<html><A   href='http://id.qq.com/'>Forget password?</A></html>");
            }

            public void mouseExited(MouseEvent e) {
                forget.setText("Forget password?");
            }

            public void mouseClicked(MouseEvent e) {


            }});


        account.setBounds(360,65,330,40);
        root.add(account);

        pass.setBounds(360,140,330,40);
        root.add(pass);

        remember.setBounds(356,190,170,40);
        remember.setFont(new Font("寰蒋闆呴粦",Font.PLAIN,14));
        remember.setForeground(Color.blue);
        root.add(remember);


        this.getRootPane().setDefaultButton(login);
        this.setSize(730, 380);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 鑾峰彇褰撳墠灞忓箷澶у皬
        Dimension frameSize = this.getPreferredSize();// 鑾峰彇褰撳墠绐楀彛澶у皬
        this.setLocation((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);// 淇濇寔绐楀彛寮瑰嚭浣嶇疆灞呬腑
        //this.setIconImage((new ImageIcon("image/澶村儚.GIF").getImage()));
        this.setTitle("鐢ㄦ埛鐧诲綍");
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==login)
        {
            IUserSrv us=new IUserSrvImpl();
            User user=new User();
            String acco=account.getText().trim();
            user.setAccount(acco);
            user.setPassword(new String(pass.getPassword()));
            User retUser=null;
            try {
                retUser = us.login(user);
                if (retUser != null) {
                    QQLogger.clientLogger().info(user.getQqNo() + ":" + "楠岃瘉鎴愬姛");
                    //retUser.setStatus(UserStatus.ONLINE);
                    new ClientMainFrm(retUser, us);
                    // 鍚屾椂鍏抽棴鎺夌櫥闄嗙晫闈�
                    this.dispose();
                } else {

                    JOptionPane.showMessageDialog(this, "鐢ㄦ埛鍚嶆垨瀵嗙爜閿欒", "璀﹀憡",
                            JOptionPane.WARNING_MESSAGE);
                }
            } catch (ClassNotFoundException e1) {

                e1.printStackTrace();
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(this, "缃戠粶杩炴帴寮傚父锛岃妫�鏌ョ浉鍏抽厤缃紒", "璀﹀憡",
                        JOptionPane.WARNING_MESSAGE);
            }

        } else if (e.getSource() == jbtnRegister) {

            this.setVisible(false);
            RegisterDlg r = new RegisterDlg();
            r.setModalityType(ModalityType.APPLICATION_MODAL);
            r.setVisible(true);
            User u = r.getUser();
            this.txtQqNo.setText(u.getQqNo());
            r.dispose();
            this.setVisible(true);
        }
    }
}
    static public void main(String[]args)
    {
        LoginFrame frame1=new LoginFrame();
    }
}