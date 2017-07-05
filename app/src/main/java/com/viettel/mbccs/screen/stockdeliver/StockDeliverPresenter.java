package com.viettel.mbccs.screen.stockdeliver;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.AdapterView;
import com.viettel.mbccs.data.model.Shipment;
import com.viettel.mbccs.screen.stockdeliver.adapter.ShipmentAdapter;
import com.viettel.mbccs.utils.SpinnerAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by buidinhviet on 6/13/17.
 */

public class StockDeliverPresenter implements StockDeliverContract.Presenter {
    private StockDeliverContract.ViewModel mViewModel;
    private ShipmentAdapter mAdapter;
    private Context mContext;
    public ObservableField<SpinnerAdapter<String>> codeStoreReceive = new ObservableField<>();
    protected List<String> mWareHouseData = new ArrayList<>();

    public StockDeliverPresenter(Context context, StockDeliverContract.ViewModel viewModel) {
        mViewModel = viewModel;
        mContext = context;
        fakeData();
        initFields();
        demoDataSearch();
    }

    private void initFields() {
        SpinnerAdapter<String> adapter = new SpinnerAdapter<>(mContext, mWareHouseData);
        adapter.setOnItemSelectedListener(getWareHouseItemSelectedListener());
        codeStoreReceive.set(adapter);
        mAdapter = new ShipmentAdapter();
    }

    private void fakeData() {
        // TODO: 5/31/2017 Fake data
        String[] array = new String[] { "Tất cả", "Kho 1", "Kho 2", "Kho 3", "Kho 4" };
        Collections.addAll(mWareHouseData, array);
    }

    protected AdapterView.OnItemSelectedListener getWareHouseItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    public ShipmentAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void onSearchData() {
        //TODO: call API get list shipment search result
    }

    private void demoDataSearch() {
        List<Shipment> shipments = new ArrayList<>();
        shipments.add(new Shipment("Mã phiếu", "Kênh bách khoa", "16/06/2016"));
        shipments.add(new Shipment("Mã phiếu", "Kênh Hà Nội", "11/06/2017"));
        shipments.add(new Shipment("Mã phiếu", "Kênh HCM", "12/06/2016"));
        shipments.add(new Shipment("Mã phiếu", "Kênh bách khoa", "16/06/2016"));
        shipments.add(new Shipment("Mã phiếu", "Kênh bách khoa", "16/06/2016"));
        mAdapter.setShipmentList(shipments);
    }

    public void onBackClick() {
        mViewModel.onBackPress();
    }

    public void onAddClick() {
        mViewModel.openCreateCommand();
    }
}
