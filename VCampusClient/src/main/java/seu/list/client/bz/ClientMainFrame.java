package seu.list.client.bz;

import seu.list.client.view.ClientLoginFrame;
import seu.list.common.*;


import java.net.Socket;
import java.net.ConnectException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * 客户端程序入口，启动后立即建立{@code Socket}, 需要客户端全程联网 <br>
 * 与服务器连接成功后，显示用户登录界面
 * @author 吴慕陶
 * @version 1.0
 */
public class ClientMainFrame {
	public static Socket socket;

	/**
	 * 程序入口
	 * @param args 系统命令行参数
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		try {
			socket =  new Socket(IConstant.SERVER_ADDRESS, IConstant.SERVER_PORT);		

			// 启动登录窗口
			ClientLoginFrame c = new ClientLoginFrame(socket);
			//c.setVisible(true);
			
			// 卡死主程序，防止资源释放
			while(true);
			
		}catch(ConnectException ce) {
			JOptionPane.showMessageDialog(null, "网络连接错误，请重新启动客户端或联系服务器管理员", "错误", JOptionPane.ERROR_MESSAGE);
        	System.exit(0);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void close() {
		// 要关闭客户端时，给服务器发送下线通知
		goOffline();
		// 客户端程序即将关闭，关闭socket
		try {
			if(!socket.isClosed()) {
				socket.close();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		// 退出程序
		System.exit(0);
	}
	
	public static void goOffline() {
		Message offlineMes = new Message();
		offlineMes.offline();
		Client client = new Client(socket);
		client.sendRequestToServer(offlineMes);
	}
}
