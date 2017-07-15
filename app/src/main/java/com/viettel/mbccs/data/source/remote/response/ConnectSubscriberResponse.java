package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by HuyQuyet on 7/3/17.
 */

public class ConnectSubscriberResponse {

    @SerializedName("lstNameImage")
    @Expose
    private List<String> nameImage;

    public List<String> getNameImage() {
        return nameImage;
    }

    public void setNameImage(List<String> nameImage) {
        this.nameImage = nameImage;
    }
}
