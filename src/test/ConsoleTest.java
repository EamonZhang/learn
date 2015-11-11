package test;

import java.io.IOException;

public class ConsoleTest {

	public static void main(String[] args) {
		byte[] b = new byte[2048];
		try {
			System.in.read(b);
			System.out.println(new String(b));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
