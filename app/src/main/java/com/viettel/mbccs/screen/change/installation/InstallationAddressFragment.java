package com.viettel.mbccs.screen.change.installation;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataFragment;
import com.viettel.mbccs.callback.OnListenerItemRecyclerView;
import com.viettel.mbccs.constance.WsCode;
//import com.viettel.mbccs.data.model.ApDomain;
import com.viettel.mbccs.data.model.ApDomainByType;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.source.QLKhachHangRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
//import com.viettel.mbccs.data.source.remote.request.GetApDomainRequest;
import com.viettel.mbccs.data.source.remote.request.GetAllSubInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetApDomainByTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegisterSubInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegisterSubRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
//import com.viettel.mbccs.data.source.remote.response.GetApDomainResponse;
import com.viettel.mbccs.data.source.remote.response.GetAllSubInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetApDomainByTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegisterSubInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegisterSubResponse;
import com.viettel.mbccs.screen.change.installation.adapter.InstallationAddressAdapter;
import com.viettel.mbccs.utils.EditTextUtil;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.widget.ItemSpinText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jacky on 5/22/17.
 */

public class InstallationAddressFragment extends BaseDataFragment {

    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    @BindView(R.id.ist_phone_number)
    ItemSpinText mPhoneNumber;
    @BindView(R.id.ist_type_of_pages)
    ItemSpinText mTypeOfPage;
    @BindView(R.id.ist_number_of_pages)
    ItemSpinText mNumberOfPage;
    @BindView(R.id.ist_type_of_telecommunications_services)
    ItemSpinText mTelecomService;

    private LinearLayoutManager mLinearLayoutManager;
    private InstallationAddressAdapter mAdapter;
    private QLKhachHangRepository qlKhachHangRepository;
    private CompositeSubscription subscriptions;
    private boolean typeCreate;
//    private List<ApDomain> dataPassportType;

    @Override
    protected void initData() {

    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_install_address;
    }

    @Override
    protected void initView() {

        qlKhachHangRepository = QLKhachHangRepository.getInstance();
        subscriptions = new CompositeSubscription();

        mLinearLayoutManager = new LinearLayoutManager(getBaseActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        getDataSpinnerPassport(ApDomainByType.Type.LOAI_GIAY_TO);


        EditTextUtil.hideKeyboard(getActivity());
    }


    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().setTitleToolbar(R.string.title_install_address);
    }

    private void showSearchInfoCustomer(GetRegisterSubInfoResponse mResponse) {
        List<Customer> mList = new ArrayList<>();
        if (mResponse != null) {
            mList.add(mResponse.getCustomer());
        } else {
            mList.addAll(listCustomer());
        }
        mAdapter = new InstallationAddressAdapter(listCustomer(), getBaseActivity());
        mAdapter.setOnClickItemRecycleView(new OnListenerItemRecyclerView<Customer>() {
            @Override
            public void onClickItem(Customer mCustomer, int position) {
                if (mCustomer != null) {
                    getBaseActivity().openActivity(InstallationAddressDetailActivity.class);
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<Customer> listCustomer() {
        List<Customer> mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Customer model = new Customer();
            mList.add(model);
        }
        return mList;
    }

    @OnClick({R.id.bt_search})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_search:
                callApiSearch();
                //showSearchInfoCustomer(null);
                break;
            default:
                break;
        }
    }

    private void callApiSearch() {
        getBaseActivity().showLoadingDialog();
//        GetRegisterSubInfoRequest getRegisterSubRequest = new GetRegisterSubInfoRequest();
////        getRegisterSubInfoRequest.setIsdn(mPhoneNumber.getStringEditText());
////        getRegisterSubInfoRequest.setIdNo(mTypeOfPage.getStringEditText());
////        getRegisterSubInfoRequest.setIdType(mNumberOfPage.getStringEditText());
//        getRegisterSubRequest.setIsdn("620103022");
//        getRegisterSubRequest.setIdNo("145079102");
//        getRegisterSubRequest.setIdType("1");
//        DataRequest<GetRegisterSubInfoRequest> request = new DataRequest<>();
//        request.setWsCode(WsCode.GetAllSubInfo);
//        request.setWsRequest(getRegisterSubRequest);
//        //
//        Subscription subscription = qlKhachHangRepository.getRegiterSubInfo(request)
//                .subscribe(new MBCCSSubscribe<GetRegisterSubInfoResponse>() {
//                    @Override
//                    public void onSuccess(GetRegisterSubInfoResponse object) {
//                        showSearchInfoCustomer(object);
//                        getBaseActivity().hideLoadingDialog();
//                        System.out.println("hideLoadingDialog" + 111);
//                    }
//
//                    @Override
//                    public void onError(BaseException error) {
//                        getBaseActivity().hideLoadingDialog();
//                        System.out.println("hideLoadingDialog" + 404);
//                    }
//                });
//        subscriptions.add(subscription);

        GetAllSubInfoRequest getAllSubInfoRequest = new GetAllSubInfoRequest();
        getAllSubInfoRequest.setIsdn(mPhoneNumber.getStringEditText());
        getAllSubInfoRequest.setIdNo(mTypeOfPage.getStringEditText());
        getAllSubInfoRequest.setSubType("1");
        getAllSubInfoRequest.setServiceType("L");
        getAllSubInfoRequest.setIdType("1");

        DataRequest<GetAllSubInfoRequest> request = new DataRequest<>();
        request.setWsCode(WsCode.GetAllSubInfo);
        request.setWsRequest(getAllSubInfoRequest);

        Subscription subscription = qlKhachHangRepository.getAllSubInfo(request)
                .subscribe(new MBCCSSubscribe<GetAllSubInfoResponse>() {
                    @Override
                    public void onSuccess(GetAllSubInfoResponse object) {
                        if (object == null || object.getCustomer() == null) {
                            onError(new Exception());
                        } else {

                        }
                        getBaseActivity().hideLoadingDialog();
                    }

                    @Override
                    public void onError(BaseException error) {
                        getBaseActivity().hideLoadingDialog();
                    }
                });
        subscriptions.add(subscription);

    }


    public void getDataSpinnerPassport(final String mType) {
        getBaseActivity().showLoadingDialog();
        DataRequest<GetApDomainByTypeRequest> request = new DataRequest<>();
        GetApDomainByTypeRequest getApDomainRequest = new GetApDomainByTypeRequest();
        getApDomainRequest.setType(mType);
        getApDomainRequest.setSubType("1");
        request.setWsRequest(getApDomainRequest);
        request.setWsCode(WsCode.GetApDomainByType);

        Subscription subscription = qlKhachHangRepository.getApDomainByType(request)
                .subscribe(new MBCCSSubscribe<GetApDomainByTypeResponse>() {
                    @Override
                    public void onSuccess(GetApDomainByTypeResponse response) {
                        getBaseActivity().hideLoadingDialog();
                        if (response != null) {
                            switch (mType) {
                                case ApDomainByType.Type.LOAI_GIAY_TO:
                                    mTypeOfPage.setListSpinner(response.getListName());
                                    getDataSpinnerPassport(ApDomainByType.Type.TELECOM_SERVICE);
                                    break;
                                case ApDomainByType.Type.TELECOM_SERVICE:
                                    mTelecomService.setListSpinner(response.getListName());
                                    break;
                                default:
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onError(BaseException error) {
                        getBaseActivity().hideLoadingDialog();
                    }
                });
        subscriptions.add(subscription);
    }
}
