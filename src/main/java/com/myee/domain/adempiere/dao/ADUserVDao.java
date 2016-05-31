package com.myee.domain.adempiere.dao;

import com.myee.domain.adempiere.view.ADUserV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface ADUserVDao extends JpaRepository<ADUserV, Long> {

    @Query("SELECT NEW com.myee.domain.CustomUserDetails(u) FROM ADUserV u WHERE u.loginId=:username")
    UserDetails loadUserByUsername(@Param("username") String username);

}
