package com.viettel.mbccs.screen.sell.channel;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.constance.SaleTranType;
import com.viettel.mbccs.constance.StockTotalType;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.data.model.SaleChannelInitData;
import com.viettel.mbccs.data.model.SaleProgram;
import com.viettel.mbccs.data.model.TeleComService;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListChannelByOwnerTypeIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.request.GetTotalStockRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListChannelByOwnerTypeIdResponse;
import com.viettel.mbccs.data.source.remote.response.GetTotalStockResponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
import com.viettel.mbccs.screen.sell.retail.adapter.StockAdapter;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.SpinnerAdapter;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func2;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by eo_cuong on 5/20/17.
 */

public class SaleChannelPresenter
        implements SaleChannelContract.Presenter, StockAdapter.OnStockListener {

    public ObservableField<String> filterText;
    public ObservableField<String> sellProgram;
    public ObservableField<String> channelText;
    private SpinnerAdapter<TeleComService> mAdapter;
    public ObservableField<Boolean> isCollapse;
    private StockAdapter stockAdapter;
    private Context mContext;
    private SaleChannelContract.ViewModel mViewModel;
    private List<ModelSale> mModelSales = new ArrayList<>();
    private List<SaleProgram> mSalePrograms = new ArrayList<>();
    private List<ChannelInfo> mChannelInfos = new ArrayList<>();
    private List<TeleComService> mTeleComServices = new ArrayList<>();
    private SaleProgram currentSaleProgram = new SaleProgram();
    private TeleComService currentTelecomService = new TeleComService();
    private ChannelInfo currentChannel = new ChannelInfo();
    private int currentStockPosition = -1;
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;
    private UserRepository mUserRepository;
    private DataRequest<GetTelecomServiceAndSaleProgramRequest>
            mGetTelecomServiceAndSaleProgramRequest;
    private DataRequest<GetListChannelByOwnerTypeIdRequest> mGetListChannelByOwnerTypeIdRequest;
    private DataRequest<GetTotalStockRequest> mGetTotalStockRequest;
    private CompositeSubscription mSubscription;

    public SaleChannelPresenter(Context context, SaleChannelContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        mSubscription = new CompositeSubscription();
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mUserRepository = UserRepository.getInstance();
        init();
        initialData();
    }

    private void loadModelSale() {
        mViewModel.showLoading();
        mGetTotalStockRequest = new DataRequest<>();
        mGetTotalStockRequest.setApiCode(ApiCode.GetStockTotal);
        GetTotalStockRequest request = new GetTotalStockRequest();
        if (currentSaleProgram.getId() != -1) {
            request.setSaleProgameId(currentSaleProgram.getId());
        }
        if (currentTelecomService.getId() != -1) {
            request.setTelecomServiceId(currentTelecomService.getId());
        }
        request.setOwnerId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        //hard code 2
        request.setOwnerType(2L);
        request.setStateId(StockTotalType.TYPE_NEW);
        request.setSaleTransType(SaleTranType.SALE_CHANNEL);
        mGetTotalStockRequest.setParameterApi(request);
        Subscription subscription =
                mBanHangKhoTaiChinhRepository.getModelSales(mGetTotalStockRequest)
                        .subscribe(new MBCCSSubscribe<GetTotalStockResponse>() {
                            @Override
                            public void onSuccess(GetTotalStockResponse object) {
                                mModelSales.clear();
                                mModelSales.addAll(object.getModelSaleList());
                                stockAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(BaseException error) {

                                DialogUtils.showDialogError(mContext, null, error.getMessage(),
                                        null);
                            }

                            @Override
                            public void onRequestFinish() {
                                super.onRequestFinish();
                                mViewModel.hideLoading();
                            }
                        });

        mSubscription.add(subscription);
    }

    private void initialData() {
        mViewModel.showLoading();
        Observable.zip(getObservableTeleComserviceAndSaleProgram(), getObservaleChannelInfors(),
                new Func2<TelecomServiceAndSaleProgramResponse, GetListChannelByOwnerTypeIdResponse, SaleChannelInitData>() {
                    @Override
                    public SaleChannelInitData call(
                            TelecomServiceAndSaleProgramResponse telecomServiceAndSaleProgramResponse,
                            GetListChannelByOwnerTypeIdResponse getListChannelByOwnerTypeIdResponse) {
                        if (telecomServiceAndSaleProgramResponse == null
                                || getListChannelByOwnerTypeIdResponse == null) {
                            return null;
                        }
                        return new SaleChannelInitData(telecomServiceAndSaleProgramResponse,
                                getListChannelByOwnerTypeIdResponse);
                    }
                }).subscribe(new MBCCSSubscribe<SaleChannelInitData>() {
            @Override
            public void onSuccess(SaleChannelInitData object) {
                if (object != null
                        && object.getmGetListChannelByOwnerTypeIdResponse() != null
                        && object.getTelecomServiceAndSaleProgramResponse() != null) {

                    //telecom service and salprogram
                    mTeleComServices.addAll(
                            object.getTelecomServiceAndSaleProgramResponse().getTeleComServices());
                    mSalePrograms.addAll(
                            object.getTelecomServiceAndSaleProgramResponse().getSalePrograms());
                    mAdapter.notifyDataSetChanged();
                    TeleComService defaultTelecomSercie = new TeleComService(-1,
                            mContext.getResources().getString(R.string.all_));
                    mTeleComServices.add(0, defaultTelecomSercie);

                    SaleProgram defaultSaleProgram =
                            new SaleProgram(-1, mContext.getResources().getString(R.string.all_));
                    mSalePrograms.add(0, defaultSaleProgram);

                    currentSaleProgram = mSalePrograms.get(0);
                    currentTelecomService = mTeleComServices.get(0);

                    sellProgram.set(currentSaleProgram.getName());

                    if (object.getmGetListChannelByOwnerTypeIdResponse().getChannelInfoList()
                            == null) {
                        return;
                    }
                    //channel
                    mChannelInfos.clear();
                    mChannelInfos.addAll(
                            object.getmGetListChannelByOwnerTypeIdResponse().getChannelInfoList());
                    ChannelInfo channel =
                            new ChannelInfo(-1, mContext.getResources().getString(R.string.all_));
                    mChannelInfos.add(0, channel);
                    currentChannel = mChannelInfos.get(0);
                    channelText.set(currentChannel.getChannelName());
                    changeSearchFilter();
                    loadModelSale();
                }
            }

            @Override
            public void onError(BaseException error) {
                DialogUtils.showDialogError(mContext, null, error.getMessage(), null);
            }

            @Override
            public void onRequestFinish() {
                super.onRequestFinish();
                mViewModel.hideLoading();
            }
        });
    }

    private Observable<TelecomServiceAndSaleProgramResponse> getObservableTeleComserviceAndSaleProgram() {
        mGetTelecomServiceAndSaleProgramRequest = new DataRequest<>();
        mGetTelecomServiceAndSaleProgramRequest.setApiCode(ApiCode.GetTelecomServiceAndSaleProgram);
        GetTelecomServiceAndSaleProgramRequest request =
                new GetTelecomServiceAndSaleProgramRequest();
        request.setShopId(mUserRepository.getUserInfo().getShop().getShopId());
        mGetTelecomServiceAndSaleProgramRequest.setParameterApi(request);
        return mBanHangKhoTaiChinhRepository.getTelecomserviceAndSaleProgram(
                mGetTelecomServiceAndSaleProgramRequest);
    }

    private Observable<GetListChannelByOwnerTypeIdResponse> getObservaleChannelInfors() {
        mGetListChannelByOwnerTypeIdRequest = new DataRequest<>();
        mGetListChannelByOwnerTypeIdRequest.setApiCode(ApiCode.GetListChannelByOwnerTypeId);
        GetListChannelByOwnerTypeIdRequest request = new GetListChannelByOwnerTypeIdRequest();
        request.setStaffId(mUserRepository.getUserInfo().getStaffInfo().getStaffOwnerId());
        request.setChannelTypeId(mUserRepository.getUserInfo().getStaffInfo().getChannelTypeId());
        request.setLanguage("en");
        mGetListChannelByOwnerTypeIdRequest.setParameterApi(request);
        return mBanHangKhoTaiChinhRepository.getListChannelByOwnerTypeId(
                mGetListChannelByOwnerTypeIdRequest);
    }

    private void loadServiceAndProgram() {
        mViewModel.showLoading();
        mGetTelecomServiceAndSaleProgramRequest = new DataRequest<>();
        mGetTelecomServiceAndSaleProgramRequest.setApiCode(ApiCode.GetTelecomServiceAndSaleProgram);
        GetTelecomServiceAndSaleProgramRequest request =
                new GetTelecomServiceAndSaleProgramRequest();
        mGetTelecomServiceAndSaleProgramRequest.setParameterApi(request);

        Subscription subscription = mBanHangKhoTaiChinhRepository.getTelecomserviceAndSaleProgram(
                mGetTelecomServiceAndSaleProgramRequest)
                .subscribe(new MBCCSSubscribe<TelecomServiceAndSaleProgramResponse>() {
                    @Override
                    public void onSuccess(TelecomServiceAndSaleProgramResponse object) {
                        mTeleComServices.addAll(object.getTeleComServices());
                        mSalePrograms.addAll(object.getSalePrograms());
                        mAdapter.notifyDataSetChanged();
                        TeleComService defaultTelecomSercie = new TeleComService(-1,
                                mContext.getResources().getString(R.string.all_));
                        mTeleComServices.add(0, defaultTelecomSercie);

                        SaleProgram defaultSaleProgram = new SaleProgram(-1,
                                mContext.getResources().getString(R.string.all_));
                        mSalePrograms.add(0, defaultSaleProgram);

                        currentSaleProgram = mSalePrograms.get(0);
                        currentTelecomService = mTeleComServices.get(0);

                        sellProgram.set(currentSaleProgram.getName());

                        loadModelSale();
                    }

                    @Override
                    public void onError(BaseException error) {
                        DialogUtils.showDialogError(mContext, null, error.getMessage(), null);
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        mViewModel.hideLoading();
                    }
                });

        mSubscription.add(subscription);
    }

    private void init() {
        filterText = new ObservableField<>();
        sellProgram = new ObservableField<>();
        channelText = new ObservableField<>();
        isCollapse = new ObservableField<>();
        isCollapse.set(false);
        mAdapter = new SpinnerAdapter<TeleComService>(mContext, mTeleComServices);
        mAdapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                onItemServiceClick(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        stockAdapter = new StockAdapter(mContext, mModelSales);
        stockAdapter.setOnStockListener(this);
    }

    public void onCollapse() {
        isCollapse.set(!isCollapse.get());
        changeSearchFilter();
    }

    public void onExpand() {
        isCollapse.set(false);
        changeSearchFilter();
    }

    public void chooseSellProgram() {
        mViewModel.onChooseSaleProgram(mSalePrograms);
    }

    public void chooseChannel() {
        mViewModel.onChooseChannel(mChannelInfos);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

        mSubscription.clear();
    }

    public SpinnerAdapter<TeleComService> getAdapter() {
        return mAdapter;
    }

    public void setAdapter(SpinnerAdapter<TeleComService> adapter) {
        mAdapter = adapter;
    }

    public StockAdapter getStockAdapter() {
        return stockAdapter;
    }

    public void setStockAdapter(StockAdapter stockAdapter) {
        this.stockAdapter = stockAdapter;
    }

    @Override
    public void onGetSaleProgramSuccess(SaleProgram saleProgram) {
        if (saleProgram == null) {
            return;
        }
        currentSaleProgram = saleProgram;
        sellProgram.set(currentSaleProgram.getName());
        loadModelSale();
    }

    @Override
    public void onGetChannelSuccess(ChannelInfo channelInfo) {
        if (channelInfo == null) {
            return;
        }
        currentChannel = channelInfo;
        channelText.set(currentChannel.getChannelName());
    }

    @Override
    public void onItemServiceClick(int position) {
        currentTelecomService = mTeleComServices.get(position);
        loadModelSale();
    }

    @Override
    public void onSerialPickerSuccess(List<String> serials) {
        mModelSales.get(currentStockPosition).setSerials(serials);
        stockAdapter.notifyItemChanged(currentStockPosition);
    }

    @Override
    public void refresh() {
        currentSaleProgram = mSalePrograms.get(0);
        currentTelecomService = mTeleComServices.get(0);
        mViewModel.refresh();
        sellProgram.set(currentSaleProgram.getName());
        currentStockPosition = -1;
        changeSearchFilter();
        loadModelSale();
    }

    public void changeSearchFilter() {
        String filter0;
        String filter1;
        String filter2;
        if (currentChannel.getChannelId() == -1) {
            filter0 = mContext.getResources().getString(R.string.all);
        } else {
            filter0 = currentChannel.getChannelName();
        }
        if (currentTelecomService.getId() == -1) {
            filter1 = mContext.getResources().getString(R.string.all);
        } else {
            filter1 = currentTelecomService.getName();
        }
        if (currentSaleProgram.getId() == -1) {
            filter2 = mContext.getResources().getString(R.string.all);
        } else {
            filter2 = currentSaleProgram.getName();
        }

        filterText.set(filter0
                + mContext.getString(R.string.common_lable_dot)
                + filter1
                + mContext.getString(R.string.common_lable_dot)
                + filter2);
    }

    @Override
    public void onSerialClick(ModelSale item, int position) {
        currentStockPosition = position;
        mViewModel.onSerialPicker(item);
    }

    @Override
    public void onItemFocus() {
        isCollapse.set(true);
        changeSearchFilter();
    }

    public void onNext() {
        if (!validate()) {
            return;
        }
        mViewModel.onNext(mModelSales, currentTelecomService, currentSaleProgram, currentChannel);
    }

    private boolean validate() {
        if (currentChannel.getChannelId() == -1) {
            DialogUtils.showDialogError(mContext, R.string.no_channel);
            return false;
        }
        return true;
    }

    public void onCancel() {
        ((Activity) mContext).finish();
    }
}
