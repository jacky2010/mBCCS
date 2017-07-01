package com.viettel.mbccs.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;
import com.viettel.mbccs.R;
import com.viettel.mbccs.databinding.LayoutSelectImageNoBinding;
import com.viettel.mbccs.utils.StringUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by HuyQuyet on 6/6/17.
 */

@BindingMethods({
        @BindingMethod(type = CustomSelectImageNo.class, attribute = "urlImageFront", method = "setUrlImageFront"),
        @BindingMethod(type = CustomSelectAddress.class, attribute = "urlImageBackside", method = "setUrlImageBackside"),
        @BindingMethod(type = CustomSelectAddress.class, attribute = "urlImagePortrait", method = "setUrlImagePortrait"),
        @BindingMethod(type = CustomSelectAddress.class, attribute = "imageFront", method = "setImageFront"),
        @BindingMethod(type = CustomSelectAddress.class, attribute = "imageBackside", method = "setImageBackside"),
        @BindingMethod(type = CustomSelectAddress.class, attribute = "imagePortrait", method = "setImagePortrait")
})
public class CustomSelectImageNo extends LinearLayout {

    @IntDef({ TypeSelect.CAMERA, TypeSelect.GALLERY })
    public @interface TypeSelect {
        int CAMERA = 1;
        int GALLERY = 2;
    }

    private static final int IMAGE_FRONT = 1;
    private static final int IMAGE_BACKSIDE = 2;
    private static final int IMAGE_PORTRAIT = 3;

    private Context context;
    private LayoutSelectImageNoBinding binding;
    private SelectImageCallback callback;
    private int imageSelect;
    private boolean checkFront = false;
    private boolean checkBackside = false;
    private boolean checkPortrait = false;

    public ObservableField<Bitmap> imageFront;
    public ObservableField<Bitmap> imageBackside;
    public ObservableField<Bitmap> imagePortrait;

    private Uri imageFileUri;
    private String imageFilePath;
    private String imageName;

    public CustomSelectImageNo(Context context) {
        this(context, null);
    }

    public CustomSelectImageNo(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSelectImageNo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        binding = LayoutSelectImageNoBinding.inflate(LayoutInflater.from(context), this, true);
        binding.setPresenter(this);
        imageFront = new ObservableField<>();
        imageBackside = new ObservableField<>();
        imagePortrait = new ObservableField<>();
    }

