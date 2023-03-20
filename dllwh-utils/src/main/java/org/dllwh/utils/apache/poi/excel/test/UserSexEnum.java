package org.dllwh.utils.apache.poi.excel.test;

import org.dllwh.utils.apache.poi.excel.core.BaseCommonEnum;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2022-10-04 21:42
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public enum UserSexEnum implements BaseCommonEnum {
    /**
     *
     */
    male("0", "男"),
    female("1", "女"),
    unknown("-1", "未知");

    private final String typeCode;
    private final String typeName;


    UserSexEnum(String typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    /**
     * 获取枚举value
     *
     * @return 枚举value
     */
    @Override
    public String getTypeName() {
        return typeName;
    }

    /**
     * 获取 枚举key
     *
     * @return 枚举key
     */
    @Override
    public String getTypeCode() {
        return typeCode;
    }
}
