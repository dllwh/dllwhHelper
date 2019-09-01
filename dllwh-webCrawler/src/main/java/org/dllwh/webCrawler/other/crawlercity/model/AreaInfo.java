package org.dllwh.webCrawler.other.crawlercity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 地区类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019-09-01 9:44:16 PM
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Data
@AllArgsConstructor
public class AreaInfo {
	private String areaCode;
	private String itemCode;
	private String areaName;
	private String areaUrl;
	private Integer parentId;

	public AreaInfo() {
		super();
	}

	public AreaInfo(String areaCode, String areaName) {
		super();
		this.areaCode = areaCode;
		this.areaName = areaName;
	}
}
