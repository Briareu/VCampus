//package VCampusClient.src.main.java.seu.list.client.view;
package seu.list.client.view;

/*
import VCampusClient.src.main.java.seu.list.common.ModuleType;
import main.java.seu.list.common.Course;
import main.java.seu.list.common.Message;
*/
import seu.list.common.ModuleType;
import seu.list.common.Course;
import seu.list.common.Message;
import seu.list.common.*;

import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class CourseInfor extends JDialog implements ActionListener {
	private final int WIDTH=500;
	private final int HEIGHT=500;
	private final JPanel contentPanel = new JPanel();
	private JTextField Semester;
	private JTextField CourseID;
	private JTextField CourseMajor;
	private JTextField CourseName;
	private JTextField teacherID;
	private JTextField CourseState;
	private JTextField CourseType;
	private Socket socket;
	private String userID; 

	/**
	 * Create the dialog.
	 */
	public  CourseInfor(String ID,Socket intiSocket) {
		userID=ID;
		this.socket=intiSocket;
		setBounds(100, 100, WIDTH, HEIGHT);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, WIDTH, HEIGHT);
		this.add(contentPanel);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);

		JPanel buttonPane = new JPanel();
		//buttonPane.setBounds(0, 181, 327, 37);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));


		JButton confirmButtom = new JButton("纭畾");
		confirmButtom.addActionListener(this);
		confirmButtom.setActionCommand("confirm");
		buttonPane.add(confirmButtom);

		Box box1= Box.createHorizontalBox();
		Box box2= Box.createHorizontalBox();
		Box box3= Box.createHorizontalBox();
		Box box4= Box.createHorizontalBox();
		Box box5= Box.createHorizontalBox();
		Box box6= Box.createHorizontalBox();
		Box box7= Box.createHorizontalBox();
		Box boxH= Box.createVerticalBox();

		CourseID = new JTextField();
		//CourseID.setBounds(152, 33, 86, 24);
		//getContentPane().add(CourseID);
		CourseID.setColumns(10);

		JLabel lblcourseID = new JLabel("璇剧▼ID");
		//lblcourseID.setBounds(55, 36, 72, 18);
		//getContentPane().add(lblcourseID);

		box1.add(CourseID);
		box1.add(lblcourseID);

		
		CourseName = new JTextField();
		//CourseName.setBounds(152, 70, 86, 24);
		//getContentPane().add(CourseName);
		CourseName.setColumns(10);
		JLabel lblcourseName = new JLabel("璇剧▼鍚嶇О");
//		lblcourseName.setBounds(55, 73, 72, 18);
//		getContentPane().add(lblcourseName);
		box2.add(CourseName);
		box2.add(lblcourseName);
		
		CourseMajor = new JTextField();
		//CourseMajor.setBounds(152, 107, 86, 24);
		//getContentPane().add(CourseMajor);
		CourseMajor.setColumns(10);

		JLabel lblcourseMajor = new JLabel("璇剧▼涓撲笟");
//		lblcourseMajor.setBounds(55, 110, 72, 18);
//		getContentPane().add(lblcourseMajor);
		box3.add(CourseMajor);
		box3.add(lblcourseMajor);

		
		teacherID = new JTextField();
		//teacherID.setBounds(152, 144, 86, 24);
		//getContentPane().add(teacherID);
		teacherID.setColumns(10);

		JLabel lblteacherID = new JLabel("鎺堣鏁欏笀");
//		lblteacherID.setBounds(55, 147, 72, 18);
//		getContentPane().add(lblteacherID);
		box4.add(teacherID);
		box4.add(lblteacherID);


		Semester = new JTextField();
		//Semester.setBounds(152, 170, 86, 24);
		//getContentPane().add(Semester);
		Semester.setColumns(10);

		JLabel lblsemster = new JLabel("瀛﹀勾瀛︽湡");
//		lblsemster.setBounds(55, 173, 72, 18);
//		getContentPane().add(lblsemster);
		box5.add(Semester);
		box5.add(lblsemster);



		CourseState = new JTextField();
		//CourseState.setBounds(152, 200, 86, 24);
		//getContentPane().add(CourseState);
		CourseState.setColumns(10);

		JLabel lblcourseState = new JLabel("璇剧▼鐘舵��");
//		lblcourseState.setBounds(55, 203, 72, 18);
//		getContentPane().add(lblcourseState);
		box6.add(CourseState);
		box6.add(lblcourseState);



		CourseType = new JTextField();
		//CourseType.setBounds(152, 240, 86, 24);
		//getContentPane().add(CourseType);
		CourseType.setColumns(10);


		JLabel lblcourseType = new JLabel("璇剧▼绫诲瀷");
//		lblcourseType.setBounds(55, 243, 72, 18);
//		getContentPane().add(lblcourseType);
		box7.add(CourseType);
		box7.add(lblcourseType);

		boxH.add(box1);
		boxH.add(box2);
		boxH.add(box3);
		boxH.add(box4);
		boxH.add(box5);
		boxH.add(box6);
		boxH.add(box7);
		boxH.add(confirmButtom);
		contentPanel.add(boxH);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="confirm") {
			Course course = new Course();
			course.setCourseID(CourseID.getText());
			course.setCourseName(CourseName.getText());
			course.setCourseMajor(CourseMajor.getText());
			course.setTeacherID(teacherID.getText());
			course.setCourseState(CourseState.getText());
			course.setSemester(Semester.getText());
			course.setCourseType(CourseType.getText());
			Message clientReq = new Message();
			clientReq.setModuleType(ModuleType.Course);
			clientReq.setMessageType("REQ_ADD_LESSON");
			//clientReq.setData(course.getContent());
			clientReq.setContent(course.getContent());
			try {
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(clientReq);
				oos.flush();
				ClientCourseFrame ccf = new ClientCourseFrame(userID,socket);
				this.setVisible(false);
			}
			catch(Exception error) {
				error.printStackTrace();
			}
		}
	}
}
