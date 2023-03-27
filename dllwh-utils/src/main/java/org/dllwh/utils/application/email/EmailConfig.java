package org.dllwh.utils.application.email;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.mail.EmailException;
import org.dllwh.common.constanst.ConstantHelper;
import org.dllwh.utils.application.email.entity.FolderInfo;
import org.dllwh.utils.application.email.entity.MailDetail;

import com.google.common.collect.Lists;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 邮箱配置工具类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019-08-09 10:28:19 PM
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
abstract class EmailConfig {
	/** 邮件发送服务器的地址 */
	protected String serverHost;
	/** 邮件发送服务器的地址 */
	protected int serverPort = 25;
	/** 邮件发送服务器的用户名 */
	protected String userName;
	/** 登录邮件发送服务器的密码或者授权码 */
	protected String password;
	/** 发件人昵称 */
	protected String nickName;
	/** 邮箱所使用的协议 */
	protected String protocol;
	protected final String CHARSET = ConstantHelper.UTF_8.name();
	/** 是否开始调试模式 */
	protected boolean debug = false;
	/** 服务器是否要验证用户的身份信息 */
	protected boolean auth = true;

	/************************************************************************/
	/******************* 邮件发送 **************************************/
	/************************************************************************/

	/**
	 * @方法描述 : 发送邮件
	 * @param subject     邮件主题
	 * @param content     邮件内容
	 * @param receiver    收件人
	 * @param ccReceiver  抄送人
	 * @param bccReceiver 密送人
	 * @throws MessagingException
	 * @throws EmailException 
	 * @throws AddressException
	 */
	public abstract void send(String subject, String content, String receiver, String ccReceiver, String bccReceiver) throws MessagingException, EmailException;

	/**
	 * @方法描述 : 群发邮件
	 * @param subject      邮件相关信息 - 邮件主题
	 * @param content      邮件相关信息 - 邮件正文text
	 * @param receivers    邮件相关信息 - 接收人列表
	 * @param ccReceivers  邮件相关信息 - 抄送人列表
	 * @param bccReceivers 邮件相关信息 - 密送人列表
	 * @throws MessagingException
	 * @throws EmailException 
	 * @throws AddressException
	 */
	public abstract void send(String subject, String content, String[] receivers, String[] ccReceivers,
			String[] bccReceivers) throws MessagingException, EmailException;

	/**
	 * @方法描述: 发送文本格式或Html格式的Email的方式
	 * @param subject
	 */
	public void sendTextEmail(String subject) {
	}

	/**
	 * @方法描述: 发送HTML格式的邮件
	 * @param subject
	 */
	public void sendHtmlMail(String subject) {
	}

	/**
	 * @方法描述: 发送带附件的电子邮件的应用
	 * @param subject
	 */
	public void sentAttacheEmail(String subject) {
	}

	/************************************************************************/
	/******************* 邮件接收 **************************************/
	/************************************************************************/
	public void receive() {

	}

	/**
	 * @方法描述: public Map<String, Object> showMailDetail()
	 * @return
	 */
	public MailDetail showMailDetail() {
		MailDetail detail = new MailDetail();
		return detail;
	}

	/**
	 * @方法描述: 获取邮件夹列表
	 * @return
	 */
	public List<FolderInfo> getMailFolders() {
		List<FolderInfo> resultList = Lists.newArrayList();
		return resultList;
	}

	/**
	 * @方法描述: 解析邮件
	 */
	public void mailReceiver() {
	}

	/**
	 * @方法描述: 解析内容
	 */
	public void rePart() {
	}

	/************************************************************************/
	/******************* 邮件回复 **************************************/
	/************************************************************************/

	/**
	 * @方法描述 : 邮件回复
	 * @思路: 先获取要回复的邮件，然后获取邮件的各个详情，把详情附加给新建邮件，发送
	 */
	public void reply() {
	}

	/************************************************************************/
	/******************* 邮件删除 **************************************/
	/************************************************************************/
	public void deleteMessage() {

	}

	/************************************************************************/
	/******************* 移动邮件 **************************************/
	/************************************************************************/
	public void moveMessage() {

	}

	/************************************************************************/
	/******************* 复制邮件 **************************************/
	/************************************************************************/
	public void copyMessage() {

	}
}