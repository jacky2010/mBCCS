package com.viettel.mbccs.screen.kpp.order.findstock;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.SaleTranType;
import com.viettel.mbccs.constance.StockTotalType;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockModelRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListStockModelResponse;
import com.viettel.mbccs.screen.kpp.order.findstock.adapter.StockTotalPickerAdapter;
import com.viettel.mbccs.utils.ActivityUtils;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.SpinnerAdapter;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class FindStockPresenter
        implements FindStockContract.Presenter, StockTotalPickerAdapter.OnStockTotalPickListener {

    private Context mContext;
    private FindStockContract.ViewModel mViewModel;
    public ObservableField<Boolean> isCollapse;
    public ObservableField<String> filterText;
    private SpinnerAdapter<String> stockTypeAdapter;
    public ObservableField<String> code;
    public ObservableField<String> codeError;
    public ObservableField<String> name;
    public ObservableField<String> nameError;
    private StockTotalPickerAdapter stockTotalAdapter;
    private ArrayList<StockTotal> mStockTotals = new ArrayList<>();
    private ArrayList<StockTotal> mStockTotalsSaved = new ArrayList<>();
    private DataRequest<GetListStockModelRequest> mGetListStockModelRequestBaseRequest;
    private CompositeSubscription mSubscription;
    private long stockType = -1;
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;
    private UserRepository mUserRepository;

    public FindStockPresenter(Context context, FindStockContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        mSubscription = new CompositeSubscription();
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mUserRepository = UserRepository.getInstance();
        init();
    }

    public void saveStockToList() {
        if (mStockTotals.size() >= 0) {
            for (StockTotal stockTotal : mStockTotals) {
                if (stockTotal.getCountChoice() > 0) {
                    mStockTotalsSaved.add(stockTotal);
                }
            }
        }
    }

    private void init() {
        isCollapse = new ObservableField<>();
        isCollapse.set(false);
        filterText = new ObservableField<>();

        code = new ObservableField<>();
        codeError = new ObservableField<>();
        name = new ObservableField<>();
        nameError = new ObservableField<>();

        stockTypeAdapter = new SpinnerAdapter<>(mContext,
                mContext.getResources().getStringArray(R.array.stock_type));
        stockTotalAdapter = new StockTotalPickerAdapter(mContext, mStockTotals);
        stockTypeAdapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stockTypeSelected(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        stockTotalAdapter.setOnStockTotalPickListener(this);
    }

    public void search() {

        if (!isValidate()) {
            return;
        }

        saveStockToList();

        mGetListStockModelRequestBaseRequest = new DataRequest<>();
        mGetListStockModelRequestBaseRequest.setApiCode(ApiCode.GetListStockModel);
        GetListStockModelRequest request = new GetListStockModelRequest();
        request.setStockModelId(code.get());
        if (stockType != -1) {
            request.setStockTypeId(stockType);
        }
        if (!TextUtils.isEmpty(code.get())) {
            request.setStockModelId(code.get());
        }
        request.setStateId(StockTotalType.TYPE_NEW);
        request.setOwnerType(2L);
        request.setSaleTransType(SaleTranType.SALE_CHANNEL);
        request.setOwnerId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        mGetListStockModelRequestBaseRequest.setParameterApi(request);
        mViewModel.showLoading();
        Subscription subscription = mBanHangKhoTaiChinhRepository.getListStockModel(
                mGetListStockModelRequestBaseRequest)
                .subscribe(new MBCCSSubscribe<GetListStockModelResponse>() {
                    @Override
                    public void onSuccess(GetListStockModelResponse object) {
                        if (object != null && object.getStockTotalList() != null) {
                            if (object.getStockTotalList().size() == 0) {
                                DialogUtils.showDialog(mContext, R.string.common_msg_no_data);
                            }
                            mStockTotals.clear();
                            mStockTotals.addAll(object.getStockTotalList());
                            stockTotalAdapter.notifyDataSetChanged();
                            mViewModel.closeForm();
                            return;
                        }
                        DialogUtils.showDialog(mContext, R.string.common_msg_no_data);
                    }

                    @Override
                    public void onError(BaseException error) {
                        DialogUtils.showDialog(mContext, null, error.getMessage(), null);
                        //fake();
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        mViewModel.hideLoading();
                        ActivityUtils.hideKeyboard((Activity) mContext);
                    }
                });

        mSubscription.add(subscription);
    }

    private boolean isValidate() {
        //codeError.set(null);
        //if (TextUtils.isEmpty(code.get())) {
        //    codeError.set(mContext.getString(R.string.input_empty));
        //    return false;
        //}

        return true;
    }

    public void toogleExpand() {
        isCollapse.set(!isCollapse.get());
        if (isValidate()) {
            refreshTextFilter();
        }
    }

    public void addStock() {
        saveStockToList();
        mViewModel.returnListStockTotal(mStockTotalsSaved);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        mSubscription.clear();
    }

    public ArrayAdapter<String> getStockTypeAdapter() {
        return stockTypeAdapter;
    }

    public void onCancel() {
        ((Activity) mContext).finish();
    }

    public StockTotalPickerAdapter getStockTotalAdapter() {
        return stockTotalAdapter;
    }

    public void refreshTextFilter() {
        String[] arrStockType = mContext.getResources().getStringArray(R.array.stock_type);
        String text = "";
        if (stockType == -1) {
            text += "Tất cả";
        }

        if (stockType == StockTotalType.TYPE_NEW) {
            text += mContext.getString(R.string.common_label_dot) + arrStockType[1];
        }

        if (stockType == StockTotalType.TYPE_FAIL) {
            text += mContext.getString(R.string.common_label_dot) + arrStockType[2];
        }

        if (!TextUtils.isEmpty(code.get())) {
            text += mContext.getString(R.string.common_label_dot) + code.get();
        }

        if (!TextUtils.isEmpty(name.get())) {
            text += mContext.getString(R.string.common_label_dot) + name.get();
        }

        filterText.set(text);
    }

    @Override
    public void stockTypeSelected(int position) {

        if (position == 0) {
            stockType = -1;
        }
        if (position == 1) {
            stockType = StockTotalType.TYPE_NEW;
        }
        if (position == 2) {
            stockType = StockTotalType.TYPE_FAIL;
        }
    }

    @Override
    public void onEdittextFocus() {
        if (!isCollapse.get()) {
            toogleExpand();
        }
    }
}
