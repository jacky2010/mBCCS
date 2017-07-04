package com.viettel.mbccs.base;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.Function;
import com.viettel.mbccs.data.model.LoginInfo;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.databinding.ActivitySubMenuBinding;
import com.viettel.mbccs.databinding.ItemGridMenuBinding;
import com.viettel.mbccs.databinding.ItemMenuBinding;
import com.viettel.mbccs.screen.assigntask.ListAssignTaskActivity;
import com.viettel.mbccs.screen.billing.BillingActivity;
import com.viettel.mbccs.screen.branches.BranchesActivity;
import com.viettel.mbccs.screen.change.installation.InstallationAddressActivity;
import com.viettel.mbccs.screen.changesim.ChangeSimActivity;
import com.viettel.mbccs.screen.connector.mobile.ConnectorMobileActivity;
import com.viettel.mbccs.screen.hotnewscskpp.HotNewsCSKPPActivity;
import com.viettel.mbccs.screen.information.CreateUpdateInformationActivity;
import com.viettel.mbccs.screen.inputorder.InputOrderActivity;
import com.viettel.mbccs.screen.kpp.order.KPPOrderActivity;
import com.viettel.mbccs.screen.kppfeedback.KPPFeedbackActivity;
import com.viettel.mbccs.screen.main.fragments.menu.MenuPresenter;
import com.viettel.mbccs.screen.nhapkhocapduoi.ListOrderActivity;
import com.viettel.mbccs.screen.nvtrahangcaptren.list.NVTraHangCapTrenActivity;
import com.viettel.mbccs.screen.searchproducts.SearchProductsActivity;
import com.viettel.mbccs.screen.sell.channel.SaleChannelActivity;
import com.viettel.mbccs.screen.sell.orders.SellOrdersActivity;
import com.viettel.mbccs.screen.sell.retail.SaleRetailActivity;
import com.viettel.mbccs.screen.sellanypay.SellAnyPayActivity;
import com.viettel.mbccs.screen.stockdeliver.TestStockDeliveryActivity;
import com.viettel.mbccs.screen.survey.SurveyListActivity;
import com.viettel.mbccs.screen.trahangcaptren.ListOrderReturnUpperActivity;
import com.viettel.mbccs.screen.transferanypay.TransferAnyPayActivity;
import com.viettel.mbccs.screen.viewwarehouse.ViewWarehouseActivity;
import com.viettel.mbccs.screen.xuathangchonhanvien.ChiTietXuatKhoNhanVienActivity;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/19/2017.
 */

