package org.dllwh.common.base;

import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 基础管理服务
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月12日 下午3:50:45
 * @版本: V1.0.1
 * @param <T>
 *            实体的值对像,接口调用时得用此值对像去调用
 * @since: JDK 1.8
 * @param <T>
 */
public interface BaseService<T> {
	/** 根据ID返回数据 */
	T getBaseEntity(Long id);

	/** 创建数据 */
	void createBaseEntity(T t) throws Exception;

	/** 批量新增 */
	void saveBatch(List<T> list);

	/** 删除数据 */
	void deleteBaseEntityById(Long id);

	/** 删除数据 */
	void deleteBaseEntity(T t);

	/** 编辑数据 */
	T updateBaseEntity(T t);

	/** 批量更新 */
	Integer batchUpdate(List<T> parameter) throws Exception;

	/** 保存并返回结果 */
	T saveWithResult(T t);

	/** 根据实体类不为null的字段进行查询,条件全部使用=号and条件 */
	List<T> findForJdbcParam(T record) throws Exception;

	/** 根据实体类不为null的字段查询总数,条件全部使用=号and条件 */
	Integer getCountForJdbcParam(T record) throws Exception;

	/** 根据主键进行查询,必须保证结果唯一,单个字段做主键时,可以直接写主键的值, 联合主键时,key可以是实体类,也可以是Map */
	T findOneForJdbc(T record) throws Exception;
}