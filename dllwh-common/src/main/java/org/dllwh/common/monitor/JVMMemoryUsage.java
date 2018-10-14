package org.dllwh.common.monitor;

import java.io.Serializable;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 内存使用
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月15日 上午12:00:31
 * @版本: V1.0
 * @since: JDK 1.8
 */
public class JVMMemoryUsage implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 返回 Java 虚拟机最初从操作系统请求用于内存管理的内存量（以字节为单位） */
	private long init;
	/** 返回已使用的内存量（以字节为单位） */
	private long used;
	/** 返回已提交给 Java 虚拟机使用的内存量（以字节为单位） */
	private long committed;
	/** 返回可以用于内存管理的最大内存量（以字节为单位） */
	private long max;

	public long getInit() {
		return init;
	}

	public void setInit(long init) {
		this.init = init;
	}

	public long getUsed() {
		return used;
	}

	public void setUsed(long used) {
		this.used = used;
	}

	public long getCommitted() {
		return committed;
	}

	public void setCommitted(long committed) {
		this.committed = committed;
	}

	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "JVMMemoryUsage [init=" + init + ", used=" + used + ", committed=" + committed
				+ ", max=" + max + "]";
	}

}
