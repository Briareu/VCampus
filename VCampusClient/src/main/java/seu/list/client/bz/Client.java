package seu.list.client.bz;

import seu.list.common.Message;
import seu.list.common.MessageType;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;



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

			//System.out.println(mesRet.getUserType());

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
