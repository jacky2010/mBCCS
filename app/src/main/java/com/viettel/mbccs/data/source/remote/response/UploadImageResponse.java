package com.viettel.mbccs.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by HuyQuyet on 6/8/17.
 */

public class UploadImageResponse implements Parcelable {

    @SerializedName("result")
    @Expose
    private String result;

    @SerializedName("lstName")
    @Expose
    private List<String> lstName;

    protected UploadImageResponse(Parcel in) {
        result = in.readString();
        lstName = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(result);
        dest.writeStringList(lstName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UploadImageResponse> CREATOR = new Creator<UploadImageResponse>() {
        @Override
        public UploadImageResponse createFromParcel(Parcel in) {
            return new UploadImageResponse(in);
        }

        @Override
        public UploadImageResponse[] newArray(int size) {
            return new UploadImageResponse[size];
        }
    };

    public List<String> getLstName() {
        return lstName;
    }

    public void setLstName(List<String> lstName) {
        this.lstName = lstName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
