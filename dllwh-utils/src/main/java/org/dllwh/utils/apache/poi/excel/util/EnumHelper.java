package org.dllwh.utils.apache.poi.excel.util;

import com.google.common.collect.Maps;
import org.dllwh.utils.apache.poi.excel.core.BaseCommonEnum;

import java.util.*;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2022-10-18 23:59
 * @版本: V 1.0.1
 * @since: JDK 1.8
 * @see <a href="">TODO(连接内容简介)</a>
 */
public final class EnumHelper {
    private static <E extends Enum<E> & BaseCommonEnum> Map<String, Object> getEnumsMap(Class<E> clazz) {
        Map<String, Object> resultMap = Maps.newHashMap();
        EnumSet<E> all = EnumSet.allOf(clazz);
        all.forEach(e -> resultMap.put(e.getTypeCode(), e.getTypeName()));
        return resultMap;
    }

    /**
     * 获取枚举的所有值，且key、value以"-"拼接
     *
     * @param clazz 枚举class
     */
    public static Map<String, Object> getEnumMap(Class clazz) {
        return getEnumsMap(clazz);
    }
}
