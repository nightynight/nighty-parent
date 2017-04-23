package com.brokepal.nighty.security.filter;

import com.brokepal.nighty.core.cache.Cache;
import com.brokepal.nighty.security.service.SecurityService;
import com.brokepal.nighty.security.storage_object.Session;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 对于带有"static/"的controller请求不进行过滤
 */
public class LoginFilter implements Filter {
	@Autowired
	private SecurityService securityService;

	private FilterConfig config;

	public void destroy() {

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) arg0;
		HttpServletResponse resp=(HttpServletResponse) arg1;
		resp.setHeader("Access-Control-Allow-Origin","*");

		//设置编码
		String charset=config.getInitParameter("charset");
		if (charset==null) {
			charset="UTF-8";//设置默认值
		}
		req.setCharacterEncoding(charset);
		
		//排除在配置文件中配置的不需要过滤的请求
		String noLoginPaths= config.getInitParameter("nonFilterPaths");
		if (noLoginPaths!=null) {
			String[] strArray=noLoginPaths.split(",");
			for (int i = 0; i < strArray.length; i++) {
				if (strArray[i]==null || "".equals(strArray[i].trim())) continue;
				if (req.getRequestURI().indexOf(strArray[i].trim())!=-1) {
					arg2.doFilter(arg0, arg1);
					return;
				}
			}
		}
		boolean isLogin = false;
		String username = req.getParameter("username");
		String sessionId = req.getParameter("sessionId");
		String token = req.getParameter("token");
		do {
			if (username == null || sessionId == null || token == null)
				break;
			Session session = securityService.getCurrentSession(username);
			if (session == null)
				break;
			if (!token.equals(session.getToken()))
				break;
			isLogin = true;
			securityService.resetLoginExpires(username);//重置缓存失效时间
		} while (false);
		if (isLogin){
			arg2.doFilter(arg0, arg1);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// 获取在配置文件中设置的init-param
		this.config=arg0;
	}
}
