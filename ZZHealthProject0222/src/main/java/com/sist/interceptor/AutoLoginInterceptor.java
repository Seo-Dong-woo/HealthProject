package com.sist.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// xml <mvc:intercepter>
/*
 * 									   | => preHandle(자동 로그인 , ID저장)
 *   main.do ==== DispatcherServlet ========= HandlerMapping
 *                                            url주소를 이용해서 해당 메소드 찾기
 *                                            @GetMapping("main/main.do")
 *                                            public String main_main()
 * 												 |
 * 												 |  ==> return ""
 * 												 |  -- postHandle
 * 												VueResolver
 * 												  |
 * 												  | ==> Model(request)
 *   												 --afterCompletion
 * 												  |
 * 												JSP
 */
public class AutoLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		Cookie[] cookies=request.getCookies();
		if(cookies!=null)
		{
			for(int i=0;i<cookies.length;i++)
			{
				String key=cookies[i].getName(); //key는 중복이없음
				if(key.equals("userId"))
				{
					String id=cookies[i].getValue();
					request.setAttribute("userId", id);
					break;
				}
			}
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	
}
