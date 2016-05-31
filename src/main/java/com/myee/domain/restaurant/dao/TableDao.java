package com.myee.domain.restaurant.dao;

import com.myee.domain.restaurant.model.Table;
import com.myee.dto.RTableVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TableDao extends JpaRepository<Table, Long> {

    @Query("SELECT NEW com.myee.dto.RTableVo(t) FROM Table t WHERE t.orgID=:orgID")
    List<RTableVo> selectTablesByOrgId(@Param("orgID") long orgID);
}
