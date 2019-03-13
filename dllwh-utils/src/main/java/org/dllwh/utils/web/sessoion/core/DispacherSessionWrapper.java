package org.dllwh.utils.web.sessoion.core;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
	private Integer				expireTime;

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
			SessionDAO sessionDAO, Integer expireTime) {
		this.request = request;
		this.response = response;
		this.sid = sid;
		this.session = request.getSession();
		this.sessionDAO = sessionDAO;
		this.expireTime = expireTime;
	}

	@Override
	public Object getAttribute(String name) {
		if (sessionMap == null) {
			sessionMap = sessionDAO.getSession(sid);
		}
		try {
			return sessionMap.get(name);
		} catch (Exception e) {
			log.error("session 异常  getAttribute() name:" + name, e);
			return session.getAttribute(name);
		}
	}

	@Override
	public Enumeration getAttributeNames() {
		if(sessionMap == null ){
			sessionMap = sessionDAO.getSession(sid);
		}
		Set<Entry<String, Object>> sessinSet = sessionMap.entrySet();
		if (sessinSet != null && sessinSet.size() > 0) {
			return Collections.enumeration(sessinSet);
		} else {
			return session.getAttributeNames();
		}
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
	 * 方法描述: 获取最大有效 非活动时间（秒），默认为30分钟
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
		try {
			sessionDAO.removeSession(sid);
		} catch (Exception e) {
			session.invalidate();
		}
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
			session.removeAttribute(name);
			if (sessionMap != null && sessionMap.size() > 0) {
				sessionDAO.saveSession(sid, sessionMap, expireTime);
			} else {
				sessionDAO.removeSession(sid);
			}

		} catch (Exception e) {
			log.error("session 异常  removeAttribute() name:" + name, e);
			session.removeAttribute(name);
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
			sessionDAO.saveSession(sid, sessionMap, expireTime);
		} catch (Exception e) {
			log.error("session 异常  setAttribute() name:" + name + ",value:" + value, e);
		} finally {
			session.setAttribute(name, value);
		}
	}

	/**
	 * 方法描述: 设置最大有效 非活动时间
	 * 
	 * interval为0或者负数，表示该session一直有效
	 */
	@Override
	public void setMaxInactiveInterval(int interval) {
	}
}