package com.viettel.mbccs.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.model.Subscriber;

/**
 * Created by HuyQuyet on 5/31/17.
 */

public class GetRegiterSubInfoResponse extends DataResponse implements Parcelable{

    @SerializedName("subscriber")
    @Expose
    private Subscriber subscriber;

    @SerializedName("customer")
    @Expose
    private Customer customer;

    @SerializedName("result")
    @Expose
    private boolean result;

    public GetRegiterSubInfoResponse (){

    }

    protected GetRegiterSubInfoResponse(Parcel in) {
        subscriber = in.readParcelable(Subscriber.class.getClassLoader());
        customer = in.readParcelable(Customer.class.getClassLoader());
        result = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(subscriber, flags);
        dest.writeParcelable(customer, flags);
        dest.writeByte((byte) (result ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GetRegiterSubInfoResponse> CREATOR =
            new Creator<GetRegiterSubInfoResponse>() {
                @Override
                public GetRegiterSubInfoResponse createFromParcel(Parcel in) {
                    return new GetRegiterSubInfoResponse(in);
                }

                @Override
                public GetRegiterSubInfoResponse[] newArray(int size) {
                    return new GetRegiterSubInfoResponse[size];
                }
            };

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isResult() {
        return result;
    }

    public void Result(boolean status) {
        this.result = result;
    }
}
