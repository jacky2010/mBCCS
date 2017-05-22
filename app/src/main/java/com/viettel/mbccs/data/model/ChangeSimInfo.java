package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by minhnx on 5/22/17.
 */

public class ChangeSimInfo implements Serializable{
    @SerializedName("old_serial")
    @Expose
    private String oldSerial;
    @SerializedName("new_serial")
    private String newSerial;

    public String getOldSerial() {
        return oldSerial;
    }

    public void setOldSerial(String oldSerial) {
        this.oldSerial = oldSerial;
    }

    public String getNewSerial() {
        return newSerial;
    }

    public void setNewSerial(String newSerial) {
        this.newSerial = newSerial;
    }

    public ChangeSimInfo(String oldSerial, String newSerial) {
        this.oldSerial = oldSerial;
        this.newSerial = newSerial;
    }

    public ChangeSimInfo() {
    }
}
