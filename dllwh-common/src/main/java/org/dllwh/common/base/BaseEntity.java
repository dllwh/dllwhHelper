package org.dllwh.common.base;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 基础实体类:所有实体的公共属性
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月7日 下午11:06:34
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {
	/** 实体编号（唯一标识） */
	protected Long		id;
	/** 是否有效;-1:删除;0:不可用;1:可用 */
	protected Integer	ifEnabled;
	/** 最初创建者 */
	protected Long		creator;
	/** 创建人账号 */
	protected String	creatorAccount;
	/** 创建人姓名 */
	protected String	creatorName;
	/** 数据创建时间 */
	protected Timestamp	createTime;
	/** 最后修改人 */
	protected Long		modifier;
	/** 修改人账号 */
	protected String	modifyAccount;
	/** 修改人姓名 */
	protected String	modifyName;
	/** 最后更新时间 */
	protected Timestamp	lastModifiedTime;
}