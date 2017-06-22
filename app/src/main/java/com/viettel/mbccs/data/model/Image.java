package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HuyQuyet on 6/18/17.
 */

public class Image implements Parcelable {
    private String imageID;

    private String imageName;

    private String imageVersion;

    private String imagePath;

    private String imageUri;

    private int imageStatus;

    public Image() {
    }

    protected Image(Parcel in) {
        imageID = in.readString();
        imageName = in.readString();
        imageVersion = in.readString();
        imagePath = in.readString();
        imageUri = in.readString();
        imageStatus = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageID);
        dest.writeString(imageName);
        dest.writeString(imageVersion);
        dest.writeString(imagePath);
        dest.writeString(imageUri);
        dest.writeInt(imageStatus);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageVersion() {
        return imageVersion;
    }

    public void setImageVersion(String imageVersion) {
        this.imageVersion = imageVersion;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public int getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(int imageStatus) {
        this.imageStatus = imageStatus;
    }
}
