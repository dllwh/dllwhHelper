package org.dllwh.utils.apache.poi.excel.annotation;

import java.lang.annotation.*;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: Excel注解定义（用来注解bean中属性类型是list或bean对象，从而可以将Excel注解延伸到下一层Bean中）
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2022-10-04 14:52
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Excel {

}
