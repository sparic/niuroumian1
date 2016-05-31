package com.myee.domain.cleverm.model;

import com.myee.domain.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "c_cleverm")
public class Cleverm extends AbstractEntity {

    @Id
    @Column(name = "c_cleverm_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_112", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "C_CleverM", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_112")
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
