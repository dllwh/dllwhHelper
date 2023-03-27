package org.dllwh.utils.application.email;

import javax.mail.MessagingException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Apache Commons Email
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月8日 上午12:17:36
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public final class ApacheEmailHelper extends EmailConfig {

	public ApacheEmailHelper(String serverHost, String userName, String password) {
		super();
		this.serverHost = serverHost;
		this.userName = userName;
		this.password = password;
	}

	public ApacheEmailHelper(String serverHost, String userName, String password, String nickName) {
		super();
		this.serverHost = serverHost;
		this.userName = userName;
		this.password = password;
		this.nickName = nickName;
	}

	public ApacheEmailHelper(boolean debug, String serverHost, String userName, String password, String nickName) {
		super();
		this.debug = debug;
		this.serverHost = serverHost;
		this.userName = userName;
		this.password = password;
		this.nickName = nickName;
	}

	/**
	 * @方法描述 : 邮箱服务基本配置
	 * @param email
	 * @param subject      邮件相关信息 - 邮件主题
	 * @param textMsg      邮件相关信息 - 邮件正文text
	 * @param receivers    邮件相关信息 - 接收人列表
	 * @param ccReceivers  邮件相关信息 - 抄送人列表
	 * @param bccReceivers 邮件相关信息 - 密送人列表
	 * @return
	 * @throws EmailException
	 */
	private Email getEmailConfig(Email email, String subject, String textMsg, String[] receivers, String[] ccReceivers,
			String[] bccReceivers) throws EmailException {

		// 设置服务器地址
		email.setHostName(serverHost);
		// 设置授权帐号和密码
		email.setAuthenticator(new DefaultAuthenticator(userName, password));
		// 设置邮件主体编码,必须放在前面，否则乱码
		email.setCharset(CHARSET);
		email.setDebug(debug);
		// 设置发送人邮箱和名字
		if (StringUtils.isNotEmpty(nickName)) {
			email.setFrom(userName, nickName, CHARSET);
		} else {
			email.setFrom(userName);
		}

		// 设置邮件标题
		email.setSubject(subject);

		// 设置邮件主体
		if (StringUtils.isNotBlank(textMsg)) {
			email.setMsg(textMsg);
		}
		// 添加收件人地址,可以是多个
		if (ArrayUtils.isNotEmpty(receivers)) {
			email.addTo(receivers);
		}
		// 添加抄送人地址
		if (ArrayUtils.isNotEmpty(ccReceivers)) {
			email.addCc(ccReceivers);
		}
		// 添加密送人地址
		if (ArrayUtils.isNotEmpty(bccReceivers)) {
			email.addBcc(bccReceivers);
		}

		// 添加回复人地址(暂未验证)
		// email.addReplyTo("1349310440@qq.com");

		// 增加需要回执的标记(暂未验证)
		// email.addHeader("Disposition-Notification-To", "1");
		// 通常情况下，不能投递给收件者的邮件将会退回给发件人,，只需调用setBounceAddress 进行 退回邮件处理
		email.setBounceAddress(userName);

		return email;
	}

	/**
	 * @方法描述 :发送简单文本邮件
	 * @param fromAddress  邮件相关信息 - 发件人的地址
	 * @param nickName     邮件相关信息 - 发件人昵称
	 * @param subject      邮件相关信息 - 邮件主题
	 * @param textMsg      邮件相关信息 - 邮件正文text
	 * @param receivers    邮件相关信息 - 接收人列表
	 * @param ccReceivers  邮件相关信息 - 抄送人列表
	 * @param bccReceivers 邮件相关信息 - 密送人列表
	 * @throws EmailException
	 */
	@Override
	public void send(String subject, String content, String receiver, String ccReceiver, String bccReceiver)
			throws MessagingException, EmailException {
		Email email = new SimpleEmail();
		getEmailConfig(email, subject, content, new String[] { receiver }, new String[] { ccReceiver },
				new String[] { bccReceiver });
		email.send();
	}

	@Override
	public void send(String subject, String content, String[] receivers, String[] ccReceivers, String[] bccReceivers)
			throws MessagingException, EmailException {
		Email email = new SimpleEmail();
		getEmailConfig(email, subject, content, receivers, ccReceivers, bccReceivers);
		email.send();
	}
}