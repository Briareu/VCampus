package VCampusClient.src.main.java.seu.list.client.bz;



import VCampusClient.src.main.java.seu.list.client.view.ClientCourseFrame;
import VCampusClient.src.main.java.seu.list.client.view.ClientLoginFrame;
import VCampusClient.src.main.java.seu.list.client.view.ClientStuCourseFrame;
import VCampusClient.src.main.java.seu.list.client.view.ClientTeacherFrame;
import VCampusClient.src.main.java.seu.list.common.IConstant;
import VCampusClient.src.main.java.seu.list.common.Message;

import java.net.Socket;
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

		// 启动各种窗口
		//ClientTeacherFrame c=new ClientTeacherFrame("3",socket);
		//ClientStuCourseFrame s=new ClientStuCourseFrame("2",socket);
		///ClientLoginFrame c=new ClientLoginFrame(socket);
		// 要关闭客户端时，给服务器发送下线通知
//		Message offlineMes = new Message();
//		offlineMes.offline();
//		Client client = new Client(socket);
//		client.sendRequestToServer(offlineMes);
//		try {
//			if(!socket.isClosed()) {
//				socket.close();
//			}
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
	}

}
