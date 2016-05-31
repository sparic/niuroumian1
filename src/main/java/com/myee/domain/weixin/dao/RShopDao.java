package com.myee.domain.weixin.dao;

import com.myee.domain.weixin.model.RShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Martin on 2016/1/19.
 */
public interface RShopDao extends JpaRepository<RShop, Long> {

    @Query("SELECT s FROM RShop s WHERE s.clientID=:clientId")
    List<RShop> findByClientId(@Param("clientId") long clientId);
}
