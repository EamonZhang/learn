package pinyin;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DllTest
{
     static
     {
//         System.loadLibrary("chat");
     }
 
     public static native int connect(int i);
     public static native String getstring(String src);
 
     public static void main(String[] args)
     {
//         DllTest dllTest = new DllTest();
//         System.out.println(dllTest.connect(5));
//         System.out.println(dllTest.getstring("java send"));
    	 Properties pro = new Properties();
    	 pro.setProperty("1","a");
    	 pro.setProperty("2", "b");
    	 Map<String, String> map = new HashMap<String, String>((Map)pro);
    	 for (String string : map.keySet()) {
			System.out.println(string);
		}
     }
}