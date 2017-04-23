package com.brokepal.nighty.core.util;

import sun.net.smtp.SmtpClient;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Administrator on 2016/11/7.
 */
public class SendEmail {
    public static final String HOST = "smtp.163.com";
    public static final String PROTOCOL = "smtp";
    public static final int PORT = 25;
    public static final String FROM = "nightynight_cc@163.com";//发件人的email
    public static final String PWD = "62758cc";//发件人密码


    private static Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);//设置服务器地址
        props.put("mail.store.protocol" , PROTOCOL);//设置协议
        props.put("mail.smtp.port", PORT);//设置端口
        props.put("mail.smtp.auth" , "true");

        Authenticator authenticator = new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PWD);
            }

        };
        Session session = Session.getDefaultInstance(props , authenticator);

        return session;
    }

    public static void send(String toEmail , String content, String subject) throws MessagingException {
        Session session = getSession();
        // Instantiate a message
        Message msg = new MimeMessage(session);

        //Set message attributes
        msg.setFrom(new InternetAddress(FROM));
        InternetAddress[] address = {new InternetAddress(toEmail)};
        msg.setRecipients(Message.RecipientType.TO, address);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setContent(content , "text/html;charset=utf-8");

        //Send the message
        Transport.send(msg);
    }

}
