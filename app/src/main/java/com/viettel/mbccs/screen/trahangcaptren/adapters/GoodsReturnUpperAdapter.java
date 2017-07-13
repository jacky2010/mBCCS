package com.viettel.mbccs.screen.trahangcaptren.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.databinding.ItemExportModelBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anh Vu Viet on 6/13/2017.
 */

public class GoodsReturnUpperAdapter
        extends RecyclerView.Adapter<GoodsReturnUpperAdapter.GoodsReturnUpperViewHolder> {

    private List<StockTransDetail> mList = new ArrayList<>();

    private Context mContext;

    public GoodsReturnUpperAdapter(Context context, List<StockTransDetail> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public GoodsReturnUpperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodsReturnUpperViewHolder(
                ItemExportModelBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public void onBindViewHolder(GoodsReturnUpperViewHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class GoodsReturnUpperViewHolder extends RecyclerView.ViewHolder {

        private ItemExportModelBinding mBinding;

        public GoodsReturnUpperViewHolder(ItemExportModelBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(StockTransDetail item) {
            mBinding.setCode(item.getStockModelCode());
            mBinding.setNumber(item.getCountChoice());
            mBinding.setTotal((int) item.getQuantity());
        }
    }
}
