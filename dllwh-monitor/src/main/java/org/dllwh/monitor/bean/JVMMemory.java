package org.dllwh.monitor.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取JVM内存信息
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-30 11:42:17 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class JVMMemory implements Serializable {
	
	private static final long serialVersionUID = 8743026025640080504L;
	/** 返回用于对象分配的堆的当前内存使用量 */
	private JVMMemoryUsage heapMemoryUsage;
	/** 返回 Java 虚拟机使用的非堆内存的当前内存使用量 */
	private JVMMemoryUsage nonHeapMemoryUsage;
	/** 返回其终止被挂起的对象的近似数目 */
	private Integer objectPendingFinalizationCount;
	/** 测试内存系统的 verbose 输出是否已启用 */
	private boolean isVerbose;
}
