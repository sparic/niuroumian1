package com.myee.domain.adempiere.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ad_org")
public class ADOrg {
    @Column(name = "c_location_id")
    private Long   locationID;
    @Column(name = "date_found")
    private Date   dateFound;
    @Basic
    private String description;
    @Basic
    private String fax;
    @Basic
    private String name;
    @Basic
    private String phone;
    @Column(name = "ad_client_id")
    private Long   clientID;
    @Id
    @Column(name = "ad_org_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_104", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "AD_Org", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_104")
    private Long   orgID;
    @Basic
    private boolean active = true;
    @Basic
    private Date updated;
    @Column(name = "updated_by")
    private Long updatedBy;
    @Basic
    private Date created;
    @Column(name = "created_by")
    private Long createdBy;

    public Date getDateFound() {
        return dateFound;
    }

    public void setDateFound(Date dateFound) {
        this.dateFound = dateFound;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getLocationID() {
        return locationID;
    }

    public void setLocationID(Long locationID) {
        this.locationID = locationID;
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
