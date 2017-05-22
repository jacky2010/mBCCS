package com.viettel.mbccs.screen.changesim.fragments;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.data.model.ChangeSimItem;
import com.viettel.mbccs.databinding.FragmentUpdateSimBinding;
import com.viettel.mbccs.utils.ActivityUtils;

/**
 * Created by minhnx on 5/20/17.
 */

public class UpdateSimFragment extends BaseDataBindFragment<FragmentUpdateSimBinding, UpdateSimPresenter>
        implements UpdateSimContract.ViewModel{

    private AppCompatActivity mActivity;

    public static UpdateSimFragment newInstance() {
        return new UpdateSimFragment();
    }

    @Override
    public void setPresenter(UpdateSimContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initData() {
        mPresenter = new UpdateSimPresenter(getContext(), this);
        mBinding.setPresenter(mPresenter);

        initListeners();
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_update_sim;
    }

    @Override
    protected void initView() {

    }

    private void initListeners(){
        try{
//            mBinding.txtDocumentId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View view, boolean b) {
//                    if(!b)
//                        hideSoftInput();
//                }
//            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void hideSoftInput(){
        ActivityUtils.hideKeyboard(getBaseActivity());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (AppCompatActivity) activity;
    }

    @Override
    public void onChangeSimSuccessful(ChangeSimItem item) {

    }

    @Override
    public void onChangeSimFailed() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
    }
}
