package com.myee.domain.restaurant.dao;

import com.myee.domain.restaurant.view.RQueuingV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RQueuingVDao extends JpaRepository<RQueuingV, Long> {

    @Query("SELECT COUNT(d) FROM RQueuingV d WHERE d.waitState='4' AND d.orgID=:orgID AND d.timeFallIn BETWEEN :begin AND :end AND d.waitMinutes BETWEEN :min AND :max")
    Long missOfTurn(@Param("orgID") long orgID, @Param("begin") Date begin, @Param("end") Date end, @Param("min") long min, @Param("max") long max);

    @Query("SELECT COUNT(d) FROM RQueuingV d WHERE d.waitState='4' AND d.orgID=:orgID AND d.tableTypeId=:tableType AND d.timeFallIn BETWEEN :begin AND :end AND d.waitMinutes BETWEEN :min AND :max")
    Long missOfTurn(@Param("orgID") long orgID, @Param("tableType") long tableType, @Param("begin") Date begin, @Param("end") Date end, @Param("min") long min, @Param("max") long max);
}
