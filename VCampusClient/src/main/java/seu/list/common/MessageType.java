package seu.list.common;

/**
 * 类{@code MessageType}包含了所有的消息操作类型，供{@code DAO}类解析进行具体操作
 * @author 柳多荣 吴慕陶 郭念宗 王映方 周楚翘 欧阳瑜 廖侃
 * @version 1.0
 */
public final class MessageType {

    public static final String REQ_LOGIN="REQ_LOGIN";
    public static final String REQ_LOGOUT="REQ_LOGOUT";
    public static final String REQ_REGISTER="REQ_REGISTER";
    public static final String REQ_USERDEL="REQ_USERDEL";
    public static final String REQ_USERUPDATE="REQ_USERUPDATE";
    //Message-Type
    public static final String operFeedback = "OPER_FEEDBACK";


    //server操作ClassAdmin
    public static final String ClassAdminGetAll = "CLASS_ADMIN_GETALL";
    public static final String ClassAdminAdd = "CLASS_ADMIN_ADD";
    public static final String ClassAdminDelete = "CLASS_ADMIN_DELETE";
    public static final String ClassAdminUpdate = "CLASS_ADMIN_UPDATE";
    public static final String ClassAdminSearch = "CLASS_ADMIN_SEARCH";//up is to do with student manage
    public static final String ClassSearch = "CLASS_SEARCH";
    public static final String ClassGetAll = "CLASS_GET_ALL";
    public static final String ClassDelete = "CLASS_DELETE";
    public static final String ClassAdd = "CLASS_ADD";
    public static final String ClassUpdate = "CLASS_UPDATE";//here is for class managing


    //server操作LibraryUser
    public static final String LibraryBookGetAll = "LIB_GETALL";
    public static final String LibraryBookAdd = "LIB_ADD";
    public static final String LibraryBookDelete = "LIB_DELETE";
    public static final String LibraryBookUpdate = "LIB_UPDATE";  
    public static final String LibraryBookLend = "LIB_LEND";
    public static final String LibraryBookReturn = "LIB_RETURN";
    public static final String LibraryBookFind = "LIB_FIND";

    public static final String REQ_SEARCH_LESSON="REQ_SEARCH_LESSON"; //数据库查询课程
    public static final String REQ_REMOVE_LESSON="REQ_REMOVE_LESSON"; //老师加课
    public static final String REQ_ADD_LESSON="REQ_ADD_LESSON"; //老师删课
    public static final String REQ_SHOW_ALL_LESSON="REQ_SHOW_ALL_LESSON"; //显示全部课程
    public static final String REQ_STU_ADD_LESSON="REQ_STU_ADD_LESSON"; //学生选课
    public static final String REQ_STU_REMOVE_LESSON="REQ_STU_REMOVE_LESSON"; //学生退课
    public static final String REQ_STU_ALL_CHOOOSE="REQ_STU_ALL_CHOOOSE";
    
    //Test
    public static final String TestAdmin = "TEST_ADMIN";
    public static final String TestStudent = "TEST_STUDENT";
    
    //Dormitory
    public static final String DormAdd="ADD_DORM";  //添加宿舍
    public static final String DormDelete="REMOVE_DORM";  //删除宿舍
    public static final String DormSearch="SEARCH_DORM";  //查找宿舍
    public static final String DormAdShow="SHOW_AD_DORM";  //管理员显示
    public static final String DormModify ="MODIFY_DORM"; 
    public static final String DormStShow="SHOW_ST_DORM";  //学生显示
    public static final String DormMaintain="MAINTAIN_DORM";  //维修设置
    public static final String DormExcange="REXCHANGE_DORM";  //调换设置
    public static final String DormUpdate="UPDATE_DORM";//更新id

    //Shop
    public static final String Goodsgetall="GETALL_GOODS";  
    public static final String Goodsgetturnover="GET_TURNOVER"; //获得营收值
    public static final String GoodsAdd="ADD_GOODS";  //管理员添加商品
    public static final String GoodsDelete="REMOVE_GOODS";  //管理员删除商品
    public static final String GoodsSearch_ID="SEARCH_GOODS_ID";  //ID查找商品
    public static final String GoodsSearch_Name="SEARCH_GOODS_NAME";  //名称查找商品
    public static final String Buy="BUY_GOODS";  //学生购买
    public static final String ModifyGoods="MODIFY-GOODS"; //管理员改信息
    
}
