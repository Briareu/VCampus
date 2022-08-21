package seu.list.server.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import seu.list.server.db.*;
import seu.list.common.*;

public class ClassAdminServer extends StudentAccessHelper {
	Connection con = null;

	// Modified by WU 8.16
	private Message mesFromClient; // 从客户端收到的数据
	private Message mesToClient;

	public ClassAdminServer(Message mesFromClient) {
		this.mesFromClient = mesFromClient;
	}

	@SuppressWarnings("unchecked")
	public void execute() {
		// 根据类型去执行不同的DAO层操作，不同模块的DAO类需要修改这个函数
		// 如果操作需要的参数，请在mesFromClient内取出
		// 如果操作需要返回数据给客户端，请存入dataToClient，如果没有返回值，则默认为null
		con = getConnection();
		mesToClient = new Message();
		switch (this.mesFromClient.getMessageType()) {
		case MessageType.ClassAdminAdd:
			try {
				int ret = add(con, (Student) this.mesFromClient.getData());
				this.mesToClient.setData(ret);
			} catch (Exception e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			break;
		case MessageType.ClassAdminDelete:
			try {
				List<Object> para = (List<Object>)this.mesFromClient.getData();
				int ret = delete(con, para);
				this.mesToClient.setData(ret);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		case MessageType.ClassAdminGetAll:
			try {
				Vector<Student> ret = this.getall(con);
				this.mesToClient.setData(ret);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case MessageType.ClassAdminUpdate:
			try {
				List<Object> para = (List<Object>)this.mesFromClient.getData();
				int ret = this.update(con, para);
				this.mesToClient.setData(ret);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case MessageType.ClassAdminSearch:
			try {
				List<Object> para = (List<Object>)this.mesFromClient.getData();
				Vector<Student> ret = this.select(con, para);
				this.mesToClient.setData(ret);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			//above is for student 
			//below is for class
		case MessageType.ClassAdd:
			try {
				int ret = addclass(con, (ClassManage) this.mesFromClient.getData());
				this.mesToClient.setData(ret);
			} catch (Exception e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			break;
		case MessageType.ClassDelete:
			try {
				String para = (String)this.mesFromClient.getData();
				int ret = deleteclass(con, para);
				this.mesToClient.setData(ret);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		case MessageType.ClassGetAll:
			try {
				Vector<ClassManage> ret = this.getallclass(con);
				this.mesToClient.setData(ret);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case MessageType.ClassSearch:
			try {
				List<Object> para = (List<Object>)this.mesFromClient.getData();
				Vector<ClassManage> ret = this.selectclass(con, para);
				this.mesToClient.setData(ret);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case MessageType.ClassUpdate:
			try {
				List<Object> para = (List<Object>)this.mesFromClient.getData();
				int ret = this.updateclass(con, para);
				this.mesToClient.setData(ret);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	public Message getMesToClient() { // 无需修改，网络层需要调用这个函数
		return this.mesToClient;
	}
	// Modified by WU 8.16
}