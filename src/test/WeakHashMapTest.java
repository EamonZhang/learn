package test;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapTest {  
    static Map wMap = new WeakHashMap();  
    static {  
//    	Double.compare(d1, d2);
//    	Arrays.equals(a, a2);
        wMap.put("1", "ding");  
        wMap.put("2", "job");  
    }  
    public static void testWeakHashMap(){  
  
        System.out.println("first get:"+wMap.get("1"));  
        try {  
            Thread.sleep(5000);  
        } catch (InterruptedException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        System.out.println("next get:"+wMap.get("1"));  
    }  
    public static void main(String[] args) {  
       int i =0;
    	while(true){
    	testWeakHashMap();
    	if(i%20 == 0)
    		i++;
        System.out.println("2get:"+wMap.get("2"));  
//        String
//        BigInteger.
    	}
    }  
}  