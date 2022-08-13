/**
* @author 吴慕陶
* @version jdk 1.8.0_341
* 
* 网络层
* 服务器主线程
* 为每个客户端分配一个线程
*/

package server.common;

import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread
{
	private final int port = 9876; 
	private ServerSocket srvSkt;
	private ArrayList<ClientThread> cliThdList = new ArrayList<ClientThread>();
	
	// 构造函数，初始化，启动服务端
	public Server() 
	{
		try
		{
			this.srvSkt = new ServerSocket(port);
			System.out.println("服务器建立成功 " + port);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		try
		{
			while(true)
			{
				// 不断接受客户端请求
				Socket cliSkt = this.srvSkt.accept();
				System.out.println("有一个新客户端接入");
				// 新建一个客户端线程
				ClientThread cliThd = new ClientThread(cliSkt);
				cliThd.start();
				cliThdList.add(cliThd);
				
				
				// 在接收新请求是清除已经结束请求的客户端
				final int listSize = this.cliThdList.size();
				for(int i = 0; i < listSize; ++i)
				{
					if(this.cliThdList.get(i).isStop())
					{
						this.cliThdList.remove(i);
					}
				}
			}
			
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void sendStrToAll(String str)
	{
		final int listSize = this.cliThdList.size();
		for(int i = 0; i < listSize; ++i)
		{
			this.cliThdList.get(i).sendStr(str);
		}
	}
}