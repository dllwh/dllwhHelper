package org.dllwh.utils.database.redis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月12日 下午10:30:46
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisServerModel {
	/** 唯一标示 */
	private String	ServerId;
	/** Redis地址 */
	private String	ServerHost;
	/** Redis描述 */
	private String	ServerDescribe;
}