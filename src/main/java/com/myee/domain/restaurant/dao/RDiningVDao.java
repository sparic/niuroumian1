package com.myee.domain.restaurant.dao;

import com.myee.domain.restaurant.view.RDiningV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RDiningVDao extends JpaRepository<RDiningV, Long> {

    @Query("SELECT d.tableTypeName, COUNT(d) FROM RDiningV d WHERE d.orgID=:orgID AND d.timeSeated BETWEEN :begin AND :end GROUP BY d.tableTypeName")
    List<Object[]> statTimesByType(@Param("orgID") long orgID, @Param("begin") Date begin, @Param("end") Date end);

    @Query("SELECT d.zoneName, COUNT(d) FROM RDiningV d WHERE d.orgID=:orgID AND d.timeSeated BETWEEN :begin AND :end GROUP BY d.zoneName")
    List<Object[]> statTimesByZone(@Param("orgID") long orgID, @Param("begin") Date begin, @Param("end") Date end);

    @Query("SELECT d.zoneName, COUNT(d) FROM RDiningV d WHERE d.orgID=:orgID AND d.tableTypeId=:tableType AND d.timeSeated BETWEEN :begin AND :end GROUP BY d.zoneName")
    List<Object[]> statTimesByZone(@Param("orgID") long orgID, @Param("tableType") long tableType, @Param("begin") Date begin, @Param("end") Date end);

    @Query("SELECT d.tableTypeName, AVG(d.eatMinutes) FROM RDiningV d WHERE d.orgID=:orgID AND d.timeSeated BETWEEN :begin AND :end GROUP BY d.tableTypeName")
    List<Object[]> statAvgEatMinutesByType(@Param("orgID") long orgID, @Param("begin") Date begin, @Param("end") Date end);

    @Query("SELECT d.zoneName, AVG(d.eatMinutes) FROM RDiningV d WHERE d.orgID=:orgID AND d.timeSeated BETWEEN :begin AND :end GROUP BY d.zoneName")
    List<Object[]> statAvgEatMinutesByZone(@Param("orgID") long orgID, @Param("begin") Date begin, @Param("end") Date end);

    @Query("SELECT d.zoneName, AVG(d.eatMinutes) FROM RDiningV d WHERE d.orgID=:orgID AND d.tableTypeId=:tableType AND d.timeSeated BETWEEN :begin AND :end GROUP BY d.zoneName")
    List<Object[]> statAvgEatMinutesByZone(@Param("orgID") long orgID, @Param("tableType") long tableType, @Param("begin") Date begin, @Param("end") Date end);

    @Query("SELECT d.tableTypeName, AVG(d.trunMinutes) FROM RDiningV d WHERE d.orgID=:orgID AND d.timeSeated BETWEEN :begin AND :end GROUP BY d.tableTypeName")
    List<Object[]> statAvgTrunMinutesByType(@Param("orgID") long orgID, @Param("begin") Date begin, @Param("end") Date end);

    @Query("SELECT d.zoneName, AVG(d.trunMinutes) FROM RDiningV d WHERE d.orgID=:orgID AND d.timeSeated BETWEEN :begin AND :end GROUP BY d.zoneName")
    List<Object[]> statAvgTrunMinutesByZone(@Param("orgID") long orgID, @Param("begin") Date begin, @Param("end") Date end);

    @Query("SELECT d.zoneName, AVG(d.trunMinutes) FROM RDiningV d WHERE d.orgID=:orgID AND d.tableTypeId=:tableType AND d.timeSeated BETWEEN :begin AND :end GROUP BY d.zoneName")
    List<Object[]> statAvgTrunMinutesByZone(@Param("orgID") long orgID, @Param("tableType") long tableType, @Param("begin") Date begin, @Param("end") Date end);

    @Query("SELECT d.tableName, d.zoneName, d.tableTypeName, COUNT(d), AVG(d.eatMinutes), AVG(d.trunMinutes) FROM RDiningV d WHERE d.orgID=:orgID AND d.timeSeated BETWEEN :begin AND :end GROUP BY d.tableId")
    List<Object[]> statTableTurn(@Param("orgID") long orgID, @Param("begin") Date begin, @Param("end") Date end);
}
