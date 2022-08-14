package virtualSchoolServer.server.dao;


import virtualSchoolServer.common.Course;
import virtualSchoolServer.server.db.SqlHelperImp;

import java.util.List;



public class CourseDaoImp implements CourseDao{
    @Override
    public Course searchCourseByID(String courseID) {
        // TODO Auto-generated method stub
        String sql = "select * from tb_Class where CourseID = ?";
        String[] paras = new String[1];
        paras[0] = courseID;
        List<Course> cList=new SqlHelperImp().sqlCourseQuery(sql, paras);
        if(cList!=null&&cList.size()>0) {
            return cList.get(0);
        }else
            return null;
    }
    @Override
    public List<Course> getAllCourse() {
        // TODO Auto-generated method stub
        String sql = "select * from tb_Class";
        return new SqlHelperImp().sqlCourseQuery(sql, new String[] {});
    }

    @Override
    public boolean sigAddCourse(String courseID,String uID) {
        String[] paras2 = new String[3];

        paras2[0]=uID+courseID;
        paras2[1]=uID;
        paras2[2]=courseID;
        String sql2 = "insert into tb_Stc(scID,uID,cID) values(?,?,?)";
        return new SqlHelperImp().sqlUpdate(sql2, paras2);
    }

    @Override
    public boolean sigRemoveCourse(String courseID,String uID) {
        String[] paras2=new String[2];
        String sql2="delete from tb_Stc where uID = ? and cID = ?";
        paras2[0] = uID;
        paras2[1] = courseID;
        return new SqlHelperImp().sqlUpdate(sql2, paras2);
    }

    @Override
    public boolean genAddCourse(Course course) {
        // TODO Auto-generated method stub
        String sql="insert into tb_Class(Semester,CourseID,CourseMajor,CourseName,teacherID，CourseState，CourseType) values (?,?,?,?,?,?,?)";
        String[] paras=new String[7];
        paras[0]=course.getSemester();
        paras[1]=course.getCourseID();
        paras[2]=course.getCourseMajor();
        paras[3]=course.getCourseName();
        paras[4]=course.getTeacherID();
        paras[5]=course.getCourseState();
        paras[6]=course.getCourseType();
        return new SqlHelperImp().sqlUpdate(sql, paras);
    }

    @Override
    public boolean genRemoveCourse(String courseName) {
        // TODO Auto-generated method stub
        String sql="delete from tb_Class where CourseName = ?";
        String[] paras=new String[1];
        paras[0]=courseName;
        return new SqlHelperImp().sqlUpdate(sql, paras);
    }
}
