package server.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;

public class Server 
{
	private int port = 9876;
	
	// ���캯������ʼ��
	public Server() throws Exception
	{
		ServerSocket srvSkt = new ServerSocket(port);
		
		
		while(true)
		{
			// ���Ͻ��ܿͻ�������
		}
	}
}