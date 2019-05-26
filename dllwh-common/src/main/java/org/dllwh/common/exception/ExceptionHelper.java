package org.dllwh.common.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.SocketTimeoutException;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 异常信息参数配置加载类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月20日 上午1:34:35
 * @版本: V1.0
 * @since: JDK 1.8
 */
public final class ExceptionHelper extends ExceptionUtils {
	private static String	MARKED_WORDS_ONE	= "调用%s.%s()方法出现异常,原因是:%s";
	private static String	MARKED_WORDS_TWO	= "调用%s方法出现异常,原因是:%s";

	/**
	 * @方法描述: 异常提示语
	 * @param className
	 *            出现异常所在类
	 * @param methodName
	 *            出现异常所在方法
	 * @param tips
	 *            出现异常的提示
	 */
	public static void getExceptionHint(String className, String methodName, String tips) {
		throw new IllegalArgumentException(String.format(MARKED_WORDS_ONE, className, methodName, tips));
	}

	/**
	 * @方法描述: 异常提示语
	 * @param className
	 *            出现异常所在类
	 * @param tips
	 *            出现异常的提示
	 */
	public static void getExceptionHint(String className, String tips) {
		throw new IllegalArgumentException(String.format(MARKED_WORDS_TWO, className, tips));
	}

	public static void catchHttpUtilException(Exception e, String url) {

		if (e instanceof NullPointerException) {
			throw new IllegalArgumentException("请求通信[" + url + "]时空指针异常,堆栈轨迹如下", e);
		}

		/**
		 * 解析异常 <br/>
		 * 该异常是否是 ParseException 的实例
		 */
		if (e instanceof ParseException) {
			throw new RuntimeException("请求通信[" + url + "]时解析异常,堆栈轨迹如下", e);
		}
		/** 读取超时 */
		if (e instanceof SocketTimeoutException) {
			throw new RuntimeException("请求通信[" + url + "]时读取超时,堆栈轨迹如下", e);
		}
		/** 连接超时 */
		if (e instanceof ConnectTimeoutException) {
			throw new RuntimeException("请求通信[" + url + "]时连接超时,堆栈轨迹如下", e);
		}
		/** 网络异常 */
		if (e instanceof IOException) {
			// 该异常通常是网络原因引起的,如HTTP服务器未启动等
			throw new RuntimeException("请求通信[" + url + "]时网络异常,堆栈轨迹如下", e);
		}

		/** 网络协议 */
		if (e instanceof ClientProtocolException) {
			throw new RuntimeException("请求通信[" + url + "]时网络协议异常,堆栈轨迹如下", e);
		}
		if (e instanceof Exception) {
			throw new RuntimeException("请求通信[" + url + "]时偶遇异常,堆栈轨迹如下", e);
		}
	}

	/**
	 * @方法描述:返回错误信息字符串
	 * @param exception
	 * @return
	 */
	public static String getExceptionMessage(Exception exception) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		return sw.toString();
	}

	/**
	 * @方法描述:将CheckedException转换为UncheckedException
	 */
	public static RuntimeException unchecked(Exception e) {
		if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		} else {
			return new RuntimeException(e);
		}
	}
}