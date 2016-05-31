package com.myee.domain.adempiere.view;

import com.myee.domain.AbstractView;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ad_user_v")
public class ADUserV extends AbstractView {
    @Id
    @Column(name = "ad_user_id", columnDefinition = "INT")
    private Long   userID;
    @Column(name = "c_bpartner_id")
    private Long   partnerID;
    @Column(nullable = false)
    private Date   birthday;
    @Basic
    private String mobile;
    @Basic
    private String name;
    @Basic
    private String password;
    @Basic
    private String phone;
    @Basic
    private String email;
    @Column(name = "role_type")
    private String roleType;
    @Column(name = "login_id")
    private String loginId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getPartnerID() {
        return partnerID;
    }

    public void setPartnerID(Long partnerID) {
        this.partnerID = partnerID;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
