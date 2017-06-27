package com.viettel.mbccs.utils;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import com.viettel.mbccs.R;
import com.viettel.mbccs.widget.CustomTextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.w3c.dom.Text;

/**
 * Created by eo_cuong on 6/18/17.
 */

public class SpinnerAdapter<T> extends ArrayAdapter<T> {

    private Context mContext;
    private List<T> mList;
    private int selectedPosition = 0;
    private Spinner spinner;
    private String textHint;
    private boolean isUsehintValue;
    private boolean isThemeLight = false;
    private AdapterView.OnItemSelectedListener mOnItemSelectedListener;

    public SpinnerAdapter(@NonNull Context context, List<T> list) {
        super(context, R.layout.item_spinner, list);
        mList = list;
        mContext = context;
    }

    public SpinnerAdapter(@NonNull Context context, T[] objs) {
        super(context, R.layout.item_spinner, objs);
        mList = new ArrayList<>();
        mList.addAll(Arrays.asList(objs));
        mContext = context;
    }

    public String getTextHint() {
        return textHint;
    }

    public void setTextHint(String textHint) {
        this.textHint = textHint;
    }

    public boolean isUsehintValue() {
        return isUsehintValue;
    }

    public void setUsehintValue(boolean usehintValue) {
        isUsehintValue = usehintValue;
    }

    public void setOnItemSelectedListener(
            AdapterView.OnItemSelectedListener onItemSelectedListener) {
        mOnItemSelectedListener = onItemSelectedListener;
    }

    public boolean isThemeLight() {
        return isThemeLight;
    }

    public void setThemeLight(boolean themeLight) {
        isThemeLight = themeLight;
    }

    public void setSpinner(Spinner spinner) {
        this.spinner = spinner;
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mList.size() == 0) {
                    return;
                }
                setSelectedPosition(position);
                if (mOnItemSelectedListener != null) {
                    if (!TextUtils.isEmpty(textHint) && !isUsehintValue) {
                        if (position - 1 < 0) {
                            return;
                        }
                        mOnItemSelectedListener.onItemSelected(parent, view, position - 1, id);
                    } else {
                        mOnItemSelectedListener.onItemSelected(parent, view, position, id);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (mOnItemSelectedListener != null) {
                    mOnItemSelectedListener.onNothingSelected(parent);
                }
            }
        });
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView =
                    LayoutInflater.from(mContext).inflate(R.layout.item_spinner, parent, false);
            // Lookup view for data population
            viewHolder.tvName = (CustomTextView) convertView.findViewById(R.id.text);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        if (!TextUtils.isEmpty(textHint)) {
            if (position == 0) {
                viewHolder.tvName.setText(textHint);
                if (isThemeLight) {
                    viewHolder.tvName.setTextColor(
                            mContext.getResources().getColor(R.color.white_trans));
                } else {
                    viewHolder.tvName.setTextColor(
                            mContext.getResources().getColor(R.color.grey_dove));
                }
            } else {
                if (isThemeLight) {
                    viewHolder.tvName.setTextColor(mContext.getResources().getColor(R.color.white));
                } else {
                    viewHolder.tvName.setTextColor(mContext.getResources().getColor(R.color.black));
                }

                viewHolder.tvName.setText(mList.get(position - 1).toString());
                viewHolder.imageView.setVisibility(View.GONE);
            }
        } else {
            if (isThemeLight) {
                viewHolder.tvName.setTextColor(mContext.getResources().getColor(R.color.white));
            } else {
                viewHolder.tvName.setTextColor(mContext.getResources().getColor(R.color.black));
            }
            viewHolder.tvName.setText(mList.get(position).toString());
        }

        viewHolder.imageView.setVisibility(View.GONE);
        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public int getCount() {
        if (!TextUtils.isEmpty(textHint)) {
            return mList.size() + 1;
        }
        return mList.size();
    }

    @Override
    public View getDropDownView(final int position, @Nullable View convertView,
            @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_spinner_dropdown, parent, false);
            // Lookup view for data population
            viewHolder.tvName = (CustomTextView) convertView.findViewById(R.id.text);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.item_spinner = (RelativeLayout) convertView.findViewById(R.id.item_spinner);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        if (!TextUtils.isEmpty(textHint)) {
            if (position == 0) {
                viewHolder.tvName.setText(textHint);
                viewHolder.imageView.setVisibility(
                        selectedPosition == position ? View.VISIBLE : View.GONE);
                viewHolder.item_spinner.setBackgroundColor(
                        selectedPosition == position ? mContext.getResources()
                                .getColor(R.color.spinner_grey)
                                : mContext.getResources().getColor(R.color.white));
                if (isUsehintValue) {
                    viewHolder.item_spinner.getLayoutParams().height =
                            ViewGroup.LayoutParams.WRAP_CONTENT;
                } else {
                    viewHolder.item_spinner.getLayoutParams().height = 0;
                }

                viewHolder.item_spinner.requestLayout();
            } else {
                viewHolder.tvName.setText(mList.get(position - 1).toString());
                viewHolder.imageView.setVisibility(
                        selectedPosition == position ? View.VISIBLE : View.GONE);
                viewHolder.item_spinner.setBackgroundColor(
                        selectedPosition == position ? mContext.getResources()
                                .getColor(R.color.spinner_grey)
                                : mContext.getResources().getColor(R.color.white));
                viewHolder.item_spinner.setVisibility(View.VISIBLE);
                viewHolder.item_spinner.getLayoutParams().height =
                        ViewGroup.LayoutParams.WRAP_CONTENT;
                viewHolder.item_spinner.requestLayout();
            }
        } else {
            viewHolder.tvName.setText(mList.get(position).toString());
            viewHolder.imageView.setVisibility(
                    selectedPosition == position ? View.VISIBLE : View.GONE);
            viewHolder.item_spinner.setBackgroundColor(
                    selectedPosition == position ? mContext.getResources()
                            .getColor(R.color.spinner_grey)
                            : mContext.getResources().getColor(R.color.white));
            viewHolder.item_spinner.setVisibility(View.VISIBLE);
            viewHolder.item_spinner.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
            viewHolder.item_spinner.requestLayout();
        }

        // Return the completed view to render on screen
        return convertView;
    }

    class ViewHolder {
        CustomTextView tvName;
        ImageView imageView;
        RelativeLayout item_spinner;
    }
}
