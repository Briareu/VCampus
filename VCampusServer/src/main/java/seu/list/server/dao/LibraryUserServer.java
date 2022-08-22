package seu.list.server.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.sun.net.httpserver.Authenticator.Result;

import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.Book;
import seu.list.server.db.Library_DbAccess;


public class LibraryUserServer extends Library_DbAccess {
	Connection con = null;
	Statement s = null;
	ResultSet rs=null,rsl=null;
	
	private Message mesFromClient; //从客户端发来的数据
	private Message mesToClient;  //送回给客户端的信息
	
	public LibraryUserServer(Message mesfromclient) { 
		this.mesFromClient=new Message();
		this.mesFromClient=mesfromclient;
	}
	
/*	public LibraryUserServer() { 
		bookList=new ArrayList<Book>();
	}
	*/
	
	public void execute() {
		mesToClient=new Message();
		switch(this.mesFromClient.getMessageType())
		{
		case MessageType.LibraryBookGetAll:
			this.mesToClient.setData(this.createList());
			break;
		case MessageType.LibraryBookAdd:
			this.mesToClient.setData(this.AddBook((String[]) this.mesFromClient.getData()));
			break;
		case MessageType.LibraryBookDelete:
			this.mesToClient.setData(this.DeleteBook(this.mesFromClient.getData().toString()));
			break;
		case MessageType.LibraryBookFind:
			this.mesToClient.setData(this.FindBook(this.mesFromClient.getData().toString()));
			break;
		case MessageType.LibraryBookLend:
			this.mesToClient.setData(this.LendBook(this.mesFromClient.getData().toString()));
			break;
		case MessageType.LibraryBookReturn:
			this.mesToClient.setData(this.ReturnBook(this.mesFromClient.getData().toString()));
			break;
		case MessageType.LibraryBookUpdate:
			this.mesToClient.setData(this.ModifyBook((ArrayList<String>) this.mesFromClient.getData()));
			break;
		default:
			break;
		}
	}

	
	public Message getMesToClient() {
		return this.mesToClient;
	}
	
	public ArrayList<Book> createList() {
		ArrayList<Book> bookList=new ArrayList<Book>();
		try {
			con=getConnection();
			s = con.createStatement();// 创建SQL语句对象
			rs=s.executeQuery("select * from BookList");	// 查询书籍信息
						
			//把数据库中的数据读入bookList
			while(rs.next()) {
				Book tempBook=new Book();
				tempBook.setName(rs.getString("Name"));
				tempBook.setId(rs.getString("ID"));
				tempBook.setAuthor(rs.getString("Author"));
				tempBook.setPress(rs.getString("Press"));
				tempBook.setStock(rs.getInt("Stock"));
				tempBook.setState((rs.getInt("State")==0)?false:true);
				
				bookList.add(tempBook);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, rs, s);
		}
		System.out.println("create completion\t");
		return bookList;
	}

	public Integer Login() {  //return 0-正确；1-一卡通号错误；2-密码错误
		return 0;
		
	}

	//书名或书号查找
	public ArrayList<Book> FindBook(String fbookid) {
		ArrayList<Book> bookList=new ArrayList<Book>();
		bookList=createList();
		ArrayList<Book> resbook=new ArrayList<Book>();		
		try {
			for(int i=0;i<bookList.size();i++) {
				Book tbook=bookList.get(i);
				if(tbook.getName().equals(fbookid)||tbook.getId().equals(fbookid))
					resbook.add(tbook);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, rs, s);
		}
		return resbook;
	}
	
