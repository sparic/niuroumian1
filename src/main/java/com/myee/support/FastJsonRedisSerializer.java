package com.myee.support;

import com.alibaba.fastjson.JSON;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.nio.charset.Charset;

/**
 * Created by Martin on 2016/1/15.
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {
    private final Charset  charset;
    private       Class<T> type;

    public FastJsonRedisSerializer(Class<T> type) {
        this(type, Charset.forName("UTF8"));
    }

    public FastJsonRedisSerializer(Class<T> type, Charset charset) {
        Assert.notNull(type);
        this.type = type;
        this.charset = charset;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        return JSON.toJSONBytes(t);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (null == bytes) {
            return null;
        }
        return JSON.parseObject(bytes, type);
    }
}
