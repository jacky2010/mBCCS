package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by minhnx on 6/12/17.
 */

public class KPPFeedback {

    private static final int MAX_CONTENT_SHORTENED = 160;

    @Expose
    @SerializedName("id")
    private Long id;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("createDate")
    private String createDate;
    @Expose
    @SerializedName("createUser")
    private String createUser;
    @Expose
    @SerializedName("updateDate")
    private String updateDate;
    @Expose
    @SerializedName("updateUser")
    private String updateUser;
    @Expose
    @SerializedName("ownerCode")
    private String ownerCode;
    @Expose
    @SerializedName("lang")
    private String lang;
    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("response")
    private String response;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getContentShort() {
        try {
            if (content != null) {
                if (content.length() > MAX_CONTENT_SHORTENED)
                    return content.substring(0, MAX_CONTENT_SHORTENED - 3) + "...";
                return content;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getResponseShort() {
        try {
            if (response != null) {
                if (response.length() > MAX_CONTENT_SHORTENED)
                    return response.substring(0, MAX_CONTENT_SHORTENED - 3) + "...";
                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
