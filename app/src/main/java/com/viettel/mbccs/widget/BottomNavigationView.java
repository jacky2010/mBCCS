package com.viettel.mbccs.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.MenuItem;
import com.viettel.mbccs.databinding.ItemImageGridBinding;

import java.util.List;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

/**
 * Created by FRAMGIA\vu.viet.anh on 15/05/2017.
 */

public class BottomNavigationView extends LinearLayout {

    @ColorRes
    private int mTextColor;

    private float mTextSize;

    private List<MenuItem> mListItem;

    private OnBottomItemClick mOnBottomItemClick;

    public BottomNavigationView(Context context) {
        this(context, null);
    }

    public BottomNavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr,
                                int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView(Context context, AttributeSet attrs) {
        setOrientation(HORIZONTAL);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        int padding = (int) TypedValue.applyDimension(COMPLEX_UNIT_DIP,
                getResources().getDimension(R.dimen.dp_4), getResources().getDisplayMetrics());
        int paddingTop = (int) TypedValue.applyDimension(COMPLEX_UNIT_DIP,
                getResources().getDimension(R.dimen.dp_8), getResources().getDisplayMetrics());
        setPadding(padding, paddingTop, padding, padding);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BottomNavigationView);
        try {
            setBackgroundColor(
                    a.getColor(R.styleable.BottomNavigationView_bottomBarBgColor, Color.BLACK));
            mTextColor = a.getResourceId(R.styleable.BottomNavigationView_bottomBarTextColor,
                    android.R.color.white);
            mTextSize = a.getDimension(R.styleable.BottomNavigationView_bottomBarTextSize,
                    getResources().getDimension(R.dimen.sp_14));
        } finally {
            a.recycle();
        }
    }

    public int getTextColor() {
        return mTextColor;
    }

    public float getTextSize() {
        return mTextSize;
    }

    public void setOnBottomItemClick(OnBottomItemClick onBottomItemClick) {
        mOnBottomItemClick = onBottomItemClick;
    }

    public void setSelection(int index) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setSelected(false);
        }
        getChildAt(index).setSelected(true);
    }

    public interface OnBottomItemClick {
        void onBottomItemClick(int position);
    }

    public void setBottomListItem(List<MenuItem> listItem) {
        if (listItem.isEmpty()) return;

        mListItem = listItem;
        for (int i = 0; i < mListItem.size(); i++) {
            MenuItem item = mListItem.get(i);
            ItemImageGridBinding binding =
                    DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                            R.layout.item_image_grid, this, true);
            binding.setImageText(item.getTitle());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                binding.setImage(getResources().getDrawable(item.getIcon(), null));
            } else {
                binding.setImage(getResources().getDrawable(item.getIcon()));
            }
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.text.setTextColor(getResources().getColorStateList(mTextColor, null));
                } else {
                    binding.text.setTextColor(getResources().getColorStateList(mTextColor));
                }
            } catch (Resources.NotFoundException e) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.text.setTextColor(getResources().getColor(mTextColor, null));
                } else {
                    binding.text.setTextColor(getResources().getColor(mTextColor));
                }
            }
            binding.setTextSize(mTextSize);
            final int position = i;
            binding.setOnClicked(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelection(position);
                    if (mOnBottomItemClick != null) {
                        mOnBottomItemClick.onBottomItemClick(position);
                    }
                }
            });
            View v = binding.getRoot();
            LayoutParams lp = (LayoutParams) v.getLayoutParams();
            lp.weight = 1;
        }
        setSelection(0);
    }
}
