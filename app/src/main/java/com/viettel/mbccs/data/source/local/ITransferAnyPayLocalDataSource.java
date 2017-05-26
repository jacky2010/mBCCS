package com.viettel.mbccs.data.source.local;

import com.viettel.mbccs.data.model.KeyValue;

import java.util.List;

public interface ITransferAnyPayLocalDataSource {

    List<KeyValue> getTransferTypes();
    List<KeyValue> getDefaultAmounts();

}
