package org.dllwh.utils.message.unicode;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.dllwh.common.constanst.ConstantHelper;


/**
 * @类描述: 编码相关的封装类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月14日 下午10:51:04
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class CharsetHelper {
	/** ----------------------------------------------------- Fields start */

	/**
	 * 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块
	 */
	public static final String US_ASCII = ConstantHelper.US_ASCII.name();

	/**
	 * ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1
	 */
	public static final String ISO_8859_1 = ConstantHelper.ISO8859_1.name();

	/**
	 * 8 位 UCS 转换格式
	 */
	public static final String UTF_8 = ConstantHelper.UTF_8.name();

	/**
	 * 中文超大字符集
	 */
	public static final String GBK = ConstantHelper.GBK.name();;

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	public final static String changeCharset(String str, String newCharset)
			throws UnsupportedEncodingException {
		if (str != null) {
			// 用默认字符编码解码字符串。
			byte[] bs = str.getBytes();
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	/**
	 * @方法描述: 字符串编码转换的实现方法
	 * @param str
	 *            待转换编码的字符串
	 * @param oldCharset
	 *            原编码 // * @param newCharset 目标编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public final static String changeCharset(String str, String oldCharset, String newCharset)
			throws UnsupportedEncodingException {
		if (str != null) {
			// 用旧的字符编码解码字符串。解码可能会出现异常。
			byte[] bs = str.getBytes(oldCharset);
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	/** ----------------------------------------------- [私有方法] */

	/**
	 * 将字符编码转换成US-ASCII码
	 */
	public final static String toASCII(String str) throws UnsupportedEncodingException {
		return changeCharset(str, US_ASCII);
	}

	/**
	 * 将字符编码转换成ISO-8859-1码
	 */
	public final static String toISO_8859_1(String str) throws UnsupportedEncodingException {
		return changeCharset(str, ISO_8859_1);
	}

	/**
	 * 将字符编码转换成UTF-8码
	 */
	public static String toUTF_8(String str) throws UnsupportedEncodingException {
		return changeCharset(str, UTF_8);
	}

	/**
	 * 将字符编码转换成GBK码
	 */
	public final static String toGBK(String str) throws UnsupportedEncodingException {
		return changeCharset(str, GBK);
	}

	/**
	 * Unicode转换成GBK字符集
	 *
	 * @param input
	 *            待转换字符串
	 * @return 转换完成字符串
	 */
	public final static String toGBKWithUTF8(String input) throws UnsupportedEncodingException {
		if (StringUtils.isEmpty(input)) {
			return "";
		} else {
			String s1;
			s1 = new String(input.getBytes("ISO8859_1"), "GBK");
			return s1;
		}
	}

	/**
	 * GBK转换成Unicode字符集
	 *
	 * @param input
	 *            待转换字符串
	 * @return 转换完成字符串
	 */
	public final static String toUnicodeWithGBK(String input) throws UnsupportedEncodingException {
		if (StringUtils.isEmpty(input)) {
			return "";
		} else {
			String s1;
			s1 = new String(input.getBytes("GBK"), "ISO8859_1");
			return s1;
		}
	}

	/**
	 * @方法描述: 处理乱码
	 * @param str
	 * @return
	 */
	public static String UnicodeToString(String str) {
		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			char ch = (char) Integer.parseInt(matcher.group(2), 16);
			str = str.replace(matcher.group(1), ch + "");
		}
		return str;
	}
}
