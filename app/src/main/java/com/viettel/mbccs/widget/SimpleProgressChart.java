package com.viettel.mbccs.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.graphics.Color;
import android.support.percent.PercentRelativeLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import com.viettel.mbccs.R;
import com.viettel.mbccs.databinding.LayoutSimpleProgressBinding;

/**
 * Created by FRAMGIA\vu.viet.anh on 17/05/2017.
 */

public class SimpleProgressChart extends PercentRelativeLayout {

    public ObservableFloat textSize;

    public ObservableInt textColor;

    public ObservableInt progressBgColor;

    public ObservableInt progressColor;

    public ObservableFloat progressSize;

    public ObservableFloat done;

    public SimpleProgressChart(Context context) {
        this(context, null);
    }

    public SimpleProgressChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleProgressChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(attrs);
    }

    private void initView(AttributeSet attributeSet) {
        LayoutSimpleProgressBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                        R.layout.layout_simple_progress, this, true);
        binding.setInput(this);
        textSize = new ObservableFloat();
        textColor = new ObservableInt();
        progressBgColor = new ObservableInt();
        progressColor = new ObservableInt();
        progressSize = new ObservableFloat();
        done = new ObservableFloat();

        TypedArray typedArray =
                getContext().obtainStyledAttributes(attributeSet, R.styleable.SimpleProgressChart);
        try {
            textSize.set(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                    typedArray.getDimension(R.styleable.SimpleProgressChart_progressTextSize,
                            getResources().getDimension(R.dimen.sp_14)),
                    getResources().getDisplayMetrics()));
            textColor.set(typedArray.getColor(R.styleable.SimpleProgressChart_progressTextColor,
                    Color.WHITE));

            progressBgColor.set(typedArray.getResourceId(R.styleable.SimpleProgressChart_progressBgColor,
                            android.R.color.darker_gray));
            progressColor.set(typedArray.getResourceId(R.styleable.SimpleProgressChart_progressColor,
                            R.color.colorPrimary));

            progressSize.set(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                    typedArray.getDimension(R.styleable.SimpleProgressChart_progressSize,
                            getResources().getDimension(R.dimen.dp_16)),
                    getResources().getDisplayMetrics()));

            done.set(typedArray.getFloat(R.styleable.SimpleProgressChart_progressDone, 0.0f));
        } catch (Exception e) {
            typedArray.recycle();
        }
    }

    public void setProgressTextSize(float textSize) {
        this.textSize.set(textSize);
    }

    public void setTextColor(int textColor) {
        this.textColor.set(textColor);
    }

    public void setProgressBgColor(int progressBgColor) {
        this.progressBgColor.set(progressBgColor);
    }

    public void setProgressColor(int progressColor) {
        this.progressColor.set(progressColor);
    }

    public void setProgressSize(float progressSize) {
        this.progressSize.set(progressSize);
    }

    public void setProgressDone(float done) {
        this.done.set(done);
    }
}
