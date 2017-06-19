package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by HuyQuyet on 6/18/17.
 */

public class GetListIdImageResponse {
    @Expose
    @SerializedName("lstImageName")
    private List<String> idImage;

    public List<String> getIdImage() {
        return idImage;
    }

    public void setIdImage(List<String> idImage) {
        this.idImage = idImage;
    }
}
