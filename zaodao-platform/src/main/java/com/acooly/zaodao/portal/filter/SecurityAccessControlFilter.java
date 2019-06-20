package com.acooly.zaodao.portal.filter;

import com.acooly.core.utils.Servlets;
import com.acooly.core.utils.Strings;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 前端portal认证和权限控制
 *
 */
public class SecurityAccessControlFilter implements Filter {

	private String sessionKey = "sessionKey";
	private String loginUrl = "/login";
	private List<String> exclusions = new ArrayList<String>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String configSessionKey = filterConfig.getInitParameter("sessionKey");
		if (StringUtils.isNotBlank(configSessionKey)) {
			this.sessionKey = configSessionKey;
		}
		this.loginUrl = filterConfig.getInitParameter("loginUrl");
		String ignores = filterConfig.getInitParameter("exclusions");
		if (StringUtils.isNotBlank(ignores)) {
			String[] ignoreArray = ignores.split(",");
			for (int i = 0; i < ignoreArray.length; i++) {
				if (StringUtils.isNotBlank(ignoreArray[i])) {
					exclusions.add(ignoreArray[i]);
				}
			}
		}
		Collections.sort(exclusions);
		Collections.reverse(exclusions);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
	        throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String requestUrl = Servlets.getRequestPath(request);
		if (!Strings.endsWith(requestUrl, ".jsp") && !Strings.endsWith(requestUrl, ".html")) {
			chain.doFilter(request, response);
			return;
		}

		String userAgent = request.getHeader("User-Agent");
		if (Strings.contains(userAgent, "MicroMessenger")) {
			request.setAttribute("isWechat", true);
		}

		PathMatcher pathMatcher = new AntPathMatcher();
		boolean isIgnore = false;
		if (!exclusions.isEmpty()) {
			for (String ignoreUrl : exclusions) {
				isIgnore = pathMatcher.match(ignoreUrl, requestUrl);
				if (isIgnore) {
					break;
				}
			}
		}
		if (isIgnore || !requiresAuthentication(request, response)) {
			chain.doFilter(request, response);
			return;
		}
		request.setAttribute("originalUrl", requestUrl);
		response.sendRedirect(loginUrl);
		// request.getRequestDispatcher(loginUrl).forward(request, response);
	}

	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		return getSessionKey(request) == null;
	}

	protected Object getSessionKey(HttpServletRequest request) {
		return request.getSession().getAttribute(sessionKey);
	}

	@Override
	public void destroy() {

	}

}
