package com.viettel.mbccs.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.EditText;

import com.viettel.mbccs.R;
import com.viettel.mbccs.utils.EditTextUtil;
import com.viettel.mbccs.utils.FontCache;

/**
 * Created by FRAMGIA\hoang.van.cuong on 16/05/2017.
 */

public class CustomEdittext extends EditText {

    private static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";
    private static final String STRING_EMPTY = "";

    public CustomEdittext(Context context) {
        super(context);
        applyCustomFont(context, null);
    }

    public CustomEdittext(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context, attrs);
    }

    public CustomEdittext(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        int textSize = (int) getResources().getDimension(R.dimen.sp_14);
        setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        if (attrs != null) {
            int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);
            Typeface customFont = FontCache.selectTypeface(context, textStyle);
            setTypeface(customFont);
        }
        EditTextUtil.fixFontPasswordType(this);
    }

    public String getInputText() {
        return this.getText().toString().trim();
    }

    public void clearInputText() {
        setText(STRING_EMPTY);
    }

    public void setEditable(boolean editable) {
        setFocusable(editable);
        setFocusableInTouchMode(editable);
    }
}
