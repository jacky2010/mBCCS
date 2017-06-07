package com.viettel.mbccs.screen.manager.area;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseActivity;
import com.viettel.mbccs.constance.IconType;
import com.viettel.mbccs.data.model.Area;
import com.viettel.mbccs.widget.ToolBarView;

import butterknife.BindView;

/**
 * Created by jacky on 6/5/17.
 */
public class AreaChanelDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar) ToolBarView mToolBar;

    protected Area mArea;

    @Override
    protected int getIdLayout() {
        return 0;
    }

    @Override
    protected void initData() {
        initDataBinder();
        if(getIntent()!=null && getIntent().getExtras().containsKey(Area.class.getName())){
            mArea = getIntent().getExtras().getParcelable(Area.class.getName());
        }
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
    }
}
