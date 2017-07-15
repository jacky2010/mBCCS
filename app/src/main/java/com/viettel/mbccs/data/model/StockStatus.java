package com.viettel.mbccs.data.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 7/15/17.
 */

public class StockStatus {
    long id;
    String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void StockStatus() {

    }

    public StockStatus(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<StockStatus> statusList() {
        List<StockStatus> result = new ArrayList<>();
        result.add(new StockStatus(1L, "NEW"));
        result.add(new StockStatus(2L, "OLD"));
        result.add(new StockStatus(3L, "CORRUPTED"));
        return result;
    }

    @Override
    public String toString() {
        return getName();
    }
}
