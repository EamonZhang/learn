package rtti;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		String s;
		List<String> list = Arrays.asList("d");
		System.out.println(list.size());
//		Boolean.TYPE.getClass();
		System.out.println(new SimpleDateFormat("yyyy-MM-dd ").format(new Date()));
	}

}
