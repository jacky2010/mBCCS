package com.viettel.mbccs.screen.sell.retail.payment;

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
import com.viettel.mbccs.data.model.SaleProgram;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.TeleComService;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.databinding.FragmentPaymentInforRetailBinding;
import com.viettel.mbccs.screen.common.success.DialogInputBankPlus;
import com.viettel.mbccs.screen.common.success.DialogInputWellet;
import com.viettel.mbccs.screen.sell.retail.savetransconfirm.SaveTransConfirmFragment;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import java.util.List;

/**
 * Created by eo_cuong on 5/16/17.
 */

public class PaymentInforRetailFragment extends BaseFragment
        implements PaymentInforContract.ViewModel {

    private FragmentPaymentInforRetailBinding mBinding;
    private PaymentInfoPresenter mPresenter;

    private List<StockSerial> mStockSerials;

    private TeleComService mTeleComService;

    private SaleProgram mSaleProgram;

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

    public static PaymentInforRetailFragment newInstance(List<StockSerial> stockSerials,
            TeleComService telecomService, SaleProgram saleProgram) {
        Bundle args = new Bundle();
        args.putString(Constants.BundleConstant.STOCK_SERIAL_LIST,
                GsonUtils.Object2String(stockSerials));
        args.putString(Constants.BundleConstant.TELECOMSERIVE,
                GsonUtils.Object2String(telecomService));
        args.putString(Constants.BundleConstant.SALE_PROGRAM, GsonUtils.Object2String(saleProgram));
        PaymentInforRetailFragment fragment = new PaymentInforRetailFragment();
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



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_payment_infor_retail, container,
                        false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mPresenter==null){
            mPresenter = new PaymentInfoPresenter(this, getActivity(), mStockSerials, mTeleComService,
                    mSaleProgram);
        }

        mBinding.setPresenter((PaymentInfoPresenter) mPresenter);
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
                imageCash.setImageResource(R.drawable.tien_mat_select);
                imagebankPlus.setImageResource(R.drawable.bank_plus);
                imageWellet.setImageResource(R.drawable.vi_dien_tu);
                ((PaymentInfoPresenter) mPresenter).setPaymentMethod(PaymentMethod.PAYMENT_CASH);
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
                imageCash.setImageResource(R.drawable.tien_mat);
                imagebankPlus.setImageResource(R.drawable.bank_plus_select);
                imageWellet.setImageResource(R.drawable.vi_dien_tu);
                DialogInputBankPlus dialogInputBankPlus = new DialogInputBankPlus(getActivity());
                dialogInputBankPlus.setDialogInputListener(
                        new DialogInputBankPlus.DialogInputListener() {
                            @Override
                            public void onDialogDissmiss(String phone, String secureCode) {
                                ((PaymentInfoPresenter) mPresenter).setPhone(phone);
                                ((PaymentInfoPresenter) mPresenter).setSecureCode(secureCode);
                            }
                        });
                dialogInputBankPlus.show();
                ((PaymentInfoPresenter) mPresenter).setPaymentMethod(PaymentMethod.PAYMENT_CASH);
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
                imageCash.setImageResource(R.drawable.tien_mat);
                imagebankPlus.setImageResource(R.drawable.bank_plus);
                imageWellet.setImageResource(R.drawable.vi_dien_tu_select);

                DialogInputWellet dialogInPutWellet = new DialogInputWellet(getActivity());
                dialogInPutWellet.setDialogInputListener(
                        new DialogInputWellet.DialogInputListener() {
                            @Override
                            public void onDialogDissmiss(String phone) {
                                ((PaymentInfoPresenter) mPresenter).setPhone(phone);
                            }
                        });
                dialogInPutWellet.show();
                ((PaymentInfoPresenter) mPresenter).setPaymentMethod(PaymentMethod.PAYMENT_CASH);
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
    public void goToSaveTransConfirm(BaseRequest<GetInfoSaleTranRequest> request, SaleTrans saleTrans) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("SaveTransConfirmFragment")
                .replace(R.id.container, SaveTransConfirmFragment.newInstance(request, saleTrans,null))
                .commit();
    }
}
