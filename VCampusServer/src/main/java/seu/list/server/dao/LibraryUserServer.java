package seu.list.server.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.net.httpserver.Authenticator.Result;

import seu.list.common.Book;
import seu.list.server.db.Library_DbAccess;


public class LibraryUserServer extends Library_DbAccess {
	//static Connection con = null;
	Connection con = null;
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
	
	public ArrayList<Book> createList() {
		try {
			con=getConnection();
			s = con.createStatement();// 鍒涘缓SQL璇彞瀵硅薄
			rs=s.executeQuery("select * from BookList");	// 鏌ヨ涔︾睄淇℃伅
						
			//鎶婃暟鎹簱涓殑鏁版嵁璇诲叆bookList
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

	public Integer Login() {  //return 0-姝ｇ‘锛�1-涓�鍗￠�氬彿閿欒锛�2-瀵嗙爜閿欒
		return 0;
		
	}

	//涔﹀悕鎴栦功鍙锋煡鎵�
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
	
	//涔﹀彿鏌ユ壘锛堝敮涓�锛�
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
	
	//瀛︾敓
	public static void LendBook(String bookid) { //鐢ㄤ功鍙锋煡鎵撅紙鍞竴锛�
		try {
			Book targetbook=FindBookID(bookid);
		    int result=0;			
		    if(targetbook.getStock()-1>=0) {
		    	targetbook.setStock(targetbook.getStock()-1);
		    	result = s.executeUpdate("update test set Stock='"+targetbook.getStock()+"' where ID='"+targetbook.getId()+"'");	
		    }
		    else {
	    	//涓嶅彲鍊熸姏鍑哄紓甯�
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
	
	//瀛︾敓
	public void ReturnBook(String bookid) {
		try {
			Book targetbook=FindBookID(bookid); //鐢ㄤ功鍙锋煡鎵撅紙鍞竴锛�
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
	
	//绠＄悊鍛�
	public void AddBook(Book tbook) { 
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
	
	//绠＄悊鍛�
	public void DeleteBook(String bookid) { //鐢ㄤ功鍙锋煡鎵撅紙鍞竴锛�
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
	
	//绠＄悊鍛�
	public void ModifyBook(String bookid,String attr,String modattr) { //鐢ㄤ功鍙锋煡鎵撅紙鍞竴锛�
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
	
	/*
	public static void main(String[] args) {
		LibraryUserServer u=new LibraryUserServer();
		u.LendBook("PLM");
		Integer a=1;
	}
	*/
}
