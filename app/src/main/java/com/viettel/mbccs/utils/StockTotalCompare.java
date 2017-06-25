package com.viettel.mbccs.utils;

import com.viettel.mbccs.data.model.StockTotal;
import java.util.Comparator;

/**
 * Created by eo_cuong on 6/25/17.
 */

public class StockTotalCompare implements Comparator<StockTotal> {
    @Override
    public int compare(StockTotal stockTotal, StockTotal t1) {
        return t1.getCountChoice() - stockTotal.getCountChoice();
    }
}
