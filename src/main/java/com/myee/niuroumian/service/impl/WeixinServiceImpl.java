package com.myee.niuroumian.service.impl;

import com.myee.niuroumian.dao.DishDao;
import com.myee.niuroumian.service.RedisOperation;
import com.myee.niuroumian.service.WeixinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ray.Fu on 2016/6/1.
 */
public class WeixinServiceImpl extends RedisOperation implements WeixinService{

    @Autowired
    private DishDao dishDao;

    protected WeixinServiceImpl(RedisTemplate redisTemplate) {
        super(redisTemplate);
    }

    @Override
    public Map findQuickOrderMenu() {
        String quickMenu = "quickMenu";
        Map map = new HashMap();
        map = hgetall(quickMenu);
        return map;
    }

}
