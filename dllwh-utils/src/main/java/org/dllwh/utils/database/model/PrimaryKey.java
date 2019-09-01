package org.dllwh.utils.database.model;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 主键信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019-08-20 7:10:09 PM
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class PrimaryKey implements Serializable {
	private static final long serialVersionUID = -1299405371249411174L;
	/** 列对应的表名 */
	private String tableName;
	/** 列名 */
	private String columnName;
	/** 主键名称 */
	private String pkName;
	/** 序列号 */
	private Short keySeq;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getPkName() {
		return pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

	public Short getKeySeq() {
		return keySeq;
	}

	public void setKeySeq(Short keySeq) {
		this.keySeq = keySeq;
	}

	@Override
	public String toString() {
		return "PrimaryKey [tableName=" + tableName + ", columnName=" + columnName + ", pkName=" + pkName + ", keySeq="
				+ keySeq + "]";
	}
}