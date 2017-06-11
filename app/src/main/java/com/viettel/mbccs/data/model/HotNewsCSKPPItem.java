package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by minhnx on 6/10/17.
 */

public class HotNewsCSKPPItem {

    private static final  int MAX_CONTENT_SHORTENED = 160;

    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("create_date")
    private String createDate;
    @Expose
    @SerializedName("content")
    private String content;

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
}
