package com.myee.domain.weixin.dao;

import com.myee.domain.restaurant.model.RQueuing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Ray.Fu on 2016/3/28.
 */
public interface RQueuingDao extends JpaRepository<RQueuing, Long> {

   /* @Query("SELECT t FROM RQueuing t WHERE t.adOrgId=:orgID and t.openId=:openId")
    List selectLatestDevelopment(@Param("adOrgId") long orgID, @Param("openId") String openId);*/
}
