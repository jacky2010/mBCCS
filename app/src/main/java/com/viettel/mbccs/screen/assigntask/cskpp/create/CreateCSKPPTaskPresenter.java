package com.viettel.mbccs.screen.assigntask.cskpp.create;

import android.content.Context;
import com.viettel.mbccs.R;

/**
 * Created by Anh Vu Viet on 5/23/2017.
 */

public class CreateCSKPPTaskPresenter implements CreatingCSKPPTaskContract.Presenter {

    private Context mContext;

    private CreatingCSKPPTaskContract.ViewModel mViewModel;

    public CreateCSKPPTaskPresenter(Context context,
            CreatingCSKPPTaskContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public String getToolbarTitle() {
        return mContext.getString(R.string.create_CSKPP_task_presenter_giao_viec_cskpp);
    }

    public void onSelectStaffClick() {
        mViewModel.openStaffPicker();
    }

    public void close() {
        mViewModel.onBackPressed();
    }

    public void assign() {
        // TODO: 5/27/2017 Validate info
        mViewModel.assignTask();
    }
}
