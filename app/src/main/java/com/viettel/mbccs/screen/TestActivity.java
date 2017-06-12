package com.viettel.mbccs.screen;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseActivity;
import com.viettel.mbccs.constance.IconType;
import com.viettel.mbccs.widget.ToolBarView;
import com.viettel.mbccs.widget.WrappingSlidingDrawer;

import butterknife.BindView;

/**
 * Created by jacky on 6/11/17.
 */

public class TestActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    ToolBarView mToolBar;

    @BindView(R.id.playback_drawer)
    WrappingSlidingDrawer mWrappingSlidingDrawer;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_test;
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
    }



    @Override
    public void setTitleToolbar(int idTitle) {
        super.setTitleToolbar(idTitle);
        mToolBar.setTitle(idTitle);
    }
}
