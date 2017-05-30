package com.viettel.mbccs.screen;

import android.databinding.ObservableField;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.TestBinding;
import com.viettel.mbccs.widget.SpinnerWithBorder;

public class Test extends BaseDataBindActivity<TestBinding, Void> {

    public ArrayAdapter adapter;

    public ObservableField<String> text;

    @Override
    protected int getIdLayout() {
        return R.layout.test;
    }

    @Override
    protected void initData() {
        text = new ObservableField<>();
        text.set("231321");

        adapter = new SpinnerWithBorder.HintAdapter<>(this, android.R.layout.simple_spinner_item,
                android.R.id.text1, getResources().getStringArray(R.array.task_type));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mBinding.setData(this);
        mBinding.layoutSearch.spinner.getSpinner()
                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i,
                            long l) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

        mBinding.layoutSearch.fromDate.getDateInMilis();
    }

    public ArrayAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(ArrayAdapter adapter) {
        this.adapter = adapter;
    }
}
