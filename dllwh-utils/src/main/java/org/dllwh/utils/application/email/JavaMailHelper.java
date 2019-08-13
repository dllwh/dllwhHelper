package org.dllwh.utils.application.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: JavaMail 邮件工具类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月8日 上午12:28:44
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public final class JavaMailHelper extends EmailConfig {
	/** 用于连接邮件服务器的参数配置（发送邮件时才需要用到） */
	private Properties sendProps = new Properties();
	/** 判断是否需要身份认证 */
	private Authenticator authenticator = null;
	/** 基本邮件会话，是Java Mail API的入口 */
	private Session sendMailSession = null;

	public JavaMailHelper(String serverHost, String userName) {
		super();
		this.serverHost = serverHost;
		this.userName = userName;
		init();
	}

	public JavaMailHelper(String serverHost, String userName, String password) {
		super();
		this.serverHost = serverHost;
		this.userName = userName;
		this.password = password;
		init();
	}

	public JavaMailHelper(String serverHost, String userName, String password, String nickName) {
		super();
		this.serverHost = serverHost;
		this.userName = userName;
		this.password = password;
		this.nickName = nickName;
		init();
	}

	public JavaMailHelper(String serverHost, String userName, String password, String nickName, boolean auth) {
		super();
		this.serverHost = serverHost;
		this.userName = userName;
		this.password = password;
		this.auth = auth;
		init();
	}

	public JavaMailHelper(String serverHost, String userName, String password, String nickName, boolean auth,
			boolean debug) {
		super();
		this.debug = debug;
		this.serverHost = serverHost;
		this.userName = userName;
		this.password = password;
		this.nickName = nickName;
		this.auth = auth;
		this.debug = debug;
		init();
	}

	/**
	 * @方法描述: 初始化邮件发送服务器
	 */
	private void init() {
		// 服务器是否验证用户的身份信息
		sendProps.put("mail.smtp.auth", auth);
		sendProps.put("mail.smtp.starttls.enable", true);
		sendProps.put("mail.host", serverHost);
		sendProps.put("mail.port", serverPort);
		sendProps.put("mail.debug", debug);
		if (auth) { // 邮件服务器登录验证
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				}
			};
			// 根据邮件会话属性和密码验证器构造一个发送邮件的session
			sendMailSession = Session.getDefaultInstance(sendProps, authenticator);
		} else {
			sendMailSession = Session.getDefaultInstance(sendProps);
		}
		// 设置为debug模式，可以查看详细的发送日志
		sendMailSession.setDebug(debug);
	}

	/************************************************************************/
	/******************* 邮件发送 ********************************************/
	/************************************************************************/
	/**
	 * @方法描述 : 创建邮件的实例对象
	 * @param subject      邮件主题
	 * @param content      邮件正文
	 * @param receivers    接收人列表
	 * @param ccReceivers  抄送人列表
	 * @param bccReceivers 密送人列表
	 * @return
	 * @throws MessagingException
	 */
	private Message createMimeMessage(String subject, String content, String[] receivers, String[] ccReceivers,
			String[] bccReceivers) throws MessagingException {
		// 根据session创建一个邮件消息
		Message mailMessage = new MimeMessage(sendMailSession);
		// 创建邮件发送者地址
		Address from = null;
		// 设置邮件消息的发送者
		if (StringUtils.isNotEmpty(nickName)) {
			try {
				from = new InternetAddress(userName, nickName, CHARSET);
			} catch (Exception e) {
				from = new InternetAddress(userName);
			}
		} else {
			from = new InternetAddress(userName);
		}
		mailMessage.setFrom(from);

		// 创建邮件的接收者地址，并设置到邮件消息中
		if (ArrayUtils.isNotEmpty(receivers)) {// 收件人
			InternetAddress[] addresses = new InternetAddress[receivers.length];
			for (int i = 0; i < receivers.length; i++) {
				addresses[i] = new InternetAddress(receivers[i]);
			}
			mailMessage.setRecipients(Message.RecipientType.TO, addresses);
		}
		if (ArrayUtils.isNotEmpty(ccReceivers)) {// 抄送人
			InternetAddress[] addresses = new InternetAddress[ccReceivers.length];
			for (int i = 0; i < ccReceivers.length; i++) {
				addresses[i] = new InternetAddress(ccReceivers[i]);
			}
			mailMessage.setRecipients(Message.RecipientType.CC, addresses);
		}
		if (ArrayUtils.isNotEmpty(bccReceivers)) {// 密送人
			InternetAddress[] addresses = new InternetAddress[bccReceivers.length];
			for (int i = 0; i < bccReceivers.length; i++) {
				addresses[i] = new InternetAddress(bccReceivers[i]);
			}
			mailMessage.setRecipients(Message.RecipientType.BCC, addresses);
		}

		// 设置邮件消息的主题
		mailMessage.setSubject(subject);
		// 设置邮件消息发送的时间
		mailMessage.setSentDate(new Date());
		// 设置邮件消息的主要内容
		mailMessage.setText(content);
		// 保存前面的设置
		mailMessage.saveChanges();
		return mailMessage;
	}

	@Override
	public void send(String subject, String textMsg, String receiver, String ccReceiver, String bccReceiver)
			throws MessagingException {
		Message msg = createMimeMessage(subject, textMsg, new String[] { receiver }, new String[] { ccReceiver },
				new String[] { bccReceiver });
		// 发送邮件
		Transport.send(msg);
	}

	@Override
	public void send(String subject, String content, String[] receivers, String[] ccReceivers, String[] bccReceivers)
			throws MessagingException {
		Message msg = createMimeMessage(subject, content, receivers, ccReceivers, bccReceivers);
		// 发送邮件
		Transport.send(msg);
	}
}