package org.dllwh.utils.apache.collection;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Map工具类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年11月11日 下午11:01:38
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class MapUtilHelper {
	/**
	 * 
	 * @param <K>
	 * @param <V>
	 * @方法名称: mapTransitionList
	 * @方法描述: 将map形转换成list
	 * 
	 * @param map
	 *            要转化的map
	 * @return 转化后的List
	 */
	public static <K, V> List<Object> mapTransitionList(Map<K, V> map) {
		List<Object> resultList = new ArrayList<Object>();
		if (MapUtils.isNotEmpty(map)) {
			Iterator<?> iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<?, ?> entry = (Entry<?, ?>) iter.next();
				resultList.add(entry.getValue());
			}
		}
		return resultList;
	}

	/**
	 * 
	 * @方法名称: toBean
	 * @方法描述: 把Map转换成指定类型
	 * 
	 * @param map
	 * @param clazz
	 * @return
	 */
	public static <T> T toBean(Map<String, ?> map, Class<T> clazz) {
		try {
			/**
			 * 1. 通过参数clazz创建实例 <BR/>
			 * 2. 使用BeanUtils.populate把map的数据封闭到bean中
			 */
			T bean = clazz.newInstance();
			ConvertUtils.register(new DateConverter(), java.util.Date.class);
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @方法描述 : 将一个 JavaBean 对象转化为一个 Map
	 * @param bean
	 * @param convertType
	 *            转换类型，1:小写;2:大写;0:不转换,默认是0
	 * @return
	 * @throws IntrospectionException
	 */
	private static Map<String, Object> beanToMapByConvert(Object bean, Integer convertType)
			throws IntrospectionException {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Class<? extends Object> type = bean.getClass();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		// 获取类属性
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		PropertyDescriptor descriptor = null;
		String propertyName = "";
		for (int i = 0; i < propertyDescriptors.length; i++) {
			try {
				descriptor = propertyDescriptors[i];
				// 获取Bean 各个属性名称
				propertyName = descriptor.getName();
				if (!StringUtils.equalsIgnoreCase(propertyName, "class")) {
					Method readMethod = descriptor.getReadMethod();
					// 获取 属性值
					Object result = readMethod.invoke(bean, new Object[0]);
					if ("null".equalsIgnoreCase(String.valueOf(result))
							|| StringUtils.isBlank(String.valueOf(result))) {
						result = "";
					}
					if (convertType == 1) {
						resMap.put(propertyName.toLowerCase(), result);
					} else if (convertType == 2) {
						resMap.put(propertyName.toUpperCase(), result);
					} else {
						resMap.put(propertyName, result);
					}

				}
			} catch (IllegalAccessException e) {// 如果实例化 JavaBean 失败
				e.printStackTrace();
			} catch (InvocationTargetException e) { // 如果调用属性的 setter 方法失败
				e.printStackTrace();
			}
		}
		return resMap;
	}

	/**
	 * @方法名称: BeanToMap
	 * @方法描述: 将一个 JavaBean 对象转化为一个 Map，其中的key要小写
	 *
	 * @param bean
	 *            要转化的JavaBean 对象
	 * @return 转化出来的 Map 对象
	 */
	public static Map<String, Object> beanToMapByLowerCase(Object bean) {
		try {
			return beanToMapByConvert(bean, 1);
		} catch (IntrospectionException e) { // 如果分析类属性失败
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @方法名称: BeanToMap
	 * @方法描述: 将一个 JavaBean 对象转化为一个 Map，其中的key要大写
	 *
	 * @param bean
	 *            要转化的JavaBean 对象
	 * @return 转化出来的 Map 对象
	 */
	public static Map<String, Object> beanToMapByUpperCase(Object bean) {
		try {
			return beanToMapByConvert(bean, 2);
		} catch (IntrospectionException e) { // 如果分析类属性失败
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @方法名称: BeanToMap
	 * @方法描述: 将一个 JavaBean 对象转化为一个 Map
	 *
	 * @param bean
	 *            要转化的JavaBean 对象
	 * @return 转化出来的 Map 对象
	 */
	public static Map<String, Object> beanToMap(Object bean) {
		try {
			return beanToMapByConvert(bean, null);
		} catch (IntrospectionException e) { // 如果分析类属性失败
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @方法名称: MapToBean
	 * @方法描述: 将一个 Map 对象转化为一个 JavaBean
	 * 
	 * @param map
	 *            包含属性值的 map
	 * @param clazz
	 *            要转化的类型
	 * @return 转化出来的 JavaBean 对象
	 */
	public static <T, K, V> Object mapToBean(Map<K, V> map, Class<T> clazz) {
		Object obj = null;
		try {
			// 获取类属性
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			// 创建 JavaBean 对象
			obj = clazz.newInstance();

			// 给 JavaBean 对象的属性赋值
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();

				if (map.containsKey(propertyName)) {
					// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
					Object value = map.get(propertyName);

					Object[] args = new Object[1];
					args[0] = value;

					descriptor.getWriteMethod().invoke(obj, args);
				}
			}

		} catch (IntrospectionException e) { // 如果分析类属性失败
			e.printStackTrace();
		} catch (InstantiationException e) {// 如果实例化 JavaBean 失败
			e.printStackTrace();
		} catch (IllegalAccessException e) { // 如果实例化 JavaBean 失败
			e.printStackTrace();
		} catch (InvocationTargetException e) { // 如果调用属性的 setter 方法失败
			e.printStackTrace();
		}
		return obj;
	}

	public static String toJson(Map<String, String> map) {
		if (map == null || map.size() == 0) {
			return null;
		}

		StringBuilder paras = new StringBuilder();
		paras.append("{");
		Iterator<Map.Entry<String, String>> ite = map.entrySet().iterator();
		while (ite.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) ite.next();
			paras.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue())
					.append("\"");
			if (ite.hasNext()) {
				paras.append(",");
			}
		}
		paras.append("}");
		return paras.toString();
	}
}
