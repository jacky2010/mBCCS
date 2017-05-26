package com.viettel.mbccs.screen.transferanypay;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDialog;
import com.viettel.mbccs.constance.IconType;
import com.viettel.mbccs.widget.ToolBarView;

import butterknife.BindView;
import butterknife.OnClick;

public class DialogTransferredSuccessFragment extends BaseDialog {

    @BindView(R.id.toolbar) ToolBarView mToolBar;

    @OnClick(R.id.biv_done)
    public void onDone(){
        dismiss();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getStyleDialog() {
        return 0;
    }

    @Override
    protected void initView() {
        mToolBar.setOnClickIconListener(new ToolBarView.OnClickIconListener() {
            @Override
            public void onClickIconLeft(int mIconType) {
                switch (mIconType){
                    case IconType.LEFT:
                        dismiss();
                        break;
                    case IconType.RIGHT:
                        break;
                }
            }
        });
    }

    @Override
    protected int idLayoutRes() {
        return R.layout.fragment_transferred_success;
    }

    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().setTitleToolbar(R.string.billing_info);
    }
}
