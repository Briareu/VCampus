package socket;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
public class client {
    public ServerResponse send(ClientRequest clientRequest) {
        //客户端请求与本机在20006端口建立TCP连接
        Socket client;
        try {
            client = new Socket("127.0.0.1", 20006);
            client.setSoTimeout(10000);
            //获取Socket的输出流，用来发送数据到服务端
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            //获取Socket的输入流，用来接收从服务端发送过来的数据
            ObjectInputStream buf =  new ObjectInputStream(new BufferedInputStream(
                    client.getInputStream()));

            out.writeObject(clientRequest);

            out.flush();
            Object object = buf.readObject();
            //如果构造函数建立起了连接，则关闭套接字，如果没有建立起连接，自然不用关闭
            client.close(); //只关闭socket，其关联的输入输出流也会被关闭

            if (object != null) {
                return (ServerResponse) object;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
