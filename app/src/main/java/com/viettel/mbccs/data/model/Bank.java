package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 7/10/17.
 */

public class Bank implements Parcelable {

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("amtBankId")
    @Expose
    private long amtBankId;

    @SerializedName("bankCode")
    @Expose
    private String bankCode;

    @SerializedName("bankType")
    @Expose
    private String bankType;

    @SerializedName("contactName")
    @Expose
    private String contactName;

    @SerializedName("contactTitle")
    @Expose
    private String contactTitle;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("fax")
    @Expose
    private String fax;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("oldDistrict")
    @Expose
    private String oldDistrict;

    @SerializedName("province")
    @Expose
    private String province;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("telNumber")
    @Expose
    private String telNumber;

    public Bank() {

    }

    protected Bank(Parcel in) {
        address = in.readString();
        amtBankId = in.readLong();
        bankCode = in.readString();
        bankType = in.readString();
        contactName = in.readString();
        contactTitle = in.readString();
        description = in.readString();
        email = in.readString();
        fax = in.readString();
        name = in.readString();
        oldDistrict = in.readString();
        province = in.readString();
        status = in.readString();
        telNumber = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeLong(amtBankId);
        dest.writeString(bankCode);
        dest.writeString(bankType);
        dest.writeString(contactName);
        dest.writeString(contactTitle);
        dest.writeString(description);
        dest.writeString(email);
        dest.writeString(fax);
        dest.writeString(name);
        dest.writeString(oldDistrict);
        dest.writeString(province);
        dest.writeString(status);
        dest.writeString(telNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Bank> CREATOR = new Creator<Bank>() {
        @Override
        public Bank createFromParcel(Parcel in) {
            return new Bank(in);
        }

        @Override
        public Bank[] newArray(int size) {
            return new Bank[size];
        }
    };

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getAmtBankId() {
        return amtBankId;
    }

    public void setAmtBankId(long amtBankId) {
        this.amtBankId = amtBankId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOldDistrict() {
        return oldDistrict;
    }

    public void setOldDistrict(String oldDistrict) {
        this.oldDistrict = oldDistrict;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    @Override
    public String toString() {
        return getName();
    }
}
