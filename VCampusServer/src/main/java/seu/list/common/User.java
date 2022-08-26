package seu.list.common;






import seu.list.server.dao.CourseDaoImp;
import seu.list.server.db.SqlHelperImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class User implements java.io.Serializable {
	private static final long serialVersionUID = 2342342342342342342L;//为了让ServerThread能够写回它
	private String id;//0
	private String name;//1
	private String age;//2
	private String sex;//3
	private String major;//4
	private String grade;//5
	private String pwd;//6
	private String role;//7
	private String money;//8
	private Vector<String> userContents;


	public void print() {
		System.out.print(id+"\n"+name+"\n"+age+"\n"+sex+"\n"+major+"\n"+grade+"\n"+pwd+"\n"+role+"\n"+money+"\n"+"\n");
	}

	public void setContent(Vector<String> content) {
		id = content.get(0);
		name = content.get(1);
		age = content.get(2);
		sex = content.get(3);
		major = content.get(4);
		grade = content.get(5);
		pwd = content.get(6);
		role = content.get(7);
		money = content.get(8);
	};
	/*public enum GRADE {
		大一,大二,大三,大四;
	}*/
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public Vector<String> getContent() {
		userContents = new Vector<String>();
		userContents.add(id);
		userContents.add(name);
		userContents.add(age);
		userContents.add(sex);
		userContents.add(major);
		userContents.add(grade);
		userContents.add(pwd);
		userContents.add(role);
		userContents.add(money);
		return userContents;
	}
	public List<Course> getCourses()
	{
		String sql="select * from tb_Stc where uID = ?";
		List<String> courseIDset=new SqlHelperImp().sqlRelationQuery(sql, new String[] {id});
		List<Course> cList=new ArrayList<Course>();
		for(String cID:courseIDset)
		{
			cList.add(new CourseDaoImp().searchCourseByID(cID));
		}
		return cList;
	}


	@Override
	public String toString() {
		return "{" +
				" id='" + getId() + "'" +
				", name='" + getName() + "'" +
				", age='" + getAge() + "'" +
				", sex='" + getSex() + "'" +
				", major='" + getMajor() + "'" +
				", grade='" + getGrade() + "'" +
				", pwd='" + getPwd() + "'" +
				", role='" + getRole() + "'" +
				", money='" + getMoney() + "'" +
				"}";
	}
}
