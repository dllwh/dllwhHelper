package org.dllwh.utils.database.redis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis 监控系统
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月12日 下午10:29:29
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class RedisMonitorInfo {
	/** 版本号 */
	private String	version;
	/** 进程号 */
	private String	processId;
	/** 运行时间 */
	private String	upTime;
	/** 当前连接的客户端数量 */
	private Integer	clientCount;
	/** 允许使用的内存量，单位是B，需要转换为KB */
	private Long	usedMemory;
	/** 历史上分配使用的最大的内存量，单位是B，需要转换为KB */
	private Long	usedMemoryMax;
	/** 使用的CPU百分比-系统 */
	private Double	usedCpuSys;
	/** 使用的CPU百分比 - 用户 */
	private Double	usedCpuUser;
	/** 总使用CPU百分比 */
	private Double	usedCpu;
	/** 执行命令总数量 */
	private Integer	totalCommandCount;
	/** 当前时段的OPS */
	private String	totalPercentSecondCurrent	= "0";
	/** 命中key的总数量 */
	private Integer	keyspaceHits;
	/** 未命中的key的总数量 */
	private Integer	keyspaceMisses;
	/** 当前时段命中成功的百分比 */
	private String	keyspaceHitRateCurrent		= "0";
	/** 运行以来过期的 key 的数量 */
	private Integer	expiredKeys;
	/** 运行以来删除的 key 的数量 */
	private Integer	evictedKeys;
	/** 当前保存的key的数量 */
	private Integer	dbKeyCount;
	/** 当前实例的角色，master或者slave */
	private String	role;
	/** 本次保存的时间戳，用于计算QPS */
	private long	timestamp					= 0L;
}