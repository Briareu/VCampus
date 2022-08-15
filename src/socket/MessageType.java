package socket;

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
    public static final String LibraryBookGetAll = "CLASS_ADMIN_GETALL";
    public static final String LibraryBookAdd = "CLASS_ADMIN_ADD";
    public static final String LibraryBookDelete = "CLASS_ADMIN_DELETE";
    public static final String LibraryBookUpdate = "CLASS_ADMIN_UPDATE";  
    public static final String LibraryBookLend = "CLASS_ADMIN_UPDATE";
    public static final String LibraryBookReturn = "CLASS_ADMIN_UPDATE";
    public static final String LibraryBookFind = "CLASS_ADMIN_FIND";
}