package com.viettel.mbccs.screen.inputorder.fragment.adapter;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableLong;
import com.viettel.mbccs.data.model.InvoiceList;
import com.viettel.mbccs.utils.DateUtils;

public class BillRangePresenter {
    private InvoiceList mInvoiceList;
    public ObservableLong codeFrom;
    public ObservableLong codeTo;
    public ObservableField<String> date;
    public ObservableField<String> billOwnerName;
    public ObservableLong count;
    public ObservableInt status;
    public ObservableBoolean isInvoiceImport;

    public BillRangePresenter(InvoiceList invoiceList) {
        mInvoiceList = invoiceList;
        initFields();
    }

    private void initFields() {
        codeFrom = new ObservableLong();
        codeTo = new ObservableLong();
        date = new ObservableField<>();
        billOwnerName = new ObservableField<>();
        count = new ObservableLong();
        status = new ObservableInt();
        isInvoiceImport = new ObservableBoolean();

        codeFrom.set(mInvoiceList.getFromInvoice());
        codeTo.set(mInvoiceList.getToInvoice());
        String dateAssign = DateUtils.convertStringToStringFormat(mInvoiceList.getDateAssign(),
                DateUtils.DATE_FORMAT1);
        date.set(dateAssign);
        count.set(mInvoiceList.getToInvoice() - mInvoiceList.getFromInvoice());
        billOwnerName.set(mInvoiceList.getBillOwnerName());
        status.set((int) mInvoiceList.getStatus());
        isInvoiceImport.set(mInvoiceList.isInvoiceImport());
    }
}
