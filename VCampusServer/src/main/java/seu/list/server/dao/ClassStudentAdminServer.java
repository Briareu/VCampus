package seu.list.server.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import seu.list.server.db.*;
import seu.list.common.*;
import java.util.List;

public class ClassStudentAdminServer extends StudentAccessHelper{
	Connection con = null;

	// Modified by WU 8.16
	private Message mesFromClient; // 从客户端收到的数据
	private Object dataToClient = null; // 发给客户端的数据

	public ClassStudentAdminServer(Message mesFromClient) {
		this.mesFromClient = mesFromClient;
	}

	@SuppressWarnings("unchecked")
	public void execute() {
		// 根据类型去执行不同的DAO层操作，不同模块的DAO类需要修改这个函数
		// 如果操作需要的参数，请在mesFromClient内取出
		// 如果操作需要返回数据给客户端，请存入dataToClient，如果没有返回值，则默认为null
		con = getConnection();
		switch(this.mesFromClient.getMessageType())
		{
			case MessageType.ClassAdminAdd:
			try {
				this.dataToClient = add(con, (Student)this.mesFromClient.getData());
			} catch (Exception e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
				break;
			case MessageType.ClassAdminDelete:
			try {
				this.dataToClient = delete(con, (String)this.mesFromClient.getData());
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
				break;
			case MessageType.ClassAdminGetAll:
			try {
				this.dataToClient = this.getall(con);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				break;
			case MessageType.ClassAdminUpdate:
			try {
				this.update(con, (List<Object>)this.mesFromClient.getData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			default:
				break;
		}
	}

	public Object getDataToClient() { // 无需修改，网络层需要调用这个函数
		return this.dataToClient;
	}
	// Modified by WU 8.16

	/*
	public static void main(String[] args) {
		ClassAdminServer serve=new ClassAdminServer();
		serve.delete("3");
	}
	*/
}