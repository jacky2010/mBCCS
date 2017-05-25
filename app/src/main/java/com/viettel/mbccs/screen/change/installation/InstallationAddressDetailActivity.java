package com.viettel.mbccs.screen.change.installation;

import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseActivity;
import com.viettel.mbccs.constance.IconType;
import com.viettel.mbccs.widget.ButtonIconView;
import com.viettel.mbccs.widget.ToolBarView;
import com.viettel.mbccs.widget.TopBarView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jacky on 5/22/17.
 */

public class InstallationAddressDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    ToolBarView mToolBar;
    @BindView(R.id.bt_back)
    ButtonIconView mButtonBack;
    @BindView(R.id.bt_next)
    ButtonIconView mButtonNext;

    @BindView(R.id.tbv_survey)
    TopBarView mSurvey;
    @BindView(R.id.tbv_add_new)
    TopBarView mAddNew;
    @BindView(R.id.tbv_facility)
    TopBarView mFacility;

    private int mType;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_installation_address_detail;
    }

    @Override
    protected void initData() {
        initDataBinder();
        mToolBar.setOnClickIconListener(new ToolBarView.OnClickIconListener() {
            @Override
            public void onClickIconLeft(int mIconType) {
                switch (mIconType) {
                    case IconType.LEFT:
                        finish();
                        break;
                    case IconType.RIGHT:
                        break;
                }
            }
        });
        mType = 0;
        callFragment(mType);
        setBgTopBar(mType);
        setTitleToolbar(R.string.title_install_address);
    }

    @Override
    protected int idContainerFragment() {
        return R.id.layout_container;
    }

    @Override
    public void setTitleToolbar(int idTitle) {
        super.setTitleToolbar(idTitle);
        mToolBar.setTitle(idTitle);
    }

    @OnClick({R.id.bt_back, R.id.bt_next})
    public void onClick(View v) {
        int type;
        switch (v.getId()) {
            case R.id.bt_back:
                type = (mType == TypeChangeAddress.CHANGE_ADDRESS_NEW) ? mType : (mType - 1);
                if (TypeChangeAddress.CHANGE_FACILITY > type && type >= TypeChangeAddress.CHANGE_ADDRESS_NEW) {
                    mType = type;
                    onBackFragment();
                }
                break;
            case R.id.bt_next:
                type = ((mType == TypeChangeAddress.CHANGE_FACILITY) ? mType : (mType + 1));
                if (type != mType) {
                    callFragment(type);
                }
                break;
            default:
                break;
        }
        mButtonBack.setButtonText(mType == TypeChangeAddress.CHANGE_ADDRESS_NEW ? R.string.close : R.string.back_item);
        mButtonNext.setButtonText(mType == TypeChangeAddress.CHANGE_FACILITY ? R.string.title_change_add : R.string.next);
        setBgTopBar(mType);
    }

    private void callFragment(int type) {
        mType = type;

        switch (type) {
            case TypeChangeAddress.CHANGE_ADDRESS_NEW:
                goToFragment(new ChangeAddressNewFragment(), null);
                break;
            case TypeChangeAddress.CHANGE_SURVEY:
                goToFragment(new ChangeSurveyFragment(), null);
                break;
            case TypeChangeAddress.CHANGE_FACILITY:
                goToFragment(new ChangeFacilityFragment(), null);
                break;
            default:
                break;
        }
    }

    private void setBgTopBar(int type) {
        mAddNew.setBgTextView(type == TypeChangeAddress.CHANGE_ADDRESS_NEW ? R.drawable.bg_border_item_top : R.drawable.bg_border_item_top_dis);
        mSurvey.setBgTextView(type == TypeChangeAddress.CHANGE_SURVEY ? R.drawable.bg_border_item_top : R.drawable.bg_border_item_top_dis);
        mFacility.setBgTextView(type == TypeChangeAddress.CHANGE_FACILITY ? R.drawable.bg_border_item_top : R.drawable.bg_border_item_top_dis);
    }
}
