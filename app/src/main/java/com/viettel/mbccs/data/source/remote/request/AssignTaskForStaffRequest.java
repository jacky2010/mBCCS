package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Anh Vu Viet on 6/25/2017.
 */

public class AssignTaskForStaffRequest extends BaseRequest{

    @SerializedName("taskShopMngtId")
    @Expose
    private Integer taskShopMngtId;
    @SerializedName("staffId")
    @Expose
    private Integer staffId;
    @SerializedName("type")
    @Expose
    private String type;

    public Integer getTaskShopMngtId() {
        return taskShopMngtId;
    }

    public void setTaskShopMngtId(Integer taskShopMngtId) {
        this.taskShopMngtId = taskShopMngtId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
