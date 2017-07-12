package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Anh Vu Viet on 7/2/2017.
 */

public class CreateTaskExtendRequest extends BaseRequest{

    @SerializedName("shopId")
    @Expose
    private String shopId;

    @SerializedName("staffId")
    @Expose
    private String staffId;

    @SerializedName("channelCode")
    @Expose
    private String channelCode;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("userCreate")
    @Expose
    private String userCreate;

    @SerializedName("shopCodeCreate")
    @Expose
    private String shopCodeCreate;

    @SerializedName("jobName")
    @Expose
    private String jobName;

    @SerializedName("jobDescription")
    @Expose
    private String jobDescription;

    @SerializedName("startDate")
    @Expose
    private String fromDate;

    @SerializedName("endDate")
    @Expose
    private String toDate;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getShopCodeCreate() {
        return shopCodeCreate;
    }

    public void setShopCodeCreate(String shopCodeCreate) {
        this.shopCodeCreate = shopCodeCreate;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
