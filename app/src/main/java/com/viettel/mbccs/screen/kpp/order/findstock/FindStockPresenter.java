package com.viettel.mbccs.screen.kpp.order.findstock;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.StockStateId;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockModelRequest;
import com.viettel.mbccs.screen.kpp.order.findstock.adapter.StockTotalPickerAdapter;
import com.viettel.mbccs.utils.SpinnerAdapter;
import java.util.ArrayList;
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
    private ArrayList<StockTotal> mStockTotals;
    private ArrayList<StockTotal> mStockTotalsSaved = new ArrayList<>();
    private DataRequest<GetListStockModelRequest> mGetListStockModelRequestBaseRequest;
    private CompositeSubscription mSubscription;
    private long stockType = -1;
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;
    private UserRepository mUserRepository;

    public FindStockPresenter(Context context, FindStockContract.ViewModel viewModel,
            ArrayList<StockTotal> stockTotals) {
        mContext = context;
        mViewModel = viewModel;
        mSubscription = new CompositeSubscription();
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mUserRepository = UserRepository.getInstance();
        this.mStockTotals = stockTotals;
        if (mStockTotals == null) {
            return;
        }
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
        code.set("");
        codeError = new ObservableField<>();
        name = new ObservableField<>();
        name.set("");
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
        //if (!isValidate()) {
        //    return;
        //}
        stockTotalAdapter.filter(stockType, code.get(), name.get());
        mViewModel.closeForm();
    }

    private boolean isValidate() {
        codeError.set(null);
        if (TextUtils.isEmpty(code.get())) {
            codeError.set(mContext.getString(R.string.input_empty));
            return false;
        }

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

        if (stockType == StockStateId.TYPE_NEW) {
            text += mContext.getString(R.string.common_label_dot) + arrStockType[1];
        }

        if (stockType == StockStateId.TYPE_FAIL) {
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
            stockType = StockStateId.TYPE_NEW;
        }
        if (position == 2) {
            stockType = StockStateId.TYPE_FAIL;
        }
    }

    @Override
    public void onEdittextFocus() {
    }
}
