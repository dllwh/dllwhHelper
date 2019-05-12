package org.dllwh.utils.database.redis;

import java.util.List;

import org.dllwh.utils.database.redis.command.KeyCommand;
import org.dllwh.utils.database.redis.factory.RedisConfigurationManager;

import redis.clients.jedis.JedisCluster;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis - 集群模式
 * 
 *       <pre>
 * 将众多的key-value集合存在多个节点上，当某一个节点出现障碍，不影响整个集群的功能
 *       </pre>
 * 
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月8日 上午1:11:10
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public final class JedisClusterHelper extends RedisConfigurationManager implements KeyCommand {

	public JedisClusterHelper(List<String> hostAndPortList) {
		super(hostAndPortList);
	}

	@Override
	public boolean exists(String key) throws Exception {
		JedisCluster jedisClient = getRedisClusterClient();
		try {
			return jedisClient.exists(key);
		} finally {
			closeRedisClusterClient(jedisClient);
		}
	}
}