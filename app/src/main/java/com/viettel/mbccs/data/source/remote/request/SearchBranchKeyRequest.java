package com.viettel.mbccs.data.source.remote.request;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by minhnx on 5/21/17.
 */

public class SearchBranchKeyRequest extends BaseRequest  implements Parcelable {
    @SerializedName("Field01")
    @Expose
    private String isdn;
    @SerializedName("Field02")
    @Expose
    private String documentId;

    public SearchBranchKeyRequest(){
        super();
    }

    protected SearchBranchKeyRequest(Parcel in) {
        isdn = in.readString();
        documentId = in.readString();
    }

    public static final Creator<SearchBranchKeyRequest> CREATOR = new Creator<SearchBranchKeyRequest>() {
        @Override
        public SearchBranchKeyRequest createFromParcel(Parcel in) {
            return new SearchBranchKeyRequest(in);
        }

        @Override
        public SearchBranchKeyRequest[] newArray(int size) {
            return new SearchBranchKeyRequest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(isdn);
        parcel.writeString(documentId);
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

}
