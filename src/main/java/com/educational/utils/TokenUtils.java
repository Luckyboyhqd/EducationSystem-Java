package com.educational.utils;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

public class TokenUtils {
    public static ResultSet getFalg(HttpServletRequest request){

        boolean flag = Boolean.valueOf("" + request.getAttribute("flag"));
        boolean expire = Boolean.valueOf("" + request.getAttribute("expire")) ;
        if(!expire){
            //token过期
            return new ResultSet().setCode(HttpStatus.FORBIDDEN.value()).setMsg("已过期，请重新登陆");
        }
        if(!flag){
            //没有token
            return new ResultSet().setCode(HttpStatus.UNAUTHORIZED.value()).setMsg("请先登录");
        }
        return  null;
    }
}
