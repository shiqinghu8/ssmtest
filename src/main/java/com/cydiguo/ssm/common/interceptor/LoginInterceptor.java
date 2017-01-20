package com.cydiguo.ssm.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 登陆拦截器.
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	/*
	 * preHandle()：这个方法在业务处理器处理请求之前被调用，在该方法中对用户请求request进行处理。
	 * 如果程序员决定该拦截器对请求进行拦截处理后还要调用其他的拦截器，或者是业务处理器去进行处理，则返回true；
	 * 如果程序员决定不需要再调用其他的组件去处理请求，则返回false。
	 * 
	 * postHandle()：这个方法在业务处理器处理完请求后，但是DispatcherServlet向客户端返回请求前被调用，
	 * 在该方法中对用户请求request进行处理。
	 * 
	 * afterCompletion()：这个方法在DispatcherServlet完全处理完请求后被调用，可以在该方法中进行一些资源清理的操作。
	 */
	private static final String[] IGNORE_URI = { "/login.jsp", "/Login/", "backui/", "frontui/" };

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean flag = false;
		System.out.println("进入拦截器");
		flag = true;

		return flag;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// ..
	}
}