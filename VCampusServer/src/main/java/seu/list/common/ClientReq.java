package seu.list.common;


import java.util.*;

public class ClientReq implements java.io.Serializable {
	private static final long serialVersionUID = 8454558035178168414L;
	//client用于发送相关东西的
	private String type;
	private String level;//发送者权限
	private Vector<String> Content;//用于存放前端传来的初始化数据

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Vector<String> getContent() {
		return Content;
	}
	public void setContent(Vector<String> content) {
		Content = content;
	}
}
