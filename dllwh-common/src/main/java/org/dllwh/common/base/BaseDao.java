package org.dllwh.common.base;

import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 通用数据层接口
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月14日 下午9:37:29
 * @版本: V1.0
 * @since: JDK 1.8
 * @param <T>
 */
public interface BaseDao<T> {

	/**
	 * @方法描述:保存对象
	 * @param statement
	 *            映射sql的标识字符串
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Integer insert(String statement, Object param) throws Exception;

	/**
	 * @方法描述: 删除对象
	 * @param statement
	 *            映射sql的标识字符串
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Integer delete(String statement, Object param) throws Exception;

	/**
	 * @方法描述: 修改对象
	 * @param statement
	 *            映射sql的标识字符串
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Integer update(String statement, Object param) throws Exception;

	/**
	 * @方法描述:查找对象
	 * @param statement
	 *            映射sql的标识字符串
	 * @return
	 * @throws Exception
	 */
	Object findOneForJdbcParam(String statement) throws Exception;

	/**
	 * @方法描述:查找对象
	 * @param statement
	 *            映射sql的标识字符串
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Object findOneForJdbcParam(String statement, Object param) throws Exception;

	/**
	 * @方法描述:查找对象
	 * @param statement
	 *            映射sql的标识字符串
	 * @return
	 * @throws Exception
	 */
	Object findListForJdbcParam(String statement) throws Exception;

	/**
	 * @方法描述:查找对象
	 * @param statement
	 *            映射sql的标识字符串
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Object findListForJdbcParam(String statement, Object param) throws Exception;

	/**
	 * @方法描述:使用指定的检索标准检索数据并分页返回数据For JDBC-采用预处理方式
	 * @param statement
	 *            映射sql的标识字符串
	 * @return
	 * @throws Exception
	 */
	Integer getCountForJdbcParam(String statement) throws Exception;

	/**
	 * @方法描述:使用指定的检索标准检索数据并分页返回数据For JDBC-采用预处理方式
	 * @param statement
	 *            映射sql的标识字符串
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Integer getCountForJdbcParam(String statement, Object param) throws Exception;

	/**
	 * @方法描述:执行SQL
	 * @param statement
	 *            映射sql的标识字符串
	 * @param param
	 * @return
	 */
	Integer executeSql(String statement, Object... param) throws Exception;

	/**
	 * @方法描述:执行SQL
	 * @param statement
	 *            映射sql的标识字符串
	 * @param param
	 * @return
	 */
	Integer executeSql(String statement, List<Object> param) throws Exception;
}
