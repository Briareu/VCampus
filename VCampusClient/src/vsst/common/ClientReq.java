package virtualSchoolClient.src.vsst.common;

import java.util.*;

public class ClientReq implements java.io.Serializable {
	private static final long serialVersionUID = 8454558035178168414L;
	
	//client用于发送相关东西的
	private String type;
	private String level;//发送者权限
	private Vector<String> Content;//
	
	public String getType() {//返回当前申请类别
		return type;
	}
	public void setType(String type) {
		this.type = type;//设置申请类型
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	//数据传输
	public Vector<String> getContent() {
		return Content;
	}//得到数据
	public void setContent(Vector<String> content) {//显示时可以用下标进行记录的显示
		Content = content;
	}
	
	/*public String operate;
	public Vector vec;
	
	public ClientReq(String operate, Vector vec)
	{
		this.operate = operate;
		this.vec = vec;
	}*/
}
