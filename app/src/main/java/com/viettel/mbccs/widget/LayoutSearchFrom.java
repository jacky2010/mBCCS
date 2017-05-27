package com.viettel.mbccs.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.viettel.mbccs.R;
import com.viettel.mbccs.databinding.LayoutSearchFromBinding;

public class LayoutSearchFrom extends LinearLayout {

    public ObservableField<String> filterText;
    public ObservableField<Boolean> isCollapse;
    private LinearLayout mLinearLayout;

    public LayoutSearchFrom(Context context) {
        super(context);
        init();
    }

    public LayoutSearchFrom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LayoutSearchFrom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    private void init() {
        LayoutSearchFromBinding fromBinding =
                DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                        R.layout.layout_search_from, this, true);
        fromBinding.setData(this);

        filterText = new ObservableField<>();
        isCollapse = new ObservableField<>();
        isCollapse.set(false);
        mLinearLayout = fromBinding.layoutContainer;
        mLinearLayout.setOrientation(getOrientation());
    }

    @Override
    public void addView(View child) {
        super.addView(child);
    }

    public void toogleExpand() {
        isCollapse.set(!isCollapse.get());
    }

    public void setFilterText(String st) {
        filterText.set(st);
    }
}
