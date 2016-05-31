package com.myee.dto;

import java.util.Date;
import java.util.List;

public class ResourceVo {
    private Long             resID;
    private Long             parentResID;
    private String           path;
    private String           name;
    private String           description;
    private String           content;
    private String           modified;
    private int              resType;
    private Long             size;
    private Long             targetResID;
    private String           absolutePath;
    private Long             createdBy;
    private Date             created;
    private List<ResourceVo> children;

    public ResourceVo() {
    }

    public ResourceVo(long resID, String path, String name, int resType) {
        super();
        this.resID = resID;
        this.path = path;
        this.name = name;
        this.resType = resType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getParentResID() {
        return parentResID;
    }

    public void setParentResID(Long parentResID) {
        this.parentResID = parentResID;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public Long getResID() {
        return resID;
    }

    public void setResID(Long resID) {
        this.resID = resID;
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

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public int getResType() {
        return resType;
    }

    public void setResType(int resType) {
        this.resType = resType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getTargetResID() {
        return targetResID;
    }

    public void setTargetResID(Long targetResID) {
        this.targetResID = targetResID;
    }

    public List<ResourceVo> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceVo> children) {
        this.children = children;
    }

    public boolean isLeaf() {
        return resType != 1;
    }

    public boolean isChecked() {
        return false;
    }

}
