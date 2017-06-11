package com.viettel.mbccs.screen.sell.retail.savetransconfirm;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.ObservableField;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.remote.request.CreateSaleTransChannelRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransChannelResponse;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransRetailResponse;
import com.viettel.mbccs.screen.common.success.DialogFullScreen;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class SaveTransConfirmPresenter implements SaveTransConfirmContract.Presenter {
    private Context mContext;
    private SaleTrans mSaleTrans;
    private GetInfoSaleTranRequest mGetInfoSaleTranRequest;
    private SaveTransConfirmContract.ViewModel mViewModel;
    private ChannelInfo mChannelInfo;
    public ObservableField<String> customerInfor;
    public ObservableField<String> salerInfor;
    public ObservableField<String> saleProgram;
    public CompositeSubscription mSubscription;
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;

    public SaveTransConfirmPresenter(Context context, SaveTransConfirmContract.ViewModel mViewModel,
            SaleTrans saleTrans, GetInfoSaleTranRequest getInfoSaleTranRequest,
            ChannelInfo channelInfo) {
        mContext = context;
        mSubscription = new CompositeSubscription();
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mSaleTrans = saleTrans;
        this.mChannelInfo = channelInfo;
        this.mViewModel = mViewModel;
        mGetInfoSaleTranRequest = getInfoSaleTranRequest;
        init();
    }

    private void init() {
        customerInfor = new ObservableField<>();
        saleProgram = new ObservableField<>();
        salerInfor = new ObservableField<>();
        if (mGetInfoSaleTranRequest == null) {
            return;
        }

        if (mGetInfoSaleTranRequest.getSaleProgrameCode() == null) {
            saleProgram.set(null);
        } else {
            saleProgram.set(String.format(mContext.getString(R.string.sale_confirm_sale_program),
                    mGetInfoSaleTranRequest.getSaleProgrameCode()));
        }
        if (mChannelInfo != null) {
            customerInfor.set(
                    String.format(mContext.getString(R.string.sale_channel_confirm_customer_infor),
                            mChannelInfo.getChannelName(), mChannelInfo.getChannelCode(),
                            Common.formatDouble(mSaleTrans.getAmountTax()),
                            Common.convertMoneyToString(mSaleTrans.getAmountTax())));
        } else {
            customerInfor.set(
                    String.format(mContext.getString(R.string.sale_retail_confirm_customer_infor),
                            mGetInfoSaleTranRequest.getCustomer().getCustomerName(),
                            "0123513124123", Common.formatDouble(mSaleTrans.getAmountTax()),
                            Common.convertMoneyToString(mSaleTrans.getAmountTax())));
        }
        salerInfor.set(String.format(mContext.getString(R.string.sale_retail_confirm_saler_infor),
                "Hoang Van Cuong", "0123112312513"));
    }

    @Override
    public void saveTransaction() {
        if (mChannelInfo == null) {
            saveTransactionRetail();
        } else {
            saveTransactionChannel();
        }
    }

    private void saveTransactionRetail() {
        mViewModel.showLoading();
        DataRequest<GetInfoSaleTranRequest> baseRequest = new DataRequest<>();
        baseRequest.setParameterApi(mGetInfoSaleTranRequest);
        Subscription subscription = mBanHangKhoTaiChinhRepository.createSaleTransRetail(baseRequest)
                .subscribe(new MBCCSSubscribe<CreateSaleTransRetailResponse>() {
                    @Override
                    public void onSuccess(CreateSaleTransRetailResponse object) {
                        Dialog dialog =
                                new DialogFullScreen.Builder(mContext).setCenterContent(true)
                                        .setAutoClose(true)
                                        .setTitle(mContext.getResources()
                                                .getString(R.string.sale_success))
                                        .setContent(mContext.getResources()
                                                .getString(R.string.sale_success_content))
                                        .build();

                        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {
                                mViewModel.onSaveTranSuccess();
                            }
                        });

                        dialog.setCancelable(false);
                        dialog.setCanceledOnTouchOutside(false);

                        dialog.show();
                    }

                    @Override
                    public void onError(BaseException error) {
                        //fake
                        //onSuccess(null);

                        DialogUtils.showDialogError(mContext, null, error.getMessage(), null);
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                    }
                });

        mSubscription.add(subscription);
    }

    private void saveTransactionChannel() {
        mViewModel.showLoading();
        DataRequest<CreateSaleTransChannelRequest> dataRequest = new DataRequest<>();
        dataRequest.setApiCode(ApiCode.CreateSaleTransChannel);
        CreateSaleTransChannelRequest request = mGetInfoSaleTranRequest.clone();
        dataRequest.setParameterApi(request);
        Subscription subscription =
                mBanHangKhoTaiChinhRepository.createSaleTransChannel(dataRequest)
                        .subscribe(new MBCCSSubscribe<CreateSaleTransChannelResponse>() {
                            @Override
                            public void onSuccess(CreateSaleTransChannelResponse object) {
                                Dialog dialog =
                                        new DialogFullScreen.Builder(mContext).setCenterContent(
                                                true)
                                                .setAutoClose(true)
                                                .setTitle(mContext.getResources()
                                                        .getString(R.string.sale_success))
                                                .setContent(mContext.getResources()
                                                        .getString(R.string.sale_success_content))
                                                .build();

                                dialog.setOnDismissListener(
                                        new DialogInterface.OnDismissListener() {
                                            @Override
                                            public void onDismiss(DialogInterface dialogInterface) {
                                                mViewModel.onSaveTranSuccess();
                                            }
                                        });

                                dialog.setCancelable(false);
                                dialog.setCanceledOnTouchOutside(false);

                                dialog.show();
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

    public void onClose() {

        mViewModel.close();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

        mSubscription.clear();
    }

    public void onBack() {

    }
}
