package client.bz.thread;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 用于线程管理
 * @author 姜云骞
 *
 */
public class ClientThreadSrvMgr {

	private static Map<String, ClientThreadSrv> clientThreadPool = new LinkedHashMap<String, ClientThreadSrv>();
/**
 * 用于线程的添加
 * @param userID 用户ID
 * @param clientThreadSrv 给用户分配的线程
 */
	public static void add(String userID, ClientThreadSrv clientThreadSrv) {
		clientThreadPool.put(userID, clientThreadSrv);		
	}

	/**
	 * 用于移出用户线程
	 * @param userID 需要移除的客户ID
	 * @return 返回值为得到的线程
	 */
	public static ClientThreadSrv remove(String userID) {
		return clientThreadPool.remove(userID);		
	}

	/**
	 * 用于得到整个线程池
	 * @return 返回值为当前的线程池
	 */
	public static Map<String, ClientThreadSrv> getPool(){
		return clientThreadPool;
	}
}