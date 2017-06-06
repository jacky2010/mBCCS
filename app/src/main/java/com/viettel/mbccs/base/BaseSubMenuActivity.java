package com.viettel.mbccs.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.ObservableBoolean;
import android.os.Build;
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
import com.viettel.mbccs.screen.assigntask.ListAssignTaskActivity;
import com.viettel.mbccs.screen.billing.BillingActivity;
import com.viettel.mbccs.screen.branches.BranchesActivity;
import com.viettel.mbccs.screen.change.installation.InstallationAddressActivity;
import com.viettel.mbccs.screen.changesim.ChangeSimActivity;
import com.viettel.mbccs.screen.information.CreateUpdateInformationActivity;
import com.viettel.mbccs.screen.inputorder.InputOrderActivity;
import com.viettel.mbccs.screen.kpp.order.KPPOrderActivity;
import com.viettel.mbccs.screen.main.fragments.menu.MenuPresenter;
import com.viettel.mbccs.screen.sell.channel.SaleChannelActivity;
import com.viettel.mbccs.screen.sell.orders.SellOrdersActivity;
import com.viettel.mbccs.screen.sell.retail.SaleRetailActivity;
import com.viettel.mbccs.screen.sellanypay.SellAnyPayActivity;
import com.viettel.mbccs.screen.transferanypay.TransferAnyPayActivity;
import com.viettel.mbccs.screen.viewwarehouse.ViewWarehouseActivity;
import com.viettel.mbccs.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/19/2017.
 */

