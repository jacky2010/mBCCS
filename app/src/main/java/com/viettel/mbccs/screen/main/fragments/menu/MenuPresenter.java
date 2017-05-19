package com.viettel.mbccs.screen.main.fragments.menu;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.data.model.MenuItem;
import com.viettel.mbccs.databinding.ItemMenuBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/11/17.
 */

public class MenuPresenter implements MenuContract.Presenter {

    private Context mContext;

    private MenuContract.ViewModel mViewModel;

    private List<MenuItem> mMenuItemList = new ArrayList<>();

    public MenuPresenter(Context context, MenuContract.ViewModel viewModel, List<MenuItem> list) {
        mContext = context;
        mViewModel = viewModel;
        mMenuItemList = list;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public MenuAdapter getMenuAdapter() {
        return new MenuAdapter(mContext, mMenuItemList);
    }

    public static class MenuAdapter extends RecyclerView.Adapter {

        protected Context mContext;

        protected List<MenuItem> mList;

        public MenuAdapter(Context context, List<MenuItem> list) {
            mContext = context;
            mList = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MenuViewHolder(
                    ItemMenuBinding.inflate(LayoutInflater.from(mContext), parent, false)) {
                @Override
                protected OnMenuClickListener getOnMenuClickListener() {
                    return new OnMenuClickListener() {
                        @Override
                        public void onMenuClick(int id) {
                            switch (id) {
                                case MenuItem.MenuId.MENU_HELP:
                                    break;
                                case MenuItem.MenuId.MENU_SETTING:
                                    break;
                                default:
                                    break;
                            }
                        }
                    };
                }
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((MenuViewHolder) holder).bind(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public static abstract class MenuViewHolder extends RecyclerView.ViewHolder {
            private ItemMenuBinding binding;

            private OnMenuClickListener onMenuClickListener;

            public MenuViewHolder(ItemMenuBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
                onMenuClickListener = getOnMenuClickListener();
            }

            protected abstract OnMenuClickListener getOnMenuClickListener();

            public void bind(final MenuItem item) {
                binding.setText(item.getTitle());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    binding.setImage(
                            binding.getRoot().getResources().getDrawable(item.getIcon(), null));
                } else {
                    binding.setImage(binding.getRoot().getResources().getDrawable(item.getIcon()));
                }
                binding.setTextColor(item.getColor());
                binding.setTextSize(item.getSize());
                binding.setOnClicked(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onMenuClickListener != null) {
                            onMenuClickListener.onMenuClick(item.getId());
                        }
                    }
                });
            }
        }
    }

    public interface OnMenuClickListener {
        void onMenuClick(int id);
    }
}
