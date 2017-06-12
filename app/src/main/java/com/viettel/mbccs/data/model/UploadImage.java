package com.viettel.mbccs.data.model;

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

    @Column(name = "id_image")
    private long idImage;

    @Column(name = "object_id")
    private String objectId;

    @Column(name = "action_business")
    private String actionBusiness;

    @ImageType
    @Column(name = "order_id")
    private int order;

    @Column(name = "doc_type")
    private int docType;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "image_data")
    private String imageData;

    @Column(name = "trans_date")
    private String transDate;

    @StatusUpload
    @Column(name = "status")
    private String status;

    public UploadImage() {
        super();
    }

    protected UploadImage(Parcel in) {
        idImage = in.readLong();
        objectId = in.readString();
        actionBusiness = in.readString();
        order = in.readInt();
        docType = in.readInt();
        fileName = in.readString();
        imageData = in.readString();
        transDate = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idImage);
        dest.writeString(objectId);
        dest.writeString(actionBusiness);
        dest.writeInt(order);
        dest.writeInt(docType);
        dest.writeString(fileName);
        dest.writeString(imageData);
        dest.writeString(transDate);
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

    public long getIdImage() {
        return idImage;
    }

    public void setIdImage(long idImage) {
        this.idImage = idImage;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getActionBusiness() {
        return actionBusiness;
    }

    public void setActionBusiness(String actionBusiness) {
        this.actionBusiness = actionBusiness;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getDocType() {
        return docType;
    }

    public void setDocType(int docType) {
        this.docType = docType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(@StatusUpload String status) {
        this.status = status;
    }
}
