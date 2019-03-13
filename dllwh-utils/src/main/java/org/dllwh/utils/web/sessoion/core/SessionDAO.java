package org.dllwh.utils.web.sessoion.core;

import java.util.Map;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: session 业务操作
 * @创建者: 独泪了无痕
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年3月8日 下午9:36:11
 * @版本: V 1.0.1
 * @since: JDK 1.7
 */
public interface SessionDAO {
	/**
	 * @方法描述 : 读取完整session信息
	 */
	public Map<String, Object> getSession(String sid);

	/**
	 * @方法描述 : 刷新session信息到存储仓库中
	 */
	public void saveSession(String sid, Map<String, Object> session, Integer expireTime);

	/**
	 * @方法描述 : 删除session
	 */
	public void removeSession(String sid);
}