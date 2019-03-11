package org.dllwh.utils.web.sessoion.core;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: HttpSession实现
 * @创建者: 独泪了无痕
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年3月8日 下午6:07:17
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Slf4j
@SuppressWarnings({ "unused", "deprecation" })
public class DispacherSessionWrapper implements HttpSession {
	private HttpSession			session;
	private String				sid			= "";
	private SessionDAO			sessionDAO;
	private Map<String, Object>	sessionMap	= null;
	private HttpServletRequest	request;
	private HttpServletResponse	response;

	public DispacherSessionWrapper() {
	}

	public DispacherSessionWrapper(HttpSession session) {
		this.session = session;
	}

	public DispacherSessionWrapper(String sid, HttpSession session) {
		this.session = session;
		this.sid = sid;
	}

	public DispacherSessionWrapper(HttpServletRequest request, HttpServletResponse response, String sid,
			SessionDAO sessionDAO) {
		this.request = request;
		this.response = response;
		this.sid = sid;
		this.session = request.getSession();
		this.sessionDAO = sessionDAO;
	}

	@Override
	public Object getAttribute(String name) {
		if (sessionMap == null) {
			sessionMap = sessionDAO.getSession(sid);
		}
		try {
			return sessionMap.get(name);
		} catch (Exception e) {
			throw new RuntimeException("session 异常  getAttribute() name:" + name, e);
		}
	}

	@Override
	public Enumeration getAttributeNames() {
		return session.getAttributeNames();
	}

	@Override
	public long getCreationTime() {
		if (log.isDebugEnabled()) {
			log.debug("获取创建会话时间");
		}
		return session.getCreationTime();
	}

	@Override
	public String getId() {
		return sid;
	}

	@Override
	public long getLastAccessedTime() {
		if (log.isDebugEnabled()) {
			log.debug("获取会话最后访问的时间");
		}
		return session.getLastAccessedTime();
	}

	/**
	 * 方法描述: 获取最大有效 非活动时间
	 */
	@Override
	public int getMaxInactiveInterval() {
		return session.getMaxInactiveInterval();
	}

	@Override
	public ServletContext getServletContext() {
		return session.getServletContext();
	}

	@Override
	public HttpSessionContext getSessionContext() {
		return null;
	}

	@Override
	public Object getValue(String name) {
		if (sessionMap == null) {
			sessionMap = sessionDAO.getSession(sid);
		}
		return sessionMap.get(name);
		// return session.getValue(name);
	}

	@Override
	public String[] getValueNames() {
		return null;
	}

	/**
	 * @方法描述 : 使session失效
	 */
	@Override
	public void invalidate() {
		if (sessionMap != null) {
			sessionMap.clear();
		}
		sessionDAO.removeSession(sid);
		// CookieHelper.setCookie(request, response, GlobalConstant.JSESSIONID,
		// sid, -1);
	}

	/**
	 * 方法描述：判断是否是新用户（第一次访问）
	 */
	@Override
	public boolean isNew() {
		return false;
	}

	@Override
	public void putValue(String name, Object value) {
	}

	/**
	 * @方法描述 : 移除
	 * @param name
	 */
	@Override
	public void removeAttribute(String name) {
		if (sessionMap == null) {
			sessionMap = sessionDAO.getSession(sid);
		}
		try {
			sessionMap.remove(name);
			sessionDAO.removeSession(sid);
			// CookieHelper.setCookie(request, response,
			// GlobalConstant.JSESSIONID, sid, -1);
		} catch (Exception e) {
			throw new RuntimeException("session 异常  removeAttribute() name:" + name, e);
		}
	}

	@Override
	public void removeValue(String name) {
	}

	/**
	 * @方法描述 : 存储
	 * @param name
	 * @param value
	 */
	@Override
	public void setAttribute(String name, Object value) {
		if (sessionMap == null) {
			sessionMap = sessionDAO.getSession(sid);
		}
		try {
			sessionMap.put(name, value);
			sessionDAO.saveSession(sid, sessionMap, getMaxInactiveInterval());
		} catch (Exception e) {
			throw new RuntimeException("session 异常  setAttribute() name:" + name + ",value:" + value, e);
		}
	}

	/**
	 * 方法描述: 设置最大有效 非活动时间
	 */
	@Override
	public void setMaxInactiveInterval(int interval) {
	}
}