package seu.list.client.bz;



import seu.list.client.view.ClientLoginFrame;
import seu.list.client.view.ClientStuCourseFrame;
import seu.list.client.view.ClientTeacherFrame;
import seu.list.client.view.MainMenu;
import seu.list.common.*;


import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.sql.SQLException;

public class ClientMainFrame {
	public static Socket socket;

	public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		try {
			socket =  new Socket(IConstant.SERVER_ADDRESS, IConstant.SERVER_PORT);		
		}catch(IOException e) {
			e.printStackTrace();
		}

		// 启动登录窗口
		ClientLoginFrame c = new ClientLoginFrame(socket);
		c.setVisible(true);
		
		// 卡死主程序，防止资源释放
		while(true);
		
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
