/**
* @author 吴慕陶
* @version jdk 1.8.0_341
* 
* 网络层
* 服务器内客户端线程
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
	
	// 初始化
	public ClientThread(Socket cliSkt) throws IOException
	{
		this.cliSkt = cliSkt;
		this.dInStrm = new DataInputStream(this.cliSkt.getInputStream());
		this.dOutStrm = new DataOutputStream(this.cliSkt.getOutputStream());
		
	}
	
	// 向客户端发送字符串
	public void sendStr(String dStr)
	{
		try
		{
			// 定义传输的数据类型
			this.dOutStrm.writeByte(IDataType.STRING);
			// 将数据转换成字节
			byte[] bData = dStr.getBytes();
			final int bLen = bData.length;
			// 将数据装入输出流
			this.dOutStrm.writeInt(bLen);
			this.dOutStrm.write(bData);
			this.dOutStrm.flush();
			
			System.out.println("服务器发送字符串成功，内容：" + dStr);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("服务器发送字符串失败，内容：" + dStr);
		}
	}
	
	// 监听并接收客户端发来的数据
	public void run()
	{
		try
		{
			// 不断监听
			while(true)
			{
				// 判断接收到的数据类型
				byte dType = this.dInStrm.readByte();
				if(dType == IDataType.STRING) // 接受字符串
				{
					final int bLen = this.dInStrm.readInt();
					byte[] bData = new byte[bLen];
					this.dInStrm.read(bData);
					// 将字节数据转换为字符串
					String dStr = new String(bData);
					
					System.out.println("服务器接收字符串成功，内容：" + dStr);
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
			System.out.println("服务器接收数据失败");
		}
	}
	
	public boolean isStop()
	{
		return this.stopFlag;
	}
}
