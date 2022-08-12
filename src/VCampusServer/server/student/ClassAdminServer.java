/**
 * Below is class description
 * 
 * @version  1.0 8/11
 * @author Liu
 * 
 * is used to control the student manage on administrator's interface
 * please install javafx for running test.
 * 
 * method returnPane is for return to the main stage, you can edit the url to your stage
 */
package server.student;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.lang.*;

public class ClassAdminServer {

	@FXML
    private AnchorPane pane;
	
    @FXML
    private Button button_exit;

    @FXML
    private ComboBox<?> Combobox;//classID,studentID,teacherID

    @FXML
    private TextField SearchTextFile;

    @FXML
    private Line message3;

    @FXML
    private Button button_search;

    @FXML
    private Button button_modify;

    @FXML
    private Line message2;

    @FXML
    private TableView<?> messagtable;
    
    @FXML
    private TableColumn<?, ?> studentname;
    
    @FXML
    private TableColumn<?, ?> majorname;
    
    @FXML
    private TableColumn<?, ?> studentid;
    
    @FXML
    private TableColumn<?, ?> classid;
    
    @FXML
    private TableColumn<?, ?> teacherid;
    
    @FXML
    private TableColumn<?, ?> studentphone;

    @FXML
    private Text message1;
    
    private Boolean modify;
    
    public ClassAdminServer(){
    	modify = false;
    }

    @FXML
    void ComboboxEvent(ActionEvent event) {

    }

    @FXML
    void SerarchText(ActionEvent event) {

    }

    @FXML
    void SearchButton(ActionEvent event) {

    }

    @FXML
    void MessageTable(ActionEvent event) {

    }

    @FXML
    void ModifyButton(ActionEvent event) {
    	if(modify == false) {
    		//turn to modify from watching
    		button_exit.setText("保存");
        	button_modify.setText("丢弃");
        	modify = true;
    	}
    	else {
    		//leave the modified text
    		button_exit.setText("�?�?");
        	button_modify.setText("修改");
        	modify = false;
    	}
    	//System.out.println("clicked");
    }

    @FXML
    void ExitButton(ActionEvent event) throws IOException {
    	if(modify == false) {
    		//exit
    		returnPane(event);
    	}
    	else {
    		//leave the modified text
    		button_exit.setText("�?�?");
        	button_modify.setText("修改");
        	modify = false;
    	}
    	//System.out.println("clicked");
    }
     
    public void returnPane(ActionEvent actionEvent) throws IOException {

        pane.getChildren().clear();
        Parent child = FXMLLoader.load(getClass().getClassLoader().getResource("view/Main.fxml"));
        pane.getChildren().add(child);
    }
}