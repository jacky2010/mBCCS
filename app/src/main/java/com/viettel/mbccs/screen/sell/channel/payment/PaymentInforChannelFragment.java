package com.viettel.mbccs.screen.sell.channel.payment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.constance.PaymentMethod;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.SaleProgram;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.TeleComService;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.databinding.FragmentPaymentInforChannelBinding;
import com.viettel.mbccs.screen.common.success.DialogInputBankPlus;
import com.viettel.mbccs.screen.common.success.DialogInputWellet;
import com.viettel.mbccs.screen.sell.retail.payment.PaymentInforContract;
import com.viettel.mbccs.screen.sell.retail.savetransconfirm.SaveTransConfirmFragment;
import com.viettel.mbccs.utils.ActivityUtils;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import java.util.List;

/**
 * Created by eo_cuong on 5/20/17.
 */

public class PaymentInforChannelFragment extends BaseFragment
        implements PaymentInforChannelContract.ViewModel {

    private FragmentPaymentInforChannelBinding mBinding;
    private PaymentInforChannelPresenter mPresenter;

    private List<StockSerial> mStockSerials;

    private TeleComService mTeleComService;

    private SaleProgram mSaleProgram;

    private ChannelInfo mChannelInfo;

    LinearLayout layoutPaymentCash;

    LinearLayout layoutPaymentBankPlus;

    LinearLayout layoutPaymentWellet;

    RadioGroup mRadioGroupPayment;

    RadioButton radioCash;

    RadioButton radioBankplus;

    RadioButton radioWellet;

    ImageView imageCash;

    ImageView imagebankPlus;

    ImageView imageWellet;

    public static PaymentInforChannelFragment newInstance(List<StockSerial> stockSerials,
            TeleComService telecomService, SaleProgram saleProgram, ChannelInfo channelInfo) {
        Bundle args = new Bundle();
        args.putString(Constants.BundleConstant.STOCK_SERIAL_LIST,
                GsonUtils.Object2String(stockSerials));
        args.putString(Constants.BundleConstant.TELECOMSERIVE,
                GsonUtils.Object2String(telecomService));
        args.putString(Constants.BundleConstant.SALE_PROGRAM, GsonUtils.Object2String(saleProgram));
        args.putString(Constants.BundleConstant.CHANNEL, GsonUtils.Object2String(channelInfo));
        PaymentInforChannelFragment fragment = new PaymentInforChannelFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        String stockSeriralString = bundle.getString(Constants.BundleConstant.STOCK_SERIAL_LIST);
        if (!TextUtils.isEmpty(stockSeriralString)) {
            mStockSerials = GsonUtils.String2ListObject(stockSeriralString, StockSerial[].class);
        }

        String telecomSeriveString = bundle.getString(Constants.BundleConstant.TELECOMSERIVE);
        if (!TextUtils.isEmpty(telecomSeriveString)) {
            mTeleComService = GsonUtils.String2Object(telecomSeriveString, TeleComService.class);
        }

        String saleProgramString = bundle.getString(Constants.BundleConstant.SALE_PROGRAM);
        if (!TextUtils.isEmpty(saleProgramString)) {
            mSaleProgram = GsonUtils.String2Object(saleProgramString, SaleProgram.class);
        }

        String channelString = bundle.getString(Constants.BundleConstant.CHANNEL);
        if (!TextUtils.isEmpty(saleProgramString)) {
            mChannelInfo = GsonUtils.String2Object(channelString, ChannelInfo.class);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment_infor_channel,
                container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mPresenter == null) {
            mPresenter = new PaymentInforChannelPresenter(this, getActivity(), mStockSerials,
                    mTeleComService, mSaleProgram, mChannelInfo);
        }

        mBinding.setPresenter((PaymentInforChannelPresenter) mPresenter);
        layoutPaymentCash = mBinding.paymentCash;
        layoutPaymentBankPlus = mBinding.paymentBankPlus;
        layoutPaymentWellet = mBinding.paymentWellet;
        mRadioGroupPayment = mBinding.radioPayment;
        radioCash = mBinding.radioCash;
        radioWellet = mBinding.radioWellet;
        radioBankplus = mBinding.radioBankPlus;

        layoutPaymentCash.setSelected(true);
        radioCash.setChecked(true);

        imageCash = mBinding.imageCash;
        imagebankPlus = mBinding.imageBankPlus;
        imageWellet = mBinding.imageWellet;

        layoutPaymentCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutPaymentCash.setSelected(true);
                layoutPaymentBankPlus.setSelected(false);
                layoutPaymentWellet.setSelected(false);
                radioCash.setChecked(true);
                radioBankplus.setChecked(false);
                radioWellet.setChecked(false);
                //imageCash.setImageResource(R.drawable.tien_mat_select);
                //imagebankPlus.setImageResource(R.drawable.bank_plus);
                //imageWellet.setImageResource(R.drawable.vi_dien_tu);
                ((PaymentInforChannelPresenter) mPresenter).setPaymentMethod(
                        PaymentMethod.PAYMENT_CASH);
            }
        });

        layoutPaymentBankPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutPaymentCash.setSelected(false);
                layoutPaymentBankPlus.setSelected(true);
                layoutPaymentWellet.setSelected(false);
                radioCash.setChecked(false);
                radioBankplus.setChecked(true);
                radioWellet.setChecked(false);
                //imageCash.setImageResource(R.drawable.tien_mat);
                //imagebankPlus.setImageResource(R.drawable.bank_plus_select);
                //imageWellet.setImageResource(R.drawable.vi_dien_tu);
                DialogInputBankPlus dialogInputBankPlus = new DialogInputBankPlus(getActivity());
                dialogInputBankPlus.setDialogInputListener(
                        new DialogInputBankPlus.DialogInputListener() {
                            @Override
                            public void onDialogDissmiss(String phone) {
                                ActivityUtils.hideKeyboard(getActivity());
                                ((PaymentInforChannelPresenter) mPresenter).setPhone(phone);
                            }
                        });
                dialogInputBankPlus.show();
                ((PaymentInforChannelPresenter) mPresenter).setPaymentMethod(
                        PaymentMethod.PAYMENT_BANK_PLUS);
            }
        });

        layoutPaymentWellet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutPaymentCash.setSelected(false);
                layoutPaymentBankPlus.setSelected(false);
                layoutPaymentWellet.setSelected(true);
                radioCash.setChecked(false);
                radioBankplus.setChecked(false);
                radioWellet.setChecked(true);
                //imageCash.setImageResource(R.drawable.tien_mat);
                //imagebankPlus.setImageResource(R.drawable.bank_plus);
                //imageWellet.setImageResource(R.drawable.vi_dien_tu_select);

                DialogInputWellet dialogInPutWellet = new DialogInputWellet(getActivity());
                dialogInPutWellet.setDialogInputListener(
                        new DialogInputWellet.DialogInputListener() {
                            @Override
                            public void onDialogDissmiss(String phone) {
                                ((PaymentInforChannelPresenter) mPresenter).setPhone(phone);
                            }
                        });
                dialogInPutWellet.show();
                ((PaymentInforChannelPresenter) mPresenter).setPaymentMethod(
                        PaymentMethod.PAYMENT_WELLET);
            }
        });
    }

    @Override
    public void setPresenter(PaymentInforContract.Presenter presenter) {

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
    public void onBack() {
        getActivity().finish();
    }

    @Override
    public void goToSaveTransConfirm(DataRequest<GetInfoSaleTranRequest> request,
            SaleTrans saleTrans, ChannelInfo channelInfo) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("SaveTransConfirmFragment")
                .replace(R.id.container,
                        SaveTransConfirmFragment.newInstance(request.getWsRequest(), saleTrans,
                                channelInfo))
                .commit();
    }
}
