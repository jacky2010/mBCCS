package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.Session;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class BaseRequest<T> {

    @SerializedName("sessionId")
    private Session mSession;

    @SerializedName("wsCode")
    private String wsCode;

    @SerializedName("apiKey")
    private String apiKey;

    @SerializedName("wsRequest")
    private T request;


}
