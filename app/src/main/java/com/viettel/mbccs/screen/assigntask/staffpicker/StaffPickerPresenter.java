package com.viettel.mbccs.screen.assigntask.staffpicker;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseSearchListPickerPresenter;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;
import com.viettel.mbccs.data.model.StaffInfo;
import com.viettel.mbccs.screen.assigntask.adapters.StaffAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/27/2017.
 */

public class StaffPickerPresenter extends BaseSearchListPickerPresenter<StaffInfo>
        implements StaffPickerContract.Presenter, StaffAdapter.OnStaffPickListener {

    private List<StaffInfo> mFilter;
    private Handler handler = new Handler();

    public StaffPickerPresenter(Context context, BaseSearchListViewContract.ViewModel viewModel,
                                List<StaffInfo> staffs) {
        super(context, viewModel);
        listData.addAll(staffs);
        mFilter.addAll(listData);
    }

    @Override
    public void onTextChange(String s) {
        handler.removeCallbacks(filter);
        handler.postDelayed(filter, 500);
    }

    @Override
    public void doSearch() {

    }

    @Override
    public void onSearchSuccess() {

    }

    @Override
    public void onSearchFail() {

    }

    @Override
    public String getSearchHint() {
        return null;
    }

    @Override
    public String getToolbarTitle() {
        return mContext.getString(R.string.chon_nhan_vien);
    }

    @Override
    public void onBackPressed() {
        mViewModel.onBackPressed();
    }

    @Override
    protected RecyclerView.Adapter getListAdapter() {
        if (mFilter == null)
            mFilter = new ArrayList<>();
        StaffAdapter adapter = new StaffAdapter(mFilter);
        adapter.setOnStaffPickListener(this);
        return adapter;
    }

    Runnable filter = new Runnable() {
        @Override
        public void run() {
            mFilter.clear();
            String searchKey = text.get();
            for (StaffInfo item : listData) {
                if (item.getStaffName().toLowerCase().contains(searchKey.toLowerCase())) {
                    mFilter.add(item);
                }
            }
            adapter.get().notifyDataSetChanged();
        }
    };

    @Override
    public void onItemPicked(StaffInfo staffInfo) {
        mViewModel.onItemClicked(staffInfo);
    }
}
