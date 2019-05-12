package org.dllwh.utils.database.redis.command;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 分布式锁
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月12日 下午10:09:03
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public abstract class DistributedRedisLock {
	/** 加锁 */
	public abstract boolean acquire(String lockName, long leaseTime);

	/** 锁的释放 */
	public abstract boolean release(String lockName);
}