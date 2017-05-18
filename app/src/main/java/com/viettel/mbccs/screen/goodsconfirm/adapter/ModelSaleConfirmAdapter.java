package com.viettel.mbccs.screen.goodsconfirm.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.databinding.ItemSaleConfirmBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/14/17.
 */

public class ModelSaleConfirmAdapter
        extends RecyclerView.Adapter<ModelSaleConfirmAdapter.StockConfirmViewHolder> {

    private Context mContext;
    private List<ModelSale> mStockItems;
    private List<ModelSale> mStockItemsFilter=new ArrayList<>();

    public ModelSaleConfirmAdapter(Context context, List<ModelSale> goodItems) {
        mContext = context;
        mStockItems = goodItems;
        for (ModelSale stockItem : mStockItems) {
            if (stockItem.getSerials().size() > 0) {
                mStockItemsFilter.add(stockItem);
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
            holder.bind(mStockItemsFilter.get(position));
    }

    @Override
    public int getItemCount() {
        return mStockItemsFilter.size();
    }

    class StockConfirmViewHolder extends RecyclerView.ViewHolder {

        ItemSaleConfirmBinding mBinding;

        public StockConfirmViewHolder(ItemSaleConfirmBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(ModelSale item) {
            ItemModelSalePresenter itemGoodConfirmPresenter = new ItemModelSalePresenter();
            itemGoodConfirmPresenter.setGoodItem(item);
            mBinding.setItem(itemGoodConfirmPresenter);
        }
    }
}
