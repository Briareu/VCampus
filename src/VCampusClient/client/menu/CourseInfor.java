package virtualSchoolClient.src.vsst.client.view;

import virtualSchoolClient.src.vsst.common.ClientReq;
import virtualSchoolClient.src.vsst.common.Course;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class CourseInfor extends JDialog implements ActionListener {
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
		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 400, 400);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 181, 327, 37);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			
			JButton confirmButtom = new JButton("确定");
			confirmButtom.addActionListener(this);
			confirmButtom.setActionCommand("confirm");
			buttonPane.add(confirmButtom);
		}
		
		CourseID = new JTextField();
		CourseID.setBounds(152, 33, 86, 24);
		getContentPane().add(CourseID);
		CourseID.setColumns(10);
		
		CourseName = new JTextField();
		CourseName.setBounds(152, 70, 86, 24);
		getContentPane().add(CourseName);
		CourseName.setColumns(10);
		
		CourseMajor = new JTextField();
		CourseMajor.setBounds(152, 107, 86, 24);
		getContentPane().add(CourseMajor);
		CourseMajor.setColumns(10);
		
		teacherID = new JTextField();
		teacherID.setBounds(152, 144, 86, 24);
		getContentPane().add(teacherID);
		teacherID.setColumns(10);

		Semester = new JTextField();
		Semester.setBounds(152, 170, 86, 24);
		getContentPane().add(Semester);
		Semester.setColumns(10);

		CourseState = new JTextField();
		CourseState.setBounds(152, 200, 86, 24);
		getContentPane().add(CourseState);
		CourseState.setColumns(10);

		CourseType = new JTextField();
		CourseType.setBounds(152, 240, 86, 24);
		getContentPane().add(CourseType);
		CourseType.setColumns(10);

		JLabel lblsemster = new JLabel("学年学期");
		lblsemster.setBounds(55, 173, 72, 18);
		getContentPane().add(lblsemster);
		
		JLabel lblcourseID = new JLabel("课程ID");
		lblcourseID.setBounds(55, 36, 72, 18);
		getContentPane().add(lblcourseID);
		
		JLabel lblcourseName = new JLabel("课程名称");
		lblcourseName.setBounds(55, 73, 72, 18);
		getContentPane().add(lblcourseName);
		
		JLabel lblcourseMajor = new JLabel("课程专业");
		lblcourseMajor.setBounds(55, 110, 72, 18);
		getContentPane().add(lblcourseMajor);
		
		JLabel lblteacherID = new JLabel("授课教师");
		lblteacherID.setBounds(55, 147, 72, 18);
		getContentPane().add(lblteacherID);

		JLabel lblcourseState = new JLabel("课程状态");
		lblcourseState.setBounds(55, 203, 72, 18);
		getContentPane().add(lblcourseState);

		JLabel lblcourseType = new JLabel("课程类型");
		lblcourseType.setBounds(55, 243, 72, 18);
		getContentPane().add(lblcourseType);
		
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
			ClientReq clientReq = new ClientReq();
			clientReq.setType("REQ_ADD_LESSON");
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
