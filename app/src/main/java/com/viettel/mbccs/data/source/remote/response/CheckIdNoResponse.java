package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.Contract;
import com.viettel.mbccs.data.model.Customer;
import java.util.List;

/**
 * Created by HuyQuyet on 6/4/17.
 */

public class CheckIdNoResponse extends DataResponse {

    @SerializedName("customer")
    @Expose
    private Customer customer;

    @SerializedName("lstContract ")
    @Expose
    private List<Contract> contractList;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }
}
