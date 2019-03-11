package org.dllwh.utils.web.sessoion.common;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 系统常量
 * @创建者: 独泪了无痕
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年3月8日 下午9:17:54
 * @版本: V 1.0.1
 * @since: JDK 1.7
 */
public final class GlobalConstant {
	public final static String	JSESSIONID			= "dllwhSessionId";
	public final static String	SESSIONHANDLER		= "sessionHandler";
	public final static String	EXPIRETIME		= "expireTime";
	/** cookie 的过期时间 */
	public final static int		COOKIE_EXPIRE_TIME	= 60 * 60 * 24;
}