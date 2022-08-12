package windows;

import dao.Access;

import java.sql.Connection;
import java.sql.DriverManager;
public class CourseMain {

    public static void main(String[] args) {

        Access db=new Access("D:\\seu\\vcampus.accdb", "" , "123456");
        //LoginWindows loginwindows=new LoginWindows();
        ManageWindow manageWindow=new ManageWindow();
    }
}
