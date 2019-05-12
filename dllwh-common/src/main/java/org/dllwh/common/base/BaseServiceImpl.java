package org.dllwh.common.base;

import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Service层用于处理【业务】逻辑
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月7日 下午11:16:09
 * @版本: V1.0.1
 * @param <T>
 *            实体类,接口调用时得用此值对像去调用
 * @since: JDK 1.8
 */
public class BaseServiceImpl<T> implements BaseService<T> {

	@Override
	public T getBaseEntity(Long id) {
		return null;
	}

	@Override
	public void createBaseEntity(T t) throws Exception {
	}

	@Override
	public void saveBatch(List<T> list) {
	}

	@Override
	public void deleteBaseEntityById(Long id) {
	}

	@Override
	public void deleteBaseEntity(T t) {
	}

	@Override
	public T updateBaseEntity(T t) {
		return null;
	}

	@Override
	public Integer batchUpdate(List<T> parameter) throws Exception {
		return null;
	}

	@Override
	public T saveWithResult(T t) {
		return null;
	}

	@Override
	public List<T> findForJdbcParam(T record) throws Exception {
		return null;
	}

	@Override
	public Integer getCountForJdbcParam(T record) throws Exception {
		return null;
	}

	@Override
	public T findOneForJdbc(T record) throws Exception {
		return null;
	}
}