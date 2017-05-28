package com.viettel.mbccs.screen.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableInt;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.MenuItem;
import com.viettel.mbccs.screen.sell.retail.SaleRetailActivity;
import com.viettel.mbccs.widget.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/11/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private Context mContext;

    private MainContract.ViewModel mViewModel;

    private List<MenuItem> mMenuItemList = new ArrayList<>();

    public ObservableInt mLastIndex = new ObservableInt();

    public MainPresenter(Context context, MainContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;

        // TODO: Query menu from api, currently just a fake data.
        mMenuItemList = new ArrayList<>();
        mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                mContext.getResources().getString(R.string.menu_dashboard),
                R.drawable.ic_menu_home_selector, 0, 0));
        mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                mContext.getResources().getString(R.string.menu_job),
                R.drawable.ic_menu_job_selector, 0, 0));
        mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                mContext.getResources().getString(R.string.menu_sale),
                R.drawable.ic_menu_cart_selector, 0, 0));
        mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                mContext.getResources().getString(R.string.menu_customer),
                R.drawable.ic_menu_customer_selector, 0, 0));
        mMenuItemList.add(
                new MenuItem(MenuItem.MenuId.MENU_QLKH, mContext.getString(R.string.menu_qlkh),
                        R.drawable.ic_add_black_24dp, 0, 0));
        mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_QLTC,
                mContext.getString(R.string.menu_quan_ly_thu_cuoc), R.drawable.ic_add_black_24dp, 0,
                0));
        mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_BH_KHO_TC,
                mContext.getString(R.string.menu_bh_kho_tc), R.drawable.ic_add_black_24dp, 0, 0));
        mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_BH_CSKH,
                mContext.getString(R.string.menu_bh_cskh), R.drawable.ic_add_black_24dp, 0, 0));
        mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_BAO_CAO,
                mContext.getString(R.string.menu_bao_cao), R.drawable.ic_add_black_24dp, 0, 0));
        mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_QLDB,
                mContext.getString(R.string.menu_quan_ly_dia_ban), R.drawable.ic_add_black_24dp, 0,
                0));
        mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_HELP,
                mContext.getString(R.string.menu_help), R.drawable.ic_help_outline_24dp, 0, 0));
        mMenuItemList.add(new MenuItem(MenuItem.MenuId.MENU_SETTING,
                mContext.getString(R.string.menu_settings), R.drawable.ic_settings_black_24dp, 0,
                0));
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public BottomNavigationView.OnBottomItemClick getBottomItemClickListener() {
        return new BottomNavigationView.OnBottomItemClick() {
            @Override
            public void onBottomItemClick(int position) {
                // TODO: Handle menu item clicked later. Should use menu Id instead of position.
              //  mContext.startActivity(new Intent(mContext, SaleRetailActivity.class));
                // mContext.startActivity(new Intent(mContext, SaleChannelActivity.class));
                //mContext.startActivity(new Intent(mContext, KPPOrderActivity.class));
                switch (position) {
                    case 0:
                        mViewModel.backToMain();
                        mLastIndex.set(position);
                        break;
                    case 4:
                        mViewModel.gotoMenu();
                        mLastIndex.set(position);
                        break;
                }
            }
        };
    }

    public List<MenuItem> getBottomListItem() {
        if (mMenuItemList.size() <= 4) return mMenuItemList;
        List<MenuItem> subMenu = mMenuItemList.subList(0, 4);
        subMenu.add(new MenuItem(MenuItem.MenuId.MENU_PLACEHOLDER,
                mContext.getResources().getString(R.string.menu_more),
                R.drawable.ic_menu_more_selector, 0, 0));
        return subMenu;
    }

    public ArrayList<MenuItem> getMenuItemList() {
        if (mMenuItemList.size() > 5) {
            return new ArrayList<>(mMenuItemList.subList(5, mMenuItemList.size()));
        }
        return new ArrayList<>();
    }
}
