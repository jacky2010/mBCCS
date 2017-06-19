package com.viettel.mbccs.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.percent.PercentLayoutHelper;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.Html;
import android.text.InputType;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.MovementMethod;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.txusballesteros.widgets.FitChart;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.Function;
import com.viettel.mbccs.widget.BottomNavigationView;
import com.viettel.mbccs.widget.CustomEdittext;
import com.viettel.mbccs.widget.EndlessRecyclerOnScrollListener;
import com.viettel.mbccs.widget.SpinnerWithBorder;
import com.viettel.mbccs.widget.callback.DrawableClickListener;
import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.TableHeaderAdapter;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.SwipeToRefreshListener;
import de.codecrafters.tableview.model.TableColumnModel;
import java.util.List;

/**
 * Created by FRAMGIA\bui.dinh.viet on 09/02/2017.
 */

public class BindingUtils {

    @BindingAdapter(value = {
            "android:recyclerAdapter", "columns", "orientation", "space"
    }, requireAll = false)
    public static void setRecyclerViewData(RecyclerView recyclerView, RecyclerView.Adapter adapter,
            int columns, int orientation, @DimenRes int space) {
        boolean isGrid = columns > 1;
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager;
        RecyclerView.ItemDecoration itemDecoration;
        Context context = recyclerView.getContext();
        if (isGrid) {
            layoutManager = new GridLayoutManager(context, columns);
        } else {
            layoutManager = new LinearLayoutManager(context,
                    orientation == context.getResources().getInteger(R.integer.vertical)
                            ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL, false);
        }
        recyclerView.setLayoutManager(layoutManager);
    }

    @BindingAdapter({ "android:font" })
    public static void setFont(TextView textView, String fontName) {
        textView.setTypeface(FontCache.getFont(fontName, textView.getContext()));
    }

    @BindingAdapter(value = { "loadCircleImage", "placeholder", "cacheSource" }, requireAll = false)
    public static void loadCircleImage(ImageView img, String url, Drawable placeHolder,
            boolean isCacheSource) {
        if (TextUtils.isEmpty(url) && placeHolder == null) {
            return;
        }
        Glide.with(img.getContext())
                .load(url)
                .diskCacheStrategy(
                        isCacheSource ? DiskCacheStrategy.SOURCE : DiskCacheStrategy.RESULT)
                .placeholder(placeHolder)
                .centerCrop()
                .bitmapTransform(new CircleTransform(img.getContext()))
                .into(img);
    }

    @BindingAdapter(value = {
            "loadImage", "placeHolder", "cacheSource", "centerCrop"
    }, requireAll = false)
    public static void loadImage(ImageView imageView, String url, Drawable placeHolder,
            boolean isCacheSource, boolean centerCrop) {
        DrawableRequestBuilder<String> requestBuilder = Glide.with(imageView.getContext())
                .load(url)
                .diskCacheStrategy(
                        isCacheSource ? DiskCacheStrategy.SOURCE : DiskCacheStrategy.RESULT)
                .placeholder(placeHolder == null ? imageView.getResources()
                        .getDrawable(R.drawable.no_image) : placeHolder);
        if (centerCrop) {
            requestBuilder.centerCrop();
        }
        requestBuilder.into(imageView);
    }

    @BindingAdapter({ "viewSelected" })
    public static void setViewSelected(View view, boolean isSelected) {
        view.setSelected(isSelected);
    }

