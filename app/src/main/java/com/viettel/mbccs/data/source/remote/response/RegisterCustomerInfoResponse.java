package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by HuyQuyet on 5/31/17.
 */

public class RegisterCustomerInfoResponse extends DataResponse {

    @SerializedName("result")
    @Expose
    private String result;

    @SerializedName("lstNameImage")
    @Expose
    private List<String> nameImage;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setNameImage(List<String> nameImage) {
        this.nameImage = nameImage;
    }

    public List<String> getNameImage() {
        return nameImage;
    }
}
