package org.dllwh.utils.apache.poi.excel.test;

import lombok.*;
import org.dllwh.utils.apache.poi.excel.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2022-10-04 18:34
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
@Getter
@Setter
@ExcelProperty(isSerialNo = false, serialName = "序号列")
public class UserInfo implements Serializable {
    @ExcelField(title = "用户名称", align = 2, orderNum = 0)
    private String userName;
    @ExcelField(title = "用户账号", align = 1, orderNum = 1)
    private String userAccount;
    @ExcelField(title = "性别", align = 2, orderNum = 2, enumClass = "org.dllwh.utils.apache.poi.excel.test.UserSexEnum", enumType = 2)
    private int sex;
    @Excel
    private Grade grade;
    @Excel
    private List<Achievement> achievement;
}
