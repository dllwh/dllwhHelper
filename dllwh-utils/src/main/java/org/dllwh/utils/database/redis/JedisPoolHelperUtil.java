package org.dllwh.utils.database.redis;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import redis.clients.jedis.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: Jedis 单节点 连接池使用
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2022-11-16 01:15
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public class JedisPoolHelperUtil {
    private static final Logger logger = LoggerFactory.getLogger(JedisPool.class);
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
    private final static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

    private JedisPoolHelperUtil() {
    }

    public JedisPoolHelperUtil(String host, int port) {
        this.host = host;
        this.port = port;
        jedisPool = initPool();
    }

    public JedisPoolHelperUtil(String host, int port, String password) {
        this.host = host;
        this.port = port;
        this.password = password;
        jedisPool = initPool();
    }

    static {
        getJedisPoolConfig();
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
     * 初始化单节点池，只会调用一次
     */
    private synchronized JedisPool initPool() {
        logger.debug("host:[{}], port:[{}].", host, port);
        // 断言 ，当前锁是否已经锁住，如果锁住了，就啥也不干，没锁的话就执行下面步骤
        assert !LOCK_POOL.isHeldByCurrentThread();
        LOCK_POOL.lock();
        try {
            if (jedisPool == null) {
                synchronized (JedisPoolHelper.class) {
                    if (jedisPool == null) {
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
        return jedisPool;
    }

    /**
     * 从连接池中借用一个实例
     */
    public Jedis getRedisClient() {
        return jedisPool.getResource();
    }

    /**
     * 归还连接
     *
     * @param jedis Jedis连接
     */
    public void release(Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }

    public static void main(String[] args) {
        JedisPoolHelperUtil jedisPoolUtils = new JedisPoolHelperUtil("127.0.0.1", 6379);
        Jedis redisClient = jedisPoolUtils.getRedisClient();
        redisClient.set("msg", "Hello World!");
        System.out.println(redisClient.get("msg"));
        System.out.println(redisClient.dbSize());
        jedisPoolUtils.release(redisClient);
    }
}
