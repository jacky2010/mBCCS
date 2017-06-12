package com.viettel.mbccs.screen.sell.channel.payment;

import android.content.Context;
import android.databinding.ObservableField;
import android.text.TextUtils;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.SaleTranType;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.model.SaleProgram;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.TeleComService;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetInfoSaleTranResponse;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by eo_cuong on 5/20/17.
 */

public class PaymentInforChannelPresenter implements PaymentInforChannelContract.Presenter {

    public ObservableField<String> tin;
    public ObservableField<String> tinError;
    public ObservableField<String> coupon;
    public ObservableField<String> amount;
    public ObservableField<String> amountNotTax;
    public ObservableField<String> tax;
    public ObservableField<Boolean> isGetTransInfo;
    public ObservableField<Boolean> isExpandCustomerInfo;
    public ObservableField<Boolean> isExpandPaymentInfo;
    public ObservableField<String> channelId;
    public ObservableField<String> channelIdError;
    public ObservableField<String> channelAddress;
    public ObservableField<String> channelAddressError;
    public ObservableField<String> channelName;
    public ObservableField<String> channelNameError;
    public String currentPayment;

    private PaymentInforChannelContract.ViewModel mViewModel;
    private Context mContext;
    private List<StockSerial> mStockSerials;
    private String paymentMethod;
    private String phone;
    private String secureCode;

    private DataRequest<GetInfoSaleTranRequest> mGetInfoSaleTranRequestBaseRequest;
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;

    private CompositeSubscription mSubscriptions;
    private SaleTrans mSaleTrans;
    private TeleComService mTeleComService;
    private SaleProgram mSaleProgram;
    private ChannelInfo mChannelInfo;

    public PaymentInforChannelPresenter(PaymentInforChannelContract.ViewModel viewModel,
            Context context, List<StockSerial> stockSerials, TeleComService teleComService,
            SaleProgram saleProgram, ChannelInfo channelInfo) {
        mViewModel = viewModel;
        mContext = context;
        this.mStockSerials = stockSerials;
        this.mTeleComService = teleComService;
        this.mSaleProgram = saleProgram;
        this.mChannelInfo = channelInfo;
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mSubscriptions = new CompositeSubscription();
        mSaleTrans = new SaleTrans();
        init();
    }

    private void init() {
        tin = new ObservableField<>();
        tinError = new ObservableField<>();
        coupon = new ObservableField<>();
        amount = new ObservableField<>();
        amountNotTax = new ObservableField<>();
        tax = new ObservableField<>();
        isGetTransInfo = new ObservableField<>();
        isGetTransInfo.set(false);
        isExpandCustomerInfo = new ObservableField<>();
        isExpandCustomerInfo.set(true);
        isExpandPaymentInfo = new ObservableField<>();
        isExpandPaymentInfo.set(true);

        channelId = new ObservableField<>();
        channelAddress = new ObservableField<>();
        channelName = new ObservableField<>();
        channelIdError = new ObservableField<>();
        channelAddressError = new ObservableField<>();
        channelNameError = new ObservableField<>();
    }

    public void paymentClick() {
        createTransaction();
    }

    private void createTransaction() {
        if (!isGetTransInfo.get()) {
            getTranInfo();
        } else {
            mViewModel.goToSaveTransConfirm(mGetInfoSaleTranRequestBaseRequest, mSaleTrans,
                    mChannelInfo);
        }
    }

