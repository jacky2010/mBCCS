package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.HotNewsCSKPPItem;

import java.util.List;

/**
 * Created by minhnx on 6/7/17.
 */

public class GetHotNewsCSKPPResponse{
    @Expose
    @SerializedName("listHotNews")
    private List<HotNewsCSKPPItem> listHotNews;

    public List<HotNewsCSKPPItem> getListHotNews() {
        return listHotNews;
    }

    public void setListHotNews(List<HotNewsCSKPPItem> listHotNews) {
        this.listHotNews = listHotNews;
    }
}
