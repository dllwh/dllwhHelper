package org.dllwh.monitor.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取JVM类加载信息
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-30 11:31:13 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class JVMClassLoading implements Serializable {
	
	private static final long serialVersionUID = 6228179301688627747L;
	/** 返回当前加载到Java 虚拟机中的类的数量 */
	private long loadedClassCount;
	/** 返回自Java 虚拟机开始执行到目前已经加载的类的总数 */
	private long totalLoadedClassCount;
	/** 返回自Java 虚拟机开始执行到目前已经卸载的类的总数 */
	private long unloadedClassCount;
	/** 测试是否以为类加载系统启用了verbose输出 */
	private boolean isVerbose;
}
