/**
 * Below is class description
 * 
 * @version  1.0 8/11
 * @author Liu
 * 
 * is just for testing module
 * please install javafx for running test.
 * 
 * you can use this class and the "MainController.java" to test your moule :)
 */
package Test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import java.lang.*;
import java.net.*;

public class Main extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader()
                    .getResource("view/Main.fxml"));
            primaryStage.setTitle("虚拟校园登陆");
            Scene Scene = new Scene(root, 800, 400);
            primaryStage.setScene(Scene);
            /*Scene.getStylesheets().add(
                    getClass().getResource("/view/login.css")
                            .toExternalForm());*/
            //primaryStage.getIcons().add(new Image("/images/666.png"));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
