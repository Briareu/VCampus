package seu.list.client.bz;

import seu.list.common.*;

import java.net.Socket;
import java.io.IOException;

public class ClientMainFrame {
	public static Socket socket;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			socket =  new Socket(IConstant.SERVER_ADDRESS, IConstant.SERVER_PORT);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		// 启动各种窗口
		
		
		
		// 要关闭客户端时，给服务器发送下线通知
		Message offlineMes = new Message();
		offlineMes.offline();
		Client client = new Client(socket);
		client.sendRequestToServer(offlineMes);
		try {
			if(!socket.isClosed()) {
				socket.close();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
