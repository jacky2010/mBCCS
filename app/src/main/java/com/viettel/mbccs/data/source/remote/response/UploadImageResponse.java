package com.viettel.mbccs.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 6/8/17.
 */

public class UploadImageResponse implements Parcelable {

    @SerializedName("result")
    @Expose
    private String result;

    @SerializedName("fileName")
    @Expose
    private String fileName;

    protected UploadImageResponse(Parcel in) {
        result = in.readString();
        fileName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(result);
        dest.writeString(fileName);
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
