package com.viettel.mbccs.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.viettel.mbccs.R;

import java.util.concurrent.TimeUnit;

public class RangeSliderView extends View {

    private static final long RIPPLE_ANIMATION_DURATION_MS = TimeUnit.MILLISECONDS.toMillis(700);

    private static final int DEFAULT_PAINT_STROKE_WIDTH = 5;

    private static final int DEFAULT_FILLED_COLOR = Color.parseColor("#FFA500");

    private static final int DEFAULT_EMPTY_COLOR = Color.parseColor("#C3C3C3");

    private static final float DEFAULT_BAR_HEIGHT_PERCENT = 0.10f;

    private static final float DEFAULT_SLOT_RADIUS_PERCENT = 0.125f;

    private static final float DEFAULT_SLIDER_RADIUS_PERCENT = 0.25f;

    private static final int DEFAULT_RANGE_COUNT = 5;

    private static final int DEFAULT_HEIGHT_IN_DP = 50;

    protected Paint mPaint;

    protected Paint mRipplePaint;

    protected float mRadius;

    protected float mSlotRadius;

    private int mCurrentIndex;

    private float mCurrentSlidingX;

    private float mCurrentSlidingY;

    private float mSelectedSlotX;

    private float mSelectedSlotY;

    private boolean isGotSlot = false;

    private float[] mSlotPositions;

    private int mFilledColor = DEFAULT_FILLED_COLOR;

    private int mEmptyColor = DEFAULT_EMPTY_COLOR;

    private float mBarHeightPercent = DEFAULT_BAR_HEIGHT_PERCENT;

    private int rangeCount = DEFAULT_RANGE_COUNT;

    private int mBarHeight;

    private OnSlideListener mListener;

    private float mRippleRadius = 0.0f;

    private float mDownX;

    private float mDownY;

    private Path mInnerPath = new Path();

    private Path mOuterPath = new Path();

    private float mSlotRadiusPercent = DEFAULT_SLOT_RADIUS_PERCENT;

    private float mSliderRadiusPercent = DEFAULT_SLIDER_RADIUS_PERCENT;

    private int mLayoutHeight;

    public RangeSliderView(Context context) {
        this(context, null);
    }

