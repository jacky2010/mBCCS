package com.viettel.mbccs.screen.changesim.dialogs;

import android.os.Bundle;
import android.widget.TextView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDialog;
import com.viettel.mbccs.constance.IconType;
import com.viettel.mbccs.data.model.ChangeSimItem;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import com.viettel.mbccs.widget.ToolBarView;

import butterknife.BindView;
import butterknife.OnClick;

public class DialogUpdatedSimSuccessfulFragment extends BaseDialog {

    @BindView(R.id.toolbar)
    ToolBarView mToolBar;

    @BindView(R.id.txtMessageDetail)
    TextView txtMessageDetail;

    @OnClick(R.id.biv_done)
    public void onDone() {
        dismiss();
    }

    @Override
    protected void initData() {
        try {

            Bundle args = getArguments();

            if (args != null) {
                ChangeSimItem changeSimItem = GsonUtils.String2Object(args.getString(Constants.BundleConstant.CUSTOMER_ITEM), ChangeSimItem.class);
                String total = args.getString(Constants.BundleConstant.TOTAL);
                if(changeSimItem!= null)
                    txtMessageDetail.setText(getString(R.string.change_sim_msg_updated_sim, changeSimItem.getCustomer().getCustomerName()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
                switch (mIconType) {
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
        return R.layout.fragment_updated_sim_success;
    }

    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().setTitleToolbar(R.string.billing_info);
    }
}
