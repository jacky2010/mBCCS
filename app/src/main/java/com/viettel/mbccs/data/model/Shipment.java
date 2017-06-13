package com.viettel.mbccs.data.model;

/**
 * Created by buidinhviet on 6/13/17.
 */

public class Shipment {
    private String nameBill;
    private String nameChannel;
    private String date;

    public Shipment(String nameBill, String nameChannel, String date) {
        this.nameBill = nameBill;
        this.nameChannel = nameChannel;
        this.date = date;
    }

    public String getNameBill() {
        return nameBill;
    }

    public void setNameBill(String nameBill) {
        this.nameBill = nameBill;
    }

    public String getNameChannel() {
        return nameChannel;
    }

    public void setNameChannel(String nameChannel) {
        this.nameChannel = nameChannel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
