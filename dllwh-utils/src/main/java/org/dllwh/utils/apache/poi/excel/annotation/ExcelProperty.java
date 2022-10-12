package org.dllwh.utils.apache.poi.excel.annotation;

import java.lang.annotation.*;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: Excel 文件的属性
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2022-10-06 11:54
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelProperty {
    /**
     * 是否需要添加序号列。若设置为true，将在第一列添加序号列
     */
    boolean isSerialNo() default false;

    /**
     * 序号名称，支持自定义
     */
    String serialName() default "序号";
}
