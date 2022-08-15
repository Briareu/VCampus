package virtualSchoolClient.src.vsst.common;

public interface ClientReqType {
	/**
	 * 
	 */
	//----面向登录----//
	String REQ_LOGIN="REQ_LOGIN";
	String REQ_LOGOUT="REQ_LOGOUT";
	String REQ_REGISTER="REQ_REGISTER";
	
	//----学籍管理----//
	String REQ_SHOW_SIG_INFOR="REQ_SHOW_SIG_INFOR";//查看单个学籍信息，client在vector中提供ID号
	String REQ_REMOVE_SIG_INFOR="REQ_REMOVE_SIG_INFOR";//删除单个学籍信息，client在vector中提供ID号
	String REQ_ADD_SIG_INFOR="REQ_ADD_SIG_INFOR";//增加单个学籍信息，client在Vector中提供全部信息
	String REQ_SHOW_ALL_INFOR="REQ_SHOW_ALL_INFOR";//查看全部学籍信息
	
	//----选课模块----//
	String REQ_SEARCH_LESSON="REQ_SEARCH_LESSON";//数据库查询课程
	String REQ_REMOVE_LESSON="REQ_REMOVE_LESSON";//老师加课
	String REQ_ADD_LESSON="REQ_ADD_LESSON";//老师删课
	String REQ_SHOW_ALL_LESSON="REQ_SHOW_ALL_LESSON";//显示全部课程
	String REQ_STU_ADD_LESSON="REQ_STU_ADD_LESSON";//学生选课
	String REQ_STU_REMOVE_LESSON="REQ_STU_REMOVE_LESSON";//学生退课
	
	//----图书馆模块----//
	String REQ_ADD_BOOK="REQ_ADD_BOOK";
	String REQ_REMOVE_BOOK="REQ_REMOVE_BOOK";
	String REQ_BORROW_BOOK="REQ_BORROW_BOOK";
	String REQ_RETURN_BOOK="REQ_RETURN_BOOK";
	String REQ_SHOW_ALL_BOOK="REQ_SHOW_ALL_BOOK";
	String REQ_SHOW_SIG_BOOK = "REQ_SHOW_SIG_BOOK";
	
	//----商店模块----//
	String REQ_BUY_GOODS="REQ_BUY_GOODS";
	String REQ_ADD_GOODS="REQ_ADD_GOODS";
	String REQ_REMOVE_GOODS="REQ_REMOVE_GOODS";
	String REQ_SHOW_ALL_GOODS="REQ_SHOW_ALL_GOODS";
	String REQ_SHOW_SIG_GOODS = "REQ_SHOW_SIG_GOODS";
	
	//----学生借书、选课所有信息----//
	String REQ_STU_ALL_CHOOOSE="REQ_STU_ALL_CHOOOSE";
	String REQ_STU_ALL_BORROW="REQ_STU_ALL_BORROW";

}
