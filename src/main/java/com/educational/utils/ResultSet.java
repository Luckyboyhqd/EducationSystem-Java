package com.educational.utils;

import java.io.Serializable;
import java.util.LinkedHashMap;

@SuppressWarnings("all")
public class ResultSet extends LinkedHashMap implements Serializable {
    // 状态码
    private int code = 406;
    // 消息
    private String msg = "内部错误";
    // 数据
    private Object data = "";
    // 时间戳
    private Long time = System.currentTimeMillis();

    public ResultSet() {
        this.put("code",code);
        this.put("msg",msg);
        this.put("data",data);
        this.put("time",time);
    }



	public ResultSet addStair(Object k,Object v) {
        this.put(k,v);
        return this;
    }

    public int getCode() {
        return code;
    }

    public ResultSet setCode(int code) {
        this.code = code;
        this.put("code",code);
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultSet setMsg(String msg) {
        this.msg = msg;
        this.put("msg",msg);
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResultSet setData(Object data) {
        this.data = data;
        this.put("data",data);
        return this;
    }

    public Long getTime() {
        return time;
    }

    public ResultSet setTime(Long time) {
        this.time = time;
        this.put("time",time);
        return this;
    }
}