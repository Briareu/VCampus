package virtualSchoolServer.server.bz;

import java.util.LinkedHashMap;
import java.util.Map;


public class ServerClientThreadMgr {
	private  static  Map<String, ServerSocketSrvThread>  clientThreadPool = new LinkedHashMap<String, ServerSocketSrvThread>();

	public synchronized static void add(String id, ServerSocketSrvThread clientThreadSrv) {
		clientThreadPool.put(id, clientThreadSrv);		
	}

	public synchronized static ServerSocketSrvThread remove(String id) {
		return clientThreadPool.remove(id);		
	}

	public synchronized static Map<String, ServerSocketSrvThread> getPool(){
		return clientThreadPool;
	}
	
	public synchronized static void clear() {
		clientThreadPool.clear();		
	}

}
