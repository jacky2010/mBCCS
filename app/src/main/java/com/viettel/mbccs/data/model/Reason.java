package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 5/19/17.
 */

public class Reason implements Parcelable {

    @Expose
    @SerializedName("ReasonId")
    private long reasonId;

    @Expose
    @SerializedName("ReasonCode")
    private String reasonCode;

    @Expose
    @SerializedName("ReasonName")
    private String reasonName;

    public Reason() {

    }

    protected Reason(Parcel in) {
        reasonId = in.readLong();
        reasonCode = in.readString();
        reasonName = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(reasonId);
        dest.writeString(reasonCode);
        dest.writeString(reasonName);
    }

    public long getReasonId() {
        return reasonId;
    }

    public void setReasonId(long reasonId) {
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
