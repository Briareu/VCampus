package seu.list.server.bz;

import java.util.*;

import seu.list.common.*;

/**
 * 类{@code ServerClientThreadMgr}用于管理服务器上的客户端线程池 <br>
 * 静态数据成员: {@code clientThreadPool}, 类型: {@code Map}, 客户端线程池 <br>
 * 支持对线程池的增删改查
 * @author 吴慕陶
 * @version 1.0
 * @see java.util.Map
 */
public class ServerClientThreadMgr {
	private static Map<String, ServerSocketThread> clientThreadPool = new LinkedHashMap<String, ServerSocketThread>();

	/**
	 * 添加一个新的客户端线程到线程池中，不可重复，重复会覆盖原有线程
	 * @param id 需要添加的客户端线程ID
	 * @param clientThreadSrv 需要添加的客户端线程
	 * @return 如果id对应的线程存在，则覆盖并返回原线程，如果不存在，则返回{@code null}
	 * @author 吴慕陶
	 * @version 1.0
	 * @see java.util.Map#put(Object, Object)
	 */
	public synchronized static ServerSocketThread add(String id, ServerSocketThread clientThreadSrv) {
		return clientThreadPool.put(id, clientThreadSrv);		
	}

	/**
	 * 根据输入的客户端线程ID删除对应的客户端线程
	 * @param id 需要删除的客户端线程ID
	 * @return 被删除的客户端线程，若该ID对应的线程不存在，则返回{@code null}
	 * @author 吴慕陶
	 * @version 1.0
	 * @see java.util.Map#remove(Object)
	 */
	public synchronized static ServerSocketThread remove(String id) {
		return clientThreadPool.remove(id);	
	}
	
	/**
	 * 根据输入的ID查找对应的客户端线程，若存在，则返回该线程，不存在则返回{@code null}
	 * @param id 需要获取的客户端线程ID
	 * @return 如果id对应的线程存在，则返回该线程，不存在则返回{@code null}
	 * @author 吴慕陶
	 * @version 1.0
	 * @see java.util.Map#get(Object)
	 */
	public synchronized static ServerSocketThread get(String id) {
		ServerSocketThread ret = clientThreadPool.get(id);
		return ret;
	}

	/**
	 * 获取整个客户端线程池
	 * @return 客户端线程池
	 * @author 吴慕陶
	 * @version 1.0
	 */
	public synchronized static Map<String, ServerSocketThread> getPool(){	
		return clientThreadPool;
	}
	
	/**
	 * 清空整个客户端线程池，会关闭所有客户端线程
	 * @author 吴慕陶
	 * @version 1.0
	 */
	public synchronized static void clear() {
		Iterator<Map.Entry<String, ServerSocketThread>> entries = clientThreadPool.entrySet().iterator();
		while(entries.hasNext()) {
			Map.Entry<String, ServerSocketThread> entry = entries.next();
			ServerSocketThread thd = entry.getValue();
			thd.close();
		}
		clientThreadPool.clear();		
	}
	
	/**
	 * 对所有客户端发送消息
	 * @param mes 对所有客户端发送的消息
	 * @author 吴慕陶
	 * @version 1.0
	 */
	public synchronized static void sendMesToAll(Message mes) {
		Iterator<Map.Entry<String, ServerSocketThread>> entries = clientThreadPool.entrySet().iterator();
		while(entries.hasNext()) {
			Map.Entry<String, ServerSocketThread> entry = entries.next();
			ServerSocketThread thd = entry.getValue();
			thd.sendMesToClient(mes);
		}
	}
	
	/**
	 * 打印目前连接到服务器上的所有客户端的信息
	 * @author 吴慕陶
	 * @version 1.0
	 */
	public synchronized static void printAllClient() {
		if(clientThreadPool.isEmpty()) {
			System.out.println("目前没有客户端连接到服务器！");
			return;
		}
		
		Iterator<Map.Entry<String, ServerSocketThread>> entries = clientThreadPool.entrySet().iterator();
		System.out.println("目前连接到服务器上的客户端: ");
		while(entries.hasNext()) {
			Map.Entry<String, ServerSocketThread> entry = entries.next();
			ServerSocketThread thd = entry.getValue();
			System.out.println("客户端线程ID: " + thd.getCliThdID() + ", ip地址: " + thd.getIP());
		}
	}
	
	/**
	 * 返回目前连接到服务器上的所有客户端的信息
	 * @author 柳多荣
	 * @version 1.0
	 * @return 目前连接到服务器上客户端的ID+ip地址，不存在则返回{@code null}
	 */
	public synchronized static Vector<String> getAll(){
		Vector<String> res = new Vector<String>();
		if(clientThreadPool.isEmpty()) {
			System.out.println("目前没有客户端连接到服务器！");
			return res;
		}
		
		Iterator<Map.Entry<String, ServerSocketThread>> entries = clientThreadPool.entrySet().iterator();
		System.out.println("目前连接到服务器上的客户端: ");
		while(entries.hasNext()) {
			Map.Entry<String, ServerSocketThread> entry = entries.next();
			ServerSocketThread thd = entry.getValue();
//			System.out.println("客户端线程ID: " + thd.getCliThdID() + ", ip地址: " + thd.getIP());
			res.add(thd.getCliThdID().toString());
			res.add(thd.getIP().toString());
		}
		return res;
	}
}
