package seu.list.common;

/**
 * @author 王映方
 * @version jdk1.8.0
 */

public class Book implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String author;
	private Integer stock;  //库存
	private String press;  //出版社：
	private Boolean state;  //状态 0-不可借阅；1-可借阅

	/**
	 * 构造函数
	 * @param jid 书号
	 * @param jname 书名
	 * @param jauthor 作者
	 * @param press 出版社
	 * @param jstock 库存
	 */
	public Book(String jid,String jname,String jauthor,String press,Integer jstock) {

		this.setId(jid);
		this.setName(jname);
		this.setAuthor(jauthor);
		this.setStock(jstock);
		this.setPress(press);

		if(jstock<0)
			throw new RuntimeException();

		if(jstock==0)
			this.setState(false);
		else
			this.setState(true);
	}

	/**
	 * 无参构造器
	 */
	public Book() {}

	/**
	 * 
	 * @return 书号
	 */
	public String getId() {
		return id;
	}
	/**
	 * 
	 * @param id 书号
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return 书名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @param name 书名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return 作者
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * 
	 * @param author 作者
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * 
	 * @return 库存
	 */
	public Integer getStock() {
		return stock;
	}
	
	/**
	 * 
	 * @param stock 库存
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/**
	 * 
	 * @return 出版社
	 */
	public String getPress() {
		return press;
	}

	/**
	 * 
	 * @param press 出版社
	 */
	public void setPress(String press) {
		this.press = press;
	}

	/**
	 * 
	 * @return 借阅状态（0-不可借阅；1-可借阅）
	 */
	public Boolean getState() {
		return state;
	}
	/**
	 * 
	 * @param st 借阅状态（0-不可借阅；1-可借阅）
	 */
	public void setState(boolean st) {
		this.state = st;
	}

}
