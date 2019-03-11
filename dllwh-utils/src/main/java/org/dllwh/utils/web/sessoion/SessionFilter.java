package org.dllwh.utils.web.sessoion;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.dllwh.utils.web.sessoion.common.GlobalConstant;
import org.dllwh.utils.web.sessoion.core.DefinedHttpServletRequestWrapper;
import org.dllwh.utils.web.sessoion.core.SessionDAO;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 定义请求经过的Session过滤器
 * @创建者: 独泪了无痕
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年3月8日 下午8:50:38
 * @版本: V 1.0.1
 * @since: JDK 1.7
 */
@Slf4j
public class SessionFilter implements Filter {
	private SessionDAO			sessionDAO;
	private static final String	regex	= ".*(css|ico|html|jpg|jpeg|png|gif|js)";
	private Integer				cookieExpireTime;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String requestedUri = httpRequest.getRequestURL().toString();
		if (requestedUri.matches(regex)) { // 过滤
			filterChain.doFilter(request, response);
			return;
		}
		// 交给自定义的HttpServletRequestWrapper处理
		httpRequest = new DefinedHttpServletRequestWrapper(httpRequest, httpResponse, sessionDAO,
				cookieExpireTime);
		filterChain.doFilter(httpRequest, httpResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String className = filterConfig.getInitParameter(GlobalConstant.SESSIONHANDLER);
		cookieExpireTime = Integer.valueOf(filterConfig.getInitParameter(GlobalConstant.EXPIRETIME));

		if (StringUtils.isEmpty(className)) {
			log.error("获取初始化参数sessionHandler失败！");
			return;
		}
		try {
			sessionDAO = (SessionDAO) (Class.forName(className).newInstance());
		} catch (Exception e) {
			log.error("实例化SessionHandler失败：" + e);
		}
	}
}