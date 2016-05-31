package com.myee.domain.restaurant.dao;

import com.myee.domain.restaurant.model.TableType;
import com.myee.dto.RTableTypeVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TableTypeDao extends JpaRepository<TableType, Long> {

    @Query("SELECT NEW com.myee.dto.RTableTypeVo(t) FROM TableType t WHERE t.orgID=:orgID")
    List<RTableTypeVo> selectTypesByOrgId(@Param("orgID") long orgID);

    @Query("SELECT t FROM TableType t WHERE t.orgID=:orgID")
    List<TableType> findTypeByOrgId(@Param("orgID") long orgID);
}
