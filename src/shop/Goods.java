 /*
   * ClassName: Goods
   *
   * Version 1.0
   *
   * Date: 2022.08.12
   * 
   * GoodsID, GoodsName, GoodsPrice, GoodsNumber
   * 
   * Set to manage student
   * 
   * Last modified by Ouyang
   */
package shop;

import java.io.Serializable;
public class Goods implements Serializable{
		private int GoodsID;
		private String GoodsName;
		private double GoodsPrice;
		private int GoodsNumber;
		
		public Goods() {
			super();
		}
		
		public Goods(int goodsid,String goodsname,double goodsprice,int goodsnumber){
			GoodsID=goodsid;
			GoodsName=goodsname;
			GoodsPrice=goodsprice;
			GoodsNumber=goodsnumber;
		}
		
		public int getGoodsid() {
			return GoodsID;
		}
		
		public void setGoodsid(int goodsid) {
			GoodsID = goodsid;
		}
		public String getGoodsname() {
			return GoodsName;
		}
		
		public void setGoodsname(String goodsname) {
			GoodsName = goodsname;
		}
		
		public double getGoodsprice() {
			return GoodsPrice;
		}
		
		public void setGoodsprice(double goodsprice) {
			GoodsPrice = goodsprice;
		}
		
		public int getGoodsnumber() {
			return GoodsNumber;
		}
		
		public void setGoodsnumber(int goodsnumber) {
			GoodsNumber = goodsnumber;
		}

		@Override
		public String toString() {
			return "Goods{" + "GoodsID" + GoodsID + ", GoodsName" + GoodsName + ", GoodsPrice" + GoodsPrice + ", GoodsNumber" + GoodsNumber + "}";
		}
}
