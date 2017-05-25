package com.viettel.mbccs.screen.map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MapWrapperLayout extends RelativeLayout {

    private OnListenerUpdateMarker mOnListenerUpdateMarker;
    private GestureDetector mDoubleTapDetecture;
    private OnTouchListener mGestureListener;
    private boolean mIsScrolling;

    public MapWrapperLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MapWrapperLayout(Context context) {
        super(context);
        init(context);
    }

    public MapWrapperLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mDoubleTapDetecture = new GestureDetector(context, new GestureListener());
        mGestureListener = new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mDoubleTapDetecture.onTouchEvent(event)) {
                    return true;
                }
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mIsScrolling && mOnListenerUpdateMarker != null) {
                            mIsScrolling = false;
                            mOnListenerUpdateMarker.onUpdateMarker(true);
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        };
        setOnTouchListener(mGestureListener);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        mDoubleTapDetecture.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mOnListenerUpdateMarker != null) {
                    mOnListenerUpdateMarker.onUpdateMarker(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mOnListenerUpdateMarker != null) {
                    mOnListenerUpdateMarker.onUpdateMarker(true);
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if (mOnListenerUpdateMarker != null) {
                    mOnListenerUpdateMarker.onUpdateMarker(false);
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                if (mOnListenerUpdateMarker != null) {
                    mOnListenerUpdateMarker.onUpdateMarker(true);
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    public void setOnListenerUpdateMarker(OnListenerUpdateMarker mOnListenerUpdateMarker) {
        this.mOnListenerUpdateMarker = mOnListenerUpdateMarker;
    }

    public interface OnListenerUpdateMarker {
        void onUpdateMarker(boolean mMapIsTouched);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            mIsScrolling = true;
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }
}