package com.viettel.mbccs.data.model;

public class InvoiceList {
    private long invoiceListId;
    private String fromDate;
    private String toDate;
    private String serialNo;
    private String blockNo;
    private String bockNoName;
    private long fromInvoice;
    private long toInvoice;
    private long currInvoiceNo;
    private String dateAssign;
    private long quantity;
    private String billOwnerName;
    private long status;
    private boolean isInvoiceImport;

    public String getBillOwnerName() {
        return billOwnerName;
    }

    public void setBillOwnerName(String billOwnerName) {
        this.billOwnerName = billOwnerName;
    }

    public boolean isInvoiceImport() {
        return isInvoiceImport;
    }

    public void setInvoiceImport(boolean invoiceImport) {
        isInvoiceImport = invoiceImport;
    }

    public long getInvoiceListId() {
        return invoiceListId;
    }

    public void setInvoiceListId(long invoiceListId) {
        this.invoiceListId = invoiceListId;
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

    public String getDateAssign() {
        return dateAssign;
    }

    public void setDateAssign(String dateAssign) {
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
