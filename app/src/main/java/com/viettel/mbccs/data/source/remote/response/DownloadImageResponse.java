package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by HuyQuyet on 6/16/17.
 */

public class DownloadImageResponse {

    @SerializedName("lstStockModelImage")
    @Expose
    private List<DataImage> dataImageList;

    public List<DataImage> getDataImageList() {
        return dataImageList;
    }

    public void setDataImageList(List<DataImage> dataImageList) {
        this.dataImageList = dataImageList;
    }

    public class DataImage {
        @Expose
        @SerializedName("imageName")
        private String id;

        @Expose
        @SerializedName("content")
        private String data;

        @Expose
        @SerializedName("version")
        private String version;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
