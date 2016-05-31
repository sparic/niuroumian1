package com.myee.util;

import com.myee.domain.AbstractEntity;

import java.util.Date;

public final class POUtil {

    public static void initEntity(AbstractEntity entity) {
        if (null != entity) {
            entity.setClientID(100L);
            entity.setOrgID(100L);
            entity.setActive(true);
            entity.setCreatedBy(100L);
            entity.setCreated(new Date());
            entity.setUpdated(new Date());
            entity.setUpdatedBy(100L);
        }
    }
}
