package org.dllwh.utils.application.email;

import org.dllwh.common.constanst.ConstantHelper;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月8日 上午12:28:44
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public final class JavaMailHelper {
	private final String	CHARSET	= ConstantHelper.UTF_8.name();
	/** 是否开始调试模式 */
	private boolean			debug	= false;
	/** 服务器是否要验证用户的身份信息 */
	private static boolean	auth	= false;
	/** 邮件服务器的IP */
	private String			serverHost;
	/** 登陆邮件服务器的用户名 */
	private String			serverUsername;
	/** 登陆邮件服务器的密码 */
	private String			serverPassword;
	/** 发件人昵称 */
	private String			serverNickName;

	public JavaMailHelper(String serverHost, String serverUsername) {
		super();
		this.serverHost = serverHost;
		this.serverUsername = serverUsername;
	}

	public JavaMailHelper(String serverHost, String serverUsername, String serverPassword) {
		super();
		this.serverHost = serverHost;
		this.serverUsername = serverUsername;
		this.serverPassword = serverPassword;
	}

	public JavaMailHelper(String serverHost, String serverUsername, String serverPassword,
			String serverNickName) {
		super();
		this.serverHost = serverHost;
		this.serverUsername = serverUsername;
		this.serverPassword = serverPassword;
		this.serverNickName = serverNickName;
	}

	public JavaMailHelper(boolean debug, String serverHost, String serverUsername, String serverPassword,
			String serverNickName) {
		super();
		this.debug = debug;
		this.serverHost = serverHost;
		this.serverUsername = serverUsername;
		this.serverPassword = serverPassword;
		this.serverNickName = serverNickName;
	}
}