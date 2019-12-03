package org.dllwh.monitor.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: cpu使用率
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-12-03 8:10:55 AM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Getter
@Setter
public final class CpuPerc {
	/** CPU用户使用率 */
	private double user;
	/** CPU系统使用率 */
	private double sys;
	/** CPU当前等待率 */
	private double wait;
	/** CPU当前错误率 */
	private double nice;
	/** CPU当前空闲率 */
	private double idle;
	/** CPU总的使用率 */
	private double combined;
	/** CPU硬件中断时间 */
	private double irq;
	/** CPU软件中断时间 */
	private double softIrq;
	/** CPU使用内部虚拟机运行任务的时间 */
	private double stolen;

	@Override
	public String toString() {
		return "CPU states: " + format(this.user) + " user, " + format(this.sys) + " system, " + format(this.nice)
				+ " nice, " + format(this.wait) + " wait, " + format(this.idle) + " idle";
	}

	private static String format(double val) {
		String p = String.valueOf(val * 100.0);
		int ix = p.indexOf(".") + 1;
		String percent = p.substring(0, ix) + p.substring(ix, ix + 1);
		return percent + "%";
	}
}
