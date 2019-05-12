package org.dllwh.utils.database.redis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientInfoModel {
	/** 连接的总持续时间以秒为单位 */
	private Long	ageSeconds;
	/** 当前的数据库ID */
	private int		database;
	/** 客户机的主机 */
	private String	host;
	/** 端口 */
	private int		Port;
	/** 连接的空闲时间(单位秒) */
	private int		idleSeconds;
	/** 最后一个命令中 */
	private String	lastCommand;
	/** 订阅数量 */
	private int		patternSubscriptionCount;
	/** 原始内容 */
	private String	raw;
	/** 订阅频道数量 */
	private int		subscriptionCount;
	/** 执行事务的命令数量 */
	private int		transactionCommandLength;
}