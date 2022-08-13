package VCampusServer;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;

public class LibraryUserServer extends Library_DbAccess {
	Connection con = null;
	Statement s = null;
	ResultSet rs=null;
	
	private String id;
	private String passwd;
	private ArrayList<Book> bookList;
	
	public LibraryUserServer(String jid, String jpasswd) { 
		this.id=jid;
		this.passwd=jpasswd;
		bookList=new ArrayList<Book>();
		
		createList();
	}
	
	public LibraryUserServer() {
		bookList=new ArrayList<Book>();
		createList();
	}
	
	public void createList() {
		try {
			con=getConnection();
			s = con.createStatement();// 创建SQL语句对象
			rs=s.executeQuery("select * from BookList");	// 查询书籍信息
						
			//把数据库中的数据读入bookList
			while(rs.next()) {
				Book tempBook=new Book();
				tempBook.setName(rs.getString("书名"));
				tempBook.setId(rs.getString("书号"));
				tempBook.setAuthor(rs.getString("作者"));
				tempBook.setPress(rs.getString("出版社"));
				tempBook.setStock(rs.getInt("库存"));
				tempBook.setState((rs.getInt("状态")==0)?false:true);
				
				bookList.add(tempBook);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, rs, s);
		}
	}

	public Integer Login() {  //return 0-正确；1-一卡通号错误；2-密码错误
		return 0;
		
	}

	//书名或书号查找
	public ArrayList<Book> FindBook(String fbookid) {
		try {
			ArrayList<Book> resbook=new ArrayList<Book>();
			for(int i=0;i<bookList.size();i++) {
				Book tbook=bookList.get(i);
				if(tbook.getName().equals(fbookid)||tbook.getId().equals(fbookid))
					resbook.add(tbook);				
			}
			return resbook;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, rs, s);
		}
		
		return null;
	}
	
	//书号查找（唯一）
	public Book FindBookID(String fbookid) {
		try {
			Book resbook=new Book();
			for(int i=0;i<bookList.size();i++) {
				Book tbook=bookList.get(i);
				if(tbook.getName().equals(fbookid)||tbook.getId().equals(fbookid)) {
					resbook=tbook;	
					break;
				}
			}
			return resbook;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, rs, s);
		}
		
		return null;
	}	
	
	//学生
	public void LendBook(String bookid) { //用书号查找（唯一）
		Book targetbook=FindBookID(bookid);
		if(targetbook.getStock()-1>=0)
			targetbook.setStock(targetbook.getStock()-1);
		else {
			
		}
		if(targetbook.getStock()-1==0)
			targetbook.setState(false);
	}
	//学生
	public void ReturnBook(String bookid) {
		Book targetbook=FindBookID(bookid); //用书号查找（唯一）
		if(targetbook.getState()==false)
			targetbook.setState(true);
		targetbook.setStock(targetbook.getStock()+1);
	}
	
	//管理员
	public void AddBook(Book tbook) { 
		bookList.add(tbook);
	}
	
	//管理员
	public void DeleteBook(String bookid) { //用书号查找（唯一）
		Book tbook=FindBookID(bookid);
		bookList.remove(tbook);
	}
	
	//管理员
	public void ModifyBook(String bookid) { //用书号查找（唯一）
		Book tbook=FindBookID(bookid);
	}
	
	//把数据保存到数据库
	public void SaveBookList() {
		String sql="insert into test('书名','书号','作者','出版社'+'库存'+'状态') " +
				"values('"+stuName+"','"+stuCode+"','"+stuSex+"','"+stuAge+"')"; 
	}
	
	public static void main(String[] args) {
		LibraryUserServer u=new LibraryUserServer();
		ArrayList<Book> res=u.FindBook("PLM");
		Integer a=1;
	}
}
