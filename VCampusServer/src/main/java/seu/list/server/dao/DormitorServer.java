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
			this.mesToClient.setData(this.Maintain((ArrayList<String>)this.mesFromClient.getData()));
			break;
		case MessageType.DormExcange:
				/*
				con = getConnection();
				s = con.createStatement();// 创建SQL语句对象
				rs = s.executeQuery("select * from Dormitory");	// 查询商品信息*/
			this.mesToClient.setData(this.Exchange((ArrayList<String>)this.mesFromClient.getData()));
			break;
		case MessageType.DormStShow:
			this.mesToClient.setData(this.Show(this.mesFromClient.getData().toString()));
			break;
		case MessageType.DormAdd:
			try {
				//数据库添加？？？？
				/*int result=0;
				result=s.executeUpdate("insert into Dormitory values('"+temp.getuserID()+"','"+temp.getDormitoryID()+"','"+temp.getStudentBunkID()+
						"','"+temp.getDormitoryScore()+"','"+temp.getWater()+"','"+temp.getElectricity()+"','"+temp.getDormitoryMaintain()+"','"+
						temp.getStudentExchange()+"','"+0+"')");*/
				this.mesToClient.setData(this.Add((Dormitory)this.mesFromClient.getData()));
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		case MessageType.DormDelete:
			this.mesToClient.setData(this.Delete(this.mesFromClient.getData().toString()));
			break;
		case MessageType.DormModify:
			this.mesToClient.setData(this.Modify((ArrayList<String>)this.mesFromClient.getData()));
				//int result=0;
				/*
				con = getConnection();
				s = con.createStatement();// 创建SQL语句对象
				rs = s.executeQuery("select * from Dormitory");	// 查询商品信息
				this.mesToClient.setData(tdorm);*/
			break;
		case MessageType.DormAdShow:		
			this.mesToClient.setData(this.AllDormitory());
			break;
		case MessageType.DormSearch:
			this.mesToClient.setData(this.SearchuserID(this.mesFromClient.getData().toString()));
			break;
		default:
			break;
		}
	}

	private Dormitory Maintain(ArrayList<String> para) {
		// TODO Auto-generated method stub
		System.out.println(para);
		String userID = para.get(0);
		String dormID = para.get(1);
		String maintain = para.get(2);
		Dormitory temp=new Dormitory();
		Dorm = AllDormitory();
		for (int i = 0; i < Dorm.size(); i++)
			if (Dorm.get(i).getuserID().equals(userID)) {
				Dorm.get(i).setStudentExchange(maintain);
				temp=Dorm.get(i);
			}
		return temp;
	}

	private Dormitory Exchange(ArrayList<String> para) {
		System.out.println(para);
		String userID = para.get(0);
		String dormID = para.get(1);
		String exchange = para.get(2);
		Dormitory temp=new Dormitory();
		Dorm = AllDormitory();
		for (int i = 0; i < Dorm.size(); i++)
			if (Dorm.get(i).getuserID().equals(userID)) {
				Dorm.get(i).setStudentExchange(exchange);
				temp=Dorm.get(i);
			}
		return temp;
	}

	private Dormitory Show(String userID) {
		// TODO Auto-generated method stub
		Dorm = AllDormitory();
		Dormitory temp=new Dormitory();
		for (int i = 0; i < Dorm.size(); i++)
			if (Dorm.get(i).getuserID().equals(userID)) 
				{
				temp=Dorm.get(i);
				break;
				}
		System.out.println("!!!!!!");
		System.out.println(temp);
		return temp;
	}

	private ArrayList<Dormitory> Modify(ArrayList<String> para) {
		System.out.println(para);
		String userID = para.get(0);
		String usertype = para.get(1);
		int temp = Integer.parseInt(para.get(2));
		Dorm = AllDormitory();
		for (int i = 0; i < Dorm.size(); i++)
			if (Dorm.get(i).getuserID().equals(userID)) {
				if ("卫生评分".equals(usertype)) {
					Dorm.get(i).setDormitoryScore(temp);
				}
				if ("水费".equals(usertype)) {
					Dorm.get(i).setWater(temp);
				}
				if ("电费".equals(usertype)) {
					Dorm.get(i).setElectricity(temp);
				}
			}
		return Dorm;
	}

	private ArrayList<Dormitory> Add(Dormitory data) {
		ArrayList<Dormitory> dorm=AllDormitory();
		dorm.add(data);
		return dorm;
	}

	private ArrayList<Dormitory> Delete(String string) {
		// TODO Auto-generated method stub
		ArrayList<Dormitory> dorm=AllDormitory();
		for (int i=0;i<dorm.size();i++)
			if (dorm.get(i).getuserID().equals(string)) dorm.remove(i);
		return dorm;
	}

	private ArrayList<Dormitory> AllDormitory() //从数据库中获取所有数据
	{
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

	private ArrayList<Dormitory> SearchuserID(String userID) {
		// TODO Auto-generated method stub
		ArrayList<Dormitory> temp=new ArrayList<Dormitory>();
		System.out.println(userID);
		Dorm=AllDormitory();
		for (int i=0;i<Dorm.size();i++)
		{
			Dormitory s=new Dormitory();
			s=Dorm.get(i);
			if (s.getuserID().equals(userID)) 	temp.add(s);
		}
		return temp;
	}

	public Message getMesToClient() { // 无需修改，网络层需要调用这个函数
		return this.mesToClient;
	}
}
