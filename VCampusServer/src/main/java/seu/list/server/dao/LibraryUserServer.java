package seu.list.server.dao;

import java.sql.Connection;
import java.sql.ResultSet;
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
	
	private static ArrayList<Book> bookList;
	
	private Message mesFromClient; //从客户端发来的数据
	private Message mesToClient;  //送回给客户端的信息
	
	public LibraryUserServer(Message mesfromclient) { 
		bookList=new ArrayList<Book>();
		this.mesFromClient=mesfromclient;
	}
	
	public void execute() {
		switch(this.mesFromClient.getMessageType())
		{
		case MessageType.LibraryBookGetAll:
			this.createList();
			//this.dataToClient = "lllll";
			break;
		case MessageType.LibraryBookAdd:
			this.AddBook((Book) this.mesFromClient.getData());
			break;
		case MessageType.LibraryBookDelete:
			this.DeleteBook(this.mesFromClient.getData().toString());
			break;
		case MessageType.LibraryBookFind:
			this.mesToClient.setData(this.FindBook(this.mesFromClient.getData().toString()));
			break;
		case MessageType.LibraryBookLend:
			this.LendBook(this.mesFromClient.getData().toString());
			break;
		case MessageType.LibraryBookReturn:
			this.ReturnBook(this.mesFromClient.getData().toString());
			break;
		case MessageType.LibraryBookUpdate:
			this.ModifyBook((String[]) this.mesFromClient.getData());
			break;
		default:
			break;
		}
	}
	
	public Message getMesToClient() {
		return this.mesToClient;
	}

	public void setBookList(ArrayList<Book> tbooklist) {
		bookList=tbooklist;
	}

	public ArrayList<Book> createList() {
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
	
	//书号查找（唯一）
	public Book FindBookID(String fbookid) {
		Book resbook=new Book();		
		try {
			for(int i=0;i<bookList.size();i++) {
				Book tbook=bookList.get(i);
				if(tbook.getName().equals(fbookid)||tbook.getId().equals(fbookid)) {
					resbook=tbook;
					break;
				}
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
		int res=0;	

		try {
			int result1=0,result2=0,result3=0;
			Book targetbook=FindBookID(bookid);

		    String[] arr=new String[5];		    		
			
		    if(targetbook.getStock()-1>=0) {
		    	targetbook.setStock(targetbook.getStock()-1);
		    	result1 = s.executeUpdate("update BookList set Stock='"+targetbook.getStock()+"' where ID='"+targetbook.getId()+"'");			    	
		    }
		    else {
	    	//不可借抛出异常
		    }
		    if(targetbook.getStock()-1==0) {

		    	result2 = s.executeUpdate("update BookList set State=0 where ID='"+targetbook.getId()+"'");
		    	targetbook.setState(false);	
		    }
		    else
		    	result2=1;
			if(result1>0&&result2>0) {
				res=result1;
				System.out.println("Lend completion\t");
			}
		    
		}catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeConnection(con, rs, s);
			}
		return res;
	}
	
	//学生
	public int ReturnBook(String bookid) {
		int res=0;
		try {
			int result1=0,result2=0;
			Book targetbook=FindBookID(bookid); //用书号查找（唯一）
		    
		    targetbook.setStock(targetbook.getStock()+1);		
		    targetbook.setStock(targetbook.getStock()-1);
    	    result1 = s.executeUpdate("update BookList set Stock='"+targetbook.getStock()+"' where ID='"+targetbook.getId()+"'");	

		    if(targetbook.getState()==false) {
		    	targetbook.setState(true);
			    result1 = s.executeUpdate("update BookList set State=1 where ID='"+targetbook.getId()+"'");
		    }
		    if(result1>0&&result2>0) {
				res=result1;
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
	public int AddBook(Book tbook) { 
		int res=0;

		try {
			int result=0;
			bookList.add(tbook);
			result=s.executeUpdate("insert into text values('"+tbook.getName()+"','"+tbook.getId()+"','"+tbook.getAuthor()+
					"','"+tbook.getPress()+"','"+tbook.getStock()+"','"+tbook.getState()+"')");
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
		int res=0;

		try {
			int result=0;
			Book tbook=FindBookID(bookid);
			bookList.remove(tbook);
			result=s.executeUpdate("delete from text where ID='"+bookid+"'");
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
	public void ModifyBook(String[] arr) { //用书号查找（唯一）
		String bookid=arr[0];
		String attr=arr[1];
		String modattr=arr[2];

		try {
			int result=0;
			Book tbook=FindBookID(bookid);
			result=s.executeUpdate("update test set "+attr+"='"+modattr+"' where ID='"+bookid+"'");
			int ind=bookList.indexOf(tbook);
			switch (modattr) {
			case "Name":
				bookList.get(ind).setName(modattr);
				break;
			case "ID":
				bookList.get(ind).setId(modattr);
				break;
			case "Author":
				bookList.get(ind).setAuthor(modattr);
				break;
			case "Press":
				bookList.get(ind).setPress(modattr);
				break;
			case "Stock":
				bookList.get(ind).setStock(Integer.parseInt(modattr));
				break;
			}
			if(bookList.get(ind).getStock()==0)
				bookList.get(ind).setState(false);
			else
				bookList.get(ind).setState(true);
			if(result>0) 
				System.out.println("update completion\t");
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, rs, s);
		}

	}

	
	/*	
	public static void main(String[] args) {
		LibraryUserServer u=new LibraryUserServer();
		u.LendBook("PLM");
		Integer a=1;
	}
	*/
}
