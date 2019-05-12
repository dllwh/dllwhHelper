package org.dllwh.utils.database.redis.command;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis 操作接口
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月8日 上午1:29:01
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public abstract class RedisBasicCommand {
	/** 检查给定 key 是否存在 */
	public abstract boolean exists(String key) throws Exception;
}