package com.viettel.mbccs.data.source.remote.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 6/4/17.
 */

public class CheckIdNoRequest implements Parcelable {

    // Số giấy tờ
    @SerializedName("idNo")
    @Expose
    private String idNo;

    // Loại thuê bao
    @SerializedName("subType")
    @Expose
    private String subType;

    // Dịch vụ
    @SerializedName("serviceType")
    @Expose
    private String serviceType;

    public CheckIdNoRequest() {
    }

    protected CheckIdNoRequest(Parcel in) {
        idNo = in.readString();
        subType = in.readString();
        serviceType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idNo);
        dest.writeString(subType);
        dest.writeString(serviceType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CheckIdNoRequest> CREATOR = new Creator<CheckIdNoRequest>() {
        @Override
        public CheckIdNoRequest createFromParcel(Parcel in) {
            return new CheckIdNoRequest(in);
        }

        @Override
        public CheckIdNoRequest[] newArray(int size) {
            return new CheckIdNoRequest[size];
        }
    };

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
