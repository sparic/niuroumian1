package com.myee.domain.weixin.model;

import com.myee.domain.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Martin on 2016/1/18.
 */
@Entity
@Table(name = "r_instruction_log")
public class RInstructionLog extends AbstractEntity {
    @Id
    @Column(name = "r_instruction_log_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_132", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "R_Draw_Token", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_132")
    private Long    instructionLogId;
    @Column(name = "subject_id")
    private Long    subjectId;
    @Column(name = "instruction_level1")
    private int     instructionLevel1;
    @Column(name = "instruction_level2")
    private Integer instructionLevel2;
    @Column(name = "context1")
    private String  context1;
    @Column(name = "context2")
    private String  context2;
    @Column(name = "time_occured")
    private Date    timeOccured;

    public String getContext1() {
        return context1;
    }

    public void setContext1(String context1) {
        this.context1 = context1;
    }

    public String getContext2() {
        return context2;
    }

    public void setContext2(String context2) {
        this.context2 = context2;
    }

    public int getInstructionLevel1() {
        return instructionLevel1;
    }

    public void setInstructionLevel1(int instructionLevel1) {
        this.instructionLevel1 = instructionLevel1;
    }

    public Integer getInstructionLevel2() {
        return instructionLevel2;
    }

    public void setInstructionLevel2(Integer instructionLevel2) {
        this.instructionLevel2 = instructionLevel2;
    }

    public Long getInstructionLogId() {
        return instructionLogId;
    }

    public void setInstructionLogId(Long instructionLogId) {
        this.instructionLogId = instructionLogId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Date getTimeOccured() {
        return timeOccured;
    }

    public void setTimeOccured(Date timeOccured) {
        this.timeOccured = timeOccured;
    }
}
