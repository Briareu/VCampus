package seu.list.client.bz;

import seu.list.common.Message;
import seu.list.common.MessageType;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.SocketException;

import javax.swing.JOptionPane;

public class Client {
	private Socket socket;
	
	public Client(Socket socket) {
		this.socket = socket;
	}
	
	public Message sendRequestToServer (Message clientRequest) { // �����Ƿ��������������
        try{
        	// 向服务端发送数据
            ObjectOutputStream request = new ObjectOutputStream(socket.getOutputStream());
            request.writeObject(clientRequest); 
            request.flush();
            
            // 下线通知不需要等待回应
            if(clientRequest.isOffline()) {
            	return null;
            }

            Message mesRet = new Message();
			while(true) { // 等待服务器回应数据
	            ObjectInputStream response = new ObjectInputStream(socket.getInputStream());
	            mesRet = (Message)response.readObject();
	            if(mesRet.getMessageType().equals(MessageType.operFeedback)) {
	            	break;
	            }
			}

			return mesRet; // 把收到的数据返回给客户端
			
        }catch (SocketException se) {
        	JOptionPane.showMessageDialog(null, "网络连接错误，请重新启动客户端或联系服务器管理员", "错误", JOptionPane.ERROR_MESSAGE);
        	System.exit(0);
        }catch (UnknownHostException e) {
            // TODO: handle exception
            e.printStackTrace();
        }catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
        return null;
    }
}
