package com.viettel.mbccs.screen.goodsconfirm.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.databinding.ItemSaleConfirmBinding;
import com.viettel.mbccs.utils.Common;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/14/17.
 */

public class ModelSaleConfirmAdapter
        extends RecyclerView.Adapter<ModelSaleConfirmAdapter.StockConfirmViewHolder> {

    private Context mContext;
    private List<StockSerial> mStockSerials;
    private List<StockSerial> mStockSerialsFilter = new ArrayList<>();

    public ModelSaleConfirmAdapter(Context context, List<StockSerial> stockSerials) {
        mContext = context;
        mStockSerials = stockSerials;
        for (StockSerial stockItem : mStockSerials) {
            if (Common.getSerialCountByListSerialBlock(stockItem.getSerialBOs()) > 0) {
                mStockSerialsFilter.add(stockItem);
            }
        }
    }

    @Override
    public StockConfirmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StockConfirmViewHolder(
                (ItemSaleConfirmBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_sale_confirm, parent, false));
    }

    @Override
    public void onBindViewHolder(StockConfirmViewHolder holder, int position) {
        holder.bind(mStockSerialsFilter.get(position));
    }

    @Override
    public int getItemCount() {
        return mStockSerialsFilter.size();
    }

    class StockConfirmViewHolder extends RecyclerView.ViewHolder {

        ItemSaleConfirmBinding mBinding;

        public StockConfirmViewHolder(ItemSaleConfirmBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(StockSerial item) {
            ItemModelSalePresenter itemGoodConfirmPresenter = new ItemModelSalePresenter();
            itemGoodConfirmPresenter.setStockSerial(item);
            mBinding.setItem(itemGoodConfirmPresenter);
        }
    }
}