    public void onSelectImage(View v) {
        switch (v.getId()) {
            case R.id.layout_image_front:
                imageSelect = IMAGE_FRONT;
                break;
            case R.id.layout_image_backside:
                imageSelect = IMAGE_BACKSIDE;
                break;
            case R.id.layout_image_portrait:
                imageSelect = IMAGE_PORTRAIT;
                break;
        }

        final CharSequence[] items = {
                context.getString(R.string.picker_photo_take_photo),
                context.getString(R.string.picker_photo_chooose_library),
                context.getString(R.string.picker_photo_cancel)
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.picker_photo_add_photo));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(context.getString(R.string.picker_photo_take_photo))) {
                    cameraIntent();
                } else if (items[item].equals(
                        context.getString(R.string.picker_photo_chooose_library))) {
                    galleryIntent();
                } else if (items[item].equals(context.getString(R.string.picker_photo_cancel))) {
                    dialog.dismiss();
                }
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void cameraIntent() {
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (captureIntent.resolveActivity(context.getPackageManager()) != null) {
            try {
                // Create an image file name
                String timeStamp =
                        new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
                File storageDir =
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                File image = File.createTempFile("JPEG_" + timeStamp, ".jpg", storageDir);
                imageName = "JPEG_" + timeStamp;

                imageFilePath = image.getAbsolutePath();
                imageFileUri = Uri.fromFile(image);
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (callback != null) {
                callback.onSelectImage(captureIntent, TypeSelect.CAMERA);
            }
        }
    }

    private void galleryIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (callback != null) {
            callback.onSelectImage(intent, TypeSelect.GALLERY);
        }
    }

    public void setResultIntent(Intent intent, @TypeSelect int type) {
        onSelectImageResult(intent);
    }

    private void onSelectImageResult(Intent intent) {
        Uri selectedImage = null;
        Bitmap thumbnail;
        ExifInterface ei = null;
        String picturePath;

        if (intent == null || intent.getData() == null) {
            selectedImage = imageFileUri;
            picturePath = imageFilePath;
        } else {
            selectedImage = intent.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor c =
                    context.getContentResolver().query(selectedImage, filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            picturePath = c.getString(columnIndex);
            c.close();
        }

        thumbnail = decodeBitmapFromPathFile(picturePath, 200, 200);
        try {
            ei = new ExifInterface(picturePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);

        switch (orientation) {

            case ExifInterface.ORIENTATION_ROTATE_90:
                setImageView(rotateImage(thumbnail, 90), selectedImage);
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                setImageView(rotateImage(thumbnail, 180), selectedImage);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                setImageView(rotateImage(thumbnail, 270), selectedImage);
                break;

            case ExifInterface.ORIENTATION_NORMAL:

            default:
                setImageView(thumbnail, selectedImage);
                break;
        }
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Bitmap result =
                Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix,
                        true);
        source.recycle();
        return result;
    }

    private String path(Uri selectedImage) {
        String picturePath;
        String[] filePath = { MediaStore.Images.Media.DATA };
        Cursor c = context.getContentResolver().query(selectedImage, filePath, null, null, null);
        c.moveToFirst();
        int columnIndex = c.getColumnIndex(filePath[0]);
        picturePath = c.getString(columnIndex);
        c.close();
        return picturePath;
    }

    private void setImageView(Bitmap thumbnail, Uri selectedImage) {
        switch (imageSelect) {
            case IMAGE_FRONT:
                imageFront.set(thumbnail);
                break;
            case IMAGE_BACKSIDE:
                imageBackside.set(thumbnail);
                break;
            case IMAGE_PORTRAIT:
                imagePortrait.set(thumbnail);
                break;
        }
    }

    public void setUrlImageFront(String url) {
        if (checkFront) return;
        if (StringUtils.isEmpty(url)) {
            Glide.with(context)
                    .load(R.drawable.ic_camera_placeholder)
                    .into(binding.imageFront);
            return;
        }
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.ic_camera_placeholder)
                .into(binding.imageBackside);
    }

    public void setUrlImageBackside(String url) {
        if (checkBackside) return;
        if (StringUtils.isEmpty(url)) {
            Glide.with(context)
                    .load(R.drawable.ic_camera_placeholder)
                    .into(binding.imageBackside);
            return;
        }
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.ic_camera_placeholder)
                .into(binding.imageFront);
    }

    public void setUrlImagePortrait(String url) {
        if (checkPortrait) return;
        if (StringUtils.isEmpty(url)) {
            Glide.with(context)
                    .load(R.drawable.ic_camera_placeholder)
                    .into(binding.imagePortrait);
            return;
        }
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.ic_camera_placeholder)
                .into(binding.imagePortrait);
    }

    public void setImageFront(Bitmap bitmap) {
        if (bitmap == null) {
            checkFront = false;
            return;
        }
        imageFront.set(bitmap);
        checkFront = true;
    }

    public void setImageBackside(Bitmap bitmap) {
        if (bitmap == null) {
            checkBackside = false;
            return;
        }
        imageBackside.set(bitmap);
        checkBackside = true;
    }

    public void setImagePortrait(Bitmap bitmap) {
        if (bitmap == null) {
            checkPortrait = false;
            return;
        }
        imagePortrait.set(bitmap);
        checkPortrait = true;
    }

    public Bitmap getBitmapImageFront() {
        return imageFront.get();
    }

    public Bitmap getBitmapImageBackside() {
        if (imageBackside.get() == null) return null;
        return imageBackside.get();
    }

    public Bitmap getBitmapImagePortrait() {
        if (imagePortrait.get() == null) return null;
        return imagePortrait.get();
    }

    public void recycleBitmap() {
        if (imageFront.get() != null) {
            imageFront.get().recycle();
        }

        if (imageBackside.get() != null) {
            imageBackside.get().recycle();
        }

        if (imagePortrait.get() != null) {
            imagePortrait.get().recycle();
        }

        imageFront.set(null);
        imageBackside.set(null);
        imagePortrait.set(null);
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public Bitmap decodeBitmapFromPathFile(String pathName, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(pathName, options);
    }

    public interface SelectImageCallback {
        void onSelectImage(Intent intent, int type);
    }

    public void setSelectImageCallback(SelectImageCallback callback) {
        this.callback = callback;
    }

}