    public RangeSliderView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public RangeSliderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RangeSliderView);
            TypedArray sa = context.obtainStyledAttributes(attrs, new int[]{android.R.attr.layout_height});
            try {
                mLayoutHeight = sa.getLayoutDimension(
                        0, 0);
                rangeCount = a.getInt(
                        R.styleable.RangeSliderView_rangeCount, DEFAULT_RANGE_COUNT);
                mFilledColor = a.getColor(
                        R.styleable.RangeSliderView_filledColor, DEFAULT_FILLED_COLOR);
                mEmptyColor = a.getColor(
                        R.styleable.RangeSliderView_emptyColor, DEFAULT_EMPTY_COLOR);
                mBarHeightPercent = a.getFloat(
                        R.styleable.RangeSliderView_barHeightPercent, DEFAULT_BAR_HEIGHT_PERCENT);
                mBarHeightPercent = a.getFloat(
                        R.styleable.RangeSliderView_barHeightPercent, DEFAULT_BAR_HEIGHT_PERCENT);
                mSlotRadiusPercent = a.getFloat(
                        R.styleable.RangeSliderView_slotRadiusPercent, DEFAULT_SLOT_RADIUS_PERCENT);
                mSliderRadiusPercent = a.getFloat(
                        R.styleable.RangeSliderView_sliderRadiusPercent, DEFAULT_SLIDER_RADIUS_PERCENT);
            } finally {
                a.recycle();
                sa.recycle();
            }
        }

        setmBarHeightPercent(mBarHeightPercent);
        setRangeCount(rangeCount);
        setmSlotRadiusPercent(mSlotRadiusPercent);
        setmSliderRadiusPercent(mSliderRadiusPercent);

        mSlotPositions = new float[rangeCount];
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(DEFAULT_PAINT_STROKE_WIDTH);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mRipplePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRipplePaint.setStrokeWidth(2.0f);
        mRipplePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);

                // Update mRadius after we got new height
                updateRadius(getHeight());

                // Compute drawing position again
                preComputeDrawingPosition();

                // Ready to draw now
                return true;
            }
        });
        mCurrentIndex = 0;
    }

    private void updateRadius(int height) {
        mBarHeight = (int) (height * mBarHeightPercent);
        mRadius = height * mSliderRadiusPercent;
        mSlotRadius = height * mSlotRadiusPercent;
    }

    public int getRangeCount() {
        return rangeCount;
    }

    public void setRangeCount(int rangeCount) {
        if (rangeCount < 2) {
            throw new IllegalArgumentException("rangeCount must be >= 2");
        }
        this.rangeCount = rangeCount;
    }

    public float getmBarHeightPercent() {
        return mBarHeightPercent;
    }

    public void setmBarHeightPercent(float percent) {
        if (percent <= 0.0 || percent > 1.0) {
            throw new IllegalArgumentException("Bar height percent must be in (0, 1]");
        }
        this.mBarHeightPercent = percent;
    }

    public float getmSlotRadiusPercent() {
        return mSlotRadiusPercent;
    }

    public void setmSlotRadiusPercent(float percent) {
        if (percent <= 0.0 || percent > 1.0) {
            throw new IllegalArgumentException("Slot mRadius percent must be in (0, 1]");
        }
        this.mSlotRadiusPercent = percent;
    }

    public float getmSliderRadiusPercent() {
        return mSliderRadiusPercent;
    }

    public void setmSliderRadiusPercent(float percent) {
        if (percent <= 0.0 || percent > 1.0) {
            throw new IllegalArgumentException("Slider mRadius percent must be in (0, 1]");
        }
        this.mSliderRadiusPercent = percent;
    }

    @AnimateMethod
    public void setmRadius(final float mRadius) {
        mRippleRadius = mRadius;
        if (mRippleRadius > 0) {
            RadialGradient radialGradient = new RadialGradient(
                    mDownX,
                    mDownY,
                    mRippleRadius * 3,
                    Color.TRANSPARENT,
                    Color.BLACK,
                    Shader.TileMode.MIRROR
            );
            mRipplePaint.setShader(radialGradient);
        }
        invalidate();
    }

    public void setOnSlideListener(OnSlideListener listener) {
        this.mListener = listener;
    }

    /**
     * Perform all the calculation before drawing, should only run once
     */
    private void preComputeDrawingPosition() {
        int w = getWidthWithPadding();
        int h = getHeightWithPadding();

        /** Space between each slot */
        int spacing = w / rangeCount;

        /** Center vertical */
        int y = getPaddingTop() + h / 2;
        mCurrentSlidingY = y;
        mSelectedSlotY = y;
        /**
         * Try to center it, so start by half
         * <pre>
         *
         *  Example for 4 slots
         *
         *  ____o____|____o____|____o____|____o____
         *  --space--
         *
         * </pre>
         */
        int x = getPaddingLeft() + (spacing / 2);

        /** Store the position of each slot index */
        for (int i = 0; i < rangeCount; ++i) {
            mSlotPositions[i] = x;
            if (i == mCurrentIndex) {
                mCurrentSlidingX = x;
                mSelectedSlotX = x;
            }
            x += spacing;
        }
    }

    public void setInitialIndex(int index) {
        if (index < 0 || index >= rangeCount) {
            throw new IllegalArgumentException("Attempted to set index=" + index + " out of range [0," + rangeCount + "]");
        }
        mCurrentIndex = index;
        mCurrentSlidingX = mSelectedSlotX = mSlotPositions[mCurrentIndex];
        invalidate();
    }

    public int getmFilledColor() {
        return mFilledColor;
    }

    public void setmFilledColor(int mFilledColor) {
        this.mFilledColor = mFilledColor;
        invalidate();
    }

    public int getmEmptyColor() {
        return mEmptyColor;
    }

    public void setmEmptyColor(int mEmptyColor) {
        this.mEmptyColor = mEmptyColor;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    /**
     * Measures height according to the passed measure spec
     *
     * @param measureSpec int measure spec to use
     * @return int pixel size
     */
    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result;
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            final int height;
            if (mLayoutHeight == ViewGroup.LayoutParams.WRAP_CONTENT) {
                height = dpToPx(getContext(), DEFAULT_HEIGHT_IN_DP);
            } else if (mLayoutHeight == ViewGroup.LayoutParams.MATCH_PARENT) {
                height = getMeasuredHeight();
            } else {
                height = mLayoutHeight;
            }
            result = height + getPaddingTop() + getPaddingBottom() + (2 * DEFAULT_PAINT_STROKE_WIDTH);
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * Measures width according to the passed measure spec
     *
     * @param measureSpec int measure spec to use
     * @return int pixel size
     */
    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result;
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = specSize + getPaddingLeft() + getPaddingRight() + (2 * DEFAULT_PAINT_STROKE_WIDTH) + (int) (2 * mRadius);
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private void updateCurrentIndex() {
        float min = Float.MAX_VALUE;
        int j = 0;
        /** Find the closest to x */
        for (int i = 0; i < rangeCount; ++i) {
            float dx = Math.abs(mCurrentSlidingX - mSlotPositions[i]);
            if (dx < min) {
                min = dx;
                j = i;
            }
        }
        /** This is current index of slider */
        if (j != mCurrentIndex) {
            if (mListener != null) {
                mListener.onSlide(j);
            }
        }
        mCurrentIndex = j;
        /** Correct position */
        mCurrentSlidingX = mSlotPositions[j];
        mSelectedSlotX = mCurrentSlidingX;
        mDownX = mCurrentSlidingX;
        mDownY = mCurrentSlidingY;
        animateRipple();
        invalidate();
    }

    private void animateRipple() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "mRadius", 0, mRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(RIPPLE_ANIMATION_DURATION_MS);
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mRippleRadius = 0;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        float x = event.getX();
        final int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isGotSlot = isInSelectedSlot(x, y);
                mDownX = x;
                mDownY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                if (isGotSlot) {
                    if (x >= mSlotPositions[0] && x <= mSlotPositions[rangeCount - 1]) {
                        mCurrentSlidingX = x;
                        mCurrentSlidingY = y;
                        invalidate();
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                if (isGotSlot) {
                    isGotSlot = false;
                    mCurrentSlidingX = x;
                    mCurrentSlidingY = y;
                    updateCurrentIndex();
                }
                break;
        }
        return true;
    }

    private boolean isInSelectedSlot(float x, float y) {
        return
                mSelectedSlotX - mRadius <= x && x <= mSelectedSlotX + mRadius &&
                        mSelectedSlotY - mRadius <= y && y <= mSelectedSlotY + mRadius;
    }

    private void drawEmptySlots(Canvas canvas) {
        mPaint.setColor(mEmptyColor);
        int h = getHeightWithPadding();
        int y = getPaddingTop() + (h >> 1);
        for (int i = 0; i < rangeCount; ++i) {
            canvas.drawCircle(mSlotPositions[i], y, mSlotRadius, mPaint);
        }
    }

    public int getHeightWithPadding() {
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }

    public int getWidthWithPadding() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    private void drawFilledSlots(Canvas canvas) {
        mPaint.setColor(mFilledColor);
        int h = getHeightWithPadding();
        int y = getPaddingTop() + (h >> 1);
        for (int i = 0; i < rangeCount; ++i) {
            if (mSlotPositions[i] <= mCurrentSlidingX) {
                canvas.drawCircle(mSlotPositions[i], y, mSlotRadius, mPaint);
            }
        }
    }

    private void drawBar(Canvas canvas, int from, int to, int color) {
        mPaint.setColor(color);
        int h = getHeightWithPadding();
        int half = (mBarHeight >> 1);
        int y = getPaddingTop() + (h >> 1);
        canvas.drawRect(from, y - half, to, y + half, mPaint);
    }

    private void drawRippleEffect(Canvas canvas) {
        if (mRippleRadius != 0) {
            canvas.save();
            mRipplePaint.setColor(Color.GRAY);
            mOuterPath.reset();
            mOuterPath.addCircle(mDownX, mDownY, mRippleRadius, Path.Direction.CW);
            canvas.clipPath(mOuterPath);
            mInnerPath.reset();
            mInnerPath.addCircle(mDownX, mDownY, mRippleRadius / 3, Path.Direction.CW);
            canvas.clipPath(mInnerPath, Region.Op.DIFFERENCE);
            canvas.drawCircle(mDownX, mDownY, mRippleRadius, mRipplePaint);
            canvas.restore();
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getWidthWithPadding();
        int h = getHeightWithPadding();
        int spacing = w / rangeCount;
        int border = (spacing >> 1);
        int x0 = getPaddingLeft() + border;
        int y0 = getPaddingTop() + (h >> 1);
        drawEmptySlots(canvas);
        drawFilledSlots(canvas);

        /** Draw empty bar */
        drawBar(canvas, (int) mSlotPositions[0], (int) mSlotPositions[rangeCount - 1], mEmptyColor);

        /** Draw filled bar */
        drawBar(canvas, x0, (int) mCurrentSlidingX, mFilledColor);

        /** Draw the selected range circle */
        mPaint.setColor(mFilledColor);
        canvas.drawCircle(mCurrentSlidingX, y0, mRadius, mPaint);
        drawRippleEffect(canvas);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.saveIndex = this.mCurrentIndex;
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        this.mCurrentIndex = ss.saveIndex;
    }

    static class SavedState extends BaseSavedState {
        int saveIndex;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.saveIndex = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.saveIndex);
        }

        //required field that makes Parcelables from a Parcel
        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }

    /**
     * Helper method to convert pixel to dp
     *
     * @param context
     * @param px
     * @return
     */
    static int pxToDp(final Context context, final float px) {
        return (int) (px / context.getResources().getDisplayMetrics().density);
    }

    /**
     * Helper method to convert dp to pixel
     *
     * @param context
     * @param dp
     * @return
     */
    static int dpToPx(final Context context, final float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    /**
     * Interface to keep track sliding position
     */
    public interface OnSlideListener {

        /**
         * Notify when slider change to new index position
         *
         * @param index The index value of range count [0, rangeCount - 1]
         */
        void onSlide(int index);
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface AnimateMethod {
    }
}

