package org.dllwh.monitor.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取Request请求信息
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-30 11:29:42 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class HttpRequest implements Serializable {

	private static final long serialVersionUID = -2149349255084497221L;
	/** 全部请求统计 */
	private HttpRequestCounter globalRequestCounter;
	/** 警告请求统计 */
	private HttpRequestCounter warningRequestCounter;
	/** 严重请求统计 */
	private HttpRequestCounter severeRequestCounter;
	/** 每个请求地址统计相关 */
	private List<HttpRequestCounter> requestCounterList;
}
