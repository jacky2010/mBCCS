package com.viettel.mbccs.screen.serialpicker.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.SerialBO;
import com.viettel.mbccs.databinding.ItemSerialSelectedBinding;
import com.viettel.mbccs.utils.Common;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by eo_cuong on 5/14/17.
 */

public class SerialSelectedAdapter
        extends RecyclerView.Adapter<SerialSelectedAdapter.SerialSelectedViewHolder> {
    private Context mContext;
    private Set<String> mSerials;
    private List<SerialBO> mSerialBlocks = new ArrayList<>();
    private SerialSelectedListener mSerialChooseListener;

    public SerialSelectedAdapter(Context context, Set<String> serialBlocks) {
        mContext = context;
        mSerials = serialBlocks;
        refreshData();
    }

    public void refreshData() {
        mSerialBlocks = Common.getSerialBlockBySerials(new ArrayList<String>(mSerials));
        notifyDataSetChanged();
    }

    @Override
    public SerialSelectedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SerialSelectedViewHolder(
                (ItemSerialSelectedBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_serial_selected, parent, false));
    }

    public void setSerialChooseListener(SerialSelectedListener serialChooseListener) {
        mSerialChooseListener = serialChooseListener;
    }

    @Override
    public void onBindViewHolder(SerialSelectedViewHolder holder, int position) {
        holder.binData(mSerialBlocks.get(position));
    }

    @Override
    public int getItemCount() {
        return mSerialBlocks.size();
    }

    class SerialSelectedViewHolder extends RecyclerView.ViewHolder {

        ItemSerialSelectedBinding mBinding;

        public SerialSelectedViewHolder(ItemSerialSelectedBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.layoutDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mSerialChooseListener != null) {
                        mSerialChooseListener.onDeleteSerial(
                                mSerialBlocks.get(getAdapterPosition()));
                    }
                    notifyDataSetChanged();
                }
            });
        }

        public void binData(SerialBO serialBlock) {
            ItemSerialPresenter itemSerialPresenter = new ItemSerialPresenter();
            itemSerialPresenter.setSerialBlock(serialBlock);
            mBinding.setItem(itemSerialPresenter);
        }
    }

    public interface SerialSelectedListener {
        void onDeleteSerial(SerialBO serialBlock);
    }
}
