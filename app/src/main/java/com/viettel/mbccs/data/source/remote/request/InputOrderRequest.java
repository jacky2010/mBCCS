package com.viettel.mbccs.data.source.remote.request;

import com.viettel.mbccs.data.model.InvoiceList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRAMGIA\bui.dinh.viet on 03/07/2017.
 */

public class InputOrderRequest extends BaseRequest {
    private List<Long> invoiceListId = new ArrayList<>();
    private List<InvoiceList> mInvoiceLists = new ArrayList<>();
    private Long shopId;
    private Long staffId;

    public List<Long> getInvoiceListId() {
        return invoiceListId;
    }

    public void setInvoiceListId(List<Long> invoiceListId) {
        this.invoiceListId = invoiceListId;
    }

    public List<InvoiceList> getInvoiceLists() {
        return mInvoiceLists;
    }

    public void setInvoiceLists(List<InvoiceList> invoiceLists) {
        if (invoiceLists == null) return;

        for (InvoiceList invoiceList : invoiceLists) {
            this.invoiceListId.add(invoiceList.getInvoiceListId());
        }
        mInvoiceLists = invoiceLists;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
}
