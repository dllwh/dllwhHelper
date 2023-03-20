package org.dllwh.utils.database.redis;

import com.google.common.collect.Lists;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.*;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: Jedis 分片式
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2022-11-16 01:12
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public class RedisShardedHelperUtil {
    private static ReentrantLock lockPool = new ReentrantLock();
    /**
     * sharded jedis分布式连接池
     */
    private static ShardedJedisPool shardedJedisPool;
    private String masterName;
    private List<JedisShardInfo> jedisShardInfoList;
    private String password;
    private Set<String> masters;
    private Set<String> sentinels;


    public RedisShardedHelperUtil(String masterName, String password, Set<String> masters, Set<String> sentinels) {
        super();
        this.masterName = masterName;
        this.password = password;
        setShutdownWork();
        getJedisSentinelPool();
        jedisShardInfoList = Lists.newArrayList();
        JedisShardInfo jedisShardInfo = new JedisShardInfo("127.0.0.1", 6379);
        jedisShardInfoList.add(jedisShardInfo);
    }

    private final static GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();

    static {
        genJedisConfig();
    }

    /**
     * Jedis 连接池的配置
     */
    private static void genJedisConfig() {
        // 最大连接数
        poolConfig.setMaxTotal(1024);
        //在JedisPool中最大的idle状态(空闲的)的jedis实例的个数
        poolConfig.setMaxIdle(20);
        // 在JedisPool中最小的idle状态(空闲的)的jedis实例的个数
        poolConfig.setMinIdle(10);
        // 从jedis连接池获取连接时，校验并返回可用的连接
        poolConfig.setTestOnBorrow(true);
        // 把连接放回jedis连接池时，校验并返回可用的连接
        poolConfig.setTestOnReturn(true);
        // 连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时。默认为true。
        poolConfig.setBlockWhenExhausted(true);
    }

    private synchronized ShardedJedisPool getJedisSentinelPool() {
        // 断言 ，当前锁是否已经锁住，如果锁住了，就啥也不干，没锁的话就执行下面步骤
        assert !lockPool.isHeldByCurrentThread();
        lockPool.lock();
        try {
            if (shardedJedisPool == null) {
                shardedJedisPool = new ShardedJedisPool(poolConfig, jedisShardInfoList);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        } finally {
            lockPool.unlock();
        }
        if (shardedJedisPool == null) {
            throw new RuntimeException();
        }
        System.out.println(shardedJedisPool.isClosed());
        return shardedJedisPool;
    }

    /**
     * 设置系统停止时需执行的任务
     */
    private static void setShutdownWork() {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.addShutdownHook(new Thread(() -> {
                try {
                    if (shardedJedisPool != null) {
                        shardedJedisPool.destroy();
                        shardedJedisPool = null;
                        System.out.println("关闭Redis Pool成功.");
                    }
                } catch (Exception exp) {
                    System.err.println("关闭Redis Pool失败.");
                    exp.printStackTrace();
                }
            }));
            System.out.println("设置系统停止时关闭Redis Pool的任务成功.");
        } catch (Exception e) {
            System.err.println("设置系统停止时关闭Redis Pool的任务失败.");
        }
    }

    /**
     * 获取Jedis实例
     */
    public synchronized ShardedJedis getShardedJedis() {
        return shardedJedisPool.getResource();
    }

    /**
     * 演示测试类
     */
    public static void main(String[] args) {

    }
}
