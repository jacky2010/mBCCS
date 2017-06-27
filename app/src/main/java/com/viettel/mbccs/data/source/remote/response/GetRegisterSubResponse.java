package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.Subscriber;

import java.util.List;

/**
 * Created by minhnx on 6/7/17.
 */

public class GetRegisterSubResponse extends DataResponse {
    @Expose
    @SerializedName("listSubscriber")
    private List<Subscriber> listSubscriber;

    public List<Subscriber> getListSubscriber() {
        return listSubscriber;
    }

    public void setListSubscriber(List<Subscriber> listSubscriber) {
        this.listSubscriber = listSubscriber;
    }
}
