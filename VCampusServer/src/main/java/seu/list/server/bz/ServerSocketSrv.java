package seu.list.server.bz;

public interface ServerSocketSrv extends Runnable {
	
	void start();

	void close();

	boolean isClosed();
	
}