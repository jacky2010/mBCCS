package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 5/18/17.
 */

public class SaleTrans implements Parcelable {
    @Expose
    @SerializedName("SaleTransId")
    private long saleTransId;

    @Expose
    @SerializedName("SaleTransCode")
    private String saleTransCode;

    @Expose
    @SerializedName("SaleTransDate")
    private String saleTransDate;

    @Expose
    @SerializedName("AmountTax")
    private double amountTax = 10.000;

    @Expose
    @SerializedName("AmountNotTax")
    private double amountNotTax;

    @Expose
    @SerializedName("VAT")
    private double vAT;

    @Expose
    @SerializedName("Tax")
    private double tax;

    @Expose
    @SerializedName("Discount")
    private double discount;

    @Expose
    @SerializedName("CustName")
    private String custName;

    @Expose
    @SerializedName("Status")
    private long status;

    @Expose
    @SerializedName("StatusName")
    private String statusName;

    public SaleTrans() {
    }

    protected SaleTrans(Parcel in) {
        saleTransId = in.readLong();
        saleTransCode = in.readString();
        saleTransDate = in.readString();
        amountTax = in.readDouble();
        amountNotTax = in.readDouble();
        vAT = in.readDouble();
        tax = in.readDouble();
        discount = in.readDouble();
        custName = in.readString();
        status = in.readLong();
        statusName = in.readString();
    }

    public static final Creator<SaleTrans> CREATOR = new Creator<SaleTrans>() {
        @Override
        public SaleTrans createFromParcel(Parcel in) {
            return new SaleTrans(in);
        }

        @Override
        public SaleTrans[] newArray(int size) {
            return new SaleTrans[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(saleTransId);
        dest.writeString(saleTransCode);
        dest.writeString(saleTransDate);
        dest.writeDouble(amountTax);
        dest.writeDouble(amountNotTax);
        dest.writeDouble(vAT);
        dest.writeDouble(tax);
        dest.writeDouble(discount);
        dest.writeString(custName);
        dest.writeLong(status);
        dest.writeString(statusName);
    }

    public long getSaleTransId() {
        return saleTransId;
    }

    public void setSaleTransId(long saleTransId) {
        this.saleTransId = saleTransId;
    }

    public String getSaleTransCode() {
        return saleTransCode;
    }

    public void setSaleTransCode(String saleTransCode) {
        this.saleTransCode = saleTransCode;
    }

    public String getSaleTransDate() {
        return saleTransDate;
    }

    public void setSaleTransDate(String saleTransDate) {
        this.saleTransDate = saleTransDate;
    }

    public double getAmountTax() {
        return amountTax;
    }

    public void setAmountTax(double amountTax) {
        this.amountTax = amountTax;
    }

    public double getAmountNotTax() {
        return amountNotTax;
    }

    public void setAmountNotTax(double amountNotTax) {
        this.amountNotTax = amountNotTax;
    }

    public double getvAT() {
        return vAT;
    }

    public void setvAT(double vAT) {
        this.vAT = vAT;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
