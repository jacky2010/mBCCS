package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.TaskStaffManagement;

import java.util.List;

/**
 * Created by Anh Vu Viet on 7/2/2017.
 */

public class GetInfoTaskForUpdateResponse {

    @SerializedName("lstTaskStaffManagement")
    @Expose
    private List<TaskStaffManagement> lstTaskStaffManagement = null;

    public List<TaskStaffManagement> getLstTaskStaffManagement() {
        return lstTaskStaffManagement;
    }

    public void setLstTaskStaffManagement(List<TaskStaffManagement> lstTaskStaffManagement) {
        this.lstTaskStaffManagement = lstTaskStaffManagement;
    }
}
