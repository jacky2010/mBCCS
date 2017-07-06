package com.viettel.mbccs.utils;

import android.graphics.Bitmap;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.model.UploadImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 7/7/17.
 */

public class DatabaseUtils {

    public static List<String> getBitmapAndSaveDatabase(Customer customer, Bitmap imageFront,
            Bitmap imageBackside, Bitmap imagePortrait) {
        List<String> uploadImageList = new ArrayList<>();
        if (imageFront != null) {
            UploadImage uploadImage =
                    setDataUploadImage(customer, imageFront, UploadImage.ImageType.FRONT);
            uploadImage.save();
            uploadImageList.add(String.valueOf(uploadImage.getIdImage()));
        }

        if (imageBackside != null) {
            UploadImage uploadImage =
                    setDataUploadImage(customer, imageBackside, UploadImage.ImageType.BACKSIDE);
            uploadImage.save();
            uploadImageList.add(String.valueOf(uploadImage.getIdImage()));
        }

        if (imagePortrait != null) {
            UploadImage uploadImage =
                    setDataUploadImage(customer, imagePortrait, UploadImage.ImageType.PORTRAIT);
            uploadImage.save();
            uploadImageList.add(String.valueOf(uploadImage.getIdImage()));
        }
        return uploadImageList;
    }

    private static UploadImage setDataUploadImage(Customer customer, Bitmap bitmap,
            @UploadImage.ImageType int imageType) {
        String imageBase64 =
                ImageUtils.encodeBitmapToBase64(bitmap, Bitmap.CompressFormat.JPEG, 100);
        UploadImage uploadImage = new UploadImage();
        uploadImage.setIdImage(System.currentTimeMillis());
        uploadImage.setActionBusiness("");
        uploadImage.setObjectId(customer.getCustomerId());
        uploadImage.setOrder(imageType);
        uploadImage.setDocType(1);
        uploadImage.setFileName(String.valueOf(System.currentTimeMillis()));
        uploadImage.setImageData(imageBase64);
        uploadImage.setStatus(UploadImage.StatusUpload.WAITING);
        return uploadImage;
    }
}
