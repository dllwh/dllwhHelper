package org.dllwh.monitor.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:请求地址统计详情
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-30 11:30:31 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class HttpRequestCounter implements Serializable {
	
	private static final long serialVersionUID = -7380846625610519898L;
	/** 请求路径名称 */
	private String name;
	/** 访问次数 */
	private long hits;
	/** 响应时间和 */
	private long durationsSum;
	/** 响应最长时间 */
	private long maximum;
	/** cpu执行时间和 */
	private long cpuTimeSum;
	/** 响应数据大小和 */
	private int responseSizeMean;
	/** CPU平均执行时间 */
	private int cpuTimeMean;
	/** 平均响应时间 */
	private int mean;
	/** 响应时间偏差 */
	private int standardDeviation;
	/** 错误率 */
	private float systemErrorPercentage;

}
