package com.viettel.mbccs.screen.nvtrahangcaptren.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.databinding.ItemExportImportWarehouseBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 6/4/17.
 */

public class StockTransAdapter
        extends RecyclerView.Adapter<StockTransAdapter.StockTransViewHolder> {

    private Context mContext;
    private ArrayList<StockTrans> mStockTranses;

    public StockTransAdapter(Context context, ArrayList<StockTrans> stockTranses) {
        mContext = context;
        mStockTranses = stockTranses;
    }

    @Override
    public StockTransViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StockTransViewHolder((ItemExportImportWarehouseBinding) DataBindingUtil.inflate(
                LayoutInflater.from(mContext), R.layout.item_export_import_warehouse, parent,
                false));
    }

    @Override
    public void onBindViewHolder(StockTransViewHolder holder, int position) {
        holder.bindData(mStockTranses.get(position));
    }

    @Override
    public int getItemCount() {
        return mStockTranses.size();
    }

    class StockTransViewHolder extends RecyclerView.ViewHolder {

        ItemExportImportWarehouseBinding mBinding;
        ItemStockTransPresenter mItemStockTransPresenter;

        public StockTransViewHolder(ItemExportImportWarehouseBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindData(StockTrans stockTrans) {
            mItemStockTransPresenter = new ItemStockTransPresenter(mContext, stockTrans);
            mBinding.setData(mItemStockTransPresenter);
        }
    }
}
