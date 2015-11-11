package mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
//import javax.servlet.*;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;


public class javaMail {
    private Properties properties;
    private Session mailSession;
    private MimeMessage mailMessage;
    private Transport trans;
    public javaMail() {
    }

    public void sendMail() {
        try {
        	//System.setProperty("javax.net.ssl.trustStore","/home/wxc/q");
            properties = new Properties();
            //è®¾ç½®é‚®ä»¶æœåŠ¡å™¨
            properties.put("mail.smtp.host", "mail.zhumengyuan.com");
            properties.put("mail.smtp.port", "587");
            //éªŒè¯
            properties.put("mail.smtp.auth", "true");
            //properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtps.ssl.trust", "*");
            
            MailSSLSocketFactory socketFactory= new MailSSLSocketFactory();
            socketFactory.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.socketFactory",socketFactory);

//            properties.put("mail.smtp.ssl.checkserveridentity", "true");
//            properties.put("mail.smtp.tls.enable", "true");
            //æ ¹æ®å±žæ€§æ–°å»ºä¸€ä¸ªé‚®ä»¶ä¼šè¯
            mailSession = Session.getInstance(properties,
                                              new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("wenxiaochen@zhumengyuan.com",
                        "wenxiaochen123$");
                }
            });
            mailSession.setDebug(true);
            
            
            //å»ºç«‹æ¶ˆæ¯å¯¹è±¡
            mailMessage = new MimeMessage(mailSession);
            //å‘ä»¶äºº
            mailMessage.setFrom(new InternetAddress("wenxiaochen@zhumengyuan.com"));
            //æ”¶ä»¶äºº
            mailMessage.setRecipient(MimeMessage.RecipientType.TO,
                                 new InternetAddress("zhangjin@zhumengyuan.com"));
            //ä¸»é¢˜
            mailMessage.setSubject("æµ‹è¯•");
            //å†…å®¹
            mailMessage.setText("test"); 
            //å‘ä¿¡æ—¶é—´
            mailMessage.setSentDate(new Date());
            //å­˜å‚¨ä¿¡æ¯
            mailMessage.saveChanges();
            //
            trans = mailSession.getTransport("smtp");
//            trans.connect("mail.zhumengyuan.com", "wenxiaochen@zhumengyuan.com", "wenxiaochen123$");
            //å‘é€
            trans.send(mailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
    
    public static void main(String[] args) {
		new javaMail().sendMail();
	}
}
