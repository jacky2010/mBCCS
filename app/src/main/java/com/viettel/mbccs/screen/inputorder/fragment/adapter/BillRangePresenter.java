package com.viettel.mbccs.screen.inputorder.fragment.adapter;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableLong;
import com.viettel.mbccs.data.model.BillRange;

public class BillRangePresenter {
    private BillRange mBillRange;
    public ObservableField<String> codeFrom;
    public ObservableField<String> codeTo;
    public ObservableField<String> date;
    public ObservableField<String> nameStore;
    public ObservableLong count;
    public ObservableBoolean isReceived;

    public BillRangePresenter(BillRange billRange) {
        mBillRange = billRange;
        initFields();
    }

    private void initFields() {
        codeFrom = new ObservableField<>();
        codeTo = new ObservableField<>();
        date = new ObservableField<>();
        nameStore = new ObservableField<>();
        count = new ObservableLong();
        isReceived = new ObservableBoolean();

        codeFrom.set(mBillRange.getCodeFrom());
        codeTo.set(mBillRange.getCodeTo());
        date.set(mBillRange.getDate());
        nameStore.set(mBillRange.getNameStore());
        count.set(mBillRange.getCount());
        isReceived.set(mBillRange.isReceived());
    }
}
