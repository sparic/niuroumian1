package com.myee.domain.cleverm.view;

import com.myee.domain.AbstractView;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "c_cleverm_v")
public class ClevermV extends AbstractView {
    @Id
    @Column(name = "c_cleverm_id", columnDefinition = "INT")
    private Long   clevermID;
    @Column(name = "cleverm_no")
    private String clevermNo;
    @Column(name = "cleverm_type")
    private int    clevermType;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    public Long getClevermID() {
        return clevermID;
    }

    public void setClevermID(Long clevermID) {
        this.clevermID = clevermID;
    }

    public String getClevermNo() {
        return clevermNo;
    }

    public void setClevermNo(String clevermNo) {
        this.clevermNo = clevermNo;
    }

    public int getClevermType() {
        return clevermType;
    }

    public void setClevermType(int clevermType) {
        this.clevermType = clevermType;
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

}
