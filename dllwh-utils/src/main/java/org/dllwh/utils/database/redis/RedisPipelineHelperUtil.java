package org.dllwh.utils.database.redis;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import redis.clients.jedis.*;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: Redis 管道模式
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2022-12-01 09:03
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public class RedisPipelineHelperUtil {
    private static final Logger logger = LoggerFactory.getLogger(RedisPipelineHelperUtil.class);
    /**
     * jedis连接池。
     * <p>
     * 被volatile修饰的变量不会被本地线程缓存，对该变量的读写都是直接操作共享内存。
     */
    private static volatile JedisPool jedisPool;
    private final static ReentrantLock LOCK_POOL = new ReentrantLock();

    /**
     * Redis节点所在的机器的IP
     */
    private String host;
    /**
     * Redis节点的端口
     */
    private int port;
    /**
     * Redis服务连接需要密码
     */
    private String password;
    /**
     * Redis池配置
     */
    private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();


    public RedisPipelineHelperUtil(String host, int port) {
        this.host = host;
        this.port = port;
        initPool();
    }

    public RedisPipelineHelperUtil(String host, int port, String password) {
        this.host = host;
        this.port = port;
        this.password = password;
        initPool();
    }

    /**
     * 初始化单节点池，只会调用一次
     */
    private synchronized void initPool() {

        logger.debug("host:[{}], port:[{}].", host, port);
        // 断言 ，当前锁是否已经锁住，如果锁住了，就啥也不干，没锁的话就执行下面步骤
        assert !LOCK_POOL.isHeldByCurrentThread();
        LOCK_POOL.lock();
        try {
            if (jedisPool == null) {
                synchronized (JedisPoolHelper.class) {
                    if (jedisPool == null) {
                        // 设置连接到主节点
                        if (StringUtils.isNotBlank(password)) {
                            jedisPool = new JedisPool(jedisPoolConfig, host, port, Protocol.DEFAULT_TIMEOUT, password);
                        } else {
                            jedisPool = new JedisPool(jedisPoolConfig, host, port);
                        }
                    }
                }
            }
        } catch (Exception exp) {
            // 创建Redis Pool失败
            exp.printStackTrace();
        } finally {
            LOCK_POOL.unlock();
        }
    }

    /**
     * Jedis 连接池的配置
     */
    private static void getJedisPoolConfig() {
        // 最大连接数
        jedisPoolConfig.setMaxTotal(1024);
        //在JedisPool中最大的idle状态(空闲的)的jedis实例的个数
        jedisPoolConfig.setMaxIdle(20);
        // 在JedisPool中最小的idle状态(空闲的)的jedis实例的个数
        jedisPoolConfig.setMinIdle(10);
        // 从jedis连接池获取连接时，校验并返回可用的连接
        jedisPoolConfig.setTestOnBorrow(true);
        // 把连接放回jedis连接池时，校验并返回可用的连接
        jedisPoolConfig.setTestOnReturn(true);
        // 连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时。默认为true。
        jedisPoolConfig.setBlockWhenExhausted(true);
    }

    /**
     * 从连接池中借用一个实例
     */
    public Jedis getRedisClient() {
        return jedisPool.getResource();
    }

    /**
     * 归还连接
     * <p>
     * 注意：这里不是关闭连接，在JedisPool模式下，Jedis对象会被归还给资源池。
     *
     * @param jedis Jedis连接
     */
    public void release(Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }

    public static void main(String[] args) throws Exception {
        RedisPipelineHelperUtil jedisPoolUtils = new RedisPipelineHelperUtil("127.0.0.1", 6379);
        //从redis连接池里拿出一个连接执行命令
        Jedis redisClient = jedisPoolUtils.getRedisClient();
        try {

            redisClient = jedisPool.getResource();

            //管道示例
            //管道的命令执行方式：cat redis.txt | redis-cli -h 127.0.0.1 -a password - p 6379 --pipe
            Pipeline pl = redisClient.pipelined();
            for (int i = 0; i < 10; i++) {
                pl.incr("pipelineKey");
                pl.set("sch" + i, i + "");
            }
            List<Object> results = pl.syncAndReturnAll();
            //结果显示：[1, OK, 2, OK, 3, OK, 4, OK, 5, OK, 6, OK, 7, OK, 8, OK, 9, OK, 10, OK]
            System.out.println(results);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //注意这里不是关闭连接，在JedisPool模式下，Jedis对象会被归还给资源池。
            jedisPoolUtils.release(redisClient);
        }
    }
}
