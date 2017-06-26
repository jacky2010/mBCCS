package com.viettel.mbccs.screen.information;

import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetAllSubInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegisterSubInfoResponse;
import com.viettel.mbccs.databinding.ActivityCreateUpdateInformationBinding;
import com.viettel.mbccs.screen.information.adapter.InformationCustomerAdapter;
import com.viettel.mbccs.screen.information.fragment.CreateUpdateInformationFragment;
import com.viettel.mbccs.utils.DialogUtils;
import java.util.ArrayList;
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
    private GetRegisterSubInfoResponse dataRegister;
    private GetAllSubInfoResponse dataUpdate;
    List<InformationCustomerAdapter.DataInformationCustomerAdapter> dataList;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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

        mBinding.setPresenter(mPresenter);
        mPresenter.setTypeCreate(typeCreate);
        mPresenter.getDataSpinnerPassport();
        dataList = new ArrayList<>();
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
        onBackPressed();
    }

    @Override
    public void onSearchDKTTSuccess(GetRegisterSubInfoResponse data) {
        this.dataRegister = data;
        InformationCustomerAdapter.DataInformationCustomerAdapter dataInformation =
                new InformationCustomerAdapter.DataInformationCustomerAdapter();
        dataInformation.setCustomer(data.getCustomer());
        dataInformation.setSubscriber(data.getSubscriber());
        dataList.add(dataInformation);
        adapter = new InformationCustomerAdapter(this, dataList, typeCreate);
        mPresenter.setInformationCustomerAdapter(adapter);
        adapter.setItemClick(this);
    }

    @Override
    public void onSearchCNTTSuccess(GetAllSubInfoResponse data) {
        this.dataUpdate = data;
    }

    @Override
    public void onSearchError(BaseException error) {
        DialogUtils.showDialogError(this, error);
    }

    @Override
    public void onRegisterNewPayment(GetRegisterSubInfoResponse getRegisterSubInfoResponse) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        CreateUpdateInformationFragment fragment = CreateUpdateInformationFragment.newInstance(
                CreateUpdateInformationFragment.Type.CREATE_INFORMATION);
        fragment.setDataRegister(getRegisterSubInfoResponse);
        transaction.replace(R.id.frame_create_update_information, fragment);
        transaction.addToBackStack(CreateUpdateInformationFragment.STRING_NAME);
        transaction.commit();
    }

    @Override
    public void getDataSpinnerPassportError(BaseException error) {
        DialogUtils.showDialogError(this, error, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }, false);
    }

    @Override
    public void showDialogValidate() {
        DialogUtils.showDialog(this,
                getString(R.string.create_update_information_create_validate_field));
    }

    @Override
    public void onItemClick(int position) {
        if (position != 0) return;
        CreateUpdateInformationFragment fragment = CreateUpdateInformationFragment.newInstance(
                typeCreate ? CreateUpdateInformationFragment.Type.CREATE_INFORMATION_CLONE
                        : CreateUpdateInformationFragment.Type.UPDATE_INFORMATION);
        if (typeCreate) {
            fragment.setDataRegister(dataRegister);
        } else {
            fragment.setDataUpdate(dataUpdate);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_create_update_information, fragment);
        transaction.addToBackStack(CreateUpdateInformationFragment.STRING_NAME);
        transaction.commit();
    }
}
