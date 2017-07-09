package com.viettel.mbccs.data.source.remote.request;

/**
 * Created by HuyQuyet on 6/8/17.
 */

public class UploadImageRequest extends BaseRequest  {
    private String actionBusiness;
    private String objectId;
    private int order;
    private int docType;
    private String fileName;
    private String imageData;
    private String transDate;

    public String getActionBusiness() {
        return actionBusiness;
    }

    public void setActionBusiness(String actionBusiness) {
        this.actionBusiness = actionBusiness;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getDocType() {
        return docType;
    }

    public void setDocType(int docType) {
        this.docType = docType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public UploadImageRequest() {
        super();
    }
}
