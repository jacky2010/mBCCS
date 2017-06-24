package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Anh Vu Viet on 5/20/2017.
 */

public class TaskShopManagement implements Parcelable {

    @SerializedName("taskShopMngtId")
    @Expose
    private Integer taskShopMngtId;
    @SerializedName("reasonId")
    @Expose
    private Integer reasonId;
    @SerializedName("taskMngtId")
    @Expose
    private Integer taskMngtId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("progress")
    @Expose
    private String progress;
    @SerializedName("shopId")
    @Expose
    private Integer shopId;
    @SerializedName("createDate")
    @Expose
    private String createDate;
    @SerializedName("stageId")
    @Expose
    private Integer stageId;
    @SerializedName("status")
    @Expose
    @TaskStatus
    private Integer status;

    @IntDef({ TaskType.TYPE_SU_CO_CC, TaskType.TYPE_KHAO_SAT, TaskType.TYPE_TRIEN_KHAI_MOI,
            TaskType.TYPE_THAY_DOI_LAP_DAT, TaskType.TYPE_KIEM_TRA_CAP, TaskType.TYPE_KIEM_TRA_TRAM,
            TaskType.TYPE_BAO_DUONG_TRAM, TaskType.TYPE_PHAT_SINH, TaskType.TYPE_CSKPP
    })
    public @interface TaskType {
        int TYPE_SU_CO_CC = 1;
        int TYPE_KHAO_SAT = 2;
        int TYPE_TRIEN_KHAI_MOI = 3;
        int TYPE_THAY_DOI_LAP_DAT = 4;
        int TYPE_KIEM_TRA_CAP = 5;
        int TYPE_KIEM_TRA_TRAM = 6;
        int TYPE_BAO_DUONG_TRAM = 7;
        int TYPE_PHAT_SINH = 8;
        int TYPE_CSKPP = 9;
    }

    @IntDef({ TaskStatus.STATUS_NEW, TaskStatus.STATUS_ASSIGNED, TaskStatus.STATUS_CLOSE
    })
    public @interface TaskStatus {
        int STATUS_NEW = 0;
        int STATUS_ASSIGNED = 1;
        int STATUS_CLOSE = 2;
    }

    @IntDef({ TaskStaffStatus.STATUS_NEW, TaskStaffStatus.STATUS_INPROGRESS, TaskStaffStatus.STATUS_CLOSE,
            TaskStaffStatus.STATUS_REJECTED
    })
    public @interface TaskStaffStatus {
        int STATUS_NEW = 0;
        int STATUS_INPROGRESS = 1;
        int STATUS_CLOSE = 2;
        int STATUS_REJECTED = 3;
    }

    protected TaskShopManagement(Parcel in) {
        description = in.readString();
        progress = in.readString();
        createDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(progress);
        dest.writeString(createDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TaskShopManagement> CREATOR = new Creator<TaskShopManagement>() {
        @Override
        public TaskShopManagement createFromParcel(Parcel in) {
            return new TaskShopManagement(in);
        }

        @Override
        public TaskShopManagement[] newArray(int size) {
            return new TaskShopManagement[size];
        }
    };

    public Integer getTaskShopMngtId() {
        return taskShopMngtId;
    }

    public void setTaskShopMngtId(Integer taskShopMngtId) {
        this.taskShopMngtId = taskShopMngtId;
    }

    public Integer getReasonId() {
        return reasonId;
    }

    public void setReasonId(Integer reasonId) {
        this.reasonId = reasonId;
    }

    public Integer getTaskMngtId() {
        return taskMngtId;
    }

    public void setTaskMngtId(Integer taskMngtId) {
        this.taskMngtId = taskMngtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    @TaskStatus
    public Integer getStatus() {
        return status;
    }

    public void setStatus(@TaskStatus Integer status) {
        this.status = status;
    }
}
