package org.dllwh.common.network;

import java.net.Proxy;

import javax.net.ssl.SSLContext;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Http 参数
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月14日 下午10:15:38
 * @版本: V1.0
 * @since: JDK 1.8
 */
public final class HttpParams {
	// 设置连接超时时间，单位毫秒
	private int connectTimeout = 10000;
	// 设置读取Socket超时时间,单位毫秒
	private int socketTimeout = 10000;
	// 设置读取数据超时时间，单位毫秒
	private int readTimeout = 10000;
	// 是否允许用户交互（例如弹出一个验证对话框）的上下文中对此 URL 进行检查
	private boolean allowUserInteraction = true;
	// 代理对象
	private Proxy proxy;
	// ssl 证书
	private SSLContext sslContext;

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public boolean isAllowUserInteraction() {
		return allowUserInteraction;
	}

	public void setAllowUserInteraction(boolean allowUserInteraction) {
		this.allowUserInteraction = allowUserInteraction;
	}

	public Proxy getProxy() {
		return proxy;
	}

	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}

	public SSLContext getSslContext() {
		return sslContext;
	}

	public void setSslContext(SSLContext sslContext) {
		this.sslContext = sslContext;
	}

}
