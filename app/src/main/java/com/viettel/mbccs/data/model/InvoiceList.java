package com.viettel.mbccs.data.model;

import java.util.Date;

public class InvoiceList {
    private long shopId;
    private long staffId;
    private String fromDate;
    private String toDate;
    private String codeFrom;
    private String codeTo;
    private long count;
    private String date;
    private String nameStore;
    private boolean isReceived;
    private String serialNo;
    private String blockNo;
    private String bockNoName;
    private long fromInvoice;
    private long toInvoice;
    private long currInvoiceNo;
    private Date dateAssign;
    private long quantity;
    private long status;

    public InvoiceList(String codeFrom, String codeTo, long count, String date, String nameStore) {
        this.codeFrom = codeFrom;
        this.codeTo = codeTo;
        this.count = count;
        this.date = date;
        this.nameStore = nameStore;
    }

    public String getCodeFrom() {
        return codeFrom;
    }

    public void setCodeFrom(String codeFrom) {
        this.codeFrom = codeFrom;
    }

    public String getCodeTo() {
        return codeTo;
    }

    public void setCodeTo(String codeTo) {
        this.codeTo = codeTo;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public boolean isReceived() {
        return isReceived;
    }

    public void setReceived(boolean received) {
        isReceived = received;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
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

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getBlockNo() {
        return blockNo;
    }

    public void setBlockNo(String blockNo) {
        this.blockNo = blockNo;
    }

    public String getBockNoName() {
        return bockNoName;
    }

    public void setBockNoName(String bockNoName) {
        this.bockNoName = bockNoName;
    }

    public long getFromInvoice() {
        return fromInvoice;
    }

    public void setFromInvoice(long fromInvoice) {
        this.fromInvoice = fromInvoice;
    }

    public long getToInvoice() {
        return toInvoice;
    }

    public void setToInvoice(long toInvoice) {
        this.toInvoice = toInvoice;
    }

    public long getCurrInvoiceNo() {
        return currInvoiceNo;
    }

    public void setCurrInvoiceNo(long currInvoiceNo) {
        this.currInvoiceNo = currInvoiceNo;
    }

    public Date getDateAssign() {
        return dateAssign;
    }

    public void setDateAssign(Date dateAssign) {
        this.dateAssign = dateAssign;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
}
