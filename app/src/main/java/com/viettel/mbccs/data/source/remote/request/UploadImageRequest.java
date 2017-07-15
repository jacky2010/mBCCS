package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by HuyQuyet on 6/8/17.
 */

public class UploadImageRequest extends BaseRequest  {

    @SerializedName("lstUploadImageTran")
    private List<ImageRequest> lstUploadImageTran;

    public List<ImageRequest> getLstUploadImageTran() {
        return lstUploadImageTran;
    }

    public void setLstUploadImageTran(List<ImageRequest> lstUploadImageTran) {
        this.lstUploadImageTran = lstUploadImageTran;
    }

    public UploadImageRequest() {
        super();
    }

    public static class ImageRequest {
        String fileName;
        String content;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
