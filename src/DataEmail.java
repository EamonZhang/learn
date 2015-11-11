
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class DataEmail {
	
	public static  void sendDevelopMail(String content) throws EmailException, FileNotFoundException, IOException{
		Email email = new SimpleEmail();
		String host = "mail.zhumengyuan.com";
		email.setHostName(host);
		email.setSmtpPort(587);
//		email.setAuthenticator(new DefaultAuthenticator("zhangjin@zhumengyuan.com", "zhangjin123$"));
		email.setSSLOnConnect(true);
		String datamail = "zhangjin@zhumengyuan.com";
		email.setFrom(datamail);
		email.setSubject("我是谁");
		email.setMsg(content);
		email.addTo("zhangjin@zhumengyuan.com");
//		email.addTo("zhangjin@sunmap.com");
//		email.addCc("zhangjin@sunmap.com");
		email.send();
	}
	public static void main(String[] args) throws FileNotFoundException, EmailException, IOException {
		sendDevelopMail("别问我是谁，请叫我大笨蛋，大笨蛋！");
	}
}