public class BaseSubMenuActivity
        extends BaseDataBindActivity<ActivitySubMenuBinding, BaseSubMenuActivity> {

    public static final String EXTRA_MENU_ITEM = "EXTRA_MENU_ITEM";

    protected Function mFunction;

    protected List<Function> mFunctionList = new ArrayList<>();

    protected GridLayoutManager mGridLayoutManager;

    protected LinearLayoutManager mLinearLayoutManager;

    //    protected RecyclerView.ItemDecoration mItemDecoration;

    protected MenuPresenter.MenuAdapter mMenuAdapter;

    protected MenuPresenter.OnMenuClickListener mOnMenuClickListener;

    public ObservableBoolean isGrid;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_sub_menu;
    }

    @Override
    protected void initData() {
        mFunction = getIntent().getParcelableExtra(EXTRA_MENU_ITEM);
        isGrid = new ObservableBoolean();
        isGrid.set(false);
        mGridLayoutManager = new GridLayoutManager(this, 3);
        mLinearLayoutManager = new LinearLayoutManager(this);
        //        mItemDecoration = new SpacesItemDecoration(
        //                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
        //                        getResources().getDimension(R.dimen.dp_0_6),
        //                        getResources().getDisplayMetrics()), mGridLayoutManager);
        initMenuList();
        mMenuAdapter = new SubMenuAdapter(this, mFunctionList);
        mBinding.setPresenter(this);
        mBinding.executePendingBindings();
        switchView();
    }

    public void initMenuList() {
        LoginInfo loginInfo = UserRepository.getInstance().getUser();
        for (Function f : loginInfo.getFunction()) {
            if (f.getParentCode().equals(mFunction.getFunctionCode())) {
                if (!mFunctionList.contains(f)) {
                    mFunctionList.add(f);
                }
            }
        }
    }

    public MenuPresenter.OnMenuClickListener getOnMenuClickListener() {
        if (mOnMenuClickListener == null) {
            mOnMenuClickListener = new MenuPresenter.OnMenuClickListener() {
                @Override
                public void onMenuClick(Function item) {
                    // FIXME: Static method for ALL CASE
                    switch (item.getFunctionCode()) {
                        case Function.MenuId.MENU_BAN_LE:
                            startActivity(
                                    new Intent(BaseSubMenuActivity.this, SaleRetailActivity.class));
                            break;
                        case Function.MenuId.MENU_BAN_CHO_KENH:
                            startActivity(new Intent(BaseSubMenuActivity.this,
                                    SaleChannelActivity.class));
                            break;
                        case Function.MenuId.MENU_BAN_HANG_THEO_DON_PHE_DUYET_DON_HANG:
                            startActivity(
                                    new Intent(BaseSubMenuActivity.this, SellOrdersActivity.class));
                            break;
                        case Function.MenuId.MENU_BAN_DICH_VU_VAS:
                            break;
                        case Function.MenuId.MENU_LAP_HOA_DON:
                            startActivity(
                                    new Intent(BaseSubMenuActivity.this, BillingActivity.class));
                            break;
                        case Function.MenuId.MENU_DAU_NOI_DI_DONG:
                            startActivity(new Intent(BaseSubMenuActivity.this,
                                    ConnectorMobileActivity.class));
                            break;
                        case Function.MenuId.MENU_DAU_NOI_CO_DINH:
                            // TODO: 08/06/2017 Fake
                            startActivity(new Intent(BaseSubMenuActivity.this,
                                    ConnectorMobileActivity.class));
                            break;
                        case Function.MenuId.MENU_BAN_ANYPAY:
                            startActivity(
                                    new Intent(BaseSubMenuActivity.this, SellAnyPayActivity.class));
                            break;
                        case Function.MenuId.MENU_NAP_CHUYEN_ANYPAY:
                            startActivity(new Intent(BaseSubMenuActivity.this,
                                    TransferAnyPayActivity.class));
                            break;
                        case Function.MenuId.MENU_DANG_KY_THONG_TIN:
                            Intent intentDKTT = new Intent(BaseSubMenuActivity.this,
                                    CreateUpdateInformationActivity.class);
                            intentDKTT.putExtra(CreateUpdateInformationActivity.ARG_TYPE, true);
                            startActivity(intentDKTT);
                            break;
                        case Function.MenuId.MENU_CAP_NHAT_THONG_TIN:
                            Intent intentCNTT = new Intent(BaseSubMenuActivity.this,
                                    CreateUpdateInformationActivity.class);
                            intentCNTT.putExtra(CreateUpdateInformationActivity.ARG_TYPE, false);
                            startActivity(intentCNTT);
                            break;
                        case Function.MenuId.MENU_DOI_SIM:
                            startActivity(
                                    new Intent(BaseSubMenuActivity.this, ChangeSimActivity.class));
                            break;
                        case Function.MenuId.MENU_THAY_DOI_DIA_CHI_LAP_DAT:
                            startActivity(new Intent(BaseSubMenuActivity.this,
                                    InstallationAddressActivity.class));
                            break;
                        // TODO: 6/10/17 upload image offline
                        //                        case Function.MenuId.MENU_UPLOAD_ANH:
                        //                            startActivity(new Intent(BaseSubMenuActivity.this,
                        //                                    UploadImageActivity.class));
                        //                            break;

                        case Function.MenuId.MENU_TAO_KENH_PHAN_PHOI:
                            startActivity(
                                    new Intent(BaseSubMenuActivity.this, BranchesActivity.class));
                            break;
                        case Function.MenuId.MENU_QUAN_LY_DBHC_BTS_KENH:
                            break;
                        case Function.MenuId.MENU_QUAN_LY_KPI_KPP:
                            break;
                        case Function.MenuId.MENU_QUAN_LY_THONG_TIN_KPP:
                            break;
                        case Function.MenuId.MENU_QUAN_LY_VAN_BAN_CSTT:
                            break;

                        case Function.MenuId.MENU_XAC_MINH:
                            break;
                        case Function.MenuId.MENU_GACH_NO:
                            break;
                        case Function.MenuId.MENU_THU_CUOC_NONG:
                            break;
                        case Function.MenuId.MENU_QUAN_LY_TIEN_DO_THU_CUOC:
                            break;

                        //                        case Function.MenuId.MENU_GIAO_VIEC_TO_DOI:
                        //                            break;
                        case Function.MenuId.MENU_GIAO_VIEC_PHAT_SINH:
                        case Function.MenuId.MENU_GIAO_VIEC_CS_KPP:
                        case Function.MenuId.MENU_DONG_VIEC:
                            startActivity(new Intent(BaseSubMenuActivity.this,
                                    ListAssignTaskActivity.class));
                            break;

                        case Function.MenuId.MENU_XEM_KHO:
                            startActivity(new Intent(BaseSubMenuActivity.this,
                                    ViewWarehouseActivity.class));
                            break;
                        case Function.MenuId.MENU_NHAP_HOA_DON:
                            startActivity(
                                    new Intent(BaseSubMenuActivity.this, InputOrderActivity.class));
                            break;
                        case Function.MenuId.MENU_XUAT_KHO_CAP_DUOI:
                            Intent stockDelivery = new Intent(BaseSubMenuActivity.this,
                                    TestStockDeliveryActivity.class);
                            StockTrans stockTransData = new StockTrans();
                            stockTransData.setStockTransId(1237);
                            stockTransData.setToOwnerId(1232);
                            stockTransData.setCreateDateTime("2017-01-02");
                            stockTransData.setStockTransStatusName("hang moi");
                            Bundle stockbundle = new Bundle();
                            stockbundle.putParcelable(Constants.BundleConstant.STOCK_TRANS, stockTransData);
                            stockDelivery.putExtras(stockbundle);
                            startActivity(stockDelivery);
                            break;
                        case Function.MenuId.MENU_NHAP_KHO_CAP_TREN:
                            break;
                        case Function.MenuId.MENU_TRA_HANG_CAP_TREN:
                            startActivity(new Intent(BaseSubMenuActivity.this,
                                    ListOrderReturnUpperActivity.class));
                            break;
                        case Function.MenuId.MENU_NHAP_KHO_CAP_DUOI:
                            startActivity(
                                    new Intent(BaseSubMenuActivity.this, ListOrderActivity.class));
                            break;
                        case Function.MenuId.MENU_XUAT_KHO_CHO_NHAN_VIEN:
                            break;
                        case Function.MenuId.MENU_NV_XAC_NHAN_HANG:
                            break;
                        case Function.MenuId.MENU_NHAN_VIEN_TRA_HANG_CAP_TREN:
                            startActivity(new Intent(BaseSubMenuActivity.this,
                                    NVTraHangCapTrenActivity.class));
                            break;
                        case Function.MenuId.MENU_NHAP_KHO_TU_NHAN_VIEN:
                            break;
                        case Function.MenuId.MENU_KENH_ORDER_HANG:
                            startActivity(
                                    new Intent(BaseSubMenuActivity.this, KPPOrderActivity.class));
                            break;

                        case Function.MenuId.MENU_TRA_CUU:
                            break;
                        //                        case Function.MenuId.MENU_TIEP_NHAN_BH:
                        //                            break;
                        case Function.MenuId.MENU_CHUYEN_MUC_BH:
                            break;
                        case Function.MenuId.MENU_TRA_BH:
                            break;

                        case Function.MenuId.MENU_SURVEY_KPP:
                            startActivity(
                                    new Intent(BaseSubMenuActivity.this, SurveyListActivity.class));
                            //                            startActivity(
                            //                                    new Intent(BaseSubMenuActivity.this, SurveyActivity.class));
                            break;
                        case Function.MenuId.MENU_HOTNEW_CS_KPP:
                            startActivity(new Intent(BaseSubMenuActivity.this,
                                    HotNewsCSKPPActivity.class));
                            break;
                        case Function.MenuId.MENU_KPP_FEEDBACK:
                            startActivity(new Intent(BaseSubMenuActivity.this,
                                    KPPFeedbackActivity.class));
                            break;
                        case Function.MenuId.MENU_TRA_CUU_SP:
                            startActivity(new Intent(BaseSubMenuActivity.this,
                                    SearchProductsActivity.class));
                            break;

                        case Function.MenuId.MENU_TAO_GIAY_NOP_TIEN:
                            break;
                        case Function.MenuId.MENU_PHE_DUYET_GIAY_NOP_TIEN:
                            break;
                        case Function.MenuId.MENU_DOI_SOAT_CONG_NO_GIAY_NOP_TIEN:
                            break;
                        case Function.MenuId.MENU_KHAI_BAO_GIA_KENH_CHAN_RET:
                            break;

                        //                        case Function.MenuId.MENU_BAO_CAO_PHAT_TRIEN_THUE_BAO:
                        //                            break;
                        case Function.MenuId.MENU_BAO_CAO_CHAM_SOC_KENH:
                            break;
                        case Function.MenuId.MENU_BAO_CAO_TAN_SUAT_CHAM_SOC_KENH:
                            break;
                        case Function.MenuId.MENU_BAO_CAO_TON_KHO:
                            break;
                        case Function.MenuId.MENU_BAO_CAO_GIAO_CHI_TIEU_BAN_HANG:
                            Intent intent = new Intent(BaseSubMenuActivity.this,
                                    ChiTietXuatKhoNhanVienActivity.class);
                            StockTrans stockTrans = new StockTrans();
                            stockTrans.setStockTransId(1237);
                            stockTrans.setToOwnerId(1232);
                            stockTrans.setCreateDateTime("2017-01-02");
                            stockTrans.setStockTransStatusName("hang moi");
                            Bundle bundle = new Bundle();
                            bundle.putParcelable(Constants.BundleConstant.STOCK_TRANS, stockTrans);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            break;
                    }
                }
            };
        }
        return mOnMenuClickListener;
    }

    public String getToolbarTitle() {
        return mFunction.getFunctionName();
    }

    public void switchView() {
        isGrid.set(!isGrid.get());
        if (isGrid.get()) {
            mBinding.subMenuView.setLayoutManager(mGridLayoutManager);
            //            mBinding.subMenuView.addItemDecoration(mItemDecoration);
        } else {
            mBinding.subMenuView.setLayoutManager(mLinearLayoutManager);
            //            mBinding.subMenuView.removeItemDecoration(mItemDecoration);
        }
        mBinding.subMenuView.setAdapter(mMenuAdapter);
    }

    public MenuPresenter.MenuAdapter getMenuAdapter() {
        return mMenuAdapter;
    }

    public class SubMenuAdapter extends MenuPresenter.MenuAdapter {
        public SubMenuAdapter(Context context, List<Function> list) {
            super(context, list);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (!isGrid.get()) {
                return new SubMenuListViewHolder(
                        ItemMenuBinding.inflate(getLayoutInflater(), parent, false));
            } else {
                return new SubMenuGridViewHolder(
                        ItemGridMenuBinding.inflate(getLayoutInflater(), parent, false));
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
        private ItemGridMenuBinding binding;

        private MenuPresenter.OnMenuClickListener onMenuClickListener;

        public SubMenuGridViewHolder(ItemGridMenuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            onMenuClickListener = getOnMenuClickListener();
        }

        public void bind(final Function item) {
            binding.setImageText(item.getFunctionName());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                binding.setImage(
                        binding.getRoot().getResources().getDrawable(item.getIcon(), null));
            } else {
                binding.setImage(binding.getRoot().getResources().getDrawable(item.getIcon()));
            }
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
