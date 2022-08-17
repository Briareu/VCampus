package seu.list.client.bz;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import seu.list.common.*;

public class Client implements Runnable{
	private Socket socket;
	private boolean isClosed = false;
	private Message megFromServer = null;
	
	public Client(String addr, int port) {
		try {
			this.socket = new Socket(addr, port);
		}catch (UnknownHostException e) {
	        e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			ObjectInputStream response = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			
			while(!isClosed) {
	            Message message = (Message)response.readObject();
	            if(message.getMessageType() == MessageType.operFeedback) {
	            	this.megFromServer = message;
	            }
			}
			response.close();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Message getMesFromServer() {
		while(this.megFromServer == null);
		return this.megFromServer;
	}
	
	public void sendRequestToServer (Message clientRequest) { // 参数是发向服务器的数据
        try
        {
            ObjectOutputStream request = new ObjectOutputStream(socket.getOutputStream());
            request.writeObject(clientRequest);
            request.flush();
            request.close();
        }
        catch (UnknownHostException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
	
	public void close() {
		try {
			if(!this.socket.isClosed()) {
				this.socket.close();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
