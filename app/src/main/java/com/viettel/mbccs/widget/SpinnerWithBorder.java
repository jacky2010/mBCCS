package com.viettel.mbccs.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.databinding.LayoutSpinnerWithBorderBinding;

/**
 * Created by FRAMGIA\vu.viet.anh on 07/12/2016.
 */

public class SpinnerWithBorder extends FrameLayout {

    private AppCompatSpinner mSpinner;
    private ImageView mImageView;

    public SpinnerWithBorder(Context context) {
        this(context, null);
    }

    public SpinnerWithBorder(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpinnerWithBorder(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SpinnerWithBorder(Context context, AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @BindingAdapter(value = { "adapter", "position", "leftIcon" }, requireAll = false)
    public static void bindData(SpinnerWithBorder spinnerWithBorder, BaseAdapter adapter,
            int position, Drawable drawable) {
        spinnerWithBorder.getSpinner().setAdapter(adapter);
        spinnerWithBorder.getSpinner().setSelection(position);
        if (drawable != null) {
            spinnerWithBorder.getImageView().setVisibility(View.VISIBLE);
            spinnerWithBorder.getImageView().setImageDrawable(drawable);
        } else {
            spinnerWithBorder.getImageView().setVisibility(View.GONE);
        }
    }

    @BindingAdapter(value = {
            "selectedPosition", "selectedValueAttrChanged"
    }, requireAll = false)
    public static void bindOnChange(SpinnerWithBorder spinnerWithBorder, int seletedPosition,
            final InverseBindingListener listener) {
        spinnerWithBorder.getSpinner()
                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        listener.onChange();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        spinnerWithBorder.getSpinner().setSelection(seletedPosition);
    }

    public void setOnItemSelectedListener(
            AdapterView.OnItemSelectedListener onItemSelectedListener) {
        getSpinner().setOnItemSelectedListener(onItemSelectedListener);
    }

    @InverseBindingAdapter(attribute = "selectedPosition", event = "selectedValueAttrChanged")
    public static int captureSelectedPosition(SpinnerWithBorder spinnerWithBorder) {
        return spinnerWithBorder.getSpinner().getSelectedItemPosition();
    }

    protected void initView(AttributeSet attributeSet) {
        ((LayoutSpinnerWithBorderBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.layout_spinner_with_border, this, true)).setInput(this);

        mSpinner = (AppCompatSpinner) findViewById(R.id.spinner_border);
        mImageView = (ImageView) findViewById(R.id.image_left);

        TypedArray typedArray =
                getContext().obtainStyledAttributes(attributeSet, R.styleable.SpinnerWithBorder);

        try {
            ColorStateList color =
                    typedArray.getColorStateList(R.styleable.SpinnerWithBorder_spinnerTintColor);
            if (color == null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    color = ColorStateList.valueOf(
                            getResources().getColor(R.color.grey_bright, null));
                } else {
                    color = ColorStateList.valueOf(getResources().getColor(R.color.grey_bright));
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mSpinner.setBackgroundTintList(color);
            } else {
                ViewCompat.setBackgroundTintList(mSpinner, color);
            }
        } finally {
            typedArray.recycle();
        }
    }

    public AppCompatSpinner getSpinner() {
        return mSpinner;
    }

    public void setAdapter(ArrayAdapter adapter) {
        mSpinner.setAdapter(adapter);
    }

    public void setSelectedPosition(int position) {
        mSpinner.setSelection(position);
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public static class HintAdapter<T> extends ArrayAdapter<T> {

        public HintAdapter(Context context, int resource, int textViewResourceId, T[] objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View v = super.getDropDownView(position, convertView, parent);
            v.setVisibility(position == super.getCount() - 1 ? View.GONE : View.VISIBLE);
            return v;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = super.getView(position, convertView, parent);
            try {
                if (position == super.getCount() - 1) {
                    ((TextView) v).setTextColor(Color.GRAY);
                } else {
                    ((TextView) v).setTextColor(Color.BLACK);
                }
            } catch (Exception ignored) {
            }
            return v;
        }

        @Override
        public int getCount() {
            int count = super.getCount();
            return count > 0 ? count - 1 : count;
        }
    }
}
