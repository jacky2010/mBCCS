package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.Contract;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.model.Subscriber;

/**
 * Created by HuyQuyet on 6/2/17.
 */

public class UpdateAllSubInfoRequest  extends BaseRequest {
    @SerializedName("customer")
    @Expose
    private Customer customer;

    @SerializedName("subscriber")
    @Expose
    private Subscriber subscriber;

    @SerializedName("contract")
    @Expose
    private Contract contract;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public UpdateAllSubInfoRequest() {
        super();
    }
}
