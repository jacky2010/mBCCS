package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.InvoiceList;
import java.util.List;

/**
 * Created by FRAMGIA\bui.dinh.viet on 03/07/2017.
 */

public class InputOrderResponse extends DataResponse {
    @Expose
    @SerializedName("lstInvoice")
    private List<InvoiceList> mInvoiceLists;

    public List<InvoiceList> getInvoiceLists() {
        return mInvoiceLists;
    }

    public void setInvoiceLists(List<InvoiceList> invoiceLists) {
        mInvoiceLists = invoiceLists;
    }
}
