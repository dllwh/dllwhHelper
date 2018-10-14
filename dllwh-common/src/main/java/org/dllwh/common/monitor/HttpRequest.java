package org.dllwh.common.monitor;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取Request请求信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月14日 下午11:58:33
 * @版本: V1.0
 * @since: JDK 1.8
 */
public class HttpRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 全部请求统计 */
	private HttpRequestCounter globalRequestCounter;
	/** 警告请求统计 */
	private HttpRequestCounter warningRequestCounter;
	/** 严重请求统计 */
	private HttpRequestCounter severeRequestCounter;
	/** 每个请求地址统计相关 */
	private List<HttpRequestCounter> requestCounterList;

	public HttpRequestCounter getGlobalRequestCounter() {
		return globalRequestCounter;
	}

	public void setGlobalRequestCounter(HttpRequestCounter globalRequestCounter) {
		this.globalRequestCounter = globalRequestCounter;
	}

	public HttpRequestCounter getWarningRequestCounter() {
		return warningRequestCounter;
	}

	public void setWarningRequestCounter(HttpRequestCounter warningRequestCounter) {
		this.warningRequestCounter = warningRequestCounter;
	}

	public HttpRequestCounter getSevereRequestCounter() {
		return severeRequestCounter;
	}

	public void setSevereRequestCounter(HttpRequestCounter severeRequestCounter) {
		this.severeRequestCounter = severeRequestCounter;
	}

	public List<HttpRequestCounter> getRequestCounterList() {
		return requestCounterList;
	}

	public void setRequestCounterList(List<HttpRequestCounter> requestCounterList) {
		this.requestCounterList = requestCounterList;
	}
}
