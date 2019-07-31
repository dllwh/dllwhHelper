package org.dllwh.utils.application.email;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.dllwh.common.constanst.ConstantHelper;

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
 * @see <a href="">TODO(连接内容简介)</a>
 */
public final class ApacheEmailHelper {
	private static final String	CHARSET	= ConstantHelper.UTF_8.name();

	/** 是否开始调试模式 */
	private boolean				debug	= false;
	/** 邮件服务器的IP */
	private String				serverHost;
	/** 登陆邮件服务器的用户名 */
	private String				serverUsername;
	/** 登陆邮件服务器的密码 */
	private String				serverPassword;
	/** 发件人昵称 */
	private String				serverNickName;

	public ApacheEmailHelper(String serverHost, String serverUsername, String serverPassword) {
		super();
		this.serverHost = serverHost;
		this.serverUsername = serverUsername;
		this.serverPassword = serverPassword;
	}

	public ApacheEmailHelper(String serverHost, String serverUsername, String serverPassword,
			String serverNickName) {
		super();
		this.serverHost = serverHost;
		this.serverUsername = serverUsername;
		this.serverPassword = serverPassword;
		this.serverNickName = serverNickName;
	}

	public ApacheEmailHelper(boolean debug, String serverHost, String serverUsername, String serverPassword,
			String serverNickName) {
		super();
		this.debug = debug;
		this.serverHost = serverHost;
		this.serverUsername = serverUsername;
		this.serverPassword = serverPassword;
		this.serverNickName = serverNickName;
	}

	/**
	 * @方法描述 : 邮箱服务基本配置
	 * @param email
	 * @param subject
	 *            邮件相关信息 - 邮件主题
	 * @param textMsg
	 *            邮件相关信息 - 邮件正文text
	 * @param receivers
	 *            邮件相关信息 - 接收人列表
	 * @param ccReceivers
	 *            邮件相关信息 - 抄送人列表
	 * @param bccReceivers
	 *            邮件相关信息 - 密送人列表
	 * @return
	 * @throws EmailException
	 */
	private Email getEmailConfig(Email email, String subject, String textMsg, String[] receivers,
			String[] ccReceivers, String[] bccReceivers) throws EmailException {

		// 设置服务器地址
		email.setHostName(serverHost);
		// 设置授权帐号和密码
		email.setAuthenticator(new DefaultAuthenticator(serverUsername, serverPassword));
		// 设置邮件主体编码,必须放在前面，否则乱码
		email.setCharset(CHARSET);
		email.setDebug(debug);
		// // 设置发送人邮箱和名字
		if (StringUtils.isNotEmpty(serverNickName)) {
			email.setFrom(serverUsername, serverNickName, CHARSET);
		} else {
			email.setFrom(serverUsername);
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
		email.setBounceAddress(serverUsername);

		return email;
	}

	/**
	 * @方法描述 :发送简单文本邮件
	 * @param fromAddress
	 *            邮件相关信息 - 发件人的地址
	 * @param nickName
	 *            邮件相关信息 - 发件人昵称
	 * @param subject
	 *            邮件相关信息 - 邮件主题
	 * @param textMsg
	 *            邮件相关信息 - 邮件正文text
	 * @param receivers
	 *            邮件相关信息 - 接收人列表
	 * @param ccReceivers
	 *            邮件相关信息 - 抄送人列表
	 * @param bccReceivers
	 *            邮件相关信息 - 密送人列表
	 * @throws EmailException
	 */
	public void sendEmail(String subject, String textMsg, String[] receivers, String[] ccReceivers,
			String[] bccReceivers) throws EmailException {
		Email email = new SimpleEmail();
		getEmailConfig(email, subject, textMsg, receivers, ccReceivers, bccReceivers);
		email.send();
	}
}