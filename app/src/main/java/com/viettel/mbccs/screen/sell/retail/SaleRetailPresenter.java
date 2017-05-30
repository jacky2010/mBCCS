package com.viettel.mbccs.screen.sell.retail;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.SaleTranType;
import com.viettel.mbccs.constance.StockTotalType;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.data.model.SaleProgram;
import com.viettel.mbccs.data.model.TeleComService;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.request.GetTotalStockRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetTotalStockResponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
import com.viettel.mbccs.screen.sell.retail.adapter.StockAdapter;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class SaleRetailPresenter
        implements SaleRetailContract.Presenter, StockAdapter.OnStockListener {

    public ObservableField<String> filterText;
    public ObservableField<String> sellProgram;
    private ArrayAdapter<TeleComService> mAdapter;
    public ObservableField<Boolean> isCollapse;
    private StockAdapter stockAdapter;
    private Context mContext;
    private SaleRetailContract.ViewModel mViewModel;
    private List<ModelSale> mModelSales = new ArrayList<>();
    private List<SaleProgram> mSalePrograms = new ArrayList<>();
    private List<TeleComService> mTeleComServices = new ArrayList<>();
    private SaleProgram currentSaleProgram = new SaleProgram();
    private TeleComService currentTelecomService = new TeleComService();
    private int currentStockPosition = -1;
    private UserRepository mUserRepository;
    private DataRequest<GetTelecomServiceAndSaleProgramRequest>
            mGetTelecomServiceAndSaleProgramRequest;
    private DataRequest<GetTotalStockRequest> mGetTotalStockRequest;
    private CompositeSubscription mSubscription;

    public SaleRetailPresenter(Context context, SaleRetailContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        mSubscription = new CompositeSubscription();
        mUserRepository = UserRepository.getInstance();
        init();
        loadServiceAndProgram();
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
        request.setOwnerId(0);
        request.setOwnerType(10);
        request.setStateId(StockTotalType.TYPE_NEW);
        request.setSaleTransType(SaleTranType.SALE_RETAIL);
        //TODO set attribute for request
        mGetTotalStockRequest.setParameterApi(request);
        Subscription subscription = mUserRepository.getModelSales(mGetTotalStockRequest)
                .subscribe(new MBCCSSubscribe<GetTotalStockResponse>() {
                    @Override
                    public void onSuccess(GetTotalStockResponse object) {
                        mModelSales.clear();
                        mModelSales.addAll(object.getModelSaleList());
                        stockAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(BaseException error) {

                        DialogUtils.showDialogError(mContext, null, error.getMessage(), null);
                        //fakeModelSale();
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        mViewModel.hideLoading();
                    }
                });

        mSubscription.add(subscription);
    }

    private void loadServiceAndProgram() {
        mViewModel.showLoading();
        mGetTelecomServiceAndSaleProgramRequest = new DataRequest<>();
        mGetTelecomServiceAndSaleProgramRequest.setUserName("Cuong");
        mGetTelecomServiceAndSaleProgramRequest.setApiCode(ApiCode.GetTelecomServiceAndSaleProgram);
        GetTelecomServiceAndSaleProgramRequest request =
                new GetTelecomServiceAndSaleProgramRequest();
        request.setShopId("123");
        mGetTelecomServiceAndSaleProgramRequest.setParameterApi(request);

        Subscription subscription = mUserRepository.getTelecomserviceAndSaleProgram(
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

                        SaleProgram defaultSaleProgram = new SaleProgram(-1, null,
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
                        //fakeData();
                        //loadModelSale();
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        mViewModel.hideLoading();
                    }
                });

        mSubscription.add(subscription);
    }

    private void fakeModelSale() {

        mModelSales.clear();

        ModelSale good1 = new ModelSale();
        good1.setStockMoldeName("Iphone 7 plus");
        good1.setQuantity(12);
        good1.setPrice(5000000);
        good1.setPathImage1("http://didongthongminh"
                + ".vn/images/products/2017/03/31/resized/samsung-galaxy-s8-plus"
                + "-_1490956081.jpg");

        ModelSale good2 = new ModelSale();
        good2.setStockMoldeName("Samsung J5 Prime");
        good2.setQuantity(10);
        good2.setPrice(400000);
        good2.setPathImage1(
                "https://cdn1.viettelstore.vn/images/Product/ProductImage/small/J5-Prime-A.jpg");

        ModelSale good3 = new ModelSale();
        good3.setStockMoldeName("Oppo F1s");
        good3.setQuantity(5);
        good3.setPrice(7000000);
        good3.setPathImage1(
                "https://cdn1.viettelstore.vn/images/Product/ProductImage/small/3211396993674.jpg");
        mModelSales.add(good1);
        mModelSales.add(good2);
        mModelSales.add(good3);
        stockAdapter.notifyDataSetChanged();
    }

    private void fakeData() {

        SaleProgram sell1 = new SaleProgram(1, "0", "khuyen mai 1");
        SaleProgram sell2 = new SaleProgram(1, "1", "khuyen mai 2");
        SaleProgram sell3 = new SaleProgram(1, "2", "khuyen mai 3");
        mSalePrograms.add(sell1);
        mSalePrograms.add(sell2);
        mSalePrograms.add(sell3);

        TeleComService service2 = new TeleComService(1, "Mobile");
        TeleComService service3 = new TeleComService(2, "PC");
        TeleComService service4 = new TeleComService(3, "OK");
        TeleComService service5 = new TeleComService(4, "Phu kien");
        mTeleComServices.add(service2);
        mTeleComServices.add(service3);
        mTeleComServices.add(service4);
        mTeleComServices.add(service5);

        TeleComService defaultTelecomSercie =
                new TeleComService(-1, mContext.getResources().getString(R.string.all_));
        mTeleComServices.add(0, defaultTelecomSercie);

        SaleProgram defaultSaleProgram =
                new SaleProgram(-1, mContext.getResources().getString(R.string.all_));
        mSalePrograms.add(0, defaultSaleProgram);

        currentSaleProgram = mSalePrograms.get(0);
        currentTelecomService = mTeleComServices.get(0);

        sellProgram.set(currentSaleProgram.getName());
        mAdapter.notifyDataSetChanged();
    }

    private void init() {
        filterText = new ObservableField<>();
        sellProgram = new ObservableField<>();
        isCollapse = new ObservableField<>();
        isCollapse.set(false);
        mAdapter = new ArrayAdapter<TeleComService>(mContext, R.layout.item_spinner, R.id.text,
                mTeleComServices);
        stockAdapter = new StockAdapter(mContext, mModelSales);
        stockAdapter.setOnStockListener(this);
    }

    public void toogleExpand() {
        isCollapse.set(!isCollapse.get());
        changeSearchFilter();
    }

    public void chooseSellProgram() {
        mViewModel.onChooseSaleProgram(mSalePrograms);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

        mSubscription.clear();
    }

    public ArrayAdapter<TeleComService> getAdapter() {
        return mAdapter;
    }

    public void setAdapter(ArrayAdapter<TeleComService> adapter) {
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
        String filter1;
        String filter2;
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

        filterText.set(filter1 + " - " + filter2);
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
        mViewModel.onNext(mModelSales, currentTelecomService, currentSaleProgram);
    }

    public void onCancel() {
        ((Activity) mContext).finish();
    }
}
