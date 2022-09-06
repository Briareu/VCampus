package seu.list.server.bz;

import java.io.IOException;
import java.net.SocketException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * 类{@code Server}为服务器主线程类，继承类{@code Thread} <br>
 * 在服务端程序启动会立即启动 <br>
 * 用于为每个连接到的客户端在服务器分配客户端线程 <br>
 * <br>
 * 私有数据成员: <br>
 * 1. 客户端线程id: {@code thdNum}, 类型: {@code Integer} <br>
 * 2. 服务器Socket: {@code serverSocket}, 类型: {@code Server} <br>
 * 3. 线程关闭标志: {@code isClosed}, 类型: {@code boolean} 
 * 
 * @author 吴慕陶
 * @version 1.0
 * @see java.lang.Thread
 */
public class Server extends Thread{
	private Integer thdNum = 10000;
	private ServerSocket serverSocket;
	private boolean isClosed = false;
	
	/**
	 * 类{@code Server}构造器, 用传入的端口建立{@code ServerSocket}
	 * @author 吴慕陶
	 * @version 1.0
	 * @param port 服务器端口
	 */
	public Server (int port) {
		try {
			this.serverSocket = new ServerSocket(port);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 服务器主线程的{@code run}方法，继承自类{@code Thread} <br>
	 * 主体为{@code while}循环，通过标志{@code isClosed}控制 <br>
	 * 等待客户端接入，使用{@code accept}方法阻塞 <br>
	 * 接入后为客户端分配id和线程，并加入服务器线程池 <br>
	 * {@code while}退出即代表服务器即将关闭，会关闭{@code ServerSocket}并清空线程池
	 * 
	 * @author 吴慕陶
	 * @version 1.0
	 * @see java.net.ServerSocket#accept()
	 */
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            System.out.println("服务器线程已启动，等待客户端连接..");
            
            while(!isClosed && !this.serverSocket.isClosed())
            {
                Socket socket = this.serverSocket.accept(); // 在此阻塞
                System.out.println("Thread: "+this.thdNum.toString()+", 已经建立");

                ServerSocketThread thd = new ServerSocketThread(socket, this.thdNum.toString());
                thd.start();
                ServerClientThreadMgr.add(this.thdNum.toString(), thd);
                ++this.thdNum;
            } // end while
            
        }catch(SocketException se) {
        	System.out.println("ServerSocket closed");
        }catch(IOException e){
            e.printStackTrace();
        }finally {
        	ServerClientThreadMgr.clear();
        	System.out.println("服务器线程关闭");
        }
    }
    
    /**
     * 服务器主线程的{@code close}方法，可被服务端主程序调用 <br>
     * 调用后将标志{@code isClosed}置为{@code true}, 使{@code run}方法中的{@code while}循环退出 <br>
     * @author 吴慕陶
     * @version 1.0 
     */
    public void close() {
    	this.isClosed = true;
    	try {
    		this.serverSocket.close();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	System.out.println("服务器关闭！");
    }
    
    /**
     * 获取当前服务端主线程的{@code ServerSocket}
     * @return serverSocket 服务端线程的{@code ServerSocket}
     * @author 吴慕陶
     * @version 1.0
     */
    public ServerSocket getServerSocket() {
    	return this.serverSocket;
    }
}