package com.lyc.hik.common.hik;

import java.util.HashMap;

/**
 * 返回数据结果
 *
 * @author lyc
 */
public class HikRst extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public HikRst() {
        put("code", HikConst.CODE_200);
        put("msg", "请求成功！");
    }

    public static HikRst error() {
        return error(HikConst.CODE_201, "未知异常，请联系管理员");
    }


    public static HikRst error(String msg) {
        HikRst r = new HikRst();
        r.put("code", HikConst.CODE_201);
        r.put("msg", msg);
        return r;
    }

    public static HikRst error(Integer code, String msg) {
        HikRst r = new HikRst();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static HikRst error(Integer code, String msg, Object data) {
        HikRst r = new HikRst();
        r.put("code", code);
        r.put("msg", msg);
        r.put("data", data);
        return r;
    }

    public static HikRst ok(String data) {
        HikRst r = new HikRst();
        r.put("data", data);
        return r;
    }

    public static HikRst ok() {
        return new HikRst();
    }

    public static HikRst ok(Object data) {
        return new HikRst().put("data", data);
    }

    @Override
    public HikRst put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}