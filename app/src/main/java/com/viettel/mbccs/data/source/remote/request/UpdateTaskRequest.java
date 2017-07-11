package com.viettel.mbccs.data.source.remote.request;

import android.support.annotation.IntDef;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Anh Vu Viet on 6/25/2017.
 */

public class UpdateTaskRequest extends BaseRequest{

    @SerializedName("taskStaffMngtId")
    @Expose
    private Integer taskStaffMngtId;

    @SerializedName("progress")
    @Expose
    @TaskProgress
    private Integer progress;

    @SerializedName("type")
    @Expose
    private String type;

    @IntDef({ TaskProgress.STATUS_DONG_Y, TaskProgress.STATUS_TU_CHOI })
    public @interface TaskProgress {
        int STATUS_DONG_Y = 1;
        int STATUS_TU_CHOI = 2;
    }

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

    @TaskProgress
    public Integer getProgress() {
        return progress;
    }

    public void setProgress(@TaskProgress Integer progress) {
        this.progress = progress;
    }
}
