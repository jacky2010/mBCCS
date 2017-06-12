package com.viettel.mbccs.screen.main.fragments.main;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.viettel.mbccs.data.model.HomeModel;
import com.viettel.mbccs.databinding.ItemProgressCircleStatisticBinding;
import com.viettel.mbccs.databinding.ItemProgressStatisticBinding;
import com.viettel.mbccs.databinding.ItemTextComplexStatisticBinding;
import com.viettel.mbccs.databinding.ItemTextStatisticBinding;
import java.util.List;

/**
 * Created by FRAMGIA\vu.viet.anh on 16/05/2017.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_TEXT = 0;

    public static final int TYPE_PROGRESS_BAR = 1;

    public static final int TYPE_PROGRESS_CIRCLE = 2;

    public static final int TYPE_TEXT_COMPLEX = 3;

    private Context mContext;

    private List<HomeModel> mList;

    public MainRecyclerAdapter(Context context, List<HomeModel> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_PROGRESS_BAR:
                ItemProgressStatisticBinding bindingBar =
                        ItemProgressStatisticBinding.inflate(LayoutInflater.from(mContext), parent,
                                false);
                return new ProgressBarViewHolder(bindingBar);
            case TYPE_PROGRESS_CIRCLE:
                ItemProgressCircleStatisticBinding bindingCircle =
                        ItemProgressCircleStatisticBinding.inflate(LayoutInflater.from(mContext),
                                parent, false);
                return new ProgressCircleViewHolder(bindingCircle);
            case TYPE_TEXT_COMPLEX:
                ItemTextComplexStatisticBinding bindingComplex =
                        ItemTextComplexStatisticBinding.inflate(LayoutInflater.from(mContext),
                                parent, false);
                return new TextComplexViewHolder(bindingComplex);
            case TYPE_TEXT:
            default:
                ItemTextStatisticBinding bindingText =
                        ItemTextStatisticBinding.inflate(LayoutInflater.from(mContext), parent,
                                false);
                return new TextViewHolder(bindingText);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_PROGRESS_BAR:
                ((ProgressBarViewHolder) holder).bind(mList.get(position));
                break;
            case TYPE_PROGRESS_CIRCLE:
                ((ProgressCircleViewHolder) holder).bind(mList.get(position));
                break;
            case TYPE_TEXT_COMPLEX:
                ((TextComplexViewHolder) holder).bind(mList.get(position));
                break;
            case TYPE_TEXT:
            default:
                ((TextViewHolder) holder).bind(mList.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {
        private ItemTextStatisticBinding binding;

        public TextViewHolder(ItemTextStatisticBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(HomeModel item) {
            binding.setTextTop(item.getTitle());
            binding.setTextBottom(item.getSubTitle());
        }
    }

    public class ProgressBarViewHolder extends RecyclerView.ViewHolder {
        private ItemProgressStatisticBinding binding;

        public ProgressBarViewHolder(ItemProgressStatisticBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(HomeModel item) {
            binding.setTextTop(item.getTitle());
            binding.setDone(item.getPercentage());
        }
    }

    public class ProgressCircleViewHolder extends RecyclerView.ViewHolder {
        private ItemProgressCircleStatisticBinding binding;

        public ProgressCircleViewHolder(ItemProgressCircleStatisticBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(HomeModel item) {
            binding.setTextTop(item.getTitle());
            binding.setDone(item.getPercentage());
        }
    }

    public class TextComplexViewHolder extends RecyclerView.ViewHolder {
        private ItemTextComplexStatisticBinding binding;

        public TextComplexViewHolder(ItemTextComplexStatisticBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(HomeModel item) {
            binding.setTextTop(item.getTitle());
            binding.setTextTopColor(Color.BLUE);
            binding.setTextBottom(item.getSubTitle());
            binding.setTextBottomColor(Color.RED);
        }
    }
}
