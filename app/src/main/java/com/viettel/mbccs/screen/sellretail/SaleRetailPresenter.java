package com.viettel.mbccs.screen.sellretail;

import android.content.Context;
import android.databinding.ObservableField;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.data.model.SaleProgram;
import com.viettel.mbccs.data.model.TeleComService;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.screen.sellretail.adapter.StockAdapter;
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
    private List<String> serices = new ArrayList<>();
    public ObservableField<Boolean> isCollapse;
    private StockAdapter stockAdapter;
    private Context mContext;
    private SaleRetailContract.ViewModel mViewModel;
    private List<ModelSale> stockItems = new ArrayList<>();
    private List<SaleProgram> mSalePrograms = new ArrayList<>();
    private List<TeleComService> mTeleComServices = new ArrayList<>();
    private SaleProgram currentSaleProgram = new SaleProgram();
    private TeleComService currentTelecomService = new TeleComService();
    private int currentStockPosition = -1;
    private UserRepository mUserRepository;
    private BaseRequest<GetTelecomServiceAndSaleProgramRequest>
            mGetTelecomServiceAndSaleProgramRequest;
    private CompositeSubscription mSubscription;

    public SaleRetailPresenter(Context context, SaleRetailContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        mSubscription = new CompositeSubscription();
        mUserRepository = UserRepository.getInstance();
        init();
        loadServiceAndProgram();
    }

    private void loadServiceAndProgram() {

        mGetTelecomServiceAndSaleProgramRequest = new BaseRequest<>();
        mGetTelecomServiceAndSaleProgramRequest.setWsCode(WsCode.GetTelecomServiceAndSaleProgram);
        GetTelecomServiceAndSaleProgramRequest request =
                new GetTelecomServiceAndSaleProgramRequest();
        mGetTelecomServiceAndSaleProgramRequest.setRequest(request);

        Subscription subscription = mUserRepository.getTelecomserviceAndSaleProgram(
                mGetTelecomServiceAndSaleProgramRequest)
                .subscribe(new MBCCSSubscribe<TelecomServiceAndSaleProgramResponse>() {
                    @Override
                    public void onSuccess(TelecomServiceAndSaleProgramResponse object) {
                        mTeleComServices.addAll(object.getTeleComServices());
                        mSalePrograms.addAll(object.getSalePrograms());
                        mAdapter.notifyDataSetChanged();
                        TeleComService defaultTelecomSercie = new TeleComService(0,
                                mContext.getResources().getString(R.string.all_));
                        mTeleComServices.add(0, defaultTelecomSercie);

                        SaleProgram defaultSaleProgram = new SaleProgram(0,
                                mContext.getResources().getString(R.string.all_));
                        mSalePrograms.add(0, defaultSaleProgram);

                        currentSaleProgram = mSalePrograms.get(0);
                        currentTelecomService = mTeleComServices.get(0);

                        sellProgram.set(currentSaleProgram.getName());
                    }

                    @Override
                    public void onError(BaseException error) {
                        DialogUtils.showDialogError(mContext, null, error.getMessage(), null);
                        fakeData();
                    }
                });

        mSubscription.add(subscription);
    }

    private void fakeData() {

        TeleComService service2 = new TeleComService(1, "Mobile");
        TeleComService service3 = new TeleComService(2, "PC");
        TeleComService service4 = new TeleComService(3, "OK");
        TeleComService service5 = new TeleComService(4, "Phu kien");
        mTeleComServices.add(service2);
        mTeleComServices.add(service3);
        mTeleComServices.add(service4);
        mTeleComServices.add(service5);

        ModelSale good1 = new ModelSale();
        good1.setName("Iphone 7 plus");
        good1.setRemainGoodCount(12);
        good1.setHasSerial(true);
        good1.setGoodPrice(5000000);
        good1.setImageUrl("http://didongthongminh"
                + ".vn/images/products/2017/03/31/resized/samsung-galaxy-s8-plus"
                + "-_1490956081.jpg");

        ModelSale good2 = new ModelSale();
        good2.setName("Samsung J5 Prime");
        good2.setRemainGoodCount(10);
        good2.setHasSerial(false);
        good2.setGoodPrice(400000);
        good2.setImageUrl(
                "https://cdn1.viettelstore.vn/images/Product/ProductImage/small/J5-Prime-A.jpg");

        ModelSale good3 = new ModelSale();
        good3.setName("Oppo F1s");
        good3.setRemainGoodCount(5);
        good3.setHasSerial(true);
        good3.setGoodPrice(7000000);
        good3.setImageUrl(
                "https://cdn1.viettelstore.vn/images/Product/ProductImage/small/3211396993674.jpg");
        stockItems.add(good1);
        stockItems.add(good2);
        stockItems.add(good3);

        SaleProgram sell1 = new SaleProgram(1, "khuyen mai 1");
        SaleProgram sell2 = new SaleProgram(1, "khuyen mai 2");
        SaleProgram sell3 = new SaleProgram(1, "khuyen mai 3");
        mSalePrograms.add(sell1);
        mSalePrograms.add(sell2);
        mSalePrograms.add(sell3);

        TeleComService defaultTelecomSercie =
                new TeleComService(0, mContext.getResources().getString(R.string.all_));
        mTeleComServices.add(0, defaultTelecomSercie);

        SaleProgram defaultSaleProgram =
                new SaleProgram(0, mContext.getResources().getString(R.string.all_));
        mSalePrograms.add(0, defaultSaleProgram);

        currentSaleProgram = mSalePrograms.get(0);
        currentTelecomService = mTeleComServices.get(0);

        sellProgram.set(currentSaleProgram.getName());
    }

    private void init() {
        filterText = new ObservableField<>();
        sellProgram = new ObservableField<>();
        isCollapse = new ObservableField<>();
        isCollapse.set(false);
        mAdapter = new ArrayAdapter<TeleComService>(mContext, R.layout.item_spinner, R.id.text,
                mTeleComServices);
        stockAdapter = new StockAdapter(mContext, stockItems);
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

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

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
    }

    @Override
    public void onItemServiceClick(int position) {
        currentTelecomService = mTeleComServices.get(position);
    }

    @Override
    public void onSerialPickerSuccess(List<String> serials) {
        stockItems.get(currentStockPosition).setSerials(serials);
        stockAdapter.notifyItemChanged(currentStockPosition);
    }

    public void changeSearchFilter() {
        String filter1;
        String filter2;
        if (currentTelecomService.getId() == 0) {
            filter1 = mContext.getResources().getString(R.string.all);
        } else {
            filter1 = currentTelecomService.getName();
        }
        if (currentSaleProgram.getId() == 0) {
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

    public void onNext() {
        mViewModel.onNext(stockItems);
    }
}
