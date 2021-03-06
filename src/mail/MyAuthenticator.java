package mail;
import javax.mail.*;   
public class MyAuthenticator extends Authenticator{   
    String userName=null;   
    String password=null;   
        
    public MyAuthenticator(){   
    }   
    public MyAuthenticator(String username, String password) {    
        this.userName = username;    
        this.password = password;    
    }    
    protected PasswordAuthentication getPasswordAuthentication(){   
        return new PasswordAuthentication(userName, password);   
    }   

//下面给出使用上面三个类的代码：

public static void main(String[] args){   
         //这个类主要是设置邮件   
      MailSenderInfo mailInfo = new MailSenderInfo();    
//      mailInfo.setMailServerHost("smtp.163.com");    
      mailInfo.setMailServerHost("mail.zhumengyuan.com");    
      mailInfo.setMailServerPort("587");    
      mailInfo.setValidate(true);    
      mailInfo.setUserName("zhangjin@zhumengyuan.com");    
      mailInfo.setPassword("********");//您的邮箱密码    
      
      mailInfo.setFromAddress("zhangjin@zhumengyuan.com");    
      mailInfo.setToAddress("zhangjin@zhumengyuan.com");    
      mailInfo.setSubject("设置邮箱标题 如http://www.guihua.org 中国桂花网");    
      mailInfo.setContent("设置邮箱内容 如http://www.guihua.org 中国桂花网 是中国最大桂花网站==");    
         //这个类主要来发送邮件   
      SimpleMailSender sms = new SimpleMailSender();   
      sms.sendTextMail(mailInfo);//发送文体格式    
//          sms.sendHtmlMail(mailInfo);//发送html格式   
    }  
}
