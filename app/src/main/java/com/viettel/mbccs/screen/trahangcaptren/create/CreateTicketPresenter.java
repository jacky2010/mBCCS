package com.viettel.mbccs.screen.trahangcaptren.create;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ArrayAdapter;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewPresenter;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.screen.kpp.order.findstock.adapter.StockTotalPickerAdapter;

import java.util.List;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public class CreateTicketPresenter extends BaseSearchListViewPresenter<StockTotal,BaseSearchListViewContract.ViewModel>
        implements CreateTicketContract.Presenter {

    public ObservableInt warehousePosition = new ObservableInt();

    public ObservableField<ArrayAdapter<String>> warehouseAdapter = new ObservableField<>();

    public ObservableField<ArrayAdapter<String>> goodsAdapter = new ObservableField<>();

    public CreateTicketPresenter(Context context, BaseSearchListViewContract.ViewModel viewModel) {
        super(context, viewModel);

        // TODO: 5/31/2017 Fake data
        String[] array = new String[] { "Tất cả", "Kho 1", "Kho 2", "Kho 3", "Kho 4" };
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        warehouseAdapter.set(adapter);

        String[] array1 = new String[] { "NEW", "Cũ", "Sắp hết hạn", "Hỏng", "Trôi bảo hành" };
        ArrayAdapter<String> adapter1 =
                new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, array1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        goodsAdapter.set(adapter1);

        StockTotal stock1 = new StockTotal();
        stock1.setStockModelName("Iphone 7");
        stock1.setQuantity(10);
        stock1.setStockModelCode("XAA-42423");
        StockTotal stock2 = new StockTotal();
        stock2.setStockModelName("Galaxy s8");
        stock2.setQuantity(10);
        stock2.setStockModelCode("XFFFAA-42423");
        StockTotal stock3 = new StockTotal();
        stock3.setStockModelName("Oppo F1s");
        stock3.setQuantity(10);
        stock3.setStockModelCode("CCC-42423");
        StockTotal stock4 = new StockTotal();
        stock4.setStockModelName("LG G5");
        stock4.setQuantity(10);
        listData.add(stock1);
        listData.add(stock2);
        listData.add(stock3);
        listData.add(stock4);
        this.adapter.get().notifyDataSetChanged();
    }

    @Override
    public void doSearch() {

    }

    @Override
    public void onSearchSuccess() {

    }

    @Override
    public void onSearchFail() {

    }

    @Override
    public String getSearchHint() {
        return null;
    }

    @Override
    public String getToolbarTitle() {
        return mContext.getString(
                R.string.activity_create_ticket_return_upper_lap_phieu_xuat_kho_tra_hang_cap_tren);
    }

    @Override
    public void onBackPressed() {
        mViewModel.onBackPressed();
    }

    @Override
    protected RecyclerView.Adapter getListAdapter() {
        return new StockTotalPickerAdapter(mContext, listData);
    }

    @Override
    public String getItemCountString() {
        return mContext.getString(R.string.activity_create_ticket_return_upper_danh_sach_mat_hang,
                itemCount.get());
    }

    @Override
    public void onAddClick() {
    }

    @Override
    public void pickStockTotalListSuccess(List<StockTotal> stockTotals) {
        for (StockTotal stockTotal : stockTotals) {
            for (int i = 0; i < listData.size(); i++) {
                if (listData.get(i).equals(stockTotal)) {
                    listData.get(i)
                            .setCountChoice(
                                    listData.get(i).getCountChoice() + stockTotal.getCountChoice());
                    return;
                }
            }
            listData.add(stockTotal);
        }
        adapter.get().notifyDataSetChanged();
        //TODO add list stock total
    }

    public Spannable getTextFromWarehouse() {
        // TODO: 07/06/2017 Fake data
        String s = mContext.getString(
                R.string.activity_create_ticket_return_upper_lap_phieu_xuat_tu_kho);
        SpannableStringBuilder ssb = new SpannableStringBuilder(s);
        ssb.append(" ");
        ssb.append("Fake kho");
        ssb.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {

            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
                ds.setColor(Color.BLUE);
            }
        }, s.length() + 1, ssb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }

    public String getTitleSpinnerWarehouse() {
        return mContext.getString(R.string.activity_create_ticket_return_upper_ma_kho_nhan);
    }

    public String getTitleSpinnerGoodsStatus() {
        return mContext.getString(
                R.string.activity_create_ticket_return_upper_trang_thai_hang_xuat);
    }

    public void createTicket() {
        ((CreateTicketContract.ViewModel) mViewModel).onCreateTicket();
    }

    public void addGoods() {
        ((CreateTicketContract.ViewModel) mViewModel).onAddGoods();
    }
}
