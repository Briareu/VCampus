package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Goods.Goods;

public class ShopSever extends Shop_DbAccess{
	static Connection con = null;
	static Statement s = null;
	static ResultSet rs=null;
	static double money=0;
<<<<<<< HEAD
=======
	private Message mesFromClient;
	private Message mesToClient;
	public ShopSever(Message mesFromClient) {
		super();
		this.mesFromClient = mesFromClient;
	}

	public void excute() {
		switch(this.mesFromClient.getMessageType()){
		    case MessageType.Goodsgetall:{
		    	this.getGoodsList();
		    	break;
		    }
		    
			case MessageType.GoodsSearch_ID:{
				String para=(String)this.mesFromClient.getData();
				int ID=Integer.parseInt(para);
				this.mesToClient.setData(this.SearchGoods_ID(ID));
				break;
			}
			
			case MessageType.GoodsDelete:{
				String para=(String)this.mesFromClient.getData();
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
			
			default: break;
			}
	}
	

>>>>>>> 27e89bf9ba1d83dbc5221972383f137fcce3b463

	private static ArrayList<Goods> GoodsList=new ArrayList<Goods>();

	 public static void main(String[] args) {
		//System.out.println(getList().get(1).getGoodsName());
		 /* ArrayList<Integer> a=new ArrayList<Integer>();
		 ArrayList<Integer> b=new ArrayList<Integer>();
		 a.add(1);
		 b.add(1);
		 buy(a,b);
		// System.out.println(SearchGoods_ID(1).getGoodsName());
		Goods agoods=new Goods(5,"apple",3.0,100);
		Addgoods(agoods);*/
		//AddnumberofGoods(1,3);
		//Deletegoods(5);
		//ModifyGoodsPrice(1,5.5);
	 }

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
			rs = s.executeQuery("select * from Goods");	// 查询商品信息

			//把数据库中的数据读入bookList
			while(rs.next()) {
				Goods temp=new Goods();
				temp.setGoodsID(rs.getInt("GoodsID"));
				temp.setGoodsName(rs.getString("GoodsName"));
				temp.setGoodsPrice(rs.getDouble("GoodsPrice"));
				temp.setGoodsNumber(rs.getInt("GoodsNumber"));
				GoodsList.add(temp);
				if(temp.getGoodsID()==1)
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
			if(tempGoods.getGoodsName()==temp)
				result.add(tempGoods);
		}
		return result;
	}

	public static Goods SearchGoods_ID(int temp){//按商品ID查找（唯一）
		Goods result=new Goods();
		for(int i=0;i<GoodsList.size();i++) {
			Goods tempGoods=GoodsList.get(i);
			if(tempGoods.getGoodsID()==temp)
				return tempGoods;
		}
		System.out.println("fail to find the Goods");
		return result;

	}

	public static void buy(ArrayList<Integer> goodsID,ArrayList<Integer> number) {//购买(学生)
		try{
			int result=0;
			for(int i=0;i<goodsID.size();i++) {
				Goods temp=SearchGoods_ID(goodsID.get(i));
				temp.setGoodsNumber(temp.getGoodsNumber()-number.get(i));
				result=s.executeUpdate("update Goods set GoodsNumber='"+temp.getGoodsNumber()+"'where GoodsID='"+temp.getGoodsID()+"'");
				money+=temp.getGoodsPrice()*number.get(i);
		}
			result=s.executeUpdate("update Goods set TurnOver='"+money+"'where GoodsID='"+1+"'");//第一行放营收额

			// System.out.println(money);

		}
		catch (Exception e) {
			e.printStackTrace();}
	}

	public static void Addgoods(Goods temp) {//增加商品(管理员)
		try{
			int result=0;
			GoodsList.add(temp);
			result=s.executeUpdate("insert into Goods values('"+temp.getGoodsID()+"','"+temp.getGoodsName()+"','"+temp.getGoodsPrice()+
					"','"+temp.getGoodsNumber()+"','"+0+"')");
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
			result=s.executeUpdate("delete from Goods where GoodsID='"+ID+"'");
			//System.out.println(result);

		}
		catch (Exception e) {
			e.printStackTrace();}
	}
<<<<<<< HEAD

	public static void AddnumberofGoods(int ID ,int number) {//进货（管理员）
=======
	
	public static void AddnumberofGoods(String[] args) {//进货（管理员）
>>>>>>> 27e89bf9ba1d83dbc5221972383f137fcce3b463
		try{
			int result=0;
			int ID=Integer.parseInt(args[0]);
			int number=Integer.parseInt(args[1]);
			Goods temp=SearchGoods_ID(ID);
			int sum=temp.getGoodsNumber()+number;
			temp.setGoodsNumber(sum);
			result=s.executeUpdate("update Goods set GoodsNumber='"+sum+"'where GoodsID='"+ID+"'");
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
			temp.setGoodsPrice(price);
			result=s.executeUpdate("update Goods set GoodsPrice='"+price+"'where GoodsID='"+ID+"'");
			//System.out.println(result);
			 
		}
		catch (Exception e) {
			e.printStackTrace();}
		
	}
	
}
