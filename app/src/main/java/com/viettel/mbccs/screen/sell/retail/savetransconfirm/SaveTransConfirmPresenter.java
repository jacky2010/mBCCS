package com.viettel.mbccs.screen.sell.retail.savetransconfirm;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.ObservableField;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.screen.common.success.DialogFullScreen;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class SaveTransConfirmPresenter implements SaveTransConfirmContract.Presenter {
    private Context mContext;
    private SaleTrans mSaleTrans;
    private BaseRequest<GetInfoSaleTranRequest> mGetInfoSaleTranRequest;
    private SaveTransConfirmContract.ViewModel mViewModel;
    private ChannelInfo mChannelInfo;
    public ObservableField<String> content;
    public CompositeSubscription mSubscription;
    private UserRepository mUserRepository;

    public SaveTransConfirmPresenter(Context context, SaveTransConfirmContract.ViewModel mViewModel,
            SaleTrans saleTrans, BaseRequest<GetInfoSaleTranRequest> getInfoSaleTranRequest,
            ChannelInfo channelInfo) {
        mContext = context;
        mSubscription = new CompositeSubscription();
        mUserRepository = UserRepository.getInstance();
        mSaleTrans = saleTrans;
        this.mChannelInfo = channelInfo;
        this.mViewModel = mViewModel;
        mGetInfoSaleTranRequest = getInfoSaleTranRequest;
        init();
    }

    private void init() {
        content = new ObservableField<>();
        if (mGetInfoSaleTranRequest.getRequest() == null) {
            return;
        }
        String text;
        if (mChannelInfo != null) {
            if (mGetInfoSaleTranRequest.getRequest().getSaleProgrameCode() == -1) {
                text = String.format(mContext.getResources()
                                .getString(R.string.confirm_before_save_trans_msg_no_saleprogram_channel),
                        mChannelInfo.getChannelName(), mChannelInfo.getChannelCode(),
                        Common.formatDouble(mSaleTrans.getAmountTax()),
                        Common.convertMoneyToString(mSaleTrans.getAmountTax()), "Hoang Van Cuong",
                        "01656738962");
            } else {
                text = String.format(mContext.getResources()
                                .getString(R.string.confirm_before_save_trans_msg_channel),
                        mChannelInfo.getChannelName(), mChannelInfo.getChannelCode(),
                        Common.formatDouble(mSaleTrans.getAmountTax()),
                        Common.convertMoneyToString(mSaleTrans.getAmountTax()), "Hoang Van Cuong",
                        "01656738962",
                        String.valueOf(mGetInfoSaleTranRequest.getRequest().getSaleProgrameCode()));
            }
            content.set(text);
            return;
        }

        if (mGetInfoSaleTranRequest.getRequest().getSaleProgrameCode() == -1) {
            text = String.format(mContext.getResources()
                            .getString(R.string.confirm_before_save_trans_msg_no_saleprogram),
                    mGetInfoSaleTranRequest.getRequest().getCustomer().getCustomerName(),
                    mGetInfoSaleTranRequest.getRequest().getCustomer().getTin(),
                    Common.formatDouble(mSaleTrans.getAmountTax()),
                    Common.convertMoneyToString(mSaleTrans.getAmountTax()), "Hoang Van Cuong",
                    "01656738962");
        } else {
            text = String.format(
                    mContext.getResources().getString(R.string.confirm_before_save_trans_msg),
                    mGetInfoSaleTranRequest.getRequest().getCustomer().getCustomerName(),
                    mGetInfoSaleTranRequest.getRequest().getCustomer().getTin(),
                    Common.formatDouble(mSaleTrans.getAmountTax()),
                    Common.convertMoneyToString(mSaleTrans.getAmountTax()), "Hoang Van Cuong",
                    "01656738962",
                    String.valueOf(mGetInfoSaleTranRequest.getRequest().getSaleProgrameCode()));
        }
        content.set(text);
    }

    @Override
    public void saveTransaction() {
        mViewModel.showLoading();
        Subscription subscription = mUserRepository.createSaleTransRetail(mGetInfoSaleTranRequest)
                .subscribe(new MBCCSSubscribe<SaleTrans>() {
                    @Override
                    public void onSuccess(SaleTrans object) {
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
                        onSuccess(null);

                        // DialogUtils.showDialogError(mContext, null, error.getMessage(), null);
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
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
