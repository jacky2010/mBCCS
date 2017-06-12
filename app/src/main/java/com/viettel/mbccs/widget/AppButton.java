package com.viettel.mbccs.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ButtonTyper;
import com.viettel.mbccs.utils.ColorUtils;

@BindingMethods({
        @BindingMethod(type = AppButton.class, attribute = "enabled", method = "setEnabled")
})
public class AppButton extends AppCompatButton {
    private static final float TEXT_SELECTED_ALPHA = 0.5f;
    private static final float DEFAULT_TEXT_SIZE = 20;

    private GradientDrawable mGradientDrawable;
    private Drawable mBackgroundDrawable;
    private LayerDrawable mLayerDrawable;
    private String mType;
    // Background
    private int mBgColor;
    private int mBgColorSelected;
    private int mBgColorDisabled;
    // Text color
    private int mTextColor;
    private int mTextColorSelected;
    private int mTextColorDisabled;
    // Border
    private int mStrokeWidth;
    private int mStrokeColor;
    // Corner
    private int mCornerRadius;
    // TextSize
    private int mTextSize;
    // State
    private boolean mSelected = false;

    public AppButton(Context context) {
        super(context);
        init(null);
    }

    public AppButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public AppButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mGradientDrawable = new GradientDrawable();
        configDefault();

        TypedArray a =
                getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.AppButton, 0, 0);
        if (isConfigContainType(a)) {
            brandButtonWithType();
        }

        getConfigFromXML(a);

        setClickable(true);
        refresh();
    }

    private boolean isConfigContainType(TypedArray a) {
        mType = a.getString(R.styleable.AppButton_type);
        return mType != null;
    }

    private void configDefault() {
        mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE,
                getContext().getResources().getDisplayMetrics());
//        setBorderButton();
    }

    /**
     * Set default UI value for button base on types
     */
    private void brandButtonWithType() {
        switch (mType) {
            case ButtonTyper.DEFAULT:
                mBgColor = ContextCompat.getColor(getContext(), R.color.seafoam_blue);
                mTextColor = ContextCompat.getColor(getContext(), R.color.white);

                mTextColorSelected = ColorUtils.addAlpha(mTextColor, TEXT_SELECTED_ALPHA);
                mBgColorSelected = mBgColor;
                break;
            case ButtonTyper.GREEN:
                mBgColor = ContextCompat.getColor(getContext(), R.color.green);
                mTextColor = ContextCompat.getColor(getContext(), R.color.white);
                mTextColorSelected = ColorUtils.addAlpha(mTextColor, TEXT_SELECTED_ALPHA);
                mBgColorSelected = mBgColor;
                mTextColorDisabled = ContextCompat.getColor(getContext(), R.color.white);
                break;
            case ButtonTyper.RED:
                mBgColor = ContextCompat.getColor(getContext(), R.color.red_button);
                mTextColor = ContextCompat.getColor(getContext(), R.color.seafoam_blue);
                mTextColorSelected = ColorUtils.addAlpha(mTextColor, TEXT_SELECTED_ALPHA);
                mBgColorSelected = mBgColor;
                break;
            case ButtonTyper.WHITE:
                mBgColor = ContextCompat.getColor(getContext(), R.color.white);
                mTextColor = ContextCompat.getColor(getContext(), R.color.black);

                mTextColorSelected = ColorUtils.addAlpha(mTextColor, TEXT_SELECTED_ALPHA);
                mBgColorSelected = mBgColor;
                break;
            default:
                break;
        }
    }

    private void setBorderButton() {
        mStrokeWidth = (int) getContext().getResources().getDimension(R.dimen.dp_1);
        mStrokeColor = ContextCompat.getColor(getContext(), R.color.black);
    }

    private void getConfigFromXML(TypedArray a) {
        try {
            // Background color
            mBgColor = a.getColor(R.styleable.AppButton_bgColor, mBgColor);
            // Disable it beacause button in this app don 't change background color when press
            // mBgColorSelected = ColorUtils.addColor(mBgColor, 0.85f);
            // mBgColorDisabled = ColorUtils.addAlpha(mBgColor, 0.65f);
            mBgColorSelected = a.getColor(R.styleable.AppButton_bgColorSelected, mBgColorSelected);
            mBgColorDisabled = a.getColor(R.styleable.AppButton_bgColorDisabled, mBgColorDisabled);

            // Text Color
            mTextColor = a.getColor(R.styleable.AppButton_textColorButton, mTextColor);
            mTextColorSelected =
                    a.getColor(R.styleable.AppButton_textColorSelected, mTextColorSelected);
            mTextColorDisabled =
                    a.getColor(R.styleable.AppButton_textColorDisabled, mTextColorDisabled);

            // Border
            mStrokeWidth =
                    a.getDimensionPixelSize(R.styleable.AppButton_cornerStrokeWidth, mStrokeWidth);
            mStrokeColor = a.getColor(R.styleable.AppButton_cornerStrokeColor, mStrokeColor);

            mCornerRadius = a.getDimensionPixelSize(R.styleable.AppButton_corner, mCornerRadius);
            mTextSize = a.getDimensionPixelSize(R.styleable.AppButton_textSizeButton, mTextSize);
            mSelected = a.getBoolean(R.styleable.AppButton_selected, mSelected);

            mBackgroundDrawable = a.getDrawable(R.styleable.AppButton_bgDrawable);
        } finally {
            a.recycle();
        }
    }

    private void refresh() {
        if (!isEnabled()) {
            mGradientDrawable.setColor(mBgColorDisabled);
            setBackground(mGradientDrawable);
            setTextColor(mTextColorDisabled);
        } else if (mBackgroundDrawable != null) {
            setBackground(mBackgroundDrawable);
            setTextColor(mSelected ? mTextColorSelected : mTextColor);
        } else if (mLayerDrawable != null) {
            setBackground(mLayerDrawable);
            setTextColor(mSelected ? mTextColorSelected : mTextColor);
        } else {
            mGradientDrawable.setColor(mSelected ? mBgColorSelected : mBgColor);
            mGradientDrawable.setCornerRadius(mCornerRadius);
            mGradientDrawable.setStroke(mStrokeWidth, mStrokeColor);
            mGradientDrawable.setShape(GradientDrawable.RECTANGLE);
            setBackground(mGradientDrawable);

            setTextColor(mSelected ? mTextColorSelected : mTextColor);
        }
        setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        invalidate();
    }

    public void setBackgroundColor(int color) {
        setBackgroundColor(color, color);
    }

    public void setBackgroundColor(int color, int colorSelected) {
        this.mBgColor = color;
        this.mBgColorSelected = colorSelected;
        refresh();
    }

    public void setCorner(int corner) {
        this.mCornerRadius = corner;
        refresh();
    }

    public void setType(String type) {
        this.mType = type;
        refresh();
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        this.mSelected = selected;
        refresh();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        refresh();
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        this.mSelected = pressed;
        refresh();
    }
}
