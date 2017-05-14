package com.viettel.mbccs.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.DimenRes;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.viettel.mbccs.R;
import com.viettel.mbccs.widget.EndlessRecyclerOnScrollListener;

import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.TableHeaderAdapter;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.SwipeToRefreshListener;
import de.codecrafters.tableview.model.TableColumnModel;

/**
 * Created by FRAMGIA\bui.dinh.viet on 09/02/2017.
 */

public class BindingUtils {

    @BindingAdapter(value = {
            "android:recyclerAdapter", "columns", "orientation", "space", "includeEdge",
            "haveDivider"
    }, requireAll = false)
    public static void setRecyclerViewData(RecyclerView recyclerView, RecyclerView.Adapter adapter,
                                           int columns, int orientation, @DimenRes int space, boolean includeEdge,
                                           boolean haveDivider) {
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

    @BindingAdapter({"android:font"})
    public static void setFont(TextView textView, String fontName) {
        textView.setTypeface(FontCache.getFont(fontName, textView.getContext()));
    }

    @BindingAdapter(value = {"loadCircleImage", "placeholder", "cacheSource"}, requireAll = false)
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
        if (TextUtils.isEmpty(url) && placeHolder == null) {
            return;
        }
        DrawableRequestBuilder<String> requestBuilder = Glide.with(imageView.getContext())
                .load(url)
                .diskCacheStrategy(
                        isCacheSource ? DiskCacheStrategy.SOURCE : DiskCacheStrategy.RESULT)
                .placeholder(placeHolder);
        if (centerCrop) {
            requestBuilder.centerCrop();
        }
        requestBuilder.into(imageView);
    }

    @BindingAdapter({"viewSelected"})
    public static void setViewSelected(View view, boolean isSelected) {
        view.setSelected(isSelected);
    }

    @BindingAdapter(("android:pagerAdapter"))
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

    @BindingAdapter({"setOnLoadMore"})
    public static void setOnLoadMore(RecyclerView recyclerView,
                                     EndlessRecyclerOnScrollListener onScrollListener) {
        recyclerView.addOnScrollListener(onScrollListener);
    }

    @BindingAdapter({"currentItem"})
    public static void setCurrentItem(ViewPager viewPager, int currentItem) {
        viewPager.setCurrentItem(currentItem);
    }

    @BindingAdapter(value = {"pageChangedListener"})
    public static void setOnPageChangedListener(ViewPager viewPager,
                                                ViewPager.OnPageChangeListener listener) {
        viewPager.addOnPageChangeListener(listener);
    }

    @BindingAdapter({"onLoadUrl"})
    public static void setWebViewClient(WebView webView, String url) {
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.loadUrl(url);
    }

    @BindingAdapter({"columnCount"})
    public static void setTableViewColumnCount(TableView tableView, int count) {
        tableView.setColumnCount(count);
    }

    @BindingAdapter({"dataAdapter"})
    public static void setTableViewAdapter(TableView tableView, TableDataAdapter dataAdapter) {
        tableView.setDataAdapter(dataAdapter);
    }

    @BindingAdapter({"headerAdapter"})
    public static void setTableViewHeaderAdapter(TableView tableView, TableHeaderAdapter headerAdapter) {
        tableView.setHeaderAdapter(headerAdapter);
    }

    @BindingAdapter({"columnModel"})
    public static void setTableViewColumnModel(TableView tableView, TableColumnModel columnModel) {
        tableView.setColumnModel(columnModel);
    }

    @BindingAdapter({"swipeRefreshEnable"})
    public static void setTableViewSRE(TableView tableView, boolean enable) {
        tableView.setSwipeToRefreshEnabled(enable);
    }

    @BindingAdapter({"swipeRefreshListener"})
    public static void setTableViewSRL(TableView tableView, SwipeToRefreshListener listener) {
        tableView.setSwipeToRefreshListener(listener);
    }

    @BindingAdapter({"emptyView"})
    public static void setTableViewEmptyView(TableView tableView, View view) {
        tableView.setEmptyDataIndicatorView(view);
    }
}
