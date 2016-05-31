package com.myee.domain.restaurant;

import com.myee.domain.restaurant.view.RDiningV;
import com.myee.domain.restaurant.view.RQueuingV;
import com.myee.domain.CustomUserDetails;
import com.myee.dto.*;

import java.util.Date;
import java.util.List;

public interface RestaurantService {

    List<RTableTypeVo> selectTypesByOrgId(long orgID);

    List<RTableVo> selectTablesByOrgId(long orgID);

    List<RTableZoneVo> selectZonesByOrgId(long orgID);

    PageResult<RQueuingV> selectQueuing(CustomUserDetails user, WhereVo whereVo, QueryVo queryVo);

    PageResult<RDiningV> selectDining(CustomUserDetails user, WhereVo whereVo, QueryVo queryVo);

    long countQueued(CustomUserDetails user, Date queueDate);

    long countSeated(CustomUserDetails user, Date queueDate);

    Long missOfTurn(CustomUserDetails user, WhereVo whereVo, long minWaitMinutes, long maxWaitMinutes);

    PageResult<TableTurnVo> statTableTurn(CustomUserDetails user, WhereVo where, QueryVo queryVo);

    List<Object[]> statTimesByType(CustomUserDetails user, WhereVo whereVo);

    List<Object[]> statTimesByZone(CustomUserDetails user, WhereVo whereVo);

    List<Object[]> statAvgEatMinutesByType(CustomUserDetails user, WhereVo whereVo);

    List<Object[]> statAvgEatMinutesByZone(CustomUserDetails user, WhereVo whereVo);

    List<Object[]> statAvgTrunMinutesByType(CustomUserDetails user, WhereVo whereVo);

    List<Object[]> statAvgTrunMinutesByZone(CustomUserDetails user, WhereVo whereVo);
}
