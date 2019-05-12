package org.dllwh.utils.database.redis.factory;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis 配置工厂
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月8日 上午1:38:26
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Slf4j
public final class RedisConfigFactory {
	private static ReentrantLock		lockPool	= new ReentrantLock();

	/** 支持集群 */
	private static Set<HostAndPort>		clusterNodes;
	private List<String>				hostAndPortList;

	/** 支持单台Redis的非切片链接池 */
	private static JedisSentinelPool	jedisPool	= null;
	/** 建立连接池配置参数 */
	private static JedisPoolConfig		poolConfig	= null;
	private String						auth, masterName;
	private List<String>				addressList;
	private int							timeout, database = 0;

	public RedisConfigFactory(List<String> hostAndPortList) {
		this.hostAndPortList = hostAndPortList;
	}

	public RedisConfigFactory(String auth, List<String> addressList, String masterName, int timeout,
			int database) {
		this.auth = auth;
		this.addressList = addressList;
		this.masterName = masterName;
		this.timeout = timeout;
		this.database = database;
		genJedisConfig();
	}

	/**
	 * @方法:池基本配置
	 * @创建人:独泪了无痕
	 */
	private void initClusterConfig() {
		poolConfig = new JedisPoolConfig();
		// 设置最大连接数，默认值为8.如果赋值为-1，则表示不限制；
		poolConfig.setMaxTotal(1024);
		// 最大空闲连接数
		poolConfig.setMaxIdle(10);
		// 最小空闲连接数
		poolConfig.setMinIdle(10);
		// 获取Jedis连接的最大等待时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
		poolConfig.setMaxWaitMillis(10000);
		// 每次释放连接的最大数目
		poolConfig.setNumTestsPerEvictionRun(1024);
		// 释放连接的扫描间隔（毫秒）,如果为负数,则不运行逐出线程, 默认-1
		poolConfig.setTimeBetweenEvictionRunsMillis(30000);
		// 连接最小空闲时间
		poolConfig.setMinEvictableIdleTimeMillis(1800000);
		// 连接空闲多久后释放, 当空闲时间>该值 且 空闲连接>最大空闲连接数 时直接释放
		poolConfig.setSoftMinEvictableIdleTimeMillis(10000);
		// 在获取Jedis连接时，自动检验连接是否可用
		poolConfig.setTestOnBorrow(true);
		// 在将连接放回池中前，自动检验连接是否有效
		poolConfig.setTestOnReturn(true);
		// 自动测试池中的空闲连接是否都是可用连接
		poolConfig.setTestWhileIdle(true);
		// 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
		poolConfig.setBlockWhenExhausted(false);
		// 是否启用pool的jmx管理功能, 默认true
		poolConfig.setJmxEnabled(true);
		// 是否启用后进先出, 默认true
		poolConfig.setLifo(true);
		// 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
		poolConfig.setNumTestsPerEvictionRun(3);
	}

	/**
	 * @方法描述: 初始化Redis连接池
	 */
	private synchronized void initialPool() {
		// 断言 ，当前锁是否已经锁住，如果锁住了，就啥也不干，没锁的话就执行下面步骤
		assert !lockPool.isHeldByCurrentThread();

		lockPool.lock();
		try {
			if (jedisPool == null) {
				Set<String> sentinels = new HashSet<String>();
				for (String address : addressList) {
					sentinels.add(address);
				}
				if (StringUtils.isNoneBlank(auth)) {
					jedisPool = new JedisSentinelPool(masterName, sentinels, poolConfig, timeout, auth,
							database);
				} else {
					jedisPool = new JedisSentinelPool(masterName, sentinels, poolConfig, timeout, null,
							database);
				}
			}
			log.info("JedisPool注入成功！！");
		} catch (Exception ex) {
			log.error("创建Redis Pool失败.", ex);
		} finally {
			lockPool.unlock();
		}
	}

	/**
	 * @方法描述 : 实列化JRedis 线程池相关配置
	 */
	public JedisPoolConfig genJedisConfig() {
		if (poolConfig == null) {
			initClusterConfig();
		}
		return poolConfig;
	}

	/**
	 * @方法描述: 池基本配置
	 * @return
	 */
	public synchronized JedisSentinelPool redisPoolFactory() {
		// 断言 ，当前锁是否已经锁住，如果锁住了，就啥也不干，没锁的话就执行下面步骤
		assert !lockPool.isHeldByCurrentThread();

		if (jedisPool == null) {
			synchronized (JedisSentinelPool.class) {
				initialPool();
			}
		}
		return jedisPool;
	}

	/**
	 * @return
	 * @方法描述 : 配置Redis集群节点
	 */
	public synchronized Set<HostAndPort> genClusterNode() {
		lockPool.lock();
		try {
			clusterNodes = new LinkedHashSet<HostAndPort>();
			for (String hostAndPort : hostAndPortList) {
				clusterNodes.add(new HostAndPort(StringUtils.substringBefore(hostAndPort, ":"),
						Integer.valueOf(StringUtils.substringAfter(hostAndPort, ":"))));
			}
			log.info("配置Redis集群节点成功！！");
		} catch (Exception ex) {
			log.error("配置Redis集群节点失败.", ex);
		} finally {
			lockPool.unlock();
		}
		return clusterNodes;
	}
}