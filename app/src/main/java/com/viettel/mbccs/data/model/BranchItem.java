package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.viettel.mbccs.data.source.remote.response.DataResponse;

import java.io.Serializable;

/**
 * Created by minhnx on 5/19/17.
 */

public class BranchItem extends DataResponse implements Serializable{
    @Expose
    private String channelType;
    @Expose
    private String channelCode;
    @Expose
    private String phoneNo;
    @Expose
    private String documentType;
    @Expose
    private String documentNo;
    @Expose
    private String imagePath;
    @Expose
    private String address;
    @Expose
    private long latitude;
    @Expose
    private long longitude;
    @Expose
    private String staffCode;
    @Expose
    private String btsCode;
    @Expose
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

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
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
}
