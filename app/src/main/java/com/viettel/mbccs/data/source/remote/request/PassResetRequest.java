package com.viettel.mbccs.data.source.remote.request;

/**
 * Created by FRAMGIA\bui.dinh.viet on 19/06/2017.
 */

public class PassResetRequest  extends BaseRequest {
    private String username;
    private String passold;
    private String passnew;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassold() {
        return passold;
    }

    public void setPassold(String passold) {
        this.passold = passold;
    }

    public String getPassnew() {
        return passnew;
    }

    public void setPassnew(String passnew) {
        this.passnew = passnew;
    }

    public PassResetRequest() {
        super();
    }
}
