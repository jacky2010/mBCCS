package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Anh Vu Viet on 6/25/2017.
 */

public class GetStockModelForUpdateTaskRequest extends BaseRequest{

    @SerializedName("taskStaffMngtId")
    @Expose
    private Integer taskStaffMngtId;
    @SerializedName("type")
    @Expose
    private String type;

    public Integer getTaskStaffMngtId() {
        return taskStaffMngtId;
    }

    public void setTaskStaffMngtId(Integer taskStaffMngtId) {
        this.taskStaffMngtId = taskStaffMngtId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
