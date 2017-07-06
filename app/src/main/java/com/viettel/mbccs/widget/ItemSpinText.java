package com.viettel.mbccs.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.utils.EditTextUtil;
import com.viettel.mbccs.widget.spinner.BaseEditSpinnerAdapter;
import com.viettel.mbccs.widget.spinner.SpinnerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jacky on 5/21/17.
 */

public class ItemSpinText extends LinearLayout implements View.OnClickListener, AdapterView.OnItemClickListener, TextWatcher {

    private Context mContext;
    private Unbinder mUnbinder;

    @BindView(R.id.iv_icon_left)
    ImageView mIconLeft;
    @BindView(R.id.iv_icon_right)
    ImageView mIconRight;
    @BindView(R.id.tv_title)
    TextView mText;
    @BindView(R.id.spinner)
    EditText mEditText;

    private OnSelectSpinnerListener mOnSelectSpinnerListener;
    private ListPopupWindow popupWindow;
    private BaseEditSpinnerAdapter mAdapter;
    private boolean isPopupWindowShowing;
    private String mTitleTextSpinner;

    public void setOnSelectSpinnerListener(OnSelectSpinnerListener mOnSelectSpinnerListener) {
        this.mOnSelectSpinnerListener = mOnSelectSpinnerListener;
    }

    public ItemSpinText(Context context) {
        super(context);
        init(context, null);
    }

    public ItemSpinText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ItemSpinText(Context context, AttributeSet attrs, int defStyleAttr) {
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
        inflater.inflate(R.layout.item_spinner_text_view, this);
        mUnbinder = ButterKnife.bind(this);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemSpinText);
        initTitle(mTypedArray);
        initIcon(mTypedArray, R.styleable.ItemSpinText_istIconLeft, R.styleable.ItemSpinText_istIconLeftBg, mIconLeft);
        initIcon(mTypedArray, R.styleable.ItemSpinText_istIconRight, R.styleable.ItemSpinText_istIconRightBg, mIconRight);
        initSpinner(mTypedArray);
        findViewById(R.id.frame).setOnClickListener(this);
        findViewById(R.id.iv_icon_right).setOnClickListener(this);
    }

    private void initSpinner(TypedArray mTypedArray) {
        mTitleTextSpinner = mTypedArray.getString(R.styleable.ItemSpinText_istSpinnerTitle);
        boolean isActive = mTypedArray.getBoolean(R.styleable.ItemSpinText_istSpinner, false);
        boolean isEnable = mTypedArray.getBoolean(R.styleable.ItemSpinText_istSpinnerEnable, true);
        mEditText.setVisibility(isActive ? VISIBLE : GONE);
        mEditText.setText(mTitleTextSpinner);
        mEditText.setEnabled(isEnable);
        findViewById(R.id.frame).setVisibility(isEnable ? GONE : VISIBLE);
    }


    private void initTitle(TypedArray mTypedArray) {
        String title = mTypedArray.getString(R.styleable.ItemSpinText_istTextViewTitle);
        boolean isActive = mTypedArray.getBoolean(R.styleable.ItemSpinText_istTextView, false);
        mText.setVisibility(TextUtils.isEmpty(title) || !isActive ? GONE : VISIBLE);
        if (!TextUtils.isEmpty(title)) {
            int size = mTypedArray.getDimensionPixelSize(R.styleable.ItemSpinText_istTextViewSize,
                    getResources().getDimensionPixelSize(R.dimen.default_item_spinner_text_size));
            int color = mTypedArray.getColor(R.styleable.ItemSpinText_istTextViewColor,
                    getResources().getColor(R.color.default_item_spinner_text_color));
            mText.setText(title);
            mText.setTextSize(px2dip(size));
            mText.setTextColor(color);
        }
    }

    private void initIcon(TypedArray mTypedArray, int slActive, int styleable, ImageView mImage) {
        boolean isActive = mTypedArray.getBoolean(slActive, false);
        Drawable icon = mTypedArray.getDrawable(styleable);
        if (icon != null) {
            mImage.setImageDrawable(icon);
        }
        mImage.setVisibility(icon == null || !isActive ? GONE : VISIBLE);
    }

    private int px2dip(float pxValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
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

    public void setListSpinner(List<String> list) {
        setItemData(list);
    }


    @Override
    protected void onDetachedFromWindow() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDetachedFromWindow();
    }

    public void setItemData(List<String> data) {
        mAdapter = new SpinnerAdapter(mContext, data);
        setAdapter(mAdapter);
    }

    public void setAdapter(BaseEditSpinnerAdapter mAdapter) {
        this.mAdapter = mAdapter;
        setBaseAdapter(this.mAdapter);
    }

    private final void setBaseAdapter(BaseAdapter mAdapter) {
        if (popupWindow == null) {
            initPopupWindow();
        }
        popupWindow.setAdapter(mAdapter);

    }

    public void showData() {
        if (mAdapter == null || popupWindow == null) {
            return;
        }

        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            //cacheData.clear();
            //cacheData.addAll(data);
            showFilterData("");
        }
    }

    private void initPopupWindow() {
        popupWindow = new ListPopupWindow(mContext) {
            @Override
            public boolean isShowing() {
                return isPopupWindowShowing;
            }

            @Override
            public void show() {
                super.show();
                isPopupWindowShowing = true;
                //mRightIv.startAnimation(mAnimation);
            }
        };
        popupWindow.setOnItemClickListener(this);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setPromptPosition(ListPopupWindow.POSITION_PROMPT_BELOW);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnchorView(mEditText);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                isPopupWindowShowing = false;
                // mRightIv.startAnimation(mResetAnimation);
            }
        });
    }

    public String getStringEditText() {
        return mEditText != null ? mEditText.getText().toString().trim() : "";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frame:
                EditTextUtil.hideSoftKeyboard(mEditText, mContext);
                if (mAdapter == null || popupWindow == null) {
                    return;
                }
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    popupWindow.show();
                }
                break;
            case R.id.iv_icon_right:
                mEditText.setText("");
                break;
        }

    }

    @Override
    public final void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String data = ((BaseEditSpinnerAdapter) parent.getAdapter()).getItemString(position);
        System.out.println("onItemClick" + position + "--" + data);
        mEditText.setText(data);
        popupWindow.dismiss();
    }

    @Override
    public final void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public final void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public final void afterTextChanged(Editable s) {
//        String key = s.toString();
//        mEditText.setSelection(key.length());
//        if (!TextUtils.isEmpty(key)) {
//            showFilterData(key);
//        } else {
//            if (popupWindow != null) {
//                popupWindow.dismiss();
//            }
//        }
    }

    private void showFilterData(String key) {
        if (popupWindow == null || mAdapter == null || mAdapter.getEditSpinnerFilter() == null) {
            if (popupWindow != null) {
                popupWindow.dismiss();
            }
            return;
        }
        if (mAdapter.getEditSpinnerFilter().onFilter(key)) {
            popupWindow.dismiss();
        } else {
            popupWindow.show();
        }
    }

    public String getStringSpinner() {
        return mEditText.getText().toString().equals(mTitleTextSpinner) ? "" : mEditText.getText().toString();
    }

    public interface OnSelectSpinnerListener {
        void onItemSelect(ItemSpinText itemSpinText, int position);
    }
}
