package com.viettel.mbccs.screen.information;

import android.support.v4.app.FragmentTransaction;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.databinding.ActivityCreateUpdateInformationBinding;
import com.viettel.mbccs.screen.information.adapter.InformationCustomerAdapter;
import com.viettel.mbccs.screen.information.fragment.CreateUpdateInformationFragment;
import java.util.List;

/**
 * Created by HuyQuyet on 5/29/17.
 */

public class CreateUpdateInformationActivity extends
        BaseDataBindActivity<ActivityCreateUpdateInformationBinding, CreateUpdateInformationPresenter>
        implements CreateUpdateInformationContract.View, InformationCustomerAdapter.ItemClick {
    public final static String ARG_TYPE = "TYPE";

    private InformationCustomerAdapter adapter;
    private boolean typeCreate;

    @Override
    public void onBackPressed() {
        onCancel();
    }

    @Override
    protected void onStop() {
        mPresenter.unSubscribe();
        super.onStop();
    }

    @Override
    protected int getIdLayout() {
        return R.layout.activity_create_update_information;
    }

    @Override
    protected void initData() {
        typeCreate = getIntent().getBooleanExtra(ARG_TYPE, true);
        mPresenter = new CreateUpdateInformationPresenter(this, this);
        mPresenter.setTypeCreate(typeCreate);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void setPresenter(CreateUpdateInformationContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public void onCancel() {
        finish();
    }

    @Override
    public void onSearchSuccess(List<Customer> data) {
        adapter = new InformationCustomerAdapter(data);
        mPresenter.setInformationCustomerAdapter(adapter);
        adapter.setItemClick(this);
    }

    @Override
    public void onSearchError(BaseException error) {
        // TODO: 5/29/17 show error
    }

    @Override
    public void onRegisterNewPayment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        CreateUpdateInformationFragment fragment = CreateUpdateInformationFragment.newInstance(
                CreateUpdateInformationFragment.Type.CREATE_INFORMATION);
        transaction.replace(R.id.frame_create_update_information, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onItemClick(int position) {

    }
}
