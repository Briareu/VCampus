/**
* @author ��Ľ��
* @version jdk 1.8.0_341
* 
* �����
* ���������߳�
* Ϊÿ���ͻ��˷���һ���߳�
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
	
	// ���캯������ʼ�������������
	public Server() 
	{
		try
		{
			this.srvSkt = new ServerSocket(port);
			System.out.println("�����������ɹ� " + port);
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
				// ���Ͻ��ܿͻ�������
				Socket cliSkt = this.srvSkt.accept();
				System.out.println("��һ���¿ͻ��˽���");
				// �½�һ���ͻ����߳�
				ClientThread cliThd = new ClientThread(cliSkt);
				cliThd.start();
				cliThdList.add(cliThd);
				
				
				// �ڽ���������������Ѿ���������Ŀͻ���
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