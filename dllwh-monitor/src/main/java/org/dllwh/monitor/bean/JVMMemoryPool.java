package org.dllwh.monitor.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取JVM内存池信息
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-30 11:51:49 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class JVMMemoryPool implements Serializable {
	
	private static final long serialVersionUID = -4565270940736742359L;
	/** Eden区 GC信息 */
	private JVMMemoryUsage edenMemoryUsage;
	/** Survivor区 GC信息 */
	private JVMMemoryUsage survivorMemoryUsage;
	/** 代码缓冲区 */
	private JVMMemoryUsage codeCacheMemoryUsage;
	/** Perm Gen区 GC信息 */
	private JVMMemoryUsage permGenMemoryUsage;
	/** Old Gen区 GC信息 */
	private JVMMemoryUsage oldGenMemoryUsage;
	/** Metaspace区 GC信息 */
	private JVMMemoryUsage metaspaceMemoryUsage;
}
