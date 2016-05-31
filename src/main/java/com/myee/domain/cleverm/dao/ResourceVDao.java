package com.myee.domain.cleverm.dao;

import com.myee.domain.cleverm.view.ResourceV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourceVDao extends JpaRepository<ResourceV, Long> {
    long TEMPLETE_ORG_ID = 100;
    
    @Query("SELECT r FROM ResourceV r WHERE r.parentResourceID=:parentResID AND (r.orgID=:orgID OR r.orgID=" + TEMPLETE_ORG_ID + ") ORDER BY r.resourceType, r.path")
    List<ResourceV> getChildren(@Param("parentResID") Long parentResID, @Param("orgID") Long orgID);
    
    @Query("SELECT r FROM ResourceV r WHERE r.parentResourceID IS NULL")
    List<ResourceV> getRootNode();
}
