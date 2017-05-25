package com.viettel.mbccs.data.model;

import android.support.annotation.IntDef;

/**
 * Created by Anh Vu Viet on 5/20/2017.
 */

public class JobModel {

    @IntDef({
            JobType.TYPE_CSKPP, JobType.TYPE_ARISING, JobType.TYPE_TEAM
    })
    public @interface JobType {
        int TYPE_CSKPP = 0;
        int TYPE_ARISING = 1;
        int TYPE_TEAM = 2;
    }

    @IntDef({
            JobStatus.REJECTED, JobStatus.NOT_ACCEPTED, JobStatus.INPROGRESS, JobStatus.DONE
    })
    public @interface JobStatus {
        int REJECTED = 0;
        int NOT_ACCEPTED = 1;
        int INPROGRESS = 2;
        int DONE = 3;
    }

    private String title;

    @JobType
    private int type;

    private long createdDate;

    @JobStatus
    private int status;

    public JobModel(String title, @JobType int type, long createdDate, @JobStatus int status) {
        this.title = title;
        this.type = type;
        this.createdDate = createdDate;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JobType
    public int getType() {
        return type;
    }

    public void setType(@JobType int type) {
        this.type = type;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    @JobStatus
    public int getStatus() {
        return status;
    }

    public void setStatus(@JobStatus int status) {
        this.status = status;
    }
}
