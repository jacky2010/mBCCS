package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;

/**
 * Created by Anh Vu Viet on 5/20/2017.
 */

public class TaskModel implements Parcelable {

    public static final Creator<TaskModel> CREATOR = new Creator<TaskModel>() {
        @Override
        public TaskModel createFromParcel(Parcel in) {
            return new TaskModel(in);
        }

        @Override
        public TaskModel[] newArray(int size) {
            return new TaskModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(type);
        dest.writeLong(createdDate);
        dest.writeInt(status);
        dest.writeString(startDate);
        dest.writeString(endDate);
        dest.writeString(assignDate);
        dest.writeString(description);
    }

    @IntDef({
            TaskType.TYPE_CSKPP, TaskType.TYPE_ARISING, TaskType.TYPE_TEAM
    })
    public @interface TaskType {
        int TYPE_CSKPP = 0;
        int TYPE_ARISING = 1;
        int TYPE_TEAM = 2;
    }

    @IntDef({
            TaskStatus.REJECTED, TaskStatus.NOT_ACCEPTED, TaskStatus.INPROGRESS, TaskStatus.DONE
    })
    public @interface TaskStatus {
        int REJECTED = 0;
        int NOT_ACCEPTED = 1;
        int INPROGRESS = 2;
        int DONE = 3;
    }

    private String title;

    @TaskType
    private int type;

    private long createdDate;

    @TaskStatus
    private int status;

    private String startDate;

    private String endDate;

    private String assignDate;

    private String description;

    public TaskModel(Parcel in) {
        title = in.readString();
        type = in.readInt();
        createdDate = in.readLong();
        status = in.readInt();
        startDate = in.readString();
        endDate = in.readString();
        description = in.readString();
    }

    public TaskModel(String title, @TaskType int type, long createdDate, @TaskStatus int status,
            String startDate, String endDate, String assignDate, String description) {
        this.title = title;
        this.type = type;
        this.createdDate = createdDate;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.assignDate = assignDate;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @TaskType
    public int getType() {
        return type;
    }

    public void setType(@TaskType int type) {
        this.type = type;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    @TaskStatus
    public int getStatus() {
        return status;
    }

    public void setStatus(@TaskStatus int status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