    private void getTranInfo() {
        if (!validate()) {
            return;
        }
        mViewModel.showLoading();
        isGetTransInfo.set(false);

        mGetInfoSaleTranRequestBaseRequest = new DataRequest<>();
        GetInfoSaleTranRequest request = new GetInfoSaleTranRequest();
        request.setPaymentMethod(paymentMethod);
        request.setCouponCode(coupon.get());
        request.setIsdnPay(phone);
        request.setLstSerialSale(mStockSerials);
        if (mTeleComService.getId() != -1) {
            request.setTelecomserviceId(mTeleComService.getId());
        }
        if (mSaleProgram.getId() != -1) {
            request.setSaleProgrameCode((mSaleProgram.getCode()));
        }
        request.setSaleTransType(String.valueOf(SaleTranType.SALE_CHANNEL));
        Customer customer = new Customer();
        customer.setTin(tin.get());
        customer.setAddress(channelAddress.get());
        customer.setCustomerName(channelName.get());
        customer.setCustomerId(channelId.get());
        request.setCustomer(customer);
        request.setChanelId(mChannelInfo.getChannelId());
        request.setChannelType((mChannelInfo.getChannelType()));
        request.setDiscountPolicy(mChannelInfo.getDiscountPolicy());
        request.setPricePolicy(mChannelInfo.getPricePolicy());
        request.setChanelId(mChannelInfo.getChannelId());
        request.setChannelType(mChannelInfo.getChannelType());

        mGetInfoSaleTranRequestBaseRequest.setParameterApi(request);

        Subscription subscription =
                mBanHangKhoTaiChinhRepository.getSaleTransInfo(mGetInfoSaleTranRequestBaseRequest)
                        .subscribe(new MBCCSSubscribe<GetInfoSaleTranResponse>() {
                            @Override
                            public void onSuccess(GetInfoSaleTranResponse object) {
                                isGetTransInfo.set(true);
                                loadAmount(object.getSaleTrans());
                            }

                            @Override
                            public void onError(BaseException error) {
                                DialogUtils.showDialogError(mContext, null, error.getMessage(),
                                        null);
                                //fake
//                                isGetTransInfo.set(true);
//                                SaleTrans sale = new SaleTrans();
//                                sale.setAmountNotTax(12000000);
//                                sale.setTax(123000);
//                                sale.setAmountTax(11000000);
//                                loadAmount(sale);
                            }

                            @Override
                            public void onRequestFinish() {
                                super.onRequestFinish();
                                mViewModel.hideLoading();
                            }
                        });
        mSubscriptions.add(subscription);
    }

    private void loadAmount(SaleTrans saleTran) {
        mSaleTrans = saleTran;
        amountNotTax.set(String.format(mContext.getString(R.string.common_format_money),
                Common.formatDouble(saleTran.getAmountNotTax())));
        amount.set(String.format(mContext.getString(R.string.common_format_money),
                Common.formatDouble(mSaleTrans.getAmountTax())));
        tax.set(String.format(mContext.getString(R.string.common_format_money),
                Common.formatDouble(mSaleTrans.getTax())));
    }

    private boolean validate() {
        tinError.set(null);
        channelIdError.set(null);
        channelNameError.set(null);
        channelAddressError.set(null);

        if (TextUtils.isEmpty(channelId.get())) {
            channelIdError.set(mContext.getResources().getString(R.string.input_empty));
            return false;
        }

        if (TextUtils.isEmpty(channelName.get())) {
            channelNameError.set(mContext.getResources().getString(R.string.input_empty));
            return false;
        }

        if (TextUtils.isEmpty(tin.get())) {
            tinError.set(mContext.getResources().getString(R.string.input_empty));
            return false;
        }

        if (TextUtils.isEmpty(channelAddress.get())) {
            channelAddressError.set(mContext.getResources().getString(R.string.input_empty));
            return false;
        }

        return true;
    }

    public void toogleCustomerInfo() {
        isExpandCustomerInfo.set(!isExpandCustomerInfo.get());
    }

    public void tooglePaymentInfor() {
        isExpandPaymentInfo.set(!isExpandPaymentInfo.get());
    }

    @Override
    public void subscribe() {

    }

    public void onBack() {
        mViewModel.onBack();
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }

    public SaleTrans getSaleTrans() {
        return mSaleTrans;
    }

    public void setSecureCode(String secureCode) {
        this.secureCode = secureCode;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        if (currentPayment != null && currentPayment.equals(paymentMethod)) {
            return;
        }
        this.paymentMethod = paymentMethod;
        currentPayment = paymentMethod;
        isGetTransInfo.set(false);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ChannelInfo getChannelInfo() {
        return mChannelInfo;
    }
}
