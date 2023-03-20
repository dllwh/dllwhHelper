package org.dllwh.monitor.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取JVM运行时参数及其它信息
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-30 11:54:00 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class JVMRuntime implements Serializable {
	
	private static final long serialVersionUID = -7479402295186627916L;
	/** 返回由引导类加载器用于搜索类文件的引导类路径 */
	private String bootClassPath;
	/** 返回系统类加载器用于搜索类文件的 Java 类路径 */
	private String classPath;
	/** 返回传递给 Java 虚拟机的输入变量，其中不包括传递给 main方法的变量 */
	private List<String> inputArguments;
	/** 返回 Java 库路径 */
	private String libraryPath;
	/** 返回正在运行的 Java 虚拟机实现的管理接口的规范版本 */
	private String managementSpecVersion;
	/** 返回表示正在运行的 Java 虚拟机的名称 */
	private String name;
	/** 返回 Java 虚拟机规范名称 */
	private String specName;
	/** 返回 Java 虚拟机规范供应商 */
	private String specVendor;
	/** 返回 Java 虚拟机规范版本 */
	private String specVersion;
	/** 返回 Java 虚拟机的启动时间（以毫秒为单位） */
	private long startTime;
	/** 返回所有系统属性的名称和值的映射 */
	private Map<String, String> systemProperties;
	/** 返回 Java 虚拟机的正常运行时间（以毫秒为单位） */
	private long uptime;
	/** 返回 Java 虚拟机实现名称 */
	private String vmName;
	/** 返回 Java 虚拟机实现供应商 */
	private String vmVendor;
	/** 返回 Java 虚拟机实现版本 */
	private String vmVersion;
	/** 测试Java虚拟机是否支持由引导类加载器用于搜索类文件的引导类路径机制 */
	private boolean isBootClassPathSupported;
}
