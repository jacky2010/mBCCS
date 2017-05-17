package com.viettel.mbccs.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.viettel.mbccs.R;
import com.viettel.mbccs.databinding.LayoutIncDecTextInputBinding;
import com.viettel.mbccs.variable.Constants;

/**
 * Created by Anh Vu Viet on 5/14/2017.
 */

public class IncDecTextInput extends LinearLayout {

    private EditText mEditText;

    // FIXME: Cursor move to start
    public ObservableField<String> textNumber = new ObservableField<>();

    private int mNumber;

    public IncDecTextInput(Context context) {
        this(context, null);
    }

    public IncDecTextInput(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IncDecTextInput(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IncDecTextInput(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView(Context context, AttributeSet attrs) {
        ((LayoutIncDecTextInputBinding) DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.layout_inc_dec_text_input, this, true)).setInput(this);
        mEditText = (EditText) findViewById(R.id.amount_input);
        // TODO: Custom Add/Subtract icon
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IncDecInputText);
        try {
            mEditText.setTextSize(a.getDimension(R.styleable.IncDecInputText_inputTextSize,
                    getResources().getDimension(R.dimen.sp_14)));
            mEditText.setMaxEms(a.getInteger(R.styleable.IncDecInputText_maxLength, Integer.MAX_VALUE));
            mEditText.setTextColor(a.getColor(R.styleable.IncDecInputText_inputTextColor, Color.BLACK));
        } finally {
            a.recycle();
        }

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int i = Integer.valueOf(mEditText.getText().toString());
                    if (i < 0) {
                        mNumber = 0;
                        textNumber.set(Constants.DEFAULT_NUMBER_INPUT);
                        return;
                    }
                    mNumber = i;
                } catch (NumberFormatException e) {
                    textNumber.set(String.valueOf(mNumber));
                }
            }
        });
        textNumber.set(Constants.DEFAULT_NUMBER_INPUT);
    }

    public int getNumber() {
        return mNumber;
    }

    public void onAddClick() {
        try {
            int i = Integer.valueOf(textNumber.get());
            if (i >= Integer.MAX_VALUE)
                return;
            mNumber = ++i;
            textNumber.set(String.valueOf(mNumber));
        } catch (NumberFormatException ignored) {
        }
    }

    public void onSubtractClick() {
        try {
            int i = Integer.valueOf(textNumber.get());
            if (i <= 0)
                return;
            mNumber = --i;
            textNumber.set(String.valueOf(mNumber));
        } catch (NumberFormatException ignored) {
        }
    }
}
