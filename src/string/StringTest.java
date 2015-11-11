package string;

import java.util.Collection;
import java.util.Collections;

public class StringTest {

	public static void main(String[] args) {
//		String s = String.format("(%03d) %03d-%04d", 24,12,123456789);
//		System.out.println(s);
		String str1 = "abc";
		String str2 = "ab"+"c";
		String str3 = new String("abc");
		System.out.println(str1 == str2);
		System.out.println(str1 == str3);
		System.out.println(str1 == str3.intern());
//		Collections.synchronizedMap(m)
	}
}
