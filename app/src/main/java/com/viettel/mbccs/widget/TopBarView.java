package com.viettel.mbccs.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viettel.mbccs.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jacky on 5/22/17.
 */

public class TopBarView extends RelativeLayout {

    private Context mContext;
    private Unbinder mBinder;

    @BindView(R.id.tv_title)
    TextView mText;
    @BindView(R.id.iv_icon)
    ImageView mIcon;

    public TopBarView(Context context) {
        super(context);
        init(context, null);
    }

    public TopBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TopBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.top_bar_view, this);
        mBinder = ButterKnife.bind(this);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBarView);
        initTextView(mTypedArray);
        initIcon(mTypedArray, R.styleable.TopBarView_tbvIconRightBg,
                mTypedArray.getBoolean(R.styleable.TopBarView_tbvIconRight, false),
                mIcon);
    }

    private void initIcon(TypedArray typedArray, int styleable, boolean isActive, ImageView iconView) {
        Drawable icon = typedArray.getDrawable(styleable);
        if (icon != null) {
            iconView.setImageDrawable(icon);
        }
        iconView.setVisibility(icon == null || !isActive ? GONE : VISIBLE);
    }

    private void initTextView(TypedArray mTypedArray) {
        String title = mTypedArray.getString(R.styleable.TopBarView_tbvTitle);
        boolean isActive = mTypedArray.getBoolean(R.styleable.TopBarView_tbvTextView, false);
        mText.setVisibility(TextUtils.isEmpty(title) || !isActive ? GONE : VISIBLE);
        mText.setVisibility(TextUtils.isEmpty(title) ? GONE : VISIBLE);
        if (!TextUtils.isEmpty(title)) {
            int size = mTypedArray.getDimensionPixelSize(R.styleable.TopBarView_tbvTitleSize,
                    getResources().getDimensionPixelSize(R.dimen.default_item_top_bar_text_size));
            int color = mTypedArray.getColor(R.styleable.TopBarView_tbvTitleColor,
                    getResources().getColor(R.color.default_item_top_bar_text_color));
            mText.setText(title);
            mText.setTextSize(px2dip(size));
            mText.setTextColor(color);
        }
    }

    private int px2dip(float pxValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public void setBgTextView(int id) {
        if (id != 0 && mText != null) {
            mText.setBackgroundResource(id);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mBinder != null) {
            mBinder.unbind();
        }
        super.onDetachedFromWindow();
    }
}
