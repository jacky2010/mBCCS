package com.viettel.mbccs.data.model.database;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by HuyQuyet on 6/18/17.
 */

@Table(name = "ImageDataBase")
public class ImageDataBase extends Model {

    @StringDef({
            Columns.IMAGE_ID, Columns.IMAGE_NAME, Columns.IMAGE_VERSION, Columns.IMAGE_PATH,
            Columns.IMAGE_URI, Columns.IMAGE_STATUS
    })
    public @interface Columns {
        String IMAGE_ID = "image_id";
        String IMAGE_NAME = "image_name";
        String IMAGE_VERSION = "image_version";
        String IMAGE_PATH = "image_path";
        String IMAGE_URI = "image_uri";
        String IMAGE_STATUS = "image_status";
    }

    @IntDef({ Status.DATA, Status.NO_DATA })
    public @interface Status {
        int DATA = 1;
        int NO_DATA = 0;
    }

    @Column(name = Columns.IMAGE_ID)
    private String imageID;

    @Column(name = Columns.IMAGE_NAME)
    private String imageName;

    @Column(name = Columns.IMAGE_VERSION)
    private String imageVersion;

    @Column(name = Columns.IMAGE_PATH)
    private String imagePath;

    @Column(name = Columns.IMAGE_URI)
    private String imageUri;

    @Status
    @Column(name = Columns.IMAGE_STATUS)
    private int imageStatus;

    public ImageDataBase() {
        super();
    }

    public ImageDataBase(String imageID, String imageName, String imageVersion, String imagePath,
            String imageUri,
            int imageStatus) {
        super();
        this.imageID = imageID;
        this.imageName = imageName;
        this.imageVersion = imageVersion;
        this.imagePath = imagePath;
        this.imageUri = imageUri;
        this.imageStatus = imageStatus;
    }

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

    public void setImageStatus(@Status int imageStatus) {
        this.imageStatus = imageStatus;
    }
}
