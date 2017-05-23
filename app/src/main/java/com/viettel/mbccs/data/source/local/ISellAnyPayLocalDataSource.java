package com.viettel.mbccs.data.source.local;

import com.viettel.mbccs.data.model.KeyValue;

import java.util.List;

public interface ISellAnyPayLocalDataSource {

    List<KeyValue> getCustTypes();
    List<KeyValue> getPayMethods();
    List<KeyValue> getDefaultAmounts();
    List<KeyValue> getBankPlusAmounts();

}
