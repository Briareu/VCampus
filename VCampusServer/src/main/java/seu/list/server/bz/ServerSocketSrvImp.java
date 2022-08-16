package virtualSchoolServer.server.bz;






import virtualSchoolClient.src.vsst.common.ClientReq;
import virtualSchoolServer.common.ClientReqType;
import virtualSchoolServer.common.IConstant;
import virtualSchoolServer.common.User;
import virtualSchoolServer.server.dao.UserDao;
import virtualSchoolServer.server.dao.UserDaoImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;
import java.util.Map;



/**
 *
 * @author 姜云骞
 * 线程服务的方法
 */
public class ServerSocketSrvImp implements ServerSocketSrv{
	private ServerSocket serverSocket;
	private boolean closed;

	/**
	 * 用于初始化服务线程
	 */
	public ServerSocketSrvImp() {
		// 在8888端口监听
		System.out.println("我是服务器,在" + IConstant.SERVER_PORT + "监听");
		serverSocket = null;
		try {
			serverSocket = new ServerSocket(IConstant.SERVER_PORT);
			closed = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * 用于服务的启动和线程的进入
	 */
	public void run() {
		try {

			UserDao iud = new UserDaoImpl();
			while (!closed && !serverSocket.isClosed())// 循环监听
			{
				Socket s = serverSocket.accept();

				// 接受客户端发来的信息
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				ClientReq clientReq = (ClientReq) ois.readObject();

				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				String clientReqType = clientReq.getType();
				User u = new User();
				u.setContent(clientReq.getContent());
				System.out.println("User " + u.getId() + " operation: "	+ clientReqType);

				if (clientReqType.equals(ClientReqType.REQ_LOGIN)) {// 数据库的验证登陆
					User user = iud.getUserByPwd(clientReq.getContent());
					if (user == null) {
						System.out.println("User " + u.getId() + " 验证失败 ");
						clientReq.setType("FAILED");
						clientReq.setLevel("0");
						oos.writeObject(clientReq);
						oos.flush();
						// 关闭连接
					} else {
						System.out.println("User " + u.getId() + " 验证成功 ");
						// 返回一个成功登陆的信息包
						clientReq.setLevel(user.getRole());
						oos.writeObject(clientReq);
						oos.flush();
						// 这里就单开一个线程。让该线程与该客户端保持通讯
						ServerSocketSrvThread ssst = new ServerSocketSrvThread(s);
						ServerClientThreadMgr.add(user.getId(), ssst);
						// 启动与该客户端通信的线程
						ssst.start();
					}
				}else if (clientReqType.equals(ClientReqType.REQ_REGISTER)) {// 用户注册
					User user = iud.getUser(u);
					if (user == null) {
						user = iud.addUser(u);
						clientReq.setLevel(u.getRole());
						oos.writeObject(clientReq);
						oos.flush();
					}else {
						System.out.println("User " + u.getId() + " 注册失败，已有此人");
						clientReq.setType("FAILED");
						clientReq.setLevel("1");//1表示重复了
						oos.writeObject(clientReq);
						oos.flush();
					}
				} else if(clientReqType.equals(ClientReqType.REQ_LOGOUT)){
					String userID = clientReq.getContent().get(0);
					User user = iud.searchUser(userID);
					if(user == null) {
						System.out.println("此人未登录");
					} else {
						ServerClientThreadMgr.remove(user.getId());
						System.out.println("User " + u.getId() + " 登出成功 ");
					}

				} else {
					throw new Exception("未知的信息类型或错误的Socket");
				}
			}
		} catch (SocketException se) {
			if (serverSocket.isClosed()) {
				System.out.println(" 服务器关闭 ");
			} else {
				se.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 用于线程的启动
	 */
	@Override
	public void start() {
		new Thread(this).start();
	}

	@Override
	public void close() {
		if (serverSocket != null)
			if (serverSocket != null)
				try {
					// 向当前所有线程发送离线通知
					Map<String, ServerSocketSrvThread> sctPool = ServerClientThreadMgr.getPool();
					Iterator<String> key = sctPool.keySet().iterator();
					while (key.hasNext()) {
						sctPool.get(key.next()).close();//每个线程都close掉，循环无法继续，则全部不提供服务
					}
					ServerClientThreadMgr.clear();
					// 关闭服务器Socket
					closed = true;
					serverSocket.close();
					System.out.println("服务器关闭！");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	/**
	 * 用于判断线程是否关闭
	 */
	@Override
	public boolean isClosed() {
		return closed;
	}
}
