package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.LstAPStockModelBean;
import com.viettel.mbccs.data.model.LstSubStockModelRel;

import java.util.List;

/**
 * Created by Anh Vu Viet on 6/25/2017.
 */

public class CloseTaskRequest extends BaseRequest{

    @SerializedName("taskStaffMngtId ")
    @Expose
    private Integer taskStaffMngtId;
    @SerializedName("progress")
    @Expose
    private Integer progress;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("hasNIMS")
    @Expose
    private String hasNIMS;
    @SerializedName("lstAPStockModelBean")
    @Expose
    private List<LstAPStockModelBean> lstAPStockModelBean = null;
    @SerializedName("lstSubStockModelRel")
    @Expose
    private List<LstSubStockModelRel> lstSubStockModelRel = null;

    public Integer getTaskStaffMngtId() {
        return taskStaffMngtId;
    }

    public void setTaskStaffMngtId(Integer taskStaffMngtId) {
        this.taskStaffMngtId = taskStaffMngtId;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHasNIMS() {
        return hasNIMS;
    }

    public void setHasNIMS(String hasNIMS) {
        this.hasNIMS = hasNIMS;
    }

    public List<LstAPStockModelBean> getLstAPStockModelBean() {
        return lstAPStockModelBean;
    }

    public void setLstAPStockModelBean(List<LstAPStockModelBean> lstAPStockModelBean) {
        this.lstAPStockModelBean = lstAPStockModelBean;
    }

    public List<LstSubStockModelRel> getLstSubStockModelRel() {
        return lstSubStockModelRel;
    }

    public void setLstSubStockModelRel(List<LstSubStockModelRel> lstSubStockModelRel) {
        this.lstSubStockModelRel = lstSubStockModelRel;
    }
}
