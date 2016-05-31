package com.myee.domain.weixin.model;

import com.myee.domain.AbstractEntity;
import com.myee.domain.ToDto;
import com.myee.djinn.dto.WaitToken;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Martin on 2016/1/18.
 */
@Entity
@Table(name = "r_wait_token")
public class RWaitToken extends AbstractEntity implements ToDto<WaitToken> {
    @Id
    @Column(name = "r_wait_token_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_136", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "R_Wait_Token", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_136")
    private Long   waitTokenId;
    @Column(name = "r_table_id")
    private Long   tableTypeId;
    @Column(name = "token")
    private String token;
    @Column(name = "channel_type")
    private String channelType;
    @Column(name = "comment")
    private String comment;
    @Column(name = "r_diner_id")
    private Long   dinerId;
    @Column(name = "time_took")
    private Date   timeTook;
    @Column(name = "open_id")
    private String openId;
    @Column(name = "identity_code")
    private  String identityCode;
    @Column(name = "state")
    private  int state;
    @Column(name = "waited_count")
    private Long waitedCount;
    @Column(name = "predict_waiting_time")
    private Long predictWaitingTime;

    public Long getDinerId() {
        return dinerId;
    }

    public void setDinerId(Long dinerId) {
        this.dinerId = dinerId;
    }

    public Date getTimeTook() {
        return timeTook;
    }

    public void setTimeTook(Date timeTook) {
        this.timeTook = timeTook;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getTableTypeId() {
        return tableTypeId;
    }

    public void setTableTypeId(Long tableTypeId) {
        this.tableTypeId = tableTypeId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getWaitTokenId() {
        return waitTokenId;
    }

    public void setWaitTokenId(Long waitTokenId) {
        this.waitTokenId = waitTokenId;
    }

    public String getOpenId() { return openId; }

    public void setOpenId(String openId) { this.openId = openId; }

    public String getIdentityCode() { return identityCode; }

    public void setIdentityCode(String identityCode) { this.identityCode = identityCode; }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Long getWaitedCount() {
        return waitedCount;
    }

    public void setWaitedCount(Long waitedCount) {
        this.waitedCount = waitedCount;
    }

    public Long getPredictWaitingTime() {
        return predictWaitingTime;
    }

    public void setPredictWaitingTime(Long predictWaitingTime) {
        this.predictWaitingTime = predictWaitingTime;
    }

    @Override
    public WaitToken toDto() {
        WaitToken waitToken = new WaitToken();
        return waitToken;
    }
}
