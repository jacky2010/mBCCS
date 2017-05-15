package com.viettel.mbccs.screen.goodsconfirm.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.StockItem;
import com.viettel.mbccs.databinding.ItemGoodConfirmBinding;
import java.util.List;

/**
 * Created by eo_cuong on 5/14/17.
 */

public class GoodsConfirmAdapter
        extends RecyclerView.Adapter<GoodsConfirmAdapter.GoodsConfirmViewHolder> {

    private Context mContext;
    private List<StockItem> mGoodItems;

    public GoodsConfirmAdapter(Context context, List<StockItem> goodItems) {
        mContext = context;
        mGoodItems = goodItems;
    }

    @Override
    public GoodsConfirmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodsConfirmViewHolder(
                (ItemGoodConfirmBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_good_confirm, parent, false));
    }

    @Override
    public void onBindViewHolder(GoodsConfirmViewHolder holder, int position) {
        holder.bind(mGoodItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mGoodItems.size();
    }

    class GoodsConfirmViewHolder extends RecyclerView.ViewHolder {

        ItemGoodConfirmBinding mBinding;

        public GoodsConfirmViewHolder(ItemGoodConfirmBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(StockItem item) {
            ItemGoodConfirmPresenter itemGoodConfirmPresenter = new ItemGoodConfirmPresenter();
            itemGoodConfirmPresenter.setGoodItem(item);
            mBinding.setItem(itemGoodConfirmPresenter);
        }
    }
}
