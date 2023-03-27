package org.dllwh.utils.application.email.test;

import org.apache.commons.lang3.*;
import org.dllwh.utils.application.email.model.MailSenderInfo;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.*;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: JavaMail 邮件工具类
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2023-03-20 01:33
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public class SendEmailHelper {
    private final static String CHARSET = "UTF-8";

    /**
     * 根据参数配置，创建用于连接邮件服务器的参数配置，发送邮件时才需要用到
     */
    private static Properties getProperties(MailSenderInfo mailSenderInfo) {
        Properties properties = new Properties();
        // 默认的邮件传输协议
        properties.setProperty("mail.transport.protocol", mailSenderInfo.getTransportType());
        // 默认的存储邮件协议
        properties.setProperty("mail.store.protocol", mailSenderInfo.getStoreType());
        // 设置邮件服务器主机名
        properties.put("mail.host", mailSenderInfo.getMailServerHost());
        properties.put("mail.port", mailSenderInfo.getMailServerPort());
        properties.put("mail.user", mailSenderInfo.getMailServerUser());
        // 设置是否安全验证,默认为false,一般情况都设置为true
        properties.put("mail.smtp.auth", mailSenderInfo.isValidate());
        return properties;
    }

    /**
     * 获得会话 Session
     */
    private static Session getSession(MailSenderInfo mailSenderInfo) {
        Properties properties = getProperties(mailSenderInfo);
        // 基本邮件会话，是Java Mail API的入口
        Session sendMailSession;
        Authenticator authenticator = null;
        // 判断是否需要身份认证
        if (mailSenderInfo.isValidate()) {
            // 如果需要身份认证，则创建一个密码验证器
            authenticator = new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailSenderInfo.getMailServerUser(), mailSenderInfo.getMailServerPassword());
                }
            };
        }

        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        sendMailSession = Session.getDefaultInstance(properties, authenticator);

        // 若是设置为debug模式，可以查看详细的发送日志
        sendMailSession.setDebug(mailSenderInfo.isDebugMode());
        return sendMailSession;
    }

    /**
     * 使用 JavaMail 以文本格式发送邮件
     *
     * @param mailSenderInfo 待发送的邮件信息
     */
    public static void sendTextEmail(MailSenderInfo mailSenderInfo) throws MessagingException, UnsupportedEncodingException {
        // 1. 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session session = getSession(mailSenderInfo);

        // 2. 根据session创建一个邮件对象
        Message mailMessage = new MimeMessage(session);

        // 3. From: 发件人。其中 InternetAddress 的三个参数分别为: 邮箱、显示的昵称(只用于显示, 没有特别的要求)、昵称
        mailMessage.setFrom(new InternetAddress(mailSenderInfo.getMailServerUser(), mailSenderInfo.getNickName(), CHARSET));

        // 4. To: 收件人
        mailMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mailSenderInfo.getReceiver()));

        // 5. To: 抄送收件人
        if (StringUtils.isNotBlank(mailSenderInfo.getCcReceiver())) {
            mailMessage.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(mailSenderInfo.getCcReceiver()));
        }

        // 6. To: 密送收件人
        if (StringUtils.isNotBlank(mailSenderInfo.getBccReceiver())) {
            mailMessage.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(mailSenderInfo.getBccReceiver()));
        }

        // 7. 设置邮件消息的主题
        mailMessage.setSubject(mailSenderInfo.getSubject());

        // 8. 设置邮件消息的主要内容
        mailMessage.setContent(mailSenderInfo.getContent(), "text/html;charset=UTF-8");

        // 9. 设置邮件消息发送的时间
        mailMessage.setSentDate(new Date());

        // 10. 保存前面的设置
        mailMessage.saveChanges();

        // 11. 发送使用传输对象的消息
        Transport.send(mailMessage);
    }

    /**
     * 使用 JavaMail 群发邮件
     *
     * @param mailSenderInfo 待发送的邮件信息
     */
    public void sendTextEmails(MailSenderInfo mailSenderInfo) throws MessagingException, UnsupportedEncodingException {
        // 1. 创建一封邮件，获取一个Session
        Session session = getSession(mailSenderInfo);

        // 2. 创建邮件对象
        Message mailMessage = new MimeMessage(session);

        // 3. From: 发件人。其中 InternetAddress 的三个参数分别为: 邮箱、显示的昵称(只用于显示, 没有特别的要求)、昵称的字符集编码
        mailMessage.setFrom(new InternetAddress(mailSenderInfo.getMailServerUser(), mailSenderInfo.getNickName(), CHARSET));

        // 4. To: 收件人
        String[] receivers = mailSenderInfo.getReceivers();
        if (ArrayUtils.isNotEmpty(receivers)) {
            InternetAddress[] addresses = new InternetAddress[receivers.length];
            for (int i = 0; i < receivers.length; i++) {
                addresses[i] = new InternetAddress(receivers[i]);
            }
            mailMessage.setRecipients(MimeMessage.RecipientType.TO, addresses);
        }

        // 5. To: 抄送收件人
        String[] ccReceivers = mailSenderInfo.getCcReceivers();
        if (ArrayUtils.isNotEmpty(ccReceivers)) {
            InternetAddress[] addresses = new InternetAddress[receivers.length];
            for (int i = 0; i < receivers.length; i++) {
                addresses[i] = new InternetAddress(receivers[i]);
            }
            mailMessage.setRecipients(MimeMessage.RecipientType.CC, addresses);
        }

        // 6. To: 密送收件人
        String[] bccReceivers = mailSenderInfo.getBccReceivers();
        if (ArrayUtils.isNotEmpty(bccReceivers)) {
            InternetAddress[] addresses = new InternetAddress[receivers.length];
            for (int i = 0; i < receivers.length; i++) {
                addresses[i] = new InternetAddress(receivers[i]);
            }
            mailMessage.setRecipients(MimeMessage.RecipientType.BCC, addresses);
        }

        // 7. 设置邮件的主题
        mailMessage.setSubject(mailSenderInfo.getSubject());

        // 8. 设置邮件的内容
        mailMessage.setContent(mailSenderInfo.getContent(), "text/html;charset=UTF-8");

        // 9. 设置显示的发送时间
        mailMessage.setSentDate(new Date());

        // 10. 保存前面的设置
        mailMessage.saveChanges();

        // 11. 发送使用传输对象的消息
        Transport.send(mailMessage);
    }

    /**
     * 使用 JavaMail 以HTML格式发送邮件
     *
     * @param mailSenderInfo 待发送的邮件信息
     */
    public static void sendHtmlMail(MailSenderInfo mailSenderInfo) throws UnsupportedEncodingException, MessagingException {
        // 1. 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session session = getSession(mailSenderInfo);

        // 2. 根据session创建一个邮件消息
        Message mailMessage = new MimeMessage(session);

        //  3. From: 发件人。其中 InternetAddress 的三个参数分别为: 邮箱、显示的昵称(只用于显示, 没有特别的要求)、昵称的字符集编码
        mailMessage.setFrom(new InternetAddress(mailSenderInfo.getMailServerUser(), mailSenderInfo.getNickName(), CHARSET));

        // 4. To: 收件人
        mailMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mailSenderInfo.getReceiver()));

        // 5. To: 抄送收件人
        if (StringUtils.isNotBlank(mailSenderInfo.getCcReceiver())) {
            mailMessage.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(mailSenderInfo.getCcReceiver()));
        }

        // 6. To: 密送收件人
        if (StringUtils.isNotBlank(mailSenderInfo.getBccReceiver())) {
            mailMessage.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(mailSenderInfo.getBccReceiver()));
        }

        // 7. 设置邮件的主题
        mailMessage.setSubject(mailSenderInfo.getSubject());

        // 8. 设置邮件的内容
        mailMessage.setContent(mailSenderInfo.getContent(), "text/html;charset=UTF-8");

        // 9. 设置显示的发送时间
        mailMessage.setSentDate(new Date());

        // 10. 保存前面的设置
        mailMessage.saveChanges();

        // 11. 发送使用传输对象的消息
        Transport.send(mailMessage);
    }

    /**
     * 使用 JavaMail 发送带附件的邮件
     *
     * @param mailSenderInfo 待发送的邮件信息
     */
    public static void sendAttachEmail(MailSenderInfo mailSenderInfo) throws MessagingException, UnsupportedEncodingException {
        // 1. 创建一封邮件，获取一个Session
        Session session = getSession(mailSenderInfo);

        // 2. 创建邮件对象
        MimeMessage message = new MimeMessage(session);

        // 3. From: 发件人。其中 InternetAddress 的三个参数分别为: 邮箱、显示的昵称(只用于显示, 没有特别的要求)、昵称的字符集编码
        message.setFrom(new InternetAddress(mailSenderInfo.getMailServerUser(), mailSenderInfo.getNickName(), CHARSET));

        // 4. To: 收件人
        String[] receivers = mailSenderInfo.getReceivers();
        if (ArrayUtils.isNotEmpty(receivers)) {
            InternetAddress[] addresses = new InternetAddress[receivers.length];
            for (int i = 0; i < receivers.length; i++) {
                addresses[i] = new InternetAddress(receivers[i]);
            }
            message.setRecipients(MimeMessage.RecipientType.TO, addresses);
        }

        // 5. To: 抄送收件人
        String[] ccReceivers = mailSenderInfo.getCcReceivers();
        if (ArrayUtils.isNotEmpty(ccReceivers)) {
            InternetAddress[] addresses = new InternetAddress[receivers.length];
            for (int i = 0; i < receivers.length; i++) {
                addresses[i] = new InternetAddress(receivers[i]);
            }
            message.setRecipients(MimeMessage.RecipientType.CC, addresses);
        }

        // 6. To: 密送收件人
        String[] bccReceivers = mailSenderInfo.getBccReceivers();
        if (ArrayUtils.isNotEmpty(bccReceivers)) {
            InternetAddress[] addresses = new InternetAddress[receivers.length];
            for (int i = 0; i < receivers.length; i++) {
                addresses[i] = new InternetAddress(receivers[i]);
            }
            message.setRecipients(MimeMessage.RecipientType.BCC, addresses);
        }

        // 7. 设置邮件的主题
        message.setSubject(mailSenderInfo.getSubject(), CHARSET);

        // 8. 创建一个MimeMultipart的对象
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(mailSenderInfo.getContent());
        messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(mailSenderInfo.getFilePath());
        // 设置dhFile附件处理
        messageBodyPart.setDataHandler(new DataHandler(source));
        // 设置中文附件名称
        messageBodyPart.setFileName(mailSenderInfo.getFilePath());

        // 9. 设置邮件的内容
        Multipart multipart = new MimeMultipart();
        // 把附件资源混合到 Multipart 多资源邮件模块里
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);

        // 10. 设置显示的发送时间
        message.setSentDate(new Date());

        // 11. 保存前面的设置
        message.saveChanges();

        // 12. 发送使用传输对象的消息
        Transport.send(message);
    }

    /**
     * 使用 JavaMail 发送内嵌图像中的邮件
     *
     * @param mailSenderInfo 待发送的邮件信息
     */
    public static void sendImageEmail(MailSenderInfo mailSenderInfo) throws UnsupportedEncodingException, MessagingException {
        // 1. 创建一封邮件，获取一个Session
        Session session = getSession(mailSenderInfo);

        // 2. 创建邮件对象
        MimeMessage message = new MimeMessage(session);

        // 3. From: 发件人。其中 InternetAddress 的三个参数分别为: 邮箱、显示的昵称(只用于显示, 没有特别的要求)、昵称的字符集编码
        message.setFrom(new InternetAddress(mailSenderInfo.getMailServerUser(), mailSenderInfo.getNickName(), CHARSET));

        // 4. To: 收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mailSenderInfo.getReceiver()));

        // 5. To: 抄送收件人
        if (StringUtils.isNotBlank(mailSenderInfo.getCcReceiver())) {
            message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(mailSenderInfo.getCcReceiver()));
        }

        // 6. To: 密送收件人
        if (StringUtils.isNotBlank(mailSenderInfo.getBccReceiver())) {
            message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(mailSenderInfo.getBccReceiver()));
        }

        // 7. 设置邮件的主题
        message.setSubject(mailSenderInfo.getSubject(), CHARSET);
        MimeMultipart multipart = new MimeMultipart("related");

        // 8. 创建一个MimeMultipart的对象
        BodyPart messageBodyPart = new MimeBodyPart();
        String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
        messageBodyPart.setContent(htmlText, "text/html");
        multipart.addBodyPart(messageBodyPart);

        // 9. 设置邮件的内容
        messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource(mailSenderInfo.getImagePath());
        // //设置dhImg图片处理
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID", "<image>");
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);

        // 10. 设置显示的发送时间
        message.setSentDate(new Date());

        // 11. 保存前面的设置
        message.saveChanges();

        // 12. 发送使用传输对象的消息
        Transport.send(message);
    }

    public static void main(String[] args) throws Exception {
        MailSenderInfo mailConfigInfo = new MailSenderInfo();
        mailConfigInfo.setMailServerHost("smtp.sina.com");
        mailConfigInfo.setMailServerUser("dllwhcrawler@sina.com");
        mailConfigInfo.setMailServerPassword("4f38d72caa78ffec");
        mailConfigInfo.setReceiver("duleilewuhen@sina.com");
        mailConfigInfo.setValidate(true);
        mailConfigInfo.setSubject("TEST邮件主题");
        mailConfigInfo.setContent("TEST这是邮件正文。。。");
        mailConfigInfo.setFilePath("/Users/dllwh/Desktop/redis.sh");
        mailConfigInfo.setImagePath("/Users/dllwh/Downloads/禅.jpeg");
    }
}

