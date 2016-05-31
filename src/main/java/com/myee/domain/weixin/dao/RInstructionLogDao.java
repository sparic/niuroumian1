package com.myee.domain.weixin.dao;

import com.myee.domain.weixin.model.RInstructionLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Martin on 2016/1/19.
 */
public interface RInstructionLogDao extends JpaRepository<RInstructionLog, Long> {
}
