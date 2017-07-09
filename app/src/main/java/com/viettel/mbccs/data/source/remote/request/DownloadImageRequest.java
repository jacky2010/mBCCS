package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by HuyQuyet on 6/16/17.
 */

public class DownloadImageRequest extends BaseRequest {

    @SerializedName("listImageName")
    @Expose
    private List<String> listIdImage;

    public List<String> getListIdImage() {
        return listIdImage;
    }

    public void setListIdImage(List<String> listIdImage) {
        this.listIdImage = listIdImage;
    }

    public DownloadImageRequest() {
        super();
    }
}
