package com.myee.domain;

import java.util.Arrays;
import java.util.Collection;

import com.myee.domain.adempiere.view.ADOrgV;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.myee.domain.adempiere.view.ADUserV;
import com.myee.util.StringUtil;

public class CustomUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String            username;
    private String            nickname;
    private String            password;
    private String            role;
    private boolean           enabled;
    private long              clientID;
    private String            clientName;
    private long              orgID;
    private String            orgName;

    private long              currentClientID;
    private String            currentClientName;
    private long              currentOrgID;
    private String            currentOrgName;


    final static String       ROLE_SUPERADMIN  = "SUPERADMIN";
    final static String       ROLE_CLIENTADMIN = "CLIENTADMIN";
    final static String       ROLE_CLIENTUSER  = "CLIENTUSER";

    public CustomUserDetails(long clientID, long orgID, String clientName, String orgName, String nickname, String username,
                             String password, String role, boolean enabled) {
        this.clientID = clientID;
        this.clientName = clientName;
        this.orgID = orgID;
        this.orgName = orgName;
        this.currentClientID = clientID;
        this.currentOrgID = orgID;
        this.currentClientName = clientName;
        this.currentOrgName = orgName;

        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    public CustomUserDetails(ADUserV user) {
        this(user.getClientID(), user.getOrgID(), user.getClientName(), user.getOrgName(), user.getName(), user.getLoginId(),
                user.getPassword(), user.getRoleType(), !user.isActive());
    }

    private CustomUserDetails() {
    }

    public final static CustomUserDetails EMPTY = new CustomUserDetails();

    public boolean hasRole(String role) {
        return StringUtil.equals(role, this.role);
    }

    public boolean freezeClient() {
        if (hasRole(ROLE_SUPERADMIN)) {
            return false;
        }
        return true;
    }

    public boolean freezeOrg() {
        if (hasRole(ROLE_SUPERADMIN) || hasRole(ROLE_CLIENTADMIN)) {
            return false;
        }
        return true;
    }

    public String getClientName() {
        return clientName;
    }

    public String getOrgName() {
        return orgName;
    }

    public long getClientID() {
        return clientID;
    }

    public long getOrgID() {
        return orgID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority[] gas = new GrantedAuthority[1];
        gas[0] = new SimpleGrantedAuthority(role);
        return Arrays.asList(gas);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void changeCurrentOrg(ADOrgV orgV) {
        this.currentClientID = orgV.getClientID();
        this.currentOrgID = orgV.getOrgID();
        this.currentClientName = orgV.getClientName();
        this.currentOrgName = orgV.getOrgName();
    }

    public long getCurrentClientID() {
        return currentClientID;
    }

    public String getCurrentClientName() {
        return currentClientName;
    }

    public long getCurrentOrgID() {
        return currentOrgID;
    }

    public String getCurrentOrgName() {
        return currentOrgName;
    }
}
