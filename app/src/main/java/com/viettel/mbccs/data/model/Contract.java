package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.StringDef;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by HuyQuyet on 6/2/17.
 */

public class Contract implements Parcelable {
    @StringDef({ TypeNoticeChange.AT_HOME, TypeNoticeChange.SMS, TypeNoticeChange.EMAIL })
    public @interface TypeNoticeChange {
        String SMS = "2";
        String EMAIL = "3";
        String AT_HOME = "1";
    }

    @SerializedName("contractId")
    @Expose
    private String contractId;

    @SerializedName("accountNumber")
    @Expose
    private String accountNumber;

    @SerializedName("noticeCharge")
    @Expose
    private List<String> noticeCharge;

    @SerializedName("payMethodCode")
    @Expose
    private String payMethodCode;

    public static final Creator<Contract> CREATOR = new Creator<Contract>() {
        @Override
        public Contract createFromParcel(Parcel in) {
            return new Contract(in);
        }

        @Override
        public Contract[] newArray(int size) {
            return new Contract[size];
        }
    };

    public Contract() {
    }

    protected Contract(Parcel in) {
        contractId = in.readString();
        accountNumber = in.readString();
        noticeCharge = in.createStringArrayList();
        payMethodCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contractId);
        dest.writeString(accountNumber);
        dest.writeStringList(noticeCharge);
        dest.writeString(payMethodCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<String> getNoticeCharge() {
        return noticeCharge;
    }

    public void setNoticeCharge(List<String> noticeCharge) {
        this.noticeCharge = noticeCharge;
    }

    public String getPayMethodCode() {
        return payMethodCode;
    }

    public void setPayMethodCode(String payMethodCode) {
        this.payMethodCode = payMethodCode;
    }
}
