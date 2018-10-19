package org.dllwh.common.constanst;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 监控组件常量类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月20日 上午12:30:07
 * @版本: V1.0
 * @since: JDK 1.8
 */
public final class MonitorConstanst {
	/** 监控的目录信息 */
	public static List<String> dirPaths = new ArrayList<String>();
	/** 默认监控类型中不需要监控的类型 */
	public static List<String> excludeTypeList = new ArrayList<String>();
	/** 默认监控类型之外需要监控的类型 */
	public static List<String> includeTypeList = new ArrayList<String>();
	/** http reqeust 不监控的url 后缀 */
	public static List<String> filterSuffixs = new ArrayList<String>();
	/** 定时器时间 */
	public static int monitorInterval = 60;
	/** 监控日志文件存储路径 */
	public static String dataPath = File.separator;
	/** 监控日志文件，默认存放路径 */
	public static String dataPathDefault = "monitorlog" + File.separator;

	public static boolean isOpenHttpSession = false;
	public static boolean isOpenHttpRequest = true;
	public static boolean isOpenSql = false;
}
