package org.dllwh.utils.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: cookie帮助类
 * @创建者: 独泪了无痕
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年3月8日 下午9:34:46
 * @版本: V 1.0.1
 * @since: JDK 1.7
 */
public class CookieHelper {
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name,
			String value, int seconds) {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(value)) {
			return;
		}
		Cookie cookie = new Cookie(name, value);
		// cookie.setDomain(domain);
		cookie.setMaxAge(seconds); // 设置过期时间，以秒为单位
		cookie.setPath("/");
		response.setHeader("P3P", "CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");
		response.addCookie(cookie);
	}

	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies(); // 获取所有的cookie 对象
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (name.equalsIgnoreCase(cookies[i].getName())) {
					return cookies[i].getValue();
				}
			}
		}
		return "";
	}
}