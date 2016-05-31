package com.myee.domain.restaurant.dao;

import com.myee.domain.restaurant.model.TableZone;
import com.myee.dto.RTableZoneVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TableZoneDao extends JpaRepository<TableZone, Long> {

    @Query("SELECT NEW com.myee.dto.RTableZoneVo(t) FROM TableZone t WHERE t.orgID=:orgID")
    List<RTableZoneVo> selectZonesByOrgId(@Param("orgID") long orgID);
}
