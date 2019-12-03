package org.dllwh.monitor.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取文件系统信息
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-30 11:28:39 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class FileSystem implements Serializable {
	
	private static final long serialVersionUID = -1322963117450227895L;
	/** 盘符名称 */
	private String devName;
	/** 盘符路径 */
	private String dirName;
	/** 盘符标志 */
	private long flags;
	private String options;
	/** 盘符类型 */
	private String sysTypeName;
	/**
	 * 盘符文件系统类型 <br>
	 * 0: TYPE_UNKNOWN ：未知;<br>
	 * 1: TYPE_NONE;<br>
	 * 2: TYPE_LOCAL_DISK : 本地硬盘;<br>
	 * 3: TYPE_NETWORK ：网络;<br>
	 * 4: TYPE_RAM_DISK ：闪存;<br>
	 * 5: TYPE_CDROM ：光驱;<br>
	 * 6: TYPE_SWAP ：页面交换;
	 */
	private int type;
	/** 盘符类型名 */
	private String typeName;
	private long avail;
	private double diskQueue;
	/** 读出字节 */
	private long diskReadBytes;
	/** 读出 */
	private long diskReads;
	private double diskServiceTime;
	/** 写入字节 */
	private long diskWriteBytes;
	/** 写入 */
	private long diskWrites;
	private long files;
	/** 剩余大小 */
	private long free;
	private long freeFiles;
	/** 总大小 */
	private long total;
	/** 已经使用量 */
	private long used;
	/** 资源的利用率 */
	private double usePercent;
}
