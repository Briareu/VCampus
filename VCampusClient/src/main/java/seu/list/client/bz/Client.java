package seu.list.client.bz;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import seu.list.common.*;

public class Client {
	private Socket socket;
	
	public Client(Socket socket) {
		this.socket = socket;
	}
	
	public Message sendRequestToServer (Message clientRequest) { // �����Ƿ��������������
        try{
            ObjectOutputStream request = new ObjectOutputStream(socket.getOutputStream());
            request.writeObject(clientRequest); 
            request.flush();
            request.close();
            
            if(clientRequest.isOffline()) {
            	return null;
            }
            ObjectInputStream response = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            Message mesRet = new Message();
			while(true) { // 等待服务器回应数据
	            mesRet = (Message)response.readObject();
	            if(mesRet.getMessageType() == MessageType.operFeedback) {
	            	break;
	            }
			}
			response.close();
			return mesRet; // 把收到的数据返回给客户端
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
