package com.viettel.mbccs.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.IconType;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by jacky on 5/17/17.
 */

public class ToolBarView extends RelativeLayout {

    private Context mContext;
    private Unbinder mBinder;

    @BindView(R.id.image_left) ImageView mIconLeft;
    @BindView(R.id.image_right) ImageView mIconRight;
    @BindView(R.id.title) TextView mTitle;

    private OnClickIconListener mOnClickIconListener;

    public ToolBarView(Context context) {
        super(context);
        init(context, null);
    }

    public ToolBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ToolBarView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        inflater.inflate(R.layout.custom_toolbar_app, this);
        mBinder = ButterKnife.bind(this);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.ToolbarView);

        initTitle(mTypedArray, mTitle);
        initIcon(mTypedArray, R.styleable.ToolbarView_tbIconLeft, mIconLeft);
        initIcon(mTypedArray, R.styleable.ToolbarView_tbIconRight, mIconRight);
    }

    private void initTitle(TypedArray typedArray, TextView mTitle) {
        String title = typedArray.getString(R.styleable.ToolbarView_tbTitle);
        int size = typedArray.getDimensionPixelSize(R.styleable.ToolbarView_ibSizeTitle,
                getResources().getDimensionPixelSize(R.dimen.default_toolbar_text_size));
        int color = typedArray.getColor(R.styleable.ToolbarView_tbColorTitle,
                getResources().getColor(R.color.default_item_tool_bar_text_color));
        if (TextUtils.isEmpty(title)) {
            mTitle.setVisibility(GONE);
        } else {
            mTitle.setText(title);
            mTitle.setTextSize(px2dip(size));
        }
        mTitle.setTextColor(color);
    }

    private int px2dip(float pxValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    private void initIcon(TypedArray typedArray, int styleable, ImageView iconView) {
        Drawable icon = typedArray.getDrawable(styleable);
        if (icon != null) {
            iconView.setImageDrawable(icon);
        } else {
            iconView.setVisibility(GONE);
        }
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

    public void setIconLeft(int icon) {
        if (icon > 0) {
            mIconLeft.setBackgroundResource(icon);
        }
    }

    public void setIconRight(int icon) {
        if (icon > 0) {
            mIconRight.setBackgroundResource(icon);
        }
    }

    @OnClick({R.id.image_right, R.id.image_left})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_left:
                if (mOnClickIconListener != null) {
                    mOnClickIconListener.onClickIconLeft(IconType.LEFT);
                }
                break;
            case R.id.image_right:
                if (mOnClickIconListener != null) {
                    mOnClickIconListener.onClickIconLeft(IconType.RIGHT);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mBinder != null) {
            mBinder.unbind();
        }
        super.onDetachedFromWindow();
    }

    public void setOnClickIconListener(OnClickIconListener mOnClickIconListener) {
        this.mOnClickIconListener = mOnClickIconListener;
    }

    public interface OnClickIconListener {
        void onClickIconLeft(int mIconType);
    }
}
