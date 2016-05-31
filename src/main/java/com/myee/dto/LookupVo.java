package com.myee.dto;

/**
 * Created by Martin on 2015/9/17.
 */
public class LookupVo {
    private String name;
    private Object value;

    public LookupVo() {
    }

    public LookupVo(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
