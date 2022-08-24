
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
	public static final String ServerCmdHelp = "help";
	public static final String ServerCmdClose = "close";
	
	public static void main(String[] args)
	{
		Server srvThd = new Server(IConstant.SERVER_PORT);
		srvThd.start();
		boolean isClosed = false;
		
		System.out.println("输入" + ServerCmdHelp + "以获取服务端使用帮助");
		Scanner scan = new Scanner(System.in);
		while(!isClosed && scan.hasNext()) {
			String input = scan.next();
			switch(input) {
				case ServerCmdHelp: {
					printHelp();
					break;
				}
				case ServerCmdClose: {
					srvThd.close();
					isClosed = true;
					break;
				}
				default: {
					System.out.println("错误的指令！");
					System.out.println("输入" + ServerCmdHelp + "以获取服务端使用帮助");
					break;
				}
			}
		} // end while
		scan.close();
		System.exit(0);
	}
	
	public static void printHelp() {
		System.out.println("-------------------------------------------------");
		System.out.println("服务端使用帮助");
		System.out.println("输入" + ServerCmdClose + "以关闭服务器");
		System.out.println("-------------------------------------------------");
	}
}
