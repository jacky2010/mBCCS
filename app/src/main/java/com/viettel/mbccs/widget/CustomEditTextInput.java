package com.viettel.mbccs.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import com.viettel.mbccs.R;
import com.viettel.mbccs.widget.callback.DrawableClickListener;

/**
 * Created by eo_cuong on 7/1/17.
 */

public class CustomEditTextInput extends CustomEditText {

    private Drawable mDrawableRight = null;
    private boolean isThemeLight = false;

    public CustomEditTextInput(Context context) {
        super(context);
        init();
    }

    public CustomEditTextInput(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditTextInput(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setThemeLight(boolean themeLight) {
        if (themeLight) {
            isThemeLight = themeLight;
            mDrawableRight = ResourcesCompat.getDrawable(getContext().getResources(),
                    R.drawable.ic_cancel_grey_1_24dp, null);
            if (!TextUtils.isEmpty(getText())) {
                setCompoundDrawables(getDrawableBound(getDrawableLeft()),
                        getDrawableBound(getDrawableTop()), getDrawableBound(mDrawableRight),
                        getDrawableBound(getDrawableBottom()));
            } else {
                setCompoundDrawables(getDrawableBound(getDrawableLeft()),
                        getDrawableBound(getDrawableTop()), null,
                        getDrawableBound(getDrawableBottom()));
            }
        }
        setTextColor(getContext().getResources().getColor(R.color.white));
        setHintTextColor(getContext().getResources().getColor(R.color.white_trans));
        setBackgroundResource(R.drawable.bg_input_transparent_light);
    }

    private void init() {

        int padding = (int) getContext().getResources().getDimension(R.dimen.dp_5);
        setPadding(padding, padding, padding, padding);
        mDrawableRight = ResourcesCompat.getDrawable(getContext().getResources(),
                R.drawable.ic_cancel_black_24dp, null);
        setTextColor(getContext().getResources().getColor(R.color.black));
        setHintTextColor(getContext().getResources().getColor(R.color.grey_dove));
        setBackgroundResource(R.drawable.bg_input_transparent_black);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (isEnabled()) {
                    if (!TextUtils.isEmpty(charSequence)) {
                        setCompoundDrawables(getDrawableBound(getDrawableLeft()),
                                getDrawableBound(getDrawableTop()),
                                getDrawableBound(mDrawableRight),
                                getDrawableBound(getDrawableBottom()));
                    } else {
                        setCompoundDrawables(getDrawableBound(getDrawableLeft()),
                                getDrawableBound(getDrawableTop()), null,
                                getDrawableBound(getDrawableBottom()));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        setDrawableClickListener(new DrawableClickListener() {
            @Override
            public void onClick(View view, @DrawablePosition int target) {
                if (target == DrawablePosition.RIGHT) {
                    setText("");
                }
            }
        });
    }

    private Drawable getDrawableBound(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        int h = drawable.getIntrinsicHeight();
        int w = drawable.getIntrinsicWidth();
        drawable.setBounds(0, 0, w, h);
        return drawable;
    }
}
