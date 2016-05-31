package com.myee.domain.cleverm.view;

import com.myee.domain.AbstractView;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "c_computer_v")
public class ComputerV extends AbstractView {
    @Id
    @Column(name = "c_computer_id", columnDefinition = "INT")
    private Long    computerID;
    @Column(name = "board_no")
    private String  boardNo;
    @Column(name = "computer_type")
    private int     computerType;
    @Column(name = "time_heartbeat")
    private Date    timeHeartbeat;
    @Column(name = "c_cleverm_id")
    private Long    clevermID;
    @Column(name = "cleverm_no")
    private String  clevermNo;
    @Column(name = "clevermName")
    private String  clevermName;
    @Column(name = "name")
    private String  name;
    @Column(name = "description")
    private String  description;
    @Basic
    private boolean preferred;
    @Column(name = "cleverm_type")
    private int     clevermType;

    public int getClevermType() {
        return clevermType;
    }

    public void setClevermType(int clevermType) {
        this.clevermType = clevermType;
    }

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

    public String getClevermName() {
        return clevermName;
    }

    public void setClevermName(String clevermName) {
        this.clevermName = clevermName;
    }

    public Long getComputerID() {
        return computerID;
    }

    public void setComputerID(Long computerID) {
        this.computerID = computerID;
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
