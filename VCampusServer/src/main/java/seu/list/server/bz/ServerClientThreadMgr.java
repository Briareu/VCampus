package main.java.seu.list.server.bz;

import java.util.LinkedHashMap;
import java.util.Map;


public class ServerClientThreadMgr {
	private  static  Map<String, ServerSocketThread>  clientThreadPool = new LinkedHashMap<String, ServerSocketThread>();

	public synchronized static void add(String id, ServerSocketThread clientThreadSrv) {
		clientThreadPool.put(id, clientThreadSrv);		
	}

	public synchronized static ServerSocketThread remove(String id) {
		return clientThreadPool.remove(id);		
	}

	public synchronized static Map<String, ServerSocketThread> getPool(){
		return clientThreadPool;
	}
	
	public synchronized static void clear() {
		clientThreadPool.clear();		
	}

}
