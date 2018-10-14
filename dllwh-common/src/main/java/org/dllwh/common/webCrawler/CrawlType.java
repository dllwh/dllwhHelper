package org.dllwh.common.webCrawler;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 网络爬虫枚举类型
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月14日 下午9:45:57
 * @版本: V1.0
 * @since: JDK 1.8
 */
public enum CrawlType {
	/** jsoup 是一款Java 的HTML解析器,可直接解析某个URL地址、HTML文本内容 */
	jsoup,
	/** */
	urlconn,
	/** */
	httpclient,
	/** */
	htmlunit,
	/** */
	htmlparse,
	/** */
	selenium;
}
