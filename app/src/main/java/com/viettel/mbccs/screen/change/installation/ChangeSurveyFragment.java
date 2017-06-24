package com.viettel.mbccs.screen.change.installation;

import android.Manifest;
import android.support.annotation.NonNull;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.model.LamModel;
import com.viettel.mbccs.data.model.Shop;
import com.viettel.mbccs.data.source.ManagerInstallAddressRepository;
import com.viettel.mbccs.data.source.remote.request.AddressRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListDsLamByTeamIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListTeamRequest;
import com.viettel.mbccs.data.source.remote.request.GetReceiverChangeAddressRequest;
import com.viettel.mbccs.data.source.remote.request.SubscriberRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListDsLamByTeamIdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListTeamResponse;
import com.viettel.mbccs.data.source.remote.response.GetReceiverChangeAddressResponse;
import com.viettel.mbccs.permission.PermissionListener;
import com.viettel.mbccs.permission.PermissionsUtil;
import com.viettel.mbccs.screen.change.BaseChangeAddressFragment;
import com.viettel.mbccs.screen.map.DialogMapStationFragment;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.widget.ItemSpinText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

public class ChangeSurveyFragment extends BaseChangeAddressFragment {

    @BindView(R.id.ist_team)
    ItemSpinText mItemTeam;
    @BindView(R.id.ist_lam_ds)
    ItemSpinText mItemLamDs;

    private ManagerInstallAddressRepository mInstallAddressRepository;
    private CompositeSubscription mCompositeSubscription;

    private List<String> mListTeam = new ArrayList<>();
    private List<String> mListLamDS = new ArrayList<>();


    @Override
    protected void initData() {
        mInstallAddressRepository = ManagerInstallAddressRepository.getInstance();
        mCompositeSubscription = new CompositeSubscription();
        getListTeam();
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_change_survey;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.bt_select_station})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_select_station:
                PermissionsUtil.requestPermission(getActivity(), new PermissionListener() {
                    @Override
                    public void permissionGranted(@NonNull String[] permissions) {
                        getBaseActivity().goToDialogFragment(new DialogMapStationFragment(), null);
                    }

                    @Override
                    public void permissionDenied(@NonNull String[] permissions) {

                    }
                }, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION});
                break;

            default:
                break;
        }

    }

    private void getListTeam() {
        getBaseActivity().showLoadingDialog();

        DataRequest<GetListTeamRequest> request = new DataRequest<>();
        GetListTeamRequest mGetListTeamRequest = new GetListTeamRequest();
        mGetListTeamRequest.provinceCode = "MA";
        mGetListTeamRequest.districtCode = "02";
        mGetListTeamRequest.precinctCode = "07";

        request.setParameterApi(mGetListTeamRequest);
        request.setApiCode(ApiCode.GetListTeam);

        Subscription subscription = mInstallAddressRepository.getListTeam(request)
                .flatMap(new Func1<GetListTeamResponse, Observable<Shop>>() {
                    @Override
                    public Observable<Shop> call(GetListTeamResponse getListTeamResponse) {
                        return Observable.from(getListTeamResponse.mListShopSM);
                    }
                })
                .map(new Func1<Shop, Shop>() {
                    @Override
                    public Shop call(Shop shop) {
                        mListTeam.add(shop.getShopId());
                        return shop;
                    }
                }).toList()
                .subscribe(new MBCCSSubscribe<List<Shop>>() {
                    @Override
                    public void onSuccess(List<Shop> mShopList) {
                        if (mShopList != null && mListTeam != null) {
                            getListDsLamByTeamId(Integer.valueOf(mShopList.get(0).getShopId()));
                            mItemTeam.setListSpinner(mListTeam);
                        }else {
                            getBaseActivity().hideLoadingDialog();
                        }
                    }
                    @Override
                    public void onError(BaseException error) {
                        getBaseActivity().hideLoadingDialog();
                    }
                });
        mCompositeSubscription.add(subscription);

    }

    private void getListDsLamByTeamId(int shopId) {
        getBaseActivity().showLoadingDialog();

        DataRequest<GetListDsLamByTeamIdRequest> request = new DataRequest<>();
        GetListDsLamByTeamIdRequest mGetListDsLamByTeamIdRequest = new GetListDsLamByTeamIdRequest();
        mGetListDsLamByTeamIdRequest.shopId = shopId;

        request.setParameterApi(mGetListDsLamByTeamIdRequest);
        request.setApiCode(ApiCode.GetListDsLamByTeamId);

        Subscription subscription = mInstallAddressRepository.getListDsLamByTeamId(request)
                .flatMap(new Func1<GetListDsLamByTeamIdResponse, Observable<LamModel>>() {
                    @Override
                    public Observable<LamModel> call(GetListDsLamByTeamIdResponse getListTeamResponse) {
                        return Observable.from(getListTeamResponse.mListDSLam);
                    }
                })
                .map(new Func1<LamModel, LamModel>() {
                    @Override
                    public LamModel call(LamModel shop) {
                        mListLamDS.add(shop.mName);
                        return shop;
                    }
                }).toList()
                .subscribe(new MBCCSSubscribe<List<LamModel>>() {
                    @Override
                    public void onSuccess(List<LamModel> mList) {
                        mItemLamDs.setListSpinner(mListLamDS);

                        getBaseActivity().hideLoadingDialog();
                    }

                    @Override
                    public void onError(BaseException error) {
                        getBaseActivity().hideLoadingDialog();
                    }
                });
        mCompositeSubscription.add(subscription);

    }

    private void receiverChangeAddress() {
        getBaseActivity().showLoadingDialog();
        AddressRequest<GetReceiverChangeAddressRequest> request = new AddressRequest<>();
        GetReceiverChangeAddressRequest mGetListDsLamByTeamIdRequest = new GetReceiverChangeAddressRequest();
        SubscriberRequest subscriber = new SubscriberRequest();
        subscriber.serviceType ="A";
        subscriber.isdn ="L75635";
        subscriber.shopCode ="BRTDF01";
        subscriber.province ="ZW";
        subscriber.district ="01";
        subscriber.precinct = "15";
        subscriber.staffId = 12121;
        mGetListDsLamByTeamIdRequest.subscriber = subscriber;

        request.setParameterApi(mGetListDsLamByTeamIdRequest);
        request.setApiCode(ApiCode.GetListDsLamByTeamId);
        request.setTeamId(1005450);
        request.setDsTeamId(122435499);
        request.setHasNIMS(1);

        Subscription subscription = mInstallAddressRepository.receiverChangeAddress(request)
                .subscribe(new MBCCSSubscribe<GetReceiverChangeAddressResponse>() {
                    @Override
                    public void onSuccess(GetReceiverChangeAddressResponse response) {

                        getBaseActivity().hideLoadingDialog();
                    }

                    @Override
                    public void onError(BaseException error) {
                        getBaseActivity().hideLoadingDialog();
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void callApiRequest() {
        super.callApiRequest();
        receiverChangeAddress();
    }
}