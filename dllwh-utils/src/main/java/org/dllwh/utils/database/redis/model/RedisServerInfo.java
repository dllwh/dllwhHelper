package org.dllwh.utils.database.redis.model;

import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * 
 * 		<pre>
 * Redis 监控最直接的方法就是使用系统提供的 info 命令，只需要执行redis-cli info命令，就能获得 Redis 系统的状态报告。
 * 结果会返回 Server、Clients、Memory、Persistence、Stats、Replication、CPU、Keyspace 8个部分。
 * 从info大返回结果中提取相关信息，就可以达到有效监控的目的。
 *       </pre>
 * 
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月12日 下午10:29:49
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class RedisServerInfo {
	/** ----------------------------------------------------- Fields start */
	private static LinkedHashMap<String, String> map = new LinkedHashMap<>();

	/** ----------------------------------------------------- Fields end */

	static {
		/**
		 * Server:Redis服务器的信息
		 */
		map.put("redis_version", "Redis 服务器版本");
		map.put("redis_git_sha1", "Git SHA1");
		map.put("redis_git_dirty", "Git dirty flag");
		map.put("os", "Redis 服务器的宿主操作系统");
		map.put("arch_bits", " 架构（32 或 64 位）");
		map.put("multiplexing_api", "Redis 所使用的事件处理机制");
		map.put("gcc_version", "编译 Redis 时所使用的 GCC 版本");
		map.put("process_id", "当前 Redis 服务器进程id");
		map.put("run_id", "Redis 服务器的随机标识符（用于 Sentinel 和集群）");
		map.put("tcp_port", "TCP/IP 监听端口");
		map.put("uptime_in_seconds", "自 Redis 服务器启动以来，经过的秒数");
		map.put("uptime_in_days", "自 Redis 服务器启动以来，经过的天数");
		map.put("lru_clock", "以分钟为单位进行自增的时钟，用于 LRU 管理");
		map.put("config_file", "配置文件路径");

		/**
		 * Clients:记录了已连接客户端的信息
		 */
		map.put("connected_clients", "已连接客户端的数量（不包括通过从属服务器连接的客户端）");
		map.put("client_longest_output_list", "当前连接的客户端当中，最长的输出列表");
		map.put("client_longest_input_buf", "当前连接的客户端当中，最大输入缓存");
		map.put("blocked_clients", "正在等待阻塞命令（BLPOP、BRPOP、BRPOPLPUSH）的客户端的数量");

		/**
		 * Memory:记录了服务器的内存信息
		 */
		map.put("used_memory", "由 Redis 分配器分配的内存总量，以字节（byte）为单位");
		map.put("used_memory_human", "以人类可读的格式返回 Redis 分配的内存总量");
		map.put("used_memory_rss", "从操作系统的角度，返回 Redis 已分配的内存总量（俗称常驻集大小）。这个值和 top 、 ps 等命令的输出一致");
		map.put("used_memory_peak", " Redis 的内存消耗峰值(以字节为单位)");
		map.put("used_memory_peak_human", "以人类可读的格式返回 Redis 的内存消耗峰值");
		map.put("used_memory_lua", "Lua 引擎所使用的内存大小（以字节为单位）");
		map.put("mem_fragmentation_ratio", "sed_memory_rss 和 used_memory 之间的比率");
		map.put("mem_allocator", "在编译时指定的， Redis 所使用的内存分配器。可以是 libc 、 jemalloc 或者 tcmalloc");

		/**
		 * Stats: 一般统计信息
		 */
		map.put("total_connections_received", "运行以来连接过的客户端的总数量");
		map.put("total_commands_processed", "运行以来执行过的命令的总数量");
		map.put("instantaneous_ops_per_sec", "redis当前的qps，redis内部较实时的每秒执行的命令数");
		map.put("total_net_input_bytes", "redis网络入口流量字节数");
		map.put("total_net_output_bytes", "redis网络出口流量字节数");
		map.put("instantaneous_input_kbps", "redis网络入口kps");
		map.put("instantaneous_output_kbps", "redis网络出口kps");
		map.put("rejected_connections", "拒绝的连接个数，redis连接个数达到maxclients限制，拒绝新连接的个数");
		map.put("sync_full", "主从完全同步成功次数");
		map.put("sync_partial_ok", "主从部分同步成功次数");
		map.put("sync_partial_err", "主从部分同步失败次数");
		map.put("expired_keys", "运行以来过期的 key 的数量");
		map.put("evicted_keys", "运行以来删除过的key的数量");
		map.put("keyspace_hits", "查找数据库键成功的次数");
		map.put("keyspace_misses", "查找数据库键失败的次数");
		map.put("pubsub_channels", "目前被订阅的频道数量");
		map.put("pubsub_patterns", "目前被订阅的模式数量");

		/**
		 * Replication : 主/从信息
		 */
		map.put("role", "当前实例的角色master还是slave");
		map.put("connected_slaves", "有多少个slave节点");
		map.put("master_host", "此节点对应的master的ip");
		map.put("master_port", "此节点对应的master的port");

		/**
		 * CPU : CPU 计算量统计信息
		 */
		map.put("used_cpu_sys", "Redis服务器耗费的系统CPU");
		map.put("used_cpu_user", "Redis服务器耗费的用户CPU");
		map.put("used_cpu_sys_children", " 	Redis后台进程耗费的系统CPU");
		map.put("used_cpu_user_children", "Redis后台进程耗费的用户CPU");

		/**
		 * Keyspace : 数据库相关的统计信息
		 */
		map.put("db0", "各个数据库的 key 的数量，以及带有生存期的 key 的数量");

		/**
		 * Cluster : Redis 集群信息
		 */
		map.put("cluster_enabled", "实例是否启用集群模式");
		/**
		 * Commandstats : 各种不同类型的命令的执行统计信息
		 */
	}

	private String	key;
	private String	value;
	private String	desctiption;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
		this.desctiption = StringUtils.isNoneBlank(map.get(this.key)) ? map.get(this.key) : "";
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesctiption() {
		return desctiption;
	}

	@Override
	public String toString() {
		return "RedisInfoDetail [key=" + key + ", value=" + value + ", desctiption=" + desctiption + "]";
	}
}
