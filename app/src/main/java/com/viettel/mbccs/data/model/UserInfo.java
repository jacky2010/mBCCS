package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by FRAMGIA\vu.viet.anh on 12/06/2017.
 */

public class UserInfo implements Parcelable {

    @Expose
    @SerializedName("staff")
    private StaffInfo mStaffInfo;

    @Expose
    @SerializedName("shop")
    private Shop mShop;

    @Expose
    @SerializedName("channel")
    private ChannelInfo mChannelInfo;

    protected UserInfo(Parcel in) {
        mStaffInfo = in.readParcelable(StaffInfo.class.getClassLoader());
        mShop = in.readParcelable(Shop.class.getClassLoader());
        mChannelInfo = in.readParcelable(ChannelInfo.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mStaffInfo, flags);
        dest.writeParcelable(mShop, flags);
        dest.writeParcelable(mChannelInfo, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public UserInfo() {
    }

    public UserInfo(StaffInfo staffInfo, Shop shop, ChannelInfo channelInfo) {
        mStaffInfo = staffInfo;
        mShop = shop;
        mChannelInfo = channelInfo;
    }

    public ChannelInfo getChannelInfo() {
        return mChannelInfo;
    }

    public void setChannelInfo(ChannelInfo channelInfo) {
        mChannelInfo = channelInfo;
    }

    public StaffInfo getStaffInfo() {
        return mStaffInfo;
    }

    public void setStaffInfo(StaffInfo staffInfo) {
        mStaffInfo = staffInfo;
    }

    public Shop getShop() {
        return mShop;
    }

    public void setShop(Shop shop) {
        mShop = shop;
    }
}
