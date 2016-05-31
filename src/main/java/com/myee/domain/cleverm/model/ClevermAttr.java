package com.myee.domain.cleverm.model;

import com.myee.domain.AbstractEntity;
import com.myee.domain.cleverm.model.*;

import javax.persistence.*;

@Entity
@Table(name = "c_cleverm_attribute")
public class ClevermAttr extends AbstractEntity {

    @Id
    @Column(name = "c_cleverm_attribute_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_113", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "C_CleverM_Attribute", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_113")
    private Long    clevermAttrID;
    @Column(name = "cleverm_no")
    private String  clevermNo;
    @Column(name = "cleverm_type")
    private int     clevermType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_cleverm_id")
    private Cleverm cleverm;
    @Basic
    private String  name;
    @Basic
    private String  description;
    @Column(name = "attribute_name")
    private String  attrName;
    @Column(name = "attribute_value")
    private String  attrValue;

    public Long getClevermAttrID() {
        return clevermAttrID;
    }

    public void setClevermAttrID(Long clevermAttrID) {
        this.clevermAttrID = clevermAttrID;
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

    public Cleverm getCleverm() {
        return cleverm;
    }

    public void setCleverm(Cleverm cleverm) {
        this.cleverm = cleverm;
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

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

}
