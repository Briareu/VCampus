package seu.list.server.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import seu.list.common.Goods;
import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.server.db.Shop_DbAccess;


public class ShopSever extends Shop_DbAccess{
	static Connection con = getConnection();
	static Statement s = null;
	
	
	
	static ResultSet rs=null;
	static double money=0;
	private Message mesFromClient;
	private Message mesToClient=new Message();
	public ShopSever(Message mesFromClient) {
		super();
		this.mesFromClient = mesFromClient;
		try {
			s=con.createStatement();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public void excute() {
		switch(this.mesFromClient.getMessageType()){
		    case MessageType.Goodsgetall:{
		    	this.mesToClient.setData(this.getList());
		    	
		    	break;
		    }
		    
			case MessageType.GoodsSearch_ID:{
				String para=(String)this.mesFromClient.getData();
				int ID=Integer.parseInt(para);
				this.mesToClient.setData(this.SearchGoods_ID(ID));
				break;
			}
			
			case MessageType.GoodsDelete:{
				String para=this.mesFromClient.getData()+"";
				int ID=Integer.parseInt(para);
				this.Deletegoods(ID);
				break;
			}
			
			case MessageType.GoodsAdd:{
				this.Addgoods((Goods)this.mesFromClient.getData());
				break;
			}
			
			case MessageType.ModifyGoodsPrice:{
				this.ModifyGoodsPrice((String[])this.mesFromClient.getData());
				break;
			}
			
			case MessageType.AddNumberofGoods:{
				this.AddnumberofGoods((String[])this.mesFromClient.getData());
				break;
			}
			
			case MessageType.GoodsSearch_Name:{
				this.mesToClient.setData(this.SearchGoods_Name((String)this.mesFromClient.getData()));
				break;
			}
			
			case MessageType.Buy:{
				this.buy((ArrayList<String>)this.mesFromClient.getData());
				break;
			}
			
			default: break;
			}
	}
	


	private static ArrayList<Goods> GoodsList=new ArrayList<Goods>();
	
	/* public static void main(String[] args) {
		//System.out.println(getList().get(1).getGoodsname());
		 /* ArrayList<Integer> a=new ArrayList<Integer>();
		 ArrayList<Integer> b=new ArrayList<Integer>();
		 a.add(1);
		 b.add(1);
		 buy(a,b);
		// System.out.println(SearchGoods_ID(1).getGoodsName());
		//Goods agoods=new Goods(6,"apple",3.0,100);
		//Addgoods(agoods);
		//AddnumberofGoods(1,3);
		//Deletegoods(5);
		//ModifyGoodsPrice(1,5.5);
	 }*/
	
	public static ArrayList<Goods> getGoodsList() {
		return GoodsList;
	}
	public static void setGoodsList(ArrayList<Goods> goodsList) {
		GoodsList = goodsList;
	}

	public static ArrayList<Goods> getList(){//从数据库导出
		try {
			
			con = getConnection();
			s = con.createStatement();// 创建SQL语句对象
			rs = s.executeQuery("select * from tb_Goods");	// 查询商品信息
						
			
			while(rs.next()) {
				Goods temp=new Goods();
				temp.setGoodsid(rs.getInt("GoodsID"));
				temp.setGoodsname(rs.getString("GoodsName"));
				temp.setGoodsprice(rs.getDouble("GoodsPrice"));
				temp.setGoodsnumber(rs.getInt("GoodsNumber"));
				GoodsList.add(temp);
				if(temp.getGoodsid()==-1)
					money=rs.getDouble("TurnOver");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	//		closeConnection(con, rs, s);
		}
		return GoodsList;
	}
	
	public static ArrayList<Goods> SearchGoods_Name(String temp){//按商品名称查找（所有符合）
		ArrayList<Goods> result=new ArrayList<Goods>();	
		for(int i=0;i<GoodsList.size();i++) {
			Goods tempGoods=GoodsList.get(i);
			if(tempGoods.getGoodsname().equals(temp)) 
				result.add(tempGoods);
		}
		return result;
	}
	
	public static Goods SearchGoods_ID(int temp){//按商品ID查找（唯一）	
		Goods result=new Goods();
		for(int i=0;i<GoodsList.size();i++) {
			Goods tempGoods=GoodsList.get(i);
			if(tempGoods.getGoodsid()==temp) 
				return tempGoods;
		}
		System.out.println("fail to find the Goods");
		return result;
		
	}
	
	public static void buy(ArrayList<String> args) {//购买(学生)arg[i]存id，arg[i+1]存购买的数量
		try{
			int result=0;
			for(int i=0;i<args.size();i+=2) {
				Goods temp=SearchGoods_ID(Integer.parseInt(args.get(i)));
				temp.setGoodsnumber(temp.getGoodsnumber()-Integer.parseInt(args.get(i+1)));
				result=s.executeUpdate("update tb_Goods set GoodsNumber='"+temp.getGoodsnumber()+"'where GoodsID='"+temp.getGoodsid()+"'");
				money+=temp.getGoodsprice()*Integer.parseInt(args.get(i+1));
		}
			result=s.executeUpdate("update tb_Goods set TurnOver='"+money+"'where GoodsID='"+1+"'");//第一行放营收额
			
			// System.out.println(money);
		
		}
		catch (Exception e) {
			e.printStackTrace();}
	}
	
	public static void Addgoods(Goods temp) {//增加商品(管理员)
		try{
			int result=0;
			GoodsList.add(temp);
			result=s.executeUpdate("insert into tb_Goods values('"+temp.getGoodsid()+"','"+temp.getGoodsname()+"','"+temp.getGoodsprice()+
					"','"+temp.getGoodsnumber()+"','"+0+"')");
			//System.out.println(result);
			 
		}
		catch (Exception e) {
			e.printStackTrace();}
	}
	
	public static void Deletegoods(int ID) {//下架商品(管理员)
		try{
			int result=0;
			Goods temp=SearchGoods_ID(ID);
			GoodsList.remove(temp);
			result=s.executeUpdate("delete from tb_Goods where GoodsID='"+ID+"'");
			//System.out.println(result);
			 
		}
		catch (Exception e) {
			e.printStackTrace();}
	}
	
	public static void AddnumberofGoods(String[] args) {//进货（管理员）
		try{
			int result=0;
			int ID=Integer.parseInt(args[0]);
			int number=Integer.parseInt(args[1]);
			Goods temp=SearchGoods_ID(ID);
			int sum=temp.getGoodsnumber()+number;
			temp.setGoodsnumber(sum);
			result=s.executeUpdate("update tb_Goods set GoodsNumber='"+sum+"'where GoodsID='"+ID+"'");
			//System.out.println(result);
			 
		}
		catch (Exception e) {
			e.printStackTrace();}
		
	}
	
	public static void ModifyGoodsPrice(String[] args) {//修改价格（管理员）
		try{
			int result=0;
			int ID=Integer.parseInt(args[0]);
			double price=Double.parseDouble(args[1]);
			Goods temp=SearchGoods_ID(ID);
			temp.setGoodsprice(price);
			result=s.executeUpdate("update tb_Goods set GoodsPrice='"+price+"'where GoodsID='"+ID+"'");
			//System.out.println(result);
			 
		}
		catch (Exception e) {
			e.printStackTrace();}
		
	}
	
	public Message getMesToClient() {
		return this.mesToClient;
	}
}
