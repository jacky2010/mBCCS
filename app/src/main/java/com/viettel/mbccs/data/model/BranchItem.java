package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.source.remote.response.DataResponse;

import java.io.Serializable;

/**
 * Created by minhnx on 5/19/17.
 */

public class BranchItem extends DataResponse implements Serializable {
    @Expose
    @SerializedName("channel_type")
    private String channelType;
    @Expose
    @SerializedName("channel_type_name")
    private String channelTypeName;
    @Expose
    @SerializedName("channel_code")
    private String channelCode;
    @Expose
    @SerializedName("phone_no")
    private String phoneNo;
    @Expose
    @SerializedName("document_type")
    private String documentType;
    @Expose
    @SerializedName("document_type_name")
    private String documentTypeName;
    @Expose
    @SerializedName("document_no")
    private String documentNo;
    @Expose
    @SerializedName("image_path")
    private String imagePath;
    @Expose
    @SerializedName("address")
    private String address;
    @Expose
    @SerializedName("latitude")
    private Double latitude;
    @Expose
    @SerializedName("longitude")
    private Double longitude;
    @Expose
    @SerializedName("staff_code")
    private String staffCode;
    @Expose
    @SerializedName("staff_name")
    private String staffName;
    @Expose
    @SerializedName("bts_code")
    private String btsCode;
    @Expose
    @SerializedName("bts_name")
    private String btsName;
    @Expose
    @SerializedName("document_image_path")
    private String documentImagePath;

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getBtsCode() {
        return btsCode;
    }

    public void setBtsCode(String btsCode) {
        this.btsCode = btsCode;
    }

    public String getDocumentImagePath() {
        return documentImagePath;
    }

    public void setDocumentImagePath(String documentImagePath) {
        this.documentImagePath = documentImagePath;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelTypeName() {
        return channelTypeName;
    }

    public void setChannelTypeName(String channelTypeName) {
        this.channelTypeName = channelTypeName;
    }

    public String getDocumentTypeName() {
        return documentTypeName;
    }

    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getBtsName() {
        return btsName;
    }

    public void setBtsName(String btsName) {
        this.btsName = btsName;
    }
}
