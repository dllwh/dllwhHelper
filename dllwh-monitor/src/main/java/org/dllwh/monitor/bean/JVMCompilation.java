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
 * @创建时间: 2019-11-30 11:31:48 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class JVMCompilation implements Serializable {
	
	private static final long serialVersionUID = -6187593348464954598L;
	/** 返回即时(JIT)编辑器的名称 */
	private String name;
	/** 返回在编译上花费的累积耗费时间的近似值（以毫秒为单位） */
	private long totalCompilationTime;
	/** 测试 Java虚拟机是否支持监视编译时间 */
	private boolean isCompilationTimeMonitoringSupported;
}
