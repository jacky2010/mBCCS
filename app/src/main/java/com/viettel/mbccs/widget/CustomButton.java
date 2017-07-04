package com.viettel.mbccs.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.widget.Button;
import com.viettel.mbccs.utils.FontCache;

/**
 * Created by FRAMGIA\hoang.van.cuong on 16/05/2017.
 */

public class CustomButton extends AppCompatButton {
    private static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public CustomButton(Context context) {
        super(context);
        applyCustomFont(context, null);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context, attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);
        Typeface customFont = FontCache.selectTypeface(context, textStyle);
        setTypeface(customFont);
    }
}
