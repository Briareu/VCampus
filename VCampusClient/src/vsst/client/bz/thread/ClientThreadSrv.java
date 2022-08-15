package virtualSchoolClient.src.vsst.client.bz.thread;

import common.IConstant;
import virtualSchoolClient.src.vsst.client.view.ClientLibraryModifyFrame;
import virtualSchoolClient.src.vsst.client.view.ClientShopManagerFrame;
import virtualSchoolClient.src.vsst.client.view.ClientStudentFrame;
import virtualSchoolClient.src.vsst.client.view.ClientTeacherFrame;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;



/**
 * 
 * @author 姜云骞
 * 客户端的线程服务
 */
public class ClientThreadSrv extends Thread {

	private static final String SERVER_ADDRESS = IConstant.SERVER_ADDRESS;
	private static final int SERVER_PORT = IConstant.SERVER_PORT;
	private Socket socket = null;
	private boolean isOffline;
	private String userID;
	private int sign;
	/**
	 * 客户端的线程初始化
	 * @param userID 用户的ID
	 * @param socket 用于通信的socket
	 * @param sign 用户的操作权限等级
	 * @throws UnknownHostException 抛出Socket未初始化的异常
	 * @throws IOException 抛出IO的异常
	 */
	public ClientThreadSrv(String userID, Socket socket, int sign) throws UnknownHostException, IOException {
			this.socket = socket;
			ClientThreadSrvMgr.add(userID, this);
			this.userID = userID;
			isOffline = false;
			this.sign = sign;
			// this.start(); // 应在登录验证成功后启动		
	}

	@Override
	/**
	 * 用于线程服务的启动
	 */
	public void run() {
	
		if(sign == 2) {
			ClientStudentFrame csf = new ClientStudentFrame(userID);//进入操作界面
			csf.setSocket(socket);
		} 
		else if(sign == 3) 
		{	
			try {
				ClientTeacherFrame ctf = new ClientTeacherFrame(userID,socket);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		else if(sign == 4) 
		{//改动
			ClientShopManagerFrame csmf = null;
			try {
				csmf = new ClientShopManagerFrame(socket);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//后续添加
			csmf.setSocket(socket);
		} 
		else if(sign == 5) {
			try {
				ClientLibraryModifyFrame clmf = new ClientLibraryModifyFrame(socket);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setUser(String userID) {
		this.userID = userID;
	}

	public void dispose() throws IOException {
		isOffline = true;
		this.socket.close();		
	}
	
	public void start() {
		new Thread(this).start();
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}
}
