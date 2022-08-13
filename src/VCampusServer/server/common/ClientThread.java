/**
* @author ��Ľ��
* @version jdk 1.8.0_341
* 
* �����
* �������ڿͻ����߳�
*/

package server.common;

import java.net.Socket;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ClientThread extends Thread
{
	private boolean stopFlag = false;
	
	private Socket cliSkt;
	private DataInputStream dInStrm;
	private DataOutputStream dOutStrm;
	
	// ��ʼ��
	public ClientThread(Socket cliSkt) throws IOException
	{
		this.cliSkt = cliSkt;
		this.dInStrm = new DataInputStream(this.cliSkt.getInputStream());
		this.dOutStrm = new DataOutputStream(this.cliSkt.getOutputStream());
		
	}
	
	// ��ͻ��˷����ַ���
	public void sendStr(String dStr)
	{
		try
		{
			// ���崫�����������
			this.dOutStrm.writeByte(IDataType.STRING);
			// ������ת�����ֽ�
			byte[] bData = dStr.getBytes();
			final int bLen = bData.length;
			// ������װ�������
			this.dOutStrm.writeInt(bLen);
			this.dOutStrm.write(bData);
			this.dOutStrm.flush();
			
			System.out.println("�����������ַ����ɹ������ݣ�" + dStr);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("�����������ַ���ʧ�ܣ����ݣ�" + dStr);
		}
	}
	
	// ���������տͻ��˷���������
	public void run()
	{
		try
		{
			// ���ϼ���
			while(true)
			{
				// �жϽ��յ�����������
				byte dType = this.dInStrm.readByte();
				if(dType == IDataType.STRING) // �����ַ���
				{
					final int bLen = this.dInStrm.readInt();
					byte[] bData = new byte[bLen];
					this.dInStrm.read(bData);
					// ���ֽ�����ת��Ϊ�ַ���
					String dStr = new String(bData);
					
					System.out.println("�����������ַ����ɹ������ݣ�" + dStr);
				}
				else if(dType == IDataType.END)
				{
					this.cliSkt.close();
					this.stopFlag = true;
					break;
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("��������������ʧ��");
		}
	}
	
	public boolean isStop()
	{
		return this.stopFlag;
	}
}
