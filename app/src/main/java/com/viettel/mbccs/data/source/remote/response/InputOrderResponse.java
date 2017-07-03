package com.viettel.mbccs.data.source.remote.response;

import com.viettel.mbccs.data.model.InvoiceList;
import java.util.List;

/**
 * Created by FRAMGIA\bui.dinh.viet on 03/07/2017.
 */

public class InputOrderResponse extends DataResponse {
    private List<InvoiceList> mInvoiceLists;

    public List<InvoiceList> getInvoiceLists() {
        return mInvoiceLists;
    }

    public void setInvoiceLists(List<InvoiceList> invoiceLists) {
        mInvoiceLists = invoiceLists;
    }
}
