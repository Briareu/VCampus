//package VCampusServer.src.main.java.seu.list.common;
package seu.list.common;

public final class MessageType {
    public MessageType() {
        // TODO Auto-generated constructor stub
    }


    //Message-Type
    public static final String operFeedback = "OPER_FEEDBACK";


    //server鎿嶄綔ClassAdmin
    public static final String ClassAdminGetAll = "CLASS_ADMIN_GETALL";
    public static final String ClassAdminAdd = "CLASS_ADMIN_ADD";
    public static final String ClassAdminDelete = "CLASS_ADMIN_DELETE";
    public static final String ClassAdminUpdate = "CLASS_ADMIN_UPDATE";

    //server鎿嶄綔LibraryUser
    public static final String LibraryBookGetAll = "LIB_GETALL";
    public static final String LibraryBookAdd = "LIB_ADD";
    public static final String LibraryBookDelete = "LIB_DELETE";
    public static final String LibraryBookUpdate = "LIB_UPDATE";  
    public static final String LibraryBookLend = "LIB_LEND";
    public static final String LibraryBookReturn = "LIB_RETURN";
    public static final String LibraryBookFind = "LIB_FIND";

    public static final String REQ_SEARCH_LESSON="REQ_SEARCH_LESSON";//鏁版嵁搴撴煡璇㈣绋�
    public static final String REQ_REMOVE_LESSON="REQ_REMOVE_LESSON";//鑰佸笀鍔犺
    public static final String REQ_ADD_LESSON="REQ_ADD_LESSON";//鑰佸笀鍒犺
    public static final String REQ_SHOW_ALL_LESSON="REQ_SHOW_ALL_LESSON";//鏄剧ず鍏ㄩ儴璇剧▼
    public static final String REQ_STU_ADD_LESSON="REQ_STU_ADD_LESSON";//瀛︾敓閫夎
    public static final String REQ_STU_REMOVE_LESSON="REQ_STU_REMOVE_LESSON";//瀛︾敓閫�璇�
    public static final String REQ_STU_ALL_CHOOOSE="REQ_STU_ALL_CHOOOSE";
    //Test
    public static final String TestAdmin = "TEST_ADMIN";
    public static final String TestStudent = "TEST_STUDENT";
}