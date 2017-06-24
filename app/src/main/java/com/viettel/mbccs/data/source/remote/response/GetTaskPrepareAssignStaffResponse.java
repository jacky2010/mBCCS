package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.StaffInfo;
import com.viettel.mbccs.data.model.TaskShopManagement;

import java.util.List;

/**
 * Created by Anh Vu Viet on 6/23/2017.
 */

public class GetTaskPrepareAssignStaffResponse extends DataResponse {

    @SerializedName("lstTaskShopManagement")
    @Expose
    private List<TaskShopManagement> lstTaskShopManagement = null;

    @SerializedName("lstStaff")
    @Expose
    private List<StaffInfo> lstStaff = null;

    public List<TaskShopManagement> getLstTaskShopManagement() {
        return lstTaskShopManagement;
    }

    public void setLstTaskShopManagement(List<TaskShopManagement> lstTaskShopManagement) {
        this.lstTaskShopManagement = lstTaskShopManagement;
    }

    public List<StaffInfo> getLstStaff() {
        return lstStaff;
    }

    public void setLstStaff(List<StaffInfo> lstStaff) {
        this.lstStaff = lstStaff;
    }
}
