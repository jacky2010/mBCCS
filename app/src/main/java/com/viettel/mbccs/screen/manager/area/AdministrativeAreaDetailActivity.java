package com.viettel.mbccs.screen.manager.area;

import android.os.Bundle;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.AreaType;
import com.viettel.mbccs.screen.manager.area.dialog.DialogSearchBTSFragment;
import com.viettel.mbccs.widget.ItemSpinText;
import com.viettel.mbccs.widget.ItemViewArea;
import com.viettel.mbccs.widget.SearchViewCustom;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jacky on 6/5/17.
 */

public class AdministrativeAreaDetailActivity extends AreaChanelDetailActivity {

    @BindView(R.id.search_view)
    SearchViewCustom mSearchViewCustom;

    @BindView(R.id.the_provincial)
    ItemSpinText mProvincial;

    @BindView(R.id.the_district)
    ItemSpinText mdDistrict;

    @BindView(R.id.iva_provincial)
    ItemViewArea mProvince;

    @BindView(R.id.iva_ttkd)
    ItemViewArea mTtkd;

    @BindView(R.id.iva_bts)
    ItemViewArea mBts;

    @BindView(R.id.iva_clh)
    ItemViewArea mFestival;

    private FragmentAdministrative mFragmentAdministrative;

    private int mAreaType;

    @Override
    protected void initData() {
        super.initData();
        mToolBar.setTitle(mArea.getFullName());

        mSearchViewCustom.setItemData(fakeSearch());
        mSearchViewCustom.setOnClickItem(new SearchViewCustom.OnClickItemSearchViewListener<String>() {
            @Override
            public void onResult(String data) {
                goToDialogFragment(new DialogSearchBTSFragment(), null);
            }
        });

        mProvincial.setListSpinner(fakeSearch());
        mdDistrict.setListSpinner(fakeSearch());

        mAreaType = AreaType.TAB_TH;
        setBgClickTab(mProvince, mTtkd, mBts, mFestival);

        initMap();
    }

    private void initMap() {
        mFragmentAdministrative = new FragmentAdministrative();
        Bundle mBundle = new Bundle();
        mBundle.putInt(AreaType.class.getName(), mAreaType);
        goToFragment(mFragmentAdministrative, mBundle);
    }

    private List<String> fakeSearch() {
        List<String> list = new ArrayList<>();
        list.add("BCS 001");
        list.add("BCS 002");
        list.add("BCS 003");
        list.add("BCS 004");
        list.add("BCS 005");
        list.add("BCS 006");
        list.add("BCS 007");
        list.add("BCS 008");
        list.add("BCS 009");
        list.add("BCS 0010");
        list.add("BCS 0011");
        list.add("BCS 0012");
        return list;
    }

    @OnClick({R.id.iva_provincial, R.id.iva_ttkd, R.id.iva_bts, R.id.iva_clh})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iva_provincial:
                mAreaType = AreaType.TAB_TH;
                setBgClickTab(mProvince, mTtkd, mBts, mFestival);
                break;
            case R.id.iva_ttkd:
                mAreaType = AreaType.TAB_TTKD;
                setBgClickTab(mTtkd, mProvince, mBts, mFestival);
                break;
            case R.id.iva_bts:
                mAreaType = AreaType.TAB_BTS;
                setBgClickTab(mBts, mProvince, mTtkd, mFestival);
                break;
            case R.id.iva_clh:
                mAreaType = AreaType.TAB_CLH;
                setBgClickTab(mFestival, mProvince, mTtkd, mBts);
                break;
            default:
                break;
        }

    }

    public void setBgClickTab(ItemViewArea iva_1, ItemViewArea iva_2, ItemViewArea iva_3, ItemViewArea iva_4) {
        iva_1.setBackgroundResource(R.drawable.bg_item_area_select);
        iva_2.setBackgroundResource(R.drawable.bg_item_area_unselect);
        iva_3.setBackgroundResource(R.drawable.bg_item_area_unselect);
        iva_4.setBackgroundResource(R.drawable.bg_item_area_unselect);
        if (mFragmentAdministrative != null) {
            mFragmentAdministrative.setAreaType(mAreaType);
        }
    }

    @Override
    protected int getIdLayout() {
        return R.layout.activity_administrative_area_detail;
    }

    @Override
    protected int idContainerFragment() {
        return R.id.layout_container;
    }
}
