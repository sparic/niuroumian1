package com.myee.domain.adempiere.model;

import com.myee.domain.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ad_user")
public class ADUser extends AbstractEntity {
    @Id
    @Column(name = "ad_user_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_110", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "AD_User", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_110")
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

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

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
