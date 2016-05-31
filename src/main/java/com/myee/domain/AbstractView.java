package com.myee.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractView {
    @Column(name = "ad_client_id")
    private Long    clientID;
    @Column(name = "ad_org_id")
    private Long    orgID;
    @Basic
    private boolean active;
    @Basic
    private Date    updated;
    @Column(name = "updated_by")
    private Long    updatedBy;
    @Basic
    private Date    created;
    @Column(name = "created_by")
    private Long    createdBy;
    @Column(name = "clientName")
    private String  clientName;
    @Column(name = "orgName")
    private String  orgName;

    public Long getClientID() {
        return clientID;
    }

    public void setClientID(Long aDClientID) {
        this.clientID = aDClientID;
    }

    public Long getOrgID() {
        return orgID;
    }

    public void setOrgID(Long aDOrgID) {
        this.orgID = aDOrgID;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

}
