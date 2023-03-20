package org.dllwh.monitor.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 内存使用
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-30 11:52:23 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class JVMMemoryUsage implements Serializable {
	
	private static final long serialVersionUID = -1026722119143876720L;
	/** 返回 Java 虚拟机最初从操作系统请求用于内存管理的内存量（以字节为单位） */
	private long init;
	/** 返回已使用的内存量（以字节为单位） */
	private long used;
	/** 返回已提交给 Java 虚拟机使用的内存量（以字节为单位） */
	private long committed;
	/** 返回可以用于内存管理的最大内存量（以字节为单位） */
	private long max;
}
