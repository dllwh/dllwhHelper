package org.dllwh.monitor.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取网络信息
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-30 11:57:36 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class Net implements Serializable {

	private static final long serialVersionUID = -2359077483566491608L;
	/** 网络设备名称 */
	private String name = null;
	/** 网卡的物理地址 */
	private String hwaddr = null;
	/** 网卡类型 */
	private String type = null;
	/** 网卡描述信息 */
	private String description = null;
	/** IP地址 */
	private String address = null;
	private String destination = null;
	/** 网关广播地址 */
	private String broadcast = null;
	/** 子网掩码 */
	private String netmask = null;
	private long flags = 0L;
	/** 设置网卡的最大传输单元 */
	private long mtu = 0L;
	private long metric = 0L;
	/** 接收到的总字节数 */
	private long rxBytes = 0L;
	/** 接收的总包裹数 */
	private long rxPackets = 0L;
	/** 接收到的错误包数 */
	private long rxErrors = 0L;
	// 表示这个数据包已经进入到网卡的接收缓存fifo队列，并且开始被系统中断处理准备进行数据包拷贝（从网卡缓存fifo队列拷贝到系统内存），但由于此时的系统原因（比如内存不够等）导致这个数据包被丢掉，即这个数据包被Linux系统丢掉。
	private long rxDropped = 0L; // 接收时丢弃的包数
	// 表示这个数据包还没有被进入到网卡的接收缓存fifo队列就被丢掉，因此此时网卡的fifo是满的。为什么fifo会是满的？因为系统繁忙，来不及响应网卡中断，导致网卡里的数据包没有及时的拷贝到系统内存，fifo是满的就导致后面的数据包进不来，即这个数据包被网卡硬件丢掉。
	private long rxOverruns = 0L;
	private long rxFrame = 0L;
	/** 发送的总字节数 */
	private long txBytes = 0L;
	/** 发送的总包裹数 */
	private long txPackets = 0L;
	/** 发送数据包时的错误数 */
	private long txErrors = 0L;
	/** 发送时丢弃的包数 */
	private long txDropped = 0L;
	private long txOverruns = 0L;
	private long txCollisions = 0L;
	private long txCarrier = 0L;
	private long speed = 0L;
}
