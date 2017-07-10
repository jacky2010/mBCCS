package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.StaffInfo;

import java.util.List;

/**
 * Created by Anh Vu Viet on 7/2/2017.
 */

public class GetStaffToAssignTaskResponse {

    @SerializedName("lstStaff")
    @Expose
    private List<StaffInfo> lstStaff;

    public List<StaffInfo> getLstStaff() {
        return lstStaff;
    }

    public void setLstStaff(List<StaffInfo> lstStaff) {
        this.lstStaff = lstStaff;
    }
}
