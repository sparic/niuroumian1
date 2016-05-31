package com.myee.domain.cleverm.model;

import com.myee.domain.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "c_computer")
public class Computer extends AbstractEntity {
    @Id
    @Column(name = "c_computer_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_114", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "C_Computer", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_114")
    private Long    computerID;
    @Column(name = "c_cleverm_id")
    private Long    clevermID;
    @Column(name = "board_no")
    private String  boardNo;
    @Column(name = "computer_type")
    private int     computerType;
    @Column(name = "time_heartbeat")
    private Date    timeHeartbeat;
    @Column(name = "name")
    private String  name;
    @Column(name = "description")
    private String  description;
    @Basic
    private boolean preferred;

    public Long getComputerID() {
        return computerID;
    }

    public void setComputerID(Long computerID) {
        this.computerID = computerID;
    }

    public Long getClevermID() {
        return clevermID;
    }

    public void setClevermID(Long clevermID) {
        this.clevermID = clevermID;
    }

    public String getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(String boardNo) {
        this.boardNo = boardNo;
    }

    public int getComputerType() {
        return computerType;
    }

    public void setComputerType(int computerType) {
        this.computerType = computerType;
    }

    public Date getTimeHeartbeat() {
        return timeHeartbeat;
    }

    public void setTimeHeartbeat(Date timeHeartbeat) {
        this.timeHeartbeat = timeHeartbeat;
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

    public boolean isPreferred() {
        return preferred;
    }

    public void setPreferred(boolean preferred) {
        this.preferred = preferred;
    }

}
