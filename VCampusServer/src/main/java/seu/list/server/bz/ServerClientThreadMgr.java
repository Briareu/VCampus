//package main.java.seu.list.server.bz;
package seu.list.server.bz;

import java.util.*;


public class ServerClientThreadMgr {
	private static Map<String, ServerSocketThread> clientThreadPool = new LinkedHashMap<String, ServerSocketThread>();

	public synchronized static void add(String id, ServerSocketThread clientThreadSrv) {
		clientThreadPool.put(id, clientThreadSrv);		
	}

	public synchronized static ServerSocketThread remove(String id) {
		return clientThreadPool.remove(id);		
	}
	
	public synchronized static ServerSocketThread get(String id) {
		ServerSocketThread ret = clientThreadPool.get(id);
		return ret;
	}

	public synchronized static Map<String, ServerSocketThread> getPool(){
		Iterator<Map.Entry<String, ServerSocketThread>> entries = clientThreadPool.entrySet().iterator();
		while(entries.hasNext()) {
			Map.Entry<String, ServerSocketThread> entry = entries.next();
			ServerSocketThread thd = entry.getValue();
			thd.close();
		}
		
		return clientThreadPool;
	}
	
	public synchronized static void clear() {
		
		clientThreadPool.clear();		
	}

}
