package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.Bank;
import java.util.List;

/**
 * Created by HuyQuyet on 7/10/17.
 */

public class GetBankInfoResponse {
    @SerializedName("listBank")
    @Expose
    private List<Bank> bankList;

    public List<Bank> getBankList() {
        return bankList;
    }

    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
    }
}
