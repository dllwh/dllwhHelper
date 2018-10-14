package org.dllwh.common.network;

import java.net.URI;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: HTTP 请求
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月14日 下午10:18:20
 * @版本: V1.0
 * @since: JDK 1.8
 */
public class HttpRequest implements HttpMessage {
	/** 请求方式 */
	private final HttpMethod method;
	/** 请求路径 */
	private final URI uri;
	/** 协议参数 */
	private HttpParams params;
	/** 内容参数 */
	private HttpEntity entity;
	/** 请求表头 */
	private HttpHeaders headers;

	public HttpRequest(HttpMethod method, URI uri) {
		this.method = method;
		this.uri = uri;
	}

	public HttpRequest(HttpMethod method, String url) {
		this(method, URI.create(url));
	}

	public HttpParams getParams() {
		return params;
	}

	public void setParams(HttpParams params) {
		this.params = params;
	}

	public HttpEntity getEntity() {
		return entity;
	}

	public void setEntity(HttpEntity entity) {
		this.entity = entity;
	}

	public HttpHeaders getHeaders() {
		return headers;
	}

	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public URI getUri() {
		return uri;
	}
}
