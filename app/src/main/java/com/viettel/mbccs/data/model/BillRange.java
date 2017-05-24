package com.viettel.mbccs.data.model;

public class BillRange {
    private String codeFrom;
    private String codeTo;
    private long count;
    private String date;
    private String nameStore;
    private boolean isReceived;

    public BillRange(String codeFrom, String codeTo, long count, String date, String nameStore) {
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
}
