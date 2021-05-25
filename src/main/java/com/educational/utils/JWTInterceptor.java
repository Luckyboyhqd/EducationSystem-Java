package com.educational.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}
	//拦截器 拦截未登录用户的请求
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) {
	
		com.educational.utils.JWTUtils util = new com.educational.utils.JWTUtils();
		//获取token
		String jwt = request.getHeader("Authorization");

		try {

			//如果没有token，设置flag为false
			if (jwt == null) {
				request.setAttribute("flag", false);
			} else {
				Map<String ,Object> payLoad = Jwts.parser().setSigningKey(com.educational.utils.JWTUtils.generalKey()).parseClaimsJws(jwt).getBody();
				String id = (String) payLoad.get("id");
				com.educational.utils.ThreadLocalUtils.setDate(id);
				//验证token,成功后设置flag为true
				Claims c;
				c = util.parseJWT(jwt);
				System.out.println("用户id" + c.get("id") + "已是登录状态");
				request.setAttribute("flag", true);
				request.setAttribute("id",id);
				request.setAttribute("expire","true");
				return true;
			}
		} catch (ExpiredJwtException e) {
			request.setAttribute("expire","false");
			com.educational.utils.ThreadLocalUtils.clearThread();
		}
		return true;
	}

}