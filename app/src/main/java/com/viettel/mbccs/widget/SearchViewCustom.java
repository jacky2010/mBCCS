package com.viettel.mbccs.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.utils.EditTextUtil;
import com.viettel.mbccs.widget.spinner.BaseEditSpinnerAdapter;
import com.viettel.mbccs.widget.spinner.SpinnerAdapter;

import java.util.List;

/**
 * Created by jacky on 5/28/17.
 */


public class SearchViewCustom extends RelativeLayout implements View.OnClickListener, AdapterView.OnItemClickListener, TextWatcher {

    private Context mContext;
    private ImageView mClear;
    private OnClickItemSearchViewListener mOnClickItemSearchViewListener;
    private EditText mEditText;

    private ListPopupWindow popupWindow;
    private BaseEditSpinnerAdapter adapter;
    private boolean isPopupWindowShowing;
    private RelativeLayout mRoot;

    public SearchViewCustom(Context context) {
        super(context);
        init(context, null);
    }

    public SearchViewCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SearchViewCustom(Context context, AttributeSet attrs, int defStyleAttr) {
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
        inflater.inflate(R.layout.item_search_view_custom, this);

        mRoot = (RelativeLayout) findViewById(R.id.view_search);
        mClear = (ImageView) findViewById(R.id.iv_remove);
        mEditText = (EditText) findViewById(R.id.ed_search);
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    EditTextUtil.hideSoftKeyboard(mEditText, mContext);
                    showData();
                    return true;
                }
                return false;
            }
        });
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchViewCustom);
        initClear(mTypedArray, mClear);
    }

    private void showData() {
        if (adapter == null || popupWindow == null) {
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

    public void setItemData(List<String> data) {
        adapter = new SpinnerAdapter(mContext, data);
        setAdapter(adapter);
    }


    public void setAdapter(BaseEditSpinnerAdapter adapter) {
        this.adapter = adapter;
        setBaseAdapter(this.adapter);
    }


    private final void setBaseAdapter(BaseAdapter adapter) {
        if (popupWindow == null) {
            initPopupWindow();
        }
        popupWindow.setAdapter(adapter);
    }


    private void initClear(TypedArray mTypedArray, ImageView mClear) {
        boolean isActive = mTypedArray.getBoolean(R.styleable.SearchViewCustom_svcIconClear, false);
        mClear.setVisibility(isActive ? VISIBLE : GONE);
        mClear.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_remove:
                if (mEditText != null) {
                    mEditText.setText("");
                }
                break;
            default:
                break;
        }
    }

    private void showFilterData(String key) {
        if (popupWindow == null || adapter == null || adapter.getEditSpinnerFilter() == null) {
            if (popupWindow != null) {
                popupWindow.dismiss();
            }
            return;
        }
        if (adapter.getEditSpinnerFilter().onFilter(key)) {
            popupWindow.dismiss();
        } else {
            popupWindow.show();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String data = ((BaseEditSpinnerAdapter) parent.getAdapter()).getItemString(position);
        mEditText.setText(data);
        if(mOnClickItemSearchViewListener!=null){
            mOnClickItemSearchViewListener.onResult(data);
        }
        popupWindow.dismiss();
    }

    public interface OnClickItemSearchViewListener<K> {
        void onResult(K k);
    }

    public void setOnClickItem(OnClickItemSearchViewListener mOnClickItemSearchViewListener) {
        this.mOnClickItemSearchViewListener = mOnClickItemSearchViewListener;
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
        popupWindow.setAnchorView(mRoot);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                isPopupWindowShowing = false;
                // mRightIv.startAnimation(mResetAnimation);
            }
        });
    }
}
