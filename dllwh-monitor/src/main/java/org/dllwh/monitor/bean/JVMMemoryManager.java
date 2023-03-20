package org.dllwh.monitor.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取内存管理器信息
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-30 11:46:17 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class JVMMemoryManager implements Serializable {
	
	private static final long serialVersionUID = 5128021547953295682L;
	/** 返回此内存管理器管理的内存池名称 */
	private String[] memoryPoolNames;
	/** 返回表示此内存管理器的名称 */
	private String name;
}
