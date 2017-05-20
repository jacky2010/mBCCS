package com.viettel.mbccs.screen.sell.channel.channelpicker;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.databinding.ItemChannelBinding;
import java.util.List;

/**
 * Created by eo_cuong on 5/20/17.
 */

public class ChannelAdaper extends RecyclerView.Adapter<ChannelAdaper.ChannelViewHolder> {
    private List<ChannelInfo> mChannelInfos;

    private onChannelListener mOnChannelListener;

    public ChannelAdaper(List<ChannelInfo> sellPrograms) {
        mChannelInfos = sellPrograms;
    }

    @Override
    public ChannelAdaper.ChannelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChannelAdaper.ChannelViewHolder((ItemChannelBinding) DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.item_channel, parent, false));
    }

    @Override
    public void onBindViewHolder(ChannelAdaper.ChannelViewHolder holder, int position) {
        holder.bind(mChannelInfos.get(position));
    }

    @Override
    public int getItemCount() {
        return mChannelInfos.size();
    }

    public onChannelListener getOnChannelListener() {
        return mOnChannelListener;
    }

    public void setOnChannelListener(onChannelListener onChannelListener) {
        mOnChannelListener = onChannelListener;
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {

        ItemChannelBinding mBinding;

        public ChannelViewHolder(ItemChannelBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnChannelListener != null) {
                        mOnChannelListener.onItemClick(mChannelInfos.get(getAdapterPosition()));
                    }
                }
            });
        }

        public void bind(ChannelInfo channelInfo) {
            mBinding.setItem(channelInfo);
        }
    }

    public interface onChannelListener {
        void onItemClick(ChannelInfo channelInfo);
    }
}
