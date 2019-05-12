package org.dllwh.utils.database.redis.factory;

import java.util.List;
import java.util.Set;

import org.dllwh.utils.application.CheckHelper;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisDataException;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月12日 下午10:51:02
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class RedisConfigurationManager {

	/**
	 * 单机相关配置参数
	 */
	private String				host;
	private int					port;
	private String				auth;

	/**
	 * 集群相关配置参数
	 */
	private Set<HostAndPort>	clusterNodes;
	/** 建立连接池配置参数 */
	private JedisPoolConfig		poolConfig;
	private RedisConfigFactory	factory;

	public RedisConfigurationManager(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public RedisConfigurationManager(String host, int port, String auth) {
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
	protected synchronized Jedis getRedisClient() throws JedisDataException {
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
	protected void closeRedisClient(Jedis jedisClient) {
		if (jedisClient != null) {
			jedisClient.close();
		}
	}

	public RedisConfigurationManager(List<String> hostAndPortList) {
		factory = new RedisConfigFactory(hostAndPortList);
		poolConfig = factory.genJedisConfig();
		clusterNodes = factory.genClusterNode();
	}

	/**
	 * @方法描述 : 获取操作redis的客户端
	 * @return
	 */
	protected synchronized JedisCluster getRedisClusterClient() {
		return new JedisCluster(clusterNodes, poolConfig);
	}

	/**
	 * @方法描述 : 关闭redis的客户端
	 * @return
	 */
	protected void closeRedisClusterClient(JedisCluster jedisClient) {
		if (jedisClient != null) {
			try {
				jedisClient.close();
			} catch (Exception quitExp) {
				quitExp.printStackTrace();
			}
		}
	}
}