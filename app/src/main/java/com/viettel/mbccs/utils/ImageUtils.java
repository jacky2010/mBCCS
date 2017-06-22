package com.viettel.mbccs.utils;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by FRAMGIA\bui.dinh.viet on 07/03/2017.
 */

public class ImageUtils {

    public static Bitmap drawBitmapCircle(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
        Bitmap result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP,
                BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        return result;
    }

    public static String getRealPathUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static String encodeBitmapToBase64(Bitmap image, Bitmap.CompressFormat compressFormat,
            int quality) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

    public static String encodeBitmapToBase64(Bitmap image) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

    public static String encodeFileToBase64(File file) throws IOException {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byteArrayOS.writeTo(fileOutputStream);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

    public static Bitmap decodeBase64ToBitmap(String input) {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public static Drawable bitmapToDrawable(Resources resources, Bitmap b) {
        return new BitmapDrawable(resources, b);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1,
                    Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap =
                    Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                            Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /***
     * Compress Image with param
     * @param imageFile
     * @param reqWidth
     * @param reqHeight
     * @param compressFormat
     * @param quality
     * @param destinationPath
     * @return
     * @throws IOException
     */
    static File compressImage(File imageFile, int reqWidth, int reqHeight,
            Bitmap.CompressFormat compressFormat, int quality, String destinationPath)
            throws IOException {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(destinationPath);
            // write the compressed bitmap at the destination specified by destinationPath.
            decodeSampledBitmapFromFile(imageFile, reqWidth, reqHeight).compress(compressFormat,
                    quality, fileOutputStream);
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }

        return new File(destinationPath);
    }

    static Bitmap decodeSampledBitmapFromFile(File imageFile, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth,
            int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;

        int stretch_width = Math.round((float) width / (float) reqWidth);
        int stretch_height = Math.round((float) height / (float) reqHeight);

        if (stretch_width <= stretch_height) {
            return stretch_height;
        } else {
            return stretch_width;
        }
    }

    public static File saveBase64ToFile(Context context, String base64, File fileOut)
            throws IOException {
        Bitmap bitmap = decodeBase64ToBitmap(base64);
        FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return fileOut;
    }

    /***
     * save base64 to file with id
     * @param context
     * @param base64
     * @param id
     * @return
     */
    public static File saveBase64ToFile(Context context, String base64, String  id)
            throws IOException {
        File file = FileUtils.getImageFileByIdName(context, id);
        Bitmap bitmap = decodeBase64ToBitmap(base64);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.PNG, 75, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return file;
    }
}
