package org.dllwh.monitor.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取JVM所在操作系统信息
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-30 11:52:42 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class JVMOperateSystem implements Serializable {

	private static final long serialVersionUID = 4038535302804706926L;
	/** 返回操作系统的架构 */
	private String arch;
	/** 返回 Java 虚拟机可以使用的处理器数目 */
	private int availableProcessors;
	/** 返回操作系统名称 */
	private String name;
	/** 返回最后一分钟内系统加载平均值 */
	private double systemLoadAverage;
	/** 返回操作系统的版本 */
	private String version;
}
