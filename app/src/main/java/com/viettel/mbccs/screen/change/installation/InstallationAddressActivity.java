package com.viettel.mbccs.screen.change.installation;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseActivity;
import com.viettel.mbccs.constance.IconType;
import com.viettel.mbccs.widget.ToolBarView;

import butterknife.BindView;

public class InstallationAddressActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    ToolBarView mToolBar;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_installation_address;
    }

    @Override
    protected void initData() {
        initDataBinder();
        mToolBar.setOnClickIconListener(new ToolBarView.OnClickIconListener() {
            @Override
            public void onClickIconLeft(int mIconType) {
                switch (mIconType){
                    case IconType.LEFT:
                        finish();
                        break;
                    case IconType.RIGHT:
                        break;
                }
            }
        });
        goToFragment(new InstallationAddressFragment(), null);
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
}
