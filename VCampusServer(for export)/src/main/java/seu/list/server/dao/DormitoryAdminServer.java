//not in use!
package seu.list.server.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;

import seu.list.server.db.*;
import seu.list.common.*;

public class DormitoryAdminServer extends Dormitory_DbAccess{

	static Connection con = null;
	static Statement s = null;
	static ResultSet rs=null;
	
	private static ArrayList<Dormitory> Dorm = new ArrayList<Dormitory>();
	
	private Message mesFromClient; // 从客户端收到的数据
	private Message mesToClient;

	public DormitoryAdminServer(Message mesFromClient) {
		super();
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
		case MessageType.DormAdd:
			try {
				Dormitory temp = (Dormitory)this.mesFromClient.getData();
				int result=0;
				Dorm.add(temp);
				result=s.executeUpdate("insert into Dormitory values('"+temp.getuserID()+"','"+temp.getDormitoryID()+"','"+temp.getStudentBunkID()+
						"','"+temp.getWater()+"','"+temp.getElectricity()+"','"+temp.getDormitoryScore()+"','"+temp.getDormitoryMaintain()+"','"+
						temp.getStudentExchange()+"','"+0+"')");
				//System.out.println(result);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		case MessageType.DormDelete:
			try {
				String para=(String) this.mesFromClient.getData();
				String userID=para;
				int result=0;
				Dormitory temp=SearchuserID(userID);
				Dorm.remove(temp);
				result=s.executeUpdate("delete from Dormitory where userID='"+userID+"'");
				//System.out.println(result);

			}
			catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case MessageType.DormModify:
			try {
				String[] para = (String[])this.mesFromClient.getData();
				int result=0;
				String userID=para[0];
				String usertype = para[1];
				int temp = Integer.parseInt(para[2]);
				Dormitory tdorm=SearchuserID(userID);
				if ("卫生评分".equals(usertype)) 
				{
					tdorm.setDormitoryScore(temp);
				}
				if ("水费".equals(usertype)) 
				{
					tdorm.setWater(temp);
				}
				if ("电费".equals(usertype)) 
				{
					tdorm.setElectricity(temp);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case MessageType.DormAdShow:
			try {
				con = getConnection();
				s = con.createStatement();// 创建SQL语句对象
				rs = s.executeQuery("select * from Dormitory");	// 查询商品信息
				//把数据库中的数据读入
				while(rs.next()) {
					Dormitory temp=new Dormitory();
					temp.setuserID(rs.getString("userID"));
					temp.setDormitoryID(rs.getString("DormitoryID"));
					temp.setStudentBunkID(rs.getInt("StudentBunkID"));
					temp.setWater(rs.getInt("Water"));
					temp.setElectricity(rs.getInt("Electricity"));
					temp.setDormitoryScore(rs.getInt("DormitoryScore"));
					temp.setDormitoryMaintain(rs.getString("DormitoryMaintain"));
					temp.setStudentExchange(rs.getString("StudentExchange"));
					System.out.println(temp);
					Dorm.add(temp);
				}
				this.mesToClient.setData(Dorm);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeConnection(con, rs, s);
			}
			
		default:
			break;
		}
	}

	private Dormitory SearchuserID(String userID) {
		// TODO Auto-generated method stub
		Dormitory temp=new Dormitory();
		for (int i=0;i<Dorm.size();i++)
		{
			Dormitory Temp=Dorm.get(i);
			if (Temp.getuserID()==userID) return Temp;	
		}
		System.out.println("fail to find the userID");
		return temp;
	}

	public Message getMesToClient() { // 无需修改，网络层需要调用这个函数
		return this.mesToClient;
	}
}
