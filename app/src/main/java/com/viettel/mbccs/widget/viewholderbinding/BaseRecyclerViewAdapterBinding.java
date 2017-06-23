package com.viettel.mbccs.widget.viewholderbinding;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.viettel.mbccs.base.BaseViewHolder;
import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.databinding.ItemNoDataListBinding;
import java.util.List;

/**
 * Created by FRAMGIA\hoang.van.cuong on 21/06/2017.
 */

public abstract class BaseRecyclerViewAdapterBinding<T extends BaseViewHolderBinding, K>
        extends RecyclerView.Adapter<BaseViewHolderBinding> {

    public static final int TYPE_NODATA = 0;
    public static final int TYPE_NORMAL = 1;

    public Context mContext;
    public List<K> mList;

    protected abstract T getViewHolder(ViewGroup parent);

    public BaseRecyclerViewAdapterBinding(Context context, List<K> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public BaseViewHolderBinding onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NODATA) {
            getNoDataViewHolder(parent);
        }
        return getViewHolder(parent);
    }

    public BaseViewHolderBinding getNoDataViewHolder(ViewGroup parent) {
        return new NoDataViewHolder(
                ItemNoDataListBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolderBinding holder, int position) {
        if (mList.size() > 0) {
            holder.bindData(mList.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.size() == 0) {
            return TYPE_NODATA;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        if (mList.size() == 0) {
            return 1;
        }
        return mList.size();
    }

    class NoDataViewHolder extends BaseViewHolderBinding<ItemNoDataListBinding, EmptyObject> {

        public NoDataViewHolder(ItemNoDataListBinding binding) {
            super(binding);
        }
    }

    public interface OnRecyclerItemListener<T> {
        void onItemClick(int postition, T data);
    }
}
