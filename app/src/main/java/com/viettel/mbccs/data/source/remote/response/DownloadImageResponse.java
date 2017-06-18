package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.DataImage;
import java.util.List;

/**
 * Created by HuyQuyet on 6/16/17.
 */

public class DownloadImageResponse {

    @SerializedName("")
    @Expose
    private List<DataImage> dataImageList;

    public List<DataImage> getDataImageList() {
        return dataImageList;
    }

    public void setDataImageList(List<DataImage> dataImageList) {
        this.dataImageList = dataImageList;
    }
}
