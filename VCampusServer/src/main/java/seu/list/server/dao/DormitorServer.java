package seu.list.server.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;

import seu.list.server.db.*;
import seu.list.common.*;

public class DormitorServer extends Dormitory_DbAccess{
	static Connection con = null;
	static Statement s = null;
	static ResultSet rs=null;
	
	private Message mesFromClient; // 从客户端收到的数据
	private Message mesToClient;

	ArrayList<Dormitory> Dorm = new ArrayList<Dormitory>();
	
	public DormitorServer(Message mesFromClient) {
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
		case MessageType.DormMaintain:
			try {
				Dormitory dorm=new Dormitory();
				ArrayList<String> para = (ArrayList<String>)this.mesFromClient.getData();
				System.out.println(para);
				int result=0;
				String userID=para.get(0);
				String dormID = para.get(1);
				String maintain=para.get(2);
				con = getConnection();
				s = con.createStatement();// 创建SQL语句对象
				rs = s.executeQuery("select * from Dormitory");	// 查询商品信息
				this.mesToClient.setData(dorm);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case MessageType.DormExcange:
			try {
				ArrayList<String> para = (ArrayList<String>)this.mesFromClient.getData();
				System.out.println(para);
				int result=0;
				String userID=para.get(0);
				String dormID = para.get(1);
				String exchange=para.get(2);
				Dormitory tdorm=SearchuserID(userID);
				
				con = getConnection();
				s = con.createStatement();// 创建SQL语句对象
				rs = s.executeQuery("select * from Dormitory");	// 查询商品信息
				this.mesToClient.setData(tdorm);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case MessageType.DormStShow:
			try {
			con = getConnection();
			s = con.createStatement();// 创建SQL语句对象
			rs = s.executeQuery("select * from Dormitory");	// 查询商品信息
			//把数据库中的数据读入
			while(rs.next()) {
				//if () 
				{
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
				this.mesToClient.setData(temp);
				break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, rs, s);
		}
		case MessageType.DormAdd:
			try {
				Dormitory temp = (Dormitory)this.mesFromClient.getData();
				int result=0;
				System.out.println(temp);
				
				//this.dataToClient = add(con, temp);
				Dorm.add(temp);
				con = getConnection();
				s = con.createStatement();// 创建SQL语句对象
				rs = s.executeQuery("select * from Dormitory");	// 查询商品信息
				result=s.executeUpdate("insert into Dormitory values('"+temp.getuserID()+"','"+temp.getDormitoryID()+"','"+temp.getStudentBunkID()+
						"','"+temp.getDormitoryScore()+"','"+temp.getWater()+"','"+temp.getElectricity()+"','"+temp.getDormitoryMaintain()+"','"+
						temp.getStudentExchange()+"','"+0+"')");
				
				this.mesToClient.setData(temp);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		case MessageType.DormDelete:
			try {
				String para=(String) this.mesFromClient.getData();
				String userID=para;
				System.out.println(userID);
				int result=0;
				Dormitory temp=SearchuserID(userID);
				Dorm.remove(temp);
				result=s.executeUpdate("delete from Dormitory where userID='"+userID+"'");
				System.out.println(result);

			}
			catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case MessageType.DormModify:
			try {
				ArrayList<String> para = (ArrayList<String>)this.mesFromClient.getData();
				System.out.println(para);
				int result=0;
				String userID=para.get(0);
				String usertype = para.get(1);
				int temp = Integer.parseInt(para.get(2));
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
				con = getConnection();
				s = con.createStatement();// 创建SQL语句对象
				rs = s.executeQuery("select * from Dormitory");	// 查询商品信息
				this.mesToClient.setData(tdorm);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case MessageType.DormAdShow:		
			this.mesToClient.setData(this.AllDormitory());
			break;
		case MessageType.DormSearch:
			System.out.println(this.mesFromClient.getData().toString());
			this.mesToClient.setData(this.SearchuserID(this.mesFromClient.getData().toString()));
		default:
			break;
		}
	}

	private ArrayList<Dormitory> AllDormitory() {
		// TODO Auto-generated method stub
		ArrayList<Dormitory> dorm = new ArrayList<Dormitory>();
		try {
			con = getConnection();
			s = con.createStatement();// 创建SQL语句对象
			rs = s.executeQuery("select * from Dormitory"); // 查询商品信息
			// 把数据库中的数据读入
			while (rs.next()) {

				Dormitory temp = new Dormitory();
				temp.setuserID(rs.getString("userID"));
				temp.setDormitoryID(rs.getString("DormitoryID"));
				temp.setStudentBunkID(rs.getInt("StudentBunkID"));
				temp.setWater(rs.getInt("Water"));
				temp.setElectricity(rs.getInt("Electricity"));
				temp.setDormitoryScore(rs.getInt("DormitoryScore"));
				temp.setDormitoryMaintain(rs.getString("DormitoryMaintain"));
				temp.setStudentExchange(rs.getString("StudentExchange"));
				System.out.println(temp);
				dorm.add(temp);
				}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, rs, s);
		}
		System.out.println("get all information");
		return dorm;
	}

	private Dormitory SearchuserID(String userID) {
		// TODO Auto-generated method stub
		ArrayList<Dormitory> Dorm = this.AllDormitory();
		System.out.println("!!!!!");
		Dormitory temp=new Dormitory();
		try {
		for (int i=0;i<Dorm.size();i++)
		{
			Dormitory Temp=Dorm.get(i);
			if (Temp.getuserID()==userID) return temp;	
		}
		System.out.println("fail to find the userID");
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, rs, s);
		}
		return temp;
	}

	public Message getMesToClient() { // 无需修改，网络层需要调用这个函数
		return this.mesToClient;
	}
}
