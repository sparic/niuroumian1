package com.myee.domain.weixin.dao;

import com.myee.djinn.dto.WaitToken;
import com.myee.domain.weixin.model.RWaitToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Martin on 2016/1/19.
 */
public interface RWaitTokenDao extends JpaRepository<RWaitToken, Long> {
    @Modifying
    @Query("update RWaitToken rw set rw.state = ?1,rw.updated = FROM_UNIXTIME(?6) where rw.orgID = ?2 and rw.clientID = ?3 and rw.token = ?4 and UNIX_TIMESTAMP(rw.timeTook) = ?5")
    @Transactional
    int updateState(Integer state, Long shopId, Long clientId, String token, Long timeTook, Long updateTime);

    @Modifying
    @Query("update RWaitToken rw set rw.openId = ?1 where rw.identityCode = ?2 and UNIX_TIMESTAMP(rw.timeTook) = ?3")
    @Transactional
    int updateWaitTokenOpenId(String openId, String identityCode, Long date);

    @Modifying
    @Query("update RWaitToken rw set rw.waitedCount = ?1,rw.predictWaitingTime =?4 where rw.identityCode = ?2 and UNIX_TIMESTAMP(rw.timeTook) = ?3")
    @Transactional
    int modifyWaitingInfo(Long waitedCount, String identityCode, Long date, Long predictWaitingTime);

    @Query("select rw from RWaitToken rw where rw.identityCode = ?1 and UNIX_TIMESTAMP(rw.timeTook) >= ?2 and UNIX_TIMESTAMP(rw.timeTook) <= ?3")
    RWaitToken selectTokenByIc(String identityCode, Long beginTime, Long endTime);

    @Query("select rw from RWaitToken rw where rw.clientID = ?1 and rw.orgID = ?2 and rw.tableTypeId = ?3 and rw.state = ?4")
    List<RWaitToken> selectAllTokenByInfo(Long clientId, Long orgId, Long tableTypeId, Integer state);

    @Query("select rw from RWaitToken rw where rw.clientID = ?1 and rw.orgID = ?2 and rw.tableTypeId = ?3 and rw.state = ?4 and rw.openId is not null")
    List<RWaitToken> selectAllTokenOpenIdNotNull(Long clientId, Long orgId, Long tableTypeId, Integer state);

    @Query("select rw from RWaitToken rw where rw.openId = ?1 and rw.state = ?2")
    List<RWaitToken> selectAllTokenByOpenIdState(String openId, Integer state);

    @Query("select rw from RWaitToken rw where rw.openId = ?1 and UNIX_TIMESTAMP(rw.timeTook) >= ?2 and UNIX_TIMESTAMP(rw.timeTook) <= ?3")
    List<RWaitToken> selectWait(String openId, long bTime, long eTime);

    @Modifying
    @Query("update RWaitToken rw set rw.state = ?1 where rw.waitTokenId = ?2")
    @Transactional
    int modifyWaitingStatus(int state, long waitQueueId);
}

