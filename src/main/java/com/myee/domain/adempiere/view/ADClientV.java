package com.myee.domain.adempiere.view;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ad_client_v")
public class ADClientV {
    @Basic
    private String  description;
    @Basic
    private String  name;
    @Id
    @Column(name = "ad_client_id", columnDefinition = "INT")
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getClientID() {
        return clientID;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }

    public Long getOrgID() {
        return orgID;
    }

    public void setOrgID(Long orgID) {
        this.orgID = orgID;
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
