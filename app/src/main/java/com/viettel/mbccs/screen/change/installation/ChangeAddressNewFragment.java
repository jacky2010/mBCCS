package com.viettel.mbccs.screen.change.installation;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.Area;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.screen.change.BaseChangeAddressFragment;
import com.viettel.mbccs.widget.ItemSpinText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by jacky on 5/24/17.
 */

public class ChangeAddressNewFragment extends BaseChangeAddressFragment implements ItemSpinText.OnSelectSpinnerListener {

    @BindView(R.id.ist_provincial)
    ItemSpinText mProvincial;
    @BindView(R.id.ist_district)
    ItemSpinText mDistrict;
    @BindView(R.id.ist_precinct)
    ItemSpinText mPrecinct;
    private UserRepository mUserRepository;

    private List<Area> areaProvinceList = new ArrayList<>();
    private List<Area> areaDistrictList = new ArrayList<>();
    private List<Area> areaPrecinctList = new ArrayList<>();
    private List<String> mDataProvince = new ArrayList<>();
    private List<String> mDataDistrict = new ArrayList<>();
    private List<String> mDataPrecinct = new ArrayList<>();

    @Override
    protected void initData() {
        mUserRepository = UserRepository.getInstance();
        if (mUserRepository != null) {
            areaProvinceList = mUserRepository.getListAreaProvince();
            if (areaProvinceList != null) {
                for (Area p : areaProvinceList) {
                    mDataProvince.add(p.getName());
                }
                mProvincial.setListSpinner(mDataProvince);
            }
        }
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_change_add_new;
    }

    @Override
    protected void initView() {
        mProvincial.setOnSelectSpinnerListener(this);
        mDistrict.setOnSelectSpinnerListener(this);
    }


    private void getDataAreaDistrict(String parentCode) {
        areaDistrictList = mUserRepository.getListDistrictByProvinceId(parentCode);
        if (mDataDistrict != null && mDataDistrict.size() != 0) mDataDistrict.clear();
        for (Area d : areaDistrictList) {
            mDataDistrict.add(d.getName());
        }
        mDistrict.setListSpinner(mDataDistrict);
    }

    @Override
    public void onItemSelect(ItemSpinText itemSpinText, int position) {
        if (itemSpinText == mProvincial) {
            System.out.println("itemSpinText" + position);
            getDataAreaDistrict(areaProvinceList.get(position).getProvince());
        }
    }
}