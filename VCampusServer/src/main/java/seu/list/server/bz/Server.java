//package VCampusServer.src.main.java.seu.list.server.bz;
package seu.list.server.bz;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
	private Integer thdNum = 10000;
	private ServerSocket serverSocket;
	private boolean isClosed = false;
	
	public Server (int port) {
		try {
			this.serverSocket = new ServerSocket(port);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            System.out.println("服务端已启动，等待客户端连接..");
            
            while(!isClosed)
            {
                Socket socket = this.serverSocket.accept();
                System.out.println("Thread: "+this.thdNum.toString()+", 已经建立");

                ServerSocketThread thd = new ServerSocketThread(socket, this.thdNum.toString());
                thd.start();
                ServerClientThreadMgr.add(this.thdNum.toString(), thd);
                ++this.thdNum;
            } // end while
            
            if(!this.serverSocket.isClosed()) {
    			this.serverSocket.close();
    		}
            
            ServerClientThreadMgr.clear();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void close() {
    	System.out.println("服务器关闭！");
    	this.isClosed = true;
    }

}