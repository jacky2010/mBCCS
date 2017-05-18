package com.viettel.mbccs.screen.main;

import android.content.Context;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.MenuItem;
import com.viettel.mbccs.widget.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.databinding.ObservableField;
import com.viettel.mbccs.screen.sell.orders.SellOrdersActivity;


/**
 * Created by eo_cuong on 5/11/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private Context mContext;

    private MainContract.ViewModel mViewModel;

    public MainPresenter(Context context, MainContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
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
                // TODO: Handle menu item clicked later
            }
        };
    }

    public List<MenuItem> getBottomListItem() {
        // TODO: Query menu from api, currently just a fake data.
        List<MenuItem> list = new ArrayList<>();
        list.add(new MenuItem(mContext.getResources().getString(R.string.menu_dashboard),
                R.drawable.ic_home_24dp));
        list.add(new MenuItem(mContext.getResources().getString(R.string.menu_job),
                R.drawable.ic_job_24dp));
        list.add(new MenuItem(mContext.getResources().getString(R.string.menu_sale),
                R.drawable.ic_shopping_cart_24dp));
        list.add(new MenuItem(mContext.getResources().getString(R.string.menu_customer),
                R.drawable.ic_customer_24dp));
        list.add(new MenuItem(mContext.getResources().getString(R.string.menu_more),
                R.drawable.ic_more_24dp));
        return list;
    }
}
