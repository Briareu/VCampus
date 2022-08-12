package server.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;

public class Server 
{
	private int port = 9876;
	
	// 构造函数，初始化
	public Server() throws Exception
	{
		ServerSocket srvSkt = new ServerSocket(port);
		
		
		while(true)
		{
			// 不断接受客户端请求
		}
	}
}