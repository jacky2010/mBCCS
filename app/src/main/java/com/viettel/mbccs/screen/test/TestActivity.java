package com.viettel.mbccs.screen.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.viettel.mbccs.R;
import com.viettel.mbccs.widget.RangeSliderView;

/**
 * Created by jacky on 5/14/17.
 */

public class TestActivity extends AppCompatActivity {

    private RangeSliderView mRangeSliderView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mRangeSliderView = (RangeSliderView) findViewById(R.id.rsv);

        mRangeSliderView.setInitialIndex(0);
        final RangeSliderView.OnSlideListener listener = new RangeSliderView.OnSlideListener() {
            @Override
            public void onSlide(int index) {
                Toast.makeText(
                        getApplicationContext(),
                        "Index: " + index,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        };
        mRangeSliderView.setOnSlideListener(listener);
    }
}