	//学生
	public int LendBook(String bookid) { //用书号查找（唯一）
		//return 0:书号不存在  return -1:库存为0不可借
		
		int res=0;	
		try {
			con=getConnection();
			s = con.createStatement();// 创建SQL语句对象	
			
			int result=0;
			ResultSet rsr=s.executeQuery("select * from BookList where ID='"+bookid+"'");
			Integer stock=0;
			Integer state=0;
			if(rsr.next()) {
				stock=rsr.getInt("Stock");
				state=rsr.getInt("State");
			}else
				return 0;	    		
			
		    if((stock-1)>=0) {
		    	stock=stock-1;
		    	if(stock==0)
		    		state=0;
		    	else
		    		state=1;	 
		    	result = s.executeUpdate("update BookList set Stock='"+stock+"' where ID='"+bookid+"'");	
		    	result = s.executeUpdate("update BookList set State='"+state+"' where ID='"+bookid+"'");
		    }
		    else {
		    	return -1;
		    }
			if(result>0) {
				res=result;
				System.out.println("Lend completion\t");
			}
		    
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, rs, s);
		}
		return res;
	}
	
	//学生
	public int ReturnBook(String bookid) {
		//return 0:书号不存在
		int res=0;
		try {
			con=getConnection();
			s = con.createStatement();// 创建SQL语句对象	
			
			int result=0;
			ResultSet rsr=s.executeQuery("select * from BookList where ID='"+bookid+"'");
			Integer stock=0;
			Integer state=0;
			if(rsr.next()) {
				stock=rsr.getInt("Stock");
				state=rsr.getInt("State");
			}else
				return 0;
			stock=stock+1;
			
    	    result = s.executeUpdate("update BookList set Stock='"+stock+"' where ID='"+bookid+"'");	
		    if(state==0) {
			    result = s.executeUpdate("update BookList set State=1 where ID='"+bookid+"'");
		    }
		    if(result>0) {
				res=result;
				System.out.println("Return completion\t");
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, rs, s);
		}
		return res;
	}
	
	//管理员
	public int AddBook(String[] arr) {
		//return 0:书号已存在
		int bstate=0;
		if(Integer.valueOf(arr[4])==0)
			bstate=0;
		else
			bstate=1;
		
		int res=0;
		
		try {
			int result=0;
			con=getConnection();
			s = con.createStatement();// 创建SQL语句对象		
			
			ResultSet rsr=s.executeQuery("select * from BookList where ID='"+arr[1]+"'");
			if(rsr.next()) 
				return 0;
			
			result=s.executeUpdate("insert into BookList values('"+arr[0]+"','"+arr[1]+"','"+arr[2]+
					"','"+arr[3]+"','"+arr[4]+"','"+bstate+"')");
			
			if(result>0) {
				res=result;
				System.out.println("Add completion\t");
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, rs, s);
		}
		return res;
	}
	
	//管理员
	public int DeleteBook(String bookid) { //用书号查找（唯一）
		//return 0:书号不存在
		int res=0;
		try {
			int result=0;
			con=getConnection();
			s = con.createStatement();// 创建SQL语句对象	
			
			ResultSet rsr=s.executeQuery("select * from BookList where ID='"+bookid+"'");
			if(!rsr.next()) 
				return 0;
			
			result=s.executeUpdate("delete from BookList where ID='"+bookid+"'");
			if(result>0) {
				res=result;
				System.out.println("Delete completion\t");
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, rs, s);
		}
		return res;
	}
	
	//管理员
	public int ModifyBook(ArrayList<String> arr) { //用书号查找（唯一）
		//return 0:书号不存在 
		
		String bookid=arr.get(0);
		String attr=arr.get(1);
		String modattr=arr.get(2);
		int result=0;
		try {
			con=getConnection();
			s = con.createStatement();// 创建SQL语句对象	
			
			//检查书号是否存在
			ResultSet res=s.executeQuery("select count(*)  from BookList where ID='"+bookid+"'");
			if(res != null) {
				result=s.executeUpdate("update BookList set "+attr+"='"+modattr+"' where ID='"+bookid+"'");
				if(attr.equals("Stock")) {
					int ms=Integer.valueOf(modattr);
					if(ms==0)
						result=s.executeUpdate("update BookList set State=0 where ID='"+bookid+"'");
					else
						result=s.executeUpdate("update BookList set State=1 where ID='"+bookid+"'");
				}
				
				if(result>0) 
					System.out.println("update completion\t");
			}
			else {
				return 0;
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, rs, s);
		}
		return result;
	}
	
/*
	public static void main(String[] args) {
		LibraryUserServer u=new LibraryUserServer();
		ArrayList<Book> bl=u.createList();
		Integer a=1;
	}
*/
}
