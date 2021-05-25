package com.educational.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ThreadLocalUtils {

    private final static ThreadLocal<String> RESOURCE = new ThreadLocal<>();
    //将数据源放入ThreadLocal
    public static void setDate(String data){
       RESOURCE.set(data);
    }
    //获取数据
    public static String getData(){
        String data = RESOURCE.get();
        return  data;
    }
    //获取默认数据
    public static String getDataSource(HttpServletRequest request, HttpServletResponse response, Object arg2){
        String data = RESOURCE.get();
        if(data == null){
            boolean r = new JWTInterceptor().preHandle(request,response,arg2);
            if(r){
                return getData();
            }
        }
        return data;
    }
    public static void clearThread(){
        RESOURCE.remove();
    }

}
