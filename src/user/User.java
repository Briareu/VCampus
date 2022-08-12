package user;

public abstract class User {
protected String userName;
protected String password;
protected int uid;
public User() {

}
protected String getUserName() {
	return userName;
}
protected void setUserName(String userName) {
	this.userName = userName;
}

protected String getPassword() {
	return password;
}
protected void setPassword(String password) {
	this.password = password;
}
protected int getUid() {
	return uid;
}
protected void setUid(int uid) {
	this.uid = uid;
}
public void searchinshop() {
	
}//查找
}


