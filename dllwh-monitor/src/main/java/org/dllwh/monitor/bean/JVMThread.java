package org.dllwh.monitor.bean;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取JVM线程相关信息
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-30 11:54:31 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class JVMThread implements Serializable {
	
	private static final long serialVersionUID = 7073692185296806634L;
	/** 查找因为等待获得对象监视器或可拥有同步器而处于死锁状态的线程循环 */
	private long[] findDeadlockedThreads;
	/** 找到处于死锁状态（等待获取对象监视器）的线程的周期。 */
	private long[] findMonitorDeadlockedThreads;
	/** 返回活动线程 ID。 */
	private long[] allThreadIds;
	/** 返回当前线程的总 CPU 时间（以毫微秒为单位）。 */
	private long currentThreadCpuTime;
	/** 返回当前线程在用户模式中执行的 CPU 时间（以毫微秒为单位）。 */
	private long currentThreadUserTime;
	/** 返回活动守护线程的当前数目。 */
	private int daemonThreadCount;
	/** 返回自从 Java 虚拟机启动或峰值重置以来峰值活动线程计数。 */
	private int peakThreadCount;
	/** 返回活动线程的当前数目，包括守护线程和非守护线程。 */
	private int threadCount;
	/** 返回自从 Java 虚拟机启动以来创建和启动的线程总数目。 */
	private long totalStartedThreadCount;
	/** 测试 Java 虚拟机是否支持当前线程的 CPU时间测量。 */
	private boolean isCurrentThreadCpuTimeSupported;
	/** 测试 Java 虚拟机是否支持使用对象监视器的监视。 */
	private boolean isObjectMonitorUsageSupported;
	/** 测试 Java 虚拟机是否支持使用可拥有同步器的监视。 */
	private boolean isSynchronizerUsageSupported;
	/** 测试是否启用了线程争用监视。 */
	private boolean isThreadContentionMonitoringEnabled;
	/** 测试 Java虚拟机是否支持线程争用监视。 */
	private boolean isThreadContentionMonitoringSupported;
	/** 测试是否启用了线程 CPU 时间测量。 */
	private boolean isThreadCpuTimeEnabled;
	/** 测试 Java 虚拟机实现是否支持任何线程的 CPU时间测量。 */
	private boolean isThreadCpuTimeSupported;

	private Map<String, JVMThreadInfo> threadInfoMap;

	// 根据id获取ThreadInfo
	public JVMThreadInfo getThreadInfo(long id) {
		JVMThreadInfo ifcThreadInfo = threadInfoMap.get(id + "");
		return ifcThreadInfo;
	}

	// 获取所有的IFCJVMThreadInfo信息
	@JSONField(serialize = false)
	public JVMThreadInfo[] getThreadInfos() {
		Set<String> keys = threadInfoMap.keySet();
		JVMThreadInfo[] ifcjvmThreadInfos = null;
		if (keys != null) {
			ifcjvmThreadInfos = new JVMThreadInfo[keys.size()];
			int count = 0;
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				ifcjvmThreadInfos[count] = threadInfoMap.get(key);
				count++;
			}
		}
		return ifcjvmThreadInfos;
	}
}
