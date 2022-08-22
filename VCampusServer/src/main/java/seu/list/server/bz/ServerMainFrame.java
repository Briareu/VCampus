
package seu.list.server.bz;


import seu.list.common.IConstant;
import seu.list.server.bz.Server;


/**
* @author 吴慕陶
* @JDKversion jdk 1.8.0_341
* 
* 服务端程序入口，包含public static void main
*/

//package seu.list.server.bz;

//package VCampusServer.src.main.java.seu.list.server.bz;

/*
import VCampusServer.src.main.java.seu.list.common.IConstant;
import VCampusServer.src.main.java.seu.list.server.bz.Server;
*/
//import seu.list.common.IConstant;
//import seu.list.server.bz.Server;


import java.util.*;

public class ServerMainFrame 
{
	public static void main(String[] args)
	{
		Server srvThd = new Server(IConstant.SERVER_PORT);
		srvThd.start();
		
		System.out.println("输入close关闭服务器：");
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()) {
			String input = scan.next();
			if(input.equals("close")) {
				srvThd.close();
				break;
			}
		}
		scan.close();
		System.exit(0);
	}
}
