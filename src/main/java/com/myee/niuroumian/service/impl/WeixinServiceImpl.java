package com.myee.niuroumian.service.impl;

import com.myee.niuroumian.dao.DishDao;
import com.myee.niuroumian.domain.DishInfo;
import com.myee.niuroumian.service.WeixinService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
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
        Map map = new HashMap();
        List<DishInfo> list = dishDao.queryAllDishByStoreId(1L);
        map.put("dishList","test");
        /*map.put("dishList","<a href=\"www.baidu.com\">" + list.get(0).getDishName()+"</a>" + "\n"
                + "<a href=\"www.baidu.com\">" + list.get(1).getDishName() +"</a>" + "\n"
                + "<a href=\"www.baidu.com\">" + list.get(2).getDishName() +"</a>");*/
//        List<DishInfo> list = hget(quickMenu, storeId.toString(), DishInfo.class);
        return map;
    }

}
