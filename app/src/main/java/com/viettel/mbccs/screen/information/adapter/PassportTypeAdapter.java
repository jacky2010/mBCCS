package com.viettel.mbccs.screen.information.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.viettel.mbccs.R;
import java.util.List;

/**
 * Created by HuyQuyet on 5/31/17.
 */

public class PassportTypeAdapter extends ArrayAdapter<String> {

    private Context context;
    private int layoutId;
    private List<String> dataList;

    private TextView textView;

    public PassportTypeAdapter(@NonNull Context context, @LayoutRes int resource,
            List<String> data) {
        super(context, resource, data);
        this.context = context;
        this.layoutId = resource;
        dataList = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        textView = (TextView) convertView.findViewById(R.id.text);
        textView.setText(dataList.get(position));
        return convertView;
    }
}
