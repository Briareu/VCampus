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
    
    //Dormitory
    public static final String DormAdd="ADD_DORM";  //添加宿舍
    public static final String DormDelete="REMOVE_DORM";  //删除宿舍
    public static final String DormSearch="SEARCH_DORM";  //查找宿舍
    public static final String DormAdShow="SHOW_AD_DORM";  //管理员显示
    public static final String DormStShow="SHOW_ST_DORM";  //学生显示
    public static final String DormMaintain="MAINTAIN_DORM";  //维修设置
    public static final String DormExcange="REXCHANGE_DORM";  //调换设置
    
    //Shop
    public static final String GoodsAdd="ADD_GOODS";  //管理员添加商品
    public static final String GoodsDelete="REMOVE_GOODS";  //管理员删除商品
    public static final String GoodsSearch_ID="SEARCH_GOODS_ID";  //ID查找商品
    public static final String GoodsSearch_Name="SEARCH_GOODS_NAME";  //名称查找商品
    public static final String Buy="BUY_GOODS";  //学生购买
    public static final String AddNumberofGoods="ADDNUMBER-GOODS";  //管理员进货
    public static final String ModifyGoodsPrice="MODIFYPRICE-GOODS"; //管理员改价格
}
