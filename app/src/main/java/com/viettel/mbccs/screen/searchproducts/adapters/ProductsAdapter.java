package com.viettel.mbccs.screen.searchproducts.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.SearchProduct;
import com.viettel.mbccs.databinding.ItemProductBinding;

import java.util.List;

/**
 * Created by minhnx on 6/25/17.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>
        implements View.OnFocusChangeListener {

    private Context mContext;
    private List<SearchProduct> mItems;
    private OnItemClickListener mListener;

    public ProductsAdapter(Context context, List<SearchProduct> stockItems) {
        mContext = context;
        mItems = stockItems;
    }

    public void setOnItemClickListener(OnItemClickListener onStockListener) {
        mListener = onStockListener;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public List<SearchProduct> getItems() {
        return mItems;
    }

    public void setItems(List<SearchProduct> stockItems) {
        mItems = stockItems;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductViewHolder(
                (ItemProductBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b && mListener != null) {
            mListener.onItemFocus();
        }
        if (!b) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    notifyDataSetChanged();
                }
            });
        }
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        ItemProductBinding mBinding;
        SearchProduct mItem;

        public ProductViewHolder(final ItemProductBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(SearchProduct item) {
            mItem = item;
            ItemProductPresenter itemProductPresenter = new ItemProductPresenter(mContext, item);
            mBinding.setItem(itemProductPresenter);

            mBinding.llProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener != null)
                        mListener.onItemClick(mItem);
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(SearchProduct item);

        void onItemFocus();
    }
}