package seu.list.server.dao;

import seu.list.common.Message;
import seu.list.common.MessageType;
import seu.list.common.User;
import seu.list.server.bz.ServerClientThreadMgr;
import seu.list.server.db.SqlHelperImp;

import java.util.List;
import java.util.Vector;



public class UserDaoImpl implements UserDao {
		private Message mesFromClient;
		private Message mesToClient=new Message();
		private String id;

	public Message getMesFromClient() {
		return mesFromClient;
	}

	public void setMesFromClient(Message mesFromClient) {
		this.mesFromClient = mesFromClient;
	}

	public Message getMesToClient() {
		return mesToClient;
	}

	public void setMesToClient(Message mesToClient) {
		this.mesToClient = mesToClient;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserDaoImpl(Message mesFromClient, String id){this.mesFromClient=mesFromClient;this.id=id;}
		public void excute(){
			User u=new User();
			u.setContent(mesFromClient.getContent());
			switch (this.mesFromClient.getMessageType()){
				case MessageType.REQ_LOGIN:
				{
					User user=this.getUserByPwd(this.mesFromClient.getContent());
					if(user==null){
						System.out.println("User"+ u.getId()+"验证失败");
						mesToClient.setMessageType("FAILED");
						mesToClient.setUserType(2);
					}else{
						System.out.println("User " + u.getId() + " 验证成功 ");
						mesToClient.setUserType(Integer.valueOf(user.getRole()));
					}
					break;
				}
				case MessageType.REQ_REGISTER:
				{
					User user=this.getUser(u);
					if(user==null){
						user=this.addUser(u);
						mesToClient.setUserType(Integer.valueOf(u.getRole()));
					}else {
						System.out.println("User"+u.getId()+"注册失败，已有此人");
						mesToClient.setMessageType("FAILED");
						mesToClient.setUserType(3);
					}
					break;
				}
				case MessageType.REQ_LOGOUT:
				{
					String userID= mesFromClient.getContent().get(0);
					User user=this.searchUser(userID);
					if(user==null){
						System.out.println("此人不存在");
					}else{
						ServerClientThreadMgr.remove(this.id);
						System.out.println("User " + u.getId() + " 登出成功 ");
					}
					break;
				}
				default:break;
			}
		}

		@Override
		public User getUser(User user) {
			// TODO 自动生成的方法存根
			String sql = "select * from tb_User where uID= ? and uName=?";
			String[] paras = new String[2];
			paras[0] = user.getId();
			paras[1] = user.getName();
			List<User> users = new SqlHelperImp().sqlUserQuery(sql, paras);
			if (users != null && users.size() > 0) {
				return users.get(0);
			} else
				return null;
		}

		@Override
		public User addUser(User user) {
			// TODO 自动生成的方法存根
			String sql = "insert into tb_User(uID, uName, uAge, uSex,uGrade, uMajor,uPwd,uRole,uMoney) values (?,?,?,?,?,?,?,?,?)";
			String[] paras = new String[9];
			paras[0] = user.getId();
			paras[1] = user.getName();
			paras[2] = user.getAge();
			paras[3] = user.getSex();
			paras[4] = user.getGrade();
			paras[5] = user.getMajor();
			paras[6] = user.getPwd();
			paras[7] = user.getRole();
			paras[8] = user.getMoney();
			new SqlHelperImp().sqlUpdate(sql, paras);
			return searchUser(user.getId());
		}

		@Override
		public boolean delUser(String userID) {
			if(this.searchUser(userID).getRole()=="老师"){
				return false;
			}else {
				String sql = "delete from tb_User where uID = ?";
				return new SqlHelperImp().sqlUpdate(sql , new String[] {userID});
			}
		}

		@Override
		public User searchUser(String id) {
			// TODO 自动生成的方法存根
			String sql = "select * from tb_User where uID=?";
			String[] paras = new String[1];
			paras[0] = id;
			List<User> users = new SqlHelperImp().sqlUserQuery(sql, paras);
			if (users != null && users.size() > 0) {
				return users.get(0);
			} else
				return null;
		}

		@Override
		public List<User> getAllUsers() {
			// TODO 自动生成的方法存根
			String sql = "select * from tb_User";
			return new SqlHelperImp().sqlUserQuery(sql, new String[] {});
		}

		@Override
		public User getUserByPwd(Vector<String> content) {
			String sql = "select * from tb_User where uID= ? and uPwd=?";
			String[] paras = new String[2];
			paras[0] = content.get(0);
			paras[1] = content.get(6);
			List<User> users = new SqlHelperImp().sqlUserQuery(sql, paras);
			if (users != null && users.size() > 0) {
				return users.get(0);
			} else
				return null;
		}

	}
