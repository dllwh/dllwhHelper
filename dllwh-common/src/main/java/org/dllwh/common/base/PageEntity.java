/**
 * 
 */
package org.dllwh.common.base;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 分页
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月14日 下午9:40:07
 * @版本: V1.0
 * @since: JDK 1.8
 */
public class PageEntity {
	/** 当前页数:第几页 --pageNo */
	protected Integer page = 1;
	/** 每页记录数 :pageSize */
	protected Integer rows = 10;
	/** 起始页 */
	protected int startRow;
	/** 排序字段名 */
	protected String sortName;
	/** 按什么排序(asc,desc) */
	protected String sortOrder;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		if (sortOrder.equalsIgnoreCase("asc") || sortOrder.equalsIgnoreCase("desc")) {
			this.sortOrder = sortOrder;
		} else {
			this.sortOrder = "";
		}
	}

	public int getStartRow() {
		startRow = (page - 1) * getRows();
		return startRow;
	}

}
