/**
* @author ��Ľ��
* @version jdk 1.8.0_341
* 
* ����˳�����ڣ����Ʒ��������߳�
*/

package server.common;

public class ServerMainFrame 
{
	public static void main(String[] args)
	{
		Server srvThd = new Server();
		srvThd.start();
	}
}