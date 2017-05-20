package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by eo_cuong on 5/19/17.
 */

public class SaleTrans implements Serializable, Parcelable {

    @SerializedName("saleTransId")
    @Expose
    private long saleTransId;

    @SerializedName("saleTransCode")
    @Expose
    private String saleTransCode;

    @SerializedName("saleTransDate")
    @Expose
    private String saleTransDate;

    @SerializedName("amountTax")
    @Expose
    private double amountTax = 10.000;

    @SerializedName("amountNotTax")
    @Expose
    private double amountNotTax;

    @SerializedName("VAT")
    @Expose
    private double VAT;

    @SerializedName("tax")
    @Expose
    private double tax;

    @SerializedName("discount")
    @Expose
    private double discount;

    @SerializedName("custName")
    @Expose
    private String custName;

    @SerializedName("status")
    @Expose
    private long status;

    @SerializedName("statusName")
    @Expose
    private String statusName;

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

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.saleTransId);
        dest.writeString(this.saleTransCode);
        dest.writeString(this.saleTransDate);
        dest.writeDouble(this.amountTax);
        dest.writeDouble(this.amountNotTax);
        dest.writeDouble(this.VAT);
        dest.writeDouble(this.tax);
        dest.writeDouble(this.discount);
        dest.writeString(this.custName);
        dest.writeLong(this.status);
        dest.writeString(this.statusName);
    }

    public SaleTrans() {
    }

    protected SaleTrans(Parcel in) {
        this.saleTransId = in.readLong();
        this.saleTransCode = in.readString();
        this.saleTransDate = in.readString();
        this.amountTax = in.readDouble();
        this.amountNotTax = in.readDouble();
        this.VAT = in.readDouble();
        this.tax = in.readDouble();
        this.discount = in.readDouble();
        this.custName = in.readString();
        this.status = in.readLong();
        this.statusName = in.readString();
    }

    public static final Creator<SaleTrans> CREATOR = new Creator<SaleTrans>() {
        @Override
        public SaleTrans createFromParcel(Parcel source) {
            return new SaleTrans(source);
        }

        @Override
        public SaleTrans[] newArray(int size) {
            return new SaleTrans[size];
        }
    };
}
