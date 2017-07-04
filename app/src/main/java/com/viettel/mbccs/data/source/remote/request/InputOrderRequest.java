package com.viettel.mbccs.data.source.remote.request;

import com.viettel.mbccs.data.model.InvoiceList;
import java.util.Date;
import java.util.List;

/**
 * Created by FRAMGIA\bui.dinh.viet on 03/07/2017.
 */

public class InputOrderRequest {
    private Long shopId;
    private Long staffId;
    private String fromDate;
    private String toDate;
    private List<InvoiceList> invoiceListId;

    public List<InvoiceList> getInvoiceListId() {
        return invoiceListId;
    }

    public void setInvoiceListId(List<InvoiceList> invoiceListId) {
        this.invoiceListId = invoiceListId;
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

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
