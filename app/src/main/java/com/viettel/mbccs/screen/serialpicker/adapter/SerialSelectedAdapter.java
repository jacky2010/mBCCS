package com.viettel.mbccs.screen.serialpicker.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.SerialBlock;
import com.viettel.mbccs.databinding.ItemSerialSelectedBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/14/17.
 */

public class SerialSelectedAdapter
        extends RecyclerView.Adapter<SerialSelectedAdapter.SerialSelectedViewHolder> {
    private Context mContext;
    private List<SerialBlock> mSerialBlocks = new ArrayList<>();
    private SerialSelectedListener mSerialChooseListener;

    public SerialSelectedAdapter(Context context, List<SerialBlock> serialBlocks) {
        mContext = context;
        mSerialBlocks = serialBlocks;
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

        public void binData(SerialBlock serialBlock) {
            ItemSerialPresenter itemSerialPresenter = new ItemSerialPresenter();
            itemSerialPresenter.setSerialBlock(serialBlock);
            mBinding.setItem(itemSerialPresenter);
        }
    }

    public interface SerialSelectedListener {
        void onDeleteSerial(SerialBlock serialBlock);
    }
}
