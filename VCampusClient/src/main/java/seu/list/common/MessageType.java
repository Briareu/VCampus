package seu.list.common;

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
    public static final String LibraryBookGetAll = "LIBRARY_BOOK_GETALL";
    public static final String LibraryBookAdd = "LIBRARY_BOOK_ADD";
    public static final String LibraryBookDelete = "LIBRARY_BOOK_DELETE";
    public static final String LibraryBookUpdate = "LIBRARY_BOOK_UPDATE";  
    public static final String LibraryBookLend = "LIBRARY_BOOK_LEND";
    public static final String LibraryBookReturn = "LIBRARY_BOOK_RETURN";
    public static final String LibraryBookFind = "LIBRARY_BOOK_FIND";

    
    //Test
    public static final String TestAdmin = "TEST_ADMIN";
    public static final String TestStudent = "TEST_STUDENT";
}