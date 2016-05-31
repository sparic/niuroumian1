package com.myee.domain.cleverm.view;

import com.myee.domain.AbstractView;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "c_resource_v")
public class ResourceV extends AbstractView {
    @Id
    @Column(name = "c_resource_id", columnDefinition = "INT")
    private Long   resourceID;
    @Column(name = "path")
    private String path;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "parent_resource_id")
    private Long   parentResourceID;
    @Column(name = "target_resource_id")
    private Long   targetResourceID;
    @Column(name = "resource_type")
    private int    resourceType;
    @Column(name = "size")
    private Long   size;
    @Column(name = "modified")
    private Date   modified;
    @Column(name = "absolute_path")
    private String absolutePath;

    public Long getResourceID() {
        return resourceID;
    }

    public void setResourceID(Long resourceID) {
        this.resourceID = resourceID;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParentResourceID() {
        return parentResourceID;
    }

    public void setParentResourceID(Long parentResourceID) {
        this.parentResourceID = parentResourceID;
    }

    public Long getTargetResourceID() {
        return targetResourceID;
    }

    public void setTargetResourceID(Long targetResourceID) {
        this.targetResourceID = targetResourceID;
    }

    public int getResourceType() {
        return resourceType;
    }

    public void setResourceType(int resourceType) {
        this.resourceType = resourceType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }
}
