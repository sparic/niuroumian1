package com.myee.domain.adempiere.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ad_client")
public class ADClient {
    @Basic
    private String  description;
    @Basic
    private String  name;
    @Id
    @Column(name = "ad_client_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_100", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "AD_Client", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_100")
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
    
    @PrePersist
    void onCreated() {        
        setCreated(new Date());
        setCreatedBy(100L);
    }
    
    @PreUpdate
    void onUpdated() {
        setUpdated(new Date());
        setUpdatedBy(100L);
    }

}
