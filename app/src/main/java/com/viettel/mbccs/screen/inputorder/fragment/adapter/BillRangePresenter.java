package com.viettel.mbccs.screen.inputorder.fragment.adapter;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableLong;
import com.viettel.mbccs.data.model.InvoiceList;

public class BillRangePresenter {
    private InvoiceList mInvoiceList;
    public ObservableField<String> codeFrom;
    public ObservableField<String> codeTo;
    public ObservableField<String> date;
    public ObservableField<String> nameStore;
    public ObservableLong count;
    public ObservableBoolean isReceived;

    public BillRangePresenter(InvoiceList invoiceList) {
        mInvoiceList = invoiceList;
        initFields();
    }

    private void initFields() {
        codeFrom = new ObservableField<>();
        codeTo = new ObservableField<>();
        date = new ObservableField<>();
        nameStore = new ObservableField<>();
        count = new ObservableLong();
        isReceived = new ObservableBoolean();

        codeFrom.set(mInvoiceList.getCodeFrom());
        codeTo.set(mInvoiceList.getCodeTo());
        date.set(mInvoiceList.getDate());
        nameStore.set(mInvoiceList.getNameStore());
        count.set(mInvoiceList.getCount());
        isReceived.set(mInvoiceList.isReceived());
    }
}
