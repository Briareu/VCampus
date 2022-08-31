 
package seu.list.common;

import java.io.Serializable;
/**
 * 类{@code Goods}为商品类
 * @author 欧阳瑜
 * @version 1.0
 * 
 */
public class Goods implements Serializable{
		private static final long serialVersionUID = 1L;
	
		private int GoodsID;
		private String GoodsName;
		private double GoodsPrice;
		private int GoodsNumber;

		public Goods() {
			super();
		}
		/**
		 * 构造函数
		 * @param goodsid 商品编号
		 * @param goodsname 商品名
		 * @param goodsprice 商品价格
		 * @param goodsnumber 库存
		 */
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
