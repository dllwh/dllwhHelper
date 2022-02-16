package org.dllwh.utils.apache.poi.excel;

import java.io.BufferedReader;
import java.io.InputStream;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: POI提供API给Java程序对Microsoft Office格式档案读和写的功能
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月7日 下午11:53:59
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public final class ExcelHelperUtil {
    /**
     * 创建文件输入流
     */
    private BufferedReader reader = null;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件二进制输入流
     */
    private InputStream inputStream;
    /**
     * 当前的Sheet
     */
    private int currentSheet;
    /**
     * 当前位置
     */
    private int currentPosition;
}