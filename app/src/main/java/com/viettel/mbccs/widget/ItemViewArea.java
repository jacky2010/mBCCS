package com.viettel.mbccs.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jacky on 6/5/17.
 */

public class ItemViewArea extends LinearLayout {

    private Context mContext;
    private Unbinder mBinder;

    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.item_area)
    LinearLayout mItem;

    public ItemViewArea(Context context) {
        super(context);
        init(context, null);
    }

    public ItemViewArea(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public ItemViewArea(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_view_area, this);
        mBinder = ButterKnife.bind(this);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemViewArea);
        initTitle(mTypedArray, mTitle);
    }


    public void setTitle(int idTitle) {
        setTitle(getContext().getString(idTitle));
    }

    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mTitle.setText(title);
        }
        mTitle.setVisibility(TextUtils.isEmpty(title) ? GONE : VISIBLE);
    }

    private void initTitle(TypedArray typedArray, TextView mTitle) {
        String title = typedArray.getString(R.styleable.ItemViewArea_ivaTitle);
        int size = typedArray.getDimensionPixelSize(R.styleable.ItemViewArea_ivaTitleSize,
                getResources().getDimensionPixelSize(R.dimen.default_toolbar_text_size));
        int color = typedArray.getColor(R.styleable.ItemViewArea_ivaTitleColor,
                getResources().getColor(R.color.black));
        if (TextUtils.isEmpty(title)) {
            mTitle.setVisibility(GONE);
        } else {
            mTitle.setText(title);
            mTitle.setTextSize(ScreenUtils.px2dip(mContext, size));
        }
        mTitle.setTextColor(color);
    }

    public void setBackgoundItem(int idBackgound){
        mItem.setBackgroundResource(idBackgound);
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mBinder != null) {
            mBinder.unbind();
        }
        super.onDetachedFromWindow();
    }

}
