package socket;

import java.io.DataInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketCommunicationClient {
	public static void main(String[] args) {
		try {
			Socket clientSocket = new Socket("192.168.6.78",9000);// 创建一个流Socket并与主机mice上的端口9000相连接
			OutputStream output = clientSocket.getOutputStream();// 向此Socket写入字节的一个输出流
			DataInputStream input = new DataInputStream(
					clientSocket.getInputStream());
			// file://创建新的数据输入流以便从指定的输入流中读出数据
			int c;
			String response;
			while ((c = System.in.read()) != -1)// 从屏幕上接受输入的字符串，并且分解成一个个字符
			{
				output.write((byte) c);
				if (c == '\n')// 如果字符为回车，则输出字符串缓冲
				{
					output.flush();
					response = input.readLine();
					System.out.println("Communication:" + response);
				}
			}
			output.close();
			input.close();
			clientSocket.close();
		} catch (Exception e) {
			System.err.println("Exception :" + e);
		}
	}
}
