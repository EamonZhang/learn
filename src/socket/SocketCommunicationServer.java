package socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketCommunicationServer {
	public static void main(String[] args) {
		try {
			boolean flag = true;// 设置标志位为真
			Socket client = null;// 创建Socket client以接收来自客户端的请求
			String inputLine;
			SocketAddress address = new InetSocketAddress("192.168.6.78",
					9000);
			ServerSocket serverSocket = new ServerSocket();// 以端口9000创建一个服务器Socket
			serverSocket.bind(address);
			System.out.println("服务器在端口9000上监听");
//			file: // 也可以使用serverSocket.getLocalPort()来获得端口号
			while (flag) {
				client = serverSocket.accept();
//				 file://监听并接受与此Socket的连接，该方法会阻塞直到有一个连接产生
				DataInputStream input = new DataInputStream(
						new BufferedInputStream(client.getInputStream()));
				PrintStream output = new PrintStream(new BufferedOutputStream(
						client.getOutputStream()));
				while ((inputLine = input.readLine()) != null) {
					if (inputLine.equals("Stop")) {
						flag = false;
						break;
					}
					System.out.println("service : "+inputLine);
					output.println(inputLine);
					output.flush();
				}
				output.close();
				input.close();
				client.close();
			}
			serverSocket.close();
		} catch (IOException e) {
		}
	}
}