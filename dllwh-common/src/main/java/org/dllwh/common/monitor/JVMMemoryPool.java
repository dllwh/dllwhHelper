package org.dllwh.common.monitor;

import java.io.Serializable;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取JVM内存池信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月15日 上午12:00:17
 * @版本: V1.0
 * @since: JDK 1.8
 */
public class JVMMemoryPool implements Serializable {
	private static final long serialVersionUID = 1L;
	/** Eden区 GC信息 */
	private JVMMemoryUsage edenMemoryUsage;
	/** Survivor区 GC信息 */
	private JVMMemoryUsage survivorMemoryUsage;
	/** 代码缓冲区 */
	private JVMMemoryUsage codeCacheMemoryUsage;
	/** Perm Gen区 GC信息 */
	private JVMMemoryUsage permGenMemoryUsage;
	/** Old Gen区 GC信息 */
	private JVMMemoryUsage oldGenMemoryUsage;
	/** Metaspace区 GC信息 */
	private JVMMemoryUsage metaspaceMemoryUsage;

	public JVMMemoryUsage getEdenMemoryUsage() {
		return edenMemoryUsage;
	}

	public void setEdenMemoryUsage(JVMMemoryUsage edenMemoryUsage) {
		this.edenMemoryUsage = edenMemoryUsage;
	}

	public JVMMemoryUsage getSurvivorMemoryUsage() {
		return survivorMemoryUsage;
	}

	public void setSurvivorMemoryUsage(JVMMemoryUsage survivorMemoryUsage) {
		this.survivorMemoryUsage = survivorMemoryUsage;
	}

	public JVMMemoryUsage getCodeCacheMemoryUsage() {
		return codeCacheMemoryUsage;
	}

	public void setCodeCacheMemoryUsage(JVMMemoryUsage codeCacheMemoryUsage) {
		this.codeCacheMemoryUsage = codeCacheMemoryUsage;
	}

	public JVMMemoryUsage getPermGenMemoryUsage() {
		return permGenMemoryUsage;
	}

	public void setPermGenMemoryUsage(JVMMemoryUsage permGenMemoryUsage) {
		this.permGenMemoryUsage = permGenMemoryUsage;
	}

	public JVMMemoryUsage getOldGenMemoryUsage() {
		return oldGenMemoryUsage;
	}

	public void setOldGenMemoryUsage(JVMMemoryUsage oldGenMemoryUsage) {
		this.oldGenMemoryUsage = oldGenMemoryUsage;
	}

	public JVMMemoryUsage getMetaspaceMemoryUsage() {
		return metaspaceMemoryUsage;
	}

	public void setMetaspaceMemoryUsage(JVMMemoryUsage metaspaceMemoryUsage) {
		this.metaspaceMemoryUsage = metaspaceMemoryUsage;
	}

	@Override
	public String toString() {
		return "edenMemoryUsage:{" + edenMemoryUsage.toString() + "}\r\n" + "survivorMemoryUsage:{"
				+ survivorMemoryUsage.toString() + "}\r\n" + "codeCacheMemoryUsage:{"
				+ codeCacheMemoryUsage.toString() + "}\r\n" + "permGenMemoryUsage:{"
				+ permGenMemoryUsage.toString() + "}\r\n" + "oldGenMemoryUsage:{"
				+ oldGenMemoryUsage.toString() + "}\r\n" + "metaspaceMemoryUsage:{"
				+ metaspaceMemoryUsage.toString() + "}";
	}

}
