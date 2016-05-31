package com.myee.domain.weixin.model;

import com.myee.djinn.dto.DrawToken;
import com.myee.domain.AbstractEntity;
import com.myee.domain.ToDto;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Martin on 2016/1/18.
 */
@Entity
@Table(name = "r_draw_token")
public class RDrawToken extends AbstractEntity implements ToDto<DrawToken> {
    @Id
    @Column(name = "r_draw_token_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_131", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "R_Draw_Token", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_131")
    private Long   drawTokenId;
    @Column(name = "wait_token_id")
    private Long   waitTokenId;
    @Column(name = "token")
    private String token;
    @Column(name = "time_genated")
    private Date   timeGenated;
    @Column(name = "time_drawed")
    private Date   timeDrawed;
    @Column(name = "time_deadline")
    private Date   timeDeadline;
    @Column(name = "time_exchanged")
    private Date   timeExchanged;

    public Date getTimeExchanged() {
        return timeExchanged;
    }

    public void setTimeExchanged(Date timeExchanged) {
        this.timeExchanged = timeExchanged;
    }

    public Long getDrawTokenId() {
        return drawTokenId;
    }

    public void setDrawTokenId(Long drawTokenId) {
        this.drawTokenId = drawTokenId;
    }

    public Date getTimeDeadline() {
        return timeDeadline;
    }

    public void setTimeDeadline(Date timeDeadline) {
        this.timeDeadline = timeDeadline;
    }

    public Date getTimeDrawed() {
        return timeDrawed;
    }

    public void setTimeDrawed(Date timeDrawed) {
        this.timeDrawed = timeDrawed;
    }

    public Date getTimeGenated() {
        return timeGenated;
    }

    public void setTimeGenated(Date timeGenated) {
        this.timeGenated = timeGenated;
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

    @Override
    public DrawToken toDto() {
        DrawToken drawToken = new DrawToken();
        return drawToken;
    }
}
