package seu.list.common;


import java.util.*;

public class ClientReq implements java.io.Serializable {
	private static final long serialVersionUID = 8454558035178168414L;
	
	//client���ڷ�����ض�����
	private String type;
	private String level;//������Ȩ��
	private Vector<String> Content;//
	
	public String getType() {//���ص�ǰ�������
		return type;
	}
	public void setType(String type) {
		this.type = type;//������������
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	//���ݴ���
	public Vector<String> getContent() {
		return Content;
	}//�õ�����
	public void setContent(Vector<String> content) {//��ʾʱ�������±���м�¼����ʾ
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
