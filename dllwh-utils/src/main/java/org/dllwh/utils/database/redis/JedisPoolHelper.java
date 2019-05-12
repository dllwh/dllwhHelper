package org.dllwh.utils.database.redis;

import org.dllwh.utils.database.redis.command.KeyCommand;
import org.dllwh.utils.database.redis.factory.RedisConfigurationManager;

import redis.clients.jedis.Jedis;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Jedis单机版实现类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月8日 上午1:08:18
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public final class JedisPoolHelper extends RedisConfigurationManager implements KeyCommand {

	public JedisPoolHelper(String host, int port) {
		super(host, port);
	}

	public JedisPoolHelper(String host, int port, String auth) {
		super(host, port, auth);
	}

	@Override
	public boolean exists(String key) throws Exception {
		Jedis jedisClient = getRedisClient();
		try {
			return jedisClient.exists(key);
		} finally {
			closeRedisClient(jedisClient);
		}
	}
}