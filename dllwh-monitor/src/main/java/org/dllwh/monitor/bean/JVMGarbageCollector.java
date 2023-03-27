package org.dllwh.monitor.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取JVM垃圾收集信息
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-30 11:32:16 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class JVMGarbageCollector implements Serializable {
	
	private static final long serialVersionUID = 4651083681455136401L;
	private String name;
	/** /返回已发生的回收的总次数 */
	private long collectionCount;
	/** 返回近似的累积回收时间（以毫秒为单位） */
	private long collectionTime;
}
