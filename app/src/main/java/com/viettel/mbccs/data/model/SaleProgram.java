package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class SaleProgram implements Serializable {

    @SerializedName("programId")
    @Expose
    private long id;

    @SerializedName("programCode")
    @Expose
    private String code;

    @SerializedName("programName")
    @Expose
    private String name;

    @SerializedName("from_Date")
    @Expose
    private String fromDate;

    @SerializedName("to_Date")
    @Expose
    private String toDate;

    public SaleProgram() {

    }



    public SaleProgram(long id, String code, String name) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public SaleProgram(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
