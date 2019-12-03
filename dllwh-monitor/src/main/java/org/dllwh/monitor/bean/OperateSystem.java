package org.dllwh.monitor.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取操作系统信息
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-30 11:58:26 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class OperateSystem implements Serializable {

	private static final long serialVersionUID = 337852982668379247L;
	/** 操作系统类型名 */
	private String name;
	/** 操作系统的版本号 */
	private String version;
	/** 操作系统 */
	private String arch;
	private String machine;
	/** 操作系统的描述 */
	private String description;
	private String patchLevel;
	/** 操作系统的卖主 */
	private String vendor;
	/** 操作系统卖主类型 */
	private String vendorVersion;
	/** 操作系统名称 */
	private String vendorName;
	/** 操作系统的卖主名 */
	private String vendorCodeName;
	/** 操作系统DataModel() */
	private String dataModel;
	/** 操作系统CpuEndian() */
	private String cpuEndian;
}
