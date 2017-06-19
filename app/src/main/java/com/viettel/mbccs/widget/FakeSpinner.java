package com.viettel.mbccs.widget;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.viettel.mbccs.R;
import com.viettel.mbccs.databinding.ItemFakeSpinnerBinding;

/**
 * Created by eo_cuong on 6/7/17.
 */

public class FakeSpinner extends LinearLayout {

    private CustomEdittext mEdittext;

    @BindingAdapter("android:text")
    public static void setText(FakeSpinner fakeSpinner, CharSequence s) {
        fakeSpinner.getEdittext().setText(s);
    }

    @BindingAdapter("hint")
    public static void setHint(FakeSpinner fakeSpinner, CharSequence s) {
        fakeSpinner.getEdittext().setHint(s);
    }

    @BindingAdapter("android:onClick")
    public static void setOnClick(FakeSpinner fakeSpinner, View.OnClickListener onClick) {
        fakeSpinner.getEdittext().setOnClickListener(onClick);
    }

    public CustomEdittext getEdittext() {
        return mEdittext;
    }

    public void setEdittext(CustomEdittext edittext) {
        mEdittext = edittext;
    }


    public FakeSpinner(Context context) {
        super(context);
        initView();
    }

    public FakeSpinner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public FakeSpinner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        ItemFakeSpinnerBinding item = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.item_fake_spinner, this, true);
        mEdittext = item.text;
    }
}
