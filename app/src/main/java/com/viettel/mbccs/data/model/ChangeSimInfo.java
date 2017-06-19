package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by minhnx on 5/22/17.
 */

public class ChangeSimInfo implements Serializable {
    @SerializedName("old_serial")
    @Expose
    private String oldSerial;
    @SerializedName("new_serial")
    @Expose
    private String newSerial;
    @SerializedName("recent_contact")
    @Expose
    private List<String> recentContacts;

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

    public List<String> getRecentContacts() {
        return recentContacts;
    }

    public void setRecentContacts(List<String> recentContacts) {
        this.recentContacts = recentContacts;
    }

    public ChangeSimInfo(String oldSerial, String newSerial) {
        this.oldSerial = oldSerial;
        this.newSerial = newSerial;
    }

    public ChangeSimInfo() {
    }
}
