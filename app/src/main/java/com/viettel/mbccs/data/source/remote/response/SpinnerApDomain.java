package com.viettel.mbccs.data.source.remote.response;

/**
 * Created by HuyQuyet on 6/2/17.
 */

public class SpinnerApDomain {
    private GetApDomainByTypeResponse spinnerGiayTo;
    private GetApDomainByTypeResponse spinnerHTTT;
    private GetApDomainByTypeResponse spinnerHTNhanTB;
    private GetListProductResponse spinnerListProduct;

    public SpinnerApDomain(GetApDomainByTypeResponse spinnerGiayTo,
            GetApDomainByTypeResponse spinnerHTTT, GetApDomainByTypeResponse spinnerHTNhanTB,
            GetListProductResponse spinnerListProduct) {
        this.spinnerGiayTo = spinnerGiayTo;
        this.spinnerHTTT = spinnerHTTT;
        this.spinnerHTNhanTB = spinnerHTNhanTB;
        this.spinnerListProduct = spinnerListProduct;
    }

    public GetApDomainByTypeResponse getSpinnerGiayTo() {
        return spinnerGiayTo;
    }

    public void setSpinnerGiayTo(GetApDomainByTypeResponse spinnerGiayTo) {
        this.spinnerGiayTo = spinnerGiayTo;
    }

    public GetApDomainByTypeResponse getSpinnerHTTT() {
        return spinnerHTTT;
    }

    public void setSpinnerHTTT(GetApDomainByTypeResponse spinnerHTTT) {
        this.spinnerHTTT = spinnerHTTT;
    }

    public GetApDomainByTypeResponse getSpinnerHTNhanTB() {
        return spinnerHTNhanTB;
    }

    public void setSpinnerHTNhanTB(GetApDomainByTypeResponse spinnerHTNhanTB) {
        this.spinnerHTNhanTB = spinnerHTNhanTB;
    }

    public GetListProductResponse getSpinnerListProduct() {
        return spinnerListProduct;
    }

    public void setSpinnerListProduct(GetListProductResponse spinnerListProduct) {
        this.spinnerListProduct = spinnerListProduct;
    }
}
