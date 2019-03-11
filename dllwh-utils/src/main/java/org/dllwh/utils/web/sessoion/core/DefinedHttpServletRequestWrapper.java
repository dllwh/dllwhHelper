package org.dllwh.utils.web.sessoion.core;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.dllwh.utils.web.CookieHelper;
import org.dllwh.utils.web.sessoion.common.GlobalConstant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefinedHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private HttpSession			session;
	private HttpServletRequest	request;
	private HttpServletResponse	response;
	private SessionDAO			sessionDAO;
	private Integer				cookieExpireTime;

	public DefinedHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	public DefinedHttpServletRequestWrapper(HttpServletRequest request, HttpServletResponse response,
			SessionDAO sessionDAO, Integer cookieExpireTime) {
		super(request);
		this.request = request;
		this.response = response;
		this.sessionDAO = sessionDAO;
		this.cookieExpireTime = cookieExpireTime;
	}

	/**
	 * 重写获取session的方法
	 */
	public HttpSession getSession() {
		if (log.isDebugEnabled()) {
			log.debug("getSession() invoked!");
		}
		return this.getSession(true);
	}

	/**
	 * 自定义的HttpSession实现
	 */
	public HttpSession getSession(boolean create) {
		if (create) {
			// 从cookie中获取sessionId，如果此次请求没有sessionId，重写为这次请求设置一个sessionId
			String id = CookieHelper.getCookieValue(request, GlobalConstant.JSESSIONID);
			if (StringUtils.isEmpty(id)) {
				if (log.isDebugEnabled()) {
					log.debug("creating new Session object!");
				}
				id = UUID.randomUUID().toString();
				if (cookieExpireTime == null || cookieExpireTime < 0) {
					cookieExpireTime = GlobalConstant.COOKIE_EXPIRE_TIME;
				}
				CookieHelper.setCookie(request, response, GlobalConstant.JSESSIONID, id, cookieExpireTime);
			}

			this.session = new DispacherSessionWrapper(this.request, this.response, id, sessionDAO);
			return this.session;
		} else {
			return null;
		}
	}
}