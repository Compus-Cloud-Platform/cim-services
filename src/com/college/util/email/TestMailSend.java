package com.college.util.email;

public class TestMailSend {

	public static void main(String[] args){   
        //这个类主要是设置邮件   
     MailSenderInfo mailInfo = new MailSenderInfo();    
     mailInfo.setMailServerHost("smtp.sina.com");    
     mailInfo.setMailServerPort("25");    
     mailInfo.setValidate(true);    
     mailInfo.setUserName("colleage_service@sina.com");    
     mailInfo.setPassword("colleage");//您的邮箱密码    
     mailInfo.setFromAddress("colleage_service@sina.com");    
     mailInfo.setToAddress("906702424@qq.com");    
     mailInfo.setSubject("重置密码");    
     mailInfo.setContent("你的验证码为");    
        //这个类主要来发送邮件   
     SimpleMailSender sms = new SimpleMailSender();   
         sms.sendTextMail(mailInfo);//发送文体格式    
         //sms.sendHtmlMail(mailInfo);//发送html格式   
   }  
}
