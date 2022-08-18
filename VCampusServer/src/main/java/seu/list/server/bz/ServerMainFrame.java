/**
* @author 吴慕陶
* @JDKversion jdk 1.8.0_341
* 
* 服务端程序入口，包含public static void main
*/

package seu.list.server.bz;

import seu.list.common.IConstant;

import java.util.*;

public class ServerMainFrame 
{
	public static void main(String[] args)
	{
		Server srvThd = new Server(IConstant.SERVER_PORT);
		srvThd.start();
		
		System.out.println("输入1关闭服务器：");
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()) {
			int input = scan.nextInt();
			if(input == 1) {
				srvThd.close();
				break;
			}
		}
		scan.close();
	}
}
