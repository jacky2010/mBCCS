package com.viettel.mbccs.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.viettel.mbccs.utils.CircleTransform;
import com.viettel.mbccs.utils.StringUtils;
import java.io.IOException;

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

    //    private Bitmap bitmapImageFront;
    //    private Bitmap bitmapImageBackside;
    //    private Bitmap bitmapImagePortrait;

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
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (callback != null) {
            callback.onSelectImage(intent, TypeSelect.CAMERA);
        }
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        if (callback != null) {
            callback.onSelectImage(intent, TypeSelect.GALLERY);
        }
    }

    public void setResultIntent(Intent intent, @TypeSelect int type) {
        switch (type) {
            case TypeSelect.CAMERA:
                onCaptureImageResult(intent);
                break;
            case TypeSelect.GALLERY:
                onSelectFromGalleryResult(intent);
                break;
        }
    }

    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(context.getContentResolver(),
                        data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        setImageView(bm);
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        //        data.getData();
        //        try {
        //            Bitmap bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),
        //                    data.getData());
        //            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        //            bm.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        //            File destination = new File(Environment.getExternalStorageDirectory(),
        //                    System.currentTimeMillis() + ".jpg");
        //            FileOutputStream fo;
        //            destination.createNewFile();
        //            fo = new FileOutputStream(destination);
        //            fo.write(bytes.toByteArray());
        //            fo.close();
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
        setImageView(thumbnail);
    }

    private void setImageView(Bitmap bitmap) {
        switch (imageSelect) {
            case IMAGE_FRONT:
                //                bitmapImageFront = bitmap;
                imageFront.set(bitmap);
                checkFront = true;
                break;
            case IMAGE_BACKSIDE:
                //                bitmapImageBackside = bitmap;
                imageBackside.set(bitmap);
                break;
            case IMAGE_PORTRAIT:
                //                bitmapImagePortrait = bitmap;
                imagePortrait.set(bitmap);
                break;
        }
    }

    public void setUrlImageFront(String url) {
        if (checkFront) return;
        if (StringUtils.isEmpty(url)) {
            imageFront.set((BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.ic_select_image)));
            return;
        }
        Glide.with(context)
                .load(url)
                .centerCrop()
                .bitmapTransform(new CircleTransform(context))
                .into(binding.imageBackside);
    }

    public void setUrlImageBackside(String url) {
        if (checkBackside) return;
        if (StringUtils.isEmpty(url)) {
            imageBackside.set((BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.ic_select_image)));
            return;
        }
        Glide.with(context)
                .load(url)
                .centerCrop()
                .bitmapTransform(new CircleTransform(context))
                .into(binding.imageFront);
    }

    public void setUrlImagePortrait(String url) {
        if (checkPortrait) return;
        if (StringUtils.isEmpty(url)) {
            imagePortrait.set((BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.ic_select_image)));
            return;
        }
        Glide.with(context)
                .load(url)
                .centerCrop()
                .bitmapTransform(new CircleTransform(context))
                .into(binding.imagePortrait);
    }

    public void setImageFront(Bitmap bitmap) {
        if (bitmap == null) {
            //            imageFront.set(BitmapFactory.decodeResource(context.getResources(),
            //                    R.drawable.ic_select_image));
            checkFront = false;
            return;
        }
        //        bitmapImageFront = bitmap;
        imageFront.set(bitmap);
        checkFront = true;
    }

    public void setImageBackside(Bitmap bitmap) {
        if (bitmap == null) {
            //            imageBackside.set(BitmapFactory.decodeResource(context.getResources(),
            //                    R.drawable.ic_select_image));
            checkBackside = false;
            return;
        }
        //        bitmapImageBackside = bitmap;
        imageBackside.set(bitmap);
        checkBackside = true;
    }

    public void setImagePortrait(Bitmap bitmap) {
        if (bitmap == null) {
            //            imagePortrait.set(BitmapFactory.decodeResource(context.getResources(),
            //                    R.drawable.ic_select_image));
            checkPortrait = false;
            return;
        }
        //        bitmapImagePortrait = bitmap;
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
        imageFront.get().recycle();
        imageBackside.get().recycle();
        imagePortrait.get().recycle();

        imageFront.set(null);
        imageBackside.set(null);
        imagePortrait.set(null);
    }

    public interface SelectImageCallback {
        void onSelectImage(Intent intent, int type);
    }

    public void setSelectImageCallback(SelectImageCallback callback) {
        this.callback = callback;
    }
}
