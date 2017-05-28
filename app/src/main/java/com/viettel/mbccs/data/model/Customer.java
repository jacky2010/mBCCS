package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by eo_cuong on 5/19/17.
 */

public class Customer implements Parcelable {

    @SerializedName("customerId")
    @Expose
    private String customerId;

    @SerializedName("customerName")
    @Expose
    private String customerName;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("tin")
    @Expose
    private String tin;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public Customer() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.customerId);
        dest.writeString(this.customerName);
        dest.writeString(this.address);
        dest.writeString(this.tin);
    }

    protected Customer(Parcel in) {
        this.customerId = in.readString();
        this.customerName = in.readString();
        this.address = in.readString();
        this.tin = in.readString();
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel source) {
            return new Customer(source);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };
}
