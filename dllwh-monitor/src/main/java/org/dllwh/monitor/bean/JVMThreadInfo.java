package org.dllwh.monitor.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 线程信息
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-30 11:56:15 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class JVMThreadInfo implements Serializable {

	private static final long serialVersionUID = 1941794912309480619L;
	/** 返回与此 ThreadInfo 关联的线程被阻塞进入或重进入监视器的总次数。 */
	private long blockedCount;
	/** 返回自从启用线程争用监视以来，与此 ThreadInfo 关联的线程被阻塞进入或重进入监视器的近似累计时间（以毫秒为单位）。 */
	private long blockedTime;
	/** 返回对象的字符串表示形式，与此 ThreadInfo 关联的线程被锁定并等待该对象。 */
	private String lockName;
	/** 返回拥有对象的线程的 ID，与此 ThreadInfo 关联的线程被阻塞并等待该对象。 */
	private long lockOwnerId;
	/** 返回拥有对象的线程的名称，与此 ThreadInfo 关联的线程被阻塞并等待该对象。 */
	private String lockOwnerName;
	/** 返回与此 ThreadInfo 关联的线程的 ID。 */
	private long threadId;
	/** 返回与此 ThreadInfo 关联的线程的名称。 */
	private String threadName;
	/** 返回与此 ThreadInfo 关联的线程的状态。 */
	private String threadState;
	/** 返回与此 ThreadInfo 关联的线程等待通知的总次数。 */
	private long waitedCount;
	/** 返回自从启用线程争用监视以来,与此 ThreadInfo 关联的线程等待通知的近似累计时间（以毫秒为单位）。 */
	private long waitedTime;
	/** 测试与此 ThreadInfo 关联的线程是否通过 Java 本机接口 (JNI) 执行本机代码。 */
	private boolean isInNative;
	/** 测试与此 ThreadInfo 关联的线程是否被挂起。 */
	private boolean isSuspended;
	/** 该线程的总 CPU 时间（以毫微秒为单位）。 */
	private long threadCpuTime;
	/** 该线程在用户模式中执行的 CPU 时间（以毫微秒为单位） */
	private long threadUserTime;
}
