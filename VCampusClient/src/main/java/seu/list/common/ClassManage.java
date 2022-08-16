 /*
   * ClassName: Class
   *
   * Version 1.0
   *
   * Date: 2022.08.10
   * 
   * ClassID, Major, TeacherID, ClassSize
   * 
   * Set to manage student
   * 
   * Last modified by Liu
   */


package seu.list.common;

import java.io.Serializable;

public class ClassManage implements Serializable{
	private String ClassID;
	private String Major;
	private String TeacherID;
	private int ClassSize;//exist students' number
	
	public ClassManage() {
		super();
	}
	
	public ClassManage(String classid, String major, String teacherID, int classsize)
	{
		ClassID = classid;
		Major = major;
		TeacherID = teacherID;
		ClassSize = classsize;
	}

	public String getClassID() {
		return ClassID;
	}

	public void setClassID(String classID) {
		ClassID = classID;
	}

	public String getMajor() {
		return Major;
	}

	public void setMajor(String major) {
		Major = major;
	}

	public String getTeacherID() {
		return TeacherID;
	}

	public void setTeacherID(String teacherID) {
		TeacherID = teacherID;
	}

	public int getClassSize() {
		return ClassSize;
	}

	public void setClassSize(int classSize) {
		ClassSize = classSize;
	}
	
	@Override
	public String toString() {
		return "Class{" + "ClassID" + ClassID + ", Major" + Major + ", TeacherID" + TeacherID + ", ClassSize" + ClassSize + "}";
	}
}