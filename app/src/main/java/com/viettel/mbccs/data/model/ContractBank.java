package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 7/10/17.
 */

public class ContractBank implements Parcelable{

    @SerializedName("account")
    @Expose
    private String account;

    @SerializedName("accountName")
    @Expose
    private String accountName;

    @SerializedName("bankCode")
    @Expose
    private String bankCode;

    @SerializedName("bankContractDate")
    @Expose
    private String bankContractDate;

    @SerializedName("bankContractNo")
    @Expose
    private String bankContractNo;

    @SerializedName("contractId")
    @Expose
    private Long contractId;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("status")
    @Expose
    private Long status;

    public ContractBank(){}

    protected ContractBank(Parcel in) {
        account = in.readString();
        accountName = in.readString();
        bankCode = in.readString();
        bankContractDate = in.readString();
        bankContractNo = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(account);
        dest.writeString(accountName);
        dest.writeString(bankCode);
        dest.writeString(bankContractDate);
        dest.writeString(bankContractNo);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ContractBank> CREATOR = new Creator<ContractBank>() {
        @Override
        public ContractBank createFromParcel(Parcel in) {
            return new ContractBank(in);
        }

        @Override
        public ContractBank[] newArray(int size) {
            return new ContractBank[size];
        }
    };

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankContractDate() {
        return bankContractDate;
    }

    public void setBankContractDate(String bankContractDate) {
        this.bankContractDate = bankContractDate;
    }

    public String getBankContractNo() {
        return bankContractNo;
    }

    public void setBankContractNo(String bankContractNo) {
        this.bankContractNo = bankContractNo;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
