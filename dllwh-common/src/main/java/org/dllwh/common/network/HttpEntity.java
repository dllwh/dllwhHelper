package org.dllwh.common.network;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Http 参数
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月14日 下午10:18:03
 * @版本: V1.0
 * @since: JDK 1.8
 */
public interface HttpEntity {
	ContentType getContentType();

	long getContentLength();
}
