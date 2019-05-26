package org.dllwh.utils.weixin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.dllwh.common.weixin.WeiXinConstants;
import org.dllwh.utils.apache.lang.StringUtilHelper;
import org.dllwh.utils.security.AESHelper;
import org.dllwh.utils.security.HexUtilHelper;

/**
 * @类描述: 签名加密工具类
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年8月5日 下午4:29:30
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class WeiXinDigestUtilHelper {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	private static MessageDigest getDigest(final String algorithm) {
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (final NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: SHA1签名
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月8日 上午8:14:27
	 * @param content
	 *            待签名字符串
	 * @return 签名后的字符串
	 */
	public static String SHA1(String content) {
		byte[] data = StringUtilHelper.getBytesUtf8(content);
		return HexUtilHelper.encodeHexStr(getDigest(WeiXinConstants.SHA1).digest(data));
	}

	/**
	 * @方法描述: SHA签名
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月8日 上午8:14:27
	 * @param content
	 *            待签名字符串
	 * @return 签名后的字符串
	 */
	public static String SHA(String content) {
		byte[] data = StringUtilHelper.getBytesUtf8(content);
		return HexUtilHelper.encodeHexStr(getDigest(WeiXinConstants.SHA).digest(data));
	}

	/**
	 * @方法描述: MD5签名
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月8日 上午8:14:27
	 * @param content
	 *            待签名字符串
	 * @return 签名后的字符串
	 */
	public static String MD5(String content) {
		byte[] data = StringUtilHelper.getBytesUtf8(content);
		return HexUtilHelper.encodeHexStr(getDigest(WeiXinConstants.MD5).digest(data));
	}

	/**
	 * @方法描述 : 解密
	 * @param decryptData
	 * @param iv
	 * @param key
	 * @param decryptType
	 * @param decryMode
	 * @return
	 * @throws Exception
	 */
	public static String WXBizDataCrypt(String decryptData, String iv, String key, String decryptType,
			String decryMode) throws Exception {
		return AESHelper.decrypt(decryptData, iv, key, decryptType, decryMode);
	}
}
