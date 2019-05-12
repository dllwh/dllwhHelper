package org.dllwh.utils.database.redis;

import org.dllwh.utils.application.CheckHelper;
import org.dllwh.utils.database.redis.command.RedisBasicCommand;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

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
public final class JedisPoolHelper extends RedisBasicCommand {

	private String	host;
	private int		port;
	private String	auth;

	public JedisPoolHelper(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public JedisPoolHelper(String host, int port, String auth) {
		this.host = host;
		this.port = port;
		this.auth = auth;
	}

	/**
	 * @方法描述: 获取操作redis的客户端
	 * 
	 *        <pre>
	 *        Jedis客户端实例不是线程安全的
	 *        </pre>
	 * 
	 * @return
	 * @throws JedisDataException
	 */
	private synchronized Jedis getRedisClient() throws JedisDataException {
		Jedis jedis = new Jedis(host, port);
		if (!CheckHelper.checkIsEmpty(auth)) {
			jedis.auth(auth);
		}
		return jedis;
	}

	/**
	 * @方法描述:关闭redis的客户端
	 * @param jedisClient
	 */
	private void closeRedisClient(Jedis jedisClient) {
		if (jedisClient != null) {
			jedisClient.close();
		}
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