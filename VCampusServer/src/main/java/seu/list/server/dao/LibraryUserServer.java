package seu.list.server.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.net.httpserver.Authenticator.Result;

import seu.list.common.Book;
import seu.list.server.db.Library_DbAccess;


public class LibraryUserServer extends Library_DbAccess {
	static Connection con = null;
	Statement s = null;
	ResultSet rs=null;
	
	private String id;
	private String passwd;
	private static ArrayList<Book> bookList;
	
	public LibraryUserServer(String jid, String jpasswd) { 
		this.id=jid;
		this.passwd=jpasswd;
		bookList=new ArrayList<Book>();
		
	}
	
	public LibraryUserServer() {
		bookList=new ArrayList<Book>();
	}
	
	public void setBookList(ArrayList<Book> tbooklist) {
		bookList=tbooklist;
	}
	
	public static ArrayList<Book> createList() {
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
//			closeConnection(con, rs, s);
		}
		return bookList;
	}

	public Integer Login() {  //return 0-正确；1-一卡通号错误；2-密码错误
		return 0;
		
	}

	//书名或书号查找
	public static ArrayList<Book> FindBook(String fbookid) {
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
//			closeConnection(con, rs, s);
		}
		return resbook;
	}
	
	//书号查找（唯一）
	public static Book FindBookID(String fbookid) {
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
//			closeConnection(con, rs, s);
		}
		return resbook;
	}	
	
	//学生
	public static void LendBook(String bookid) { //用书号查找（唯一）
		try {
			Book targetbook=FindBookID(bookid);
		    int result=0;			
		    if(targetbook.getStock()-1>=0) {
		    	targetbook.setStock(targetbook.getStock()-1);
		    	result = s.executeUpdate("update test set Stock='"+targetbook.getStock()+"' where ID='"+targetbook.getId()+"'");	
		    }
		    else {
	    	//不可借抛出异常
		    }
		    if(targetbook.getStock()-1==0) {
		    	result = s.executeUpdate("update test set State=0 where ID='"+targetbook.getId()+"'");
		    	targetbook.setState(false);	
		    }
		    
		}catch (Exception e) {
				e.printStackTrace();
			} finally {
//				closeConnection(con, rs, s);
			}
	}
	
	//学生
	public static void ReturnBook(String bookid) {
		try {
			Book targetbook=FindBookID(bookid); //用书号查找（唯一）
		    int result=0;
		    targetbook.setStock(targetbook.getStock()+1);		
		
		    targetbook.setStock(targetbook.getStock()-1);
    	    result = s.executeUpdate("update test set Stock='"+targetbook.getStock()+"' where ID='"+targetbook.getId()+"'");	
		    if(targetbook.getState()==false) {
		    	targetbook.setState(true);
			    result = s.executeUpdate("update test set State=1 where ID='"+targetbook.getId()+"'");
		}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
//			closeConnection(con, rs, s);
		}

	}
	
	//管理员
	public static void AddBook(Book tbook) { 
		try {
			int result=0;
			bookList.add(tbook);		
			result=s.executeUpdate("insert into text values('"+tbook.getName()+"','"+tbook.getId()+"','"+tbook.getAuthor()+
					"','"+tbook.getPress()+"','"+tbook.getStock()+"','"+tbook.getState()+"')");
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
//			closeConnection(con, rs, s);
		}

	}
	
	//管理员
	public static void DeleteBook(String bookid) { //用书号查找（唯一）
		try {
			int result=0;
			Book tbook=FindBookID(bookid);
			bookList.remove(tbook);
			result=s.executeUpdate("delete from text where ID='"+bookid+"'");
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
//			closeConnection(con, rs, s);
		}
	}
	
	//管理员
	public static void ModifyBook(String bookid,String attr,String modattr) { //用书号查找（唯一）
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
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
//			closeConnection(con, rs, s);
		}
		
	}
	
	
	public static void main(String[] args) {
		LibraryUserServer u=new LibraryUserServer();
		u.LendBook("PLM");
		Integer a=1;
	}
}
