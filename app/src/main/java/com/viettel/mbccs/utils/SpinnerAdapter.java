package com.viettel.mbccs.utils;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.widget.CustomTextView;
import java.util.List;

/**
 * Created by eo_cuong on 6/18/17.
 */

public class SpinnerAdapter<T> extends ArrayAdapter<T> {

    private Context mContext;
    private List<T> mList;
    private int selectedPosition = 0;

    public SpinnerAdapter(@NonNull Context context, List<T> list) {
        super(context, 0, list);
        mList = list;
        mContext = context;
    }


    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView =
                    LayoutInflater.from(mContext).inflate(R.layout.item_spinner, parent, false);
        }
        // Lookup view for data population
        CustomTextView tvName = (CustomTextView) convertView.findViewById(R.id.text);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
        // Populate the data into the template view using the data object
        tvName.setText(mList.get(position).toString());
        imageView.setVisibility(selectedPosition == position ? View.VISIBLE : View.GONE);
        // Return the completed view to render on screen
        return convertView;
    }
}
