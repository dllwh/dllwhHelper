package org.dllwh.utils.application.email.test;

import org.apache.commons.lang3.*;
import org.dllwh.utils.application.email.model.*;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: 邮件接收
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2023-03-20 23:18
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public class ReceiveEmailHelper {

    /**
     * 根据参数配置，创建会话对象，用于连接邮件服务器的参数配置，发送邮件时才需要用到
     */
    private static Properties getProperties(BaseMailConfig mailConfigInfo) {
        Properties properties = new Properties();
        // 默认的邮件传输协议
        properties.setProperty("mail.transport.protocol", mailConfigInfo.getTransportType());
        // 默认的存储邮件协议
        properties.setProperty("mail.store.protocol", mailConfigInfo.getStoreType());
        // 设置邮件服务器主机名
        properties.put("mail.host", mailConfigInfo.getMailServerHost());
        properties.put("mail.port", mailConfigInfo.getMailServerPort());
        properties.put("mail.user", mailConfigInfo.getMailServerUser());
        // 设置是否安全验证,默认为false,一般情况都设置为true
        properties.put("mail.smtp.auth", mailConfigInfo.isValidate());
        return properties;
    }


    /**
     * 获得会话 Session
     */
    private static Session getSession(BaseMailConfig mailConfigInfo) {
        Properties properties = getProperties(mailConfigInfo);
        // 基本邮件会话，是Java Mail API的入口
        Session sendMailSession;
        Authenticator authenticator = null;

        // 判断是否需要身份认证
        if (mailConfigInfo.isValidate()) {
            // 如果需要身份认证，则创建一个密码验证器
            authenticator = new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailConfigInfo.getMailServerUser(), mailConfigInfo.getMailServerPassword());
                }
            };
        }

        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        sendMailSession = Session.getDefaultInstance(properties, authenticator);

        // 若是设置为debug模式，可以查看详细的发送日志
        sendMailSession.setDebug(mailConfigInfo.isDebugMode());
        return sendMailSession;
    }

    /**
     * 获取邮箱的基本邮件信息
     *
     * @param folder 收件箱对象
     * @return 返回邮箱基本信息
     */
    private static Map<String, Integer> emailInfo(Folder folder) throws MessagingException {
        Map<String, Integer> emailInfo = new HashMap<>();
        // 未读邮件数。由于POP3协议无法获知邮件的状态,所以getUnreadMessageCount得到的是收件箱的邮件总数
        emailInfo.put("unreadMessageCount", folder.getUnreadMessageCount());
        // 删除邮件数。由于POP3协议无法获知邮件的状态,所以下面(删除、新邮件)得到的结果始终都是为0
        emailInfo.put("deletedMessageCount", folder.getDeletedMessageCount());
        // 新邮件
        emailInfo.put("newMessageCount", folder.getNewMessageCount());
        // 获得收件箱中的邮件总数
        emailInfo.put("messageCount", folder.getMessageCount());
        return emailInfo;
    }


    /***
     * 获得邮件主题
     */
    private static String getSubject(MimeMessage msg) throws UnsupportedEncodingException, MessagingException {
        return decodeText(msg.getSubject());
    }

    /***
     * 获得邮件发件人
     */
    private static String getFrom(MimeMessage msg) throws MessagingException, UnsupportedEncodingException {
        Address[] froms = msg.getFrom();
        if (froms.length < 1) {
            throw new MessagingException("没有发件人!");
        }

        InternetAddress address = (InternetAddress) froms[0];
        String person = address.getPersonal();
        person = (person != null) ? decodeText(person) + " " : "";
        return person + "<" + address.getAddress() + ">";
    }

    /***
     * 根据收件人类型，获取邮件收件人、抄送和密送地址。如果收件人类型为空，则获得所有的收件人
     * type可选值
     * <p>Message.RecipientType.TO  收件人</p>
     * <p>Message.RecipientType.CC  抄送</p>
     * <p>Message.RecipientType.BCC 密送</p>
     * @param msg  邮件内容
     * @param type 收件人类型
     * @return 收件人1 <邮件地址1>, 收件人2 <邮件地址2>, ...
     */
    private static String getReceiveAddress(Message msg, Message.RecipientType type) throws MessagingException {
        StringBuilder recipientAddress = new StringBuilder();
        Address[] address;
        if (type == null) {
            address = msg.getAllRecipients();
        } else {
            address = msg.getRecipients(type);
        }

        if (address == null || address.length < 1) {
            if (type == null) {
                throw new MessagingException("没有收件人!");
            } else if ("cc".equalsIgnoreCase(type.toString())) {
                throw new MessagingException("没有抄送人!");
            } else if ("bcc".equalsIgnoreCase(type.toString())) {
                throw new MessagingException("没有密送人!");
            }
        }

        for (Address add : Objects.requireNonNull(address)) {
            InternetAddress internetAddress = (InternetAddress) add;
            recipientAddress.append(internetAddress.toUnicodeString()).append(",");
        }
        //删除最后一个逗号
        recipientAddress.deleteCharAt(recipientAddress.length() - 1);
        return recipientAddress.toString();
    }

    /***
     * 获得邮件发送时间
     * @param msg 邮件内容
     * @return 默认返回：yyyy年mm月dd日 星期X HH:mm
     */
    private static String getSentDate(Message msg, String pattern) throws MessagingException {
        Date receivedDate = msg.getSentDate();
        if (receivedDate == null) {
            return "";
        }
        pattern = StringUtils.isNotBlank(pattern) ? "yyyy年MM月dd日 E HH:mm " : pattern;
        return new SimpleDateFormat(pattern).format(receivedDate);
    }

    /***
     * 获得邮件接收时间
     * @param msg 邮件内容
     * @return 默认返回：yyyy年mm月dd日 星期X HH:mm
     */
    private static String getReceivedDate(Message msg, String pattern) throws MessagingException {
        Date receivedDate = msg.getReceivedDate();
        if (receivedDate == null) {
            return "";
        }
        pattern = StringUtils.isNotBlank(pattern) ? "yyyy年MM月dd日 E HH:mm " : pattern;
        return new SimpleDateFormat(pattern).format(receivedDate);
    }

    /***
     * 判断邮件是否已读  www.2cto.com
     * @param msg 邮件内容
     * @return 如果邮件已读返回true, 否则返回false
     */
    private static boolean isSeen(Message msg) throws MessagingException {
        return msg.getFlags().contains(Flags.Flag.SEEN);
    }

    /***
     * 判断邮件是否需要阅读回执
     * @param msg 邮件内容
     * @return 需要回执返回true, 否则返回false
     */
    private static boolean isReplySign(Message msg) throws MessagingException {
        return ArrayUtils.isNotEmpty(msg.getHeader("Disposition-Notification-To"));
    }

    /***
     * 获得邮件的优先级
     * @param msg 邮件内容
     * @return 1(High):紧急  3:普通(Normal)  5:低(Low)
     */
    private static String getPriority(Message msg) throws MessagingException {
        String priority = "普通";
        String[] headers = msg.getHeader("X-Priority");
        if (headers != null) {
            String headerPriority = headers[0];
            if (headerPriority.contains("1") || headerPriority.contains("High")) {
                priority = "紧急";
            } else if (headerPriority.contains("5") || headerPriority.contains("Low")) {
                priority = "低";
            } else {
                priority = "普通";
            }
        }
        return priority;
    }

    /***
     * 获得邮件文本内容
     * @param part    邮件体
     * @param content 存储邮件文本内容的字符串
     */
    private static void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {
        //如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断
        boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;
        if (part.isMimeType("text/*") && !isContainTextAttach) {
            content.append(part.getContent().toString());
        } else if (part.isMimeType("message/rfc822")) {
            getMailTextContent((Part) part.getContent(), content);
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                getMailTextContent(bodyPart, content);
            }
        }
    }

    /***
     * 文本解码
     * @param encodeText 解码MimeUtility.encodeText(String text)方法编码后的文本
     * @return 解码后的文本
     */
    private static String decodeText(String encodeText) throws UnsupportedEncodingException {
        if (encodeText == null || "".equals(encodeText)) {
            return "";
        } else {
            return MimeUtility.decodeText(encodeText);
        }
    }

    /***
     * 判断邮件中是否包含附件 （Part为Message接口）,邮件中存在附件返回true，不存在返回false
     */
    private static boolean isContainAttachment(Part part) throws MessagingException, IOException {
        boolean flag = false;
        if (part.isMimeType("multipart/*")) {
            MimeMultipart multipart = (MimeMultipart) part.getContent();
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                String disposition = bodyPart.getDisposition();
                if (disposition != null && (disposition.equalsIgnoreCase(Part.ATTACHMENT) || disposition.equalsIgnoreCase(Part.INLINE))) {
                    flag = true;
                } else if (bodyPart.isMimeType("multipart/*")) {
                    flag = isContainAttachment(bodyPart);
                } else {
                    String contentType = bodyPart.getContentType();
                    if (contentType.contains("application")) {
                        flag = true;
                    }
                    if (contentType.contains("name")) {
                        flag = true;
                    }
                }
                if (flag) {
                    break;
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            flag = isContainAttachment((Part) part.getContent());
        }
        return flag;
    }

    /**
     * 使用 JavaMail 接收邮件
     */
    public static void recipientMail(MailReceiveInfo mailReceiveInfo) throws MessagingException, IOException {
        // 1. 创建一封邮件，获取一个Session
        Session session = getSession(mailReceiveInfo);

        // 2. 获取Store对象
        Store emailStore = session.getStore(mailReceiveInfo.getStoreType());
        // POP3服务器登录认证，user我们在properties中已经指定默认
        emailStore.connect(mailReceiveInfo.getMailServerUser(), mailReceiveInfo.getMailServerPassword());

        // 3. 获取收件箱内容：（电子邮件）收件箱  folder：邮件夹
        Folder emailFolder = emailStore.getFolder("INBOX");

        // 4. 设置对邮件帐户的访问权限。 Folder.READ_ONLY (只读或者1)、Folder.READ_WRITE(只写或者2)
        emailFolder.open(Folder.READ_ONLY);

        // 获取邮箱基本信息
        System.out.println(emailInfo(emailFolder));

        // 5. 获取收件箱中的所有邮件
        Message[] messages = emailFolder.getMessages();

        // 解析所有邮件
        for (Message message : messages) {
            System.out.println("------------------解析第" + message.getMessageNumber() + "封邮件-------------------- ");
            System.out.println("主题: " + MimeUtility.decodeText(message.getSubject()));
            System.out.println("发件人: " + InternetAddress.toString(message.getFrom()));
            System.out.println("收件人：" + getReceiveAddress(message, Message.RecipientType.TO));
            System.out.println("邮件正文: " + message.getContent().toString());
            System.out.println("接收时间：" + getReceivedDate(message, "yyyy-MM-dd HH:mm:ss"));
            System.out.println("发送时间：" + getSentDate(message, "yyyy-MM-dd HH:mm:ss"));
            System.out.println("是否已读：" + isSeen(message));
            System.out.println("是否需要回执：" + isReplySign(message));
            System.out.println("邮件大小：" + message.getSize() * 1024 + "kb");
            System.out.println("邮件优先级：" + getPriority(message));
            System.out.println("是否包含附件：" + isContainAttachment(message));
            //用来存储正文的对象
            StringBuffer content = new StringBuffer();
            getMailTextContent(message, content);
            System.out.println("邮件正文：" + content);
            System.out.println("-----------第" + message.getMessageNumber() + "封邮件解析结束------------");
            System.out.println();
        }
        // 6. 关闭邮件夹对象
        emailFolder.close(false);
        // 7. 关闭连接对象
        emailStore.close();
    }

    public static void main(String[] args) throws MessagingException, IOException {
        MailReceiveInfo mailConfigInfo = new MailReceiveInfo();
        mailConfigInfo.setMailServerHost("imap.sina.com");
        mailConfigInfo.setMailServerUser("dllwhcrawler@sina.com");
        mailConfigInfo.setMailServerPassword("4f38d72caa78ffec");
        mailConfigInfo.setValidate(true);

        ReceiveEmailHelper.recipientMail(mailConfigInfo);
    }
}
