/**
 * Below is class description
 * 
 * @version  1.0 8/11
 * @author Liu
 * 
 * is used to control the student message on student's interface
 * please install javafx for running test.
 * 
 * method returnPane is for return to the main stage, you can edit the url to your stage
 */
package VCampusServer;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ClassStudentServer {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private TextField studentname;//can change to be editable by clicking the modify

    @FXML
    private TextField studentorigin;//can change to be editable by clicking the modify

    @FXML
    private Button button_modify;

    @FXML
    private TextField studentid;

    @FXML
    private Button button_exit;

    @FXML
    private TextField classid;//can change to be editable by clicking the modify

    @FXML
    private TextField teacherid;

    @FXML
    private TextField major;

    @FXML
    private TextField studentstatus;//can change to be editable by clicking the modify

    @FXML
    private TextField studentgender;//can change to be editable by clicking the modify

    @FXML
    private TextField studentphone;//can change to be editable by clicking the modify

    @FXML
    private Text message2;

    @FXML
    private TextField studentcredit;//can change to be editable by clicking the modify
    
	private Boolean modify;

	public ClassStudentServer(){
    	modify = false;
    }

    @FXML
    void ModifyButton(ActionEvent event) {
    	if(modify == false) {
    		//turn to modify from watching
    		button_exit.setText("保存");
        	button_modify.setText("丢弃");
        	modify = true;
        	studentname.setEditable(true);
        	studentorigin.setEditable(true);
        	classid.setEditable(true);
        	studentstatus.setEditable(true);
        	studentgender.setEditable(true);
        	studentphone.setEditable(true);
        	studentcredit.setEditable(true);
    	}
    	else {
    		//leave the modified text
    		button_exit.setText("退出");
        	button_modify.setText("修改");
        	modify = false;
        	studentname.setEditable(false);
        	studentorigin.setEditable(false);
        	classid.setEditable(false);
        	studentstatus.setEditable(false);
        	studentgender.setEditable(false);
        	studentphone.setEditable(false);
        	studentcredit.setEditable(false);
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
    		button_exit.setText("退出");
        	button_modify.setText("修改");
        	modify = false;
        	studentname.setEditable(false);
        	studentorigin.setEditable(false);
        	classid.setEditable(false);
        	studentstatus.setEditable(false);
        	studentgender.setEditable(false);
        	studentphone.setEditable(false);
        	studentcredit.setEditable(false);
    	}
    	//System.out.println("clicked");
    }

    @FXML
    void StudentName(ActionEvent event) {

    }

    @FXML
    void Major(ActionEvent event) {

    }

    @FXML
    void ClassID(ActionEvent event) {

    }

    @FXML
    void TeacherID(ActionEvent event) {

    }

    @FXML
    void StudentStatus(ActionEvent event) {

    }

    @FXML
    void StudentOrigin(ActionEvent event) {

    }

    @FXML
    void SudentGender(ActionEvent event) {

    }

    @FXML
    void StudentPhone(ActionEvent event) {

    }

    @FXML
    void StudentCredit(ActionEvent event) {

    }
    
    public void returnPane(ActionEvent actionEvent) throws IOException {

        anchorpane.getChildren().clear();
        Parent child = FXMLLoader.load(getClass().getClassLoader().getResource("view/Main.fxml"));
        anchorpane.getChildren().add(child);
    }
}
