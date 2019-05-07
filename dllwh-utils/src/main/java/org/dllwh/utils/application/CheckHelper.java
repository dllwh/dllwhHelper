package org.dllwh.utils.application;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 检验帮助类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月7日 下午11:17:52
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public final class CheckHelper {

	/**
	 * @方法描述: 判断对象是否为null
	 * @param object
	 * @return
	 */
	public static Boolean checkIsNull(Object object) {
		if (object == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @方法描述: 判断对象数组中的对象是否为null
	 * @param objects
	 * @return 对象数组中所有对象为null，则返回true，否则返回false。
	 */
	public static Boolean checkIsNull(Object[] objects) {
		if (objects == null) {
			return true;
		} else {
			for (Object o : objects) {
				if (!checkIsNull(o)) {
					return false;
				}
			}

			return true;
		}
	}

	/**
	 * @方法描述:判断list是否为空
	 * @param list
	 * @return list为null或空，返回true，否则返回false。
	 */
	public static Boolean checkIsEmpty(List<?> list) {
		if (checkIsNull(list) || list.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @方法描述: 判断list数组中的对象是否为空
	 * @param lists
	 * @return 数组中所有对象为null或为空，返回true，否则返回false。
	 */
	public static Boolean checkIsEmpty(List<?>[] lists) {
		if (checkIsNull(lists) || lists.length == 0) {
			return true;
		} else {
			for (List<?> list : lists) {
				if (!checkIsEmpty(list)) {
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * @方法描述: 判断set是否为空
	 * @param set
	 * @return set为null或空，返回true，否则返回false。
	 */
	public static Boolean checkIsEmpty(Set<?> set) {
		if (checkIsNull(set) || set.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @方法描述: 判断set数组中的对象是否为空
	 * @param sets
	 * @return 数组中所有对象为null或空，返回true，否则返回false。
	 */
	public static Boolean checkIsEmpty(Set<?>[] sets) {
		if (checkIsNull(sets) || sets.length == 0) {
			return true;
		} else {
			for (Set<?> set : sets) {
				if (!checkIsEmpty(set)) {
					return false;
				}
			}

			return true;
		}
	}

	/**
	 * @方法描述: 判断map是否为空
	 * @param map
	 * @return map为null或空，返回true，否则返回false。
	 */
	public static Boolean checkIsEmpty(Map<?, ?> map) {
		if (checkIsNull(map) || map.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @方法描述: 判断string是否为空
	 * @param string
	 * @return string为null或空，返回true，否则返回false。
	 */
	public static Boolean checkIsEmpty(String string) {
		if (StringUtils.isEmpty(string)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @方法描述: 判断string是否为空
	 * @param string
	 * @return string为null或空或者' '，返回true，否则返回false。
	 */
	public static Boolean checkIsBlank(String string) {
		if (StringUtils.isBlank(string)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @方法描述: 检测Integer 非空且大于0为真)
	 * @param integer
	 * @return
	 */
	public static boolean checkInteger(Integer integer) {
		if (integer != null && integer > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @方法描述: 检测Float 非空且大于0为真
	 * @param f
	 * @return
	 */
	public static boolean checkFloat(Float f) {
		if (f != null && f > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @方法描述: 检测Long 非空且大于0为真
	 * @param l
	 * @return
	 */
	public static boolean checkLong(Long l) {
		if (l != null && l > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @方法描述: 检测Double 非空且大于0为真
	 * @param value
	 * @return
	 */
	public static boolean checkDouble(Double value) {
		if (value != null && value > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @方法描述: 检测List 非空且大于0为真
	 * @param list
	 * @return
	 */
	public static boolean checkList(List<?> list) {
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}
}