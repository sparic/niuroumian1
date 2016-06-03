package com.myee.niuroumian.service.impl;

import com.myee.niuroumian.dao.DishDao;
import com.myee.niuroumian.domain.DishInfo;
import com.myee.niuroumian.service.WeixinService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ray.Fu on 2016/6/1.
 */
@Service
public class WeixinServiceImpl implements WeixinService{

    @Autowired
    private DishDao dishDao;

    @Autowired
       protected WeixinServiceImpl(DishDao dishDao) {
//        super(redisTemplate);
        this.dishDao = dishDao;
    }

    @Override
    public Map findQuickOrderMenu(Long storeId) {
        String quickMenu = "quickMenu";
        Map map = new HashMap();
//        List<DishInfo> list = hget(quickMenu, storeId.toString(), DishInfo.class);
        return map;
    }

    @Test
    public void initQuickMenu(Long storeId) {
        String quickMenuKey = "quickMenu";
//        hset(quickMenuKey, storeId.toString(), initDish("红烧牛肉拉面", BigDecimal.valueOf(15L), 1L), null);
//        hset(quickMenuKey, storeId.toString(), initDish("红烧牛肉刀削面", BigDecimal.valueOf(15L), 1L), null);
//        hset(quickMenuKey, storeId.toString(), initDish("红烧牛肉拌刀削面", BigDecimal.valueOf(15L), 1L), null);
    }

    public static DishInfo initDish(String dishName, BigDecimal dishPrice, Long storeId) {
        DishInfo dish = new DishInfo();
        dish.setDishName(dishName);
        dish.setDishPrice(dishPrice);
        dish.setStoreId(storeId);
        return dish;
    }

}
