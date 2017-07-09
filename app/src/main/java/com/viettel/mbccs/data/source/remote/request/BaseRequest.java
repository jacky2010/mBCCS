package com.viettel.mbccs.data.source.remote.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.source.UserRepository;

/**
 * Created by eo_cuong on 7/9/17.
 */

public class BaseRequest implements Parcelable {

    public BaseRequest() {
        setLanguage(UserRepository.getInstance().getLanguageFromSharePrefs());
    }

    @SerializedName("language")
    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.language);
    }

    protected BaseRequest(Parcel in) {
        this.language = in.readString();
    }

    public static final Creator<BaseRequest> CREATOR = new Creator<BaseRequest>() {
        @Override
        public BaseRequest createFromParcel(Parcel source) {
            return new BaseRequest(source);
        }

        @Override
        public BaseRequest[] newArray(int size) {
            return new BaseRequest[size];
        }
    };
}
