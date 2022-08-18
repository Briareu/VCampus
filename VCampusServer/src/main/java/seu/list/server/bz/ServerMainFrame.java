package seu.list.server.bz;


import seu.list.common.IConstant;
import seu.list.server.bz.Server;


/**
* @author ��Ľ��
* @version jdk 1.8.0_341
* 
* ����˳�����ڣ����Ʒ��������߳�
*/

//package VCampusServer.src.main.java.seu.list.common;

public class ServerMainFrame 
{
	public static void main(String[] args)
	{
		Server srvThd = new Server(IConstant.SERVER_PORT);
		srvThd.start();
	}
}
