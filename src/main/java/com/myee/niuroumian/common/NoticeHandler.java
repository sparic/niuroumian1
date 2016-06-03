package com.myee.niuroumian.common;

import com.alibaba.fastjson.JSON;
import com.myee.niuroumian.response.Headers;
import com.myee.niuroumian.response.Response;
import com.myee.niuroumian.util.StringUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class NoticeHandler<Tin, Tout> {

    private String noticeType;
    private Class<Tin> clazz;
    private Type type;

    @SuppressWarnings("rawtypes")
    public Class getGenericType(int index) {
        Type genType = getClass().getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            throw new RuntimeException("Index outof bounds");
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public abstract Response<Tout> onSuccess(Headers headers, Tin in);

    public abstract void onError(int code, String message);

    protected Tin parseByClass(String body) {
        return JSON.parseObject(body, clazz);
    }

    protected Tin parseByType(String body) {
        return JSON.parseObject(body, type);
    }
}
