package ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class SSHTest {
	public static void main(String[] args) {
		 String hostname = "127.0.0.1";  
	       String username = "zhangjin";
	       String password = "123";
	       try
	       {
	           Connection conn = new Connection(hostname);
	           conn.connect();
	           
	           boolean isAuthenticated = conn.authenticateWithPassword(username,password);  
	           if (isAuthenticated == false)  
	               throw new IOException("Authentication failed.");  
	             
	           Session sess = conn.openSession();  
	           sess.execCommand("tail -5 /home/zhangjin/share/nohup1.out");
	           //如果使用自定义shell命令则是：sess.execCommand("/home/test.sh");如果是系统shell命令则不需要考虑绝对路径：sess.execCommand("ps aux");  
	           System.out.println("Here is some information about the remote host:");
	           InputStream stdout = new StreamGobbler(sess.getStdout());
	           BufferedReader br = new BufferedReader(new InputStreamReader(stdout));  
	           while (true)  
	           {   
	               String line = br.readLine();  
	               if (line == null)  
	                   break;  
	               System.out.println(line);
	           }  
	           System.out.println("ExitCode: " + sess.getExitStatus());  
	           sess.close();  
	           conn.close(); 
	       }  
	       catch (IOException e)  
	       {  
	           e.printStackTrace(System.err);  
	       }  
	}
}
