package com.viettel.mbccs.data.source.remote.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.MBCCSApplication;
import com.viettel.mbccs.data.model.Session;

/**
 * Created by HuyQuyet on 5/27/17.
 */

public class DataRequest implements Parcelable{
    @Expose
    @SerializedName("session")
    private Session mSession;

    @Expose
    @SerializedName("apiCode")
    private String apiCode;

    @Expose
    @SerializedName("apiKey")
    private String apiKey;

    public DataRequest() {
        mSession = MBCCSApplication.session;
        apiKey = MBCCSApplication.apiKey;
    }

    public Session getSession() {
        return mSession;
    }

    public void setSession(Session mSession) {
        this.mSession = mSession;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mSession, flags);
        dest.writeString(this.apiCode);
        dest.writeString(this.apiKey);
    }

    protected DataRequest(Parcel in) {
        this.mSession = in.readParcelable(Session.class.getClassLoader());
        this.apiCode = in.readString();
        this.apiKey = in.readString();
    }

    public static final Creator<DataRequest> CREATOR = new Creator<DataRequest>() {
        @Override
        public DataRequest createFromParcel(Parcel source) {
            return new DataRequest(source);
        }

        @Override
        public DataRequest[] newArray(int size) {
            return new DataRequest[size];
        }
    };
}
