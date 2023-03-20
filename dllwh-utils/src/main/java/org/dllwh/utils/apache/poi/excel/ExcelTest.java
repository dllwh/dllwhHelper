package org.dllwh.utils.apache.poi.excel;

import com.google.common.collect.Lists;
import org.dllwh.utils.apache.poi.excel.test.*;
import org.junit.*;

import java.io.File;
import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2022-10-04 14:56
 * @版本: V 1.0.1
 * @since: JDK 1.8
 * @see <a href="">TODO(连接内容简介)</a>
 */
public class ExcelTest<E extends Enum<E>> {
    private List<UserInfo> userInfoList = Lists.newArrayList();

    @Before
    public void initData() {
        for (int i = 1; i < 12; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName("用户-" + i);
            userInfo.setUserAccount("账号—" + System.currentTimeMillis());
            userInfo.setSex(i % 2);

            userInfo.setGrade(new Grade("三年五载_" + i));

            List<Achievement> achievementList = Lists.newArrayList();
            for (int j = 0; j < 3; j++) {
                String subjectName = "测试_" + i + "**" + j;
                achievementList.add(new Achievement(subjectName, j));
            }
            userInfo.setAchievement(achievementList);

            userInfoList.add(userInfo);
        }
        Class<?> cls = UserInfo.class;

    }

    @Test
    public void exportExcelTest() throws Exception {
        ExportExcel excel = new ExportExcel();
        excel.addSheet("基本信息", UserInfo.class);
        excel.setDataList(userInfoList);

        String fileName = "/Users/dllwh/Downloads/基本信息.xlsx";
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        excel.writeFile(fileName);
    }
}

