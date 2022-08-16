package seu.list.server.db;
import virtualSchoolServer.common.Book;
import virtualSchoolServer.common.Course;
import virtualSchoolServer.common.Goods;
import virtualSchoolServer.common.User;
import virtualSchoolServer.server.dao.DAOUtil;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;



public class SqlHelperImp implements SqlHelper{
	private static final String HXTT_ACCESS_JDBC_DRIVER="net.ucanaccess.jdbc.UcanaccessDriver";
	private static final String ACCESS_DRIVER = HXTT_ACCESS_JDBC_DRIVER;
	private static String url;
	static {
		String dbpath = new File("").getAbsolutePath().replace('\\', '/') + "/db_vCampus.accdb";
		url = "jdbc:ucanaccess://" + dbpath;
	}
	String user="";
	String passwd="";

	public boolean sqlUpdate(String sql,String []paras)
	{
		PreparedStatement ps=null;
		Connection ct=null;
		boolean b=true;
		try {
			//1.????????
			Class.forName(ACCESS_DRIVER);
			//2.???????
			ct=DriverManager.getConnection(url);
			ps=ct.prepareStatement(sql);
			//??ps???????
			for(int i=0;i<paras.length;i++)
			{
				ps.setString(i+1, paras[i]);
			}

			//??锟斤拷???
			ps.executeUpdate();

		} catch (Exception e) {
			b=false;
			e.printStackTrace();
		}finally{
			//??????
			try {
				if(ps!=null)
					ps.close();
				if(ct!=null)
					ct.close();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return b;
	}

	public List<User> sqlUserQuery(String sql, String []paras)
	{
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;
		List<User> users = null;

		try {
			//1.????????
			Class.forName(ACCESS_DRIVER);
			//2.???????
			ct=DriverManager.getConnection(url,user,passwd);

			ps=ct.prepareStatement(sql);
			//??ps???????
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			//??锟斤拷???
			rs=ps.executeQuery();
			users = DAOUtil.UserResultSet2List(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//??????
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(ct!=null)
					ct.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return users;
	}

	@Override
	public List<Book> sqlBookQuery(String sql, String[] paras) {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;
		List<Book> books = null;

		try {
			//1.????????
			Class.forName(ACCESS_DRIVER);
			//2.???????
			ct=DriverManager.getConnection(url,user,passwd);
			ps=ct.prepareStatement(sql);
			//??ps???????
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			//??锟斤拷???
			rs=ps.executeQuery();
			books = DAOUtil.BookResultSet2List(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//??????
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(ct!=null)
					ct.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return books;
	}

	@Override
	public List<Goods> sqlGoodsQuery(String sql, String[] paras) {
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;
		List<Goods> goods = null;

		try {
			//1.????????
			Class.forName(ACCESS_DRIVER);
			//2.???????
			ct=DriverManager.getConnection(url,user,passwd);

			ps=ct.prepareStatement(sql);
			//??ps???????
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			//??锟斤拷???
			rs=ps.executeQuery();
			goods = DAOUtil.GoodsResultSet2List(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//??????
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(ct!=null)
					ct.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return goods;
	}

	@Override
	public List<Course> sqlCourseQuery(String sql, String[] paras) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		Connection ct = null;
		ResultSet rs=null;
		List<Course> courses=null;
		try{
			Class.forName(ACCESS_DRIVER);
			ct=DriverManager.getConnection(url, user, passwd);
			ps=ct.prepareStatement(sql);
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			rs=ps.executeQuery();
			courses = DAOUtil.CourseResultSet2List(rs);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(ct!=null)
					ct.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return courses;
	}

	@Override
	public List<String> sqlRelationQuery(String sql, String[] paras) {
		// 用来获取对应关系
		PreparedStatement ps = null;
		Connection ct = null;
		ResultSet rs=null;
		List<String> objs=null;
		try{
			Class.forName(ACCESS_DRIVER);
			ct=DriverManager.getConnection(url, user, passwd);
			ps=ct.prepareStatement(sql);
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			rs=ps.executeQuery();
			objs = DAOUtil.relationResulList(rs);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(ct!=null)
					ct.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return objs;
	}
}
