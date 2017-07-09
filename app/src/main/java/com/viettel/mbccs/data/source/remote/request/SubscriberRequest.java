package com.viettel.mbccs.data.source.remote.request;

/**
 * Created by jacky on 6/24/17.
 */

public class SubscriberRequest  extends BaseRequest {
    public String serviceType;
    public String isdn;
    public String shopCode;
    public String province;
    public String district;
    public String precinct;
    public int staffId;

    public SubscriberRequest() {
        super();
    }
}
