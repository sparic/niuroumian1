package com.myee.domain.weixin.dao;

import com.myee.domain.weixin.model.RDrawToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Martin on 2016/1/19.
 */
public interface RDrawTokenDao extends JpaRepository<RDrawToken, Long> {
}
