package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 5/19/17.
 */

public class Reason implements Parcelable {

    // mã lý do
    @Expose
    @SerializedName("reasonId")
    private String reasonId;

    // code lý do
    @Expose
    @SerializedName("reasonCode")
    private String reasonCode;

    // tên lý do
    @Expose
    @SerializedName("reasonName")
    private String reasonName;

    public Reason() {

    }

    protected Reason(Parcel in) {
        reasonId = in.readString();
        reasonCode = in.readString();
        reasonName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(reasonId);
        dest.writeString(reasonCode);
        dest.writeString(reasonName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Reason> CREATOR = new Creator<Reason>() {
        @Override
        public Reason createFromParcel(Parcel in) {
            return new Reason(in);
        }

        @Override
        public Reason[] newArray(int size) {
            return new Reason[size];
        }
    };

    public String getReasonId() {
        return reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }
}
