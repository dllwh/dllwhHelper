package org.dllwh.utils.web.sessoion.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.dllwh.utils.web.sessoion.common.GlobalConstant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefinedHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private HttpSession			session;
	private HttpServletRequest	request;
	private HttpServletResponse	response;
	private SessionDAO			sessionDAO;
	private Integer				expireTime;

	public DefinedHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	public DefinedHttpServletRequestWrapper(HttpServletRequest request, HttpServletResponse response,
			SessionDAO sessionDAO, Integer expireTime) {
		super(request);
		this.request = request;
		this.response = response;
		this.sessionDAO = sessionDAO;
		this.expireTime = expireTime;
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

			String id = (String) request.getAttribute(GlobalConstant.JSESSIONID);
			if (StringUtils.isEmpty(id)) {
				if (log.isDebugEnabled()) {
					log.debug("getSession() new invoked!");
				}
				request.setAttribute(GlobalConstant.JSESSIONID, request.getSession().getId());
			}

			session = new DispacherSessionWrapper(request, response, id, sessionDAO, expireTime);
			return session;
		} else {
			return null;
		}
	}
}