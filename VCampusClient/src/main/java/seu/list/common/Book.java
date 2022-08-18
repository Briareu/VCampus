package seu.list.common;

public class Book implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String name;
	private String author;
	private Integer stock;  //库存
	private String press;  //出版社：
	private Boolean state;  //状态 0-不可借阅；1-可借阅

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

	public Book() {}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}

	public Boolean getState() {
		return state;
	}
	public void setState(boolean b) {
		this.state = b;
	}

}
