package org.dllwh.monitor.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取系统用户信息
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-30 11:59:34 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class SysUser implements Serializable {
	
	private static final long serialVersionUID = -3636658725511905232L;
	/** 当前系统进程表中的用户名 */
	private String user;
	/** 用户控制台 */
	private String device;
	/** 用户host */
	private String host;
	private long time = 0L;
}
