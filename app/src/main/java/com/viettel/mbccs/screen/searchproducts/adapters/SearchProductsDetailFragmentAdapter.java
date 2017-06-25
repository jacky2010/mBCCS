package com.viettel.mbccs.screen.searchproducts.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.viettel.mbccs.screen.searchproducts.fragments.CompareProductsDetailFragment;
import com.viettel.mbccs.screen.searchproducts.fragments.ListProductsDetailFragment;

import java.util.List;

/**
 * Created by minhnx on 6/25/17.
 */

public class SearchProductsDetailFragmentAdapter extends FragmentStatePagerAdapter {
    private static int NUM_ITEMS = 2;
    private List<String> listTitle;

    public SearchProductsDetailFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public SearchProductsDetailFragmentAdapter(FragmentManager fm, List<String> listTitle) {
        super(fm);
        this.listTitle = listTitle;
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    public void setData(List<String> listTitle) {
        this.listTitle = listTitle;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ListProductsDetailFragment.newInstance();
            case 1:
                return CompareProductsDetailFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }
}