public class BaseSubMenuActivity
        extends BaseDataBindActivity<ActivitySubMenuBinding, BaseSubMenuActivity> {

    public static final String EXTRA_MENU_ITEM = "EXTRA_MENU_ITEM";

    protected MenuItem mMenuItem;

    protected List<MenuItem> mMenuItemList = new ArrayList<>();

    protected GridLayoutManager mGridLayoutManager;

    protected LinearLayoutManager mLinearLayoutManager;

    protected RecyclerView.ItemDecoration mItemDecoration;

    protected MenuPresenter.MenuAdapter mMenuAdapter;

    protected MenuPresenter.OnMenuClickListener mOnMenuClickListener;

    public ObservableBoolean isGrid;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_sub_menu;
    }

    @Override
    protected void initData() {
        mMenuItem = getIntent().getParcelableExtra(EXTRA_MENU_ITEM);
        isGrid = new ObservableBoolean();
        isGrid.set(false);
        mGridLayoutManager = new GridLayoutManager(this, 3);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mItemDecoration = new SpacesItemDecoration(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.dp_6),
                        getResources().getDisplayMetrics()), mGridLayoutManager);
        initMenuList();
        mMenuAdapter = new SubMenuAdapter(this, mMenuItemList);
        mBinding.setPresenter(this);
        mBinding.executePendingBindings();
    }

    public void initMenuList() {
        // FIXME: Fake data
        switch (mMenuItem.getId()) {
            case MenuItem.MenuId.MENU_DAU_NOI_QUAN_LY_KHACH_HANG:
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_dau_noi_di_dong), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_dau_noi_co_dinh), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_DANG_KY_THONG_TIN,
                        getString(R.string.menu_dang_ky_thong_tin), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_CAP_NHAT_THONG_TIN,
                        getString(R.string.menu_cap_nhat_thong_tin), R.drawable.ic_add_black_24dp,
                        0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_CHANGE_SIM,
                        getString(R.string.menu_doi_sim), R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_TD_LAP_DAT,
                        getString(R.string.menu_thay_doi_dia_chi_lap_dat),
                        R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_quan_ly_cong_viec), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_GIAO_VIEC_PHAT_SINH,
                        getString(R.string.menu_giao_viec_phat_sinh), R.drawable.ic_add_black_24dp,
                        0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_GIAO_VIEC_CS_KPP,
                        getString(R.string.menu_giao_viec_cs_kpp), R.drawable.ic_add_black_24dp, 0,
                        0));
                break;
            case MenuItem.MenuId.MENU_QUAN_LY_THU_CUOC:
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_chuc_nang_xac_minh), R.drawable.ic_add_black_24dp,
                        0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_chuc_nang_gach_no), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_chuc_nang_tra_cuoc_nong),
                        R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_chuc_nang_quan_ly_tien_do_thu_cuoc),
                        R.drawable.ic_add_black_24dp, 0, 0));
                break;
            case MenuItem.MenuId.MENU_BAN_HANG_KHO_TAI_CHINH:
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_SALE_RETAIL,
                        getString(R.string.menu_ban_le), R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_SALE_CHANNEL,
                        getString(R.string.menu_ban_cho_kenh), R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(
                        new MenuItem(MenuItem.MenuId.MENU_BAN_HANG_THEO_DON_PHE_DUYET_DON_HANG,
                                getString(R.string.menu_ban_hang_theo_don),
                                R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_ban_dich_vu_vas), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_lap_hoa_don), R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_SELL_ANYPAY,
                        getString(R.string.menu_ban_anypay), R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_TRANSFER_ANYPAY,
                        getString(R.string.menu_nap_chuyen_anypay), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_BAN_HANG_THEO_DON_PHE_DUYET_DON_HANG,
                        getString(R.string.menu_ban_hang_theo_don), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_ban_dich_vu_vas), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_LAP_HOA_DON,
                        getString(R.string.menu_lap_hoa_don), R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_SELL_ANYPAY,
                        getString(R.string.menu_ban_anypay), R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_TRANSFER_ANYPAY,
                        getString(R.string.menu_nap_chuyen_anypay), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_XEM_KHO,
                        getString(R.string.menu_xem_kho), R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(
                        new MenuItem(MenuItem.MenuId.MENU_XEM_KHO, getString(R.string.menu_xem_kho),
                                R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_NHAP_HOA_DON,
                        getString(R.string.menu_nhap_hoa_don), R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_xuat_kho_cap_duoi), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_nhap_kho_cap_tren), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_tra_hang_cap_tren), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_nhap_kho_cap_duoi), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_xuat_kho_cho_nhan_vien),
                        R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_nv_xac_nhan_hang), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_nv_tra_hang_cap_tren), R.drawable.ic_add_black_24dp,
                        0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_nhap_kho_tu_nhan_vien),
                        R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_CHANNEL_ORDER,
                        getString(R.string.menu_kenh_order_hang), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_tao_giay_nop_tien), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_phe_duyet_giay_nop_tien),
                        R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_doi_soat_cong_no_giay_nop_tien),
                        R.drawable.ic_add_black_24dp, 0, 0));
                break;
            case MenuItem.MenuId.MENU_BH_CHAM_SOC_KHACH_HANG:
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_tra_cuu_bao_hanh), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_tiep_nhan_bao_hanh), R.drawable.ic_add_black_24dp,
                        0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_chuyen_muc_bao_hanh), R.drawable.ic_add_black_24dp,
                        0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_survey_kpp), R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_hotnew_cs_kpp), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_kpp_feedback), R.drawable.ic_add_black_24dp, 0, 0));
                break;
            case MenuItem.MenuId.MENU_BAO_CAO:
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_khai_bao_gia_kenh_chan_ret),
                        R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_bao_cao_phat_trien_thue_bao),
                        R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_cham_soc_kenh), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_bao_cao_tan_suat_cham_soc_kenh),
                        R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_bao_cao_ton_kho), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_bao_cao_giao_chi_tieu_ban_hang),
                        R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_giao_chi_tieu_ban_hang),
                        R.drawable.ic_add_black_24dp, 0, 0));
                break;
            case MenuItem.MenuId.MENU_QUAN_LY_DIA_BAN:
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_CREATE_BRANCH,
                        getString(R.string.menu_tao_kpp), R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_quan_ly_dia_ban_hanh_chinh),
                        R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_quan_ly_kpi_kpp), R.drawable.ic_add_black_24dp, 0,
                        0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_quan_ly_thong_tin_kpp),
                        R.drawable.ic_add_black_24dp, 0, 0));
                mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                        getString(R.string.menu_quan_ly_van_ban_chinh_sach),
                        R.drawable.ic_add_black_24dp, 0, 0));
                break;
        }
    }

    public MenuPresenter.OnMenuClickListener getOnMenuClickListener() {
        if (mOnMenuClickListener == null) {
            mOnMenuClickListener = new MenuPresenter.OnMenuClickListener() {
                @Override
                public void onMenuClick(MenuItem item) {
                    // FIXME: Static method for ALL CASE
                    switch (item.getId()) {
                        case MenuItem.MenuId.MENU_LAP_HOA_DON:
                            startActivity(
                                    new Intent(BaseSubMenuActivity.this, BillingActivity.class));
                            break;
                        case MenuItem.MenuId.MENU_TD_LAP_DAT:
                            startActivity(new Intent(BaseSubMenuActivity.this,
                                    InstallationAddressActivity.class));
                            break;
                        case MenuItem.MenuId.MENU_GIAO_VIEC_PHAT_SINH:
                        case MenuItem.MenuId.MENU_GIAO_VIEC_CS_KPP:
                            startActivity(new Intent(BaseSubMenuActivity.this,
                                    ListAssignTaskActivity.class));
                            break;
                        case MenuItem.MenuId.MENU_BAN_HANG_THEO_DON_PHE_DUYET_DON_HANG:
                            startActivity(
                                    new Intent(BaseSubMenuActivity.this, SellOrdersActivity.class));
                            break;
                        case MenuItem.MenuId.MENU_XEM_KHO:
                            startActivity(new Intent(BaseSubMenuActivity.this,
                                    ViewWarehouseActivity.class));
                            break;
                        case MenuItem.MenuId.MENU_SALE_RETAIL:
                            startActivity(
                                    new Intent(BaseSubMenuActivity.this, SaleRetailActivity.class));
                            break;
                        case MenuItem.MenuId.MENU_NHAP_HOA_DON:
                            startActivity(
                                    new Intent(BaseSubMenuActivity.this, InputOrderActivity.class));
                            break;
                        case MenuItem.MenuId.MENU_SALE_CHANNEL:
                            startActivity(new Intent(BaseSubMenuActivity.this,
                                    SaleChannelActivity.class));
                            break;
                        case MenuItem.MenuId.MENU_CHANNEL_ORDER:
                            startActivity(
                                    new Intent(BaseSubMenuActivity.this, KPPOrderActivity.class));
                            break;
                        case MenuItem.MenuId.MENU_CREATE_BRANCH:
                            startActivity(
                                    new Intent(BaseSubMenuActivity.this, BranchesActivity.class));
                            break;
                        case MenuItem.MenuId.MENU_CHANGE_SIM:
                            startActivity(
                                    new Intent(BaseSubMenuActivity.this, ChangeSimActivity.class));
                            break;
                        case MenuItem.MenuId.MENU_SELL_ANYPAY:
                            startActivity(
                                    new Intent(BaseSubMenuActivity.this, SellAnyPayActivity.class));
                            break;

                        case MenuItem.MenuId.MENU_TRANSFER_ANYPAY:
                            startActivity(
                                    new Intent(BaseSubMenuActivity.this, TransferAnyPayActivity.class));
                            break;
                        case MenuItem.MenuId.MENU_DANG_KY_THONG_TIN:
                            Intent intentDKTT = new Intent(BaseSubMenuActivity.this,
                                    CreateUpdateInformationActivity.class);
                            intentDKTT.putExtra(CreateUpdateInformationActivity.ARG_TYPE, true);
                            startActivity(intentDKTT);
                            break;
                        case MenuItem.MenuId.MENU_CAP_NHAT_THONG_TIN:
                            Intent intentCNTT = new Intent(BaseSubMenuActivity.this,
                                    CreateUpdateInformationActivity.class);
                            intentCNTT.putExtra(CreateUpdateInformationActivity.ARG_TYPE, false);
                            startActivity(intentCNTT);
                            break;
                    }
                }
            };
        }
        return mOnMenuClickListener;
    }

    public String getToolbarTitle() {
        return mMenuItem.getTitle();
    }

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
                    binding.text.setTextColor(
                            getResources().getColorStateList(item.getColor(), null));
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
                        onMenuClickListener.onMenuClick(item);
                    }
                }
            });
        }
    }
}
