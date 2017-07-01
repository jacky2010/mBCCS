package com.viettel.mbccs.screen.viewproduct.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.screen.viewproduct.fragment.ProductDetailImageFragment;

import java.util.List;

/**
 * Created by minhnx on 6/30/17.
 */

public class ProductImagePagerAdapter extends FragmentStatePagerAdapter {

    private List<KeyValue> mItems;

    public ProductImagePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ProductImagePagerAdapter(FragmentManager fm, List<KeyValue> items) {
        super(fm);

        mItems = items;
    }

    public void setItems(List<KeyValue> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public KeyValue getItemObject(int position) {
        return mItems != null ? mItems.get(position) : null;
    }

    @Override
    public Fragment getItem(int position) {
        return ProductDetailImageFragment.newInstance(mItems.get(position));
    }

    @Override
    public int getCount() {
        return mItems != null ? mItems.size() : 0;
    }
}