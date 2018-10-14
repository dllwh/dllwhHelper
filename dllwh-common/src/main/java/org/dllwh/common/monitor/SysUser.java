package org.dllwh.common.monitor;

import java.io.Serializable;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取系统用户信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月15日 上午12:02:30
 * @版本: V1.0
 * @since: JDK 1.8
 */
public class SysUser implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 当前系统进程表中的用户名 */
	private String user;
	/** 用户控制台 */
	private String device;
	/** 用户host */
	private String host;
	private long time = 0L;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "SysUser [user=" + user + ", device=" + device + ", host=" + host + ", time=" + time
				+ "]";
	}

}
