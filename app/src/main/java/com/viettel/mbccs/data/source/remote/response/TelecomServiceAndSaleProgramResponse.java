package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.SaleProgram;
import com.viettel.mbccs.data.model.TelecomService;
import java.util.ArrayList;
import java.util.List;

public class TelecomServiceAndSaleProgramResponse extends DataResponse{

    @SerializedName("lstTelecomService")
    @Expose
    private List<TelecomService> mTeleComServices = new ArrayList<>();

    @SerializedName("lstSalesProgram")
    @Expose
    private List<SaleProgram> mSalePrograms = new ArrayList<>();

    public List<TelecomService> getTeleComServices() {
        return mTeleComServices;
    }

    public void setTeleComServices(List<TelecomService> teleComServices) {
        mTeleComServices = teleComServices;
    }

    public List<SaleProgram> getSalePrograms() {
        return mSalePrograms;
    }

    public void setSalePrograms(List<SaleProgram> salePrograms) {
        mSalePrograms = salePrograms;
    }
}
