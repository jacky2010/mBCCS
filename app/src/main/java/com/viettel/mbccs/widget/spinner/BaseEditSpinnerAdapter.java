package com.viettel.mbccs.widget.spinner;

import android.widget.BaseAdapter;


public abstract class BaseEditSpinnerAdapter extends BaseAdapter {

    public abstract EditSpinnerFilter getEditSpinnerFilter();

    public abstract String getItemString(int position);

}
