package seu.list.server.bz;

import seu.list.common.IConstant;
import seu.list.server.bz.Server;
import seu.list.server.view.ServerFrame;

import java.util.*;

/**
 * 类{@code ServerMainFrame}为整个服务器端的程序入口，包含{@code public static void main}方法 <br>
 * 用于对服务端进行操作、交互 <br>
 * 数据成员为一系列服务器命令，类型: {@code public static final String} <br>
 * 服务器指令: <br>
 * 1. {@code help} 显示服务端使用帮助 <br>
 * 2. {@code launch} 启动服务器 <br>
 * 3. {@code close} 关闭服务器 <br>
 * 4. {@code reboot} 重启服务器 <br>
 * 5. {@code print all} 输出连接到服务器的所有客户端信息 <br>
 * 6. {@code exit} 退出服务端程序(同时会关闭正在运行的服务器线程) 
 * @author 吴慕陶
 * @version 1.0
 */
public class ServerMainFrame 
{
	public static final String ServerCmdHelp = "help";
	public static final String ServerCmdLaunch = "launch";
	public static final String ServerCmdClose = "close";
	public static final String ServerCmdReboot = "reboot";
	public static final String ServerCmdPrintAll = "print all";
	public static final String ServerCmdExit = "exit";
	public static Server srvThd = null;
	public static boolean isRunning = false;
	public static boolean isClosed = false;
	
	/**
	 * {@code main}方法，整个服务端程序的入口 <br>
	 * 主体为{@code while}循环，在命令行输出使用提示，等待用户输入指令
	 * @param args 系统命令行参数
	 * @author 吴慕陶
	 * @version 1.0
	 */
	public static void main(String[] args)
	{
		ServerFrame serverframe = new ServerFrame();
		serverframe.setVisible(true);
		
/*		System.out.println("欢迎使用虚拟校园系统-服务端程序！");
		System.out.println("输入" + ServerCmdHelp + "以获取服务端使用帮助");
		Scanner scan = new Scanner(System.in);
		
		
		while(!isClosed && scan.hasNext()) {
			//String input = scan.next();
			String input = scan.nextLine();
			switch(input) {
				case ServerCmdHelp: {
					printHelp();
					break;
				}
				case ServerCmdLaunch: {
					if(!isRunning) {
						srvThd = new Server(IConstant.SERVER_PORT);
						srvThd.start();
						isRunning = true;
					}else {
						System.out.println("服务器正在运行，请勿重复启动！");
						System.out.println("输入" + ServerCmdHelp + "以获取服务端使用帮助");
					}
					break;
				}
				case ServerCmdClose: {
					if(isRunning) {
						srvThd.close();
						srvThd = null;
						isRunning = false;
					}else {
						System.out.println("服务器还未启动！");
						System.out.println("输入" + ServerCmdHelp + "以获取服务端使用帮助");
					}
					break;
				}
				case ServerCmdReboot: {
					if(isRunning) {
						srvThd.close();
						srvThd = new Server(IConstant.SERVER_PORT);
						srvThd.start();
					}else {
						System.out.println("服务器还未启动！");
						System.out.println("输入" + ServerCmdHelp + "以获取服务端使用帮助");
					}
					break;
				}
				case ServerCmdPrintAll: {
					ServerClientThreadMgr.printAllClient();
					break;
				}
				case ServerCmdExit: {
					System.out.println("是否要退出服务器端程序? [y/n]");
					String choice = scan.next();
					if(choice.equals("y")) {
						if(isRunning) {
							srvThd.close();
							srvThd = null;
							isRunning = false;
						}
						serverframe.close();
						isClosed = true;
						
					}else {
						System.out.println("输入" + ServerCmdHelp + "以获取服务端使用帮助");
					}
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
*/
		while(!isClosed) {};
		System.exit(0);
	}
	
	/**
	 * 
	 * 重启服务器方法
	 * @author 柳多荣
	 * @version 1.0
	 * @return 如果成功重启返回1，若服务器没有运行导致无法重启返回0
	 */
	public static int reboot() {
		if(isRunning) {
			srvThd.close();
			srvThd = new Server(IConstant.SERVER_PORT);
			srvThd.start();
			System.out.println("服务器重启成功！");
			return 1;
		}else {
			return 0;
		}
	}
	
	/**
	 * 
	 * 开始运行服务器的方法
	 * @author 柳多荣
	 * @version 1.0
	 * @return 如果成功重启返回1，若服务器已经运行导致无法重启返回0
	 */
	public static int launch() {
		if(!isRunning) {
			srvThd = new Server(IConstant.SERVER_PORT);
			srvThd.start();
			isRunning = true;
			return 1;
		}else {
			return 0;
		}
	}
	
	/**
	 * 
	 * 获取与服务器连接的客户端信息
	 * @author 柳多荣
	 * @version 1.0
	 * @return 如果成功重启返回1，若服务器没有运行导致无法重启返回0
	 */
	public static Vector<String> getall(){
		Vector<String> res = new Vector<String>();
		res = ServerClientThreadMgr.getAll();
		return res;
	}
	
	/**
	 * 
	 * 退出服务端程序的方法
	 * @author 柳多荣
	 * @version 1.0
	 */
	public static void exitbtn() {
		if(isRunning) {
			srvThd.close();
			srvThd = null;
			isRunning = false;
		}
		isClosed = true;
		System.exit(0);
	}
	
	/**
	 * 
	 * 关闭服务器方法
	 * @author 柳多荣
	 * @version 1.0
	 * @return 如果成功重启返回1，若服务器导致没有执行关闭服务器的操作则返回0
	 */
	public static int closebtn() {
		if(isRunning) {
			srvThd.close();
			srvThd = null;
			isRunning = false;
			return 1;
		}else {
			return 0;
		}
	}
	
	/**
	 * 打印服务端使用帮助
	 * @author 吴慕陶
	 * @version 1.0
	 */
	
	public static void printHelp() {
		System.out.println("----------------------欢迎使用虚拟校园系统-服务器端----------------------");
		System.out.println("服务端使用帮助");
		System.out.println("输入" + ServerCmdLaunch + "以启动服务器");
		System.out.println("输入" + ServerCmdClose + "以关闭服务器");
		System.out.println("输入" + ServerCmdReboot + "以重启服务器");
		System.out.println("输入" + ServerCmdPrintAll + "以打印所有已连接的客户端信息");
		System.out.println("输入" + ServerCmdExit + "以退出服务端程序(会同时关闭正在运行的服务器线程)");
		System.out.println("----------------------欢迎使用虚拟校园系统-服务器端----------------------");
	}
}
