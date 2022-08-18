//package VCampusClient.src.main.java.seu.list.client.view;
package seu.list.client.view;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tools {
    //设置窗口居中
    public static void setWindowspos(int WIDTH, int HEIGHT, JFrame jframe){
        //传递宽和高
        Toolkit kit = Toolkit.getDefaultToolkit();//获取当前屏幕大小
        Dimension screensize = kit.getScreenSize();
        int width=screensize.width;
        int height = screensize.height;
        int x=(width-WIDTH)/2;
        int y=(height-HEIGHT)/2;
        jframe.setBounds(x,y,WIDTH,HEIGHT);
    }

    public static String getpassword(JPasswordField jp){
        String password=new String(jp.getPassword());
        return password;
    }

    public static int ishasdata(ResultSet rs,String data[]) throws SQLException {
        int num=0;
        while(rs.next()){
            if(rs.getString("Account").equals(data[0])&&
            rs.getString("Password").equals(data[1])){
                System.out.println(rs.getString("Account")+"\t"+rs.getString("Password"));
                if(rs.getString("Admin").equals("1")){
                    num=2;break;
                }else{
                    num=1;break;
                }

            }
        }
       rs.close();
        return num;
    }
}
