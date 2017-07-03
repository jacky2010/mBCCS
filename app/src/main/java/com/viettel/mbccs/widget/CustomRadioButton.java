package com.viettel.mbccs.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import com.viettel.mbccs.utils.FontCache;

/**
 * Created by eo_cuong on 7/3/17.
 */

public class CustomRadioButton extends AppCompatRadioButton {
    private static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public CustomRadioButton(Context context) {
        super(context);
        applyCustomFont(context, null);
    }

    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context, attrs);
    }

    public CustomRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);
        Typeface customFont = FontCache.selectTypeface(context, textStyle);
        setTypeface(customFont);
    }
}
