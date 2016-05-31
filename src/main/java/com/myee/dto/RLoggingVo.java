package com.myee.dto;

import com.myee.dto.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Martin on 2015/7/4.
 */
public class RLoggingVo {
    private long             clientID;
    private long             orgID;
    private String           orgName;
    private Date             dateLogging;
    private List<RQueuingVo> queuingVoList;
    private List<RDiningVo>  diningVoList;

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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Date getDateLogging() {
        return dateLogging;
    }

    public void setDateLogging(Date dateLogging) {
        this.dateLogging = dateLogging;
    }

    public List<RQueuingVo> getQueuingVoList() {
        return queuingVoList;
    }

    public void setQueuingVoList(List<RQueuingVo> queuingVoList) {
        this.queuingVoList = queuingVoList;
    }

    public List<RDiningVo> getDiningVoList() {
        return diningVoList;
    }

    public void setDiningVoList(List<RDiningVo> diningVoList) {
        this.diningVoList = diningVoList;
    }
}
