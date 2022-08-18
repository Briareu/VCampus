package seu.list.client.dao;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
import virtualSchoolClient.src.vsst.client.bz.ClientConServer;
import virtualSchoolClient.src.vsst.common.ClientReq;
*/


/*
public class SocketLink {
	ClientReq sendMsm;
	ClientReq receiveMsm;
	
	public SocketLink(ClientReq sendMsm)
	{
		this.sendMsm = sendMsm;
	}
	
	public void send()
	{
		try {
			ObjectOutputStream dos = new ObjectOutputStream(ClientConServer.getSocket().getOutputStream());
			dos.writeObject(sendMsm);
			dos.flush();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ClientReq receive()
	{
		try {
			ObjectInputStream ois = new ObjectInputStream(ClientConServer.getSocket().getInputStream());
			receiveMsm = (ClientReq)ois.readObject();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return receiveMsm;
		
	}
}
*/
