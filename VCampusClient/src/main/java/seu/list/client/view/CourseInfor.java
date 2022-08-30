package seu.list.client.view;
//package seu.list.client.view;



import seu.list.client.bz.Client;
import seu.list.common.Course;
import seu.list.common.Message;
import seu.list.common.ModuleType;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
/**
 * @author 郭念宗
 * @version jdk1.8.0
 */
public class CourseInfor extends JDialog implements ActionListener {
	private final int WIDTH=400;
	private final int HEIGHT=450;
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
	 * @param ID 用户id
	 * @param socket 与服务器通信的socket
	 */
	public  CourseInfor(String ID,Socket socket) {
		userID=ID;
		this.socket=socket;
		Client client=new Client(this.socket);
		setBounds(100, 100, WIDTH, HEIGHT);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, WIDTH, HEIGHT);
		this.add(contentPanel);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);

		JPanel buttonPane = new JPanel();

		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));


		JButton confirmButtom = new JButton("确定");
		confirmButtom.setFont(new Font("微软雅黑",Font.BOLD,20));
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
		Box box8=Box.createHorizontalBox();
		Box boxH= Box.createVerticalBox();

		CourseID = new JTextField();
		CourseID.setColumns(10);

		JLabel lblcourseID = new JLabel("课程ID");
		lblcourseID.setFont(new Font("微软雅黑",Font.BOLD,20));
		box1.add(CourseID);
		box1.add(lblcourseID);


		CourseName = new JTextField();
		CourseName.setColumns(10);
		JLabel lblcourseName = new JLabel("课程名称");
		lblcourseName.setFont(new Font("微软雅黑",Font.BOLD,20));
		box2.add(CourseName);
		box2.add(lblcourseName);

		CourseMajor = new JTextField();
		CourseMajor.setColumns(10);

		JLabel lblcourseMajor = new JLabel("课程专业");
		lblcourseMajor.setFont(new Font("微软雅黑",Font.BOLD,20));
		box3.add(CourseMajor);
		box3.add(lblcourseMajor);


		teacherID = new JTextField();
		teacherID.setColumns(10);

		JLabel lblteacherID = new JLabel("授课教师");
		lblteacherID.setFont(new Font("微软雅黑",Font.BOLD,20));

		box4.add(teacherID);
		box4.add(lblteacherID);


		Semester = new JTextField();
		Semester.setColumns(10);

		JLabel lblsemster = new JLabel("学年学期");
		lblsemster.setFont(new Font("微软雅黑",Font.BOLD,20));

		box5.add(Semester);
		box5.add(lblsemster);



		CourseState = new JTextField();
		CourseState.setColumns(10);

		JLabel lblcourseState = new JLabel("课程状态");
		lblcourseState.setFont(new Font("微软雅黑",Font.BOLD,20));
		box6.add(CourseState);
		box6.add(lblcourseState);



		CourseType = new JTextField();

		CourseType.setColumns(10);


		JLabel lblcourseType = new JLabel("课程类型");
		lblcourseType.setFont(new Font("微软雅黑",Font.BOLD,20));

		box7.add(CourseType);
		box7.add(lblcourseType);

		box8.add(Box.createHorizontalStrut(100));
		box8.add(confirmButtom);
		boxH.add(Box.createVerticalStrut(20));
		boxH.add(box1);
		boxH.add(Box.createVerticalStrut(15));
		boxH.add(box2);
		boxH.add(Box.createVerticalStrut(15));
		boxH.add(box3);
		boxH.add(Box.createVerticalStrut(15));
		boxH.add(box4);
		boxH.add(Box.createVerticalStrut(15));
		boxH.add(box5);
		boxH.add(Box.createVerticalStrut(15));
		boxH.add(box6);
		boxH.add(Box.createVerticalStrut(15));
		boxH.add(box7);
		boxH.add(Box.createVerticalStrut(25));
		boxH.add(box8);
		boxH.add(Box.createVerticalStrut(15));
		contentPanel.add(boxH);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	/**
	 * 事件响应
	 * @param e 事件
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="confirm") {
			Client client = new Client(this.socket);
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
			clientReq.setContent(course.getContent());
			Message rec= client.sendRequestToServer(clientReq);
			if(rec.isSeccess()){
				try {
					ClientCourseFrame ccf = new ClientCourseFrame(userID,this.socket);
				} catch (ClassNotFoundException ex) {
					throw new RuntimeException(ex);
				} catch (SQLException ex) {
					throw new RuntimeException(ex);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
				this.setVisible(false);
			}else{
				JOptionPane.showMessageDialog(null,"课程id不可重复，请重新填写","错误",JOptionPane.ERROR_MESSAGE);
			}

		}
	}
}
