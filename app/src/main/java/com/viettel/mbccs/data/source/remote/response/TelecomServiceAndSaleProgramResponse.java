package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.SaleProgram;
import com.viettel.mbccs.data.model.TeleComService;
import java.util.ArrayList;
import java.util.List;

public class TelecomServiceAndSaleProgramResponse extends DataResponse{

    @SerializedName("telecomService")
    @Expose
    private List<TeleComService> mTeleComServices = new ArrayList<>();

    @SerializedName("saleProgram")
    @Expose
    private List<SaleProgram> mSalePrograms = new ArrayList<>();

    public List<TeleComService> getTeleComServices() {
        return mTeleComServices;
    }

    public void setTeleComServices(List<TeleComService> teleComServices) {
        mTeleComServices = teleComServices;
    }

    public List<SaleProgram> getSalePrograms() {
        return mSalePrograms;
    }

    public void setSalePrograms(List<SaleProgram> salePrograms) {
        mSalePrograms = salePrograms;
    }
}
