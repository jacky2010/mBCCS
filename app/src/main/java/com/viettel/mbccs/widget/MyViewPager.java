package com.viettel.mbccs.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.viettel.mbccs.R;

@BindingMethods({
        @BindingMethod(type = MyViewPager.class, attribute = "onPageSelected", method = "setOnPageSelected"),
        @BindingMethod(type = MyViewPager.class, attribute = "onPageScrollEnd", method = "setOnPageScrollEnd")})
public class MyViewPager extends ViewPager {
    private float mStartDragX;
    private boolean isDisableSwipe;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyViewPager);
        isDisableSwipe = a.getBoolean(R.styleable.MyViewPager_isDisableSwipe, false);
    }

    public void setOnPageSelected(final OnPageListener listener) {
        this.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                listener.onPageSelected(position);
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isDisableSwipe) return false;
        switch (ev.getAction() & MotionEventCompat.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mStartDragX = ev.getX();
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isDisableSwipe) return false;
        return super.onTouchEvent(ev);
    }

    public interface OnPageListener {
        void onPageSelected(int position);
    }

    public interface OnPageScrolledListener {
        void onPageScrollEnd();
    }
}
