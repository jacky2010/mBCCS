package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.internal.Streams;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/14/17.
 */

public class SerialBlock {
    @Expose
    private int from;
    @Expose
    private int to;

    public SerialBlock() {
        setFrom(-1);
        setTo(-1);
    }

    public SerialBlock(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public String getFromString() {
        return String.valueOf(from);
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public String getToString() {
        return String.valueOf(to);
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getQuantity() {
        return getTo() - getFrom() + 1;
    }

    public String getQuantityString() {
        return String.valueOf(getQuantity());
    }

    public List<Integer> toSerialList() {
        List<Integer> integers = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            integers.add(i);
        }
        return integers;
    }
}
