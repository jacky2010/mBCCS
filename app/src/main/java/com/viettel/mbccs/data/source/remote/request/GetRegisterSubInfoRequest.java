package com.viettel.mbccs.data.source.remote.request;

/**
 * Created by HuyQuyet on 5/31/17.
 */

public class GetRegisterSubInfoRequest  extends BaseRequest {

    // thuê bao
    private String isdn;

    // số giấy tờ
    private String idNo;

    // loại giấy tờ
    private String idType;

    private String checkRegisterStatus;

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getCheckRegisterStatus() {
        return checkRegisterStatus;
    }

    public void setCheckRegisterStatus(String checkRegisterStatus) {
        this.checkRegisterStatus = checkRegisterStatus;
    }

    public GetRegisterSubInfoRequest() {
        super();
    }
}
