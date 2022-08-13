/**
* @author 吴慕陶
* @version jdk 1.8.0_341
* 
* 服务端程序入口，控制服务器主线程
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
