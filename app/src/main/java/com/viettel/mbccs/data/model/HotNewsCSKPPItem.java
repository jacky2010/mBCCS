package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by minhnx on 6/10/17.
 */

public class HotNewsCSKPPItem {

    private static final int MAX_CONTENT_SHORTENED = 160;

    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("createDate")
    private String createDate;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("createUser")
    private String createUser;
    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("ownerCode")
    private String ownerCode;
    @Expose
    @SerializedName("lang")
    private String lang;
    @Expose
    @SerializedName("updateDate")
    private String updateDate;
    @Expose
    @SerializedName("updateUser")
    private String updateUser;
    @Expose
    @SerializedName("shortContent")
    private String shortContent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
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
}
