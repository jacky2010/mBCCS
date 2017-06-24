package com.viettel.mbccs.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.utils.EditTextUtil;
import com.viettel.mbccs.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jacky on 6/23/17.
 */

public class ItemTitleWithEditText extends LinearLayout {

    private Context mContext;
    private Unbinder mUnbinder;

    @BindView(R.id.tv_title)
    TextView mText;
    @BindView(R.id.ed_text)
    EditText mEditText;

    public ItemTitleWithEditText(Context context) {
        super(context);
        init(context, null);
    }

    public ItemTitleWithEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ItemTitleWithEditText(Context context, AttributeSet attrs, int defStyleAttr) {
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
        inflater.inflate(R.layout.item_title_with_edit_text, this);
        mUnbinder = ButterKnife.bind(this);

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemTitleWithEditText);
        initTitle(mTypedArray);
        initEditText(mTypedArray);
    }

    private void initEditText(TypedArray mTypedArray) {
        String title = mTypedArray.getString(R.styleable.ItemTitleWithEditText_iteEditText);
        boolean isActive = mTypedArray.getBoolean(R.styleable.ItemTitleWithEditText_iteEditText, true);
        mEditText.setVisibility(isActive ? VISIBLE : GONE);
        mEditText.setText(title);
        EditTextUtil.hideSoftKeyboard(mEditText, mContext);
    }

    private void initTitle(TypedArray mTypedArray) {
        String title = mTypedArray.getString(R.styleable.ItemTitleWithEditText_iteTextViewTitle);
        boolean isActive = mTypedArray.getBoolean(R.styleable.ItemTitleWithEditText_iteTextView, true);
        mText.setVisibility(TextUtils.isEmpty(title) || !isActive ? GONE : VISIBLE);
        if (!TextUtils.isEmpty(title)) {
            int size = mTypedArray.getDimensionPixelSize(R.styleable.ItemTitleWithEditText_iteTextViewSize,
                    getResources().getDimensionPixelSize(R.dimen.default_item_spinner_text_size));
            int color = mTypedArray.getColor(R.styleable.ItemTitleWithEditText_iteTextViewColor,
                    getResources().getColor(R.color.default_item_spinner_text_color));
            mText.setText(title);
            mText.setTextSize(ScreenUtils.px2dip(mContext, size));
            mText.setTextColor(color);
        }
    }
}
