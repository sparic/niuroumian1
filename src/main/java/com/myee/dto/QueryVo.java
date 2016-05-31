package com.myee.dto;

/**
 * Created by Martin on 2015/8/17.
 */
public class QueryVo {
    private int draw;
    private int start;
    private int length;

    public QueryVo() {
    }

    public QueryVo(int draw, int start, int length) {
        this.draw = draw;
        this.start = start;
        this.length = length;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
