package com.viettel.mbccs.screen.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableInt;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseSubMenuActivity;
import com.viettel.mbccs.data.model.Function;
import com.viettel.mbccs.widget.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/11/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private Context mContext;

    private MainContract.ViewModel mViewModel;

    private List<Function> mFunctionList = new ArrayList<>();

    public ObservableInt mLastIndex = new ObservableInt();

    public MainPresenter(Context context, MainContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;

        // TODO: Query menu from api, currently just a fake data.
        mFunctionList = new ArrayList<>();
        mFunctionList.add(new Function(Function.TopMenu.MENU_DASHBOARD,
                mContext.getResources().getString(R.string.menu_dashboard),
                R.drawable.ic_menu_home_selector));
        mFunctionList.add(new Function(Function.TopMenu.MENU_QUAN_LY_CONG_VIEC,
                mContext.getResources().getString(R.string.menu_task),
                R.drawable.ic_menu_job_selector));
        mFunctionList.add(new Function(Function.TopMenu.MENU_QUAN_LY_BAN_HANG,
                mContext.getResources().getString(R.string.menu_sale),
                R.drawable.ic_menu_cart_selector));
        mFunctionList.add(new Function(Function.TopMenu.MENU_QUAN_LY_THONG_TIN_KH,
                mContext.getResources().getString(R.string.menu_customer),
                R.drawable.ic_menu_customer_selector));

        mFunctionList.add(new Function(Function.TopMenu.MENU_QUAN_LY_BAN_HANG,
                mContext.getString(R.string.menu_quan_ly_ban_hang), 0));
        mFunctionList.add(new Function(Function.TopMenu.MENU_QUAN_LY_THONG_TIN_KH,
                mContext.getString(R.string.menu_quan_ly_thong_tin_kh), 0));
        mFunctionList.add(new Function(Function.TopMenu.MENU_QUAN_LY_DIA_BAN,
                mContext.getString(R.string.menu_quan_ly_dia_ban), 0));
        mFunctionList.add(new Function(Function.TopMenu.MENU_QUAN_LY_THU_CUOC,
                mContext.getString(R.string.menu_quan_ly_thu_cuoc), 0));
        mFunctionList.add(new Function(Function.TopMenu.MENU_QUAN_LY_CONG_VIEC,
                mContext.getString(R.string.menu_quan_ly_cong_viec), 0));
        mFunctionList.add(new Function(Function.TopMenu.MENU_QUAN_LY_KHO,
                mContext.getString(R.string.menu_quan_ly_kho), 0));
        mFunctionList.add(new Function(Function.TopMenu.MENU_QUAN_LY_BAO_HANH,
                mContext.getString(R.string.menu_quan_ly_bao_hanh), 0));
        mFunctionList.add(new Function(Function.TopMenu.MENU_QUAN_LY_CSKH,
                mContext.getString(R.string.menu_quan_ly_cham_soc_kh), 0));
        mFunctionList.add(new Function(Function.TopMenu.MENU_QUAN_LY_TAI_CHINH,
                mContext.getString(R.string.menu_quan_ly_tai_chinh), 0));
        mFunctionList.add(new Function(Function.TopMenu.MENU_BAO_CAO,
                mContext.getString(R.string.menu_bao_cao), 0));

        mFunctionList.add(
                new Function(Function.TopMenu.MENU_HELP, mContext.getString(R.string.menu_help),
                        R.drawable.ic_help_outline_24dp));
        mFunctionList.add(new Function(Function.TopMenu.MENU_SETTINGS,
                mContext.getString(R.string.menu_settings), R.drawable.ic_settings_black_24dp));
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
                switch (position) {
                    case 0:
                        mViewModel.backToMain();
                        mLastIndex.set(position);
                        break;
                    case 1:
                    case 2:
                    case 3:
                        Intent intent = new Intent(mContext, BaseSubMenuActivity.class);
                        intent.putExtra(BaseSubMenuActivity.EXTRA_MENU_ITEM,
                                mFunctionList.get(position));
                        mContext.startActivity(intent);
                        break;
                    case 4:
                        mViewModel.gotoMenu();
                        mLastIndex.set(position);
                        break;
                }
            }
        };
    }

    public List<Function> getBottomListItem() {
        if (mFunctionList.size() <= 4) return mFunctionList;
        List<Function> subMenu = mFunctionList.subList(0, 4);
        subMenu.add(new Function(Function.TopMenu.MENU_MORE,
                mContext.getResources().getString(R.string.menu_more),
                R.drawable.ic_menu_more_selector));
        return subMenu;
    }

    public ArrayList<Function> getFunctionList() {
        if (mFunctionList.size() > 5) {
            return new ArrayList<>(mFunctionList.subList(5, mFunctionList.size()));
        }
        return new ArrayList<>();
    }

    public void settingsClick() {
        mViewModel.gotoSettings();
    }
}
