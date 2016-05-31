package com.myee.domain.weixin.model;

import com.myee.domain.AbstractEntity;
import com.myee.domain.ToDto;
import com.myee.djinn.dto.Diner;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Martin on 2016/1/18.
 */
@Entity
@Table(name = "r_diner")
public class RDiner extends AbstractEntity implements ToDto<Diner> {
    @Id
    @Column(name = "r_diner_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_129", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "R_Diner", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_129")
    private Long   dinerID;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "weixin")
    private String weixin;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "time_first_repast")
    private Date   timeFirstRepast;

    public Long getDinerID() {
        return dinerID;
    }

    public void setDinerID(Long dinerID) {
        this.dinerID = dinerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getTimeFirstRepast() {
        return timeFirstRepast;
    }

    public void setTimeFirstRepast(Date timeFirstRepast) {
        this.timeFirstRepast = timeFirstRepast;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    @Override
    public Diner toDto() {
        Diner diner = new Diner();
        diner.setDinerId(dinerID);
        diner.setFirstName(firstName);
        diner.setLastName(lastName);
        diner.setNickName(nickName);
        diner.setWeixin(weixin);
        diner.setMobile(mobile);
        return diner;
    }
}
