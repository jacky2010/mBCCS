package com.viettel.mbccs.data.source.remote.response;

/**
 * Created by HuyQuyet on 6/2/17.
 */

public class SpinnerApDomain {
    private GetApDomainResponse spinnerGiayTo;
    private GetApDomainResponse spinnerHTTT;
    private GetApDomainResponse spinnerHTNhanTB;

    public SpinnerApDomain(GetApDomainResponse spinnerGiayTo, GetApDomainResponse spinnerHTTT,
            GetApDomainResponse spinnerHTNhanTB) {
        this.spinnerGiayTo = spinnerGiayTo;
        this.spinnerHTTT = spinnerHTTT;
        this.spinnerHTNhanTB = spinnerHTNhanTB;
    }

    public GetApDomainResponse getSpinnerGiayTo() {
        return spinnerGiayTo;
    }

    public void setSpinnerGiayTo(GetApDomainResponse spinnerGiayTo) {
        this.spinnerGiayTo = spinnerGiayTo;
    }

    public GetApDomainResponse getSpinnerHTTT() {
        return spinnerHTTT;
    }

    public void setSpinnerHTTT(GetApDomainResponse spinnerHTTT) {
        this.spinnerHTTT = spinnerHTTT;
    }

    public GetApDomainResponse getSpinnerHTNhanTB() {
        return spinnerHTNhanTB;
    }

    public void setSpinnerHTNhanTB(GetApDomainResponse spinnerHTNhanTB) {
        this.spinnerHTNhanTB = spinnerHTNhanTB;
    }
}
