package com.viettel.mbccs.screen.main.fragments.main;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.HomeModel;
import java.util.ArrayList;
import java.util.List;

import static com.viettel.mbccs.screen.main.fragments.main.MainRecyclerAdapter.TYPE_TEXT_COMPLEX;

/**
 * Created by eo_cuong on 5/11/17.
 */

public class MainFragmentPresenter implements MainFragmentContract.Presenter {

    private Context mContext;

    private MainFragmentContract.ViewModel mViewModel;

    private MainRecyclerAdapter mRecyclerAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    public MainFragmentPresenter(Context context, MainFragmentContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        List<HomeModel> list = new ArrayList<>();
        list.add(new HomeModel("abcd", "tile2", 20, 0));
        list.add(new HomeModel("abc", "tile3", 30, 1));
        list.add(new HomeModel("abcd12", "tile4", 50, 2));
        list.add(new HomeModel("abcd131", "tile6", 90, 2));
        list.add(new HomeModel("abcd41", "tile5", 40, 3));
        list.add(new HomeModel("abcd515", "tile7", 100, 3));
        mRecyclerAdapter = new MainRecyclerAdapter(mContext, list);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public RecyclerView.Adapter getRecyclerAdapter() {
        return mRecyclerAdapter;
    }

    public synchronized RecyclerView.LayoutManager getLayoutManager() {
        if (mLayoutManager == null) mLayoutManager = new GridLayoutManager(mContext, 2);
        return mLayoutManager;
    }

    public GridLayoutManager.SpanSizeLookup getSpanSizeLookup() {
        return new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mRecyclerAdapter.getItemViewType(position)) {
                    case TYPE_TEXT_COMPLEX:
                        return 2;
                    default:
                        return 1;
                }
            }
        };
    }

    public RecyclerView.ItemDecoration getRecyclerDecoration() {
        return new SpacesItemDecoration((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                mContext.getResources().getDimension(R.dimen.dp_6),
                mContext.getResources().getDisplayMetrics()), getLayoutManager());
    }

    public static class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        private RecyclerView.LayoutManager layoutManager;

        public SpacesItemDecoration(int space, RecyclerView.LayoutManager layoutManager) {
            this.space = space;
            this.layoutManager = layoutManager;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            outRect.top = 0;

            if (layoutManager instanceof GridLayoutManager) {
                for (int i = 0; i < ((GridLayoutManager) layoutManager).getSpanCount(); i++) {
                    if (parent.getChildLayoutPosition(view) == i) {
                        outRect.top = space;
                    }
                }
            } else {
                if (parent.getChildLayoutPosition(view) == 0) {
                    outRect.top = space;
                }
            }
        }
    }
}
