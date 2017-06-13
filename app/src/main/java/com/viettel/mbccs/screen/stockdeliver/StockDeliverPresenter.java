package com.viettel.mbccs.screen.stockdeliver;

import android.databinding.ObservableField;
import com.viettel.mbccs.data.model.Shipment;
import com.viettel.mbccs.screen.stockdeliver.adapter.ShipmentAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by buidinhviet on 6/13/17.
 */

public class StockDeliverPresenter implements StockDeliverContract.Presenter {
    private StockDeliverContract.ViewModel mViewModel;
    public ObservableField<String> countShipment;
    private ShipmentAdapter mAdapter;

    public StockDeliverPresenter(StockDeliverContract.ViewModel viewModel) {
        mViewModel = viewModel;
        initFields();
        demoDataSearch();
    }

    private void initFields() {
        countShipment = new ObservableField<>();
        mAdapter = new ShipmentAdapter();
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

    }
}
