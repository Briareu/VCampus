package virtualSchoolClient.src.vsst.common;
//import vss.server.bz.user;



import java.util.Vector;

public class Course {
	//private static final long serialVersionUID = 2342342342342342342L;//为了让ServerThread能够写回它

	private String CourseID;
	private  String Semester;

	private String CourseName;
	private String CourseMajor;
	private String teacherID;
	private String CourseState;
	private String CourseType;

	public Course() {
	}


	public Course(String courseID, String semester, String courseName, String courseMajor, String teacherID, String courseState, String courseType) {
		CourseID = courseID;
		Semester = semester;
		CourseName = courseName;
		CourseMajor = courseMajor;
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
				"CourseID='" + CourseID + '\'' +
				", Semester='" + Semester + '\'' +
				", CourseName='" + CourseName + '\'' +
				", CourseMajor='" + CourseMajor + '\'' +
				", teacherID='" + teacherID + '\'' +
				", CourseState='" + CourseState + '\'' +
				", CourseType='" + CourseType + '\'' +
				'}';
	}




	public Vector<String> getContent() {
		Vector<String> courseContents = new Vector<String>();
		courseContents.add(CourseID);
		courseContents.add(Semester);
		courseContents.add(CourseName);
		courseContents.add(CourseMajor);

		courseContents.add(teacherID);
		courseContents.add(CourseState);
		courseContents.add(CourseType);
		return courseContents;
	}
	//	private String CourseID;
//	private  String Semester;
//
//	private String CourseName;
//	private String CourseMajor;
//	private String teacherID;
//	private String CourseState;
//	private String CourseType;
	public void setContent(Vector<String> content) {
		CourseID = content.get(0);
		Semester = content.get(1);
		CourseName = content.get(2);
		CourseMajor = content.get(3);
		teacherID = content.get(4);
		CourseState=content.get(5);
		CourseType=content.get(6);
	}
}
