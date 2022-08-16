package seu.list.common;


import java.util.Vector;

public class Course {
	private  String Semester;
	private String CourseID;
	private String CourseMajor;
	private String CourseName;
	private String teacherID;
	private String CourseState;
	private String CourseType;



	public Course() {
	}

	public Course(String semester, String courseID, String courseMajor, String courseName, String teacherID, String courseState, String courseType) {
		Semester = semester;
		CourseID = courseID;
		CourseMajor = courseMajor;
		CourseName = courseName;
		this.teacherID = teacherID;
		CourseState = courseState;
		CourseType = courseType;
	}

	public String getSemester() {
		return Semester;
	}

	public void setSemester(String semester) {
		Semester = semester;
	}

	public String getCourseID() {
		return CourseID;
	}

	public void setCourseID(String courseID) {
		CourseID = courseID;
	}

	public String getCourseMajor() {
		return CourseMajor;
	}

	public void setCourseMajor(String courseMajor) {
		CourseMajor = courseMajor;
	}

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	public String getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}

	public String getCourseState() {
		return CourseState;
	}

	public void setCourseState(String courseState) {
		CourseState = courseState;
	}

	public String getCourseType() {
		return CourseType;
	}

	public void setCourseType(String courseType) {
		CourseType = courseType;
	}

	@Override
	public String toString() {
		return "Course{" +
				"Semester='" + Semester + '\'' +
				", CourseID='" + CourseID + '\'' +
				", CourseMajor='" + CourseMajor + '\'' +
				", CourseName='" + CourseName + '\'' +
				", teacherID='" + teacherID + '\'' +
				", CourseState='" + CourseState + '\'' +
				", CourseType='" + CourseType + '\'' +
				'}';
	}
	//	private  String Semester;
//	private String CourseID;
//	private String CourseMajor;
//	private String CourseName;
//	private String teacherID;
//	private String CourseState;
//	private String CourseType;
	public Vector<String> getContent() {
		Vector<String> courseContents = new Vector<String>();
		courseContents.add(Semester);
		courseContents.add(CourseID);
		courseContents.add(CourseMajor);
		courseContents.add(CourseName);
		courseContents.add(teacherID);
		courseContents.add(CourseState);
		courseContents.add(CourseType);
		return courseContents;
	}
	public void setContent(Vector<String> content) {
		Semester = content.get(0);
		CourseID = content.get(1);
		CourseMajor = content.get(2);
		CourseName = content.get(3);
		teacherID = content.get(4);
		CourseState=content.get(5);
		CourseType=content.get(6);
	}
}
