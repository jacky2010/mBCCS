package com.viettel.mbccs.data.model.database;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;
import android.support.annotation.StringDef;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by HuyQuyet on 6/8/17.
 */

@Table(name = "UploadImage")
public class UploadImage extends Model implements Parcelable {

    @IntDef({ ImageType.FRONT, ImageType.BACKSIDE, ImageType.PORTRAIT })
    public @interface ImageType {
        int FRONT = 1;
        int BACKSIDE = 2;
        int PORTRAIT = 3;
    }

    @StringDef({ StatusUpload.WAITING, StatusUpload.IN_PROGRESS, StatusUpload.SUCCESS, StatusUpload.ERROR})
    public @interface StatusUpload {
        String WAITING = "Waiting";
        String IN_PROGRESS = "Inprogress";
        String SUCCESS = "Success";
        String ERROR = "Error";
    }

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "content")
    private String content;

    @StatusUpload
    @Column(name = "status")
    private String status;

    public UploadImage() {
        super();
    }

    protected UploadImage(Parcel in) {
        imageName = in.readString();
        content = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageName);
        dest.writeString(content);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UploadImage> CREATOR = new Creator<UploadImage>() {
        @Override
        public UploadImage createFromParcel(Parcel in) {
            return new UploadImage(in);
        }

        @Override
        public UploadImage[] newArray(int size) {
            return new UploadImage[size];
        }
    };

    public String getImageName() {
        return imageName;
    }

    public void setIdImage(String imageName) {
        this.imageName = imageName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(@StatusUpload String status) {
        this.status = status;
    }
}
