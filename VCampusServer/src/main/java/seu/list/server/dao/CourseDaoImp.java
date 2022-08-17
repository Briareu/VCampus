//package VCampusServer.src.main.java.seu.list.server.dao;
package seu.list.server.dao;



/*
import VCampusServer.src.main.java.seu.list.common.Course;
import VCampusServer.src.main.java.seu.list.server.dao.CourseDao;
import VCampusServer.src.main.java.seu.list.server.db.SqlHelperImp;
*/
import seu.list.common.Course;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.User;
import seu.list.server.dao.CourseDao;
import seu.list.server.db.SqlHelperImp;

import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;



public class CourseDaoImp implements CourseDao {
		// Modified by WU 8.16
		private Message mesFromClient; // �ӿͻ����յ�������
		private Message mesToClient; // �����ͻ��˵�����
		
		public CourseDaoImp(Message mesFromClient) {
			this.mesFromClient = mesFromClient;
		}
		
		public void execute() { 
			// ��������ȥִ�в�ͬ��DAO���������ͬģ���DAO����Ҫ�޸��������
			// ���������Ҫ�Ĳ���������mesFromClient��ȡ��
			// ���������Ҫ�������ݸ��ͻ��ˣ������mesToClient�����û�з���ֵ����Ĭ��Ϊnull
			switch(this.mesFromClient.getMessageType()) 
			{
				case MessageType.REQ_STU_ADD_LESSON: {
					System.out.println("serving REQ_STU_ADD_LESSON");
					System.out.println("adding....");
					String CourseId = this.mesFromClient.getContent().get(0);
					String UserId = this.mesFromClient.getContent().get(1);
					this.mesToClient.setData(this.sigAddCourse(CourseId, UserId));
					System.out.println("REQ_STU_ADD_LESSON finished");
					break;
				}
				case MessageType.REQ_STU_REMOVE_LESSON: {
					System.out.println("serving REQ_STU_REMOVE_LESSON");
					System.out.println("removing....");
					String CourseId = this.mesFromClient.getContent().get(0);
					String UserId = this.mesFromClient.getContent().get(1);
					this.mesToClient.setData(this.sigRemoveCourse(CourseId,UserId));
					System.out.println("REQ_STU_REMOVE_LESSON finshed");
					break;
				}
				case MessageType.REQ_ADD_LESSON:{
					System.out.println("serving REQ_ADD_LESSON");
					System.out.println("adding....");
					//��ʦ��ӿγ�
					Course course = new Course();
					course.setContent(this.mesFromClient.getContent());
					this.mesToClient.setData(this.genAddCourse(course));
					System.out.println("REQ_ADD_LESSON finished");
					break;
				}
				case MessageType.REQ_REMOVE_LESSON: {
					System.out.println("serving REQ_REMOVE_LESSON");
					System.out.println("removing.....");
					String courseName = this.mesFromClient.getContent().get(1);
					this.mesToClient.setData(this.genRemoveCourse(courseName));
					System.out.println("REQ_REMOVE_LESSON finished");
					break;
				}
				case MessageType.REQ_SEARCH_LESSON: {
					System.out.println("serving REQ_SEARCH_LESSON");
					System.out.println("searching.....");
					//���ص����γ���Ϣ
					Course course = new Course();
					String courseID = this.mesFromClient.getContent().get(0);
					course = this.searchCourseByID(courseID);
					//course.print();
					//content�ĸ���
					this.mesToClient.setContent(course.getContent());
					System.out.println("REQ_SEARCH_LESSON finished");
					break;
				}
				case MessageType.REQ_SHOW_ALL_LESSON: {
					System.out.println("serving REQ_SHOW_ALL_LESSON");
					System.out.println("grabbing.....");
					//�������пγ�
					Vector<String> sigCourseContent= new Vector<String>();
					Vector<String> allCourseContent = new Vector<String>();
					List<Course> allCourse = new LinkedList<Course>();
					allCourse = this.getAllCourse();
					Iterator<Course> iteAllCourse = allCourse.iterator();
					while(iteAllCourse.hasNext()) {
						sigCourseContent = iteAllCourse.next().getContent();
						for(int i=0;i<=4;i++) {
							allCourseContent.add(sigCourseContent.get(i));
						}
					}
					this.mesToClient.setContent(allCourseContent);
					System.out.println("REQ_SHOW_ALL_LESSON finished");
					break;
				}
				case MessageType.REQ_STU_ALL_CHOOOSE: {
					System.out.println("serving REQ_STU_ALL_CHOOOSE");
					System.out.println("grabbing......");
					//�������и�ѧ����ѡ�γ�
					Vector<String> sigCourseContent= new Vector<String>();
					Vector<String> allCourseContent = new Vector<String>();
					List<Course> allCourse = new LinkedList<Course>();
					User user = new User();
					user.setContent(this.mesFromClient.getContent());
					allCourse = user.getCourses();
					Iterator<Course> iteAllCourse = allCourse.iterator();
					while(iteAllCourse.hasNext()) {
						sigCourseContent = iteAllCourse.next().getContent();
						for(int i=0;i<=4;i++) {
							allCourseContent.add(sigCourseContent.get(i));
						}
					}
					this.mesToClient.setContent(allCourseContent);
					System.out.println("REQ_STU_ALL_CHOOOSE finished");
					break;
				}
				default:
					break;
			}
		}

		public Message getMesToClient() { // �����޸ģ��������Ҫ�����������
			return this.mesToClient;
		}
		// Modified by WU 8.16
	
	
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
