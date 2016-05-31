package com.myee.dto;

import com.myee.dto.LookupVo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Martin on 2015/8/17.
 */
public class WhereVo implements Serializable {
    private LookupVo tableType;
    private Integer  dinnerType;
    private int      waitState;
    private Date     beginDate;
    private Date     endDate;
    private Date     queueDate;
    private boolean  before1;
    private boolean  before2;
    private boolean  before7;
    private boolean  before30;
    private String   tableName;

    public Long tableType() {
        if (tableType != null && null != tableType.getValue()) {
            return Long.valueOf(tableType.getValue().toString());
        }
        return null;
    }

    public Integer getDinnerType() {
        return dinnerType;
    }

    public void setDinnerType(Integer dinnerType) {
        this.dinnerType = dinnerType;
    }

    public LookupVo getTableType() {
        return tableType;
    }

    public void setTableType(LookupVo tableType) {
        this.tableType = tableType;
    }

    public int getWaitState() {
        return waitState;
    }

    public void setWaitState(int waitState) {
        this.waitState = waitState;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date startDate) {
        this.beginDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getQueueDate() {
        return queueDate;
    }

    public void setQueueDate(Date queueDate) {
        this.queueDate = queueDate;
    }

    public boolean isBefore1() {
        return before1;
    }

    public void setBefore1(boolean before1) {
        this.before1 = before1;
    }

    public boolean isBefore2() {
        return before2;
    }

    public void setBefore2(boolean before2) {
        this.before2 = before2;
    }

    public boolean isBefore7() {
        return before7;
    }

    public void setBefore7(boolean before7) {
        this.before7 = before7;
    }

    public boolean isBefore30() {
        return before30;
    }

    public void setBefore30(boolean before30) {
        this.before30 = before30;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
