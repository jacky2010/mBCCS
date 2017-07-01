package com.viettel.mbccs.screen.viewproduct.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.screen.viewproduct.fragment.ViewProductDescriptionFragment;

import java.util.List;

/**
 * Created by minhnx on 6/25/17.
 */

public class ViewProductDetailFragmentAdapter extends FragmentStatePagerAdapter {
    private static int NUM_ITEMS = 3;

    private List<String> mListTitle;
    private List<KeyValue> mListFeatures;
    private List<KeyValue> mListComments;
    private String mDescription;

    public ViewProductDetailFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewProductDetailFragmentAdapter(FragmentManager fm, List<String> listTitle) {
        super(fm);
        this.mListTitle = listTitle;
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    public void setData(String description, List<KeyValue> features, List<KeyValue> comments) {
        mDescription = description;
        mListFeatures = features;
        mListComments = comments;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ViewProductDescriptionFragment.newInstance(mDescription, null, null);
            case 1:
                return ViewProductDescriptionFragment.newInstance(null, mListFeatures, null);
            case 2:
                return ViewProductDescriptionFragment.newInstance(null, null, mListComments);
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mListTitle.get(position);
    }
}
