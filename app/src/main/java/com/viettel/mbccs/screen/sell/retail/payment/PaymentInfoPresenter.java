package com.viettel.mbccs.screen.sell.retail.payment;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.text.TextUtils;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.constance.PaymentMethod;
import com.viettel.mbccs.constance.PriceType;
import com.viettel.mbccs.constance.SaleTranType;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.model.SaleProgram;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.TelecomService;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetInfoSaleTranResponse;
import com.viettel.mbccs.utils.ActivityUtils;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by eo_cuong on 5/16/17.
 */

public class PaymentInfoPresenter implements PaymentInforContract.Presenter {

    public ObservableField<String> name;
    public ObservableField<String> tin;
    public ObservableField<String> address;
    public ObservableField<String> nameError;
    public ObservableField<String> tinError;
    public ObservableField<String> addressError;
    public ObservableField<String> coupon;
    public ObservableField<String> amount;
    public ObservableField<String> amountNotTax;
    public ObservableField<String> tax;
    public ObservableField<Boolean> isGetTransInfo;
    public ObservableField<Boolean> isExpandCustomerInfo;
    public ObservableField<Boolean> isExpandPaymentInfo;
    private PaymentInforContract.ViewModel mViewModel;
    private Context mContext;
    private List<StockSerial> mStockSerials;
    private String phone = null;
    private String secureCode;
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;
    private UserRepository mUserRepository;
    private DataRequest<GetInfoSaleTranRequest> mGetInfoSaleTranRequestBaseRequest;
    private CompositeSubscription mSubscriptions;
    private SaleTrans mSaleTrans;
    private TelecomService mTeleComService;
    private SaleProgram mSaleProgram;
    private String currentPayment = PaymentMethod.PAYMENT_CASH;
    private String paymentMethod = PaymentMethod.PAYMENT_CASH;

    //cache
    public static Customer cacheCustomer;
    public static String cacheCoupon;
    public static String cachepaymentMethod;
    public static String cachePhone;

    public static void clearCache() {
        cacheCustomer = null;
        cacheCoupon = null;
        cachepaymentMethod = null;
        cachePhone = null;
    }

    public PaymentInfoPresenter(PaymentInforContract.ViewModel viewModel, Context context,
            List<StockSerial> stockSerials, TelecomService teleComService,
            SaleProgram saleProgram) {
        mViewModel = viewModel;
        mContext = context;
        this.mStockSerials = stockSerials;
        this.mTeleComService = teleComService;
        this.mSaleProgram = saleProgram;
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mUserRepository = UserRepository.getInstance();
        mSubscriptions = new CompositeSubscription();
        mSaleTrans = new SaleTrans();
        init();
    }

    private void init() {
        if (cacheCustomer == null) {
            cacheCustomer = new Customer();
        }
        isGetTransInfo = new ObservableField<>();
        isGetTransInfo.set(false);
        name = new ObservableField<String>() {
            @Override
            public void set(String value) {
                super.set(value);
                isGetTransInfo.set(false);
                cacheCustomer.setCustomerName(value);
            }
        };
        name.set(cacheCustomer.getCustomerName());
        tin = new ObservableField<String>() {
            @Override
            public void set(String value) {
                super.set(value);
                cacheCustomer.setTin(value);
            }
        };
        tin.set(cacheCustomer.getTin());
        address = new ObservableField<String>() {
            @Override
            public void set(String value) {
                super.set(value);
                isGetTransInfo.set(false);
                cacheCustomer.setAddress(value);
            }
        };
        address.set(cacheCustomer.getAddress());
        nameError = new ObservableField<>();
        tinError = new ObservableField<>();
        addressError = new ObservableField<>();
        coupon = new ObservableField<String>() {
            @Override
            public void set(String value) {
                super.set(value);
                cacheCoupon = value;
            }
        };
        amount = new ObservableField<>();
        amountNotTax = new ObservableField<>();
        tax = new ObservableField<>();

        isExpandCustomerInfo = new ObservableField<>();
        isExpandCustomerInfo.set(true);
        isExpandPaymentInfo = new ObservableField<>();
        isExpandPaymentInfo.set(true);

        if (!TextUtils.isEmpty(cachePhone)) {
            phone = cachePhone;
        }

        if (!TextUtils.isEmpty(cachepaymentMethod)) {
            paymentMethod = cachepaymentMethod;
            currentPayment = cachepaymentMethod;
            mViewModel.initPaymentMethod(paymentMethod);
        }

        if (!TextUtils.isEmpty(cacheCoupon)) {
            coupon.set(cacheCoupon);
        }
    }

    public void paymentClick() {
        ActivityUtils.hideKeyboard((Activity) mContext);
        if (!validate()) {
            return;
        }
        createTransaction();
    }

    private void createTransaction() {
        if (!isGetTransInfo.get()) {
            getTranInfo();
        } else {
            mViewModel.goToSaveTransConfirm(mGetInfoSaleTranRequestBaseRequest, mSaleTrans);
        }
    }

    private void getTranInfo() {
        mViewModel.showLoading();
        isGetTransInfo.set(false);

        mGetInfoSaleTranRequestBaseRequest = new DataRequest<>();
        mGetInfoSaleTranRequestBaseRequest.setWsCode(WsCode.GetSaleTransInfo);
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
        request.setSaleTransType(String.valueOf(SaleTranType.SALE_RETAIL));
        request.setShopId(Long.parseLong(mUserRepository.getUserInfo().getShop().getShopId()));
        request.setPriceType(PriceType.PRICE_RETAIL);
        request.setStaffId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        Customer customer = new Customer();
        customer.setTin(tin.get());
        customer.setAddress(address.get());
        customer.setCustomerName(name.get());
        request.setCustomer(customer);

        mGetInfoSaleTranRequestBaseRequest.setWsRequest(request);

        Subscription subscription =
                mBanHangKhoTaiChinhRepository.getSaleTransInfo(mGetInfoSaleTranRequestBaseRequest)
                        .subscribe(new MBCCSSubscribe<GetInfoSaleTranResponse>() {
                            @Override
                            public void onSuccess(GetInfoSaleTranResponse object) {
                                if (object != null && object.getSaleTrans() != null) {
                                    isGetTransInfo.set(true);
                                    loadAmount(object.getSaleTrans());
                                    return;
                                }
                                onError(new Throwable());
                            }

                            @Override
                            public void onError(BaseException error) {
                                DialogUtils.showDialog(mContext, null, error.getMessage(), null);
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
        boolean isValid = true;
        addressError.set(null);
        tinError.set(null);
        nameError.set(null);
        if (TextUtils.isEmpty(name.get())) {
            nameError.set(mContext.getResources().getString(R.string.input_empty));
            isValid = false;
        }

        if (TextUtils.isEmpty(address.get())) {
            addressError.set(mContext.getResources().getString(R.string.input_empty));
            isValid = false;
        }

        if (TextUtils.isEmpty(phone)) {
            if (paymentMethod == PaymentMethod.PAYMENT_BANK_PLUS) {
                mViewModel.openBankplus();
                isValid = false;
            }

            if (paymentMethod == PaymentMethod.PAYMENT_WELLET) {
                mViewModel.openWellet();
                isValid = false;
            }
        }
        //        if (TextUtils.isEmpty(tin.get().trim())) {
        //            tinError.set(mContext.getResources().getString(R.string.input_empty));
        //            return false;
        //        }

        return isValid;
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
        cachepaymentMethod = currentPayment;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        cachePhone = phone;
    }
}
