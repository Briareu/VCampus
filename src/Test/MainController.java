/**
 * Below is class description
 * 
 * @version  1.0 8/11
 * @author Liu
 * 
 * is just for testing module
 * please install javafx for running test.
 * 
 * you can use this class and the "Main.java" to test your moule :)
 * just change the url in getresource() to your own url for fxml files 
 */
package Test;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import VCampusServer.ClassAdminServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class MainController implements Initializable {
	@FXML
    private Button button_admin;
	private AnchorPane adminView;
	@FXML
	private Button button_student;

	@Override
    public void initialize(URL location, ResourceBundle resources) {
    }
	

    @FXML
    void AdminButton(ActionEvent actionevent) throws IOException{
        ((Node) (actionevent.getSource())).getScene().getWindow().hide();
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/ClassAdminServer.fxml"));
            Parent target =  loader.load();

            //studentController stu =loader.<studentController>getController();
            //stu.getData(Integer.valueOf(accountNumber.getText()),password.getText());
              //载入窗口B的定义文件；<span style="white-space:pre"> </span>
            Scene scene = new Scene(target); //创建场景；
            Stage stg = new Stage();//创建舞台；
			stg.setTitle("学生学籍管理界面");
			stg.setScene(scene); // 将场景载入舞台；

			//scene.getStylesheets().add(getClass().getResource("/view/Student.css").toExternalForm());

			//stg.getIcons().add(new Image("/images/666.png"));
			stg.setResizable(true);

			stg.show(); // 显示窗口；
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    @FXML
    void StudentButton(ActionEvent actionevent) throws IOException{
    	((Node) (actionevent.getSource())).getScene().getWindow().hide();
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/ClassStudentServer.fxml"));
            Parent target =  loader.load();

            //studentController stu =loader.<studentController>getController();
            //stu.getData(Integer.valueOf(accountNumber.getText()),password.getText());
              //载入窗口B的定义文件；<span style="white-space:pre"> </span>
            Scene scene = new Scene(target); //创建场景；
            Stage stg = new Stage();//创建舞台；
			stg.setTitle("学生信息管理界面");
			stg.setScene(scene); // 将场景载入舞台；

			//scene.getStylesheets().add(getClass().getResource("/view/Student.css").toExternalForm());

			//stg.getIcons().add(new Image("/images/666.png"));
			stg.setResizable(true);

			stg.show(); // 显示窗口；
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
