package org.dllwh.utils.database.redis;

import java.util.List;
import java.util.Set;

import org.dllwh.utils.database.redis.command.RedisBasicCommand;
import org.dllwh.utils.database.redis.factory.RedisConfigFactory;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

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
public final class JedisClusterHelper extends RedisBasicCommand {
	private static JedisClusterHelper	redisClusterFactory;
	private Set<HostAndPort>			clusterNodes;
	/** 建立连接池配置参数 */
	private JedisPoolConfig				poolConfig;
	private RedisConfigFactory			factory;

	private JedisClusterHelper(List<String> hostAndPortList) {
		factory = new RedisConfigFactory(hostAndPortList);
		poolConfig = factory.genJedisConfig();
		clusterNodes = factory.genClusterNode();
	}

	/**
	 * @方法描述 : 静态工厂方法
	 * @return
	 */
	public static JedisClusterHelper getInstance(List<String> hostAndPortList) {
		if (redisClusterFactory == null) {
			redisClusterFactory = new JedisClusterHelper(hostAndPortList);
		}
		return redisClusterFactory;
	}

	/**
	 * @方法描述 : 获取操作redis的客户端
	 * @return
	 */
	private synchronized JedisCluster getRedisClient() {
		return new JedisCluster(clusterNodes, poolConfig);
	}

	/**
	 * @方法描述 : 关闭redis的客户端
	 * @return
	 */
	private void closeRedisClient(JedisCluster jedisClient) {
		if (jedisClient != null) {
			try {
				jedisClient.close();
			} catch (Exception quitExp) {
				quitExp.printStackTrace();
			}
		}
	}

	@Override
	public boolean exists(String key) throws Exception {
		JedisCluster jedisClient = getRedisClient();
		try {
			return jedisClient.exists(key);
		} finally {
			closeRedisClient(jedisClient);
		}
	}
}