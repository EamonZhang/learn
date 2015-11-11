package ant;

import java.io.UnsupportedEncodingException;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println("Hello ZW!!!");
		System.out.println("aaa");
		String s = new String("悠悠".getBytes("UTF-8"));
		System.out.println(s);
	}
}
