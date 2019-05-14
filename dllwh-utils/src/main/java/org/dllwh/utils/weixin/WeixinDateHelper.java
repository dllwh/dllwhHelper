package org.dllwh.utils.weixin;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 日期工具类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月14日 下午11:30:32
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class WeixinDateHelper {
	private static final String	yyyyMMdd		= "yyyyMMdd";
	private static final String	yyyy_MM_dd		= "yyyy-MM-dd";
	private static final String	yyyyMMddHHmmss	= "yyyyMMddHHmmss";

	/**
	 * 日期对象转换为yyyymmdd的字符串形式
	 * 
	 * @param date
	 *            日期对象
	 * @return
	 */
	public static String fortmat2yyyyMMdd(Date date) {
		return new SimpleDateFormat(yyyyMMdd).format(date);
	}

	/**
	 * 日期对象转换为yyyy_mm_dd的字符串形式
	 * 
	 * @param date
	 *            日期对象
	 * @return
	 */
	public static String fortmat2yyyy_MM_dd(Date date) {
		return new SimpleDateFormat(yyyy_MM_dd).format(date);
	}

	/**
	 * 日期对象转换为yyyymmddhhmmss的字符串形式
	 * 
	 * @param date
	 *            日期对象
	 * @return
	 */
	public static String fortmat2yyyyMMddHHmmss(Date date) {
		return new SimpleDateFormat(yyyyMMddHHmmss).format(date);
	}

	/**
	 * yyyymmddhhmmss形式的字符串转换为日期对象
	 * 
	 * @param date
	 *            日期字符串
	 * @return
	 */
	public static Date parse2yyyyMMddHHmmss(String date) {
		try {
			return new SimpleDateFormat(yyyyMMddHHmmss).parse(date);
		} catch (ParseException e) {

		}
		return null;
	}

	/**
	 * 单位为分的金额格式化为元的字符串形式
	 * 
	 * @param fee
	 *            金额 单位为分
	 * @return
	 */
	public static String formaFee2Fen(double fee) {
		return new DecimalFormat("#").format(fee * 100);
	}

	/**
	 * 当前时间戳转换为秒的字符串形式
	 * 
	 * @return
	 */
	public static String timestamp2string() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
}
