package com.viettel.mbccs.widget.spinner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
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
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.viettel.mbccs.R;

import java.util.List;

public class EditSpinner extends RelativeLayout implements View.OnClickListener, AdapterView.OnItemClickListener, TextWatcher {

    private EditText mEditText;
    private ImageView mRightIv;
    private View mRightImageTopView;
    private Context mContext;
    private ListPopupWindow popupWindow;
    private BaseEditSpinnerAdapter mAdapter;
    private TypedArray mTypedArray;
    private boolean isPopupWindowShowing;
    //private Animation mAnimation;
    //private Animation mResetAnimation;

    public EditSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView(attrs);
        initAnimation();
    }


    public void setItemData(List<String> data) {
        mAdapter = new SpinnerAdapter(mContext, data);
        setAdapter(mAdapter);
    }

    public void setText(String text) {
        mEditText.setText(text);
    }

    public void setTextColor(@ColorInt int color) {
        mEditText.setTextColor(color);
    }

    public String getText() {
        return mEditText.getText().toString();
    }

    public void setHint(String hint) {
        mEditText.setHint(hint);
    }

    public void setRightImageDrawable(Drawable drawable) {
        mRightIv.setImageDrawable(drawable);
    }

    public void setRightImageResource(@DrawableRes int res) {
        mRightIv.setImageResource(res);
    }

    public void setAdapter(BaseEditSpinnerAdapter mAdapter) {
        this.mAdapter = mAdapter;
        setBaseAdapter(this.mAdapter);
    }

    private void initAnimation() {
        //mAnimation = new RotateAnimation(0, -90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //mAnimation.setDuration(300);
        //mAnimation.setFillAfter(true);
        //mResetAnimation = new RotateAnimation(-90, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //mResetAnimation.setDuration(300);
        //mResetAnimation.setFillAfter(true);
    }

    private void initView(AttributeSet attrs) {
        LayoutInflater.from(mContext).inflate(R.layout.edit_spinner, this);
        mEditText = (EditText) findViewById(R.id.edit_sipnner_edit);
        mRightIv = (ImageView) findViewById(R.id.edit_spinner_expand);
        mRightImageTopView = findViewById(R.id.edit_spinner_expand_above);
        mRightImageTopView.setOnClickListener(this);
        mRightIv.setOnClickListener(this);
        //mRightIv.setRotation(90);
        mEditText.addTextChangedListener(this);
        mTypedArray = mContext.obtainStyledAttributes(attrs,
                R.styleable.EditSpinner);
        mEditText.setHint(mTypedArray.getString(R.styleable.EditSpinner_hint_text));
        int imageId = mTypedArray.getResourceId(R.styleable.EditSpinner_rightImage, 0);
        if (imageId != 0) {
            mRightIv.setImageResource(imageId);
        }
        mRightIv.setVisibility(imageId == 0 ? GONE : VISIBLE);
        int bg = mTypedArray.getResourceId(R.styleable.EditSpinner_Background, 0);
        if (bg != 0) {
            mEditText.setBackgroundResource(bg);
        }
        mTypedArray.recycle();
    }

    private final void setBaseAdapter(BaseAdapter mAdapter) {
        if (popupWindow == null) {
            initPopupWindow();
        }
        popupWindow.setAdapter(mAdapter);
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


    @Override
    public final void onClick(View v) {
        if (mAdapter == null || popupWindow == null) {
            return;
        }
        if (v.getId() == R.id.edit_spinner_expand_above) {
            v.setClickable(false);
            return;
        }
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
//            cacheData.clear();
//            cacheData.addAll(data);
            showFilterData("");
        }
    }

    @Override
    public final void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       // mEditText.setText(((BaseEditSpinnerAdapter) parent.getAdapter()).getItemString(position));
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

    public void setEnabledEditText(boolean isEnable) {
        if (mEditText != null) {
            mEditText.setEnabled(isEnable);
        }
    }
}
