package org.dllwh.utils.security;

import java.security.MessageDigest;

/**
 * @类描述: 散列算法（签名算法）:MD5是一种不可逆的加密算法，目前是最牢靠的加密算法之一，尚没有能够逆运算的程序被开发出来，
 *       它对应任何字符串都可以加密成一段唯一的固定长度的代码。
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年4月8日 上午11:45:05
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class Md5Helper {
	/**
	 * @方法描述: MD5加密
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年6月8日 上午11:05:20
	 * @param plainText
	 * @param num
	 * @return
	 */
	public static String md5(String plainText, int num) {
		StringBuffer buf = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (num == 16) {
			return buf.toString().substring(8, 24);
		} else {
			return buf.toString();
		}
	}
}