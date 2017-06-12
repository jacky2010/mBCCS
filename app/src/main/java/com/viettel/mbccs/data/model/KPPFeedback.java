package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by minhnx on 6/12/17.
 */

public class KPPFeedback {

    private static final  int MAX_CONTENT_SHORTENED = 160;

    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("create_date")
    private String createDate;
    @Expose
    @SerializedName("creator")
    private String creator;
    @Expose
    @SerializedName("response_date")
    private String responseDate;
    @Expose
    @SerializedName("response_user")
    private String responseUser;

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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    public String getResponseUser() {
        return responseUser;
    }

    public void setResponseUser(String responseUser) {
        this.responseUser = responseUser;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getContentShort(){
        try{
            if(content != null)
            {
                if(content.length() > MAX_CONTENT_SHORTENED)
                    return content.substring(0, MAX_CONTENT_SHORTENED - 3) + "...";
                return content;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public String getResponseShort(){
        try{
            if(response != null)
            {
                if(response.length() > MAX_CONTENT_SHORTENED)
                    return response.substring(0, MAX_CONTENT_SHORTENED - 3) + "...";
                return response;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
