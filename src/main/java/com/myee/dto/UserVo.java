package com.myee.dto;

import com.myee.domain.CustomUserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserVo {
    private long     clientID;
    private long     orgID;
    private String   clientName;
    private String   orgName;
    private String   username;
    private String   nickname;
    private String[] authorities;

    public UserVo() {
    }

    public UserVo(Object principal) {
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails user = (CustomUserDetails) principal;
            setClientID(user.getClientID());
            setOrgID(user.getOrgID());
            setClientName(user.getClientName());
            setOrgName(user.getOrgName());
            setNickname(user.getNickname());
            setUsername(user.getUsername());
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            String[] roles = new String[authorities.size()];
            int index = 0;
            for (GrantedAuthority authority : authorities) {
                roles[index] = authority.getAuthority();
            }
            setAuthorities(roles);
        }
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public long getClientID() {
        return clientID;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public long getOrgID() {
        return orgID;
    }

    public void setOrgID(long orgID) {
        this.orgID = orgID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }

}
