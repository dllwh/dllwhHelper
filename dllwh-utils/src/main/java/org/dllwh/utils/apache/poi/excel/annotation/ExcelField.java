package org.dllwh.utils.apache.poi.excel.annotation;

import java.lang.annotation.*;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: Excel注解定义（用来配置Excel与Bean的关系）
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2022-10-03 21:25
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {
    /**
     * 字段标题，即列名
     */
    String title();

    /**
     * 字段对齐方式（0：自动；1：靠左；2：居中；3：靠右）
     */
    int align() default 0;

    /**
     * 字段字段排序（升序，与Excel中列号对应）
     */
    int orderNum();
    /**
     * 如果是枚举类型，请设置枚举全路径类名
     */
    String enumClass() default "";

    /**
     * 如果是枚举类型，枚举取值返回：0：枚举code；1：枚举name；：2：枚举code + name：
     */
    int enumType() default 0;


    /**
     * 字段值是否为公式(true表示是，false表示不是，只在导出使用)
     */
    boolean isFormula() default false;

    /**
     * 是否只读取公式，不读取值（true表示读取公式，false表示读取值，只用于导入使用）
     */
    boolean readFormula() default false;

    /**
     * 是否隐藏列(true表示是，false表示不是)
     */
    boolean isColumnHidden() default false;

    /**
     * 文字后缀,如% 90 变成90%
     */
    String suffix() default "";

    /**
     * 是否换行 即支持\n
     */
    boolean isWrap() default false;

    /**
     * 超链接,如果是需要实现接口返回对象
     */
    boolean isHyperlink() default false;
}
