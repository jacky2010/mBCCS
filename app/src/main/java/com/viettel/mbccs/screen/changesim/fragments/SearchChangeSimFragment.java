package com.viettel.mbccs.screen.changesim.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.data.model.ChangeSimItem;
import com.viettel.mbccs.databinding.FragmentSearchChangeSimBinding;
import com.viettel.mbccs.screen.changesim.dialogs.DialogActionBeforeUpdateSimFragment;
import com.viettel.mbccs.utils.ActivityUtils;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;

/**
 * Created by minhnx on 5/20/17.
 */

public class SearchChangeSimFragment extends BaseDataBindFragment<FragmentSearchChangeSimBinding, SearchChangeSimPresenter>
        implements SearchChangeSimContract.ViewModel {

    private AppCompatActivity mActivity;
    private boolean shownLoadingDialog = false;

    public static SearchChangeSimFragment newInstance() {
        return new SearchChangeSimFragment();
    }

    @Override
    public void setPresenter(SearchChangeSimContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {
        if(shownLoadingDialog)
            return;

        showLoadingDialog();
        shownLoadingDialog = true;
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
        shownLoadingDialog = false;
    }

    @Override
    protected void initData() {
        mPresenter = new SearchChangeSimPresenter(getContext(), this);
        mBinding.setPresenter(mPresenter);

        initListeners();
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_search_change_sim;
    }

    @Override
    protected void initView() {

    }

    private void initListeners() {
        try {
            mBinding.txtDocumentId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (!b)
                        hideSoftInput();
                }
            });

//            mBinding.spDocumentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                    if(view == null)
//                        return;
//                    if (!Constants.View.HINT.equals(view.getTag()))
//                        mPresenter.onDocumentTypeChanged(i);
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> adapterView) {
//
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideSoftInput() {
        ActivityUtils.hideKeyboard(getBaseActivity());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (AppCompatActivity) activity;
    }

    @Override
    public void onSimFound(String isdn, String documentType, String documentId) {
        try {
            hideSoftInput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSimNotFound(String isdn, String documentType, String documentId) {
        try {
            hideSoftInput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPrepareChangeSim(ChangeSimItem item) {
        try{
            Bundle args = new Bundle();
            args.putString(Constants.BundleConstant.CHANGE_SIM_ITEM, GsonUtils.Object2String(item));

            UpdateSimFragment fragment = UpdateSimFragment.newInstance();
            fragment.setArguments(args);

            mActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_main, fragment)
                    .addToBackStack(null)
                    .commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void goToPreAction(Bundle args) {
        try{
            getBaseActivity().goToDialogFragment(new DialogActionBeforeUpdateSimFragment(), args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
    }
}
