package VCampusClient.src.main.java.seu.list.client.bz;




import main.java.seu.list.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;





public class ClientConServer{

	/**
	 * 用于登录验证和注册
	 */
	private User user;
	public boolean IsConnect;
	private static Socket s;
	public int sign;
	
	public ClientConServer()
	{
		IsConnect = true;
		try
		{
			s = new Socket("127.0.0.1",8888);
		}
		catch(Exception e)
		{
			IsConnect = false;
			e.printStackTrace();
		}
	}
	
	
	public void sendCheck(String ID,String pwd) throws ClassNotFoundException
	{
		
		sign = 0;
		setUser(new User());
		getUser().setId(ID);
		getUser().setPwd(pwd);
		
        ClientReq Req = new ClientReq();
        Req.setContent(getUser().getContent());
        Req.setType("REQ_LOGIN");
		try {
			ObjectOutputStream oos = new ObjectOutputStream(getSocket().getOutputStream());
			oos.writeObject(Req);
			oos.flush();//标识
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			ObjectInputStream ois = new ObjectInputStream(getSocket().getInputStream());
			Req = (ClientReq) ois.readObject();
			sign = Integer.valueOf(Req.getLevel());
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendRegister(String ID,String Age,String Grade,String Major, String Money,String Name,String Pwd,String Role,String Sex)
	{
		setUser(new User());
		getUser().setId(ID);
		getUser().setAge(Age);
		getUser().setGrade(Grade);
		getUser().setMajor(Major);
		getUser().setMoney(Money);
		getUser().setName(Name);
		getUser().setPwd(Pwd);
		getUser().setRole(Role);
		getUser().setSex(Sex);
		
		ClientReq Req = new ClientReq();
		Req.setContent(getUser().getContent());
		/*
		for(int i=0;i<getUser().getContent().size();i++) {
			System.out.println(getUser().getContent().get(i));
		}*/
		//用来测试所传信息
		
		Req.setType("REQ_REGISTER");
		try {
			ObjectOutputStream oos = new ObjectOutputStream(getSocket().getOutputStream());
			oos.writeObject(Req);
			oos.flush();
			
			ObjectInputStream dis = new ObjectInputStream(getSocket().getInputStream());
			//sign = dis.readInt();//从输入流中读取一个int型数据
			Req = (ClientReq) dis.readObject();
			sign = Integer.valueOf(Req.getLevel());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public static Socket getSocket() {
		return s;
	}


	public static void setSocket(Socket s) {
		ClientConServer.s = s;
	}
}
