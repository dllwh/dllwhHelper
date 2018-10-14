package org.dllwh.common.base;

import java.sql.Timestamp;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 日期相关
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月14日 下午9:50:41
 * @版本: V1.0
 * @since: JDK 1.8
 */
public abstract class DataEntity {
	/** 实体编号（唯一标识） */
	protected Integer id;
	/** 是否可见;1:可见;0:不可见 */
	protected Integer ifVisible;
	/** 是否有效;-1:删除;0:不可用;1:可用 */
	protected Integer ifEnabled;
	/** 最初创建者 */
	protected Integer creator;
	/** 数据创建时间 */
	protected Timestamp createTime;
	/** 最后修改人 */
	protected Integer modifier;
	/** 最后更新时间 */
	protected Timestamp updateTime;

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "DataEntity [creator=" + creator + ", createTime=" + createTime + ", modifier="
				+ modifier + ", updateTime=" + updateTime + "]";
	}
}
