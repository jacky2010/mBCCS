package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by minhnx on 6/7/17.
 */

public class KPPRespondFeedbackRequest extends BaseRequest  {
    @Expose
    @SerializedName("username")
    private String username;
    @Expose
    @SerializedName("feedbackId")
    private Long feedbackId;
    @Expose
    @SerializedName("content")
    private String content;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public KPPRespondFeedbackRequest() {
        super();
    }
}
