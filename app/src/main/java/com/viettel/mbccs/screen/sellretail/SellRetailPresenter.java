package com.viettel.mbccs.screen.sellretail;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.StockItem;
import com.viettel.mbccs.screen.sellretail.adapter.StockAdapter;
import com.viettel.mbccs.screen.sellretail.sellprogrampicker.SellProgramPickerActivity;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class SellRetailPresenter implements SellRetailContract.Presenter {

    public ObservableField<String> filterText;
    public ObservableField<String> sellProgram;
    private ArrayAdapter<String> mAdapter;
    private List<String> serices = new ArrayList<>();
    public ObservableField<Boolean> isCollapse;
    private StockAdapter stockAdapter;
    private Context mContext;
    private SellRetailContract.ViewModel mViewModel;
    private List<StockItem> stockItems = new ArrayList<>();

    public SellRetailPresenter(Context context, SellRetailContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        fakeData();
        init();
    }

    private void fakeData() {
        serices.add("Mobile");
        serices.add("PC");
        serices.add("XXX");
        serices.add("UYYY");

        StockItem good1 = new StockItem();
        good1.setName("Iphone 7 plus");
        good1.setRemainGoodCount(12);
        good1.setHasSerial(true);
        good1.setGoodPrice(5000000);
        good1.setImageUrl(
                "http://didongthongminh.vn/images/products/2017/03/31/resized/samsung-galaxy-s8-plus-_1490956081.jpg");

        StockItem good2 = new StockItem();
        good2.setName("Samsung J5 Prime");
        good2.setRemainGoodCount(10);
        good2.setHasSerial(false);
        good2.setGoodPrice(400000);
        good2.setImageUrl(
                "https://cdn1.viettelstore.vn/images/Product/ProductImage/small/J5-Prime-A.jpg");

        StockItem good3 = new StockItem();
        good3.setName("Oppo F1s");
        good3.setRemainGoodCount(5);
        good3.setHasSerial(true);
        good3.setGoodPrice(7000000);
        good3.setImageUrl(
                "https://cdn1.viettelstore.vn/images/Product/ProductImage/small/3211396993674.jpg");
        stockItems.add(good1);
        stockItems.add(good2);
        stockItems.add(good3);
    }

    private void init() {
        filterText = new ObservableField<>();
        sellProgram = new ObservableField<>();
        isCollapse = new ObservableField<>();
        isCollapse.set(false);
        mAdapter = new ArrayAdapter<String>(mContext, R.layout.item_spinner, R.id.text, serices);
        stockAdapter = new StockAdapter(mContext, stockItems);
    }

    public void onCollapse() {
        isCollapse.set(!isCollapse.get());
    }

    public void onExpand() {
        isCollapse.set(false);
    }

    public void chooseSellProgram() {
        mContext.startActivity(new Intent(mContext, SellProgramPickerActivity.class));
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public ArrayAdapter<String> getAdapter() {
        return mAdapter;
    }

    public void setAdapter(ArrayAdapter<String> adapter) {
        mAdapter = adapter;
    }

    public StockAdapter getStockAdapter() {
        return stockAdapter;
    }

    public void setStockAdapter(StockAdapter stockAdapter) {
        this.stockAdapter = stockAdapter;
    }
}
