package VCampusClient.src.main.java.seu.list.common;

public final class MessageType {
    public MessageType() {
        // TODO Auto-generated constructor stub
    }


    //Message-Type
    public static final String operFeedback = "OPER_FEEDBACK";


    //server操作ClassAdmin
    public static final String ClassAdminGetAll = "CLASS_ADMIN_GETALL";
    public static final String ClassAdminAdd = "CLASS_ADMIN_ADD";
    public static final String ClassAdminDelete = "CLASS_ADMIN_DELETE";
    public static final String ClassAdminUpdate = "CLASS_ADMIN_UPDATE";

    //server操作LibraryUser
    public static final String LibraryBookGetAll = "LIB_GETALL";
    public static final String LibraryBookAdd = "LIB_ADD";
    public static final String LibraryBookDelete = "LIB_DELETE";
    public static final String LibraryBookUpdate = "LIB_UPDATE";  
    public static final String LibraryBookLend = "LIB_LEND";
    public static final String LibraryBookReturn = "LIB_RETURN";
    public static final String LibraryBookFind = "LIB_FIND";

    public static final String REQ_SEARCH_LESSON="REQ_SEARCH_LESSON";//数据库查询课程
    public static final String REQ_REMOVE_LESSON="REQ_REMOVE_LESSON";//老师加课
    public static final String REQ_ADD_LESSON="REQ_ADD_LESSON";//老师删课
    public static final String REQ_SHOW_ALL_LESSON="REQ_SHOW_ALL_LESSON";//显示全部课程
    public static final String REQ_STU_ADD_LESSON="REQ_STU_ADD_LESSON";//学生选课
    public static final String REQ_STU_REMOVE_LESSON="REQ_STU_REMOVE_LESSON";//学生退课
    
    //Test
    public static final String TestAdmin = "TEST_ADMIN";
    public static final String TestStudent = "TEST_STUDENT";
}