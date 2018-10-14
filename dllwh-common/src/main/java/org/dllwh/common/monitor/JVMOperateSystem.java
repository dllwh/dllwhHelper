package org.dllwh.common.monitor;

import java.io.Serializable;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取JVM所在操作系统信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月15日 上午12:00:45
 * @版本: V1.0
 * @since: JDK 1.8
 */
public class JVMOperateSystem implements Serializable {
	private static final long serialVersionUID = 1L;
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

	public String getArch() {
		return arch;
	}

	public void setArch(String arch) {
		this.arch = arch;
	}

	public int getAvailableProcessors() {
		return availableProcessors;
	}

	public void setAvailableProcessors(int availableProcessors) {
		this.availableProcessors = availableProcessors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSystemLoadAverage() {
		return systemLoadAverage;
	}

	public void setSystemLoadAverage(double systemLoadAverage) {
		this.systemLoadAverage = systemLoadAverage;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "IFCJVMOperatingSystem [arch=" + arch + ", availableProcessors="
				+ availableProcessors + ", name=" + name + ", systemLoadAverage="
				+ systemLoadAverage + ", version=" + version + "]";
	}
}
