package com.viettel.mbccs.screen.main.fragments.main;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.HomeModel;
import com.viettel.mbccs.widget.SpacesItemDecoration;

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
        list.add(new HomeModel("Đơn đặt hàng mới", "20", 20, 0));
        list.add(new HomeModel("Tiến độ thu cước", "tile3", 30, 1));
        list.add(new HomeModel("Chỉ tiêu đấu nối", "tile4", 50, 2));
        list.add(new HomeModel("Chỉ tiêu bán hàng", "tile6", 90, 2));
        list.add(new HomeModel("4500$", "3700$", 40, 3));
        list.add(new HomeModel("1000$", "3000$", 100, 3));
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
}
