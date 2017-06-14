package com.viettel.mbccs.data.model;

/**
 * Created by minhnx on 6/13/17.
 */

public class DefaultPaymentAmount {
    private double amount;
    private String title;
    private boolean selected;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public DefaultPaymentAmount(double amount, String title, boolean selected) {
        this.amount = amount;
        this.title = title;
        this.selected = selected;
    }

    public DefaultPaymentAmount() {
    }
}
