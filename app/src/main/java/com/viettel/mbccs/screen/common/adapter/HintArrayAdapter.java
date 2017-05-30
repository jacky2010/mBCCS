package com.viettel.mbccs.screen.common.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.variable.Constants;

import java.util.List;

/**
 * Created by minhnx on 5/30/17.
 */

public class HintArrayAdapter<T> extends ArrayAdapter<T> {

    private Context mContext;
    private int layoutId;
    private int textViewId;

    public HintArrayAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId) {
        super(context, resource, textViewResourceId);
        this.mContext = context;
        this.layoutId = resource;
        this.textViewId = textViewResourceId;
    }

    public HintArrayAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull T[] objects) {
        super(context, resource, textViewResourceId, objects);
        this.mContext = context;
        this.layoutId = resource;
        this.textViewId = textViewResourceId;
    }

    public HintArrayAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<T> objects) {
        super(context, resource, textViewResourceId, objects);
        this.mContext = context;
        this.layoutId = resource;
        this.textViewId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layoutId, parent, false);
        TextView texview = (TextView) view.findViewById(textViewId);

        if(position == 0) {
            texview.setText("");
            texview.setHint(getItem(position).toString()); //"Hint to be displayed"
            view.setTag(Constants.View.HINT);
        } else {
            texview.setText(getItem(position).toString());
        }

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view;

        if(position == 0){
            view = inflater.inflate(R.layout.spinner_hint_list_item_layout, parent, false); // Hide first row
        } else {
            view = inflater.inflate(layoutId, parent, false);
            TextView texview = (TextView) view.findViewById(textViewId);
            texview.setText(getItem(position).toString());
        }

        return view;
    }
}