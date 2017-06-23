package com.viettel.mbccs.screen.information;

import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.ApDomainByType;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetRegiterSubInfoResponse;
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

    private ArrayAdapter<String> adapterPassportType;
    private InformationCustomerAdapter adapter;
    private boolean typeCreate;
    private List<GetRegiterSubInfoResponse> getRegiterSubInfoResponseList;
    private GetRegiterSubInfoResponse data;

    private List<String> dataPassportType;
    private List<ApDomainByType> apDomainList;

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
        dataPassportType = new ArrayList<>();
        adapterPassportType =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dataPassportType);
        adapterPassportType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPresenter.setAdapterPassportType(adapterPassportType);
        mBinding.spinner.setOnItemSelectedListener(mPresenter);

        mBinding.setPresenter(mPresenter);
        mPresenter.setTypeCreate(typeCreate);
        mPresenter.getDataSpinnerPassport();
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
    public void onSearchSuccess(GetRegiterSubInfoResponse data) {
        this.data = data;
        getRegiterSubInfoResponseList = new ArrayList<>();
        getRegiterSubInfoResponseList.add(data);
        adapter = new InformationCustomerAdapter(this, getRegiterSubInfoResponseList, typeCreate);
        mPresenter.setInformationCustomerAdapter(adapter);
        adapter.setItemClick(this);
    }

    @Override
    public void onSearchError(BaseException error) {
        DialogUtils.showDialogError(this, error);
    }

    @Override
    public void onRegisterNewPayment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        CreateUpdateInformationFragment fragment = CreateUpdateInformationFragment.newInstance(
                CreateUpdateInformationFragment.Type.CREATE_INFORMATION, null);
        transaction.replace(R.id.frame_create_update_information, fragment);
        transaction.addToBackStack(CreateUpdateInformationFragment.STRING_NAME);
        transaction.commit();
    }

    @Override
    public void getDataSpinnerPassportSuccess(List<ApDomainByType> data) {
        apDomainList = data;
        for (ApDomainByType a : data) {
            dataPassportType.add(a.getName());
        }
        adapterPassportType.notifyDataSetChanged();
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
                        : CreateUpdateInformationFragment.Type.UPDATE_INFORMATION, data);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_create_update_information, fragment);
        transaction.addToBackStack(CreateUpdateInformationFragment.STRING_NAME);
        transaction.commit();
    }
}
