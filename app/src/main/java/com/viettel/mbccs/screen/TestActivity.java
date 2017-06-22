package com.viettel.mbccs.screen;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.BindView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseActivity;
import com.viettel.mbccs.constance.IconType;
import com.viettel.mbccs.utils.FileUtils;
import com.viettel.mbccs.utils.ImageCompressor;
import com.viettel.mbccs.utils.ImageUtils;
import com.viettel.mbccs.widget.CustomTextView;
import com.viettel.mbccs.widget.MultiDirectionSlidingDrawer;
import com.viettel.mbccs.widget.ToolBarView;
import java.io.File;
import java.io.IOException;
import rx.Subscriber;

/**
 * Created by jacky on 6/11/17.
 */

public class TestActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    ToolBarView mToolBar;

    @BindView(R.id.drawer)
    MultiDirectionSlidingDrawer mWrappingSlidingDrawer;

    @BindView(R.id.text)
    CustomTextView text;

    @BindView(R.id.image)
    ImageView mImage;

    private static final int PICK_IMAGE_REQUEST = 1;
    private File actualImage;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initData() {
        initDataBinder();
        mToolBar.setOnClickIconListener(new ToolBarView.OnClickIconListener() {
            @Override
            public void onClickIconLeft(int mIconType) {
                switch (mIconType) {
                    case IconType.LEFT:
                        finish();
                        break;
                    case IconType.RIGHT:
                        break;
                }
            }
        });

        mWrappingSlidingDrawer.setOnDrawerCloseListener(
                new MultiDirectionSlidingDrawer.OnDrawerCloseListener() {
                    @Override
                    public void onDrawerClosed() {
                    }
                });

        mWrappingSlidingDrawer.setOnDrawerOpenListener(
                new MultiDirectionSlidingDrawer.OnDrawerOpenListener() {
                    @Override
                    public void onDrawerOpened() {
                    }
                });

        mWrappingSlidingDrawer.initContent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data == null) {
                //showError("Failed to open picture!");
                return;
            }
            try {
                actualImage = FileUtils.from(this, data.getData());
                new ImageCompressor(this).compressFileToBase64AsObservable(actualImage)
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(String s) {
                                text.setText(s);
                                //  mImage.setImageBitmap(ImageUtils.decodeBase64ToBitmap(s));

                                //Demo save base64
                                try {
                                    File file =
                                            ImageUtils.saveBase64ToFile(getApplicationContext(), s,
                                                    "1101");
                                    Toast.makeText(TestActivity.this,
                                            "file save in " + file.getAbsolutePath(),
                                            Toast.LENGTH_SHORT).show();

                                    mImage.setImageBitmap(
                                            BitmapFactory.decodeFile(file.getAbsolutePath()));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                //new ImageCompressor(this).compressToFileAsObservable(actualImage)
                //        .subscribe(new Subscriber<File>() {
                //            @Override
                //            public void onCompleted() {
                //
                //            }
                //
                //            @Override
                //            public void onError(Throwable e) {
                //
                //            }
                //
                //            @Override
                //            public void onNext(File s) {
                //                text.setText(s.getAbsolutePath());
                //                mImage.setImageBitmap(
                //                        BitmapFactory.decodeFile(s.getAbsolutePath()));
                //            }
                //        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void chooImage(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void setTitleToolbar(int idTitle) {
        super.setTitleToolbar(idTitle);
        mToolBar.setTitle(idTitle);
    }
}
