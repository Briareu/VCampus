package VCampusServer.src.main.java.seu.list.common;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;




public class Client {
    public Message sendRequestToServer (Message clientRequest ) {
        try
        {
            //Socket socket = new Socket("192.168.43.81",19888);//192.168.43.81
            Socket socket = new Socket("localhost",8080);
            socket.setSoTimeout(10000);
            ObjectOutputStream request = new ObjectOutputStream(socket.getOutputStream());
            request.writeObject(clientRequest);
            request.flush();
            socket.shutdownOutput();

            ObjectInputStream response = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            Message message = (Message)response.readObject();
            response.close();
            socket.close();


            if(message!=null)
            {
                return message;
            }
        }
        catch (UnknownHostException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}