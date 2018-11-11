package org.dllwh.utils.apache.collection;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 数组工具类(继承于Apache)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年11月11日 下午10:58:25
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ArrayUtilHelper extends ArrayUtils {
	/**
	 * 
	 * @方法名称: converCharToInt
	 * @方法描述: 将字符数组转换成数字数组
	 * 
	 * @param ca
	 * @return
	 */
	public static int[] converCharToInt(char[] ca) {
		int len = ca.length;
		int[] iArr = new int[len];
		try {
			for (int i = 0; i < len; i++) {
				iArr[i] = Integer.parseInt(String.valueOf(ca[i]));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return iArr;
	}

	/** 清除字符串数组中的null */
	public static String[] clearNull(String[] array) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				list.add(array[i]);
			}
		}
		return list.toArray(new String[list.size()]);
	}
}