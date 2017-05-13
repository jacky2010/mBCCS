package com.viettel.mbccs.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.DataBindingUtil;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.databinding.ObservableField;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.viettel.mbccs.R;
import com.viettel.mbccs.databinding.SearchViewBinding;

@BindingMethods({
        @BindingMethod(type = SearchInputView.class, attribute = "text", method = "setText")
})
@InverseBindingMethods({
        @InverseBindingMethod(type = SearchInputView.class, attribute = "text", method = "getText")
})
public class SearchInputView extends LinearLayout {

    protected static final String INPUT_TYPE_TEXT = "text";
    protected static final String INPUT_TYPE_DATE = "date";
    protected static final String INPUT_TYPE_NUMBER = "number";
    protected static final String INPUT_TYPE_DECIMAL = "numberDecimal";
    protected static final String INPUT_TYPE_EMAIL = "email";
    protected static final String INPUT_TYPE_PHONE = "phone";
    protected static final String INPUT_TYPE_PASSWORD = "password";
    protected static final String IME_OPTIONS_DONE = "actionDone";
    protected static final String IME_OPTIONS_SEARCH = "actionSearch";
    protected static final String IME_OPTIONS_NEXT = "actionNext";
    private SearchViewBinding mBinding;
    private EditText mEditText;
    public ObservableField<String> text;
    private float textSize;
    private int color;
    private String inputType = INPUT_TYPE_TEXT;
    private String imeOptions;
    private int maxLength;
    private int lines;

    public SearchInputView(Context context) {
        this(context, null);
    }

    public SearchInputView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchInputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attributeSet) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.search_view,
                this, true);
        mBinding.setData(this);
        mEditText = mBinding.textFiler;
        text = new ObservableField<>();
        TypedArray typedArray =
                getContext().obtainStyledAttributes(attributeSet, R.styleable.SearchInputView);
        try {
            String content = typedArray.getString(R.styleable.SearchInputView_text);
            text.set(content);
            textSize = typedArray.getDimension(R.styleable.SearchInputView_textSize,
                    getResources().getDimension(R.dimen.sp_13));
            color = typedArray.getColor(R.styleable.SearchInputView_textColor,
                    getResources().getColor(R.color.black));
            inputType = typedArray.getString(R.styleable.SearchInputView_inputType);
            imeOptions = typedArray.getString(R.styleable.SearchInputView_imeOptions);
            maxLength = typedArray.getInt(R.styleable.SearchInputView_maxLength, Integer.MAX_VALUE);
            lines = typedArray.getInt(R.styleable.SearchInputView_lines, 1);
        } catch (Exception e) {
            typedArray.recycle();
        }

        setAttribute();
    }

    private void setAttribute() {
        mEditText.setText(text.get());
        setLines(lines);
        mEditText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(maxLength) });
        setInputType(inputType);
        setImeOptions(imeOptions);
        setColor(color);
        setTextSize(textSize);
    }

    @BindingAdapter("textAttrChanged")
    public static void setTextChangedListener(SearchInputView view,
            final InverseBindingListener contentChanged) {
        view.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                contentChanged.onChange();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void setInputType(String inputType) {
        if (inputType == null) {
            return;
        }
        switch (inputType) {
            case INPUT_TYPE_NUMBER:
                mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case INPUT_TYPE_DECIMAL:
                mEditText.setInputType(
                        InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;
            case INPUT_TYPE_TEXT:
                mEditText.setInputType(
                        InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                break;
            case INPUT_TYPE_EMAIL:
                mEditText.setInputType(InputType.TYPE_CLASS_TEXT
                        | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                        | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                break;
            case INPUT_TYPE_PHONE:
                mEditText.setInputType(InputType.TYPE_CLASS_PHONE);
                break;
            case INPUT_TYPE_PASSWORD:
                mEditText.setInputType(
                        InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
            default:
                break;
        }
    }

    public void setImeOptions(String imeOptions) {
        if (imeOptions == null) {
            mEditText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            return;
        }
        switch (imeOptions) {
            case IME_OPTIONS_DONE:
                mEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
                break;
            case IME_OPTIONS_SEARCH:
                mEditText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
                break;
            case IME_OPTIONS_NEXT:
                mEditText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                break;
        }
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getInputType() {
        return inputType;
    }

    public String getImeOptions() {
        return imeOptions;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public String getText() {
        return text.get();
    }

    public void setText(String s) {
        text.set(s);
    }

    public EditText getEditText() {
        return mEditText;
    }
}
