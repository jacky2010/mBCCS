package com.viettel.mbccs.base;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.MenuItem;
import com.viettel.mbccs.databinding.ActivitySubMenuBinding;
import com.viettel.mbccs.databinding.ItemImageGridBinding;
import com.viettel.mbccs.databinding.ItemMenuBinding;
import com.viettel.mbccs.screen.main.fragments.menu.MenuPresenter;
import com.viettel.mbccs.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/19/2017.
 */

public abstract class BaseSubMenuActivity extends BaseActivity {

    protected ActivitySubMenuBinding mBinding;

    protected List<MenuItem> mMenuItemList = new ArrayList<>();

    protected GridLayoutManager mGridLayoutManager;

    protected LinearLayoutManager mLinearLayoutManager;

    protected RecyclerView.ItemDecoration mItemDecoration;

    protected MenuPresenter.MenuAdapter mMenuAdapter;

    public ObservableBoolean isGrid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sub_menu);
        isGrid = new ObservableBoolean();
        isGrid.set(false);
        mGridLayoutManager = new GridLayoutManager(this, 3);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mItemDecoration = new SpacesItemDecoration((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimension(R.dimen.dp_6),
                getResources().getDisplayMetrics()), mGridLayoutManager);
        initMenuList();
        mMenuAdapter = new SubMenuAdapter(this, mMenuItemList);
        mBinding.setPresenter(this);
        mBinding.executePendingBindings();
    }

    public abstract void initMenuList();

    public abstract MenuPresenter.OnMenuClickListener getOnMenuClickListener();

    public abstract String getToolbarTitle();

    public void switchView() {
        isGrid.set(!isGrid.get());
        if (isGrid.get()) {
            mBinding.subMenuView.setLayoutManager(mGridLayoutManager);
            mBinding.subMenuView.addItemDecoration(mItemDecoration);
        } else {
            mBinding.subMenuView.setLayoutManager(mLinearLayoutManager);
            mBinding.subMenuView.removeItemDecoration(mItemDecoration);
        }
        mBinding.subMenuView.setAdapter(mMenuAdapter);
    }

    public MenuPresenter.MenuAdapter getMenuAdapter() {
        return mMenuAdapter;
    }

    public class SubMenuAdapter extends MenuPresenter.MenuAdapter {
        public SubMenuAdapter(Context context, List<MenuItem> list) {
            super(context, list);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (!isGrid.get()) {
                return new SubMenuListViewHolder(
                        ItemMenuBinding.inflate(getLayoutInflater(), parent, false));
            } else {
                return new SubMenuGridViewHolder(
                        ItemImageGridBinding.inflate(getLayoutInflater(), parent, false));
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (!isGrid.get()) {
                ((SubMenuListViewHolder) holder).bind(mList.get(position));
            } else {
                ((SubMenuGridViewHolder) holder).bind(mList.get(position));
            }
        }
    }

    public class SubMenuListViewHolder extends MenuPresenter.MenuAdapter.MenuViewHolder {

        public SubMenuListViewHolder(ItemMenuBinding binding) {
            super(binding);
        }

        @Override
        protected MenuPresenter.OnMenuClickListener getOnMenuClickListener() {
            return BaseSubMenuActivity.this.getOnMenuClickListener();
        }
    }

    public class SubMenuGridViewHolder extends RecyclerView.ViewHolder {
        private ItemImageGridBinding binding;

        private MenuPresenter.OnMenuClickListener onMenuClickListener;

        public SubMenuGridViewHolder(ItemImageGridBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            onMenuClickListener = getOnMenuClickListener();
        }

        public void bind(final MenuItem item) {
            binding.setImageText(item.getTitle());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                binding.setImage(
                        binding.getRoot().getResources().getDrawable(item.getIcon(), null));
            } else {
                binding.setImage(binding.getRoot().getResources().getDrawable(item.getIcon()));
            }
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.text.setTextColor(getResources().getColorStateList(item.getColor(), null));
                } else {
                    binding.text.setTextColor(getResources().getColorStateList(item.getColor()));
                }
            } catch (Resources.NotFoundException e) {
                int colorRes = item.getColor();
                if (item.getColor() == 0) colorRes = android.R.color.black;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.text.setTextColor(getResources().getColor(colorRes, null));
                } else {
                    binding.text.setTextColor(getResources().getColor(colorRes));
                }
            }
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
