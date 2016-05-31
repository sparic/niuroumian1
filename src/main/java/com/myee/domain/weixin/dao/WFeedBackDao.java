package com.myee.domain.weixin.dao;

import com.myee.domain.weixin.model.RWaitToken;
import com.myee.domain.weixin.model.WFeedBack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Ray.Fu on 2016/5/10.
 */
public interface WFeedBackDao extends JpaRepository<WFeedBack, Long> {

}
