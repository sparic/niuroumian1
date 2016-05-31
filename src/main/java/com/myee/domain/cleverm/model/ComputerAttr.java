package com.myee.domain.cleverm.model;

import com.myee.domain.AbstractEntity;
import com.myee.domain.cleverm.model.*;

import javax.persistence.*;

@Entity
@Table(name = "c_computer_attribute")
public class ComputerAttr extends AbstractEntity {
    @Id
    @Column(name = "c_computer_attribute_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_115", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "C_Computer_Attribute", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_115")
    private Long     computerAttrID;
    @Column(name = "computer_type")
    private int      computerType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_computer_id")
    private Computer computer;
    @Basic
    private String   name;
    @Basic
    private String   description;
    @Column(name = "attribute_name")
    private String   attrName;
    @Column(name = "attribute_value")
    private String   attrValue;

    public Long getComputerAttrID() {
        return computerAttrID;
    }

    public void setComputerAttrID(Long computerAttrID) {
        this.computerAttrID = computerAttrID;
    }

    public int getComputerType() {
        return computerType;
    }

    public void setComputerType(int computerType) {
        this.computerType = computerType;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
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
