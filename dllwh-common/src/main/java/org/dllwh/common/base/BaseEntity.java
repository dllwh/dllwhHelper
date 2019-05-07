package org.dllwh.common.base;

import java.sql.Timestamp;

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

	public BaseEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseEntity(Long id, Integer ifEnabled, Long creator, String creatorAccount, String creatorName,
			Timestamp createTime, Long modifier, String modifyAccount, String modifyName,
			Timestamp lastModifiedTime) {
		super();
		this.id = id;
		this.ifEnabled = ifEnabled;
		this.creator = creator;
		this.creatorAccount = creatorAccount;
		this.creatorName = creatorName;
		this.createTime = createTime;
		this.modifier = modifier;
		this.modifyAccount = modifyAccount;
		this.modifyName = modifyName;
		this.lastModifiedTime = lastModifiedTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIfEnabled() {
		return ifEnabled;
	}

	public void setIfEnabled(Integer ifEnabled) {
		this.ifEnabled = ifEnabled;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public String getCreatorAccount() {
		return creatorAccount;
	}

	public void setCreatorAccount(String creatorAccount) {
		this.creatorAccount = creatorAccount;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getModifier() {
		return modifier;
	}

	public void setModifier(Long modifier) {
		this.modifier = modifier;
	}

	public String getModifyAccount() {
		return modifyAccount;
	}

	public void setModifyAccount(String modifyAccount) {
		this.modifyAccount = modifyAccount;
	}

	public String getModifyName() {
		return modifyName;
	}

	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}

	public Timestamp getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Timestamp lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
}