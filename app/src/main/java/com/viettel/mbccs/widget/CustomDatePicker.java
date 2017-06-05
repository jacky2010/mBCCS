package com.viettel.mbccs.widget;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.databinding.LayoutDatePickerBinding;
import com.viettel.mbccs.utils.DateUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Anh Vu Viet on 5/13/2017.
 */

@BindingMethods({
        @BindingMethod(type = CustomDatePicker.class, attribute = "setDate", method = "setDateString")
})
public class CustomDatePicker extends LinearLayout {

    private Calendar mCalendar;
    public ObservableField<String> date = new ObservableField<>();

    public CustomDatePicker(Context context) {
        this(context, null);
    }

    public CustomDatePicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomDatePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomDatePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView(final Context context, AttributeSet attrs) {
        ((LayoutDatePickerBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.layout_date_picker, this, true)).setInput(this);
        mCalendar = Calendar.getInstance();
        setDate();

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mCalendar.set(Calendar.YEAR, year);
                        mCalendar.set(Calendar.MONTH, month);
                        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        setDate();
                    }
                }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        TextView textView = (TextView) findViewById(R.id.text_date);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomDatePicker);
        try {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    a.getDimension(R.styleable.CustomDatePicker_dateTextSize,
                            getResources().getDimension(R.dimen.sp_14)));
            textView.setTextColor(
                    a.getColor(R.styleable.CustomDatePicker_dateTextColor, Color.BLACK));
        } finally {
            a.recycle();
        }
    }

    private void setDate() {
        date.set(DateUtils.convertToString(mCalendar.getTime(), DateUtils.CALENDAR_DATE_FORMAT_DD_MM_YY,
                null));
    }

    public long getDateInMilis() {
        return mCalendar.getTimeInMillis();
    }

    public String getStringDate() {
        return DateUtils.timestampToString(mCalendar.getTimeInMillis(),
                DateUtils.CALENDAR_DATE_FORMAT_DD_MM_YY_HH, null);
    }

    public void setDateString(String dateString) {
        Date date = DateUtils.stringToDate(dateString, DateUtils.CALENDAR_DATE_FORMAT_DD_MM_YY,
                Locale.getDefault());
        mCalendar.setTime(date);
        setDate();
    }
}
