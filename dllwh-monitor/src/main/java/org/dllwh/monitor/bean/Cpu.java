package org.dllwh.monitor.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取CPU信息
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-30 11:26:54 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class Cpu implements Serializable {

	private static final long serialVersionUID = 9041819692134552654L;
	/** CPU的总量MHz */
	private int mhz;
	/** 获得CPU的卖主，如：Intel */
	private String vendor;
	/** 获得CPU的类别，如：Celeron */
	private String model;
	/** 缓冲存储器数量 */
	private long cacheSize;
	/** CPU核数 */
	private int totalCores;
	/** sockets数 */
	private int totalSockets;
}