    @BindingAdapter(("pagerAdapter"))
    public static void setPagerAdapter(ViewPager viewPager, PagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter(("viewPager"))
    public static void setViewPager(TabLayout tabLayout, ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @BindingAdapter(("bindText"))
    public static void setText(TextView view, @StringRes int stringId) {
        view.setText(Html.fromHtml(view.getContext().getResources().getString(stringId)));
    }

    @BindingAdapter(("viewActivate"))
    public static void setActivateState(View view, boolean isActivate) {
        view.setActivated(isActivate);
    }

    /**
     * binding data numbers message inbox
     */
    @BindingAdapter(("numMessage"))
    public static void setNumMessage(TextView text, int nums) {
        if (nums > 0) {
            text.setText(String.valueOf(nums));
            text.setVisibility(View.VISIBLE);
        } else {
            text.setVisibility(View.GONE);
        }
    }

    @BindingAdapter({ "setOnLoadMore" })
    public static void setOnLoadMore(RecyclerView recyclerView,
            EndlessRecyclerOnScrollListener onScrollListener) {
        recyclerView.addOnScrollListener(onScrollListener);
    }

    @BindingAdapter(value = { "currentItem", "animation" }, requireAll = false)
    public static void setCurrentItem(ViewPager viewPager, int currentItem, boolean animation) {
        viewPager.setCurrentItem(currentItem, animation);
    }

    @BindingAdapter({ "currentItem" })
    public static void setCurrentItem(ViewPager viewPager, int currentItem) {
        viewPager.setCurrentItem(currentItem);
    }

    @BindingAdapter(value = { "pageChangedListener" })
    public static void setOnPageChangedListener(ViewPager viewPager,
            ViewPager.OnPageChangeListener listener) {
        viewPager.addOnPageChangeListener(listener);
    }

    @BindingAdapter({ "onLoadUrl" })
    public static void setWebViewClient(WebView webView, String url) {
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.loadUrl(url);
    }

    @BindingAdapter({ "columnCount" })
    public static void setTableViewColumnCount(TableView tableView, int count) {
        tableView.setColumnCount(count);
    }

    @BindingAdapter({ "dataAdapter" })
    public static void setTableViewAdapter(TableView tableView, TableDataAdapter dataAdapter) {
        tableView.setDataAdapter(dataAdapter);
    }

    @BindingAdapter({ "headerAdapter" })
    public static void setTableViewHeaderAdapter(TableView tableView,
            TableHeaderAdapter headerAdapter) {
        tableView.setHeaderAdapter(headerAdapter);
    }

    @BindingAdapter({ "columnModel" })
    public static void setTableViewColumnModel(TableView tableView, TableColumnModel columnModel) {
        tableView.setColumnModel(columnModel);
    }

    @BindingAdapter({ "swipeRefreshEnable" })
    public static void setTableViewSRE(TableView tableView, boolean enable) {
        tableView.setSwipeToRefreshEnabled(enable);
    }

    @BindingAdapter({ "swipeRefreshListener" })
    public static void setTableViewSRL(TableView tableView, SwipeToRefreshListener listener) {
        tableView.setSwipeToRefreshListener(listener);
    }

    @BindingAdapter({ "emptyView" })
    public static void setTableViewEmptyView(TableView tableView, View view) {
        tableView.setEmptyDataIndicatorView(view);
    }

    @BindingAdapter({ "android:src" })
    public static void setImageResource(ImageView imageview, int resource) {
        imageview.setImageResource(resource);
    }

    @BindingAdapter({ "imageDrawable" })
    public static void setImageDrawable(ImageView imageview, Drawable resource) {
        Glide.with(imageview.getContext())
                .load(resource)
                .centerCrop()
                .bitmapTransform(new CircleTransform(imageview.getContext()))
                .into(imageview);
    }

    @BindingAdapter({ "imageBitmap" })
    public static void setImageBitmap(ImageView imageview, Drawable resource) {
        Glide.with(imageview.getContext())
                .load(resource)
                .centerCrop()
                .bitmapTransform(new CircleTransform(imageview.getContext()))
                .into(imageview);
    }

    @BindingAdapter({ "textHtml" })
    public static void setText(TextView text, String html) {
        if (TextUtils.isEmpty(html)) {
            return;
        }
        text.setText(Html.fromHtml(html));
    }

    @BindingAdapter({ "textUnderLine" })
    public static void setTextUnderLine(TextView textUnderLine, String text) {
        SpannableString content = new SpannableString(text);
        content.setSpan(new UnderlineSpan(), 0, text.length(), 0);
        textUnderLine.setText(content);
    }

    @BindingAdapter(value = { "android:adapter", "android:divider" }, requireAll = false)
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter,
            boolean divider) {
        recyclerView.setLayoutManager(
                new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL,
                        false));
        recyclerView.setHasFixedSize(true);
        if (divider) {
            recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(30));
        }
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter(value = {
            "recyclerAdapter", "layoutManager", "spanSizeLookup", "decoration"
    }, requireAll = false)
    public static void setRecyclerAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter,
            RecyclerView.LayoutManager layoutManager,
            GridLayoutManager.SpanSizeLookup spanSizeLookup,
            RecyclerView.ItemDecoration decoration) {
        if (layoutManager instanceof GridLayoutManager && spanSizeLookup != null) {
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(spanSizeLookup);
        }
        if (decoration != null) {
            recyclerView.addItemDecoration(decoration);
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("layout_widthPercent")
    public static void setWidthPercent(View view, float width) {
        try {
            PercentLayoutHelper.PercentLayoutParams params =
                    (PercentLayoutHelper.PercentLayoutParams) view.getLayoutParams();
            PercentLayoutHelper.PercentLayoutInfo info = params.getPercentLayoutInfo();
            info.widthPercent = width / 100;
        } catch (ClassCastException ignored) {
        }
    }

    @BindingAdapter({ "bottomItemClickListener" })
    public static void setBottomItemClickListener(BottomNavigationView view,
            BottomNavigationView.OnBottomItemClick listener) {
        view.setOnBottomItemClick(listener);
    }

    @BindingAdapter("layout_height")
    public static void setLayoutHeight(View view, float height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) height;
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("movementMethod")
    public static void setMovementMethod(TextView view, MovementMethod method) {
        view.setMovementMethod(method);
    }

    @BindingAdapter("value")
    public static void setFitChartValue(FitChart chart, float value) {
        chart.setValue(value);
    }

    @BindingAdapter({ "bottomListItem" })
    public static void setBottomListItem(BottomNavigationView view, List<Function> list) {
        view.setBottomListItem(list);
    }

    @BindingAdapter({ "eyeClickActive" })
    public static void enableEyeClick(EditText inputView, boolean isEnable) {
        if (isEnable) {
            inputView.setInputType(
                    InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            return;
        }
        inputView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    }

    @BindingAdapter({ "setSpinnerAdapter" })
    public static void setSpinnerAdapter(Spinner view, ArrayAdapter adapter) {
        view.setAdapter(adapter);
    }

    @BindingAdapter({ "spinnerClickItem" })
    public static void spinnerClickItem(Spinner view, AdapterView.OnItemSelectedListener listener) {
        view.setOnItemSelectedListener(listener);
    }

    @BindingAdapter(value = {
            "drawableStart", "drawableTop", "drawableEnd", "drawableBottom"
    }, requireAll = false)
    public static void setDrawableStart(TextView view, Drawable left, Drawable top, Drawable right,
            Drawable bottom) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            view.setCompoundDrawablesRelativeWithIntrinsicBounds(left, top, right, bottom);
        } else {
            view.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        }
    }

    @BindingAdapter({ "addItemDecoration" })
    public static void addItemDecoration(RecyclerView view, RecyclerView.ItemDecoration decor) {
        view.addItemDecoration(decor);
    }

    @BindingAdapter("bindText")
    public static void setTextScroll(TextView textView, String text) {
        textView.setText(text);
        textView.setSelected(true);
    }


    @BindingAdapter("setSpinnerSelection")
    public static void setSpinnerSelection(Spinner spinner, int position) {
        spinner.setSelection(position);
    }

    @BindingAdapter("onCheckedChangedListener")
    public static void setOnCheckedChangedListener(SwitchCompat v,
            CompoundButton.OnCheckedChangeListener listener) {
        v.setOnCheckedChangeListener(listener);
    }

    @BindingAdapter("selected")
    public static void setTextSelected(TextView textView, boolean isSelected) {
        textView.setSelected(isSelected);
    }

    @BindingAdapter("position")
    public static void setPosition(Spinner spinner, int position) {
        spinner.setSelection(position);
    }

    @BindingAdapter("error")
    public static void setTextError(EditText textView, String error) {
        if (error != null) {
            textView.requestFocus();
        }
        textView.setError(error);
    }

    @BindingAdapter({ "loadUrlWebView" })
    public static void loadUrl(WebView view, String url) {
        view.loadUrl(url);
    }

    @BindingAdapter({ "contentHtmlGray" })
    public static void setContentHtml(WebView view, String content) {
        view.setBackgroundColor(Color.TRANSPARENT);
        view.loadData(content, "text/html; charset=utf-8", "utf-8");
    }

    @BindingAdapter({ "setBackgroundColor" })
    public static void setBackgroundColor(View v, @ColorRes int color) {
        v.setBackground(new ColorDrawable(color));
    }

    @BindingAdapter("requestFocus")
    public static void requestFocus(TextView textView, boolean isFocus) {
        textView.requestFocus();
    }

    @BindingAdapter({ "animatedProgress" })
    public static void setAnimatedProgress(ProgressBar pb, int progress) {
        AnimationUtils.startAnimatedProgress(pb, progress);
    }

    @BindingAdapter({ "touchListener" })
    public static void setTouchListener(View view, View.OnTouchListener listener) {
        view.setOnTouchListener(listener);
    }

    @BindingAdapter("customEditTextDrawable")
    public static void setCustomEditTextDrawable(CustomEdittext v, DrawableClickListener listener) {
        v.setDrawableClickListener(listener);
    }

    /***
     * Binding data for spinner with border
     * @param spinnerWithBorder
     * @param adapter
     * @param position
     * @param drawable
     * @param textHint
     * @param useHintValue
     */
    @BindingAdapter(value = {
            "adapter", "position", "leftIcon", "textHint", "useHintValue"
    }, requireAll = false)
    public static void bindData(SpinnerWithBorder spinnerWithBorder, BaseAdapter adapter,
            int position, Drawable drawable, String textHint, boolean useHintValue) {
        if (adapter instanceof SpinnerAdapter) {
            ((SpinnerAdapter) adapter).setSpinner(spinnerWithBorder.getSpinner());
            if (!TextUtils.isEmpty(textHint)) {
                ((SpinnerAdapter) adapter).setTextHint(textHint);
                ((SpinnerAdapter) adapter).setUsehintValue(useHintValue);
            }
        }
        spinnerWithBorder.getSpinner().setAdapter(adapter);
        spinnerWithBorder.getSpinner().setSelection(position);
        if (drawable != null) {
            spinnerWithBorder.getImageView().setVisibility(View.VISIBLE);
            spinnerWithBorder.getImageView().setImageDrawable(drawable);
        } else {
            spinnerWithBorder.getImageView().setVisibility(View.GONE);
        }
    }

    @BindingAdapter(value = {
            "adapter", "position", "textHint", "useHintValue"
    }, requireAll = false)
    public static void bindData(Spinner spinnerWithBorder, BaseAdapter adapter,
            int position, String textHint, boolean useHintValue) {
        if (adapter instanceof SpinnerAdapter) {
            ((SpinnerAdapter) adapter).setSpinner(spinnerWithBorder);
            if (!TextUtils.isEmpty(textHint)) {
                ((SpinnerAdapter) adapter).setTextHint(textHint);
                ((SpinnerAdapter) adapter).setUsehintValue(useHintValue);
            }
        }
        spinnerWithBorder.setAdapter(adapter);
        spinnerWithBorder.setSelection(position);
    }
}