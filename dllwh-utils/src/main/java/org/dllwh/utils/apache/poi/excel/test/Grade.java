package org.dllwh.utils.apache.poi.excel.test;

import lombok.*;
import org.dllwh.utils.apache.poi.excel.annotation.ExcelField;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2022-10-12 23:16
 * @版本: V 1.0.1
 * @since: JDK 1.8
 * @see <a href="">TODO(连接内容简介)</a>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    @ExcelField(title = "年级名称", align = 1, orderNum = 3)
    private String gradeName;
}
