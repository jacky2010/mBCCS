package com.viettel.mbccs.utils;

import android.graphics.Bitmap;
import com.viettel.mbccs.data.model.database.UploadImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 7/7/17.
 */

public class DatabaseUtils {

    public static List<String> getBitmapAndSaveDatabase(List<String> imageName, Bitmap imageFront,
            Bitmap imageBackside, Bitmap imagePortrait) {
        List<String> uploadImageList = new ArrayList<>();
        if (imageFront != null) {
            UploadImage uploadImage =
                    setDataUploadImage(imageName.get(0), imageFront, UploadImage.ImageType.FRONT);
            uploadImage.save();
            uploadImageList.add(String.valueOf(uploadImage.getImageName()));
        }

        if (imageBackside != null) {
            UploadImage uploadImage =
                    setDataUploadImage(imageName.get(1), imageBackside, UploadImage.ImageType.BACKSIDE);
            uploadImage.save();
            uploadImageList.add(String.valueOf(uploadImage.getImageName()));
        }

        if (imagePortrait != null) {
            UploadImage uploadImage =
                    setDataUploadImage(imageName.get(2), imagePortrait, UploadImage.ImageType.PORTRAIT);
            uploadImage.save();
            uploadImageList.add(String.valueOf(uploadImage.getImageName()));
        }
        return uploadImageList;
    }

    private static UploadImage setDataUploadImage(String name, Bitmap bitmap,
            @UploadImage.ImageType int imageType) {
        String imageBase64 =
                ImageUtils.encodeBitmapToBase64(bitmap, Bitmap.CompressFormat.JPEG, 100);
        UploadImage uploadImage = new UploadImage();
        uploadImage.setIdImage(name);
        uploadImage.setContent(imageBase64);
        uploadImage.setStatus(UploadImage.StatusUpload.WAITING);
        return uploadImage;
    }
}
