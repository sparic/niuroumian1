package com.myee.niuroumian.domain;

import javax.persistence.*;

/**
 * Created by Ray.Fu on 2016/6/1.
 */
@Entity
@Table(name = "t_user")
public class UserInfo {
    @Id
    @Column(name = "user_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_101", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "T_User", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_101")
    private Long userId;

    @Column(name = "open_id")
    private String openId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "mobile_num")
    private Long mobileNum;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(Long mobileNum) {
        this.mobileNum = mobileNum;
    }
}
