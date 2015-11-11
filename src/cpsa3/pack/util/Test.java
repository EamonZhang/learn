package cpsa3.pack.util;  
  
import java.io.IOException;  
  
import org.apache.commons.httpclient.HttpException;  
  
public class Test {  
      
    private static String PHONE = "13898186463";  
    private static String PWD = "hello123";  
    private static String TO = "13898186463";  
    private static String MSG = "你好：Hello World!";  
      
    public static void main(String[] args) throws HttpException, IOException {  
        Fetion.sendMsg(PHONE, PWD, TO, MSG);  
    }  
  
}  