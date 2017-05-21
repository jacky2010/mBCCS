package com.viettel.mbccs.data.source.local;

import com.viettel.mbccs.data.model.KeyValue;

import java.util.List;

public interface IBranchesLocalDataSource {

//    BranchItem getDistributtedChannelInfo(String isdn, String documentId);
//
//    BranchItem createDistributtedChannel(BranchItem item);

    List<KeyValue> getChannelTypes();
    List<KeyValue> getDocumentTypes();
    List<KeyValue> getStaffs();
    List<KeyValue> getBtses();

}
