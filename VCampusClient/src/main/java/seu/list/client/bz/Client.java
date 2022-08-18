//package VCampusClient.src.main.java.seu.list.client.bz;
package seu.list.client.bz;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/*
import VCampusClient.src.main.java.seu.list.common.Message;
import VCampusClient.src.main.java.seu.list.common.MessageType;
*/

import seu.list.common.Message;
import seu.list.common.MessageType;

public class Client {
	private Socket socket;
	
	public Client(Socket socket) {
		this.socket = socket;
	}
	
	public Message sendRequestToServer (Message clientRequest) { // �����Ƿ��������������
        try{
            ObjectOutputStream request = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream response = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            request.writeObject(clientRequest); 
            request.flush();

			System.out.println("socket.shutdownOutput()");
			//socket.shutdownOutput();
            //request.close();
            
            if(clientRequest.isOffline()) {
            	return null;
            }

            Message mesRet = new Message();
			while(true) { // 等待服务器回应数据
	            mesRet = (Message)response.readObject();
	            if(mesRet.getMessageType().equals(MessageType.operFeedback)) {
	            	break;
	            }
			}

			request.close();
			response.close();

			System.out.println(mesRet.getContent());
			System.out.println("socket.shutdownInput()");
			//socket.shutdownInput();
			//response.close();

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
